package org.academiadecodigo.roothless.server.gameObjects.monsters;

import org.academiadecodigo.roothless.server.Dungeon;
import org.academiadecodigo.roothless.server.gameObjects.Monster;

/**
/* Created by codecadet on 02/03/17.
/**/
public class TestMonster extends Monster {


    public TestMonster(Dungeon dungeon) {
        setHealth(60);
        setBaseDamage(20);
        setDefense(10);
        setDescription("Bobi");
        setDead(false);
        setDungeon(dungeon);
        super.lvlScaling();
    }

    @Override
    public void attack() {


    }
}
