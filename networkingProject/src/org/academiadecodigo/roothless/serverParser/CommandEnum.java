package org.academiadecodigo.roothless.serverParser;

/**
 * Created by codecadet on 01/03/17.
 */
public enum CommandEnum {

    CHAT("/"),
    ATTACK("/skill"),
    DEFEND("/defense"),
    PICK("/pick"),
    OPTION("/option"),
    WHISPER("/w"),
    HELP("/resources/help"),
    STATUS("/status"),
    QUIT("/quit");

    private String commandType;

    CommandEnum(String s) {

        commandType = s;

    }

    public String getCommandType() {
        return commandType;
    }
}
