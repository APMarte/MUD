package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 02/03/17.
 */
public class ServerParser {


    private ServerParser() {
    }

    public static Strategy parseCommand(String commandToParse, Dungeon dungeon) {

        String[] splited = commandToParse.split(" ");


        switch (splited[0]) {

            //COMBAT PARSER
            case "/skill":
                return new Situation(new Skill()).handleByServer(commandToParse,dungeon);

            case "/defense":
                return new Situation(new Defend()).handleByServer(commandToParse,dungeon);

            case "/pick":
                System.out.println("entrei");
                return new Situation(new Pick()).handleByServer(commandToParse,dungeon);

            //QUIZ PARSER
            case "/option":
                return new Situation(new Option()).handleByServer(commandToParse,dungeon);

        }

        return null;

    }

}
