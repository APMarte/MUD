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
        synchronized (getDungeon()) {
            while (getMonster().getHealth() > 0) {
                try {
                    getDungeon().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("before null");
        setMonster(null);
        System.out.println("ta morto caralho");
        //broadcast - > monster is dead ou broadcast no strategy?
        getDungeon().getQueue().clear();
        System.out.println("queue cleared");
        setLoot(loot);
        //broadcast your party is moving to the next room*/
    }
}




