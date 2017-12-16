package com.example.alexagnoii.sleepingknights;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alexagnoii.sleepingknights.Knight.Item;

import java.util.ArrayList;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String SCHEMA = "game";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
        Log.i("LOGS|databaseHelper", "Constructor");
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String knightTable;
        String itemTable;
        String inventoryTable;
        Log.i("LOGS|databaseHelper", "onCreate");

//        knightTable = "CREATE TABLE " + Knight.TABLE_NAME + " ("
//                + Knight.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                + Knight.COLUMN_NAME + " TEXT,"
//                + Knight.COLUMN_ATTACK + " INTEGER,"
//                + Knight.COLUMN_DEFENSE + " INTEGER,"
//                + Knight.COLUMN_HP + " INTEGER,"
//                + Knight.COLUMN_CHP + " INTEGER,"
//                + Knight.COLUMN_LEVEL + " INTEGER,"
//                + Knight.COLUMN_EXP + " INTEGER,"
//                + Knight.COLUMN_GOLD + " INTEGER,"
//                + Knight.COLUMN_WEAPON + " INTEGER,"
//                + Knight.COLUMN_ARMOR + " INTEGER,"
//                + Knight.COLUMN_SHIELD + " INTEGER"
//                + ");";

        itemTable = "CREATE TABLE " + Item.TABLE_NAME + " ("
                + Item.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Item.COLUMN_NAME + " TEXT,"
                + Item.COLUMN_DESCRIPTION + " TEXT,"
                + Item.COLUMN_TYPE + " INTEGER,"
                + Item.COLUMN_BOOST + " INTEGER,"
                + Item.COLUMN_COST + " INTEGER, "
                + Item.COLUMN_SKIN + " INTEGER"
                + ");";

        inventoryTable = "CREATE TABLE inventory ("
                + "_inventory_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "item_id INTEGER, "
                + "item_equip "
                + ");";


        //sqLiteDatabase.execSQL(knightTable); /*Convert this table to enemy table*/

        sqLiteDatabase.execSQL(itemTable);
        sqLiteDatabase.execSQL(inventoryTable);


        //Generate items that are inside the system.
        generateItems(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i("databaseHelper", "update");
        //String dropKnight = "DROP TABLE IF EXISTS " + Knight.TABLE_NAME+ ";";
        String dropItem = "DROP TABLE IF EXISTS " + Item.TABLE_NAME + ";";
        String dropInvent = "DROP TABLE IF EXISTS inventory;";
        //sqLiteDatabase.execSQL(dropKnight);
        sqLiteDatabase.execSQL(dropItem);
        sqLiteDatabase.execSQL(dropInvent);
        onCreate(sqLiteDatabase);
    }

    private void generateItems(SQLiteDatabase sqLiteDatabase) {
        ArrayList<Item> itemList = new ArrayList<Item>();
        //These are the default weapons, must always be ID 1, 2, 3
        Item defaultWeapon = new Item(/*Name*/"Blade", /*Desc*/ "a normal blade", /*Atk*/ 1, /*Cost*/ 0, /*SkinId*/1, 1),
             defaultArmor = new Item(/*Name*/"Armor", /*Desc*/"a simple armor", /*Def*/1, /*Cost*/0, /*SkinId*/2, 2),
             defaultShield = new Item(/*Name*/"Shield", /*Desc*/"a simple shield", /*Def*/1, /*Cost*/0, /*SkinId*/3, 3);


        itemList.add(defaultArmor);
        itemList.add(defaultWeapon);
        itemList.add(defaultShield);

        //These are the rest of the items
        itemList.add(new Item("Blade of Wow!", "its so wow!", 2, 10, 4, 1));
        //itemList.add(new Weapon("Blade of WTF?", "idk WTF?", 3, 20, 5));
        //itemList.add(new Weapon("Blade of OMFG!", "OMFG OMFG", 4, 30, 6));
        itemList.add(new Item("Armor of Wow!", "such wow armor!", 2, 10, 7, 2));
        //itemList.add(new Armor("Armor of WTF!", "such WTF armor!", 3, 20, 8));
        //itemList.add(new Armor("Armor of OMFG!", "Suc- OMFG OMFG!", 4, 30, 9));
        itemList.add(new Item("Shield of Shield", "Shieldception!", 5, 15, 10, 3));
        //itemList.add(new Armor("Shield without Shield!", "Shield using no shield!", 10, 50, 11));


        //Add to DB (item)
        for (int i = 0; i < itemList.size(); i++) {
            Log.i("LOGS|DATABASEHELPER", itemList.get(i).getName());
            addItem(itemList.get(i), itemList.get(i).getType(), sqLiteDatabase);
        }

        //Add to DB (Inventory)
        addToInventory(1, sqLiteDatabase);
        addToInventory(2, sqLiteDatabase);
        addToInventory(3, sqLiteDatabase);


    }
    public long addItem(Item item, int type, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(Item.COLUMN_NAME, item.getName());
        contentValues.put(Item.COLUMN_BOOST, item.getBoost());
        contentValues.put(Item.COLUMN_DESCRIPTION, item.getDescription());
        contentValues.put(Item.COLUMN_COST, item.getCost());
        contentValues.put(Item.COLUMN_SKIN, item.getSkinId());
        contentValues.put(Item.COLUMN_TYPE, type);

        long id = db.insert(item.TABLE_NAME, null, contentValues);
        //db.close();
        Log.i("LOGS", id+"");
        return id;
    }

    public long addItem(Item item, int type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Item.COLUMN_NAME, item.getName());
        contentValues.put(Item.COLUMN_BOOST, item.getBoost());
        contentValues.put(Item.COLUMN_DESCRIPTION, item.getDescription());
        contentValues.put(Item.COLUMN_COST, item.getCost());
        contentValues.put(Item.COLUMN_SKIN, item.getSkinId());
        contentValues.put(Item.COLUMN_TYPE, type);

        long id = db.insert(item.TABLE_NAME, null, contentValues);
        db.close();
        Log.i("LOGS", id+"");
        return id;
    }

    public long addToInventory(long itemId, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_id", itemId);
        contentValues.put("item_equip", 0);

        long id = db.insert("inventory", null, contentValues);
        //db.close();
        return id;
    }

    public long addToInventory(long itemId) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item_id", itemId);

        long id = db.insert("inventory", null, contentValues);
        db.close();
        return id;
    }

    public Item getItem(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Item.TABLE_NAME,
                null,
                Item.COLUMN_ID + "=?",
                new String[]{ id+"" },
                null,
                null,
                null);

        Item item = null;
        if(c.moveToFirst()){
                item = new Item(c.getString(c.getColumnIndex(Item.COLUMN_NAME)),
                                      c.getString(c.getColumnIndex(Item.COLUMN_DESCRIPTION)),
                                      c.getInt(c.getColumnIndex(Item.COLUMN_BOOST)),
                                      c.getInt(c.getColumnIndex(Item.COLUMN_COST)),
                                      c.getInt(c.getColumnIndex(Item.COLUMN_SKIN)),
                                      c.getInt(c.getColumnIndex(Item.COLUMN_TYPE))
                                );


        }

        c.close();
        db.close();

        return item;
    }

    public Cursor getAllInventoryItems() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query("inventory", null,null,null,null,null,null);
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(Item.TABLE_NAME, null, Item.COLUMN_ID + ">?",new String[]{3+"" },null,null,null);
    }
}
