package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;

/**
 * Created by codecadet on 01/03/17.
 */
public class Skill implements Strategy {

    @Override
    public String processCommand(String command) {

        //Parsing Attack model /a dmg
        String[] parsed = command.split(" ");
        String dmg = parsed[1];

        return "Player X"+" has atacked with "+dmg+"\n";
    }
}
