package org.academiadecodigo.roothless.clienttoserverparser;

import org.academiadecodigo.roothless.client.player.Player;

import java.io.OutputStream;

/**
 * Created by codecadet on 02/03/17.
 */
public class ClientParser {

    private String commandToParse;
    private Player player;


    public ClientParser(String commandToParse, Player player) {
        this.player = player;
        this.commandToParse = commandToParse;

    }



    public String parseCommand() {

        System.out.println("cmd:"+commandToParse);

        switch (commandToParse) {

            case "/a":
                return new Situation(new Attack()).handleByPlayer(player);


            case "/d":
                return new Situation(new Defend()).handleByPlayer(player);


            default:
                return "Command not valid";

        }


    }

}
