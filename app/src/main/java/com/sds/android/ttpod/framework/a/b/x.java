package com.sds.android.ttpod.framework.a.b;

import com.alibaba.wireless.security.SecExceptionCode;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.KVStatisticEvent.ValueOperateMode;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d.c;
import com.sds.android.ttpod.framework.a.v;
import java.io.File;

/* ThemeStatistic */
public class x {
    private static boolean a = true;

    /* ThemeStatistic */
    private enum a {
        FOLLOW_SKIN,
        LOCAL
    }

    /* ThemeStatistic */
    private enum b {
        INTERNAL,
        LOCAL
    }

    public static void a(String str) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_INVALID_ENCRYPTED_DATA, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void b(String str) {
        w.a(316, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void c(String str) {
        w.a(317, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void a() {
        w.a(132, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void b() {
        w.a(133, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void c() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_NO_SUCH_INDEX, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
        s();
    }

    public static void d() {
        w.a(129, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
        s();
    }

    public static void e() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_DATA_FILE_MISMATCH, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
        s();
    }

    public static void d(String str) {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_NO_DATA_FILE, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void e(String str) {
        w.a(341, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void f(String str) {
        w.a(294, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void g(String str) {
        w.a("theme", "show", str);
    }

    public static void f() {
        File[] a = v.a();
        w.a("theme", "show", "update", (long) (a != null ? a.length : 0));
    }

    public static void g() {
        w.a("theme", "show", "change");
    }

    public static void a(int i) {
        w.a(318, (int) StatisticHelper.DELAY_SEND, (long) i, ValueOperateMode.ADD);
    }

    public static void h() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_NO_MEMORY, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
        s();
    }

    public static void i() {
        w.a(314, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void j() {
        w.a("theme", "show", "background-update", (long) com.sds.android.ttpod.framework.modules.skin.b.a().size());
    }

    public static void k() {
        w.a(311, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void l() {
        w.a(312, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void b(int i) {
        w.a(315, (int) StatisticHelper.DELAY_SEND, (long) i, ValueOperateMode.ADD);
    }

    public static void h(String str) {
        w.a(313, (int) StatisticHelper.DELAY_SEND, str, ValueOperateMode.UNIQUE);
    }

    public static void m() {
        w.a(342, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void n() {
        int i;
        String Y = com.sds.android.ttpod.framework.storage.environment.b.Y();
        String b = v.b(m.a(Y) ? com.sds.android.ttpod.framework.storage.environment.b.Z() : Y);
        Enum enumR = b.LOCAL;
        if (b == "assets://") {
            enumR = b.INTERNAL;
        }
        w.a((int) SecExceptionCode.SEC_ERROR_STA_KEY_NOT_EXISTED, (int) StatisticHelper.DELAY_SEND, enumR, ValueOperateMode.DEFAULT);
        Y = e.j(Y);
        if (enumR != b.INTERNAL || m.a(Y)) {
            i = 0;
        } else {
            i = Character.digit(Y.charAt(0), 10);
        }
        new SUserEvent("PAGE_CLICK", r.ACTION_DEFAULT_SKIN_TYPE.getValue(), s.PAGE_NONE.getValue()).append(Downloads.COLUMN_STATUS, Integer.valueOf(i)).post();
        c.a("theme_name", Y);
        c.a("theme_type", String.valueOf(i));
    }

    public static void o() {
        Enum enumR = com.sds.android.ttpod.framework.storage.environment.b.ab() ? a.FOLLOW_SKIN : a.LOCAL;
        w.a((int) SecExceptionCode.SEC_ERROR_STA_ILLEGEL_KEY, (int) StatisticHelper.DELAY_SEND, enumR, ValueOperateMode.DEFAULT);
        int i = enumR == a.FOLLOW_SKIN ? 0 : 1;
        new SUserEvent("PAGE_CLICK", r.ACTION_DEFAULT_BKG_TYPE.getValue(), s.PAGE_NONE.getValue()).append(Downloads.COLUMN_STATUS, Integer.valueOf(i)).post();
        c.a("background_type", String.valueOf(i));
    }

    public static void p() {
        new SUserEvent("PAGE_CLICK", r.ACTION_USER_BACKGROUND_COUNT.getValue(), s.PAGE_NONE.getValue()).append("background_count", Integer.valueOf(com.sds.android.ttpod.framework.modules.skin.b.b())).post();
    }

    public static void q() {
        if (a) {
            a = false;
            n();
            o();
            r();
            p();
        }
    }

    public static void r() {
        File[] a = v.a();
        new SUserEvent("PAGE_CLICK", r.ACTION_DOWNLOADED_SKIN_COUNT.getValue(), s.PAGE_NONE.getValue()).append("skin_count", Integer.valueOf(a == null ? 0 : a.length)).post();
    }

    public static void s() {
        w.a(319, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void t() {
        w.a(321, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void u() {
        w.a(340, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }

    public static void v() {
    }

    public static void w() {
        w.a(343, (int) StatisticHelper.DELAY_SEND, 1, ValueOperateMode.ADD);
    }
}
