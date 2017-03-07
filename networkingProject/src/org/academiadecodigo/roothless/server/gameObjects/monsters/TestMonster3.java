package org.academiadecodigo.roothless.server.gameObjects.monsters;

import org.academiadecodigo.roothless.server.Dungeon;

/**
 * /* Created by codecadet on 02/03/17.
 * /
 **/
public class TestMonster3 extends Monster {


    public TestMonster3(Dungeon dungeon) {
        setHealth(150);
        setBaseDamage(20);
        setDefense(10);
        setDescription("You find some very very cute animals.\n" +
                "\n" +
                "          The Sorcerer however alerts the group, for he feels that something is really really wrong here.\n" +
                "\n" +
                "          Suddenly the animals become really fierce and dangerous!\n" +
                "\n" +
                "\n" +
                "           /|       |\\\n" +
                "        `__\\\\       //__'\n" +
                "           ||      ||\n" +
                "         \\__`\\     |'__/\n" +
                "           `_\\\\   //_'\n" +
                "           _.,:---;,._\n" +
                "           \\_:     :_/                                                ,\n" +
                "             |@. .@|                                                 /|      __\n" +
                "             |     |                                                / |   ,-~ /\n" +
                "             ,\\.-./ \\                                              Y :|  //  /\n" +
                "             ;;`-'   `---__________-----.-.                        | jj /( .^\n" +
                "             ;;;                         \\_\\                       >-\"~\"-v\"\n" +
                "             ';;;                         |                       /       Y\n" +
                "              ;    |                      ;                      jo  o    |\n" +
                "               \\   \\     \\        |      /                      ( ~T~     j\n" +
                "                \\_, \\    /        \\     |\\                       >._-' _./                      .---.\n" +
                "                  |';|  |,,,,,,,,/ \\    \\ \\_                    /   \"~\"  |                     @ @   )\n" +
                "                  |  |  |           \\   /   |                  Y     _,  |                     ^     |\n" +
                "                  \\  \\  |           |  / \\  |                 /| ;-\"~ _  l                    [|]    | ##\n" +
                "                   | || |           | |   | |               / l/ ,-\"~    \\                    /      |####\n" +
                "                   | || |           | |   | |               \\//\\/      .- \\                  (       |####\n" +
                "                   | || |           | |   | |                Y        /    Y                  \\| /   |#BP#\n" +
                "                   |_||_|           |_|   |_|                l       I     !                 / |.'   |###\n" +
                "                  /_//_/           /_/   /_/                 ]\\      _\\    /\"\\              _\\ ``\\   )##\n" +
                "                                                            (\" ~----( ~   Y.  )            /,,_/,,____#\n");
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
