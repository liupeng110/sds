package com.sds.android.ttpod.framework.support.appwidget;

import android.content.SharedPreferences;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.player.PlayStatus;

/* AppWidgetPreference */
final class a {
    private static a b = null;
    private SharedPreferences a;

    static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    a() {
        this.a = null;
        this.a = BaseApplication.e().getSharedPreferences("appwidget_preference", 4);
    }

    void a(String str) {
        this.a.edit().putString("media_artist", str).commit();
    }

    String b() {
        return this.a.getString("media_artist", BaseApplication.e().getString(R.string.unknown));
    }

    void b(String str) {
        this.a.edit().putString("media_album", str).commit();
    }

    String c() {
        return this.a.getString("media_album", BaseApplication.e().getString(R.string.unknown));
    }

    void c(String str) {
        this.a.edit().putString("media_title", str).commit();
    }

    String d() {
        return this.a.getString("media_title", BaseApplication.e().getString(R.string.unknown));
    }

    void d(String str) {
        this.a.edit().putString("current_artist_bitmap_path", str).commit();
    }

    String e() {
        return this.a.getString("current_artist_bitmap_path", null);
    }

    void a(boolean z) {
        this.a.edit().putBoolean("is_show_desktop_lyric_enabled", z).commit();
    }

    boolean f() {
        return this.a.getBoolean("is_show_desktop_lyric_enabled", true);
    }

    void b(boolean z) {
        this.a.edit().putBoolean("appwidget_enabled", z).commit();
    }

    boolean g() {
        return this.a.getBoolean("appwidget_enabled", false);
    }

    void a(f fVar) {
        this.a.edit().putInt("play_mode", fVar.ordinal()).commit();
    }

    f h() {
        return f.values()[this.a.getInt("play_mode", f.REPEAT.ordinal())];
    }

    void a(PlayStatus playStatus) {
        this.a.edit().putInt("play_status", playStatus.ordinal()).commit();
    }
}
