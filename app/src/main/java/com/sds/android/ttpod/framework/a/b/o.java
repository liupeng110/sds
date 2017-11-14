package com.sds.android.ttpod.framework.a.b;

import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.m;
import com.sina.weibo.sdk.api.CmdObject;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.util.UUID;

/* MusicLibraryStatistic */
public class o {
    private static String a;
    private static String b;

    public static void a(String str) {
        b = str;
    }

    public static String a() {
        return a;
    }

    public static void b() {
        a = UUID.randomUUID().toString();
        b(CmdObject.CMD_HOME);
    }

    public static void a(boolean z) {
        if (!m.a(b)) {
            w.a("find_music", "collect", b, z ? 0 : 1, (long) p.f());
        }
    }

    public static void c() {
        if (!m.a(b)) {
            w.a("find_music", "listen", b, 0, (long) p.f());
        }
    }

    public static void d() {
        w.a(86, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(int i, String str) {
        a(WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY, i, str);
    }

    public static void b(int i, String str) {
        a("singer", i, str);
    }

    public static void c(int i, String str) {
        a("radio", i, str);
    }

    public static void d(int i, String str) {
        a("category-son", i, str);
    }

    private static void b(String str) {
        w.a("find_music", "library_show", str + "_" + a);
    }

    private static void a(String str, int i, String str2) {
        w.a("find_music", "library_show", str + "_" + a, 0, 0, String.valueOf(i), str2);
    }

    public static String e() {
        return "find_music";
    }
}
