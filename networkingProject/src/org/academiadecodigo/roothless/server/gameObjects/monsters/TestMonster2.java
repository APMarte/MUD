package org.academiadecodigo.roothless.server.gameObjects.monsters;

import org.academiadecodigo.roothless.server.Dungeon;

/**
 * /* Created by codecadet on 02/03/17.
 * /
 **/
public class TestMonster2 extends Monster {


    public TestMonster2(Dungeon dungeon) {
        setHealth(150);
        setBaseDamage(20);
        setDefense(10);
        setDescription("You enter a new room...\n" +
                "\n" +
                "     In it there's a pool with a hungry crocodile!\n" +
                "\n" +
                "     Crocodiles have high defense against all kinds of attacks, specailly physical ones.\n" +
                "     Their critical chance is quite high, be careful." +
                "" +
                "                                                                               \n" +
                "                                                                               \n" +
                "                                            ___.-----.______                   \n" +
                "                                    ___.-----'::::::::::::::::`---.___         \n" +
                "                 _.--._            (:::;,-----'~~~~~`----::::::::::.. `-.      \n" +
                "    _          .'_---. `--.__       `~~'                 `~`--.:::::`..  `..   \n" +
                "   ; `-.____.-' ' {0} ` `--._`---.____                         `:::::::: : ::  \n" +
                "  :_^              ~   `--.___ `----.__`----.____                ~::::::.`;':  \n" +
                "   :`-._,---------.___(       `---.___ `---.___  `----.___         ~|;:,' : |  \n" +
                "    `-.V_V_V_V,---.____ _,        ._  `----.____ `----.__ `-----.___;--'  ; :  \n" +
                "                   `---' `.  `._    `))  ,  , , `----.____.----.____   --' :|  \n" +
                "                         / `,--.\\    `.` `  ` ` ,   ,  ,     _.--   `-----'|'  \n" +
                "  _.~~~~~~._____    __./'_/'     :   .:----.___ ` ` ` ``  .-'      , ,  :::'   \n" +
                "                  ///--\\;  ____  :   :'    ____`---.___.--::     , ` ` ::'     \n" +
                "                  `'           _.'   (    /______     (   `-._   `-._,-'       \n" +
                "                            .-' __.-//     /_______---'       `-._   `.        \n" +
                "                ~~~        /////    `'       ~~~~~~      ~~ ______;   ::.      \n" +
                "                           `'`'                            /_______   _.'      \n" +
                "               ,     ~~~                  ~~~~~~~~           /___.---'  --__   \n" +
                "               `                                              ~~~              \n" +
                "                                                                               ");
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
