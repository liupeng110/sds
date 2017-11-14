package com.sds.android.ttpod.framework.modules.search;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.sds.android.ttpod.framework.storage.database.a;

public class SearchContentProvider extends ContentProvider {
    public static final Uri a = Uri.parse(c + "/" + d + "/");
    private static final String b = SearchContentProvider.class.getName();
    private static final String c = ("content://" + b);
    private static final String d = SearchMediaLinkInfo.class.getSimpleName();

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return a.b(a.a(str));
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        a.a(a.a(contentValues));
        return uri;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SearchMediaLinkInfo a = a.a(contentValues);
        a.a(a, a.getMediaId());
        return 1;
    }
}
