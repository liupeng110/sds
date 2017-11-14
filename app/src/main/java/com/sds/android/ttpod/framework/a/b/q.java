package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sina.weibo.sdk.api.CmdObject;
import java.util.UUID;

/* RankStatistic */
public class q {
    private static String a;

    public static String a() {
        return a;
    }

    public static String b() {
        return "find_music";
    }

    public static void c() {
        a = UUID.randomUUID().toString();
        a(CmdObject.CMD_HOME);
    }

    public static void a(int i, String str, int i2) {
        a("rank-list-play", i, str);
        w.a(83, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void b(int i, String str, int i2) {
        a("rank-detail", i, str);
        w.a(84, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(int i, String str) {
        a("rank-detail-play", i, str);
    }

    public static void a(String str, boolean z, int i) {
        b("rank_" + str, z, i);
    }

    private static void b(String str, boolean z, int i) {
        w.a("find_music", "collect", str + "_" + a, z ? 0 : 1, (long) i);
    }

    private static void a(String str) {
        w.a("find_music", "rank_show", str + "_" + a);
    }

    private static void a(String str, int i, String str2) {
        w.a("find_music", "rank_show", str + "_" + a, 0, 0, String.valueOf(i), str2);
    }
}
