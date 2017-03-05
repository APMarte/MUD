package org.academiadecodigo.roothless.client.player;


/**
 * Created by apm on 28-02-2017.
 */
public abstract class Player {

    private String name;    //all
    private String choosenClass;
    private int health;     //all
    private int baseDamage;  //all
    private int defense;    //all
    private int strength;  //Paladin > Thief > Archer
    private int intelligence; //Priest and Sorcerer
    private int dexterity; // Archer and Thief and Sorcerer
    private int faith; //Priest and Paladin
    private int critChance; // all
    private boolean hasActed;
    private boolean dead;

    public Player(String name) {

        this.name = name;
    }

    abstract public int dmgCalc();

    protected int critRoll(int dmg) {
        int returnValue = dmg;
        double rng = Math.random() * 100;

        if (rng <= critChance) {
            returnValue = dmg * 2;
        }

        return returnValue;
    }

    protected boolean hitCheck (int dex) {
        int hitChance = 60 + dex;

        if ( hitChance >= Math.random() * 100) {
            return true;
        }
        return false;
    }

    protected int preCritDmg (int dmg) {

        return (int) (Math.random() * ((dmg + 5) - (dmg - 5)) + (dmg - 5));
    }

    public int getHealth() {
        return health;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public int getdefense() {
        return defense;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getFaith() {
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

    public boolean getHasActed() {
        return hasActed;
    }

    public void setHasActed(boolean hasActed) {
        this.hasActed = hasActed;
    }

    public int getDefense() {
        return defense;
    }

    public int getCritChance() {
        return critChance;
    }

    public void setCritChance(int critChance) {
        this.critChance = critChance;
    }

    public boolean isHasActed() {
        return hasActed;
    }

    public boolean isDead() {
        return dead;
    }

    public String getChoosenClass() {
        return choosenClass;
    }

    public void setChoosenClass(String choosenClass) {
        this.choosenClass = choosenClass;
    }

}
