package com.sds.android.ttpod.media.mediastore;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.sds.android.sdk.lib.util.d;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.util.ArrayList;
import java.util.List;

public final class MediaDBHelper extends SQLiteOpenHelper {
    public static final String ACTION_UPDATE_DB_VERSION = (MediaDBHelper.class.getName() + "update_db_version");
    private static final String DB_NAME = "media.db";
    private static final int DB_VERSION = 702;
    static final int DB_VERSION_7_0_0 = 700;
    static final int DB_VERSION_7_0_1 = 701;
    static final int DB_VERSION_7_0_2 = 702;
    static final String GROUP_COLUMN_ADDED_TIMESTAMP = "group_added_time";
    static final String GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME = "associate_table_local_name";
    static final String GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME = "associate_table_online_name";
    static final String GROUP_COLUMN_CUR_BASE_ORDERBY = "base_orderby";
    static final String GROUP_COLUMN_MAP_TABLE_NAME = "map_table_name";
    static final String GROUP_COLUMN_NAME = "name";
    static final String GROUP_MAP_ADDED_TIMESTAMP = "group_map_added_time";
    static final String GROUP_MAP_COLUMN_MEDIA_ID = "_id";
    static final String GROUP_MAP_COLUMN_SORT_ORDER = "sort_order";
    public static final String KEY_DB_VERSION_NEW = "db_version_new";
    public static final String KEY_DB_VERSION_OLD = "db_version_old";
    static final String MEDIA_COLUMN_ADDED_TIME_STAMP = "added_time";
    static final String MEDIA_COLUMN_ALBUM = "album";
    static final String MEDIA_COLUMN_ALBUM_KEY = "album_key";
    static final String MEDIA_COLUMN_ARTIST = "artist";
    static final String MEDIA_COLUMN_ARTIST_KEY = "artist_key";
    static final String MEDIA_COLUMN_BIT_RATE = "bit_rate";
    static final String MEDIA_COLUMN_CHANNELS = "channels";
    static final String MEDIA_COLUMN_COMMENT = "comment";
    static final String MEDIA_COLUMN_COMPOSER = "composer";
    static final String MEDIA_COLUMN_DURATION = "duration";
    static final String MEDIA_COLUMN_ERROR_STATUS = "error_status";
    static final String MEDIA_COLUMN_EXTRA = "extra";
    static final String MEDIA_COLUMN_FILE_NAME_KEY = "file_name_key";
    static final String MEDIA_COLUMN_FOLDER = "folder";
    static final String MEDIA_COLUMN_GENRE = "genre";
    static final String MEDIA_COLUMN_GENRE_KEY = "genre_key";
    static final String MEDIA_COLUMN_GRADE = "grade";
    static final String MEDIA_COLUMN_ID = "_id";
    static final String MEDIA_COLUMN_LAST_PLAY_TIME_STAMP = "last_play_time";
    static final String MEDIA_COLUMN_LOCAL_DATA_SOURCE = "local_data_source";
    static final String MEDIA_COLUMN_MIME_TYPE = "mime_type";
    static final String MEDIA_COLUMN_MODIFIED_TIME_STAMP = "modified_time";
    static final String MEDIA_COLUMN_SAMPLE_RATE = "sample_rate";
    static final String MEDIA_COLUMN_SIZE = "size";
    static final String MEDIA_COLUMN_SONG_ID = "song_id";
    static final String MEDIA_COLUMN_START_TIME = "start_time";
    static final String MEDIA_COLUMN_TITLE = "title";
    static final String MEDIA_COLUMN_TITLE_KEY = "title_key";
    static final String MEDIA_COLUMN_TRACK = "track";
    static final String MEDIA_COLUMN_USE_COUNT = "use_count";
    static final String MEDIA_COLUMN_YEAR = "year";
    private static final String TAG = "MediaDBHelper";
    private Context mContext;
    private SQLiteDatabase mSQLiteDatabase;

    private interface TransactionCallback<Result> {
        Result runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception;
    }

    static class Builder {
        private static final String COUNT_FUNCTION = "COUNT(*)";
        private static final String TABLE_GROUP = "group_";
        private static final String TABLE_MEDIA_LOCAL = "media_local";
        private static final String TABLE_MEDIA_ONLINE = "media_online_";

        Builder() {
        }

        static String embraceWithSingleQuotationMarks(String str) {
            return "'" + str.replace("'", "''") + "'";
        }

        static String buildOnlineMediaTableName() {
            return TABLE_MEDIA_ONLINE + (System.currentTimeMillis() & System.nanoTime());
        }

        static String buildArtistGroupSelection(String str) {
            return "SELECT artist," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_ARTIST_PREFIX) + " || " + "artist" + SelectCountryActivity.SPLITTER + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY " + "artist";
        }

