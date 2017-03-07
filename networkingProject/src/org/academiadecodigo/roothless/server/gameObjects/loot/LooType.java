package org.academiadecodigo.roothless.server.gameObjects.loot;

/**
 * Created by codecadet on 07/03/17.
 */
public enum LooType {

    LOOT1("You notice a guy rubbing an oil lamp frenetically and then sighing in a disappointed way.\n" +
            "        Your party gets a bit puzzled but then he explains that his magical friend that lives inside the lamp is feeling\n" +
            "        grumpy today.\n" +
            "\n" +
            "        Rrriiiiigghhht... your group decides to leave this crazy guy by himself, but the sorcerer shouts:\n" +
            "\n" +
            "        \"Wait, he's not lying! I can feel an amazing magical aura from that oil lamp!\"\n" +
            "\n" +
            "        The guy smirks and says:\n" +
            "\n" +
            "        \"Name's Alladin, a thief and a prince, believe it or not! I can teach one of you some exercises on dexterity!\"\n" +
            "\n" +
            "\n" +
            "                                                          .-.\n" +
            "                                                         [.-''-.,\n" +
            "                                                         |  //`~\\)\n" +
            "                                                         (<| 0\\0|>_\n" +
            "                                                         \";\\  _\"/ \\\\_ _,\n" +
            "                                                        __\\|'._/_  \\ '='-,\n" +
            "                                                       /\\ \\    || )_///_\\>>\n" +
            "                                                      (  '._ T |\\ | _/),-'\n" +
            "                                                       '.   '._.-' /'/ |\n" +
            "                                                       | '._   _.'`-.._/\n" +
            "                                                       ,\\ / '-' |/\n" +
            "                                                       [_/\\-----j\n" +
            "                                                  _.--.__[_.--'_\\__\n" +
            "                                                 /         `--'    '---._\n" +
            "                                                /  '---.  -'. .'  _.--   '.\n" +
            "                                                \\_      '--.___ _;.-o     /\n" +
            "                                                  '.__ ___/______.__8----'", 10, "dex"),

    LOOT2("You feel a powerful aura fom an old guy screaming: \"THOUGH SHALL NOT PASS!!!\".\n"+
                  "\n"+
                  "        \"Gandalf! Is that you?\" - The Sorcerer asked.\n"+
                  "\n"+
                  "        \"Oh, long time no see!\" - Said Gandalf in a calmer tone.\n"+
                  "\n"+
                  "        After chatting for a while, he tells the group that he has a potion that makes the one who takes it a faster reader.\n"+
                  "        Hmm... books, who from the group would benefit more from reading faster?\n"+
                  "\n"+
                  "\n"+
                  "\n"+
                  "                                                              ....\n"+
                  "                                                            .'' .'''\n"+
                  "                            .                             .'   :\n"+
                  "                            \\\\                          .:    :\n"+
                  "                             \\\\                        _:    :       ..----.._\n"+
                  "                              \\\\                    .:::.....:::.. .'         ''.\n"+
                  "                               \\\\                 .'  #-. .-######'     #        '.\n"+
                  "                                \\\\                 '.##'/ ' ################       :\n"+
                  "                                 \\\\                  #####################         :\n"+
                  "                                  \\\\               ..##.-.#### .''''###'.._        :\n"+
                  "                                   \\\\             :--:########:            '.    .' :\n"+
                  "                                    \\\\..__...--.. :--:#######.'   '.         '.     :\n"+
                  "                                    :     :  : : '':'-:'':'::        .         '.  .'\n"+
                  "                                    '---'''..: :    ':    '..'''.      '.        :'\n"+
                  "                                       \\\\  :: : :     '      ''''''.     '.      .:\n"+
                  "                                        \\\\ ::  : :     '            '.      '      :\n"+
                  "                                         \\\\::   : :           ....' ..:       '     '.\n"+
                  "                                          \\\\::  : :    .....####\\\\ .~~.:.             :\n"+
                  "                                           \\\\':.:.:.:'#########.===. ~ |.'-.   . '''.. :\n"+
                  "                                            \\\\    .'  ########## \\ \\ _.' '. '-.       '''.\n"+
                  "                                            :\\\\  :     ########   \\ \\      '.  '-.        :\n"+
                  "                                           :  \\\\'    '   #### :    \\ \\      :.    '-.      :\n"+
                  "                                          :  .'\\\\   :'  :     :     \\ \\       :      '-.    :\n"+
                  "                                         : .'  .\\\\  '  :      :     :\\ \\       :        '.   :\n"+
                  "                                         ::   :  \\\\'  :.      :     : \\ \\      :          '. :\n"+
                  "                                         ::. :    \\\\  : :      :    ;  \\ \\     :           '.:\n"+
                  "                                          : ':    '\\\\ :  :     :     :  \\:\\     :        ..'\n"+
                  "                                             :    ' \\\\ :        :     ;  \\|      :   .'''\n"+
                  "                                             '.   '  \\\\:                         :.''\n"+
                  "                                              .:..... \\\\:       :            ..''\n"+
                  "                                             '._____|'.\\\\......'''''''.:..'''\n"+
                  "                                                        \\\\", 10, "int"),


    LOOT3("You suddenly notice the almost ethereal presence of a very calm being.\n"+
                  "\n"+
                  "He calls himself Buddah and tells you that he can teach one of you something useful.\n"+
                  "He talks about Inner peace, Detachment, Focus on the Present, etc.\n"+
                  "\n"+
                  "Things that would be most beneficial to someone who likes to discuss Faith matters...\n"+
                  "\n"+
                  "\n"+
                  "                                        _\n"+
                  "                                     _ooOoo_\n"+
                  "                                    o8888888o\n"+
                  "                                    88\" . \"88\n"+
                  "                                    (| -_- |)\n"+
                  "                                    O\\  =  /O\n"+
                  "                                 ____/`---'\\____\n"+
                  "                               .'  \\\\|     |//  `.\n"+
                  "                              /  \\\\|||  :  |||//  \\\n"+
                  "                             /  _||||| -:- |||||_  \\\n"+
                  "                             |   | \\\\\\  -  /'| |   |\n"+
                  "                             | \\_|  `\\`---'//  |_/ |\n"+
                  "                             \\  .-\\__ `-. -'__/-.  /\n"+
                  "                           ___`. .'  /--.--\\  `. .'___\n"+
                  "                        .\"\" '<  `.___\\_<|>_/___.' _> \\\"\".\n"+
                  "                       | | :  `- \\`. ;`. _/; .'/ /  .' ; |\n"+
                  "                       \\  \\ `-.   \\_\\_`. _.'_/_/  -' _.' /\n"+
                  "             ===========`-.`___`-.__\\ \\___  /__.-'_.'_.-'===========\n"+
                  "                                     `=--=-'", 10 , "fth");



    private String description;
    private int buff;
    private String property;

    LooType(String description, int buff, String property) {
        this.description=description;
        this.buff=buff;
        this.property=property;
    }

    public String getDescription() {
        return description;
    }

    public int getBuff() {
        return buff;
    }


    public String getProperty() {
        return property;
    }

}
