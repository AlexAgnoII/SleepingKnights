package com.example.alexagnoii.sleepingknights;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alexagnoii.sleepingknights.Knight.Item;
import com.example.alexagnoii.sleepingknights.Knight.Knight;

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

    }
}
