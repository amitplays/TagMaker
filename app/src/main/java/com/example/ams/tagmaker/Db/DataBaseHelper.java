package com.example.ams.tagmaker.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static String DB_NAME = "Tags.db";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 2);
        this.myContext = context;
    }



    // Insert Data in TagList

    public boolean insertTAG(int tagID, String tagName,String tagDescription, String SecureTagDescription){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TagId",tagID);
        contentValues.put("TagName",tagName);
        contentValues.put("TagDescription",tagDescription);
        contentValues.put("SecureTagDescription",SecureTagDescription);
       long result = db2.insert(tagName, null , contentValues);

        db2.close();

        return result != -1;

    }

    public void createTagList(String TagListTableName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+ TagListTableName +" (TagId TEXT PRIMARY KEY NOT NULL ,TagName TEXT,TagDescription TEXT, SecureTagDescription TEXT)");



    }







    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL("create table if not exists tagList (TagId TEXT PRIMARY KEY NOT NULL ,TagName TEXT,TagDescription TEXT, SecureTagDescription TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
