
package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.server.gameObjects.loot.loot1;
import org.academiadecodigo.roothless.server.gameObjects.monsters.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;
import org.academiadecodigo.roothless.server.gameObjects.monsters.TestMonster;
import org.academiadecodigo.roothless.server.level.CombatRoom;
import org.academiadecodigo.roothless.server.level.MixedRoom;
import org.academiadecodigo.roothless.server.level.QuizRoom;
import org.academiadecodigo.roothless.server.level.Room;
import org.academiadecodigo.roothless.serverParser.Strategy;

import java.io.*;
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
    private int maxRooms = 2;
    private LinkedBlockingQueue<Strategy> queue;
    volatile private int countAction;
    volatile private boolean showLoot;
    private String currentLore;
    private boolean started;


    public void enterDungeon() throws IOException {
        monsterArray.add(new TestMonster(this));
        lootArray.add(new loot1());

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
            while (room.getLoot() != null) {        // TODO: 06/03/17 use a wait
            }
            System.out.println("loot sucessful");
        }
        System.out.println("room cleared");
        queue.clear();
        level++;
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

        BufferedReader readLore = new BufferedReader(new FileReader("resources/lore1"));

        String line = "";
        String result = "";

        while ((line = readLore.readLine()) != null) {
            result += line + " ";
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
        int randomIndex = (int) (Math.random() * (monsterArray.size() - 1));
        Monster returnMonster = monsterArray.get(randomIndex);
        monsterArray.remove(randomIndex);
        return returnMonster;
    }

    private Loot randomLoot() {
        int randomIndex = (int) (Math.random() * (lootArray.size() - 1));
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

    public List<Monster> getMonsterArray() {
        return monsterArray;
    }

    public void setMonsterArray(ArrayList<Monster> monsterArray) {
        this.monsterArray = monsterArray;
    }

    public List<Loot> getLootArray() {
        return lootArray;
    }

    public void setLootArray(List<Loot> lootArray) {
        this.lootArray = lootArray;
    }

    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getCountAction() {
        return countAction;
    }

    public void setCountAction(int countAction) {
        this.countAction = countAction;
    }

}

