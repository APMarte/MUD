package org.academiadecodigo.roothless.server;

/**
 * Created by apm on 27-02-2017.
 */
public class Game {

    private Stage stage;
    private boolean gameOn;         //if False when game is over and when the 5 players aren't on yet
    private boolean hasNewMessage;  //to check if there's a new message to deliver to the Server

    public Game(Stage stage) {
        this.stage = stage;
    }

    public Game(){
        stage = new Stage();
    }

    public boolean isGameOn() {                         // TODO: 02/03/17  Server must have a while(true) { if(!isGameOn() && listFull) { break; } }
        return gameOn;
    }

    public boolean canIStart(boolean listFull) {      // TODO: 01/03/17 Server must have a while(true){  if(game.canIStart(listFull)){ break; }  }
        if (listFull) {
            gameOn = true;
            start();
            return true;
        }
        return false;
    }


    public void start() {

        String intro = "You are part of a group of 5 brave adventurers that received a special mission from the most " +
                "iconic Emperor that history has ever seen, his highness Trumpet Piglet, the brand new ruler of the " +
                "Ultra Safe and Armed Kingdoms (USAK - please don't you ever read it as \"You Sack\" or \"You Suck\", " +
                "or else your head will roll - it's a nation of defenders of the Truth, Justice and Freedom, ok? " +
                "Difamation is not freedom of speech, and \"inconvenient truths\" are just alternative facts). " +
                "Anyway, the Emperor told you that he has a huuuge but very simple and effective plan " +
                "to make the empire as great as it used to be.\nYou're a bit confused because you can't really recall such times, " +
                "but anyway, to make that plan come true, you must contribute by completing your mission." +
                "\nBasically there's this evil guild called DAESH (Dangerous Armed Enemies with Strange Habits). " +
                "Your mission is to penetrate their HQ and just obliterate them!\n\n[You can chat with each other at anytime, " +
                "but for executing commands type \"/[name of command]\". Try typing \"help\" for the full list of commands].";
        System.out.println(intro);

        //stage.enterdungeon(intro);


        //// TODO: 02/03/17 For the stage to start the first room (enter Dungeon), it will first receive this intro, broadcast it and then start the first room.

    }




}