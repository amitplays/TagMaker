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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists TagList (TagId INTEGER NOT NULL ,TagName TEXT ,TagDescription TEXT, SecureTagDescription TEXT, noOfTags INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    // Insert Data in TagList

    public boolean insertTAG(int tagID, String tagName,String tagDescription, String SecureTagDescription, int noOfTags){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TagId",tagID);
        contentValues.put("TagName",tagName);
        contentValues.put("TagDescription",tagDescription);
        contentValues.put("SecureTagDescription",SecureTagDescription);
        contentValues.put("noOfTags",noOfTags);
       long result = db2.insert("TagList", null , contentValues);

        db2.close();

        return result != -1;

    }



    public List<TagListModel> getTagDetails() {

        List<TagListModel> data = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from TagList;", null);
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

    public Cursor getListOfTags() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT TagName, noOfTags from TagList;", null);
        return cursor;
    }


//    public ArrayList<TagNameModel> getListOfTags() {
//
//        ArrayList<TagNameModel> data = new ArrayList<>();
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("SELECT DISTINCT TagName, noOfTags from TagList;", null);
//        StringBuffer stringBuffer = new StringBuffer();
//
//        TagNameModel dataModel;
//        while (cursor.moveToNext()) {
//            dataModel = new TagNameModel();
//            String TagName = cursor.getString(cursor.getColumnIndexOrThrow("TagName"));
//            int NoofTags = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("noOfTags")));
//            dataModel.setTagName(TagName);
//            dataModel.setNumbeOfTags(NoofTags);
//            data.add(dataModel);
//        }
//        return data;
//    }

}
