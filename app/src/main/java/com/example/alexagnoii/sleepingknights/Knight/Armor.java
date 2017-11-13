package com.example.alexagnoii.sleepingknights.Knight;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class Armor extends Item{

    private int defenseIncrease;

    public Armor(String name, String description, int cost, int defenseIncrease) {
        super(name, description, cost);
        this.defenseIncrease = defenseIncrease;
    }

    public Armor(){
        super(null, null, 0);
    }

    public int getDefenseIncrease() {
        return defenseIncrease;
    }

    public void setDefenseIncrease(int defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }
}
