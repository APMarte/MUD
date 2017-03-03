package org.academiadecodigo.roothless.client.player;

import org.academiadecodigo.roothless.client.player.classes.*;

/**
 * Created by codecadet on 02/03/17.
 */
public class PlayerFactory {



    public static Player getNewPlayer(String nome, PlayerType playerType){

        Player player = null;

        boolean archer = false;
        boolean paladin = false;
        boolean priest = false;
        boolean sorcerer = false;
        boolean thif = false;


        switch (playerType){
            case ARCHER:
                if (!archer){
                    player = new Archer(nome);
                    archer = true;
                } else{
                    System.out.println("Chose another Player Type");
                }
                break;
            case PALADIN:
                if (!paladin){
                    player = new Paladin(nome);
                    paladin = true;
                } else{
                    System.out.println("Chose another Player Type");
                }
                break;
            case PRIEST:
                if (!priest){
                    player = new Priest(nome);
                    priest = true;
                } else{
                    System.out.println("Chose another Player Type");
                }
                break;
            case SORCERER:
                if (!sorcerer){
                    player = new Sorcerer(nome);
                    sorcerer = true;
                } else{
                    System.out.println("Chose another Player Type");
                }
                break;
            case THIEF:
                if (!thif){
                    player = new Thief(nome);
                    thif = true;
                } else{
                    System.out.println("Chose another Player Type");
                }
                break;
        }
        return player;
    }

}
