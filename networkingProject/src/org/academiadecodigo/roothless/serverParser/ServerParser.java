package org.academiadecodigo.roothless.serverParser;

/**
 * Created by codecadet on 02/03/17.
 */
public class ServerParser {

    /*private String commandToParse;



    public ServerParser(String commandToParse) {

        this.commandToParse = commandToParse;

    }*/



    public String parseCommand(String commandToParse) {

        String[] splited = commandToParse.split(" ");

        switch (splited[0]) {

            //COMBAT PARSER
            case "/a":
                return new Situation(new Attack()).handleByServer(commandToParse);

            case "/d":
                return new Situation(new Defend()).handleByServer(commandToParse);

            case "/pick":
                return new Situation(new Pick()).handleByServer(commandToParse);

            //QUIZ PARSER
            case "/option":
                return new Situation(new Option()).handleByServer(commandToParse);

            //Command not Valid!
            default:
                return "Command not valid";

        }


    }

}
