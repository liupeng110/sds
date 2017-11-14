package com.sds.android.ttpod.framework.storage.environment;

import android.content.Context;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.cloudapi.ttpod.result.Account;
import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.modules.core.b.a.c;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.GroupType;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/* Preferences */
public class b {
    private static final String a = EnvironmentContentProvider.a;
    private static Account b;
    private static NewUser c;
    private static Context d = null;

    /* Preferences */
    public interface a {
        void a(c cVar);
    }

    public static void a(Context context) {
        d = context;
        a.a(context);
    }

    public static boolean a() {
        return a.a(a, c.IS_SPLASH_AUDIO_ENABLED.name(), Boolean.valueOf(true));
    }

    public static String b() {
        return a.a(a, c.KTV_URL_DOMAIN.name(), null);
    }

    public static void a(String str) {
        a.b(a, c.KTV_URL_DOMAIN.name(), str);
    }

    public static String c() {
        return a.a(a, c.KTV_CHECK_CODE.name(), null);
    }

    public static void b(String str) {
        a.b(a, c.KTV_CHECK_CODE.name(), str);
    }

    public static String d() {
        return a.a(a, c.KTV_ROOM_INFO.name(), null);
    }

    public static void a(long j) {
        a.a(a, c.KTV_USER_ID.name(), Long.valueOf(j));
    }

    public static long e() {
        return a.a(a, c.KTV_USER_ID.name(), 0);
    }

    public static void c(String str) {
        a.b(a, c.KTV_ROOM_INFO.name(), str);
    }

    public static void a(boolean z) {
        a.b(a, c.IS_SPLASH_AUDIO_ENABLED.name(), Boolean.valueOf(z));
    }

    public static void b(boolean z) {
        a.b(a, c.LYRIC_PIC_MATCH_BANNER.name(), Boolean.valueOf(z));
    }

    public static boolean f() {
        return a.a(a, c.LYRIC_PIC_MATCH_BANNER.name(), Boolean.valueOf(true));
    }

    public static Set<String> g() {
        Set<String> a = a.a(a, c.MEDIA_SCAN_EXCLUDE_FOLDER_SET.name(), null);
        String b = d.b();
        if (a == null) {
            a = new HashSet();
            a.add("/system");
            a.add(b + "/media/audio/Ringtones");
            a.add(b + "/media/audio/Notifications");
            a.add(b + "/Ringtones");
            a.add(b + "/Notifications");
            a.add(b + "/NaviOne");
            a.add(b + "/Android");
            a.add(b + "/gameloft");
            a.add(b + "/gameloft");
            a.add(b + "/mj fairy land");
            a.add(b + "/recordings");
            a.add(b + "/My Documents/My Recordings");
            a.add(b + "/glu");
            a.add(b + "/sf_iv_data");
            a.add(b + "/miliao/audio");
            a.add(b + "/yy");
            a.add(b + "/weichang");
            a.add(b + "/MIUI/sound_recorder");
            a.add(b + "/Tencent/MicroMsg");
            a.add(b + "/Tencent/MobileQQ");
            a.add(b + "/RM/res/song");
        }
        if (!a.contains(b + "/µUTONAVI")) {
            a.add(b + "/µUTONAVI");
            a.add(b + "/µAIDU");
            String str = "/storage/extSdCard";
            if (!b.equals(str)) {
                a.add(str + "/media/audio/Ringtones");
                a.add(str + "/media/audio/Notifications");
                a.add(str + "/Ringtones");
                a.add(str + "/Notifications");
                a.add(str + "/NaviOne");
                a.add(str + "/Android");
                a.add(str + "/gameloft");
                a.add(str + "/gameloft");
                a.add(str + "/mj fairy land");
                a.add(str + "/recordings");
                a.add(str + "/My Documents/My Recordings");
                a.add(str + "/glu");
                a.add(str + "/sf_iv_data");
                a.add(str + "/miliao/audio");
                a.add(str + "/yy");
                a.add(str + "/weichang");
                a.add(str + "/MIUI/sound_recorder");
                a.add(str + "/Tencent/MicroMsg");
                a.add(str + "/Tencent/MobileQQ");
                a.add(str + "/µUTONAVI");
                a.add(str + "/µAIDU");
            }
        }
        return a;
    }

    public static void a(Set<String> set) {
        a.b(a, c.MEDIA_SCAN_EXCLUDE_FOLDER_SET.name(), (Set) set);
    }

    public static boolean h() {
        return a.a(a, c.MEDIA_SCAN_EXCLUDE_DURATION_LESS_THAN_60.name(), Boolean.valueOf(true));
    }

    public static void c(boolean z) {
        a.b(a, c.MEDIA_SCAN_EXCLUDE_DURATION_LESS_THAN_60.name(), Boolean.valueOf(z));
    }

    public static boolean i() {
        return a.a(a, c.MEDIA_SCAN_EXCLUDE_AMR_MID.name(), Boolean.valueOf(true));
    }

    public static void d(boolean z) {
        a.b(a, c.MEDIA_SCAN_EXCLUDE_AMR_MID.name(), Boolean.valueOf(z));
    }

    public static boolean j() {
        return a.a(a, c.MEDIA_SCAN_EXCLUDE_HIDDEN_FOLDER.name(), Boolean.valueOf(true));
    }

    public static void e(boolean z) {
        a.b(a, c.MEDIA_SCAN_EXCLUDE_HIDDEN_FOLDER.name(), Boolean.valueOf(z));
    }

    public static Set<String> k() {
        return a.a(a, c.MEDIA_SCAN_AUTO_FOLDER_SET.name(), null);
    }

