package com.ttfm.android.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import com.ttfm.android.sdk.data.LastPlayInfo;
import com.ttfm.android.sdk.utils.DatabaseUtils;
import java.util.ArrayList;
import java.util.List;

public class ChannelLastPlayDB {
    private TTFMBaseDB baseDb;

    public static abstract class LastPlayEntry implements BaseColumns {
        public static final String COLUMN_NAME_CHANNEL_ID = "channelID";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_HAS_ZAN = "hasZan";
        public static final String COLUMN_NAME_IS_COLLECTED = "isCollected";
        public static final String COLUMN_NAME_IS_HATED = "isHated";
        public static final String COLUMN_NAME_IS_SKIPPED = "isSkipped";
        public static final String COLUMN_NAME_MUSIC_ID = "musicID";
        public static final String COLUMN_NAME_PLAYEDMS = "playedMs";
        public static final String COLUMN_NAME_SERIALNO = "serialNo";
        public static final String TABLE_NAME = "table_channel_last_play";
    }

    public ChannelLastPlayDB(TTFMBaseDB tTFMBaseDB) {
        this.baseDb = tTFMBaseDB;
    }

    public synchronized LastPlayInfo[] getLastPlayList() {
        List arrayList;
        Throwable th;
        arrayList = new ArrayList();
        Cursor query;
        try {
            query = DatabaseUtils.query(this.baseDb.getReadableDatabase(), LastPlayEntry.TABLE_NAME, null, null, null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        do {
                            arrayList.add(createEntity(query));
                        } while (query.moveToNext());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    this.baseDb.close();
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            this.baseDb.close();
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            this.baseDb.close();
            throw th;
        }
        return (LastPlayInfo[]) arrayList.toArray(new LastPlayInfo[0]);
    }

    public synchronized LastPlayInfo getLastPlay(int i) {
        Throwable th;
        LastPlayInfo lastPlayInfo = null;
        synchronized (this) {
            Cursor query;
            try {
                query = DatabaseUtils.query(this.baseDb.getReadableDatabase(), LastPlayEntry.TABLE_NAME, null, "channelID = ?", new String[]{String.valueOf(i)}, null, null, null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            lastPlayInfo = createEntity(query);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        this.baseDb.close();
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                this.baseDb.close();
            } catch (Throwable th3) {
                th = th3;
                query = null;
                if (query != null) {
                    query.close();
                }
                this.baseDb.close();
                throw th;
            }
        }
        return lastPlayInfo;
    }

    public synchronized boolean addLastPlay(LastPlayInfo lastPlayInfo) {
        boolean insertOrReplace;
        ContentValues contentValues = new ContentValues();
        contentValues.put("channelID", Integer.valueOf(lastPlayInfo.channelId));
        contentValues.put(LastPlayEntry.COLUMN_NAME_MUSIC_ID, Long.valueOf(lastPlayInfo.musicId));
        contentValues.put(LastPlayEntry.COLUMN_NAME_SERIALNO, Long.valueOf(lastPlayInfo.serialNo));
        contentValues.put("duration", Integer.valueOf(lastPlayInfo.duration));
        contentValues.put(LastPlayEntry.COLUMN_NAME_PLAYEDMS, Integer.valueOf(lastPlayInfo.playedMS));
        contentValues.put(LastPlayEntry.COLUMN_NAME_IS_SKIPPED, Boolean.valueOf(lastPlayInfo.isSkipped));
        contentValues.put(LastPlayEntry.COLUMN_NAME_IS_COLLECTED, Boolean.valueOf(lastPlayInfo.isCollected));
        contentValues.put(LastPlayEntry.COLUMN_NAME_HAS_ZAN, Boolean.valueOf(lastPlayInfo.hasZan));
        contentValues.put(LastPlayEntry.COLUMN_NAME_IS_HATED, Boolean.valueOf(lastPlayInfo.isHated));
        String str = "channelID = ?";
        String[] strArr = new String[]{String.valueOf(lastPlayInfo.channelId)};
        try {
            insertOrReplace = DatabaseUtils.insertOrReplace(this.baseDb.getWritableDatabase(), contentValues, LastPlayEntry.TABLE_NAME, new String[]{"_id"}, str, strArr);
        } finally {
            this.baseDb.close();
        }
        return insertOrReplace;
    }

    public synchronized boolean deleteLastPlay(int i) {
        boolean z = true;
        TTFMBaseDB tTFMBaseDB = null;
        synchronized (this) {
            int i2 = "channelID = ?";
            try {
                i2 = DatabaseUtils.delete(this.baseDb.getWritableDatabase(), LastPlayEntry.TABLE_NAME, i2, new String[]{String.valueOf(i)});
                if (i2 <= 0) {
                    z = tTFMBaseDB;
                }
            } finally {
                tTFMBaseDB = this.baseDb;
                tTFMBaseDB.close();
            }
        }
        return z;
    }

    public synchronized void cleanAll() {
        try {
            DatabaseUtils.delete(this.baseDb.getWritableDatabase(), LastPlayEntry.TABLE_NAME, null, null);
            this.baseDb.close();
        } catch (Throwable th) {
            this.baseDb.close();
        }
    }

    public synchronized int getNum() {
        Cursor cursor = null;
        int i = 0;
        synchronized (this) {
            try {
                cursor = DatabaseUtils.rawQuery(this.baseDb.getReadableDatabase(), "select count(*) from table_channel_last_play", null);
                if (cursor != null && cursor.moveToFirst()) {
                    i = (int) cursor.getLong(0);
                }
                if (cursor != null) {
                    cursor.close();
                }
                this.baseDb.close();
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                this.baseDb.close();
            }
        }
        return i;
    }

    private LastPlayInfo createEntity(Cursor cursor) {
        boolean z;
        boolean z2 = true;
        LastPlayInfo lastPlayInfo = new LastPlayInfo();
        lastPlayInfo.channelId = DatabaseUtils.getInt(cursor, "channelID");
        lastPlayInfo.musicId = DatabaseUtils.getLong(cursor, LastPlayEntry.COLUMN_NAME_MUSIC_ID);
        lastPlayInfo.serialNo = DatabaseUtils.getLong(cursor, LastPlayEntry.COLUMN_NAME_SERIALNO);
        lastPlayInfo.duration = DatabaseUtils.getInt(cursor, "duration");
        lastPlayInfo.playedMS = DatabaseUtils.getInt(cursor, LastPlayEntry.COLUMN_NAME_PLAYEDMS);
        lastPlayInfo.isSkipped = DatabaseUtils.getInt(cursor, LastPlayEntry.COLUMN_NAME_IS_SKIPPED) == 1;
        if (DatabaseUtils.getInt(cursor, LastPlayEntry.COLUMN_NAME_IS_COLLECTED) == 1) {
            z = true;
        } else {
            z = false;
        }
        lastPlayInfo.isCollected = z;
        if (DatabaseUtils.getInt(cursor, LastPlayEntry.COLUMN_NAME_HAS_ZAN) == 1) {
            z = true;
        } else {
            z = false;
        }
        lastPlayInfo.hasZan = z;
        if (DatabaseUtils.getInt(cursor, LastPlayEntry.COLUMN_NAME_IS_HATED) != 1) {
            z2 = false;
        }
        lastPlayInfo.isHated = z2;
        return lastPlayInfo;
    }
}
