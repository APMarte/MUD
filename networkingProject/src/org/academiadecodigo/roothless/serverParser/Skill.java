package org.academiadecodigo.roothless.serverParser;

import org.academiadecodigo.roothless.client.player.Player;
import org.academiadecodigo.roothless.client.player.PlayerType;
import org.academiadecodigo.roothless.server.Dungeon;

/**
 * Created by codecadet on 01/03/17.
 */
public class Skill implements Strategy {

    private int dmg;
    private int dmg2;
    private Dungeon dungeon;
    private String name;
    private String playerType;
    private String attackType;


    @Override
    public Strategy processCommand(String command, Dungeon dungeon) {

        this.dungeon = dungeon;
        dmg = Integer.parseInt(command.split(" ")[4]);
        dmg2 = (int) (dmg + (dmg * 0.25));
        name = command.split(" ")[3];
        playerType = command.split(" ")[2];
        attackType = command.split(" ")[1];

        return this;
    }

    @Override
    public String run() {

        synchronized (dungeon) {

            String returnString = null;

            if (playerType.equals("1.txt")) {
                if (attackType.equals("1")) {
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                    returnString = name + " has attacked with " + dmg + " damage, " + str; //TODO Change You by name player -->command must have player name
                } else {
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg2);
                    System.out.println("Archer 2");
                    returnString = name + " has attacked with " + dmg + " damage, " + str; //TODO Change You by name player -->command must have player name
                }
            } else if (playerType.equals("paladin")) {
                if (attackType.equals("1")) {
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                    returnString = name + " has attacked with " + dmg + " , damage" + str; //TODO Change You by name player -->command must have player name
                } else {
                    System.out.println("Pally 2");
                    returnString = name + " has defend you all"; //TODO Change You by name player -->command must have player name
                }
            } else if (playerType.equals("3.txt")) {
                if (attackType.equals("1")) {
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                    returnString = name + " has attacked with " + dmg + " , damage" + str; //TODO Change You by name player -->command must have player name
                } else {
                    System.out.println("Priest 2");
                    returnString = "/modify hp " + dmg2 * -1; //TODO Change You by name player -->command must have player name
                }
            } else if (playerType.equals("sorcerer")) {
                if (attackType.equals("1")) {
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                    returnString = name + " has transformed monster into a cow and deal " + dmg + "  damage, " + str; //TODO Change You by name player -->command must have player name
                } else {
                    System.out.println("Sorc 2");
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg2);
                    returnString = name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
                }
            } else if (playerType.equals("thief")) {
                if (attackType.equals("1")) {
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                    returnString = name + " has stolen the monster neckles and deal " + dmg + " damage, " + str; //TODO Change You by name player -->command must have player name
                } else {
                    System.out.println("Thief 2");
                    String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                    returnString = name + " has attacked with " + dmg + " damage, " + str; //TODO Change You by name player -->command must have player name
                }
            }
            dungeon.notifyAll();
            return returnString;
        }
    }
}
