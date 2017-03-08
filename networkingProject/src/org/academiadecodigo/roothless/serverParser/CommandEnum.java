package org.academiadecodigo.roothless.serverParser;

/**
 * Created by codecadet on 01/03/17.
 */
public enum CommandEnum {

    ATTACK("/skill"),
    DEFEND("/defense"),
    PICK("/pick"),
    OPTION("/option"),
    WHISPER("/w"),
    HELP("/help"),
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
