package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 01/03/17.
 */
public class Situation {

    private Strategy strategy;

    public Situation(Strategy strategy) {

        this.strategy = strategy;
    }

    public Strategy handleByServer(String comand, Dungeon dungeon) {

        return this.strategy.processCommand(comand,dungeon);

    }
}
