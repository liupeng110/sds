package com.ttfm.android.sdk.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class TTFMBaseDB extends SQLiteClosable {
    public static final String DB_NAME = "ttfm.db";
    private static TTFMBaseDB instance;
    private ChannelLastPlayDB clpdb;
    private TTFMBaseDBHelper mDbHelper;
    private ChannelPlayHistoryDB sldb;

    public static ChannelPlayHistoryDB getChannelPlayHistoryDB(Context context) {
        TTFMBaseDB instance = getInstance(context);
        if (instance.sldb == null) {
            instance.sldb = new ChannelPlayHistoryDB(instance);
        }
        return instance.sldb;
    }

    public static ChannelLastPlayDB getChannelLastPlayDB(Context context) {
        TTFMBaseDB instance = getInstance(context);
        if (instance.clpdb == null) {
            instance.clpdb = new ChannelLastPlayDB(instance);
        }
        return instance.clpdb;
    }

    public static synchronized void closeDatabase(Context context) {
        synchronized (TTFMBaseDB.class) {
            TTFMBaseDB instance = getInstance(context);
            if (instance != null) {
                instance.close();
            }
            instance = null;
        }
    }

    private static synchronized TTFMBaseDB getInstance(Context context) {
        TTFMBaseDB tTFMBaseDB;
        synchronized (TTFMBaseDB.class) {
            if (instance == null) {
                instance = new TTFMBaseDB(context);
            }
            tTFMBaseDB = instance;
        }
        return tTFMBaseDB;
    }

    private TTFMBaseDB(Context context) {
        this.mDbHelper = TTFMBaseDBHelper.getInstance(context);
    }

    public SQLiteDatabase getWritableDatabase() {
        acquireReference();
        return this.mDbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        acquireReference();
        return this.mDbHelper.getReadableDatabase();
    }

    protected void onAllReferencesReleased() {
        this.mDbHelper.close();
    }
}
