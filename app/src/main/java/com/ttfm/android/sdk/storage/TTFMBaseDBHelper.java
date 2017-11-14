package com.ttfm.android.sdk.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TTFMBaseDBHelper extends SQLiteOpenHelper {
    private static final String COMMA_SEP = ",";
    private static final String DATE_TYPE = "DATE";
    private static final String INT_TYPE = " INT";
    private static final String SQL_CREATE_CHANNEL_LASTPLAY_TABLE = "CREATE TABLE table_channel_last_play (_id INTEGER PRIMARY KEY,channelID INT,musicID INT,serialNo INT,duration INT,playedMs INT,isSkipped INT,isCollected INT,hasZan INT,isHated INT )";
    private static final String SQL_CREATE_CHANNEL_PLAYHISTORY_TABLE = "CREATE TABLE table_channel_play_history (_id INTEGER PRIMARY KEY,channelID INT,channelName TEXT,bgImgUrl TEXT,newSongCount TEXT,newTopicCount TEXT,channelType INT,channelTag TEXT,sortTime INT )";
    private static final String SQL_DELETE_CHANNEL_LASTPLAY_TABLE = "DROP TABLE IF EXISTS table_channel_last_play";
    private static final String SQL_DELETE_CHANNEL_PLAYHISTORY_TABLE = "DROP TABLE IF EXISTS table_channel_play_history";
    private static final String TEXT_TYPE = " TEXT";
    private static final String TINYTEXT_TYPE = "TINYTEXT";
    private static TTFMBaseDBHelper mInstance = null;
    public static final int version = 1;
    private int oldVersion;

    private TTFMBaseDBHelper(Context context, String str) {
        super(context, str, null, 1);
    }

    public static synchronized TTFMBaseDBHelper getInstance(Context context) {
        TTFMBaseDBHelper tTFMBaseDBHelper;
        synchronized (TTFMBaseDBHelper.class) {
            if (mInstance == null) {
                mInstance = new TTFMBaseDBHelper(context, TTFMBaseDB.DB_NAME);
            }
            tTFMBaseDBHelper = mInstance;
        }
        return tTFMBaseDBHelper;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(SQL_CREATE_CHANNEL_PLAYHISTORY_TABLE);
        sQLiteDatabase.execSQL(SQL_CREATE_CHANNEL_LASTPLAY_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.oldVersion = i;
    }
}
