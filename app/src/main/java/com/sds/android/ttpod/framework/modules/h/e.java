package com.sds.android.ttpod.framework.modules.h;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.storage.a.a;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/* UnicomFlowUtil */
public class e {
    private static final String a = c.class.getSimpleName();

    public static boolean a() {
        boolean G = a.a().G();
        boolean H = a.a().H();
        boolean f = f();
        g.a(a, "unicom flow isUnicomFlowEnable enable:" + G + "  usable:" + H + "  is unicom sdcard:" + f);
        return G && H && f;
    }

    public static boolean b() {
        return a.a().y() != d.OPEN.ordinal() && a();
    }

    public static boolean c() {
        int y = a.a().y();
        int x = a.a().x();
        g.a(a, "is need use proxy open status:" + y + "  trial status:" + x);
        return y == d.OPEN.ordinal() || y == d.UNSUBSCRIBE.ordinal() || x == a.TRIAL.ordinal();
    }

    public static String a(String str, String str2) {
        String str3 = "MzAwMDAwNDU1MDpCREFBQUQ5QjczOUQzQjNG";
        try {
            if (j.a()) {
                str3 = Base64.encodeToString((str + ":" + str2).getBytes(), 0).trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str3;
    }

    public static double a(long j) {
        return a(((double) j) / 1048576.0d);
    }

    public static double a(double d) {
        return new BigDecimal(d).setScale(2, 4).doubleValue();
    }

    public static boolean d() {
        return h() && c();
    }

    public static boolean e() {
        return c.d() == 1;
    }

    public static boolean f() {
        g.a(a, "imsi" + c.b());
        return c.b().startsWith("46001") || c.b().startsWith("46006");
    }

    public static boolean g() {
        g.a(a, "is use gprs network type:" + c.d());
        if (c.d() == 0 || 3 == c.d() || 1 == c.d() || 4 == c.d()) {
            return true;
        }
        return false;
    }

    public static boolean h() {
        return a() && g();
    }

    public static int i() {
        Calendar instance = Calendar.getInstance();
        return instance.getActualMaximum(5) - instance.get(5);
    }

    public static boolean j() {
        return Calendar.getInstance().get(5) >= 20;
    }

    public static boolean k() {
        return Calendar.getInstance().get(5) < 15;
    }

    public static String l() {
        String z = a.a().z();
        if (m.a(z)) {
            return m();
        }
        return z;
    }

    public static String m() {
        String line1Number;
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) BaseApplication.e().getSystemService("phone");
        if (telephonyManager != null) {
            line1Number = telephonyManager.getLine1Number();
            if (!m.a(line1Number) && line1Number.length() > 11) {
                line1Number = line1Number.substring(line1Number.length() - 11);
            }
        } else {
            line1Number = str;
        }
        return (m.a(line1Number) || "00000000000".equals(line1Number)) ? "" : line1Number;
    }

    public static void a(boolean z, String str) {
        Context e = BaseApplication.e();
        final NotificationManager notificationManager = (NotificationManager) e.getSystemService("notification");
        notificationManager.cancel(1000);
        Notification notification = new Notification();
        PendingIntent activity = PendingIntent.getActivity(e, 0, new Intent(), 0);
        if (z) {
            notification.icon = R.drawable.img_notification_unicom_open;
        } else {
            notification.icon = R.drawable.img_notification_unicom_close;
        }
        notification.tickerText = str;
        notification.when = System.currentTimeMillis();
        notification.setLatestEventInfo(e, "联通流量畅听", str, activity);
        notification.flags |= 16;
        notificationManager.notify(1000, notification);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                notificationManager.cancel(1000);
            }
        }, 5000);
    }

    public static void n() {
        ((NotificationManager) BaseApplication.e().getSystemService("notification")).cancel(1000);
    }

    public static void a(Context context) {
        if (d()) {
            a(com.sds.android.sdk.lib.a.a.c(), context.getString(com.sds.android.sdk.lib.a.a.c() ? R.string.unicom_flow_open : R.string.unicom_flow_close));
        }
    }

    public static void b(Context context) {
        if (com.sds.android.sdk.lib.a.a.c()) {
            a(com.sds.android.sdk.lib.a.a.c(), context.getString(R.string.unicom_flow_close));
        }
    }

    public static Date o() {
        Calendar instance = Calendar.getInstance();
        instance.set(1, 2100);
        return instance.getTime();
    }

    public static void p() {
        a.a().a(r());
    }

    public static void q() {
        a.a().c(r());
    }

    private static Date r() {
        Calendar instance = Calendar.getInstance();
        instance.add(2, 1);
        instance.set(5, 1);
        instance.set(10, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        return instance.getTime();
    }
}
