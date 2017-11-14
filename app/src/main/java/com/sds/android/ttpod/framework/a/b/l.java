package com.sds.android.ttpod.framework.a.b;

import android.support.v4.media.TransportMediator;
import com.alibaba.wireless.security.SecExceptionCode;
import com.baidu.location.BDLocation;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.KVStatisticEvent.ValueOperateMode;
import com.sds.android.sdk.core.statistic.StatisticHelper;

/* LocalStatistic */
public class l {

    /* LocalStatistic */
    private enum a {
        ZERO_TO_TEN_MIN,
        TEN_TO_TWENTY_MIN,
        TWENTY_TO_THIRTY_MIN,
        THIRTY_TO_SIXTY_MIN,
        GREATER_SIXTY_MIN
    }

    public static void a() {
        w.a(296, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void b() {
        w.a(296, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void c() {
        w.a(244, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void d() {
        w.a(256, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void e() {
        w.a(257, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void f() {
        w.a(258, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void g() {
        w.a(259, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void h() {
        w.a(239, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void i() {
        w.a(240, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void j() {
        w.a(241, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void k() {
        w.a(242, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void l() {
        w.a(175, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void m() {
        w.a(171, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(boolean z) {
        if (z) {
            w.a(136, (int) StatisticHelper.DELAY_SEND, 1);
        } else {
            w.a(137, (int) StatisticHelper.DELAY_SEND, 1);
        }
    }

    public static void b(boolean z) {
        if (z) {
            w.a(140, (int) StatisticHelper.DELAY_SEND, 1);
        } else {
            w.a(141, (int) StatisticHelper.DELAY_SEND, 1);
        }
    }

    public static void n() {
        w.a(151, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void o() {
        w.a(154, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void p() {
        w.a(158, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void q() {
        w.a(159, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void r() {
        w.a(160, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void c(boolean z) {
        if (z) {
            w.a((int) BDLocation.TypeNetWorkLocation, (int) StatisticHelper.DELAY_SEND, 1);
        } else {
            w.a(162, (int) StatisticHelper.DELAY_SEND, 1);
        }
    }

    public static void s() {
        w.a(163, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void t() {
        w.a(134, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void u() {
        w.a(122, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void v() {
        w.a(123, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void w() {
        w.a(124, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void x() {
        w.a((int) TransportMediator.KEYCODE_MEDIA_PLAY, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void y() {
        w.a((int) TransportMediator.KEYCODE_MEDIA_PAUSE, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void z() {
        w.a(125, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void A() {
        w.a(117, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void B() {
        w.a(118, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void C() {
        w.a(119, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void D() {
        w.a(NewUser.LOCAL_LOGIN, "click", NewUser.LOCAL_LOGIN);
        w.a(1, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void E() {
        w.a(NewUser.LOCAL_LOGIN, "click", "local-music");
        w.a(2, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void F() {
        w.a(NewUser.LOCAL_LOGIN, "click", "favorite-song");
        w.a(7, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void G() {
        w.a(NewUser.LOCAL_LOGIN, "click", "favorite-songlist");
        w.a(10, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void H() {
        w.a(14, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void I() {
        w.a(NewUser.LOCAL_LOGIN, "click", "play-mode");
        w.a(15, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void J() {
        w.a(NewUser.LOCAL_LOGIN, "click", "audio-effect");
        w.a(16, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void K() {
        w.a(NewUser.LOCAL_LOGIN, "click", "skin");
        w.a(19, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void L() {
        w.a(NewUser.LOCAL_LOGIN, "click", "sleep");
        w.a(22, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void a(long j) {
        Enum enumR;
        if (0 < j && 10 >= j) {
            enumR = a.ZERO_TO_TEN_MIN;
        } else if (10 < j && 20 >= j) {
            enumR = a.TEN_TO_TWENTY_MIN;
        } else if (20 < j && 30 >= j) {
            enumR = a.TWENTY_TO_THIRTY_MIN;
        } else if (30 >= j || 60 < j) {
            enumR = a.GREATER_SIXTY_MIN;
        } else {
            enumR = a.THIRTY_TO_SIXTY_MIN;
        }
        w.a(114, (int) StatisticHelper.DELAY_SEND, enumR, ValueOperateMode.UNIQUE);
    }

    public static void M() {
        w.a(NewUser.LOCAL_LOGIN, "click", "download");
        w.a(23, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void N() {
        w.a(NewUser.LOCAL_LOGIN, "click", "setting");
        w.a(24, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void O() {
        w.a(344, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void P() {
        w.a(NewUser.LOCAL_LOGIN, "click", "exit");
        w.a(28, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void Q() {
        w.a(NewUser.LOCAL_LOGIN, "click", "song_tab");
        w.a(3, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void R() {
        w.a(NewUser.LOCAL_LOGIN, "click", "artist_tab");
        w.a(4, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void S() {
        w.a(NewUser.LOCAL_LOGIN, "click", "album_tab");
        w.a(5, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void T() {
        w.a(38, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void U() {
        w.a(NewUser.LOCAL_LOGIN, "click", "play_list_button");
        w.a(11, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void V() {
        w.a(NewUser.LOCAL_LOGIN, "click", "local-onekey_match");
    }

    public static void W() {
        w.a(NewUser.LOCAL_LOGIN, "click", "local-match_banner");
    }

    public static void X() {
        w.a(37, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void Y() {
        w.a(39, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void Z() {
        w.a(40, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aa() {
        w.a(41, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ab() {
        w.a(NewUser.LOCAL_LOGIN, "click", "local-menu");
        w.a(42, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ac() {
        w.a(43, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ad() {
        w.a(44, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ae() {
        w.a(47, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void af() {
        w.a(49, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ag() {
        w.a(50, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ah() {
        w.a(51, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ai() {
        w.a(52, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aj() {
        w.a(53, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ak() {
        w.a(55, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void al() {
        w.a(62, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void am() {
        w.a(63, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void an() {
        w.a(64, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ao() {
        w.a(65, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ap() {
        w.a(66, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aq() {
        w.a(67, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ar() {
        w.a(68, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void as() {
        w.a(69, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void at() {
        w.a(70, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void au() {
        w.a(71, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void av() {
        w.a(72, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aw() {
        w.a(73, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ax() {
        w.a(74, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void ay() {
        w.a(75, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void az() {
        w.a(45, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aA() {
        w.a(56, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aB() {
        w.a(57, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aC() {
        w.a(58, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aD() {
        w.a(59, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aE() {
        w.a(60, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aF() {
        w.a(61, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aG() {
        w.a(224, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aH() {
        w.a(225, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aI() {
        w.a(226, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aJ() {
        w.a(228, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aK() {
        w.a(229, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aL() {
        w.a(231, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aM() {
        w.a(230, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aN() {
        w.a(232, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aO() {
        w.a(233, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aP() {
        w.a(234, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aQ() {
        w.a(235, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aR() {
        w.a(236, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aS() {
        w.a(237, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aT() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_INCORRECT_DATA_FILE, (int) StatisticHelper.DELAY_SEND, 1);
    }

    public static void aU() {
        w.a((int) SecExceptionCode.SEC_ERROR_STA_INCORRECT_DATA_FILE_DATA, (int) StatisticHelper.DELAY_SEND, 1);
    }
}
