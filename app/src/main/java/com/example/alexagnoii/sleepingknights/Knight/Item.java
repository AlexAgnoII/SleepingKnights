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
    public static final String COLUMN_SKIN = "skin_id";

    protected long id;
    protected String name;
    protected String description;
    protected int cost;
    protected int skinId;
    protected int boost;
    protected int type;

    public Item(String name, String description, int boost, int cost, int skinId, int type) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.skinId = skinId;
        this.boost = boost;
        this.type = type;
    }

    public Item(long id, String name, String description, int cost, int skinId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.skinId = skinId;
    }


    public Item(long id, String name, String description, int cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Item(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Item() {

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

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public int getSkinId() {
        return skinId;
    }

    public void setSkinId(int skinId) {
        this.skinId = skinId;
    }

    public int getBoost() {
        return boost;
    }

    public void setBoost(int boost) {
        this.boost = boost;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
