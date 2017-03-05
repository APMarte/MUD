package org.academiadecodigo.roothless.client.player.classes;

import org.academiadecodigo.roothless.client.player.Player;


/**
 * Created by apm on 28-02-2017.
 */
public class Priest extends Player {

    public Priest(String name) {
        super(name);
        setChoosenClass("priest");
        setHealth(10);
        setBaseDamage(10);
        setDefense(10);
        setStrength(10);
        setDexterity(10);
        setFaith(10);
        setIntelligence(10);
        setCritChance(10);
    }

    @Override
    public int dmgCalc() {
        int baseDmg = getBaseDamage();
        int intel = getIntelligence();
        int fth = getFaith();

        if (hitCheck(getDexterity())) {

            int temp = preCritDmg ((int) (intel * 0.35 + fth * 0.65) + baseDmg);

            return super.critRoll(temp);
        }

        return 0;

    }
}
