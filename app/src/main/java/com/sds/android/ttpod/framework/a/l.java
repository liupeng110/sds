package com.sds.android.ttpod.framework.a;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.j;

@SuppressLint({"NewApi"})
/* NotificationBuilder */
public class l {
    private static boolean a = false;
    private static int b;
    private static int c;
    private final Object A;
    private Context d;
    private long e = System.currentTimeMillis();
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private CharSequence k;
    private CharSequence l;
    private PendingIntent m;
    private RemoteViews n;
    private PendingIntent o;
    private PendingIntent p;
    private CharSequence q;
    private RemoteViews r;
    private Uri s;
    private int t = -1;
    private long[] u;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    public l(Context context) {
        this.d = context;
        if (j.c()) {
            this.A = new Builder(context);
        } else {
            this.A = null;
        }
        a(context);
    }

    public l a(long j) {
        this.e = j;
        if (this.A != null) {
            ((Builder) this.A).setWhen(j);
        }
        return this;
    }

    public l a(int i) {
        this.h = i;
        if (this.A != null) {
            ((Builder) this.A).setSmallIcon(i);
        }
        return this;
    }

    public l b(int i) {
        this.f = i;
        return this;
    }

    public l a(CharSequence charSequence) {
        this.k = charSequence;
        if (this.A != null) {
            ((Builder) this.A).setContentTitle(charSequence);
        }
        return this;
    }

    public l b(CharSequence charSequence) {
        this.l = charSequence;
        if (this.A != null) {
            ((Builder) this.A).setContentText(charSequence);
        }
        return this;
    }

    public l a(RemoteViews remoteViews) {
        this.n = remoteViews;
        if (this.A != null) {
            ((Builder) this.A).setContent(remoteViews);
        }
        return this;
    }

    public l a(PendingIntent pendingIntent) {
        this.m = pendingIntent;
        if (this.A != null) {
            ((Builder) this.A).setContentIntent(pendingIntent);
        }
        return this;
    }

    public Notification a() {
        Notification notification;
        if (this.A != null) {
            notification = ((Builder) this.A).getNotification();
        } else {
            notification = d();
        }
        if (this.f != 0) {
            notification.icon = this.f;
            notification.iconLevel = this.g;
        }
        return notification;
    }

    private Notification d() {
        Notification notification = new Notification();
        notification.setLatestEventInfo(this.d, this.k, this.l, this.m);
        notification.when = this.e;
        notification.number = this.j;
        notification.icon = this.h;
        notification.iconLevel = this.i;
        notification.tickerText = this.q;
        if (this.n != null) {
            notification.contentView = this.n;
        }
        if (this.r != null) {
            notification.tickerView = this.r;
        }
        notification.deleteIntent = this.o;
        if (j.b()) {
            notification.fullScreenIntent = this.p;
        }
        notification.sound = this.s;
        notification.audioStreamType = this.t;
        notification.vibrate = this.u;
        notification.ledARGB = this.v;
        notification.ledOnMS = this.w;
        notification.ledOffMS = this.x;
        notification.defaults = this.y;
        notification.flags = this.z;
        if (!(this.w == 0 || this.x == 0)) {
            notification.flags |= 1;
        }
        if ((this.y & 4) != 0) {
            notification.flags |= 1;
        }
        return notification;
    }

    private static void a(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (viewGroup.getChildAt(i) instanceof TextView) {
                TextView textView = (TextView) viewGroup.getChildAt(i);
                String charSequence = textView.getText().toString();
                if ("{notification_title_test_tag}".equals(charSequence)) {
                    b = textView.getTextColors().getDefaultColor();
                } else if ("{notification_text_test_tag}".equals(charSequence)) {
                    c = textView.getTextColors().getDefaultColor();
                }
            } else if (viewGroup.getChildAt(i) instanceof ViewGroup) {
                a((ViewGroup) viewGroup.getChildAt(i));
            }
        }
    }

    private static void a(Context context) {
        if (!a) {
            try {
                Notification notification = new Notification();
                notification.setLatestEventInfo(context, "{notification_title_test_tag}", "{notification_text_test_tag}", null);
                ViewGroup linearLayout = new LinearLayout(context);
                a((ViewGroup) notification.contentView.apply(context, linearLayout));
                linearLayout.removeAllViews();
                a = true;
            } catch (Exception e) {
                a = false;
            }
        }
    }

    public static int b() {
        return b;
    }

    public static int c() {
        return c;
    }
}
