package com.sds.android.ttpod.framework.storage.environment;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Pair;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.Action;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* AccessHelper */
final class a {
    private static a a;
    private static Context b = null;

    /* AccessHelper */
    static class a extends BroadcastReceiver {
        private List<Pair<c, com.sds.android.ttpod.framework.storage.environment.b.a>> a = new ArrayList();

        a() {
        }

        void a(c cVar, com.sds.android.ttpod.framework.storage.environment.b.a aVar) {
            this.a.add(new Pair(cVar, aVar));
        }

        void b(c cVar, com.sds.android.ttpod.framework.storage.environment.b.a aVar) {
            for (Pair pair : this.a) {
                if (pair.first == cVar && pair.second == aVar) {
                    this.a.remove(pair);
                    return;
                }
            }
        }

        boolean a() {
            return this.a.isEmpty();
        }

        public void onReceive(Context context, Intent intent) {
            try {
                c valueOf = c.valueOf(intent.getStringExtra("preferences_id"));
                for (Pair pair : this.a) {
                    if (pair.first == valueOf) {
                        ((com.sds.android.ttpod.framework.storage.environment.b.a) pair.second).a(valueOf);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        static IntentFilter b() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.PREFERENCES_CHANGED);
            return intentFilter;
        }
    }

    static void a(Context context) {
        b = context;
    }

    private static Uri a(String str, String str2) {
        return Uri.parse(str + str2);
    }

    private static String a() {
        return a.STRING.name();
    }

    private static String b() {
        return a.SET.name();
    }

    static boolean a(String str, String str2, Boolean bool) {
        try {
            Cursor query = b.getContentResolver().query(a(str, str2), null, a(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    bool = Boolean.valueOf(query.getString(0));
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool.booleanValue();
    }

    static void b(String str, String str2, Boolean bool) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, bool.toString());
        try {
            b.getContentResolver().update(a(str, str2), contentValues, a(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String a(String str, String str2, String str3) {
        try {
            Cursor query = b.getContentResolver().query(a(str, str2), null, a(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    str3 = query.getString(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    static void b(String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, str3);
        try {
            b.getContentResolver().update(a(str, str2), contentValues, a(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int a(String str, String str2, int i) {
        try {
            Cursor query = b.getContentResolver().query(a(str, str2), null, a(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    i = query.getInt(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    static void a(String str, String str2, Integer num) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, num.toString());
        try {
            b.getContentResolver().update(a(str, str2), contentValues, a(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static long a(String str, String str2, long j) {
        try {
            Cursor query = b.getContentResolver().query(a(str, str2), null, a(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    j = query.getLong(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    static void a(String str, String str2, Long l) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, l.toString());
        try {
            b.getContentResolver().update(a(str, str2), contentValues, a(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void a(String str, String str2, Float f) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, f.toString());
        try {
            b.getContentResolver().update(a(str, str2), contentValues, a(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static float a(String str, String str2, float f) {
        try {
            Cursor query = b.getContentResolver().query(a(str, str2), null, a(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    f = query.getFloat(0);
                }
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    static Set<String> a(String str, String str2, Set<String> set) {
        Exception e;
        try {
            Cursor query = b.getContentResolver().query(a(str, str2), null, b(), null, null);
            if (query != null) {
                if (query.moveToNext()) {
                    int columnCount = query.getColumnCount();
                    Set<String> hashSet = new HashSet(columnCount);
                    int i = 0;
                    while (i < columnCount) {
                        try {
                            hashSet.add(query.getString(i));
                            i++;
                        } catch (Exception e2) {
                            e = e2;
                            set = hashSet;
                        }
                    }
                    set = hashSet;
                }
                query.close();
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return set;
        }
        return set;
    }

    static void b(String str, String str2, Set<String> set) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(str2, a((Set) set));
        try {
            b.getContentResolver().update(a(str, str2), contentValues, b(), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String a(Set<String> set) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : set) {
            stringBuilder.append(append).append("=_=");
        }
        String append2 = stringBuilder.toString();
        if (append2.endsWith("=_=")) {
            return append2.substring(0, append2.length() - "=_=".length());
        }
        return append2;
    }

    static Set<String> a(String str) {
        Set<String> hashSet = new HashSet();
        if (!(str == null || m.a(str))) {
            for (Object add : str.split("=_=")) {
                hashSet.add(add);
            }
        }
        return hashSet;
    }

    static synchronized void a(Context context, c cVar, com.sds.android.ttpod.framework.storage.environment.b.a aVar) {
        synchronized (a.class) {
            if (a == null) {
                a = new a();
                context.registerReceiver(a, a.b());
            }
            a.a(cVar, aVar);
        }
    }

    static synchronized void b(Context context, c cVar, com.sds.android.ttpod.framework.storage.environment.b.a aVar) {
        synchronized (a.class) {
            if (a != null) {
                a.b(cVar, aVar);
                if (a.a()) {
                    try {
                        context.unregisterReceiver(a);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    a = null;
                }
            }
        }
    }
}
