package com.example.alexagnoii.sleepingknights;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alexagnoii.sleepingknights.Knight.Armor;
import com.example.alexagnoii.sleepingknights.Knight.Item;
import com.example.alexagnoii.sleepingknights.Knight.Knight;
import com.example.alexagnoii.sleepingknights.Knight.Weapon;

/**
 * Created by Alex Agno II on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String SCHEMA = "game";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String knightTable;
        String itemTable;
        String inventoryTable;

        knightTable = "CREATE TABLE " + Knight.TABLE_NAME + " ("
                + Knight.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Knight.COLUMN_NAME + " TEXT,"
                + Knight.COLUMN_ATTACK + " INTEGER,"
                + Knight.COLUMN_DEFENSE + " INTEGER,"
                + Knight.COLUMN_HP + " INTEGER,"
                + Knight.COLUMN_CHP + " INTEGER,"
                + Knight.COLUMN_ATTACK + " INTEGER,"
                + Knight.COLUMN_DEFENSE + " INTEGER,"
                + Knight.COLUMN_LEVEL + " INTEGER,"
                + Knight.COLUMN_EXP + " INTEGER,"
                + Knight.COLUMN_GOLD + " INTEGER,"
                + Knight.COLUMN_WEAPON + " INTEGER,"
                + Knight.COLUMN_ARMOR + " INTEGER,"
                + Knight.COLUMN_SHIELD + " INTEGER"
                + ");";

        itemTable = "CREATE TABLE " + Item.TABLE_NAME + " ("
                + Item.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Item.COLUMN_NAME + " TEXT,"
                + Item.COLUMN_DESCRIPTION + " TEXT,"
                + Item.COLUMN_TYPE + " TEXT,"
                + Item.COLUMN_BOOST + " INTEGER,"
                + Item.COLUMN_COST + " TEXT"
                + ");";

        inventoryTable = "CREATE TABLE inventory ("
                + "inventory_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "item_id INTEGER"
                + ");";

        sqLiteDatabase.execSQL(knightTable);
        sqLiteDatabase.execSQL(itemTable);
        sqLiteDatabase.execSQL(inventoryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropKnight = "DROP TABLE IF EXISTS " + Knight.TABLE_NAME+ ";";
        String dropItem = "DROP TABLE IF EXISTS " + Item.TABLE_NAME + ";";
        sqLiteDatabase.execSQL(dropKnight);
        sqLiteDatabase.execSQL(dropItem);
        onCreate(sqLiteDatabase);
    }

    public long addKnight(Knight knight){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Knight.COLUMN_NAME, knight.getName());
        contentValues.put(Knight.COLUMN_HP, knight.getHealthPoints());
        contentValues.put(Knight.COLUMN_CHP, knight.getCurrentHP());
        contentValues.put(Knight.COLUMN_ATTACK, knight.getAttack());
        contentValues.put(Knight.COLUMN_DEFENSE, knight.getDefense());
        contentValues.put(Knight.COLUMN_LEVEL, knight.getLevel());
        contentValues.put(Knight.COLUMN_EXP, knight.getExp());
        contentValues.put(Knight.COLUMN_GOLD, knight.getGold());
        contentValues.put(Knight.COLUMN_WEAPON, knight.getWeapon().getId());
        contentValues.put(Knight.COLUMN_ARMOR, knight.getArmor().getId());
        contentValues.put(Knight.COLUMN_SHIELD, knight.getShield().getId());

        long id = db.insert(Knight.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public boolean updateKnight(Knight updatedKnight, int id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Knight.COLUMN_NAME, updatedKnight.getName());
        contentValues.put(Knight.COLUMN_HP, updatedKnight.getHealthPoints());
        contentValues.put(Knight.COLUMN_CHP, updatedKnight.getCurrentHP());
        contentValues.put(Knight.COLUMN_ATTACK, updatedKnight.getAttack());
        contentValues.put(Knight.COLUMN_DEFENSE, updatedKnight.getDefense());
        contentValues.put(Knight.COLUMN_LEVEL, updatedKnight.getLevel());
        contentValues.put(Knight.COLUMN_EXP, updatedKnight.getExp());
        contentValues.put(Knight.COLUMN_GOLD, updatedKnight.getGold());
        contentValues.put(Knight.COLUMN_WEAPON, updatedKnight.getWeapon().getId());
        contentValues.put(Knight.COLUMN_ARMOR, updatedKnight.getArmor().getId());
        contentValues.put(Knight.COLUMN_SHIELD, updatedKnight.getShield().getId());

        db.update(Knight.TABLE_NAME, contentValues, Knight.COLUMN_ID + "=?", new String[]{updatedKnight.getId() + ""});
        return true;
    }

    public Knight getKnight(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Knight.TABLE_NAME,
                null,
                Knight.COLUMN_ID + "= ?",
                new String[]{id + ""},
                null,
                null,
                null);
        Knight k = null;
        if(c.moveToFirst()) {
            k = new Knight();
            k.setName(c.getString(c.getColumnIndex(Knight.COLUMN_NAME)));
            k.setAttack(c.getInt(c.getColumnIndex(Knight.COLUMN_ATTACK)));
            k.setDefense(c.getInt(c.getColumnIndex(Knight.COLUMN_DEFENSE)));
            k.setHealthPoints(c.getInt(c.getColumnIndex(Knight.COLUMN_HP)));
            k.setGold(c.getLong(c.getColumnIndex(Knight.COLUMN_GOLD)));
            k.setLevel(c.getLong(c.getColumnIndex(Knight.COLUMN_LEVEL)));
            k.setExp(c.getLong(c.getColumnIndex(Knight.COLUMN_EXP)));
            k.setCurrentHP(c.getInt(c.getColumnIndex(Knight.COLUMN_CHP)));
            k.setArmor(getArmor(c.getLong(c.getColumnIndex(Knight.COLUMN_ARMOR))));
            k.setWeapon(getWeapon(c.getLong(c.getColumnIndex(Knight.COLUMN_WEAPON))));
            k.setShield(getArmor(c.getLong(c.getColumnIndex(Knight.COLUMN_SHIELD))));
            k.setId(id);
        }
        c.close();
        db.close();
        return k;
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
}
