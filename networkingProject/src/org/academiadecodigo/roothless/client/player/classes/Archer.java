package org.academiadecodigo.roothless.client.player.classes;

import org.academiadecodigo.roothless.client.player.Player;

/**
 * Created by apm on 28-02-2017.
 */
public class Archer extends Player {


    public Archer(String name) {
        super(name);
        setChoosenClass("1.txt");
        super.setHealth(100);
        setBaseDamage(10);
        setDefense(10);
        setStrength(10);
        setDexterity(10);
        setFaith(10);
        setIntelligence(10);
        setCritChance(25);
    }

    @Override
    public int dmgCalc() {
        int baseDmg = getBaseDamage();
        int str = getStrength();
        int dex = getDexterity();

        if (hitCheck(getDexterity())) {

            int temp = preCritDmg ((int) (str * 0.35 + dex * 0.65) + baseDmg);

            return super.critRoll(temp);
        }

        return 0;


    }
}

