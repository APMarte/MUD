package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;

/**
 * Created by codecadet on 01/03/17.
 */
public class Situation {

    private Strategy strategy;

    public Situation(Strategy strategy) {

        this.strategy = strategy;
    }

    public String handleByServer(String comand) {

        return this.strategy.processCommand(comand);

    }
}
