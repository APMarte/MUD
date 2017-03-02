package org.academiadecodigo.roothless.server.gameObjects.monsters;

import org.academiadecodigo.roothless.server.Stage;
import org.academiadecodigo.roothless.server.gameObjects.Monster;

/**
 * Created by codecadet on 02/03/17.
 */
public class TestMonster extends Monster {


    public TestMonster(Stage stage) {
        setHealth(60);
        setBaseDamage(20);
        setDefense(10);
        setDescription("Lorem ipsum dolor sit amet");
        setDead(false);
        setStage(stage);

    }

    @Override
    public void Attack() {
        dmgScaling();

    }

    @Override
    public void dmgScaling() {
        int level = getStage().getLevel();
        int levelModifier = level / 10;
        if (level >= 1) {
            setBaseDamage(getBaseDamage() + (getBaseDamage() * levelModifier));
        }
    }
}
