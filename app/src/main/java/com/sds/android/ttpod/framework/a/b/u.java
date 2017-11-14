package com.sds.android.ttpod.framework.a.b;

import com.sds.android.ttpod.media.mediastore.MediaItem;

/* SearchStatistic */
public class u {
    private static String a;
    private static String b;
    private static String c;
    private static String d;

    public static String a() {
        return d;
    }

    public static void a(String str) {
        d = str;
    }

    public static String b() {
        return c;
    }

    public static void b(String str) {
        c = str;
    }

    public static void c(String str) {
        a = str;
    }

    public static String c() {
        return b;
    }

    public static void d(String str) {
        b = str;
    }

    public static void a(Integer num, String str) {
        a(num, str, b);
    }

    public static void a(Integer num, String str, String str2) {
        long j;
        if (num.intValue() == 1) {
            j = 0;
        } else if (num.intValue() == -1) {
            j = 2;
        } else {
            j = 1;
        }
        w.a("search", "click", str, j, str2, c);
    }

    public static void e(String str) {
        a(Integer.valueOf(1), str);
    }

    public static void a(Integer num) {
        e(a + "-album" + "");
    }

    public static void a(MediaItem mediaItem) {
        long j;
        String str = "search";
        String str2 = "click";
        String str3 = a + "-third-show";
        if (mediaItem.hasCopyright()) {
            j = 0;
        } else {
            j = 1;
        }
        w.a(str, str2, str3, j, 0);
    }

    public static void b(MediaItem mediaItem) {
        long j;
        String str = "search";
        String str2 = "click";
        String str3 = a + "-third-click";
        if (mediaItem.hasCopyright()) {
            j = 0;
        } else {
            j = 1;
        }
        w.a(str, str2, str3, j, 0);
    }

    public static void d() {
        w.a("search", "recognizer", "recognizer-result");
    }

    public static void e() {
        w.a("search", "recognizer", "recognizer-again");
    }

    public static void f() {
        w.a("search", "recognizer", "recognizer-button");
    }

    public static void a(boolean z) {
        w.a("search", "collect", p.b(), (long) (z ? 0 : 1), (long) p.f(), b, c);
    }
}
