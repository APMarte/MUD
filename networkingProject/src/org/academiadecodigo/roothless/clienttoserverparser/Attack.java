package org.academiadecodigo.roothless.clienttoserverparser;

import org.academiadecodigo.roothless.client.player.Player;

import java.io.OutputStream;

/**
 * Created by codecadet on 01/03/17.
 */
public class Attack implements Strategy {

    @Override
    public String processCommand(Player player) {

        return String.valueOf(player.getName()+" atacou com dano "+player.getBaseDamage());
    }


}
