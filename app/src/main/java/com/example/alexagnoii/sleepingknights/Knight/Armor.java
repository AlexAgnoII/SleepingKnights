package com.example.alexagnoii.sleepingknights.Knight;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class Armor extends Item{

    private int defenseIncrease;

    public Armor(long id, String name, String description, int cost, int defenseIncrease) {
        super(id, name, description, cost);
        this.defenseIncrease = defenseIncrease;
    }

    public Armor(long id){
        super(id, null, null, 0);
    }

    public Armor() {
        super(0, null, null, 0);
    }

    public Armor(String name, String description, int def, int cost, int skinId) {
        super(name, description, cost, skinId);
        this.defenseIncrease = def;
    }

    public int getDefenseIncrease() {
        return defenseIncrease;
    }

    public void setDefenseIncrease(int defenseIncrease) {
        this.defenseIncrease = defenseIncrease;
    }
}
