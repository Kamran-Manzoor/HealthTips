package com.example.salman.healthtips;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Salman on 7/19/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Data.db";
    public static final String TABLE_NAMEHealth = "Health";
    public static final String TABLE_NAMEFitness = "Fitness";
   public static final String TABLE_NAMEBone = "Bones";
    public static final String TABLE_NAMEHairs = "Hairs";
    public static final String TABLE_NAMESkin = "Skin";
    public static final String TABLE_NAME = "Healthtips";

    public static final String COL_1 = "Title";
    public static final String COL_2 = "Content";
    public static final String COL_3 = "Url";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAMEHealth +" (Title TEXT,Content TEXT,Url TEXT)");
        db.execSQL("create table " + TABLE_NAMEHairs +" (Title TEXT,Content TEXT,Url TEXT)");
        db.execSQL("create table " + TABLE_NAMEFitness +" (Title TEXT,Content TEXT,Url TEXT)");
        db.execSQL("create table " + TABLE_NAMEBone +" (Title TEXT,Content TEXT,Url TEXT)");
        db.execSQL("create table " + TABLE_NAMESkin +" (Title TEXT,Content TEXT,Url TEXT)");
        db.execSQL("create table " + TABLE_NAME +" (Title TEXT,Url TEXT)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMEHealth);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMEBone);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMESkin);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMEFitness);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMEHairs);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

        onCreate(db);
    }
    public int getProfilesCount(String name) {
        String countQuery = "SELECT  * FROM " + name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }


    public boolean insertData(String name,String title,String content,String url) {
        SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, title);
        contentValues.put(COL_2, content);
        contentValues.put(COL_3, url);
        String Table_Name = name;
        long result = db.insert(Table_Name, null, contentValues);

        if (result == -1 && result == -1)
            return false;
        else {

            return true;

        }

    }
    public void create(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS " + name + "(Title TEXT,Content TEXT,Url TEXT)");

    }
     public boolean insertData1(String name,String title,String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,title);
        contentValues.put(COL_3,url);
        String Table_Name=name;
        long result = db.insert(Table_Name,null ,contentValues);

        if(result == -1 &&result==-1)
            return false;
        else{

            return true;}
    }
    public Cursor getAllData(String name ) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+name+"",null);
        return res;
    }
    public void deleteData (String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ tablename);;
    }

}
