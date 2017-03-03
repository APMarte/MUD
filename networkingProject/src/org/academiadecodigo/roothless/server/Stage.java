
package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.server.gameObjects.Monster;

import java.util.ArrayList;

/**
/* Created by codecadet on 01/03/17.
*/
/**/


public class Stage {

    private Game game;
    private ArrayList<Monster> monsterArray;
    private Monster monster;
    //private ArrayList<Loot> lootArray;
    //private Loot loot;
    private String[] questions;
    private String question;

    public int getLevel() {
        return level;
    }

    private int level = 1;


    public void init () {

    }

    public static void enterDungeon() {

    }

    private void room1() {
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
                "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " +
                "qui officia deserunt mollit anim id est laborum.";
        //monster =;
        monsterArray.remove(monster);
        //loot =;

        //broadcast(description);

        synchronized (this) {
            try {
                while (monster != null) {
                    wait();
                }

                while (question != null) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        setupNextRoom();
        room2();
    }

    private void room2() {


        //room3();
    }

    private void setupNextRoom() {
        monster = null;
        //loot = null;
        question = null;
        level++;
    }

}

