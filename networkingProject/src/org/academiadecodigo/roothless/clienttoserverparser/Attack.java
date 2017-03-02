package org.academiadecodigo.roothless.clienttoserverparser;

import org.academiadecodigo.roothless.client.player.Player;

import java.io.OutputStream;

/**
 * Created by codecadet on 01/03/17.
 */
public class Attack implements Strategy {


    @Override
    public void processCommand(Player P1) {
        P1.getBaseDamage();

    }


}
