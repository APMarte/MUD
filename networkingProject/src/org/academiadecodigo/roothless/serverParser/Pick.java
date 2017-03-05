package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 01/03/17.
 */
public class Pick implements Strategy{

    private Dungeon dungeon;

    @Override
    public Strategy processCommand(String command, Dungeon dungeon) {
        this.dungeon=dungeon;

        return this;
    }

    @Override
    public String run() {
        System.out.println("oix");
        String pick ="/modify ";

        pick += dungeon.getRoom().getLoot().getProperty()+" ";
        pick += dungeon.getRoom().getLoot().getBuff()+" ";
        System.out.println("----------------------" + pick);

        dungeon.getRoom().setLoot(null);
        System.out.println("---------------" + dungeon.getRoom().getLoot());

        return pick;//dungeon.getRoom().getMonster().monsterHealth(Integer.parseInt(dmg)) + "| has atacked with " + dmg ;
    }
}
