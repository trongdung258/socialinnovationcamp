package com.example.yuu.socialinnovationcamp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yuu.socialinnovationcamp.Model.Diary;

import java.util.Vector;

/**
 * Created by Yuu on 8/13/2016.
 */
public class DiaryDataBaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static String DB_NAME = "diary.db";
    public static String KEY_TIME = "time";
    public static String KEY_CONTENT = "content";
    public static String KEY_MOOD = "mood";
    public static String TABLE_NAME = "diary";


    public DiaryDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreatable = "CREATE  TABLE 'diary' "
                + "('id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,"
                + " 'time' TEXT,"
                + " 'content' TEXT)";
        db.execSQL(queryCreatable);
        String queryCreatable1 = "CREATE  TABLE 'mood' "
                + "('id' INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL ,"
                + " 'time' TEXT,"
                + " 'mood' TEXT)";
        db.execSQL(queryCreatable1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDiary(Diary diary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TIME, diary.getDateTime());
        contentValues.put(KEY_CONTENT, diary.getContent());
        db.insert(TABLE_NAME, null, contentValues);
    }

    public void addMood(String time, int mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TIME, time);
        contentValues.put(KEY_MOOD, mood);
        db.insert("mood", null, contentValues);
    }

    public Vector<Diary> getAllDiary() {
        Vector<Diary> arrayList = new Vector<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT time,content FROM diary";
        Cursor cursor = db.rawQuery(sql, new String[0]);
        while (cursor.moveToNext()) {
            String time = cursor.getString(0);
            String content = cursor.getString(1);
            Diary diary = new Diary(content, time);
            arrayList.add(diary);
        }
        cursor.close();
        return arrayList;
    }

    public String contentDiary(String time) {
        String content = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT content FROM diary where time =" + time;
        Cursor cursor = db.rawQuery(sql, new String[0]);
        while (cursor.moveToNext()) {
            content = cursor.getString(0);
        }
        cursor.close();
        return content;
    }

    public void deleteDiary(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "time = " + time, null);
    }
}
