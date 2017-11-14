package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.lib.e.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.storage.environment.b;
import java.util.HashMap;

/* ListStatistic */
public class j {
    private static int a = -1;
    private static String b = "";
    private static String c = "";

    public static int a() {
        return a;
    }

    public static void a(int i) {
        a = i;
    }

    public static String b() {
        return b;
    }

    public static void a(String str) {
        b = str;
    }

    public static String c() {
        return c;
    }

    public static void b(String str) {
        c = str;
    }

    public static void a(int i, String str, long j, long j2, int i2, String str2) {
        a(i, str, j2 == j ? 1 : 0, j2, i2, str2);
        g.a("ListStatics", "listType:" + i + " listId:" + str + " startPlaySongId:" + j + " songId:" + j2 + " uuid:" + str2);
    }

    public static void a(int i, String str, long j, int i2) {
        a(i, str, 2, j, i2, c);
    }

    public static void a(long j, int i, boolean z) {
        if (z) {
            a(a, b, 3, j, i, c);
        }
    }

    private static void a(int i, String str, int i2, long j, int i3, String str2) {
        String aG = b.aG();
        if (i == -1 || m.a(str) || m.a(aG) || j < 0 || i3 < 0 || m.a(str2)) {
            g.a("ListStatistic", "error listType=" + i + ",listId=" + str + ",clientId=" + aG + ",off=" + i3 + ",uuid=" + str2);
            return;
        }
        a(i, str, i2, j, i3, aG, c.d(), str2);
    }

    private static void a(int i, String str, int i2, long j, int i3, String str2, int i4, String str3) {
        if (i3 > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("l", Integer.valueOf(i));
            hashMap.put("lid", str);
            hashMap.put("a", Integer.valueOf(i2));
            hashMap.put("sid", Long.valueOf(j));
            hashMap.put("off", Integer.valueOf(i3));
            hashMap.put("client_id", str2);
            hashMap.put("net", Integer.valueOf(i4));
            hashMap.put("uuid", str3);
            hashMap.put("f", EnvironmentUtils.b.d());
            a(hashMap);
        }
    }

    private static void a(final HashMap<String, Object> hashMap) {
        a.a(new Runnable() {
            public void run() {
                g.a("ListStatistic", hashMap.toString());
                com.sds.android.sdk.lib.a.a.a("http://0.0.0.0", null, hashMap);
            }
        });
    }
}
