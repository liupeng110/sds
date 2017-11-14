package com.sds.android.ttpod.framework.a.b;

/* LyricPicStatistic */
public class m {
    public static void a(String str, Long l, String str2, String str3, Integer num) {
        w.a("lyric_pic", "search", str, (long) num.intValue(), l == null ? 0 : l.longValue(), str2, str3);
    }

    public static void a(String str, int i, String str2, String str3, Integer num) {
        w.a("lyric_pic", "search", str, (long) num.intValue(), (long) i, str2, str3);
    }
}
