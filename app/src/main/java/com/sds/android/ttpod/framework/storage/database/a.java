package com.sds.android.ttpod.framework.storage.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Environment;
import com.sds.android.sdk.lib.c.c;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.modules.search.SearchContentProvider;
import com.sds.android.ttpod.framework.modules.search.SearchMediaLinkInfo;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Playlists.Members;
import java.io.File;
import java.util.List;

/* SearchSqliteDb */
public class a {
    private static c a;
    private static final String[] b = new String[]{Members.MEDIA_ID, "lyric_path", "lyric_search_time", "artist", "artist_search_time"};

    public static void a(Context context) {
        if (a == null) {
            File file = new File(a());
            b.a("Search", "init DB , file path = " + file.getAbsolutePath() + ", exsit = " + file.exists() + " can read = " + file.canRead() + ", can write = " + file.canWrite() + ", is file = " + file.isFile());
            a = new c(context, "search.db", 16777217, new com.sds.android.sdk.lib.c.c.a() {
                public void a(int i, int i2) {
                }
            });
            a.a(SearchMediaLinkInfo.class);
            a.a();
            b.a("Search", "init DB end , file path = " + file.getAbsolutePath() + ", exsit = " + file.exists() + " can read = " + file.canRead() + ", can write = " + file.canWrite() + ", is file = " + file.isFile());
        }
    }

    private static String a() {
        return Environment.getDataDirectory().getAbsolutePath() + File.separator + "data" + File.separator + EnvironmentUtils.a() + File.separator + "databases" + File.separator + "search.db";
    }

    public static void a(SearchMediaLinkInfo searchMediaLinkInfo) {
        a.a((Object) searchMediaLinkInfo);
    }

    public static void a(ContentResolver contentResolver, SearchMediaLinkInfo searchMediaLinkInfo) {
        contentResolver.insert(SearchContentProvider.a, c(searchMediaLinkInfo));
    }

    public static SearchMediaLinkInfo a(String str) {
        Object searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(str);
        List a = a.a(searchMediaLinkInfo, false);
        return (a == null || a.isEmpty()) ? null : (SearchMediaLinkInfo) a.get(0);
    }

    public static SearchMediaLinkInfo a(ContentResolver contentResolver, String str) {
        return a(contentResolver.query(SearchContentProvider.a, null, str, null, null));
    }

    public static void a(SearchMediaLinkInfo searchMediaLinkInfo, String str) {
        Object searchMediaLinkInfo2 = new SearchMediaLinkInfo();
        searchMediaLinkInfo2.setMediaId(str);
        a.a((Object) searchMediaLinkInfo, searchMediaLinkInfo2);
    }

    public static void a(ContentResolver contentResolver, SearchMediaLinkInfo searchMediaLinkInfo, String str) {
        contentResolver.update(SearchContentProvider.a, c(searchMediaLinkInfo), str, null);
    }

    public static Cursor b(SearchMediaLinkInfo searchMediaLinkInfo) {
        if (searchMediaLinkInfo == null) {
            return null;
        }
        Cursor matrixCursor = new MatrixCursor(b);
        matrixCursor.addRow(new Object[]{searchMediaLinkInfo.getMediaId(), searchMediaLinkInfo.getLyricPath(), searchMediaLinkInfo.getLyricSearchTime(), searchMediaLinkInfo.getArtist(), searchMediaLinkInfo.getArtistSearchTime()});
        return matrixCursor;
    }

    private static SearchMediaLinkInfo a(Cursor cursor) {
        if (cursor == null || !cursor.moveToFirst()) {
            return null;
        }
        SearchMediaLinkInfo searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(cursor.getString(cursor.getColumnIndex(Members.MEDIA_ID)));
        searchMediaLinkInfo.setLyricPath(cursor.getString(cursor.getColumnIndex("lyric_path")));
        searchMediaLinkInfo.setLyricSearchTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("lyric_search_time"))));
        searchMediaLinkInfo.setArtist(cursor.getString(cursor.getColumnIndex("artist")));
        searchMediaLinkInfo.setArtistSearchTime(Long.valueOf(cursor.getLong(cursor.getColumnIndex("artist_search_time"))));
        cursor.close();
        return searchMediaLinkInfo;
    }

    public static ContentValues c(SearchMediaLinkInfo searchMediaLinkInfo) {
        if (searchMediaLinkInfo == null) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Members.MEDIA_ID, searchMediaLinkInfo.getMediaId());
        contentValues.put("lyric_path", searchMediaLinkInfo.getLyricPath());
        contentValues.put("lyric_search_time", searchMediaLinkInfo.getLyricSearchTime());
        contentValues.put("artist", searchMediaLinkInfo.getArtist());
        contentValues.put("artist_search_time", searchMediaLinkInfo.getArtistSearchTime());
        return contentValues;
    }

    public static SearchMediaLinkInfo a(ContentValues contentValues) {
        if (contentValues == null) {
            return null;
        }
        SearchMediaLinkInfo searchMediaLinkInfo = new SearchMediaLinkInfo();
        searchMediaLinkInfo.setMediaId(contentValues.getAsString(Members.MEDIA_ID));
        searchMediaLinkInfo.setLyricPath(contentValues.getAsString("lyric_path"));
        searchMediaLinkInfo.setLyricSearchTime(contentValues.getAsLong("lyric_search_time"));
        searchMediaLinkInfo.setArtist(contentValues.getAsString("artist"));
        searchMediaLinkInfo.setArtistSearchTime(contentValues.getAsLong("artist_search_time"));
        return searchMediaLinkInfo;
    }
}
