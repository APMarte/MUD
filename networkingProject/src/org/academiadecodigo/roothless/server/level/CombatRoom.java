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
        synchronized (getDungeon()) {
            try {
                while (getMonster() != null) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

