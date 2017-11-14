package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.core.statistic.KVStatisticEvent.ValueOperateMode;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.m;
import com.sina.weibo.sdk.api.CmdObject;
import java.util.UUID;

/* FindSongNewStatistic */
public class i {
    private static String a;
    private static long b;
    private static String c;

    public static void a(String str) {
        w.a(349, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void b(String str) {
        w.a(350, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void c(String str) {
        w.a(351, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void d(String str) {
        w.a(354, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void e(String str) {
        w.a(355, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void f(String str) {
        w.a(356, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void g(String str) {
        w.a(357, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void h(String str) {
        w.a(358, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void a() {
        a = UUID.randomUUID().toString();
        j(CmdObject.CMD_HOME);
    }

    public static void a(long j, String str) {
        b = j;
        c = str;
    }

    public static void b() {
        w.a("find_music", "listen", "home-top", 0, 0, String.valueOf(b), c);
    }

    public static void c() {
        w.a("find_music", "listen", "first-publish", 0, 0, String.valueOf(b), c);
    }

    public static void d() {
        w.a("find_music", "listen", "category-" + c, 0, 0, String.valueOf(b), c);
    }

    public static String e() {
        return "find_music";
    }

    public static void a(long j, int i) {
        a("home-top", "tj_reveal", 0, (long) i, String.valueOf(j), "");
    }

    public static void a(int i, long j) {
        w.a(i, (int) StatisticHelper.DELAY_SEND, j, ValueOperateMode.UNIQUE);
    }

    private static void j(String str) {
        w.a("find_music", "tj_show", str + "_" + a);
    }

    private static void a(String str, String str2, long j, long j2, String str3, String str4) {
        w.a("find_music", str2, str + "_" + a, j, j2, str3, str4);
    }

    public static boolean i(String str) {
        return !m.a(str) && str.startsWith("song-tj-songlist_");
    }
}
