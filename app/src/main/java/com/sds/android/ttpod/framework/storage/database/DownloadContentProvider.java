package com.sds.android.ttpod.framework.storage.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.util.ArrayList;
import java.util.List;

public class DownloadContentProvider extends ContentProvider {
    public static final Uri a = Uri.parse(c + "/");
    private static final String b = DownloadContentProvider.class.getName();
    private static final String c = ("content://" + b);
    private static final String d = DownloadTaskInfo.class.getSimpleName();

    public boolean onCreate() {
        b.a("DownloadContentProvider", "onCreate");
        b.a(getContext());
        b.a("DownloadContentProvider", "onCreate end");
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            List arrayList = new ArrayList();
            int indexOf = str.indexOf("=");
            String substring = str.substring(0, indexOf);
            if (substring.equals("save_path")) {
                arrayList = b.b(new DownloadTaskInfo(str.substring(indexOf + 1)));
            } else if (substring.equals("info_type")) {
                arrayList = b.b(new DownloadTaskInfo(Integer.valueOf(str.substring(indexOf + 1))));
            }
            return b.a(arrayList);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return uri;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        try {
            b.c(new DownloadTaskInfo(str));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 1;
    }
}
