package com.ttfm.android.sdk.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.ttfm.android.sdk.entity.ChannelEntity;
import com.ttfm.android.sdk.entity.ChannelEntity.LabelInfo;
import com.ttfm.android.sdk.utils.DatabaseUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChannelPlayHistoryDB {
    public static final int MAX_NUM = 25;
    private TTFMBaseDB baseDb;

    public static abstract class ChannelHistoryEntry implements BaseColumns {
        public static final String COLUMN_NAME_BACKGROUND_IMG = "bgImgUrl";
        public static final String COLUMN_NAME_CHANNEL_ID = "channelID";
        public static final String COLUMN_NAME_CHANNEL_NAME = "channelName";
        public static final String COLUMN_NAME_CHANNEL_TAG = "channelTag";
        public static final String COLUMN_NAME_CHANNEL_TYPE = "channelType";
        public static final String COLUMN_NAME_NEWSONG_COUNT = "newSongCount";
        public static final String COLUMN_NAME_NEWTOPIC_COUNT = "newTopicCount";
        public static final String COLUMN_NAME_SORTTIME = "sortTime";
        public static final String TABLE_NAME = "table_channel_play_history";
    }

    public ChannelPlayHistoryDB(TTFMBaseDB tTFMBaseDB) {
        this.baseDb = tTFMBaseDB;
    }

    public synchronized List<ChannelEntity> getList() {
        List<ChannelEntity> arrayList;
        Cursor query;
        Throwable th;
        arrayList = new ArrayList();
        try {
            query = DatabaseUtils.query(this.baseDb.getReadableDatabase(), ChannelHistoryEntry.TABLE_NAME, null, null, null, null, null, "sortTime DESC");
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        arrayList.add(createEntity(query));
                        while (query.moveToNext()) {
                            arrayList.add(createEntity(query));
                        }
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
        return arrayList;
    }

    public synchronized boolean add(ChannelEntity channelEntity) {
        boolean insertOrReplace;
        ContentValues contentValues = new ContentValues();
        contentValues.put("channelID", Integer.valueOf(channelEntity.getChannelId()));
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_CHANNEL_NAME, channelEntity.getChannelName());
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_BACKGROUND_IMG, channelEntity.getChannelBackgroundImg());
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_NEWSONG_COUNT, channelEntity.getNewSongCount());
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_NEWTOPIC_COUNT, channelEntity.getNewTopicCount());
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_CHANNEL_TYPE, Integer.valueOf(channelEntity.getCiType()));
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_CHANNEL_TAG, toJson(channelEntity.getLabelInfos()));
        contentValues.put(ChannelHistoryEntry.COLUMN_NAME_SORTTIME, Long.valueOf(System.currentTimeMillis()));
        String str = "channelID = ?";
        String[] strArr = new String[]{String.valueOf(channelEntity.getChannelId())};
        try {
            insertOrReplace = DatabaseUtils.insertOrReplace(this.baseDb.getWritableDatabase(), contentValues, ChannelHistoryEntry.TABLE_NAME, new String[]{"_id"}, str, strArr);
        } finally {
            this.baseDb.close();
        }
        return insertOrReplace;
    }

    public synchronized List<ChannelEntity> fixListSizeTo(int i) {
        List<ChannelEntity> list;
        Cursor query;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            List<ChannelEntity> arrayList = new ArrayList();
            try {
                SQLiteDatabase writableDatabase = this.baseDb.getWritableDatabase();
                Cursor rawQuery = DatabaseUtils.rawQuery(writableDatabase, "select count(*) from table_channel_play_history", null);
                if (rawQuery == null) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    this.baseDb.close();
                    list = null;
                } else {
                    try {
                        int i2;
                        if (rawQuery.moveToFirst()) {
                            i2 = (int) rawQuery.getLong(0);
                        } else {
                            i2 = 0;
                        }
                        rawQuery.close();
                        if (i2 > i) {
                            List arrayList2 = new ArrayList();
                            query = DatabaseUtils.query(writableDatabase, ChannelHistoryEntry.TABLE_NAME, null, null, null, null, null, "sortTime DESC");
                            if (query == null) {
                                if (query != null) {
                                    query.close();
                                }
                                this.baseDb.close();
                                list = null;
                            } else {
                                try {
                                    if (query.moveToFirst()) {
                                        arrayList2.add(createEntity(query));
                                        while (query.moveToNext()) {
                                            arrayList2.add(createEntity(query));
                                        }
                                    }
                                    if (query != null) {
                                        query.close();
                                    }
                                    while (i < i2) {
                                        String[] strArr = new String[]{String.valueOf(((ChannelEntity) arrayList2.get(i)).getChannelId())};
                                        if (DatabaseUtils.delete(writableDatabase, ChannelHistoryEntry.TABLE_NAME, "channelID = ?", strArr) > 0) {
                                            arrayList.add(arrayList2.get(i));
                                        }
                                        i++;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = query;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    this.baseDb.close();
                                    throw th;
                                }
                            }
                        }
                        query = rawQuery;
                        if (query != null) {
                            query.close();
                        }
                        this.baseDb.close();
                        list = arrayList;
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = rawQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        this.baseDb.close();
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                if (cursor != null) {
                    cursor.close();
                }
                this.baseDb.close();
                throw th;
            }
        }
        return list;
    }

    public synchronized boolean update(ChannelEntity channelEntity, boolean z) {
        boolean z2 = true;
        TTFMBaseDB tTFMBaseDB = null;
        synchronized (this) {
            int i = "channelID = ?";
            String[] strArr = new String[]{String.valueOf(channelEntity.getChannelId())};
            ContentValues contentValues = new ContentValues();
            contentValues.put("channelID", Integer.valueOf(channelEntity.getChannelId()));
            contentValues.put(ChannelHistoryEntry.COLUMN_NAME_CHANNEL_NAME, channelEntity.getChannelName());
            contentValues.put(ChannelHistoryEntry.COLUMN_NAME_BACKGROUND_IMG, channelEntity.getChannelBackgroundImg());
            contentValues.put(ChannelHistoryEntry.COLUMN_NAME_NEWSONG_COUNT, channelEntity.getNewSongCount());
            contentValues.put(ChannelHistoryEntry.COLUMN_NAME_NEWTOPIC_COUNT, channelEntity.getNewTopicCount());
            contentValues.put(ChannelHistoryEntry.COLUMN_NAME_CHANNEL_TYPE, Integer.valueOf(channelEntity.getCiType()));
            contentValues.put(ChannelHistoryEntry.COLUMN_NAME_CHANNEL_TAG, toJson(channelEntity.getLabelInfos()));
            if (z) {
                contentValues.put(ChannelHistoryEntry.COLUMN_NAME_SORTTIME, Long.valueOf(System.currentTimeMillis()));
            }
            try {
                i = DatabaseUtils.update(this.baseDb.getWritableDatabase(), ChannelHistoryEntry.TABLE_NAME, contentValues, i, strArr);
                if (i <= 0) {
                    z2 = tTFMBaseDB;
                }
            } finally {
                tTFMBaseDB = this.baseDb;
                tTFMBaseDB.close();
            }
        }
        return z2;
    }

    public synchronized boolean delete(int i) {
        boolean z = true;
        TTFMBaseDB tTFMBaseDB = null;
        synchronized (this) {
            int i2 = "channelID = ?";
            try {
                i2 = DatabaseUtils.delete(this.baseDb.getWritableDatabase(), ChannelHistoryEntry.TABLE_NAME, i2, new String[]{String.valueOf(i)});
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

    public synchronized void clean() {
        try {
            DatabaseUtils.delete(this.baseDb.getWritableDatabase(), ChannelHistoryEntry.TABLE_NAME, null, null);
            this.baseDb.close();
        } catch (Throwable th) {
            this.baseDb.close();
        }
    }

    public synchronized int getTotalNum() {
        Cursor cursor = null;
        int i = 0;
        synchronized (this) {
            try {
                cursor = DatabaseUtils.rawQuery(this.baseDb.getReadableDatabase(), "select count(*) from table_channel_play_history", null);
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

    private ChannelEntity createEntity(Cursor cursor) {
        ChannelEntity channelEntity = new ChannelEntity();
        channelEntity.setChannelId(DatabaseUtils.getInt(cursor, "channelID"));
        channelEntity.setChannelName(DatabaseUtils.getString(cursor, ChannelHistoryEntry.COLUMN_NAME_CHANNEL_NAME));
        channelEntity.setChannelBackgroundImg(cursor.getString(cursor.getColumnIndexOrThrow(ChannelHistoryEntry.COLUMN_NAME_BACKGROUND_IMG)));
        channelEntity.setNewSongCount(DatabaseUtils.getString(cursor, ChannelHistoryEntry.COLUMN_NAME_NEWSONG_COUNT));
        channelEntity.setNewTopicCount(DatabaseUtils.getString(cursor, ChannelHistoryEntry.COLUMN_NAME_NEWTOPIC_COUNT));
        channelEntity.setCollected(false);
        channelEntity.setLastModifyTime(DatabaseUtils.getLong(cursor, ChannelHistoryEntry.COLUMN_NAME_SORTTIME));
        channelEntity.setCiType(DatabaseUtils.getInt(cursor, ChannelHistoryEntry.COLUMN_NAME_CHANNEL_TYPE));
        channelEntity.setLabelInfos(toObject(DatabaseUtils.getString(cursor, ChannelHistoryEntry.COLUMN_NAME_CHANNEL_TAG)));
        return channelEntity;
    }

    private String toJson(ArrayList<LabelInfo> arrayList) {
        String str = "";
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                LabelInfo labelInfo = (LabelInfo) it.next();
                if (labelInfo != null) {
                    if (!str.isEmpty()) {
                        str = str + ";";
                    }
                    str = str + labelInfo.getLiId() + SelectCountryActivity.SPLITTER + labelInfo.getLiName() + SelectCountryActivity.SPLITTER + labelInfo.getLiValue();
                }
            }
        }
        return str;
    }

    private ArrayList<LabelInfo> toObject(String str) {
        ArrayList<LabelInfo> arrayList = new ArrayList();
        if (str != null) {
            for (String str2 : str.split(";")) {
                if (str2 != null) {
                    String[] split = str2.split(SelectCountryActivity.SPLITTER);
                    if (split != null && split.length >= 3) {
                        LabelInfo labelInfo = new LabelInfo();
                        try {
                            labelInfo.setLiId((long) Integer.valueOf(split[0]).intValue());
                        } catch (Exception e) {
                        }
                        labelInfo.setLiName(split[1]);
                        labelInfo.setLiValue(split[2]);
                        arrayList.add(labelInfo);
                    }
                }
            }
        }
        return arrayList;
    }
}
