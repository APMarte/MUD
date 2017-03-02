package org.academiadecodigo.roothless.clienttoserverparser;

/**
 * Created by codecadet on 01/03/17.
 */
public class Defend implements Strategy{

    @Override
    public void processCommand(String command) {
        System.out.println("Este turno defendi");
    }
}
