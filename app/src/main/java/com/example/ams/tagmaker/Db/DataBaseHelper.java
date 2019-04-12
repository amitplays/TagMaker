package com.example.ams.tagmaker.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ams.tagmaker.Models.TagListModel;
import com.example.ams.tagmaker.Models.TagNameModel;

import java.util.ArrayList;
import java.util.List;

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

    public boolean insertListName(int tagID, String tagName) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TagId", tagID);
        contentValues.put("TagName", tagName);
        long result = db2.insert("tagList", null, contentValues);

        db2.close();

        return result != -1;

    }

    public void createTagList(String TagListTableName){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+ TagListTableName +" (TagId TEXT PRIMARY KEY NOT NULL ,TagName TEXT,TagDescription TEXT, SecureTagDescription TEXT)");

    }


    public List<TagListModel> getdata(String TABLE) {

        List<TagListModel> data = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE + " ;", null);
        StringBuffer stringBuffer = new StringBuffer();

        TagListModel dataModel;
        while (cursor.moveToNext()) {
            dataModel = new TagListModel();
            String TagName = cursor.getString(cursor.getColumnIndexOrThrow("TagName"));
            String TagDescription = cursor.getString(cursor.getColumnIndexOrThrow("TagDescription"));
            String SecureTagDescription = cursor.getString(cursor.getColumnIndexOrThrow("SecureTagDescription"));
            dataModel.setTagName(TagName);
            dataModel.setTagDescription(TagDescription);
            dataModel.setSecretDescription(SecureTagDescription);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        return data;
    }

    public List<TagNameModel> getListName() {

        List<TagNameModel> data = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tagList ;", null);
        StringBuffer stringBuffer = new StringBuffer();

        TagNameModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel = new TagNameModel();
            String TagName = cursor.getString(cursor.getColumnIndexOrThrow("TagName"));
            dataModel.setTagName(TagName);
            stringBuffer.append(dataModel);
            data.add(dataModel);
        }
        return data;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists tagList (TagID INTEGER, TagName TEXT PRIMARY KEY NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