    public static void b(Set<String> set) {
        a.b(a, c.MEDIA_SCAN_AUTO_FOLDER_SET.name(), (Set) set);
    }

    public static f l() {
        return f.values()[a.a(a, c.PLAY_MODE.name(), f.REPEAT.ordinal())];
    }

    public static void a(f fVar) {
        a.a(a, c.PLAY_MODE.name(), Integer.valueOf(fVar.ordinal()));
    }

    public static String m() {
        return a.a(a, c.PLAYING_GROUPID.name(), MediaStorage.GROUP_ID_ALL_LOCAL);
    }

    public static void d(String str) {
        a.b(a, c.PLAYING_GROUPID.name(), str);
    }

    public static String n() {
        return a.a(a, c.PLAYING_MEDIAID.name(), null);
    }

    public static void e(String str) {
        a.b(a, c.PLAYING_MEDIAID.name(), str);
    }

    public static String o() {
        return a.a(a, c.LAST_PLAY_POSITION_INFO.name(), "");
    }

    public static boolean p() {
        return a.a(a, c.NEED_SEND_MEDIA_LIST.name(), Boolean.valueOf(false));
    }

    public static void f(boolean z) {
        a.b(a, c.NEED_SEND_MEDIA_LIST.name(), Boolean.valueOf(z));
    }

    public static void f(String str) {
        a.b(a, c.LAST_PLAY_POSITION_INFO.name(), str);
    }

    public static void a(int i) {
        a.a(a, c.SLEEP_DELAY_TIME.name(), Integer.valueOf(i));
    }

    public static int q() {
        return a.a(a, c.SLEEP_DELAY_TIME.name(), 30);
    }

