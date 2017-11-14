package com.sds.android.ttpod.framework.modules.core.g;

import android.content.Context;
import android.content.SharedPreferences;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.core.b.a.c;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.AudioQuality;

/* OldPreferenceCompact */
public class a {
    public static boolean a() {
        SharedPreferences sharedPreferences = BaseApplication.e().getSharedPreferences("com.sds.android.ttpod_preferences", 4);
        return sharedPreferences == null || !sharedPreferences.contains("play_on_headset_plug");
    }

    public static void b() {
        Context e = BaseApplication.e();
        SharedPreferences sharedPreferences = e.getSharedPreferences("com.sds.android.ttpod_preferences", 4);
        a(e);
        b(e);
        c(e);
        if (sharedPreferences != null && sharedPreferences.contains("play_on_headset_plug")) {
            a(sharedPreferences);
        }
    }

    private static void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("playback", 4);
        if (sharedPreferences != null && sharedPreferences.contains("playingmode")) {
            b.a(f.values()[sharedPreferences.getInt("playingmode", 0)]);
            b.ad(sharedPreferences.getBoolean("show_notification_when_paused", true));
            b.ab(sharedPreferences.getBoolean("show_previous_in_notification", true));
            sharedPreferences.edit().clear().commit();
        }
    }

    private static void a(SharedPreferences sharedPreferences) {
        c(sharedPreferences);
        b(sharedPreferences);
        d(sharedPreferences);
        e(sharedPreferences);
        f(sharedPreferences);
        g(sharedPreferences);
        sharedPreferences.edit().clear().commit();
    }

    private static void b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("landscape", 4);
        if (sharedPreferences != null && sharedPreferences.contains("enable_landscape")) {
            b.g(sharedPreferences.getBoolean("enable_landscape", false));
            b.o(sharedPreferences.getInt("landscape_effect_index", 0));
            sharedPreferences.edit().clear().commit();
        }
    }

    private static void c(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("minilyric", 4);
        if (sharedPreferences != null && sharedPreferences.contains("enable_mini_lyric")) {
            b.h(sharedPreferences.getBoolean("enable_mini_lyric", true));
            sharedPreferences.edit().clear().commit();
        }
    }

    private static void b(SharedPreferences sharedPreferences) {
        b.i(sharedPreferences.getBoolean("lock_screen_flag", true));
    }

    private static void c(SharedPreferences sharedPreferences) {
        b.l(Boolean.valueOf(sharedPreferences.getBoolean("play_on_headset_plug", false)).booleanValue());
        b.k(Boolean.valueOf(sharedPreferences.getBoolean("pause_on_headset_draw", true)).booleanValue());
        b.m(Boolean.valueOf(sharedPreferences.getBoolean("headset_flag", true)).booleanValue());
        b.n(Boolean.valueOf(sharedPreferences.getBoolean("switch_headset_action", false)).booleanValue());
    }

    private static void d(SharedPreferences sharedPreferences) {
        c cVar;
        b.o(Boolean.valueOf(sharedPreferences.getBoolean("shake_song_flag", false)).booleanValue());
        int i = sharedPreferences.getInt("sensor_sensitivity_item_index", 1);
        if (i == 0) {
            cVar = c.SMOOTH_SHAKE;
        } else if (i == 1) {
            cVar = c.EASY_SHAKE;
        } else if (i == 2) {
            cVar = c.NORMAL_SHAKE;
        } else if (i == 3) {
            cVar = c.HARD_SHAKE;
        } else {
            cVar = c.EXTREME_SHAKE;
        }
        b.a(cVar);
    }

    private static void e(SharedPreferences sharedPreferences) {
        b.p(sharedPreferences.getBoolean("light_list", false));
        b.q(sharedPreferences.getBoolean("light_play", true));
        b.r(sharedPreferences.getBoolean("light_lockscreen", false));
        b.s(sharedPreferences.getBoolean("light_landscape", true));
    }

    private static void f(SharedPreferences sharedPreferences) {
        AudioQuality audioQuality = AudioQuality.values()[sharedPreferences.getInt("key_online_media_audition_quality", 0)];
        b.a(audioQuality);
        b.b(audioQuality);
        b.c(audioQuality);
        b.d(AudioQuality.values()[sharedPreferences.getInt("key_favorite_download_quality", 2)]);
        int i = sharedPreferences.getInt("key_favorite_download_network", 1);
        com.sds.android.ttpod.framework.modules.core.b.a aVar = com.sds.android.ttpod.framework.modules.core.b.a.DISABLE;
        if (i == 1) {
            aVar = com.sds.android.ttpod.framework.modules.core.b.a.WIFI;
        } else if (i == 2) {
            aVar = com.sds.android.ttpod.framework.modules.core.b.a.ALL;
        }
        b.a(aVar);
        c();
        aVar = com.sds.android.ttpod.framework.modules.core.b.a.DISABLE;
        String string = sharedPreferences.getString("lyric_download", "always");
        if ("always".equals(string)) {
            aVar = com.sds.android.ttpod.framework.modules.core.b.a.ALL;
        } else if ("wifi".equals(string)) {
            aVar = com.sds.android.ttpod.framework.modules.core.b.a.WIFI;
        }
        b.b(aVar);
        b.N(sharedPreferences.getBoolean("prefer_artist_pic_play", true));
        b.v(sharedPreferences.getBoolean("prefer_show_embed_art", false));
    }

    private static void c() {
        String string = BaseApplication.e().getSharedPreferences("mediascan", 4).getString("download_media_folder", "");
        if (!m.a(string)) {
            b.g(string);
        }
    }

    private static void g(SharedPreferences sharedPreferences) {
        b.t(sharedPreferences.getBoolean("auto_play", false));
        b.u(sharedPreferences.getBoolean("auto_airplane", false));
        b.x(sharedPreferences.getBoolean("enable_push", true));
        b.w(sharedPreferences.getBoolean("enable_notification_bar", true));
    }
}
