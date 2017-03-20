
package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.server.gameObjects.loot.LooType;
import org.academiadecodigo.roothless.server.gameObjects.loot.loot1;
import org.academiadecodigo.roothless.server.gameObjects.monsters.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;
import org.academiadecodigo.roothless.server.gameObjects.monsters.TestMonster;
import org.academiadecodigo.roothless.server.gameObjects.monsters.TestMonster2;
import org.academiadecodigo.roothless.server.gameObjects.monsters.TestMonster3;
import org.academiadecodigo.roothless.server.level.CombatRoom;
import org.academiadecodigo.roothless.server.level.Room;
import org.academiadecodigo.roothless.serverParser.Strategy;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * /* Created by codecadet on 01/03/17.
 */
/**/


public class Dungeon {


    private List<Monster> monsterArray;
    private List<Loot> lootArray;
    private String[] questions;
    volatile private Room room;
    private int level = 1;
    private boolean hasEnded;
    private int maxRooms = 3;
    private LinkedBlockingQueue<Strategy> queue;
    volatile private int countAction;
    volatile private boolean showLoot;
    private String currentLore;
    private boolean started;


    public void enterDungeon() throws IOException {

        init();

        while ((level - 1) < maxRooms) {
            //double rng = Math.random();
            //if (rng <= 0.33)
            //{
            currentLore = getLore(level);
            System.out.println("entered room " + level);
            room = new CombatRoom(randomMonster(), randomLoot(), this);
            setStarted(true);
            setPrintDescription(true);
            room.run();
            showLoot = true;
            System.out.println("loot was set // before while loot || show loot -> " + showLoot);
            //broadcast loot description
            synchronized (this) {
                while (room.getLoot() != null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("loot sucessful");
            System.out.println("room cleared");
            queue.clear();
            level++;
        }
    }




    public Dungeon(LinkedBlockingQueue queue) {
        this.queue = queue;
        monsterArray = new LinkedList<>();
        lootArray = new LinkedList<>();

    }

    public String getCurrentLore() {
        return currentLore;
    }

    public String getLore(int level) throws IOException {
        //BufferedReader readLore = new BufferedReader(new FileReader("resources/lore/room1"));
        String path = ("resources/lore/room" + level);
        URL resource = getClass().getResource(path.startsWith("/") ? path : "/" + path);
        BufferedReader readLore = new BufferedReader(new InputStreamReader(resource.openStream()));
        String line = "";
        String result = "";

        while ((line = readLore.readLine()) != null) {
            result += line + "\n";
        }

        readLore.close();
        return result;

    }

    public String checkActions() {

        String rStrat = null;

        if (countAction <= 4 && room.getMonster() != null) {
            System.out.println("first if / count at " + countAction);
            rStrat = readStrategy();
        }

        if (room.getMonster() == null || countAction >= 4) {
            countAction = 0;
            if (room.getLoot() != null) {
                return readStrategy();
            }
            //monsterAttack = false;
            if (rStrat != null) {
                System.out.println("in rstrat dif null -> " + rStrat);
                return "/modify hasActed |" + rStrat;
            } else {
                return "/modify hasActed";
            }
        } else {
            System.out.println("else returning strat / count at " + countAction);
            countAction++;
            return rStrat;
        }

    }

    public String readStrategy() {

        return queue.poll().run();
    }

    public String monsterOutput() {
        return room.getMonster().attack();
    }

    private Monster randomMonster() {
        int randomIndex = (int) (Math.random() * (monsterArray.size()));
        Monster returnMonster = monsterArray.get(randomIndex);
        monsterArray.remove(randomIndex);
        return returnMonster;
    }

    private Loot randomLoot() {
        int randomIndex = (int) (Math.random() * (lootArray.size()));
        Loot returnLoot = lootArray.get(randomIndex);
        lootArray.remove(randomIndex);
        return returnLoot;
    }

    public LinkedBlockingQueue<Strategy> getQueue() {
        return queue;
    }
    public boolean isShowLoot() {
        return showLoot;
    }

    public void setShowLoot(boolean showLoot) {
        this.showLoot = showLoot;
    }

    public boolean isPrintDescription() {
        return printDescription;

    }

    public void setPrintDescription(boolean printDescription) {
        this.printDescription = printDescription;
    }

    private boolean printDescription;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public int getLevel() {
        return level;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    private void init() throws IOException {
        monsterArray.add(new TestMonster(this));
        monsterArray.add(new TestMonster2(this));
        monsterArray.add(new TestMonster3(this));
        lootArray.add(new loot1(LooType.LOOT1));
        lootArray.add(new loot1(LooType.LOOT2));
        lootArray.add(new loot1(LooType.LOOT3));

    }

}

