package org.academiadecodigo.roothless.server.gameObjects.monsters;

import org.academiadecodigo.roothless.server.Dungeon;

/**
 * /* Created by codecadet on 02/03/17.
 * /
 **/
public class TestMonster extends Monster {


    public TestMonster(Dungeon dungeon) {
        setHealth(100);
        setBaseDamage(20);
        setDefense(10);
        setDescription("Bobi");
        setDead(false);
        setDungeon(dungeon);
        super.lvlScaling();
    }

    @Override
    public String attack() {
        int dmg = getBaseDamage();
        return "/modify hp " + (int) (Math.random() * ((dmg + 5) - (dmg - 5)) + (dmg - 5)) + " 1";
    }
}
