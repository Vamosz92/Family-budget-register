package com.example.beadando;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDataBase {

    private static SQLiteDatabase dbHelper;

    public MyDataBase(Context context){
        dbHelper = new DataBaseHelper(context).getWritableDatabase();
    }

    public void hozzaad(String kategoria, String megnevezes, int osszeg, String datum){
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COLUMN_NAME_KATEGORIA, kategoria);
        values.put(DataBaseHelper.COLUMN_NAME_MEGNEVEZES, megnevezes);
        values.put(DataBaseHelper.COLUMN_NAME_OSSZEG, osszeg);
        values.put(DataBaseHelper.COLUMN_NAME_DATUM, datum);
        dbHelper.insert(DataBaseHelper.TABLE_NAME, null, values);
    }

    public Cursor listazas(){
        Cursor c = dbHelper.query(DataBaseHelper.TABLE_NAME,
                new String[]{
                        DataBaseHelper.COLUMN_NAME_ID,
                        DataBaseHelper.COLUMN_NAME_KATEGORIA,
                        DataBaseHelper.COLUMN_NAME_MEGNEVEZES,
                        DataBaseHelper.COLUMN_NAME_OSSZEG,
                        DataBaseHelper.COLUMN_NAME_DATUM}, null,null,null,null,null);

        if(c.moveToFirst() || c.getCount()!=0){
            c.moveToFirst();
        }
        else {
            c.close();
            return null;
        }
        return c;
    }

    public Cursor listaSzures(String kategoria){
        Cursor c = dbHelper.query(DataBaseHelper.TABLE_NAME,
                new String[]{
                        DataBaseHelper.COLUMN_NAME_KATEGORIA,
                        DataBaseHelper.COLUMN_NAME_MEGNEVEZES,
                        DataBaseHelper.COLUMN_NAME_OSSZEG,
                        DataBaseHelper.COLUMN_NAME_DATUM}, DataBaseHelper.COLUMN_NAME_KATEGORIA + "=?",
                new String[]{kategoria},null,null,null);

        if(c.moveToFirst() || c.getCount()!=0){
            c.moveToFirst();
        }
        else {
            c.close();
            return null;
        }
        return c;
    }

    public Cursor datumSzerintRendezNovekvo(){
        Cursor c = dbHelper.query(DataBaseHelper.TABLE_NAME,
                new String[]{
                        DataBaseHelper.COLUMN_NAME_KATEGORIA,
                        DataBaseHelper.COLUMN_NAME_MEGNEVEZES,
                        DataBaseHelper.COLUMN_NAME_OSSZEG,
                        DataBaseHelper.COLUMN_NAME_DATUM}, null,
                null, null, null, DataBaseHelper.COLUMN_NAME_DATUM);
        if(c.moveToFirst() || c.getCount()!=0){
            c.moveToFirst();
        }
        else {
            c.close();
            return null;
        }
        return c;
    }

    public Cursor datumSzerintRendezCsokkeno(){
        Cursor c = dbHelper.query(DataBaseHelper.TABLE_NAME,
                new String[]{
                        DataBaseHelper.COLUMN_NAME_KATEGORIA,
                        DataBaseHelper.COLUMN_NAME_MEGNEVEZES,
                        DataBaseHelper.COLUMN_NAME_OSSZEG,
                        DataBaseHelper.COLUMN_NAME_DATUM}, null,
                null, null, null, DataBaseHelper.COLUMN_NAME_DATUM + " DESC");
        if(c.moveToFirst() || c.getCount()!=0){
            c.moveToFirst();
        }
        else {
            c.close();
            return null;
        }
        return c;
    }

    public void torolSort(int _id){
        dbHelper.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper.COLUMN_NAME_ID + " = ?", new String[]{_id+""});
    }
}