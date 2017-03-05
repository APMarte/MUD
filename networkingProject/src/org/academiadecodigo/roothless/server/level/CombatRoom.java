package org.academiadecodigo.roothless.server.level;

import org.academiadecodigo.roothless.server.Dungeon;
import org.academiadecodigo.roothless.server.gameObjects.monsters.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;

/**
 * Created by codecadet on 03/03/17.
 */
public class CombatRoom extends Room {

    public CombatRoom(Monster monster, Loot loot, Dungeon dungeon) {
        super(monster, loot, dungeon);
    }


    @Override
    public void run() {
        Loot loot = getLoot();
        setLoot(null);
        System.out.println("----------------entered run");
        //broadcast - > getMonster().getDescription();
        while (getMonster().getHealth() > 0) {
        }
        System.out.println("before null");
        setMonster(null);
        System.out.println("ta morto caralho");
        //broadcast - > monster is dead ou broadcast no strategy?
        /*getDungeon().getQueue().clear();
        setLoot(loot);
        //broadcast loot description
        while (loot != null) {
        }
        getDungeon().getQueue().clear();
        //broadcast your party is moving to the next room*/
    }
}




