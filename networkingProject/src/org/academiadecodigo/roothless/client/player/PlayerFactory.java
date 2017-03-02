package org.academiadecodigo.roothless.client.player;

import org.academiadecodigo.roothless.client.player.classes.*;

/**
 * Created by codecadet on 02/03/17.
 */
public class PlayerFactory {

    public static Player getNewPlayer(String nome, PlayerType playerType){

        Player player = null;

        switch (playerType){
            case ARCHER:
                player = new Archer(nome);
                break;
            case PALADIN:
                player = new Paladin(nome);
                break;
            case PRIEST:
                player = new Priest(nome);
                break;
            case SORCERER:
                player = new Sorcerer(nome);
                break;
            case THIEF:
                player = new Thief(nome);
                break;
        }
        return player;
    }

}
