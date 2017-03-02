package org.academiadecodigo.roothless.clienttoserverparser;

/**
 * Created by codecadet on 01/03/17.
 */
public class Situation {

    private Strategy strategy;

    public Situation(Strategy strategy) {
        this.strategy = strategy;
    }

    public void handleByPlayer(String command){

        this.strategy.processCommand(command);

    }
}
