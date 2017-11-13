package com.example.alexagnoii.sleepingknights.Knight;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class Weapon extends Item {

    private int attackIncrease;

    public Weapon(String name, String description, int cost, int attackIncrease) {
        super(name, description, cost);
        this.attackIncrease = attackIncrease;
    }

    public Weapon(){super(null, null, 0);}

    public int getAttackIncrease() {
        return attackIncrease;
    }

    public void setAttackIncrease(int attackIncrease) {
        this.attackIncrease = attackIncrease;
    }
}
