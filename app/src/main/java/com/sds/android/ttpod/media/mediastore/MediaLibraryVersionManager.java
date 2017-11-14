package com.sds.android.ttpod.media.mediastore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.SparseArrayCompat;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.media.mediastore.old.MediaDatabaseHelper;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Medias;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class MediaLibraryVersionManager {
    private static final String TAG = "MediaLibraryVersionManager";
    static final int VERSION_OLD_MEDIA_DB = -1;
    private static MediaLibraryVersionManager mInstance;
    private int mNewVersion = 0;
    private int mOldVersion = 0;

    public static MediaLibraryVersionManager instance() {
        if (mInstance == null) {
            mInstance = new MediaLibraryVersionManager();
        }
        return mInstance;
    }

    private MediaLibraryVersionManager() {
    }

    public void setVersion(int i, int i2) {
        this.mOldVersion = i;
        this.mNewVersion = i2;
    }

    public void doCompact(Context context) {
        doVersionCompact(context);
        this.mOldVersion = this.mNewVersion;
    }

    public boolean isCompacted(Context context) {
        if (this.mOldVersion == 0 || this.mOldVersion == this.mNewVersion) {
            return true;
        }
        if (this.mOldVersion != -1 || context.getDatabasePath(MediaDatabaseHelper.DBTABASE_NAME).exists()) {
            return false;
        }
        this.mNewVersion = this.mOldVersion;
        return false;
    }

    private void doVersionCompact(Context context) {
        g.c(TAG, "OldVersion:" + this.mOldVersion + " NewVersion:" + this.mNewVersion);
        if (this.mOldVersion < SecExceptionCode.SEC_ERROR_STA_KEY_ENC) {
            doUpdate2Version700(context);
        }
        if (this.mOldVersion < SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM) {
            doUpdate2Version701(context);
        }
        if (this.mOldVersion < SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_MEMORY) {
            doUpdate2Version702(context);
        }
    }

    private void doUpdate2Version700(Context context) {
        MediaDatabaseHelper mediaDatabaseHelper;
        Exception e;
        Throwable th;
        try {
            mediaDatabaseHelper = new MediaDatabaseHelper(context);
            try {
                SQLiteDatabase readableDatabase = mediaDatabaseHelper.getReadableDatabase();
                if (readableDatabase != null) {
                    processLocalMedia(context, readableDatabase);
                    processLocalPlaylist(context, readableDatabase);
                    readableDatabase.close();
                }
                if (mediaDatabaseHelper != null) {
                    mediaDatabaseHelper.close();
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    if (mediaDatabaseHelper != null) {
                        mediaDatabaseHelper.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (mediaDatabaseHelper != null) {
                        mediaDatabaseHelper.close();
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            mediaDatabaseHelper = null;
            e.printStackTrace();
            if (mediaDatabaseHelper != null) {
                mediaDatabaseHelper.close();
            }
        } catch (Throwable th3) {
            th = th3;
            mediaDatabaseHelper = null;
            if (mediaDatabaseHelper != null) {
                mediaDatabaseHelper.close();
            }
            throw th;
        }
    }

    private void doUpdate2Version701(Context context) {
        Collection queryMediaItemList = MediaStorage.queryMediaItemList(context, MediaStorage.GROUP_ID_RECENTLY_ADD_OLD, MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC);
        MediaStorage.insertGroup(context, "recently_add", MediaStorage.GROUP_ID_RECENTLY_ADD, GroupType.CUSTOM_LOCAL);
        if (queryMediaItemList.size() > 0) {
            MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_RECENTLY_ADD, queryMediaItemList);
        }
        queryMediaItemList = MediaStorage.queryMediaItemList(context, MediaStorage.GROUP_ID_RECENTLY_PLAY_OLD, MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC);
        MediaStorage.insertGroup(context, "recently_play", MediaStorage.GROUP_ID_RECENTLY_PLAY, GroupType.CUSTOM_LOCAL);
        if (queryMediaItemList.size() > 0) {
            MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_RECENTLY_PLAY, queryMediaItemList);
        }
    }

    private void doUpdate2Version702(Context context) {
        List queryMediaIDs = MediaStorage.queryMediaIDs(context, MediaStorage.GROUP_ID_RECENTLY_PLAY, MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC);
        if (queryMediaIDs.size() > 100) {
            for (int i = 100; i < queryMediaIDs.size(); i++) {
                MediaStorage.deleteMediaItem(context, MediaStorage.GROUP_ID_RECENTLY_PLAY, (String) queryMediaIDs.get(i));
            }
        }
    }

    private MediaItem createOnlineMediaItemFromCursor(Cursor cursor) {
        return new MediaItem(null, Long.valueOf(cursor.getLong(cursor.getColumnIndex(MediasColumns.SONG_ID))), null, null, cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex("artist")), null, null, null, null, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(0), Integer.valueOf(0), Long.valueOf(cursor.getLong(cursor.getColumnIndex("date_added"))), Long.valueOf(cursor.getLong(cursor.getColumnIndex("date_modified"))), null, true, cursor.getString(cursor.getColumnIndex("_data")), MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
    }

    private MediaItem createMediaItemFromCursor(Cursor cursor) {
        try {
            String string = cursor.getString(16);
            if (string.startsWith("/")) {
                int i;
                boolean z;
                int indexOf = string.indexOf(124);
                if (indexOf > 0) {
                    String[] split = string.substring(indexOf + 1).split("-");
                    if (split.length != 2) {
                        return null;
                    }
                    int intValue = Integer.valueOf(split[0]).intValue();
                    if (intValue == 0) {
                        intValue = 1;
                    }
                    string = string.substring(0, indexOf);
                    i = intValue;
                } else {
                    i = 0;
                }
                String l = e.l(string);
                String string2 = cursor.getString(17);
                String string3 = cursor.getString(18);
                String string4 = cursor.getString(19);
                String string5 = cursor.getString(20);
                String string6 = cursor.getString(21);
                String string7 = cursor.getString(23);
                Integer valueOf = Integer.valueOf(i);
                Integer valueOf2 = Integer.valueOf(cursor.getInt(11));
                Integer valueOf3 = Integer.valueOf(cursor.getInt(9));
                Integer valueOf4 = Integer.valueOf(0);
                Integer valueOf5 = Integer.valueOf(0);
                Integer valueOf6 = Integer.valueOf(cursor.getInt(7));
                Integer valueOf7 = Integer.valueOf(cursor.getInt(8));
                Integer valueOf8 = Integer.valueOf(cursor.getInt(15));
                String string8 = cursor.getString(22);
                Integer valueOf9 = Integer.valueOf(cursor.getInt(14));
                Integer valueOf10 = Integer.valueOf(cursor.getInt(12));
                Long valueOf11 = Long.valueOf(cursor.getLong(5));
                Long valueOf12 = Long.valueOf(cursor.getLong(4));
                Long valueOf13 = Long.valueOf(cursor.getLong(13));
                if (cursor.getInt(6) > 0) {
                    z = true;
                } else {
                    z = false;
                }
                return new MediaItem(null, null, string, l, string2, string3, string4, string5, string6, string7, valueOf, valueOf2, valueOf3, valueOf4, valueOf5, valueOf6, valueOf7, valueOf8, string8, valueOf9, valueOf10, valueOf11, valueOf12, valueOf13, z, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void processLocalMedia(Context context, SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(MediaDatabaseHelper.DATABASE_VIEW_MEDIAS, Medias.CURSOR_COLS, null, null, null, null, null);
        if (query != null) {
            int count = query.getCount();
            Collection arrayList = new ArrayList(count);
            Collection arrayList2 = new ArrayList(count);
            while (query.moveToNext()) {
                MediaItem createMediaItemFromCursor = createMediaItemFromCursor(query);
                if (createMediaItemFromCursor != null) {
                    arrayList.add(createMediaItemFromCursor);
                    if (createMediaItemFromCursor.getFav()) {
                        arrayList2.add(createMediaItemFromCursor);
                    }
                }
            }
            query.close();
            if (!arrayList.isEmpty()) {
                MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_ALL_LOCAL, arrayList);
            }
            if (!arrayList2.isEmpty()) {
                MediaStorage.insertMediaItems(context, MediaStorage.GROUP_ID_FAV_LOCAL, arrayList2);
            }
        }
    }

    private void processLocalPlaylist(Context context, SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query("playlists", new String[]{"_id", "name"}, null, null, null, null, null);
        if (query != null) {
            SparseArrayCompat sparseArrayCompat = new SparseArrayCompat(query.getCount());
            while (query.moveToNext()) {
                String buildGroupID = MediaStorage.buildGroupID();
                sparseArrayCompat.append(query.getInt(0), buildGroupID);
                MediaStorage.insertGroup(context, query.getString(1), buildGroupID, GroupType.CUSTOM_LOCAL);
            }
            query.close();
            int size = sparseArrayCompat.size();
            Object obj = new String[Medias.CURSOR_COLS.length];
            System.arraycopy(Medias.CURSOR_COLS, 0, obj, 0, obj.length);
            obj[0] = "media_id as _id";
            String str = "playlists_map inner join media_info on media_id=media_info._id";
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArrayCompat.keyAt(i);
                processLocalPlaylistMember(context, sQLiteDatabase, str, obj, (long) keyAt, (String) sparseArrayCompat.get(keyAt));
            }
        }
    }

    private void processLocalPlaylistMember(Context context, SQLiteDatabase sQLiteDatabase, String str, String[] strArr, long j, String str2) {
        Cursor query = sQLiteDatabase.query(str, strArr, "playlist_id=" + j, null, null, null, null);
        if (query != null) {
            Collection arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(createMediaItemFromCursor(query));
            }
            query.close();
            if (!arrayList.isEmpty()) {
                MediaStorage.insertMediaItems(context, str2, arrayList);
            }
        }
    }

    private void processOnlineFavorite(Context context, SQLiteDatabase sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        Cursor query = sQLiteDatabase2.query(MediaDatabaseHelper.DATABASE_TABLE_ONLINE_MEDIAS, new String[]{MediasColumns.SONG_ID, "title", "artist", "_data", "date_added", "date_modified"}, null, null, null, null, null);
        if (query != null) {
            Collection arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                arrayList.add(createOnlineMediaItemFromCursor(query));
            }
            query.close();
            if (!arrayList.isEmpty()) {
                MediaStorage.insertMediaItems(context, MediaStorage.buildOnlineFavGroupID(), arrayList);
            }
        }
    }
}
