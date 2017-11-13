package com.example.alexagnoii.sleepingknights.Knight;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class Knight {

    public static final String TABLE_NAME = "knight";
    public static final String COLUMN_ID = "_id"; //Primary keys has underscores
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_HP ="hp";
    public static final String COLUMN_CHP ="currentHp";
    public static final String COLUMN_ATTACK = "attackPoints";
    public static final String COLUMN_DEFENSE = "defensePoints";
    public static final String COLUMN_LEVEL = "level";
    public static final String COLUMN_EXP = "experiencePoints";
    public static final String COLUMN_GOLD = "gold";
    public static final String COLUMN_ARMOR = "armor_id";
    public static final String COLUMN_WEAPON = "weapon_id";
    public static final String COLUMN_SHIELD = "shield_id";


    private long id;
    private String name;
    private int healthPoints;
    private int currentHP;
    private int attack;
    private int defense;
    private long level;
    private long exp;
    private long gold;
    private Weapon weapon;
    private Armor armor;
    private Armor shield;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLevel() {
        return level;
    }

    public void setLevel(long level) {
        this.level = level;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Armor getShield() {return shield;}

    public void setShield(Armor shield) {this.shield = shield;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

}
