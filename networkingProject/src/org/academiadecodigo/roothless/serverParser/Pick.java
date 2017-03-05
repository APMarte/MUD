package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 01/03/17.
 */
public class Pick implements Strategy{

    private String dmg;
    private String client;
    private Dungeon dungeon;

    @Override
    public Strategy processCommand(String command, Dungeon dungeon) {
        this.dungeon=dungeon;
        String dmg = command.split(" ")[1];

        return this;
    }

    @Override
    public String run() {
        System.out.println("bemfica");

        String pick ="/modify ";

        pick += dungeon.getRoom().getLoot().getProperty()+" ";
        pick += dungeon.getRoom().getLoot().getBuff()+" ";
        pick += "\n";

        System.out.println();
        return pick;//dungeon.getRoom().getMonster().monsterHealth(Integer.parseInt(dmg)) + "| has atacked with " + dmg ;
    }
}
