package org.academiadecodigo.roothless.client.player.classes;

import org.academiadecodigo.roothless.client.player.Player;

/**
 * Created by apm on 28-02-2017.
 */
public class Paladin extends Player {

    public Paladin(String name){
        super(name);
        setChoosenClass("paladin");
        setHealth(100);
        setBaseDamage(10);
        setDefense(10);
        setStrength(10);
        setDexterity(10);
        setFaith(10);
        setIntelligence(10);
        setCritChance(15);
    }

    @Override
    public int dmgCalc() {
        int baseDmg = getBaseDamage();
        int str = getStrength();
        int fth = getFaith();

        if (hitCheck(getDexterity())) {

            int temp = preCritDmg ((int) (fth * 0.35 + str * 0.65) + baseDmg);

            return super.critRoll(temp);
        }

        return 0;
    }


}
