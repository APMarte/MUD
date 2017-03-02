package org.academiadecodigo.roothless.clienttoserverparser;

import java.io.OutputStream;

/**
 * Created by codecadet on 02/03/17.
 */
public class ClientParser {

    private String commandToParse;

    //Recebe comando e outputstream do cliente
    public ClientParser(String commandToParse) {

        this.commandToParse = commandToParse;
    }

    public void parseCommand() {

        for (CommandEnum ce : CommandEnum.values()) {

            if (ce.getCommandType() == commandToParse) {

                //ATIVA ESTRATEGIA E PASSA NO ARGUMENTO DO HANDLEBYPLAYER O OUTPUTSTREAM
                //OU NO ARGUMENTO DA STRATEGY? UHMUHMUHMUHMUHMUHMHUMH

                switch (commandToParse) {
                    case "/a":
                        new Situation(new Attack()).handleByPlayer(commandToParse);
                        break;
                    case "/d":
                        new Situation(new Defend()).handleByPlayer(commandToParse);
                        break;
                    default:
                        System.out.println("Command not valid");

                }


            }
        }
    }
}