package com.example.sebastian.gamememory;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

/**
 * Created by Sebastian on 01.12.2017.
 */

public class Baza extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "GameMemoryBaza";
    public static String DATABASE_TABLE = "GameMemory";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE = "image";

    public Baza(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "("+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_IMAGE + " PHOTO" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
    public void AddCategoryToBase(String name, String image) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_IMAGE,image);
        db.insert(DATABASE_TABLE, null, cv);
        db.close();
    }
    public SQLiteCursor TakeData(){
        SQLiteDatabase db = this.getReadableDatabase();
        SQLiteCursor cursor = (SQLiteCursor) db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        //db.close();
        return  cursor;
    }
    public  void DeleteBase() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM "+ DATABASE_TABLE);
    }

}