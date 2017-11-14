package com.ut.mini.c;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import com.ut.mini.UTAnalytics;
import com.ut.mini.UTTracker;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.base.c;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import com.ut.mini.plugin.UTMCPlugin;
import java.util.List;
import java.util.Map;

/* UTMI1010_2001Event */
public class a extends UTMCPlugin implements com.ut.mini.core.a.a {
    private long a = 0;
    private long b = 0;

    public int[] returnRequiredMsgIds() {
        return new int[]{3};
    }

    public void onPluginMsgArrivedFromSDK(int i, Object obj) {
        if (i == 3) {
            Map map = (Map) obj;
            if (map.containsKey(UTLogFieldsScheme.EVENTID.toString())) {
                if ("2001".equals((String) map.get(UTLogFieldsScheme.EVENTID.toString()))) {
                    long parseLong;
                    if (map.containsKey(UTLogFieldsScheme.ARG3.toString())) {
                        try {
                            parseLong = Long.parseLong((String) map.get(UTLogFieldsScheme.ARG3.toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        this.a = parseLong + this.a;
                        if (a()) {
                            a(this.a);
                            this.a = 0;
                        }
                    }
                    parseLong = 0;
                    this.a = parseLong + this.a;
                    if (a()) {
                        a(this.a);
                        this.a = 0;
                    }
                }
            }
        }
    }

    private static boolean a() {
        try {
            Context k = c.a().k();
            if (k != null) {
                String packageName = k.getPackageName();
                if (packageName != null) {
                    ActivityManager activityManager = (ActivityManager) k.getSystemService("activity");
                    if (activityManager != null) {
                        try {
                            List runningTasks = activityManager.getRunningTasks(1);
                            if (runningTasks != null && runningTasks.size() > 0) {
                                ComponentName componentName = ((RunningTaskInfo) runningTasks.get(0)).topActivity;
                                if (componentName != null && packageName.contains(componentName.getPackageName())) {
                                    return false;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    private static void a(long j) {
        if (j > 0) {
            UTOriginalCustomHitBuilder uTOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder("UT", 1010, "" + j, null, null, null);
            UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send(uTOriginalCustomHitBuilder.build());
            } else {
                com.ut.mini.b.a.c(1, "Record app display event error", "Fatal Error,must call setRequestAuthentication method first.");
            }
        }
    }

    public void b() {
        a(SystemClock.elapsedRealtime() - this.b);
    }

    public void c() {
        this.b = SystemClock.elapsedRealtime();
    }

    public void a(Activity activity, Bundle bundle) {
    }

    public void a(Activity activity) {
    }

    public void b(Activity activity) {
        com.ut.mini.a.a(activity);
    }

    public void c(Activity activity) {
        com.ut.mini.a.b(activity);
    }

    public void b(Activity activity, Bundle bundle) {
    }
}
