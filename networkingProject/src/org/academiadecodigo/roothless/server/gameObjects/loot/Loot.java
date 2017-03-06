package org.academiadecodigo.roothless.server.gameObjects.loot;

/**
 * Created by codecadet on 03/03/17.
 */
public abstract class Loot {

    private String description = "You found a small Katana!\n" +
            "        It's light and very sharp, ideal for someone with fast and deadly attacks!\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "                       /\\\n" +
            "            /vvvvvvvvvvvv \\--------------------------------------,\n" +
            "            `^^^^^^^^^^^^ /=====================================\"\n" +
            "                        \\/";
    private int buff= 10;
    private String property= "str";


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
