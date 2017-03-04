package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.server.Dungeon;

import java.util.Scanner;

/**
 * Created by codecadet on 01/03/17.
 */
public class Option implements Strategy {

    private String command;

    @Override
    public Strategy processCommand(String command, Dungeon dungeon) {
    this.command = command;
        return null;
    }

    @Override
    public String run() {

        switch (command){

            case "a":
                return "a";
            case "b":
                return "b";
            case "c":
                return "c";
        }
        return null;
    }
}
