package com.sds.android.ttpod.framework.storage.environment;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.l;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.Action;
import java.util.Set;

public final class EnvironmentContentProvider extends ContentProvider {
    static final String a = (d + "/" + b.class.getSimpleName() + "/");
    static final String b = (d + "/" + d.class.getSimpleName() + "/");
    private static final String c = EnvironmentContentProvider.class.getName();
    private static final String d = ("content://" + c);
    private SharedPreferences e;

    enum a {
        STRING,
        SET,
        MAP
    }

    public boolean onCreate() {
        this.e = getContext().getSharedPreferences("preference", 0);
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        d(uri);
        if (a(uri)) {
            return a(uri, str);
        }
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        d(uri);
        if (a(uri)) {
            return c(uri);
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        d(uri);
        if (a(uri)) {
            return a(uri, contentValues, str);
        }
        return 0;
    }

    private boolean a(Uri uri) {
        return uri.toString().startsWith(a);
    }

    private String b(Uri uri) {
        d(uri);
        return uri.toString().substring(a(uri) ? a.length() : b.length());
    }

    private Cursor a(Uri uri, String str) {
        String b = b(uri);
        if (this.e.contains(b)) {
            if (m.a(a.STRING.name(), str)) {
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{b});
                matrixCursor.addRow(new Object[]{this.e.getString(b, "")});
                return matrixCursor;
            } else if (m.a(a.SET.name(), str)) {
                Set<String> a = a.a(this.e.getString(b, null));
                String[] strArr = new String[a.size()];
                Object[] objArr = new Object[a.size()];
                int i = 0;
                for (String str2 : a) {
                    strArr[i] = str2;
                    int i2 = i + 1;
                    objArr[i] = str2;
                    i = i2;
                }
                Cursor matrixCursor2 = new MatrixCursor(strArr);
                matrixCursor2.addRow(objArr);
                return matrixCursor2;
            }
        }
        return null;
    }

    private int c(Uri uri) {
        l.a(this.e.edit().remove(b(uri)));
        return 0;
    }

    private int a(Uri uri, ContentValues contentValues, String str) {
        String b = b(uri);
        if (m.a(a.STRING.name(), str)) {
            l.a(this.e.edit().putString(b, contentValues.getAsString(b)));
        } else if (m.a(a.SET.name(), str)) {
            l.a(this.e.edit().putString(b, contentValues.getAsString(b)));
        }
        try {
            if (b.contains("PREFIX")) {
                b = b.substring(0, b.indexOf("PREFIX") + "PREFIX".length());
            }
            if (c.valueOf(b).isNotifyChanged()) {
                getContext().sendBroadcast(new Intent(Action.PREFERENCES_CHANGED).putExtra("preferences_id", b));
            }
        } catch (Exception e) {
            g.b("EnvironmentContentProvider", "key:" + b + " not existed!");
        }
        return 0;
    }

    private void d(Uri uri) {
        if (com.sds.android.sdk.lib.util.EnvironmentUtils.a.j()) {
            String uri2 = uri.toString();
            if (!uri2.startsWith(a) && !uri2.startsWith(b)) {
                throw new UnsupportedOperationException(uri2 + ": Type not be supported!");
            }
        }
    }
}
