package com.example.hp.SneakAlert;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by shweta on 02-10-2017.
 */

public class DatabaseAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sneak_Alert.db";

    private static final String CREATE_TABLE_OWNER = "create table " + "OWNER" + "(NAME text,EMAIL text,PASSWORD text,IMAGE_LOC text,APPS text);";
    private static final String CREATE_TABLE_TRUSTED = "create table " + "TRUSTED" + "(NAME text,IMAGE_ID text,IMAGE_LOC text);";
    private static final String CREATE_TABLE_BLOCKED = "create table " + "BLOCKED" + "(NAME text,IMAGE_ID text,IMAGE_LOC text);";
    private static final String CREATE_TABLE_LOGS = "create table " + "LOGS" + "(NAME text,APP_NAME text,LOGIN_TIME time,DURATION time,DATE date);";
    private static final String CREATE_TABLE_LOC_DEF = "create table " + "LOC_DEF" + "(LOC_NAME text,LONG integer,LATIT integer,SETTINGS text);";
    private static final String CREATE_TABLE_SETTINGS = "create table " + "SETTINGS" + "(NAME text,STATUS text);";

    private static SQLiteDatabase db;

    public DatabaseAdapter(Context context) {
        super(context, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(CREATE_TABLE_OWNER);
        _db.execSQL(CREATE_TABLE_TRUSTED);
        _db.execSQL(CREATE_TABLE_BLOCKED);
        _db.execSQL(CREATE_TABLE_LOGS);
        _db.execSQL(CREATE_TABLE_LOC_DEF);
        _db.execSQL(CREATE_TABLE_SETTINGS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        onCreate(_db);
    }


    public DatabaseAdapter open() throws SQLException {
        db = this.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    void Insert_Into_Owner(String name,String email_id,String password,String img_loc,String apps){
        ContentValues newValues = new ContentValues();
        newValues.put("NAME",name);
        newValues.put("EMAIL",email_id);
        newValues.put("PASSWORD",password);
        newValues.put("IMAGE_LOC",img_loc);
        newValues.put("APPS",apps);
        db.insert("OWNER", null, newValues);
    }
    void Insert_Into_Trusted(String name,String img_id,String img_loc){
        ContentValues newValues = new ContentValues();
        newValues.put("NAME",name);
        newValues.put("IMAGE_ID",img_id);
        newValues.put("IMAGE_LOC",img_loc);
        db.insert("TRUSTED", null, newValues);
    }
    void Insert_Into_Blocked(String name,String img_id,String img_loc){
        ContentValues newValues = new ContentValues();
        newValues.put("NAME",name);
        newValues.put("IMAGE_ID",img_id);
        newValues.put("IMAGE_LOC",img_loc);
        db.insert("BLOCKED", null, newValues);
    }

    void Insert_Into_Logs(String name, String app_name, Time login_time, Time duration, Date date){
        ContentValues newValues = new ContentValues();
        newValues.put("NAME",name);
        newValues.put("APP_NAME",app_name);
        newValues.put("LOGIN_TIME", String.valueOf(login_time));
        newValues.put("DURATION", String.valueOf(duration));
        newValues.put("DATE", String.valueOf(date));
        db.insert("LOGS", null, newValues);
    }
    void Insert_Into_Loc_Def(String loc_name,int longit,int latit,String settings){
        ContentValues newValues = new ContentValues();
        newValues.put("NAME",loc_name);
        newValues.put("LONG",longit);
        newValues.put("LATIT",latit);
        newValues.put("SETTINGS",settings);
        db.insert("LOC_DEF", null, newValues);
    }
    void Insert_Into_Settings(String name,String status){
        ContentValues newValues = new ContentValues();
        newValues.put("NAME",name);
        newValues.put("STATUS",status);
        db.insert("SETTINGS", null, newValues);
    }
}
