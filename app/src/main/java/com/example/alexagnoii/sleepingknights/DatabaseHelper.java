package com.example.alexagnoii.sleepingknights;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alexagnoii.sleepingknights.Knight.Armor;
import com.example.alexagnoii.sleepingknights.Knight.Item;
import com.example.alexagnoii.sleepingknights.Knight.Knight;
import com.example.alexagnoii.sleepingknights.Knight.Weapon;

import java.util.ArrayList;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String SCHEMA = "game";
    public static final int VERSION = 2;

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
                + "item_id INTEGER"
                + ");";


        //sqLiteDatabase.execSQL(knightTable); /*Convert this table to enemy table*/

        sqLiteDatabase.execSQL(itemTable);
        sqLiteDatabase.execSQL(inventoryTable);


        //Generate items that are inside the system.

    }

    private void generateItems() {
        ArrayList<Item> itemList = new ArrayList<Item>();
        //These are the default weapons, must always be ID 1, 2, 3
        Weapon defaultWeapon = new Weapon(/*Name*/ /*Desc*/ /*Atk*/ /*Cost*/ /*SkinId*/);
        Armor defaultArmor = new Armor(/*Name*/ /*Desc*/ /*Def*/ /*Cost*/ /*SkinId*/),
                defaultShield = new Armor(/*Name*/ /*Desc*/ /*Def*/ /*Cost*/ /*SkinId*/);

        //These are the rest of the items
        /****coming soon*****/


        itemList.add(defaultArmor);
        itemList.add(defaultWeapon);
        itemList.add(defaultShield);
        //Add to DB.

        for (int i = 0; i < itemList.size(); i++) {
            if(itemList.get(i) instanceof Armor) {
                
            }
            else if (itemList.get(i) instanceof Weapon) {

            }

            else {
                Log.i("LOGS|DATABASEHELPER", "Fail to identify item type.");
            }

        }


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


    public boolean deleteKnight (long id){

        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Knight.TABLE_NAME,
                Knight.COLUMN_ID +  "= ?",
                new String[]{id + ""});
        return rowsAffected > 0;
    }

    public long addArmor(Armor armor){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Armor.COLUMN_NAME, armor.getName());
        contentValues.put(Armor.COLUMN_BOOST, armor.getDefenseIncrease());
        contentValues.put(Armor.COLUMN_DESCRIPTION, armor.getDescription());
        contentValues.put(Armor.COLUMN_COST, armor.getCost());

        long id = db.insert(Armor.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public boolean updateArmor(Armor updatedArmor, int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Armor.COLUMN_NAME, updatedArmor.getName());
        contentValues.put(Armor.COLUMN_BOOST, updatedArmor.getDefenseIncrease());
        contentValues.put(Armor.COLUMN_DESCRIPTION, updatedArmor.getDescription());
        contentValues.put(Armor.COLUMN_COST, updatedArmor.getCost());

        db.update(Armor.TABLE_NAME, contentValues, Armor.COLUMN_ID + "=?", new String[]{updatedArmor.getId() + ""});
        return true;
    }

    public Armor getArmor(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Armor.TABLE_NAME,
                null,
                Armor.COLUMN_ID + "= ?",
                new String[]{id + ""},
                null,
                null,
                null);
        Armor a = null;
        if(c.moveToFirst()) {
            a = new Armor();
            a.setName(c.getString(c.getColumnIndex(Armor.COLUMN_NAME)));
            a.setDefenseIncrease(c.getInt(c.getColumnIndex(Armor.COLUMN_BOOST)));
            a.setDescription(c.getString(c.getColumnIndex(Armor.COLUMN_DESCRIPTION)));
            a.setCost(c.getInt(c.getColumnIndex(Armor.COLUMN_COST)));
            a.setId(id);
        }

        c.close();
        db.close();
        return a;
    }

    public boolean deleteArmor (long id){

        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Armor.TABLE_NAME,
                Armor.COLUMN_ID +  "= ?",
                new String[]{id + ""});
        return rowsAffected > 0;
    }

    public long addWeapon(Weapon weapon){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Weapon.COLUMN_NAME, weapon.getName());
        contentValues.put(Weapon.COLUMN_BOOST, weapon.getAttackIncrease());
        contentValues.put(Weapon.COLUMN_DESCRIPTION, weapon.getDescription());
        contentValues.put(Weapon.COLUMN_COST, weapon.getCost());

        long id = db.insert(Weapon.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public boolean updateWeapon(Weapon updatedWeapon, int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Armor.COLUMN_NAME, updatedWeapon.getName());
        contentValues.put(Armor.COLUMN_BOOST, updatedWeapon.getAttackIncrease());
        contentValues.put(Armor.COLUMN_DESCRIPTION, updatedWeapon.getDescription());
        contentValues.put(Armor.COLUMN_COST, updatedWeapon.getCost());

        db.update(Weapon.TABLE_NAME, contentValues, Weapon.COLUMN_ID + "=?", new String[]{updatedWeapon.getId() + ""});
        return true;
    }

    public Weapon getWeapon(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Armor.TABLE_NAME,
                null,
                Armor.COLUMN_ID + "= ?",
                new String[]{id + ""},
                null,
                null,
                null);
        Weapon w = null;
        if(c.moveToFirst()) {
            w = new Weapon();
            w.setName(c.getString(c.getColumnIndex(Armor.COLUMN_NAME)));
            w.setAttackIncrease((c.getInt(c.getColumnIndex(Armor.COLUMN_BOOST))));
            w.setDescription(c.getString(c.getColumnIndex(Armor.COLUMN_DESCRIPTION)));
            w.setCost(c.getInt(c.getColumnIndex(Armor.COLUMN_COST)));
            w.setId(id);
        }
        c.close();
        db.close();
        return w;
    }

    public boolean deleteWeapon (long id){

        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Weapon.TABLE_NAME,
                Weapon.COLUMN_ID +  "= ?",
                new String[]{id + ""});
        return rowsAffected > 0;
    }
}
