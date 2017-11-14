package com.sds.android.ttpod.framework.a.b;

import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.tencent.open.SocialConstants;

/* ErrorStatistic */
public class g {
    public static void a(String str, String str2) {
        new SSystemEvent("SYS_EXCEPTION", str).append(Downloads.COLUMN_URI, str2).post();
    }

    public static void a(String str) {
        w.a("error", "lyric", str);
    }

    public static void b(String str) {
        w.a("error", SocialConstants.PARAM_AVATAR_URI, str);
    }

    public static void c(String str) {
        w.a("error", "online", str);
    }

    public static void d(String str) {
        w.a("error", "ad_sdk", str);
    }

    public static void e(String str) {
        w.a("error", "user", str);
    }

    public static void f(String str) {
        w.a("error", "update", str);
    }

    public static void g(String str) {
        w.a("error", "music_circle", str);
        new SSystemEvent("SYS_EXCEPTION", "music_circle").append(Downloads.COLUMN_URI, str).post();
    }

    public static void a(long j) {
        w.a("error", "not_url", "song", j);
    }

    public static void b(long j) {
        w.a("error", "not_url", "download", j);
    }

    public static void a() {
        w.a("error", "crash", "error");
    }

    public static void a(int i) {
        w.a("error", "song", NewUser.LOCAL_LOGIN, (long) i);
    }

    public static void a(String str, int i, String str2) {
        w.a("error", "song", str, (long) i, 0, str2, null);
    }

    public static void a(String str, long j, String str2) {
        w.a("error", "song", str, -12, j, str2, null);
    }
}
