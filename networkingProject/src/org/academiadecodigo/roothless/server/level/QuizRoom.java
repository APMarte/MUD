package org.academiadecodigo.roothless.server.level;

import org.academiadecodigo.roothless.server.Dungeon;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;

/**
 * Created by codecadet on 03/03/17.
 */
public class QuizRoom extends Room {

    public QuizRoom(String question, Loot loot, Dungeon dungeon) {
        super(question, loot, dungeon);
    }

    @Override
    public void run() {
        synchronized (getDungeon()) {
            try {
                while (getQuestion() != null) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
