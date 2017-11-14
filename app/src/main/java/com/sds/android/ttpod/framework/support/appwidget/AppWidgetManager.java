package com.sds.android.ttpod.framework.support.appwidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.framework.storage.environment.b.a;
import com.sds.android.ttpod.framework.storage.environment.c;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;

public final class AppWidgetManager {
    private static AppWidgetManager a = null;
    private Monitor b = null;
    private a c = new a(this) {
        final /* synthetic */ AppWidgetManager a;

        {
            this.a = r1;
        }

        public void a(c cVar) {
            if (cVar == c.CURRENT_ARTIST_BITMAP_PATH) {
                g.a("AppWidgetManager", "CURRENT_ARTIST_BITMAP_PATH");
                a.a().d(b.a(com.sds.android.ttpod.framework.support.a.g.e().g()));
                AppWidgetProviderBase.b();
            } else if (cVar == c.PLAY_MODE) {
                a.a().a(b.l());
                AppWidgetProviderBase.b();
            } else if (cVar == c.IS_SHOW_DESKTOP_LYRIC_ENABLED) {
                a.a().a(b.s());
                AppWidgetProviderBase.b();
            }
        }
    };

    public static class Monitor extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            g.a("AppWidgetManager", action);
            if (Action.PLAY_STATUS_CHANGED.equals(action)) {
                PlayStatus h = com.sds.android.ttpod.framework.support.a.g.e().h();
                a.a().a(h);
                AppWidgetProviderBase.b(h);
                AppWidgetProviderBase.a(h);
            } else if (Action.PLAY_MEDIA_CHANGED.equals(action)) {
                g.a("AppWidgetManager", "PLAY_MEDIA_CHANGED");
                MediaItem g = com.sds.android.ttpod.framework.support.a.g.e().g();
                a a = a.a();
                if (g != null && !g.isNull()) {
                    a.a(g.getArtist());
                    a.b(g.getAlbum());
                    a.c(g.getTitle());
                    try {
                        AppWidgetProviderBase.b();
                    } catch (NoClassDefFoundError e) {
                        e.printStackTrace();
                    }
                }
            } else if (Action.APP_WIDGET_QUERY.equals(action)) {
                g.a("AppWidgetManager", "APP_WIDGET_QUERY");
                AppWidgetProviderBase.b();
            } else if (Action.APP_WIDGET_ENABLE_CHANGED.equals(action)) {
                g.a("AppWidgetManager", "APP_WIDGET_ENABLE_CHANGED");
                a.a().b(intent.getBooleanExtra("app_widget_enable", false));
                AppWidgetProviderBase.b(com.sds.android.ttpod.framework.support.a.g.e().h());
            }
        }

        static IntentFilter a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Action.APP_WIDGET_QUERY);
            intentFilter.addAction(Action.PLAY_STATUS_CHANGED);
            intentFilter.addAction(Action.PLAY_MEDIA_CHANGED);
            intentFilter.addAction(Action.APP_WIDGET_ENABLE_CHANGED);
            return intentFilter;
        }
    }

    public static AppWidgetManager a() {
        synchronized (AppWidgetManager.class) {
            if (a == null) {
                a = new AppWidgetManager();
            }
        }
        return a;
    }

    public void b() {
        b.a(c.CURRENT_ARTIST_BITMAP_PATH, this.c);
        b.a(c.PLAY_MODE, this.c);
        b.a(c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.c);
        this.b = new Monitor();
        BaseApplication.e().registerReceiver(this.b, Monitor.a());
    }

    public void c() {
        b.b(c.CURRENT_ARTIST_BITMAP_PATH, this.c);
        b.b(c.PLAY_MODE, this.c);
        b.b(c.IS_SHOW_DESKTOP_LYRIC_ENABLED, this.c);
        AppWidgetProviderBase.a();
        BaseApplication.e().unregisterReceiver(this.b);
    }

    public boolean a(Intent intent) {
        boolean z = false;
        if (intent == null) {
            throw new NullPointerException("intent should not be null!");
        }
        String stringExtra = intent.getStringExtra("command");
        if ("switch_play_mode_command".equals(stringExtra)) {
            b.a(f.values()[(b.l().ordinal() + 1) % f.values().length]);
        } else if (!"switch_desktop_lyric_hide_show_command".equals(stringExtra)) {
            return false;
        } else {
            if (!b.s()) {
                z = true;
            }
            b.h(z);
        }
        return true;
    }
}
