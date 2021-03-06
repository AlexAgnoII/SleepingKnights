package com.example.alexagnoii.sleepingknights.Knight;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class Consumable extends Item {

    private int pointsAdded; //Health points added (for now). (Or for future reference, defense added (for defense potions) etc.

    public Consumable(long id, String name, String description, int cost, int pointsAdded) {
        super(id, name, description, cost);

        this.pointsAdded = pointsAdded;
    }

    public Consumable() {
        super(0, null, null, 0);

    }

    public int getPointsAdded() {
        return pointsAdded;
    }

    public void setPointsAdded(int pointsAdded) {
        this.pointsAdded = pointsAdded;
    }
}
