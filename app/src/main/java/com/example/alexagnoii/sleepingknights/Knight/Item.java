package com.example.alexagnoii.sleepingknights.Knight;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class Item {
    public static final String TABLE_NAME = "item";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION ="description";
    public static final String COLUMN_BOOST = "boost"; //attk for weapon, def for armor, hp for potions.
    public static final String COLUMN_TYPE = "type";//type of item
    public static final String COLUMN_COST = "cost";//type of item

    protected String name;
    protected String description;
    protected int cost;

    public Item(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