        static String buildAlbumGroupSelection(String str) {
            return "SELECT album," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_ALBUM_PREFIX) + " || " + "album" + SelectCountryActivity.SPLITTER + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY " + "album";
        }

        static String buildFolderGroupSelection(String str) {
            return "SELECT folder," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_FOLDER_PREFIX) + " || " + "folder" + SelectCountryActivity.SPLITTER + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY " + "folder";
        }

        static String buildGenreGroupSelection(String str) {
            return "SELECT genre," + embraceWithSingleQuotationMarks(MediaStorage.GROUP_ID_GENRE_PREFIX) + " || " + "genre" + SelectCountryActivity.SPLITTER + COUNT_FUNCTION + " FROM " + TABLE_MEDIA_LOCAL + (str == null ? "" : " WHERE " + str) + " GROUP BY " + "genre";
        }

        static String buildCustomLocalGroupSelection() {
            return "SELECT name,map_table_name,associate_table_local_name,associate_table_online_name FROM group_ WHERE associate_table_local_name NOT NULL AND associate_table_online_name IS NULL ORDER BY group_added_time DESC";
        }

        static String buildCustomOnlineGroupSelection() {
            return "SELECT name, map_table_name,associate_table_local_name,associate_table_online_name FROM group_ WHERE associate_table_online_name NOT NULL AND associate_table_local_name IS NULL ORDER BY group_added_time DESC";
        }

        static String buildCustomMixGroupSelection() {
            return "SELECT name, map_table_name,associate_table_local_name,associate_table_online_name FROM group_ WHERE associate_table_online_name NOT NULL AND associate_table_local_name NOT NULL ORDER BY group_added_time DESC";
        }

        static String buildCustomAllGroupSelection() {
            return "SELECT name, map_table_name,associate_table_local_name,associate_table_online_name FROM group_ ORDER BY group_added_time DESC";
        }

        static String buildCustomGroupAssociateMediaTableNameSelection(String str) {
            return "SELECT associate_table_local_name,associate_table_online_name,base_orderby FROM group_ WHERE map_table_name=" + embraceWithSingleQuotationMarks(str);
        }

        static String buildDeleteUnUsedItemFromCustomGroupSelection(String str, String str2, String str3) {
            String str4 = "";
            if (str2 != null && str3 != null) {
                str4 = "(SELECT " + str + "." + "_id" + " FROM " + str + " WHERE " + str + "." + "_id" + " NOT IN " + "(SELECT " + str + "." + "_id" + " FROM " + str + SelectCountryActivity.SPLITTER + str2 + " WHERE " + str + "." + "_id" + "=" + str2 + "." + "_id" + " UNION " + " SELECT " + str + "." + "_id" + " FROM " + str + SelectCountryActivity.SPLITTER + str3 + " WHERE " + str + "." + "_id" + "=" + str3 + "." + "_id" + "))";
            } else if (str2 != null) {
                str4 = "(SELECT " + str + "." + "_id" + " FROM " + str + " WHERE " + str + "." + "_id" + " NOT IN " + "(SELECT " + str + "." + "_id" + " FROM " + str + SelectCountryActivity.SPLITTER + str2 + " WHERE " + str + "." + "_id" + "=" + str2 + "." + "_id" + "))";
            } else if (str3 != null) {
                str4 = "(SELECT " + str + "." + "_id" + " FROM " + str + " WHERE " + str + "." + "_id" + " NOT IN " + "(SELECT " + str + "." + "_id" + " FROM " + str + SelectCountryActivity.SPLITTER + str3 + " WHERE " + str + "." + "_id" + "=" + str3 + "." + "_id" + "))";
            }
            return "DELETE FROM " + str + " WHERE " + str + "." + "_id" + " IN " + str4;
        }

        static String buildCustomGroupMapCountSelection(List<String> list) {
            if (list.isEmpty()) {
                throw new IllegalArgumentException("groupMapNameList must not empty!");
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT ");
            for (String append : list) {
                stringBuilder.append("(").append("SELECT ").append(COUNT_FUNCTION).append(" FROM ").append(append).append(")").append(SelectCountryActivity.SPLITTER);
            }
            return stringBuilder.substring(0, stringBuilder.length() - 1);
        }

        static String buildAddCustomGroupSelection(String str, String str2) {
            return "SELECT sort_order,group_map_added_time FROM " + str + " WHERE " + "_id" + "=" + embraceWithSingleQuotationMarks(str2);
        }

        static String buildMediaItemSelectionFromMediaLocal(String str, String str2) {
            return "SELECT _id,song_id,local_data_source,folder,title,artist,album,genre,composer,mime_type,start_time,duration,track,year,grade,bit_rate,sample_rate,channels,comment,error_status,use_count,added_time,modified_time,last_play_time,(SELECT COUNT(*) FROM group_id_customfav_local WHERE media_local._id=group_id_customfav_local._id),extra FROM media_local" + (str != null ? " WHERE " + str : "") + (str2 != null ? " ORDER BY " + unionOrderBy(str2) : "");
        }

        static String buildMediaItemSelectionFromGroupMapSelection(String str, String str2, String str3, String str4, String str5, String str6) {
            String substring;
            String str7 = null;
            String str8 = str6 == null ? "sort_order" : str6;
            if (str8.endsWith(" DESC")) {
                substring = str8.substring(0, str8.length() - " DESC".length());
            } else {
                substring = str8;
            }
            if (str2 != null) {
                str8 = buildMediaItemSelectionSelection(str, str2, "(SELECT COUNT(*) FROM group_id_customfav_local WHERE " + str2 + "." + "_id" + "=" + MediaStorage.GROUP_ID_FAV_LOCAL + "." + "_id" + ")", substring);
            } else {
                str8 = null;
            }
            if (str3 != null) {
                str7 = buildMediaItemSelectionSelection(str, str3, buildOnlineFavGroupSelection(str3, str4), substring);
            }
            if (str8 == null || str7 != null) {
                if (str8 == null && str7 != null) {
                    str8 = str7;
                } else if (str8 == null || str7 == null) {
                    throw new IllegalArgumentException("invalid query");
                } else {
                    str8 = str8 + " UNION " + str7;
                }
            }
            StringBuilder append = new StringBuilder().append("SELECT * FROM (").append(str8).append(")");
            if (str5 == null) {
                str8 = "";
            } else {
                str8 = " WHERE " + str5;
            }
            return append.append(str8).append(" ORDER BY ").append(unionOrderBy(str6)).toString();
        }

        public static String buildMediaItemSelectionSelection(String str, String str2, String str3, String str4) {
            d.a((Object) str3, "favCountSelection");
            d.a((Object) str2, "associateMediaTableName");
            d.a((Object) str, "groupMapTableName");
            d.a((Object) str4, "orderBy");
            return "SELECT " + str + "." + "_id" + " AS " + "_id" + SelectCountryActivity.SPLITTER + "song_id" + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_LOCAL_DATA_SOURCE + SelectCountryActivity.SPLITTER + "folder" + SelectCountryActivity.SPLITTER + "title" + SelectCountryActivity.SPLITTER + "artist" + SelectCountryActivity.SPLITTER + "album" + SelectCountryActivity.SPLITTER + "genre" + SelectCountryActivity.SPLITTER + "composer" + SelectCountryActivity.SPLITTER + "mime_type" + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_START_TIME + SelectCountryActivity.SPLITTER + "duration" + SelectCountryActivity.SPLITTER + "track" + SelectCountryActivity.SPLITTER + "year" + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_GRADE + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_BIT_RATE + SelectCountryActivity.SPLITTER + "sample_rate" + SelectCountryActivity.SPLITTER + "channels" + SelectCountryActivity.SPLITTER + "comment" + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_ERROR_STATUS + SelectCountryActivity.SPLITTER + "use_count" + SelectCountryActivity.SPLITTER + "added_time" + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_MODIFIED_TIME_STAMP + SelectCountryActivity.SPLITTER + "last_play_time" + SelectCountryActivity.SPLITTER + str3 + SelectCountryActivity.SPLITTER + MediaDBHelper.MEDIA_COLUMN_EXTRA + SelectCountryActivity.SPLITTER + "group_map_added_time" + SelectCountryActivity.SPLITTER + str4 + " FROM " + str2 + SelectCountryActivity.SPLITTER + str + " WHERE " + str2 + "." + "_id" + "=" + str + "." + "_id";
        }

        static String buildOnlineFavGroupSelection(String str, String str2) {
            return str2 == null ? "'0'" : "(SELECT COUNT(*) FROM " + str2 + " WHERE " + str + "." + "_id" + "=" + str2 + "." + "_id" + ")";
        }

        static boolean isCustomGroupExisted(SQLiteDatabase sQLiteDatabase, String str) {
            boolean z = false;
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type='table' and name=" + embraceWithSingleQuotationMarks(str), null);
            if (rawQuery != null) {
                if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                    z = true;
                }
                rawQuery.close();
            }
            return z;
        }

        static String unionOrderBy(String str) {
            if (m.a(str, MediaStorage.MEDIA_ORDER_BY_ALBUM_DESC) || m.a(str, "album_key")) {
                return str + SelectCountryActivity.SPLITTER + "track";
            }
            return str;
        }

        static String getOnlineFavTable(SQLiteDatabase sQLiteDatabase) {
            long g;
            try {
                g = b.g();
            } catch (Exception e) {
                e.printStackTrace();
                g = 0;
            }
            if (g > 0) {
                String buildOnlineFavGroupID = MediaStorage.buildOnlineFavGroupID();
                if (isCustomGroupExisted(sQLiteDatabase, buildOnlineFavGroupID)) {
                    return buildOnlineFavGroupID;
                }
            }
            return null;
        }
    }

    MediaDBHelper(Context context) {
        super(context, DB_NAME, null, SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_MEMORY);
        this.mContext = context;
        openDatabaseAsync();
    }

    private void openDatabaseAsync() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                MediaDBHelper.this.tryConnectDatabase();
            }
        });
        thread.setPriority(10);
        thread.start();
    }

    private SQLiteDatabase tryConnectDatabase() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (this.mContext) {
            if (this.mSQLiteDatabase == null) {
                this.mSQLiteDatabase = getWritableDatabase();
            }
            sQLiteDatabase = this.mSQLiteDatabase;
        }
        return sQLiteDatabase;
    }

    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        try {
            writableDatabase = super.getWritableDatabase();
        } catch (Exception e) {
            writableDatabase = null;
        }
        return writableDatabase;
    }

    private void createMediaTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE " + str + " (" + "_id" + " TEXT PRIMARY KEY NOT NULL," + "song_id" + " INTEGER NULL," + MEDIA_COLUMN_LOCAL_DATA_SOURCE + " TEXT DEFAULT NULL," + "folder" + " TEXT NULL," + MEDIA_COLUMN_SIZE + " INTEGER NULL," + "title" + " TEXT NULL," + "title_key" + " TEXT NULL," + "artist" + " TEXT NULL," + "artist_key" + " TEXT NULL," + "album" + " TEXT NULL," + "album_key" + " TEXT NULL," + "genre" + " TEXT NULL," + "genre_key" + " TEXT NULL," + "file_name_key" + " TEXT NULL," + "composer" + " TEXT NULL," + "mime_type" + " TEXT NULL," + MEDIA_COLUMN_START_TIME + " INTEGER DEFAULT 0 NULL," + "duration" + " INTEGER DEFAULT 0 NULL," + "track" + " INTEGER NULL," + "year" + " INTEGER NULL," + MEDIA_COLUMN_GRADE + " INTEGER NULL," + MEDIA_COLUMN_BIT_RATE + " INTEGER NULL," + "sample_rate" + " INTEGER NULL," + "channels" + " INTEGER NULL," + "comment" + " TEXT NULL," + MEDIA_COLUMN_ERROR_STATUS + " INTEGER DEFAULT 0 NULL," + "use_count" + " INTEGER DEFAULT 0 NULL," + "added_time" + " INTEGER NULL," + MEDIA_COLUMN_MODIFIED_TIME_STAMP + " INTEGER NULL," + "last_play_time" + " INTEGER NULL," + MEDIA_COLUMN_EXTRA + " TEXT NULL);");
    }

    private void createGroupTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("CREATE TABLE " + str + " (" + "name" + " TEXT NOT NULL," + GROUP_COLUMN_MAP_TABLE_NAME + " TEXT PRIMARY KEY NOT NULL," + GROUP_COLUMN_ADDED_TIMESTAMP + " INTEGER NOT NULL," + GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME + " TEXT DEFAULT NULL," + GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME + " TEXT DEFAULT NULL, " + GROUP_COLUMN_CUR_BASE_ORDERBY + " TEXT DEFAULT NULL);");
    }

    void createGroupMapTable(SQLiteDatabase sQLiteDatabase, String str) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE " + str + " (" + "sort_order" + " INTEGER PRIMARY KEY AUTOINCREMENT," + "group_map_added_time" + " INTEGER NOT NULL," + "_id" + " TEXT KEY NOT NULL);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createMediaTable(sQLiteDatabase, "media_local");
        createGroupTable(sQLiteDatabase, "group_");
        doInsertLocalCustomGroup(sQLiteDatabase, "all_local", MediaStorage.GROUP_ID_ALL_LOCAL);
        doInsertLocalCustomGroup(sQLiteDatabase, "fav_local", MediaStorage.GROUP_ID_FAV_LOCAL);
        doInsertLocalCustomGroup(sQLiteDatabase, "recently_add", MediaStorage.GROUP_ID_RECENTLY_ADD);
        doInsertLocalCustomGroup(sQLiteDatabase, "recently_play", MediaStorage.GROUP_ID_RECENTLY_PLAY);
        g.c("MediaDB", "onCreate");
        notifyUpdateDbVersion(-1, SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_MEMORY);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        g.b(TAG, "onUpgrade: From " + i + " to " + i2);
        notifyUpdateDbVersion(i, i2);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        g.b(TAG, "onDowngrade: From " + i + " to " + i2);
        notifyUpdateDbVersion(i, i2);
    }

    private void notifyUpdateDbVersion(int i, int i2) {
        this.mContext.sendBroadcast(new Intent(ACTION_UPDATE_DB_VERSION).putExtra(KEY_DB_VERSION_OLD, i).putExtra(KEY_DB_VERSION_NEW, i2));
    }

    void insertMediaItemToGroup(final String str, final ContentValues contentValues) {
        doWithTransaction(null, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    sQLiteDatabase.insert("media_local", null, contentValues);
                }
                List access$100 = MediaDBHelper.this.getCustomMediaIDs(sQLiteDatabase, str);
                String asString = contentValues.getAsString("_id");
                if (!access$100.contains(asString)) {
                    Long asLong = contentValues.getAsLong("song_id");
                    if (asLong == null || !m.a(MediaItem.genIDWithSongID(asLong), asString)) {
                        MediaDBHelper.this.associateLocalMediaTable(sQLiteDatabase, str);
                    } else {
                        sQLiteDatabase.insert(MediaDBHelper.this.associateOnlineMediaTable(sQLiteDatabase, str), null, contentValues);
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_id", asString);
                    contentValues.put("group_map_added_time", Long.valueOf(System.currentTimeMillis()));
                    sQLiteDatabase.insert(str, null, contentValues);
                }
                return null;
            }
        });
    }

    void insertMediaItemsToGroup(final String str, final ContentValues[] contentValuesArr) {
        doWithTransaction(null, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str = str;
                String str2 = null;
                String str3 = null;
                long currentTimeMillis = System.currentTimeMillis();
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    for (ContentValues insert : contentValuesArr) {
                        sQLiteDatabase.insert("media_local", null, insert);
                    }
                }
                List access$100 = MediaDBHelper.this.getCustomMediaIDs(sQLiteDatabase, str);
                ContentValues[] contentValuesArr = contentValuesArr;
                int length = contentValuesArr.length;
                int i = 0;
                while (i < length) {
                    String str4;
                    long j;
                    ContentValues contentValues = contentValuesArr[i];
                    String asString = contentValues.getAsString("_id");
                    if (access$100.contains(asString)) {
                        str4 = str2;
                        str2 = str3;
                        j = currentTimeMillis;
                    } else {
                        String access$200;
                        Object obj = contentValues.get("song_id");
                        obj = (obj == null || !contentValues.get("_id").equals(MediaItem.genIDWithSongID((Long) obj))) ? null : 1;
                        if (obj != null) {
                            if (str2 == null) {
                                access$200 = MediaDBHelper.this.associateOnlineMediaTable(sQLiteDatabase, str);
                            } else {
                                access$200 = str2;
                            }
                            sQLiteDatabase.insert(access$200, null, contentValues);
                            String str5 = str3;
                            str3 = access$200;
                            access$200 = str5;
                        } else if (str3 == null) {
                            access$200 = MediaDBHelper.this.associateLocalMediaTable(sQLiteDatabase, str);
                            str3 = str2;
                        } else {
                            access$200 = str3;
                            str3 = str2;
                        }
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("_id", asString);
                        long j2 = 1 + currentTimeMillis;
                        contentValues2.put("group_map_added_time", Long.valueOf(currentTimeMillis));
                        sQLiteDatabase.insert(str, null, contentValues2);
                        long j3 = j2;
                        str2 = access$200;
                        str4 = str3;
                        j = j3;
                    }
                    i++;
                    currentTimeMillis = j;
                    str3 = str2;
                    str2 = str4;
                }
                return null;
            }
        });
    }

    private List<String> getCustomMediaIDs(SQLiteDatabase sQLiteDatabase, String str) {
        List<String> arrayList = new ArrayList();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT _id FROM " + str, null);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                arrayList.add(rawQuery.getString(0));
            }
            rawQuery.close();
        }
        return arrayList;
    }

    private String associateOnlineMediaTable(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
        if (rawQuery == null || !rawQuery.moveToNext()) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw new IllegalArgumentException("do not have the table " + str);
        }
        String string = rawQuery.getString(1);
        if (string == null) {
            string = Builder.buildOnlineMediaTableName();
            createMediaTable(sQLiteDatabase, string);
            ContentValues contentValues = new ContentValues();
            contentValues.put(GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME, string);
            sQLiteDatabase.update("group_", contentValues, "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
        }
        rawQuery.close();
        return string;
    }

    private String associateLocalMediaTable(SQLiteDatabase sQLiteDatabase, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME, "media_local");
        sQLiteDatabase.update("group_", contentValues, "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
        return "media_local";
    }

    void deleteTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("DROP TABLE " + str);
    }

    void deleteGroup(final String str) {
        doWithTransaction(null, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String access$400 = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, str);
                if (access$400 != null) {
                    MediaDBHelper.this.deleteTable(sQLiteDatabase, access$400);
                }
                MediaDBHelper.this.deleteTable(sQLiteDatabase, str);
                sQLiteDatabase.delete("group_", "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
                return null;
            }
        });
    }

    void clearGroup(final String str) {
        doWithTransaction(tryConnectDatabase(), new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str = str;
                String access$400 = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, str);
                if (access$400 != null) {
                    sQLiteDatabase.execSQL("DELETE FROM " + access$400);
                }
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    sQLiteDatabase.execSQL("DELETE FROM media_local");
                }
                sQLiteDatabase.execSQL("DELETE FROM " + str);
                return null;
            }
        });
    }

    void deleteMediaItems(final String str, final String str2) {
        doWithTransaction(tryConnectDatabase(), new TransactionCallback<Object>() {
            public Object runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str = str;
                String access$400 = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, str);
                if (access$400 != null) {
                    sQLiteDatabase.delete(access$400, str2, null);
                }
                sQLiteDatabase.delete(str, str2, null);
                if (str.equals(MediaStorage.GROUP_ID_ALL_LOCAL)) {
                    sQLiteDatabase.delete("media_local", str2, null);
                }
                return null;
            }
        });
    }

    void updateGroup(String str, ContentValues contentValues) {
        tryConnectDatabase().update("group_", contentValues, "map_table_name=" + Builder.embraceWithSingleQuotationMarks(str), null);
    }

    void updateMediaItem(final String str, final ContentValues contentValues, final String str2) {
        doWithTransaction(null, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                Object obj = contentValues.get("song_id");
                int i = (obj == null || !contentValues.get("_id").equals(MediaItem.genIDWithSongID((Long) obj))) ? 0 : 1;
                if (i != 0) {
                    String str = str;
                    if (MediaStorage.GROUP_ID_FAV.equals(str)) {
                        str = MediaStorage.buildOnlineFavGroupID();
                    }
                    Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
                    if (rawQuery != null) {
                        if (rawQuery.moveToNext()) {
                            String string = rawQuery.getString(1);
                            rawQuery.close();
                            sQLiteDatabase.update(string, contentValues, str2, null);
                        } else {
                            rawQuery.close();
                        }
                    }
                } else {
                    sQLiteDatabase.update("media_local", contentValues, str2, null);
                }
                return null;
            }
        });
    }

    void exchangeSortOrder(final String str, final ContentValues[] contentValuesArr) {
        doWithTransaction(null, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                ContentValues contentValues = new ContentValues();
                for (ContentValues contentValues2 : contentValuesArr) {
                    String asString = contentValues2.getAsString("exchange_key_1");
                    String asString2 = contentValues2.getAsString("exchange_key_2");
                    long j = 0;
                    long j2 = 0;
                    Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildAddCustomGroupSelection(str, asString), null);
                    if (rawQuery != null) {
                        if (rawQuery.moveToNext()) {
                            j = rawQuery.getLong(0);
                            j2 = rawQuery.getLong(1);
                        }
                        rawQuery.close();
                        long j3 = 0;
                        long j4 = 0;
                        Cursor rawQuery2 = sQLiteDatabase.rawQuery(Builder.buildAddCustomGroupSelection(str, asString2), null);
                        if (rawQuery2 != null) {
                            if (rawQuery2.moveToNext()) {
                                j3 = rawQuery2.getLong(0);
                                j4 = rawQuery2.getLong(1);
                            }
                            rawQuery2.close();
                            contentValues.put("_id", asString2);
                            contentValues.put("group_map_added_time", Long.valueOf(j4));
                            sQLiteDatabase.update(str, contentValues, "sort_order=" + j, null);
                            contentValues.put("_id", asString);
                            contentValues.put("group_map_added_time", Long.valueOf(j2));
                            sQLiteDatabase.update(str, contentValues, "sort_order=" + j3, null);
                        }
                    }
                }
                MediaDBHelper.this.updateCurOrderBy(sQLiteDatabase, str, "sort_order");
                return null;
            }
        });
    }

    private void updateCurOrderBy(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        sQLiteDatabase.execSQL("UPDATE group_ SET base_orderby=" + Builder.embraceWithSingleQuotationMarks(str2) + " WHERE " + GROUP_COLUMN_MAP_TABLE_NAME + "=" + Builder.embraceWithSingleQuotationMarks(str));
    }

    Cursor queryMediaItem(final String str, final String str2, final String str3) {
        return (Cursor) doWithTransaction(null, new TransactionCallback<Cursor>() {
            public Cursor runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                String str = str2;
                if (str.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str == null ? "" : str + " AND ") + "album" + "=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_ALBUM_PREFIX.length())), str3), null);
                } else if (str.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str == null ? "" : str + " AND ") + "folder" + "=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_FOLDER_PREFIX.length())), str3), null);
                } else if (str.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str == null ? "" : str + " AND ") + "artist" + "=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_ARTIST_PREFIX.length())), str3), null);
                } else if (str.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str == null ? "" : str + " AND ") + "genre" + "=" + Builder.embraceWithSingleQuotationMarks(str.substring(MediaStorage.GROUP_ID_GENRE_PREFIX.length())), str3), null);
                } else if (str.equals(MediaStorage.GROUP_ID_RECENTLY_ADD_OLD)) {
                    return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal(str, str3), null);
                } else {
                    if (str.equals(MediaStorage.GROUP_ID_RECENTLY_PLAY_OLD)) {
                        return sQLiteDatabase.rawQuery(Builder.buildMediaItemSelectionFromMediaLocal((str == null ? "" : str + " AND ") + "last_play_time" + "<>'0'", str3), null);
                    } else if (str.equals(MediaStorage.GROUP_ID_FAV)) {
                        String str2 = str3 == null ? "sort_order" : str3;
                        if (str2.endsWith(" DESC")) {
                            str2 = str2.substring(0, str2.length() - " DESC".length());
                        }
                        r1 = Builder.buildMediaItemSelectionSelection(MediaStorage.GROUP_ID_FAV_LOCAL, "media_local", "'1'", str2);
                        r2 = Builder.getOnlineFavTable(sQLiteDatabase);
                        if (r2 != null) {
                            r3 = MediaDBHelper.this.getAssociateOnlineTableName(sQLiteDatabase, r2);
                            String buildMediaItemSelectionSelection = Builder.buildMediaItemSelectionSelection(r2, r3, "'1'", str2);
                            str2 = "SELECT * FROM (" + r1 + ")" + " WHERE " + "song_id" + " NOT IN " + "(" + "SELECT " + "song_id" + " FROM " + r3 + ")" + " OR " + "song_id" + " IS NULL";
                            r1 = buildMediaItemSelectionSelection;
                        } else {
                            str2 = r1;
                            r1 = null;
                        }
                        if (r1 != null) {
                            str2 = str2 + " UNION " + r1;
                        }
                        return sQLiteDatabase.rawQuery("SELECT * FROM (" + str2 + ")" + (str2 == null ? "" : " WHERE " + str2) + " ORDER BY " + Builder.unionOrderBy(str3), null);
                    } else {
                        Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
                        if (rawQuery != null) {
                            if (rawQuery.moveToNext()) {
                                r1 = rawQuery.getString(0);
                                r2 = rawQuery.getString(1);
                                String string = rawQuery.getString(2);
                                rawQuery.close();
                                r3 = Builder.buildMediaItemSelectionFromGroupMapSelection(str, r1, r2, Builder.getOnlineFavTable(sQLiteDatabase), str, str3);
                                Cursor rawQuery2 = sQLiteDatabase.rawQuery(r3, null);
                                if (m.a(str) && !m.a(string, str3)) {
                                    long j;
                                    Cursor rawQuery3 = sQLiteDatabase.rawQuery("SELECT MAX(sort_order) FROM " + str, null);
                                    if (rawQuery3 != null) {
                                        if (rawQuery3.moveToNext()) {
                                            j = rawQuery3.getLong(0);
                                        } else {
                                            j = 0;
                                        }
                                        rawQuery3.close();
                                    } else {
                                        j = 0;
                                    }
                                    sQLiteDatabase.execSQL("INSERT INTO " + str + " (" + "_id" + SelectCountryActivity.SPLITTER + "group_map_added_time" + ") " + " SELECT " + "_id" + SelectCountryActivity.SPLITTER + "group_map_added_time" + " FROM " + "(" + r3 + ")");
                                    if (j > 0) {
                                        sQLiteDatabase.execSQL("DELETE FROM " + str + " WHERE " + "sort_order" + "<=" + j);
                                    }
                                    MediaDBHelper.this.updateCurOrderBy(sQLiteDatabase, str, str3);
                                }
                                return rawQuery2;
                            }
                            rawQuery.close();
                        }
                        return null;
                    }
                }
            }
        });
    }

    void insertLocalCustomGroup(SQLiteDatabase sQLiteDatabase, final String str, final String str2) {
        doWithTransaction(sQLiteDatabase, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                MediaDBHelper.this.doInsertLocalCustomGroup(sQLiteDatabase, str, str2);
                return null;
            }
        });
    }

    private void doInsertLocalCustomGroup(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        createGroupMapTable(sQLiteDatabase, str2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put(GROUP_COLUMN_MAP_TABLE_NAME, str2);
        contentValues.put(GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME, "media_local");
        contentValues.put(GROUP_COLUMN_ADDED_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
        sQLiteDatabase.insert("group_", null, contentValues);
    }

    void insertMixCustomGroup(SQLiteDatabase sQLiteDatabase, final String str, final String str2) {
        doWithTransaction(sQLiteDatabase, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                MediaDBHelper.this.createGroupMapTable(sQLiteDatabase, str2);
                String buildOnlineMediaTableName = Builder.buildOnlineMediaTableName();
                MediaDBHelper.this.createMediaTable(sQLiteDatabase, buildOnlineMediaTableName);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_MAP_TABLE_NAME, str2);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ASSOCIATE_TABLE_LOCAL_NAME, "media_local");
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME, buildOnlineMediaTableName);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ADDED_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
                sQLiteDatabase.insert("group_", null, contentValues);
                return null;
            }
        });
    }

    void insertOnlineCustomGroup(SQLiteDatabase sQLiteDatabase, final String str, final String str2) {
        doWithTransaction(sQLiteDatabase, new TransactionCallback<Void>() {
            public Void runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                MediaDBHelper.this.createGroupMapTable(sQLiteDatabase, str2);
                String buildOnlineMediaTableName = Builder.buildOnlineMediaTableName();
                MediaDBHelper.this.createMediaTable(sQLiteDatabase, buildOnlineMediaTableName);
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_MAP_TABLE_NAME, str2);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ASSOCIATE_TABLE_ONLINE_NAME, buildOnlineMediaTableName);
                contentValues.put(MediaDBHelper.GROUP_COLUMN_ADDED_TIMESTAMP, Long.valueOf(System.currentTimeMillis()));
                sQLiteDatabase.insert("group_", null, contentValues);
                return null;
            }
        });
    }

    Cursor queryArtistGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildArtistGroupSelection(str), null);
    }

    Cursor queryFolderGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildFolderGroupSelection(str), null);
    }

    Cursor queryGenreGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildGenreGroupSelection(str), null);
    }

    Cursor queryAlbumGroup(String str) {
        return tryConnectDatabase().rawQuery(Builder.buildAlbumGroupSelection(str), null);
    }

    Cursor queryCustomLocalGroup() {
        return queryCustomGroup(Builder.buildCustomLocalGroupSelection());
    }

    Cursor queryCustomOnlineGroup() {
        return queryCustomGroup(Builder.buildCustomOnlineGroupSelection());
    }

    Cursor queryCustomMixGroup() {
        return queryCustomGroup(Builder.buildCustomMixGroupSelection());
    }

    Cursor queryCustomAllGroup() {
        return queryCustomGroup(Builder.buildCustomAllGroupSelection());
    }

    Cursor queryCustomGroup(final String str) {
        return (Cursor) doWithTransaction(null, new TransactionCallback<Cursor>() {
            public Cursor runInTransaction(SQLiteDatabase sQLiteDatabase) throws Exception {
                Cursor rawQuery = sQLiteDatabase.rawQuery(str, null);
                List<String> arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                if (rawQuery == null) {
                    return null;
                }
                while (rawQuery.moveToNext()) {
                    arrayList.add(rawQuery.getString(0));
                    String string = rawQuery.getString(1);
                    arrayList2.add(string);
                    sQLiteDatabase.execSQL(Builder.buildDeleteUnUsedItemFromCustomGroupSelection(string, rawQuery.getString(2), rawQuery.getString(3)));
                }
                rawQuery.close();
                if (arrayList.size() <= 0) {
                    return null;
                }
                Cursor rawQuery2 = sQLiteDatabase.rawQuery(Builder.buildCustomGroupMapCountSelection(arrayList2), null);
                if (rawQuery2 == null) {
                    return null;
                }
                if (rawQuery2.moveToNext()) {
                    MatrixCursor matrixCursor = new MatrixCursor(new String[]{"name", MediaDBHelper.GROUP_COLUMN_MAP_TABLE_NAME, "COUNT"});
                    int i = 0;
                    for (String str : arrayList) {
                        Object[] objArr = new Object[3];
                        objArr[0] = str;
                        objArr[1] = arrayList2.get(i);
                        int i2 = i + 1;
                        objArr[2] = Integer.valueOf(rawQuery2.getInt(i));
                        matrixCursor.addRow(objArr);
                        i = i2;
                    }
                    rawQuery2.close();
                    return matrixCursor;
                }
                rawQuery2.close();
                return null;
            }
        });
    }

    private <Result> Result doWithTransaction(SQLiteDatabase sQLiteDatabase, TransactionCallback<Result> transactionCallback) {
        Object tryConnectDatabase;
        Result result = null;
        if (sQLiteDatabase == null) {
            tryConnectDatabase = tryConnectDatabase();
        }
        d.a(tryConnectDatabase, "database");
        if (tryConnectDatabase != null) {
            tryConnectDatabase.beginTransaction();
            try {
                result = transactionCallback.runInTransaction(tryConnectDatabase);
                tryConnectDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                tryConnectDatabase.endTransaction();
            }
        }
        return result;
    }

    private String getAssociateOnlineTableName(SQLiteDatabase sQLiteDatabase, String str) {
        String str2 = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery(Builder.buildCustomGroupAssociateMediaTableNameSelection(str), null);
        if (rawQuery != null) {
            if (rawQuery.moveToNext()) {
                str2 = rawQuery.getString(1);
            }
            rawQuery.close();
        }
        return str2;
    }
}
