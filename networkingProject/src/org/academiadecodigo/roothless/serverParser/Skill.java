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
        dmg2= (int)(dmg + (dmg * 0.25));
        name = command.split(" ")[3];
        playerType=command.split(" ")[2];
        attackType=command.split(" ")[1];

        return this;
    }

    @Override
    public String run() {

        if(playerType.equals("archer")) {
            if(attackType.equals("1")) {
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                return name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
            }else{
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg2);
                System.out.println("Archer 2");
                return name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
            }
        }else if(playerType.equals("paladin")){
            if(attackType.equals("1")) {
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                return name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
            }else{
                System.out.println("Pally 2");
                return name + " has defend you all" ; //TODO Change You by name player -->command must have player name
            }
        }else if(playerType.equals("priest")){
            if(attackType.equals("1")) {
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                return name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
            }else{
                System.out.println("Priest 2");
                return "/modify hp " + dmg2 * - 1; //TODO Change You by name player -->command must have player name
            }
        }else if(playerType.equals("sorcerer")){
            if(attackType.equals("1")) {
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                return name + " has transformed monster into a cow and deal " + dmg + "  damage, " + str; //TODO Change You by name player -->command must have player name
            }else{
                System.out.println("Sorc 2");
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg2);
                return name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
            }
        }else if(playerType.equals("thief")){
            if(attackType.equals("1")) {
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                return name + " has stolen the monster neckles and deal " + dmg + " damage , " + str; //TODO Change You by name player -->command must have player name
            }else{
                System.out.println("Thief 2");
                String str = dungeon.getRoom().getMonster().monsterHealth(dmg);
                return name + " has attacked with " + dmg + " , " + str; //TODO Change You by name player -->command must have player name
            }
        }
        return null;
    }


}
