package org.academiadecodigo.roothless.serverParser;

/**
 * Created by codecadet on 01/03/17.
 */
public class Attack implements Strategy {

    @Override
    public String processCommand(String command) {

        //Parsing Attack model /a dmg
        System.out.println("oix");
        String[] parsed = command.split(" ");
        String dmg = parsed[1];

        return dmg;
    }
}
