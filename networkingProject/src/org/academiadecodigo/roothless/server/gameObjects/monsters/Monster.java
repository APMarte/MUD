package org.academiadecodigo.roothless.server.gameObjects.monsters;

import org.academiadecodigo.roothless.server.Dungeon;

/**
/* Created by apm on 27-02-2017.
*/
/**/


public abstract class Monster {

    private volatile int health;     //all
    private int baseDamage;  //all
    private int defense;    //all
    private String description;
    private Dungeon dungeon;
    volatile private boolean dead;


    abstract public String attack();

    protected void lvlScaling() {
        int level = dungeon.getLevel();
        if (level >= 1) {
            int levelModifier = (level - 1) / 10;
            baseDamage += baseDamage * levelModifier;
            health += health * levelModifier * 2;
            defense += defense * levelModifier / 2;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public String monsterHealth(int dmg){

        if (getHealth() - dmg >= 0) {
            setHealth(getHealth() - (dmg * (1 - getDefense()/100)));
        } else {
            setHealth(0);
        }

        return "Monster Health: " + getHealth();
    }
}


