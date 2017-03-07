package org.academiadecodigo.roothless.server.gameObjects.loot;

/**
 * Created by codecadet on 03/03/17.
 */
public abstract class Loot {

    private LooType type;

    public Loot(LooType type) {
        this.type=type;
    }


    public LooType getType() {
        return type;
    }
}
