package org.academiadecodigo.roothless.server.level;

import org.academiadecodigo.roothless.server.Dungeon;
import org.academiadecodigo.roothless.server.gameObjects.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;

/**
 * Created by codecadet on 03/03/17.
 */
public abstract class Room {

    private Monster monster;
    private Loot loot;
    private String question;
    private Dungeon dungeon;

    public Room (Monster monster, Loot loot, String question, Dungeon dungeon) {
        this.monster = monster;
        this.loot = loot;
        this.question = question;
        this.dungeon = dungeon;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Room (Monster monster, Loot loot, Dungeon dungeon) {
        this.monster = monster;
        this.loot = loot;
        this.dungeon = dungeon;
    }

    public Room (String question, Loot loot, Dungeon dungeon) {
        this.question = question;
        this.loot = loot;
        this.dungeon = dungeon;
    }

    abstract public void run();

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Loot getLoot() {
        return loot;
    }

    public void setLoot(Loot loot) {
        this.loot = loot;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}

