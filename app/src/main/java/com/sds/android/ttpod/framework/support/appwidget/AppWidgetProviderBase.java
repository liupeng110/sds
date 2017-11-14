package com.sds.android.ttpod.framework.support.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.RemoteViews;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.support.SupportService;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.framework.support.a.g;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.media.text.TTTextUtils;
import com.tencent.open.SocialConstants;
import java.io.File;

public abstract class AppWidgetProviderBase extends AppWidgetProvider {
    private static volatile boolean a;
    private static Context b;
    private static final a[] d = new a[]{new a(R.layout.appwidget_main_4x1, "com.sds.android.ttpod.component.appwidget.DesktopPlayer1"), new a(R.layout.appwidget_main_4x2, "com.sds.android.ttpod.component.appwidget.DesktopPlayer2"), new a(R.layout.appwidget_main_4x4, "com.sds.android.ttpod.component.appwidget.DesktopPlayer4"), new a(R.layout.appwidget_main_5x1, "com.sds.android.ttpod.component.appwidget.DesktopPlayer5x1"), new a(R.layout.appwidget_main_5x2, "com.sds.android.ttpod.component.appwidget.DesktopPlayer5x2")};
    private static Handler e = new Handler(Looper.getMainLooper());
    private static Runnable f = new Runnable() {
        public void run() {
            g e = g.e();
            if (e.h() != PlayStatus.STATUS_PLAYING) {
                AppWidgetProviderBase.a = false;
                return;
            }
            MediaItem g = e.g();
            if (g == null || g.isNull()) {
                AppWidgetProviderBase.a = false;
                return;
            }
            AppWidgetProviderBase.b(e.i(), g.getDuration().intValue(), e.j());
            AppWidgetProviderBase.a = true;
            AppWidgetProviderBase.e.postDelayed(this, 1000);
        }
    };
    private Handler c = new Handler(this, Looper.myLooper()) {
        final /* synthetic */ AppWidgetProviderBase a;

        public void handleMessage(Message message) {
            Process.killProcess(Process.myPid());
        }
    };

    private static final class a {
        private int a;
        private ComponentName b;

        private a(int i, String str) {
            this.a = i;
            this.b = new ComponentName("com.sds.android.ttpod", str);
        }
    }

    public static void a(Context context) {
        b = context;
    }

