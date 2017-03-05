package org.academiadecodigo.roothless.client.player.classes;

import org.academiadecodigo.roothless.client.player.Player;


/**
 * Created by apm on 28-02-2017.
 */
public class Sorcerer extends Player {

    public Sorcerer(String name) {
        super(name);
        setChoosenClass("sorcerer");
        setHealth(10);
        setBaseDamage(10);
        setDefense(10);
        setStrength(10);
        setDexterity(10);
        setFaith(10);
        setIntelligence(10);
        setCritChance(20);
    }

    @Override
    public int dmgCalc() {
        int baseDmg = getBaseDamage();
        int intel = getIntelligence();
        int dex = getDexterity();

        if (hitCheck(getDexterity())) {

            int temp = preCritDmg ((int) (dex * 0.35 + intel * 0.65) + baseDmg);

            return super.critRoll(temp);
        }

        return 0;

    }
}
