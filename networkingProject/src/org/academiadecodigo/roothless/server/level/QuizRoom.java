package org.academiadecodigo.roothless.server.level;

import org.academiadecodigo.roothless.server.Dungeon;
import org.academiadecodigo.roothless.server.gameObjects.loot.Loot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by codecadet on 03/03/17.
 */
public class QuizRoom extends Room {

    private final String[][] Q_AND_A = {{"Who is the current Emperor of USAK?" , "Trumpet Piglet", "Marxie, the Burner in Sandals", "Hilarious Python"},
            {"Who is the previous Emperor of USAK?", "Hussein Osama from the Barracks (also known as Obama)", "Bill, the Python", "Georgie, the Junior Bushturd"},
            {"What does USAK stand for?","United Safe and  Armed    Kingdoms","United States of American Kobolds"},
            {"How do you call a person from USAK?","Armedican","USAKer","Crazy"},
            {"What does DAESH stand for?","Dangerous Armed Enemies withStrange Habits","Dangerous Aliens from the East Silent Hills","Dope Addicts and Ecstasy Snorters of Heroin"},
            {"What's the name given to the attack that made the Twin Mage Towers fall, and who organized it?","Nine Hells in Heaven, the ALL-Keda.","The errorism begins, Georgie, the Junior Bushturd.","Vai tudo abaixo, Kalashnikov."},
            {"What's the meaning of life?","To love and assist each other.","To loath and assassinate each other.","To do what God tells you to."},
            {"What does TTIP stand for?","Treaty that'll Totally Implode the Planet","Trumpet's Treaty on Ingenious Paradoxes","Truth and Truces are Inherently Protocols"},
            {"Why was the emperor Bill named The Python?","Because he had a little Python that his scribe (Monica Lewd and Kinky) licked a lot.","Because he was vile like a Python." , "Because he had a great sense of humour."}};

    private boolean[] questionsAsked = new boolean[Q_AND_A.length];
    private boolean[] answersShowed = new boolean[4];
    private boolean rigthAnswer;
    private boolean wrongAnswer;
    private String Q;
    private String A;
    private String B;
    private String C;
    private int rightAnswerIndex;



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

    private String[][] getQuestionArray(){
        return Q_AND_A;
    }

    private static int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int questionI(){
        return random(0, getQuestionArray().length);
    }

    private int answers(){
        return random(1, 3);
    }

    private int questionsAndAnswers() throws FileNotFoundException {

        rightAnswerIndex = questionI();
        if (questionsAsked[rightAnswerIndex]){
            questionsAndAnswers();
        }
        else {
            //System.out.println(q + " " + Q_AND_A[q][0]);
            Q = Q_AND_A[rightAnswerIndex][0];
        }
        int a = answers();
        A = Q_AND_A[rightAnswerIndex][a];
        //System.out.println(Q_AND_A[q][a]);
        answersShowed[0] = true;
        answersShowed[a] = true;
        for (int i = 0; i < answersShowed.length; i++) {
            if (!answersShowed[i]) {
                B = Q_AND_A[rightAnswerIndex][i];
                answersShowed[i] = true;
                break;
            }
        }
        for (int i = 0; i < answersShowed.length; i++) {
            if (!answersShowed[i]) {
                C = Q_AND_A[rightAnswerIndex][i];
            }
        }

        return rightAnswerIndex;
        //selectOneAnswer(q);
    }

}
