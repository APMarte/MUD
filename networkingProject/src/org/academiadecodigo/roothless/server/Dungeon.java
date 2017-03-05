
package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.server.gameObjects.monsters.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;
import org.academiadecodigo.roothless.server.level.CombatRoom;
import org.academiadecodigo.roothless.server.level.MixedRoom;
import org.academiadecodigo.roothless.server.level.QuizRoom;
import org.academiadecodigo.roothless.server.level.Room;
import org.academiadecodigo.roothless.serverParser.Strategy;

import java.util.ArrayList;
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
    private Room room;
    private int level = 1;
    private boolean hasEnded;
    private int maxRooms = 1;
    private LinkedBlockingQueue<Strategy> queue;
    private int countAction;

    public Dungeon(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public void enterDungeon() {
        while ((level - 1) < maxRooms) {
            //double rng = Math.random();
            //if (rng <= 0.33) {
            room = new CombatRoom(randomMonster(), randomLoot(), this);
            room.run();
            /*} else if (rng >= 0.34 && rng <= 0.66) {
                room = new QuizRoom().run();
            } else {
                room = new MixedRoom().run();
            }*/
            level++;
        }
    }

    public LinkedBlockingQueue<Strategy> getQueue() {
        return queue;
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

    public String readStrategy(){

        System.out.println("oix");

        return queue.poll().run();
    }

    public String checkActions() {

        if(countAction<5){
            return readStrategy();
        }else{
            countAction=0;
            return "/modify hasActed";
        }

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
}

