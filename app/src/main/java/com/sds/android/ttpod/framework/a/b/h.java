package com.sds.android.ttpod.framework.a.b;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.framework.a.a.b;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

/* ExceptionUtils */
public class h {
    private static SharedPreferences a;

    @TargetApi(8)
    public static void a(final Context context) {
        if (!j.a() || !ActivityManager.isUserAMonkey()) {
            a = context.getSharedPreferences("preference_exception", 0);
            Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                public void uncaughtException(Thread thread, Throwable th) {
                    b.b("crash", " --> " + a.a().d());
                    Writer stringWriter = new StringWriter();
                    th.printStackTrace(new PrintWriter(stringWriter));
                    String obj = stringWriter.toString();
                    Log.e("ExceptionReporter", "TTPod_Crash_Exception:\n" + obj);
                    if (context == null || !c.e()) {
                        h.c(context);
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    long j = h.a.getLong("last_crash_time", 0);
                    h.a.edit().putLong("last_crash_time", currentTimeMillis).commit();
                    if (currentTimeMillis - j < 8000) {
                        h.c(context);
                        return;
                    }
                    Intent intent = new Intent(Action.EXCEPTION_REPORT);
                    intent.putExtra("android.intent.extra.SUBJECT", th.toString());
                    intent.putExtra("android.intent.extra.TEXT", obj);
                    intent.putExtra("extra_default_logs", b.a());
                    intent.putExtra("extra_life_circles", b.b());
                    intent.addFlags(335544320);
                    context.startActivity(intent);
                    System.exit(0);
                }
            });
        }
    }

    private static void c(Context context) {
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.EXIT, new Object[0]));
        context.sendBroadcast(new Intent(Action.EXIT));
        if (BaseApplication.e().f()) {
            System.exit(0);
        }
    }
}
