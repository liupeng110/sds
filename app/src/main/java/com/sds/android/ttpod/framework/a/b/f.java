package com.sds.android.ttpod.framework.a.b;

import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.core.statistic.StatisticHelper;
import com.sds.android.ttpod.framework.storage.environment.b;

/* AudioEffectStatistic */
public class f {
    public static void a() {
        w.a("audio_effect", "show", "audio-effect-best", b.ag() ? 1 : 0);
        if (b.ag()) {
            w.a(17, (int) StatisticHelper.DELAY_SEND, 1);
        } else {
            w.a(18, (int) StatisticHelper.DELAY_SEND, 1);
        }
    }

    public static void b() {
        w.a(87, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void c() {
        w.a(88, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void d() {
        w.a(90, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void e() {
        w.a(91, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void f() {
        w.a(92, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void g() {
        w.a(93, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void h() {
        w.a(94, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void i() {
        w.a(95, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void j() {
        w.a(96, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void k() {
        w.a(97, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void l() {
        w.a(98, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void m() {
        w.a(99, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void n() {
        w.a(100, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void o() {
        w.a(101, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void p() {
        w.a((int) SecExceptionCode.SEC_ERROR_INIT_LOADSO_FAIL, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void q() {
        w.a((int) SecExceptionCode.SEC_ERROR_INIT_NO_RSA_FILE_ERROR, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void r() {
        w.a((int) SecExceptionCode.SEC_ERROR_INIT_PUBLICKKEY_FIND_ERROR, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void s() {
        w.a((int) SecExceptionCode.SEC_ERROR_INIT_SO_NOT_EXIST, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void t() {
        w.a(107, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void u() {
        w.a(108, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void v() {
        w.a(109, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void w() {
        w.a(110, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void x() {
        w.a(111, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void y() {
        w.a(112, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void z() {
        w.a(113, (int) StatisticHelper.DELAY_SEND, 1);
    }
}
