package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 01/03/17.
 */
public class Pick implements Strategy{

    private Dungeon dungeon;
    private String name;

    @Override
    public Strategy processCommand(String command, Dungeon dungeon) {
        this.dungeon=dungeon;

        return this;
    }

    @Override
    public String run() {

        String pick;

        synchronized (dungeon) {
            pick = "/modify ";

            pick += dungeon.getRoom().getLoot().getType().getProperty() + " ";
            pick += dungeon.getRoom().getLoot().getType().getBuff() + " ";
            System.out.println("----------------------" + pick);

            dungeon.getRoom().setLoot(null);
            dungeon.notifyAll();

            return pick;
        }

    }
}