    public static void a() {
        com.sds.android.sdk.lib.util.g.a("AppWidgetProviderBase", "updateAppWidgetStop");
        AppWidgetManager instance = AppWidgetManager.getInstance(b);
        for (a aVar : d) {
            RemoteViews remoteViews = new RemoteViews(b.getPackageName(), aVar.a);
            a(remoteViews, PlayStatus.STATUS_STOPPED);
            b(remoteViews);
            try {
                instance.updateAppWidget(aVar.b, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void b() {
        AppWidgetManager instance = AppWidgetManager.getInstance(b);
        MediaItem g = g.e().g();
        Bitmap a = b.a(com.sds.android.ttpod.framework.storage.environment.b.a(g), com.sds.android.ttpod.common.c.a.a(200));
        for (a aVar : d) {
            RemoteViews remoteViews = new RemoteViews(b.getPackageName(), aVar.a);
            try {
                a(remoteViews, a);
            } catch (Exception e) {
                String a2 = a(com.sds.android.ttpod.framework.storage.environment.b.a(g));
                com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "setAlbumCoverToRemoteView e albumCoverFile = " + a2);
                a(remoteViews, a2);
            }
            a(remoteViews);
            b(remoteViews);
            try {
                instance.updateAppWidget(aVar.b, remoteViews);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void a(RemoteViews remoteViews) {
        MediaItem g = g.e().g();
        if (!(g == null || g.isNull())) {
            a(remoteViews, g.getArtist(), g.getAlbum(), g.getTitle());
        }
        a(remoteViews, g.e().h());
        a(remoteViews, com.sds.android.ttpod.framework.storage.environment.b.l());
        a(remoteViews, com.sds.android.ttpod.framework.storage.environment.b.s());
    }

    public static void a(PlayStatus playStatus) {
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "updatePlayStatus");
        AppWidgetManager instance = AppWidgetManager.getInstance(b);
        for (a aVar : d) {
            RemoteViews remoteViews = new RemoteViews(b.getPackageName(), aVar.a);
            a(remoteViews, playStatus);
            b(remoteViews);
            try {
                instance.updateAppWidget(aVar.b, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void a(RemoteViews remoteViews, String str, String str2, String str3) {
        int layoutId = remoteViews.getLayoutId();
        if (!(R.layout.appwidget_main_4x1 == layoutId || R.layout.appwidget_main_5x1 == layoutId)) {
            remoteViews.setTextViewText(R.id.text_artist, TTTextUtils.validateString(b, str));
        }
        if (R.layout.appwidget_main_4x2 == layoutId || R.layout.appwidget_main_5x2 == layoutId) {
            remoteViews.setTextViewText(R.id.text_album, TTTextUtils.validateString(b, str2));
        }
        remoteViews.setTextViewText(R.id.text_title, TTTextUtils.validateString(b, str3));
        if (R.layout.appwidget_main_4x1 == layoutId || R.layout.appwidget_main_5x1 == layoutId) {
            remoteViews.setTextViewText(R.id.text_title, TTTextUtils.validateString(b, str) + " - " + TTTextUtils.validateString(b, str3));
        }
    }

    private static void a(RemoteViews remoteViews, Bitmap bitmap) throws Exception {
        if (bitmap != null) {
            remoteViews.setImageViewBitmap(R.id.image_album_cover, bitmap);
        } else {
            remoteViews.setImageViewResource(R.id.image_album_cover, R.drawable.img_appwidget_album_cover_normal);
        }
    }

    private static void a(RemoteViews remoteViews, String str) {
        File e = e.e(str);
        if (e == null || !e.exists()) {
            remoteViews.setImageViewResource(R.id.image_album_cover, R.drawable.img_appwidget_album_cover_normal);
        } else {
            remoteViews.setImageViewUri(R.id.image_album_cover, Uri.fromFile(e));
        }
    }

    private static void a(RemoteViews remoteViews, f fVar) {
        int layoutId = remoteViews.getLayoutId();
        if (R.layout.appwidget_main_4x1 != layoutId && R.layout.appwidget_main_5x1 != layoutId) {
            remoteViews.setImageViewResource(R.id.button_playmode, a(fVar.ordinal()));
        }
    }

    private static void a(RemoteViews remoteViews, PlayStatus playStatus) {
        remoteViews.setImageViewResource(R.id.button_play_pause, PlayStatus.STATUS_PLAYING == playStatus ? R.drawable.img_appwidget_pause : R.drawable.img_appwidget_play);
    }

    private static void a(RemoteViews remoteViews, boolean z) {
        remoteViews.setImageViewResource(R.id.button_minilyric, z ? R.drawable.img_appwidget_minilyric_on : R.drawable.img_appwidget_minilyric_off);
    }

    private static String a(String str) {
        String str2 = com.sds.android.ttpod.framework.a.k() + File.separator + "albumCoverFileTmp";
        Bitmap a = b.a(str, com.sds.android.ttpod.common.c.a.a(200));
        if (a != null) {
            b.a(a, str2);
            a.recycle();
        }
        return str2;
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "onUpdate");
        AppWidgetProviderInfo appWidgetInfo = appWidgetManager.getAppWidgetInfo(iArr[0]);
        if (appWidgetInfo != null) {
            a(context, appWidgetManager, appWidgetInfo.provider, appWidgetInfo.initialLayout);
        }
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        AppWidgetProviderInfo appWidgetInfo = appWidgetManager.getAppWidgetInfo(i);
        if (appWidgetInfo != null) {
            a(context, appWidgetManager, appWidgetInfo.provider, appWidgetInfo.initialLayout);
        }
    }

    private static void a(Context context, AppWidgetManager appWidgetManager, ComponentName componentName, int i) {
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "initAppWidget");
        b = context;
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), i);
        a a = a.a();
        try {
            a(remoteViews, b.a(a.e(), com.sds.android.ttpod.common.c.a.a(200)));
        } catch (Exception e) {
            String a2 = a(a.e());
            com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "setAlbumCoverToRemoteView e albumCoverFile = " + a2);
            a(remoteViews, a2);
        }
        a(remoteViews, a.b(), a.c(), a.d());
        a(remoteViews, PlayStatus.STATUS_STOPPED);
        a(remoteViews, a.h());
        a(remoteViews, a.f());
        b(remoteViews);
        try {
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        context.sendBroadcast(new Intent(Action.APP_WIDGET_QUERY));
    }

    private static int a(int i) {
        return new int[]{R.drawable.img_appwidget_playmode_repeat, R.drawable.img_appwidget_playmode_repeatone, R.drawable.img_appwidget_playmode_sequence, R.drawable.img_appwidget_playmode_shuffle}[i];
    }

    private static void b(RemoteViews remoteViews) {
        b(remoteViews, 0, R.id.image_album_cover, Action.START_ENTRY);
        a(remoteViews, 1, R.id.button_play_pause, "play_pause_command");
        a(remoteViews, 2, R.id.button_play_prev, "previous_command");
        a(remoteViews, 3, R.id.button_play_next, "next_command");
        a(remoteViews, 4, R.id.button_playmode, "switch_play_mode_command");
        a(remoteViews, 5, R.id.button_minilyric, "switch_desktop_lyric_hide_show_command");
    }

    private static void a(RemoteViews remoteViews, int i, int i2, String str) {
        remoteViews.setOnClickPendingIntent(i2, PendingIntent.getService(b, i, new Intent(b, SupportService.class).putExtra("command", str).setAction(str), 134217728));
    }

    private static void b(RemoteViews remoteViews, int i, int i2, String str) {
        Context context = b;
        Intent intent = new Intent(str);
        intent.putExtra(SocialConstants.PARAM_TYPE, "widget");
        remoteViews.setOnClickPendingIntent(i2, PendingIntent.getActivity(context, i, intent, 134217728));
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", intent.getAction());
        this.c.removeMessages(0);
        this.c.sendEmptyMessageDelayed(0, 30000);
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "onEnabled");
        a.a().b(true);
        context.sendBroadcast(new Intent(Action.APP_WIDGET_ENABLE_CHANGED).putExtra("app_widget_enable", true));
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "onDisabled");
        a.a().b(false);
        context.sendBroadcast(new Intent(Action.APP_WIDGET_ENABLE_CHANGED).putExtra("app_widget_enable", false));
    }

    public static void b(PlayStatus playStatus) {
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "updateProcessStatus");
        boolean g = a.a().g();
        if (playStatus != PlayStatus.STATUS_PLAYING) {
            return;
        }
        if (g) {
            a = true;
            e.postDelayed(f, 1000);
            return;
        }
        a = false;
        e.removeCallbacks(f);
    }

    private static void b(int i, int i2, float f) {
        com.sds.android.sdk.lib.util.g.c("AppWidgetProviderBase", "refreshProcess");
        int i3 = (int) (((float) i2) * f);
        if (i3 < 0) {
            i3 = 0;
        }
        AppWidgetManager instance = AppWidgetManager.getInstance(b);
        for (a aVar : d) {
            RemoteViews remoteViews = new RemoteViews(b.getPackageName(), aVar.a);
            a(remoteViews, i, i2, i3);
            a(remoteViews);
            b(remoteViews);
            try {
                instance.updateAppWidget(aVar.b, remoteViews);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void a(RemoteViews remoteViews, int i, int i2, int i3) {
        remoteViews.setTextViewText(R.id.text_time_current, b(i));
        remoteViews.setTextViewText(R.id.text_time_duration, b(i2));
        remoteViews.setProgressBar(R.id.seekbar_progress, i2, i, false);
        remoteViews.setInt(R.id.seekbar_progress, "setSecondaryProgress", i3);
    }

    private static String b(int i) {
        int i2 = i / 1000;
        return c(i2 / 60) + ":" + c(i2 % 60);
    }

    private static String c(int i) {
        if (i < 0) {
            return "00";
        }
        if (i < 0 || i >= 10) {
            return "" + i;
        }
        return FeedbackItem.STATUS_WAITING + i;
    }
}
