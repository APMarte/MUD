package org.academiadecodigo.roothless.client.player.classes;

import org.academiadecodigo.roothless.client.player.Player;


/**
 * Created by apm on 28-02-2017.
 */
public class Sorcerer extends Player {

    public Sorcerer(String name){
        super(name);
        setHealth(10);
        setBaseDamage(10);
        setDefense(10);
        setStrength(10);
        setDexterity(10);
        setFaith(10);
        setIntelligence(10);
    }
}
