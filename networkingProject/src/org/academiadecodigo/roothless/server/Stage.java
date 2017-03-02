package org.academiadecodigo.roothless.server;

import org.academiadecodigo.roothless.server.gameObjects.Monster;

import java.util.ArrayList;

/**
 * Created by codecadet on 01/03/17.
 */
public class Stage {

    private Game game;
    private ArrayList<Monster> monsterArray;
    private Monster monster;
    private ArrayList<Loot> lootArray;
    private Loot loot;
    private String[] questions;
    private String question;


    public Stage(Game game) {
        this.monsterArray =
    }

    public static void enterDungeon() {

    }

    private void room1() {
        String description = ;
        monster =;
        monsterArray.remove(monster);
        loot =;

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

        setNull();
        room2();
    }

    private void room2() {


        room3();
    }

    private void setNull() {
        monster = null;
        loot = null;
        question = null;
    }


}
