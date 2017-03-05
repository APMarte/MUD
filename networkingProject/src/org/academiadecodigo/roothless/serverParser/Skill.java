package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 01/03/17.
 */
public class Skill implements Strategy {

    private String dmg;
    private String client;
    private Dungeon dungeon;

    @Override
    public Strategy processCommand(String command, Dungeon dungeon) {
        this.dungeon = dungeon;
        dmg = command.split(" ")[1];

        return this;
    }

    @Override
    public String run() {

        String str = dungeon.getRoom().getMonster().monsterHealth(Integer.parseInt(dmg));

        return "You have attacked with " + dmg + " | " + str;

    }


}
