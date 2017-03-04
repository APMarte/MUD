package org.academiadecodigo.roothless.server.level;

import org.academiadecodigo.roothless.server.Dungeon;
import org.academiadecodigo.roothless.server.gameObjects.Monster;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;

/**
 * Created by codecadet on 03/03/17.
 */
public class MixedRoom extends Room {

    public MixedRoom (Monster monster, Loot loot, String question, Dungeon dungeon) {
        super(monster, loot, question, dungeon);
    }

    @Override
    public void run() {
        synchronized (getDungeon()) {
            try {
                while (getMonster()!= null) {
                    wait();
                }

                while (getQuestion() != null) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
