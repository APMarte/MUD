
package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.server.gameObjects.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;
import org.academiadecodigo.roothless.server.level.CombatRoom;
import org.academiadecodigo.roothless.server.level.MixedRoom;
import org.academiadecodigo.roothless.server.level.QuizRoom;
import org.academiadecodigo.roothless.server.level.Room;

import java.util.ArrayList;
import java.util.List;

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


   /* public void enterDungeon() {
        while ((level - 1) < maxRooms) {
            double rng = Math.random();
            if (rng <= 0.33) {
                room = new CombatRoom().run();
            } else if (rng >= 0.34 && rng <= 0.66) {
                room = new QuizRoom().run();
            } else {
                room = new MixedRoom().run();
            }
            level++;
        }
    }*/


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
}

