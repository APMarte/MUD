package org.academiadecodigo.roothless.client.player;


/**
 * Created by apm on 28-02-2017.
 */
public abstract class Player {

    private String name;    //all
    private int health;     //all
    private int baseDamage;  //all
    private int defense;    //all
    private int strength;  //Paladin > Thief > Archer
    private int intelligence; //Priest and Sorcerer
    private int dexterity; // Archer and Thief and Sorcerer
    private int faith; //Priest and Paladin

    private boolean dead;

    public Player(String name){
        this.name = name;

    }

    public int getHealth(){
        return health;
    }
    public int getBaseDamage(){
        return baseDamage;
    }
    public int getdefense(){
        return defense;
    }
    public int getStrength(){
        return strength;
    }
    public int getIntelligence(){
        return intelligence;
    }
    public int getDexterity(){
        return dexterity;
    }
    public int getFaith(){
        return faith;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setFaith(int faith) {
        this.faith = faith;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }



}
