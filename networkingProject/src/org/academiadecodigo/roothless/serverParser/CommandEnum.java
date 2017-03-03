package org.academiadecodigo.roothless.serverParser;

/**
 * Created by codecadet on 01/03/17.
 */
public enum CommandEnum {

    CHAT("/"),
    ATTACK("/a"),
    DEFEND("/d"),
    SKILL("/s"),
    PICK("/p"),
    OPTION("/o"),
    WHISPER("/w"),
    HELP("/h"),
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
