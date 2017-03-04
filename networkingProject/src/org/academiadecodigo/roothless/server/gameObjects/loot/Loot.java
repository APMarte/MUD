package org.academiadecodigo.roothless.server.gameObjects.loot;

/**
 * Created by codecadet on 03/03/17.
 */
public abstract class Loot {

    private String description;
    private int buff=10;
    private String property="Strengh";


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBuff() {
        return buff;
    }

    public void setBuff(int buff) {
        this.buff = buff;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }



}
