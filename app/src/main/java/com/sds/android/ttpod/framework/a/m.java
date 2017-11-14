package com.sds.android.ttpod.framework.a;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseIntArray;
import android.widget.RemoteViews;
import com.sds.android.sdk.lib.util.b;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.media.player.PlayStatus;

/* NotificationUtils */
public class m {
    private static long a = 0;
    private static final SparseIntArray b = new SparseIntArray();

    static {
        b.put(0, 0);
        b.put(1, 1);
        b.put(-1, -1);
        b.put(2, 2);
        b.put(-2, -2);
    }

    public static Notification a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, Bitmap bitmap, PendingIntent pendingIntent) {
        return a(context, i, charSequence, charSequence2, bitmap, 0, pendingIntent);
    }

    public static Notification a(Context context, int i, CharSequence charSequence, CharSequence charSequence2, Bitmap bitmap, long j, PendingIntent pendingIntent) {
        Bitmap a;
        l lVar = new l(context);
        if (bitmap != null) {
            try {
                a = b.a(bitmap, a.a(64));
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (a == null && i != 0) {
                a = BitmapFactory.decodeResource(context.getResources(), i);
            }
            if (j.c()) {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_default);
                remoteViews.setTextViewText(R.id.textview_title, charSequence);
                remoteViews.setTextColor(R.id.textview_title, l.c());
                remoteViews.setTextViewText(R.id.textview_sub_title, charSequence2);
                remoteViews.setTextColor(R.id.textview_sub_title, l.c());
                remoteViews.setImageViewBitmap(R.id.imgview_largeicon, a);
                lVar.a(remoteViews);
                lVar.b(i);
            } else {
                lVar.a(charSequence);
                lVar.b(charSequence2);
                lVar.a(i);
            }
            lVar.a(pendingIntent);
            lVar.a(j);
            return lVar.a();
        }
        a = bitmap;
        a = BitmapFactory.decodeResource(context.getResources(), i);
        if (j.c()) {
            RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), R.layout.notification_default);
            remoteViews2.setTextViewText(R.id.textview_title, charSequence);
            remoteViews2.setTextColor(R.id.textview_title, l.c());
            remoteViews2.setTextViewText(R.id.textview_sub_title, charSequence2);
            remoteViews2.setTextColor(R.id.textview_sub_title, l.c());
            remoteViews2.setImageViewBitmap(R.id.imgview_largeicon, a);
            lVar.a(remoteViews2);
            lVar.b(i);
        } else {
            lVar.a(charSequence);
            lVar.b(charSequence2);
            lVar.a(i);
        }
        lVar.a(pendingIntent);
        lVar.a(j);
        return lVar.a();
    }

    public static Notification a(Context context, PlayStatus playStatus, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3, PendingIntent pendingIntent4, PendingIntent pendingIntent5) {
        PendingIntent pendingIntent6;
        PendingIntent pendingIntent7;
        l lVar = new l(context);
        lVar.b(R.drawable.img_notification_tickericon);
        lVar.a(pendingIntent);
        if (a <= 0) {
            a = System.currentTimeMillis();
        }
        lVar.a(a);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_play);
        remoteViews.setTextViewText(R.id.title, charSequence);
        remoteViews.setTextViewText(R.id.text, charSequence2);
        if (!j.f()) {
            remoteViews.setTextColor(R.id.title, l.b());
            remoteViews.setTextColor(R.id.text, l.c());
        }
        a(remoteViews, bitmap);
        lVar.a(remoteViews);
        if (com.sds.android.ttpod.framework.storage.environment.b.bb()) {
            pendingIntent6 = pendingIntent2;
        } else {
            pendingIntent6 = null;
        }
        if (com.sds.android.ttpod.framework.storage.environment.b.bc()) {
            pendingIntent7 = pendingIntent5;
        } else {
            pendingIntent7 = null;
        }
        if (j.f()) {
            remoteViews.setViewVisibility(R.id.control_bar, 0);
            a(remoteViews, playStatus, pendingIntent3, pendingIntent6, pendingIntent4, pendingIntent7);
        }
        Notification a = lVar.a();
        if (j.g()) {
            a.priority = b.get(com.sds.android.ttpod.framework.storage.environment.b.ba());
            remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_play_big);
            if (charSequence != null) {
                remoteViews.setTextViewText(R.id.title, charSequence);
                remoteViews.setTextViewText(R.id.text, charSequence2);
                remoteViews.setTextViewText(R.id.text2, charSequence3);
            }
            a(remoteViews, bitmap);
            a.bigContentView = remoteViews;
            a(remoteViews, playStatus, pendingIntent3, pendingIntent6, pendingIntent4, pendingIntent7);
        }
        return a;
    }

    private static void a(RemoteViews remoteViews, PlayStatus playStatus, PendingIntent pendingIntent, PendingIntent pendingIntent2, PendingIntent pendingIntent3, PendingIntent pendingIntent4) {
        if (playStatus == PlayStatus.STATUS_PLAYING) {
            remoteViews.setViewVisibility(R.id.button_play_notification_play, 8);
            remoteViews.setViewVisibility(R.id.button_pause_notification_play, 0);
        } else {
            remoteViews.setViewVisibility(R.id.button_pause_notification_play, 8);
            remoteViews.setViewVisibility(R.id.button_play_notification_play, 0);
        }
        remoteViews.setOnClickPendingIntent(R.id.button_play_notification_play_pause, pendingIntent);
        a(remoteViews, R.id.button_previous_notification_play, pendingIntent2);
        a(remoteViews, R.id.button_next_notification_play, pendingIntent3);
        a(remoteViews, R.id.button_exit_notification_play, pendingIntent4);
    }

    private static void a(RemoteViews remoteViews, int i, PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            remoteViews.setViewVisibility(i, 0);
            remoteViews.setOnClickPendingIntent(i, pendingIntent);
            return;
        }
        remoteViews.setViewVisibility(i, 8);
    }

    private static void a(RemoteViews remoteViews, Bitmap bitmap) {
        if (bitmap == null) {
            remoteViews.setViewVisibility(R.id.imageview_notification_logo, 8);
            remoteViews.setImageViewResource(R.id.imageview_notification_play, R.drawable.img_notification_artist_default);
            return;
        }
        remoteViews.setViewVisibility(R.id.imageview_notification_logo, 0);
        remoteViews.setImageViewBitmap(R.id.imageview_notification_play, bitmap);
    }

    public static void a(int i, Notification notification) {
        ((NotificationManager) BaseApplication.e().getSystemService("notification")).notify(i, notification);
    }

    public static void a(int i) {
        try {
            ((NotificationManager) BaseApplication.e().getSystemService("notification")).cancel(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a() {
        a(15121750);
        a(15121730);
        a(15121740);
    }
}
