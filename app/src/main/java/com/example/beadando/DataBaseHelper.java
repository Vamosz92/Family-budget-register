package com.example.beadando;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "koltsegvetes.db";
    public static final String TABLE_NAME = "Penzmozgások";
    public static final String COLUMN_NAME_ID = "Id";
    public static final String COLUMN_NAME_KATEGORIA = "Kategória";
    public static final String COLUMN_NAME_MEGNEVEZES = "Megnevezés";
    public static final String COLUMN_NAME_OSSZEG = "Összeg";
    public static final String COLUMN_NAME_DATUM = "Dátum";

    private static final String SQL_CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    COLUMN_NAME_KATEGORIA + " TEXT, " +
                    COLUMN_NAME_MEGNEVEZES + " TEXT, " +
                    COLUMN_NAME_OSSZEG + " INT, " +
                    COLUMN_NAME_DATUM + " TEXT)";


    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper(Context context){
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

}