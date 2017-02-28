package org.academiadecodigo.roothless.gameObjects;

/**
 * Created by apm on 27-02-2017.
 */
public abstract class Monster {

    private int health;     //all
    private int baseDamage;  //all
    private int defense;    //all
    private boolean dead;

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
}

