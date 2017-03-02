package org.academiadecodigo.roothless.server.gameObjects;

import org.academiadecodigo.roothless.server.Stage;

*
 * Created by apm on 27-02-2017.


public abstract class Monster {

    private int health;     //all
    private int baseDamage;  //all
    private int defense;    //all
    private String description;
    private Stage stage;
    private boolean dead;


    abstract public void attack();

    protected void dmgScaling() {
        int level = stage.getLevel();
        if (level >= 1) {
            int levelModifier = (level - 1) / 10;
            baseDamage += baseDamage * levelModifier;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

