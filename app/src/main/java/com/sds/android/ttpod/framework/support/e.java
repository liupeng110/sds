package com.sds.android.ttpod.framework.support;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Process;
import com.sds.android.sdk.lib.util.m;
import java.util.List;

/* SupportFactory */
public class e {
    private static c a;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (e.class) {
            if (a != null) {
                cVar = a;
            } else {
                if (m.a("com.sds.android.ttpod.main", b(context))) {
                    a = new a(context);
                } else {
                    a = new c(context);
                }
                cVar = a;
            }
        }
        return cVar;
    }

    private static String b(Context context) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }
}