    public static void g(boolean z) {
        a.b(a, c.IS_AUTO_ORIENTATE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean r() {
        return a.a(a, c.IS_AUTO_ORIENTATE_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void h(boolean z) {
        a.b(a, c.IS_SHOW_DESKTOP_LYRIC_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean s() {
        return a.a(a, c.IS_SHOW_DESKTOP_LYRIC_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void i(boolean z) {
        a.b(a, c.IS_SHOW_LOCK_SCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean j(boolean z) {
        return a.a(a, c.IS_SHOW_LOCK_SCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    public static void k(boolean z) {
        a.b(a, c.IS_HEADSET_UNPLUG_AUTO_STOP_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean t() {
        return a.a(a, c.IS_HEADSET_UNPLUG_AUTO_STOP_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void l(boolean z) {
        a.b(a, c.IS_HEADSET_PLUG_AUTO_PLAY_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean u() {
        return a.a(a, c.IS_HEADSET_PLUG_AUTO_PLAY_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void m(boolean z) {
        a.b(a, c.IS_HEADSET_CONTROL_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean v() {
        return a.a(a, c.IS_HEADSET_CONTROL_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void n(boolean z) {
        a.b(a, c.IS_HEADSET_EXCHANGE_LONG_CLICK_DOUBLE_CLICK_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean w() {
        return a.a(a, c.IS_HEADSET_EXCHANGE_LONG_CLICK_DOUBLE_CLICK_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void o(boolean z) {
        a.b(a, c.IS_SHAKE_SWITCH_SONG_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean x() {
        return a.a(a, c.IS_SHAKE_SWITCH_SONG_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void a(c cVar) {
        a.a(a, c.SHAKE_PLAY_SENSITIVITY.name(), Integer.valueOf(cVar.ordinal()));
    }

    public static c y() {
        return c.values()[a.a(a, c.SHAKE_PLAY_SENSITIVITY.name(), c.EASY_SHAKE.ordinal())];
    }

    public static void p(boolean z) {
        a.b(a, c.IS_BACKLIGHT_LIST_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean z() {
        return a.a(a, c.IS_BACKLIGHT_LIST_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void q(boolean z) {
        a.b(a, c.IS_BACKLIGHT_PLAYER_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean A() {
        return a.a(a, c.IS_BACKLIGHT_PLAYER_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void r(boolean z) {
        a.b(a, c.IS_BACKLIGHT_LOCKSCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean B() {
        return a.a(a, c.IS_BACKLIGHT_LOCKSCREEN_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void s(boolean z) {
        a.b(a, c.IS_BACKLIGHT_LANDSCAPE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean C() {
        return a.a(a, c.IS_BACKLIGHT_LANDSCAPE_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void t(boolean z) {
        a.b(a, c.IS_PLAY_WHILE_STARTING_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean D() {
        return a.a(a, c.IS_PLAY_WHILE_STARTING_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void u(boolean z) {
        a.b(a, c.IS_AIRPLANE_WHILE_SLEEPING_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean E() {
        return a.a(a, c.IS_AIRPLANE_WHILE_SLEEPING_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void v(boolean z) {
        a.b(a, c.IS_SHOW_INNER_PICTURE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean F() {
        return a.a(a, c.IS_SHOW_INNER_PICTURE_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void w(boolean z) {
        a.b(a, c.IS_HIDE_NOTICE_BAR_WHILE_LOCKSCREEN_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean G() {
        return a.a(a, c.IS_HIDE_NOTICE_BAR_WHILE_LOCKSCREEN_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void x(boolean z) {
        a.b(a, c.IS_RECEIVE_PUSH_MESSAGE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean H() {
        return a.a(a, c.IS_RECEIVE_PUSH_MESSAGE_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void y(boolean z) {
        a.b(a, c.IS_ONLY_USE_WIFI.name(), Boolean.valueOf(z));
    }

    public static boolean I() {
        return a.a(a, c.IS_ONLY_USE_WIFI.name(), Boolean.valueOf(false));
    }

    public static void z(boolean z) {
        a.b(a, c.IS_AUTO_SAVE_WHEN_LISTEN.name(), Boolean.valueOf(z));
    }

    public static boolean J() {
        return a.a(a, c.IS_AUTO_SAVE_WHEN_LISTEN.name(), Boolean.valueOf(false));
    }

    public static void a(AudioQuality audioQuality) {
        a.a(a, c.AUDITION_QUALITY_2G.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    public static AudioQuality K() {
        int a = a.a(a, c.AUDITION_QUALITY_2G.name(), AudioQuality.COMPRESSED.ordinal());
        if (a == AudioQuality.LOSSLESS.ordinal()) {
            a--;
        }
        return AudioQuality.values()[a];
    }

    public static void b(AudioQuality audioQuality) {
        a.a(a, c.AUDITION_QUALITY_3G.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    public static AudioQuality L() {
        int a = a.a(a, c.AUDITION_QUALITY_3G.name(), AudioQuality.STANDARD.ordinal());
        if (a == AudioQuality.LOSSLESS.ordinal()) {
            a--;
        }
        return AudioQuality.values()[a];
    }

    public static void c(AudioQuality audioQuality) {
        a.a(a, c.AUDITION_QUALITY_WIFI.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    public static AudioQuality M() {
        int a = a.a(a, c.AUDITION_QUALITY_WIFI.name(), AudioQuality.STANDARD.ordinal());
        if (a == AudioQuality.COMPRESSED.ordinal() || a == AudioQuality.STANDARD.ordinal() || a == AudioQuality.SUPER.ordinal()) {
            return AudioQuality.values()[a];
        }
        return AudioQuality.STANDARD;
    }

    public static void b(int i) {
        a.a(a, c.MV_PLAY_QUALITY.name(), Integer.valueOf(i));
    }

    public static int N() {
        return a.a(a, c.MV_PLAY_QUALITY.name(), 0);
    }

    public static void c(int i) {
        a.a(a, c.MV_DOWNLOAD_QUALITY.name(), Integer.valueOf(i));
    }

    public static int O() {
        return a.a(a, c.MV_DOWNLOAD_QUALITY.name(), -1);
    }

    public static void d(AudioQuality audioQuality) {
        a.a(a, c.AUTO_DOWNLOAD_AUDIO_QUALITY.name(), Integer.valueOf(audioQuality.ordinal()));
    }

    public static AudioQuality P() {
        int a = a.a(a, c.AUTO_DOWNLOAD_AUDIO_QUALITY.name(), AudioQuality.UNDEFINED.ordinal());
        if (a == AudioQuality.UNDEFINED.ordinal() || a == AudioQuality.COMPRESSED.ordinal() || a == AudioQuality.STANDARD.ordinal() || a == AudioQuality.SUPER.ordinal() || a == AudioQuality.LOSSLESS.ordinal()) {
            return AudioQuality.values()[a];
        }
        return AudioQuality.STANDARD;
    }

    public static void a(com.sds.android.ttpod.framework.modules.core.b.a aVar) {
        a.a(a, c.AUTO_DOWNLOAD_AUDIO_NETWORK.name(), Integer.valueOf(aVar.ordinal()));
    }

    public static void g(String str) {
        a.b(a, c.AUDIO_DOWNLOAD_FOLDER_PATH.name(), str);
    }

    public static String Q() {
        return a.a(a, c.AUDIO_DOWNLOAD_FOLDER_PATH.name(), com.sds.android.ttpod.framework.a.r());
    }

    public static void b(com.sds.android.ttpod.framework.modules.core.b.a aVar) {
        a.a(a, c.AUTO_DOWNLOAD_LYRIC_NETWORK.name(), Integer.valueOf(aVar.ordinal()));
    }

    public static com.sds.android.ttpod.framework.modules.core.b.a R() {
        return com.sds.android.ttpod.framework.modules.core.b.a.values()[a.a(a, c.AUTO_DOWNLOAD_LYRIC_NETWORK.name(), com.sds.android.ttpod.framework.modules.core.b.a.ALL.ordinal())];
    }

    public static void d(int i) {
        a.a(a, c.LYRIC_HIGHLIGHT_COLOR.name(), Integer.valueOf(i));
    }

    public static int S() {
        return a.a(a, c.LYRIC_HIGHLIGHT_COLOR.name(), -1);
    }

    public static void e(int i) {
        a.b(a, c.LYRIC_FONT_SIZE.name(), i + "");
    }

    public static int T() {
        int i = 0;
        String a = a.a(a, c.LYRIC_FONT_SIZE.name(), "");
        if (a != null && a.length() > 0) {
            try {
                i = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    public static boolean U() {
        return a.a(a, c.LYRIC_KALA_OK_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void A(boolean z) {
        a.b(a, c.LYRIC_KALA_OK_ENABLED.name(), Boolean.valueOf(z));
    }

    public static int V() {
        return a.a(a, c.LYRIC_ALIGNMENT.name(), -1);
    }

    public static boolean W() {
        return a.a(a, c.LYRIC_FADE.name(), Boolean.valueOf(true));
    }

    public static int X() {
        return a.a(a, c.PLAYER_LAST_DISPLAY_PANEL.name(), 1);
    }

    public static void f(int i) {
        a.a(a, c.PLAYER_LAST_DISPLAY_PANEL.name(), Integer.valueOf(i));
    }

    public static void h(String str) {
        a.b(a, c.SKIN_PATH.name(), str);
    }

    public static String Y() {
        return a.a(a, c.SKIN_PATH.name(), null);
    }

    public static String Z() {
        return "assets://" + com.sds.android.ttpod.framework.modules.skin.a.a().b();
    }

    public static void i(String str) {
        a.b(a, c.BACKGROUND_PATH.name(), str);
    }

    public static String aa() {
        return a.a(a, c.BACKGROUND_PATH.name(), "follow_skin");
    }

    public static boolean ab() {
        return aa().startsWith("follow_skin");
    }

    public static int ac() {
        return a.a(a, c.MINI_LYRIC_FONT_SIZE.name(), 20);
    }

    public static void g(int i) {
        a.a(a, c.MINI_LYRIC_FONT_SIZE.name(), Integer.valueOf(i));
    }

    public static boolean ad() {
        return a.a(a, c.IS_MINI_LYRIC_LOCKED_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void B(boolean z) {
        a.b(a, c.IS_MINI_LYRIC_LOCKED_ENABLED.name(), Boolean.valueOf(z));
    }

    public static int ae() {
        return a.a(a, c.MINI_LYRIC_COLOR_STYLE.name(), 20);
    }

    public static void h(int i) {
        a.a(a, c.MINI_LYRIC_COLOR_STYLE.name(), Integer.valueOf(i));
    }

    public static void i(int i) {
        a.a(a, c.MINI_LYRIC_Y_POSITION.name(), Integer.valueOf(i));
    }

    public static int af() {
        return a.a(a, c.MINI_LYRIC_Y_POSITION.name(), com.sds.android.ttpod.common.c.a.e() >> 2);
    }

    public static boolean ag() {
        return a.a(a, c.IS_CLOUD_AUDIO_EFFECT_ENABLED.name(), Boolean.valueOf(false));
    }

    public static void C(boolean z) {
        a.b(a, c.IS_CLOUD_AUDIO_EFFECT_ENABLED.name(), Boolean.valueOf(z));
    }

    public static void D(boolean z) {
        a.b(a, c.FORCE_REQUEST_AUDIO_EFFECT.name(), Boolean.valueOf(z));
    }

    public static boolean ah() {
        return a.a(a, c.FORCE_REQUEST_AUDIO_EFFECT.name(), Boolean.valueOf(false));
    }

    public static void j(int i) {
        a.a(a, c.CURRENT_REVERB_NUM.name(), Integer.valueOf(i));
    }

    public static int ai() {
        return a.a(a, c.CURRENT_REVERB_NUM.name(), 0);
    }

    public static String aj() {
        return a.a(a, c.EQUALIZER_SETTING.name(), null);
    }

    public static void j(String str) {
        a.b(a, c.EQUALIZER_SETTING.name(), str);
    }

    public static String ak() {
        return a.a(a, c.CUSTOM_EQUALIZER_SETTING.name(), null);
    }

    public static void k(String str) {
        a.b(a, c.CUSTOM_EQUALIZER_SETTING.name(), str);
    }

    public static int al() {
        return a.a(a, c.REVERB_PRESET.name(), 0);
    }

    public static void k(int i) {
        a.a(a, c.REVERB_PRESET.name(), Integer.valueOf(i));
    }

    public static int am() {
        return a.a(a, c.BASSBOOST_STRENGTH.name(), 0);
    }

    public static void l(int i) {
        a.a(a, c.BASSBOOST_STRENGTH.name(), Integer.valueOf(i));
    }

    public static void E(boolean z) {
        a.b(a, c.EFFECT_IS_EDIT.name(), Boolean.valueOf(z));
    }

    public static boolean an() {
        return a.a(a, c.EFFECT_IS_EDIT.name(), Boolean.valueOf(false));
    }

    public static boolean ao() {
        return a.a(a, c.IS_BOOSTLIMIT_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void F(boolean z) {
        a.b(a, c.IS_BOOSTLIMIT_ENABLED.name(), Boolean.valueOf(z));
    }

    public static int ap() {
        return a.a(a, c.TREBLEBOOST_STRENGTH.name(), 0);
    }

    public static void m(int i) {
        a.a(a, c.TREBLEBOOST_STRENGTH.name(), Integer.valueOf(i));
    }

    public static int aq() {
        return a.a(a, c.VIRTUALIZER_STRENGTH.name(), 0);
    }

    public static void n(int i) {
        a.a(a, c.VIRTUALIZER_STRENGTH.name(), Integer.valueOf(i));
    }

    public static float ar() {
        return a.a(a, c.CHANNEL_BALANCE.name(), 0.0f);
    }

    public static void a(float f) {
        a.a(a, c.CHANNEL_BALANCE.name(), Float.valueOf(f));
    }

    public static void a(String str, String str2) {
        if (str.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
            str = MediaStorage.GROUP_ID_ARTIST_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            str = MediaStorage.GROUP_ID_FOLDER_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX)) {
            str = MediaStorage.GROUP_ID_GENRE_PREFIX;
        }
        a.b(a, c.SORT_ORDER_PREFIX.name() + str, str2);
    }

    public static String l(String str) {
        String str2 = "title_key";
        if (MediaStorage.GROUP_ID_RECENTLY_ADD.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC;
        } else if (MediaStorage.GROUP_ID_RECENTLY_PLAY.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_PLAY_TIME_DESC;
        } else if (MediaStorage.GROUP_ID_FAV_LOCAL.equals(str) || str.startsWith(MediaStorage.GROUP_ID_ONLINE_FAV_PREFIX) || MediaStorage.GROUP_ID_EFFECT_LOCAL.equals(str) || str.startsWith(MediaStorage.GROUP_ID_EFFECT_ONLINE) || MediaStorage.GROUP_ID_FAV.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_ADD_CUSTOM_GROUP_TIME_DESC;
        } else if (str.equals(MediaStorage.GROUP_ID_DOWNLOAD)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_ADD_TIME_DESC;
        } else if (str.startsWith(MediaStorage.GROUP_ID_CUSTOM_PREFIX) && !MediaStorage.GROUP_ID_ALL_LOCAL.equals(str)) {
            str2 = MediaStorage.MEDIA_ORDER_BY_CUSTOM;
        } else if (str.startsWith(MediaStorage.GROUP_ID_ARTIST_PREFIX)) {
            str = MediaStorage.GROUP_ID_ARTIST_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_FOLDER_PREFIX)) {
            str = MediaStorage.GROUP_ID_FOLDER_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_GENRE_PREFIX)) {
            str = MediaStorage.GROUP_ID_GENRE_PREFIX;
        } else if (str.startsWith(MediaStorage.GROUP_ID_ALBUM_PREFIX)) {
            str2 = "track";
        }
        return a.a(a, c.SORT_ORDER_PREFIX.name() + str, str2);
    }

    public static boolean as() {
        return a.a(a, c.IS_FIRST_USE_AUDIO_EFFECT.name(), Boolean.valueOf(true));
    }

    public static void G(boolean z) {
        a.b(a, c.IS_FIRST_USE_AUDIO_EFFECT.name(), Boolean.valueOf(z));
    }

    public static NewUser at() {
        if (c == null || c.getUserId() <= 0) {
            String a = a.a(a, c.USER_INFO.name(), null);
            if (!m.a(a)) {
                c = (NewUser) com.sds.android.sdk.lib.util.f.a(a, NewUser.class);
            }
        }
        return (c == null || c.getUserId() <= 0) ? new NewUser() : c;
    }

    public static void au() {
        String a = a.a(a, c.USER_INFO.name(), null);
        if (m.a(a)) {
            c = new NewUser();
        } else {
            c = (NewUser) com.sds.android.sdk.lib.util.f.a(a, NewUser.class);
        }
    }

    public static void a(NewUser newUser) {
        c = newUser;
        a.b(a, c.USER_INFO.name(), newUser == null ? null : com.sds.android.sdk.lib.util.f.a((Object) newUser));
    }

    public static boolean av() {
        Account aw = aw();
        return aw.getId() > 0 && !m.a(aw.getToken()) && at().getUserId() > 0;
    }

    public static Account aw() {
        if (b == null || b.getId() <= 0) {
            String a = a.a(a, c.ACCOUNT_INFO.name(), null);
            if (!m.a(a)) {
                b = (Account) com.sds.android.sdk.lib.util.f.a(a, Account.class);
            }
        }
        return (b == null || b.getId() <= 0) ? new Account() : b;
    }

    public static void a(Account account) {
        b = account;
        a.b(a, c.ACCOUNT_INFO.name(), account == null ? null : com.sds.android.sdk.lib.util.f.a((Object) account));
    }

    public static String ax() {
        return a.a(a, c.LOGIN_USERNAME_RECORD.name(), "");
    }

    public static void m(String str) {
        a.b(a, c.LOGIN_USERNAME_RECORD.name(), str);
    }

    public static void a(Long l) {
        a.a(a, c.LATEST_UPDATE_VERSION_TIME.name(), l);
    }

    public static Long ay() {
        return Long.valueOf(a.a(a, c.LATEST_UPDATE_VERSION_TIME.name(), 0));
    }

    public static String az() {
        return a.a(a, c.IGNORE_UPDATE_VERSION.name(), "1.0.0");
    }

    public static boolean aA() {
        return a.a(a, c.HEADSET_PLUG_TIP_DONE.name(), Boolean.valueOf(false));
    }

    public static void H(boolean z) {
        a.b(a, c.HEADSET_PLUG_TIP_DONE.name(), Boolean.valueOf(z));
    }

    public static int aB() {
        return a.a(a, c.LANDSCAPE_EFFECT_INDEX.name(), 0);
    }

    public static void o(int i) {
        a.a(a, c.LANDSCAPE_EFFECT_INDEX.name(), Integer.valueOf(i));
    }

    public static boolean aC() {
        return a.a(a, c.IS_SHOW_NEW_LANDSCAPE.name(), Boolean.valueOf(false));
    }

    public static void I(boolean z) {
        a.b(a, c.IS_SHOW_NEW_LANDSCAPE.name(), Boolean.valueOf(z));
    }

    public static boolean aD() {
        return a.a(a, c.IS_FOLLOWED_TTPOD_SINA_WEIBO.name(), Boolean.valueOf(false));
    }

    public static void J(boolean z) {
        a.b(a, c.IS_FOLLOWED_TTPOD_SINA_WEIBO.name(), Boolean.valueOf(z));
    }

    public static boolean aE() {
        return a.a(a, c.IS_NEED_RESUME_PLAY_STATUS_FROM_SOUND_SEACH.name(), Boolean.valueOf(false));
    }

    public static void K(boolean z) {
        a.b(a, c.IS_NEED_RESUME_PLAY_STATUS_FROM_SOUND_SEACH.name(), Boolean.valueOf(z));
    }

    public static String aF() {
        return a.a(a, c.PUSH_CLIENT_ID_LAST_RECORDED_TIME.name(), null);
    }

    public static void n(String str) {
        a.b(a, c.PUSH_CLIENT_ID_LAST_RECORDED_TIME.name(), str);
    }

    public static String aG() {
        return a.a(a, c.PUSH_CLIENT_ID.name(), null);
    }

    public static void o(String str) {
        a.b(a, c.PUSH_CLIENT_ID.name(), str);
    }

    public static void L(boolean z) {
        a.b(a, c.IS_SHOW_DESKTOP_LYRIC_SETTING_TIP_SHOW.name(), Boolean.valueOf(z));
    }

    public static boolean aH() {
        return a.a(a, c.IS_SHOW_DESKTOP_LYRIC_SETTING_TIP_SHOW.name(), Boolean.valueOf(true));
    }

    public static void M(boolean z) {
        a.b(a, c.IS_SHOW_MUSICCIRCLE_FIND_FRIEND_GUIDE.name(), Boolean.valueOf(z));
    }

    public static int aI() {
        return a.a(a, c.IMAGE_DOWN_AMOUNT_WIFI.name(), 5);
    }

    public static int aJ() {
        return a.a(a, c.IMAGE_DOWN_AMOUNT_2G.name(), 2);
    }

    public static void p(int i) {
        a.a(a, c.IMAGE_DOWN_AMOUNT_WIFI.name(), Integer.valueOf(i));
    }

    public static void q(int i) {
        a.a(a, c.IMAGE_DOWN_AMOUNT_2G.name(), Integer.valueOf(i));
    }

    public static boolean aK() {
        return a.a(a, c.ARTIST_PIC_PLAY.name(), Boolean.valueOf(true));
    }

    public static void N(boolean z) {
        a.b(a, c.ARTIST_PIC_PLAY.name(), Boolean.valueOf(z));
    }

    public static String aL() {
        return a.a(a, c.LATEST_UPDATE_VERSION.name(), FeedbackItem.STATUS_WAITING);
    }

    public static void p(String str) {
        a.b(a, c.LATEST_UPDATE_VERSION.name(), str);
    }

    public static void q(String str) {
        a.b(a, c.APP_VERSION.name(), str);
    }

    public static String aM() {
        return a.a(a, c.APP_VERSION.name(), "");
    }

    public static boolean aN() {
        return !com.sds.android.sdk.lib.util.EnvironmentUtils.a.f().equals(aM());
    }

    public static void r(String str) {
        a.b(a, c.INSTALL_INFO.name(), str);
    }

    public static String aO() {
        return a.a(a, c.INSTALL_INFO.name(), "");
    }

    public static void O(boolean z) {
        a.b(a, c.IS_IP_SUPPORT.name(), Boolean.valueOf(z));
    }

    public static boolean aP() {
        return a.a(a, c.IS_IP_SUPPORT.name(), Boolean.valueOf(true));
    }

    public static void P(boolean z) {
        a.b(a, c.IS_SEARCH_RESTRICTED.name(), Boolean.valueOf(z));
    }

    public static boolean aQ() {
        return a.a(a, c.IS_SEARCH_RESTRICTED.name(), Boolean.valueOf(true));
    }

    public static void Q(boolean z) {
        a.b(a, c.IS_SHOW_360_GUIDE.name(), Boolean.valueOf(z));
    }

    public static void R(boolean z) {
        a.b(a, c.IS_SHOW_360_UNION.name(), Boolean.valueOf(z));
    }

    public static boolean aR() {
        return a.a(a, c.IS_SHOW_RECOMMEND_APP.name(), Boolean.valueOf(false));
    }

    public static void S(boolean z) {
        a.b(a, c.IS_SHOW_RECOMMEND_APP.name(), Boolean.valueOf(z));
    }

    public static boolean aS() {
        return a.a(a, c.IS_SHOW_DOWNLOAD_MV_GUIDE_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void T(boolean z) {
        a.b(a, c.IS_SHOW_MUSIC_LIBRARY_MV_GUIDE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean aT() {
        return a.a(a, c.IS_SHOW_VERTICAL_MV_GUIDE_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void U(boolean z) {
        a.b(a, c.IS_SHOW_VERTICAL_MV_GUIDE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean aU() {
        return a.a(a, c.IS_SHOW_SEARCH_SONG_MV_GUIDE_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void V(boolean z) {
        a.b(a, c.IS_SHOW_SEARCH_SONG_MV_GUIDE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean aV() {
        return a.a(a, c.IS_SHOW_MUSIC_LIBRARY_MV_GUIDE_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void W(boolean z) {
        a.b(a, c.IS_SHOW_DOWNLOAD_MV_GUIDE_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean aW() {
        return a.a(a, c.IS_SHOW_DOWNLOAD_HIGHLIGHT.name(), Boolean.valueOf(true));
    }

    public static void X(boolean z) {
        a.b(a, c.IS_SHOW_DOWNLOAD_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    public static void Y(boolean z) {
        a.b(a, c.IS_SHOW_MV_DOWNLOAD_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    public static boolean aX() {
        return a.a(a, c.IS_SHOW_EFFECT_HIGHLIGHT.name(), Boolean.valueOf(true));
    }

    public static void Z(boolean z) {
        a.b(a, c.IS_SHOW_EFFECT_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    public static boolean aY() {
        return a.a(a, c.IS_SHOW_FAVORITE_HIGHLIGHT.name(), Boolean.valueOf(false));
    }

    public static void aa(boolean z) {
        a.b(a, c.IS_SHOW_FAVORITE_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    public static com.sds.android.ttpod.framework.a.b.d.q.a aZ() {
        if (m.a(a.a(a, c.RUN_STATE.name(), com.sds.android.ttpod.framework.a.b.d.q.a.FOREGROUND.toString()), com.sds.android.ttpod.framework.a.b.d.q.a.BACKGROUND.toString())) {
            return com.sds.android.ttpod.framework.a.b.d.q.a.BACKGROUND;
        }
        return com.sds.android.ttpod.framework.a.b.d.q.a.FOREGROUND;
    }

    public static void a(com.sds.android.ttpod.framework.a.b.d.q.a aVar) {
        a.b(a, c.RUN_STATE.name(), aVar.toString());
    }

    public static void a(c cVar, a aVar) {
        a(d, cVar, aVar);
    }

    public static void a(Context context, c cVar, a aVar) {
        com.sds.android.sdk.lib.util.d.a((Object) cVar, "preferencesID");
        com.sds.android.sdk.lib.util.d.a(Boolean.valueOf(cVar.isNotifyChanged()), cVar.name() + ".mSendChangedNotify", Boolean.TRUE, "Boolean.TRUE");
        a.a(context, cVar, aVar);
    }

    public static void b(c cVar, a aVar) {
        b(d, cVar, aVar);
    }

    public static void b(Context context, c cVar, a aVar) {
        com.sds.android.sdk.lib.util.d.a((Object) cVar, "preferencesID");
        com.sds.android.sdk.lib.util.d.a(Boolean.valueOf(cVar.isNotifyChanged()), cVar.name() + ".mSendChangedNotify", Boolean.TRUE, "Boolean.TRUE");
        a.b(context, cVar, aVar);
    }

    public static void a(String str, MediaItem mediaItem) {
        if (mediaItem != null && !mediaItem.isNull()) {
            a.b(a, c.CURRENT_ARTIST_BITMAP_PATH.name(), mediaItem.getID() + str);
        }
    }

    public static String a(MediaItem mediaItem) {
        if (!(mediaItem == null || mediaItem.isNull())) {
            String a = a.a(a, c.CURRENT_ARTIST_BITMAP_PATH.name(), "");
            if (a.startsWith(mediaItem.getID())) {
                return a.substring(mediaItem.getID().length());
            }
        }
        return "";
    }

    public static void b(String str, MediaItem mediaItem) {
        if (mediaItem != null && !mediaItem.isNull()) {
            a.b(a, c.CURRENT_LYRIC_PATH.name(), mediaItem.getID() + str);
        }
    }

    public static String b(MediaItem mediaItem) {
        if (!(mediaItem == null || mediaItem.isNull())) {
            String a = a.a(a, c.CURRENT_LYRIC_PATH.name(), "");
            if (a.startsWith(mediaItem.getID())) {
                return a.substring(mediaItem.getID().length());
            }
        }
        return "";
    }

    public static int ba() {
        return a.a(a, c.NOTIFICATION_PRIORITY.name(), 0);
    }

    public static void r(int i) {
        if (i < -2 || i > 2) {
            throw new IllegalArgumentException("priority should be <= NotificationUtils.NOTIFICATION_PRIORITY_MAXor >= NotificationUtils.NOTIFICATION_PRIORITY_MIN");
        }
        a.a(a, c.NOTIFICATION_PRIORITY.name(), Integer.valueOf(i));
    }

    public static boolean bb() {
        return a.a(a, c.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void ab(boolean z) {
        a.b(a, c.IS_SHOW_PREVIOUS_IN_NOTIFICATION_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean bc() {
        return a.a(a, c.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void ac(boolean z) {
        a.b(a, c.IS_SHOW_CLOSE_IN_NOTIFICATION_ENABLED.name(), Boolean.valueOf(z));
    }

    public static boolean bd() {
        return a.a(a, c.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED.name(), Boolean.valueOf(true));
    }

    public static void ad(boolean z) {
        a.b(a, c.IS_SHOW_NOTIFICATION_WHILE_PAUSED_ENABLED.name(), Boolean.valueOf(z));
    }

    public static void s(int i) {
        a.a(a, c.AUDIO_FADE_LENGTH.name(), Integer.valueOf(i));
    }

    public static int be() {
        return a.a(a, c.AUDIO_FADE_LENGTH.name(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
    }

    public static void t(int i) {
        a.a(a, c.AUDIO_FADE_PALY_PAUSE_LENGTH.name(), Integer.valueOf(i));
    }

    public static int bf() {
        return a.a(a, c.AUDIO_FADE_PALY_PAUSE_LENGTH.name(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
    }

    public static void s(String str) {
        a.b(a, c.CACHED_MEDIA_FOLDER_PATH.name(), str);
    }

    public static String bg() {
        return a.a(a, c.CACHED_MEDIA_FOLDER_PATH.name(), com.sds.android.ttpod.framework.a.g());
    }

    public static void u(int i) {
        a.a(a, c.AUDIO_FADE_SEEK_LENGTH.name(), Integer.valueOf(i));
    }

    public static int bh() {
        return a.a(a, c.AUDIO_FADE_SEEK_LENGTH.name(), (int) SecExceptionCode.SEC_ERROR_DYN_STORE);
    }

    public static int bi() {
        return a.a(a, c.AUDIO_SESSION_ID.name(), 0);
    }

    public static void v(int i) {
        a.a(a, c.AUDIO_SESSION_ID.name(), Integer.valueOf(i));
    }

    public static void t(String str) {
        a.b(a, c.ONLINE_ORIGIN.name(), str);
    }

    public static String bj() {
        return a.a(a, c.ONLINE_ORIGIN.name(), "");
    }

    public static Long bk() {
        return Long.valueOf(a.a(a, c.VALID_STARTUP_TIME.name(), 0));
    }

    public static void b(Long l) {
        a.a(a, c.VALID_STARTUP_TIME.name(), l);
    }

    public static void u(String str) {
        a.b(a, c.ONLINE_MEDIA_LIST_GROUP_NAME.name(), str);
    }

    public static String bl() {
        return a.a(a, c.ONLINE_MEDIA_LIST_GROUP_NAME.name(), "");
    }

    public static int bm() {
        return a.a(a, c.APP_RUNNING_STATE.name(), 0);
    }

    public static void w(int i) {
        a.a(a, c.APP_RUNNING_STATE.name(), Integer.valueOf(i));
    }

    public static void b(long j) {
        a.a(a, c.APP_STARTUP_TIME.name(), Long.valueOf(j));
    }

    public static long bn() {
        return a.a(a, c.APP_STARTUP_TIME.name(), 0);
    }

    public static void c(long j) {
        a.a(a, c.APP_EXIT_TIME.name(), Long.valueOf(j));
    }

    public static long bo() {
        return a.a(a, c.APP_EXIT_TIME.name(), 0);
    }

    public static String bp() {
        return a.a(a, c.HOMEPAGE_ELEMENT_SETTING.name(), "");
    }

    public static void v(String str) {
        a.b(a, c.HOMEPAGE_ELEMENT_SETTING.name(), str);
    }

    public static boolean bq() {
        return a.a(a, c.IS_SHOW_UNICOM_FLOW_HIGHLIGHT.name(), Boolean.valueOf(true));
    }

    public static void ae(boolean z) {
        a.b(a, c.IS_SHOW_UNICOM_FLOW_HIGHLIGHT.name(), Boolean.valueOf(z));
    }

    public static void a(Date date) {
        a.a(a, c.UNICOM_FLOW_CHECK_LAST_TIME.name(), Long.valueOf(date.getTime()));
    }

    public static void b(Date date) {
        a.a(a, c.UNICOM_FLOW_CONFIG_LAST_TIME.name(), Long.valueOf(date.getTime()));
    }

    public static Date br() {
        return new Date(a.a(a, c.UNICOM_FLOW_CONFIG_LAST_TIME.name(), 0));
    }

    public static float bs() {
        return a.a(a, c.UNICOM_UNOPEN_FLOW_SIZE.name(), 30.0f);
    }

    public static void b(float f) {
        a.a(a, c.UNICOM_UNOPEN_FLOW_SIZE.name(), Float.valueOf(f));
    }

    public static void af(boolean z) {
        a.b(a, c.UNICOM_FLOW_2G_3G_MV_DIALOG.name(), Boolean.valueOf(z));
    }

    public static boolean bt() {
        return a.a(a, c.UNICOM_FLOW_2G_3G_MV_DIALOG.name(), Boolean.valueOf(true));
    }

    public static void ag(boolean z) {
        a.b(a, c.IS_POPUP_MONTHEND_DIALOG.name(), Boolean.valueOf(z));
    }

    public static boolean bu() {
        return a.a(a, c.IS_POPUP_MONTHEND_DIALOG.name(), Boolean.valueOf(true));
    }

    public static void x(int i) {
        a.a(a, c.MV_CHECK_TYPE.name(), Integer.valueOf(i));
    }

    public static void ah(boolean z) {
        a.b(a, c.DOWNLOAD_CHOSE_DIALOG_ENABLE.name(), Boolean.valueOf(z));
    }

    public static boolean bv() {
        return a.a(a, c.DOWNLOAD_CHOSE_DIALOG_ENABLE.name(), Boolean.valueOf(true));
    }

    public static void ai(boolean z) {
        a.b(a, c.SHOW_BACKGROUND_MORE.name(), Boolean.valueOf(z));
    }

    public static void d(long j) {
        a.a(a, c.BACKGROUND_MORE_CREATE_TIME.name(), Long.valueOf(j));
    }

    public static void e(long j) {
        a.a(a, c.FEEDBACK_LAST_UPDATE_TIME.name(), Long.valueOf(j));
    }

    public static long bw() {
        return a.a(a, c.FEEDBACK_LAST_UPDATE_TIME.name(), 0);
    }

    public static boolean bx() {
        return a.a(a, c.FEEDBACK_HAS_UPDATE.name(), Boolean.valueOf(false));
    }

    public static void a(GroupType groupType, String str) {
        String b = b(groupType);
        if (b != null) {
            a.b(a, c.GROUP_SORT_ORDER_PREFIX.name() + b, str);
        }
    }

    private static String b(GroupType groupType) {
        if (groupType == GroupType.DEFAULT_ARTIST) {
            return MediaStorage.GROUP_ID_ARTIST_PREFIX;
        }
        if (groupType == GroupType.DEFAULT_ALBUM) {
            return MediaStorage.GROUP_ID_ALBUM_PREFIX;
        }
        if (groupType == GroupType.DEFAULT_FOLDER) {
            return MediaStorage.GROUP_ID_FOLDER_PREFIX;
        }
        return null;
    }

    public static String a(GroupType groupType) {
        String str = "";
        String b = b(groupType);
        if (b != null) {
            return a.a(a, c.GROUP_SORT_ORDER_PREFIX.name() + b, str);
        }
        return str;
    }

    public static String by() {
        return a.a(a, c.LAST_CMMUSIC_INIT_SUCCESS_IMSI.name(), "");
    }

    public static void w(String str) {
        a.b(a, c.LAST_CMMUSIC_INIT_SUCCESS_IMSI.name(), str);
    }

    public static void x(String str) {
        a.b(a, c.GLOBAL_PLAYING_CONTEXT.name(), str);
    }

    public static String bz() {
        return a.a(a, c.GLOBAL_PLAYING_CONTEXT.name(), "");
    }

    public static void aj(boolean z) {
        a.b(a, c.SUPPORT_INIT_FINISHED.name(), Boolean.valueOf(z));
    }

    public static boolean bA() {
        return a.a(a, c.SUPPORT_INIT_FINISHED.name(), Boolean.valueOf(true));
    }

    public static void ak(boolean z) {
        a.b(a, c.ENABLE_DANMAKU.name(), Boolean.valueOf(z));
    }

    public static boolean bB() {
        return a.a(a, c.ENABLE_DANMAKU.name(), Boolean.valueOf(true));
    }
}
