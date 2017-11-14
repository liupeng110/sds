package com.sds.android.ttpod.component.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.EnvironmentUtils.CPU;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.a.e;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;
import java.io.File;

/* StormPlayer */
public class b implements e {
    private static String a;

    public void a(Context context, String str, String str2) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.storm.smart");
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setData(Uri.parse(str));
            launchIntentForPackage.setAction("android.intent.action.VIEW");
            launchIntentForPackage.setFlags(32768);
            Bundle bundle = new Bundle();
            bundle.putString("title", str2);
            launchIntentForPackage.putExtras(bundle);
            ((Activity) context).startActivityForResult(launchIntentForPackage, 1);
            g.a("StormPlayer", "storm play......");
        }
    }

    public static boolean a(Context context) {
        return a(context, "com.storm.smart") >= 60;
    }

    public void a() {
        String c = c();
        if (!m.a(c)) {
            a(c);
        }
    }

    public String b() {
        return "com.storm.smart";
    }

    private static String c() {
        if (CPU.cpuFamily() != 1) {
            return "";
        }
        if ((CPU.cpuFeatures() & 4) != 0) {
            return "neon";
        }
        return "v" + CPU.armArch();
    }

    private static void a(String str) {
        if (!m.a(a)) {
            String replace = a.replace("AndroidStorm_", "AndroidStorm_" + str);
            DownloadTaskInfo a = e.a(replace, a.x() + File.separator + com.sds.android.sdk.lib.util.e.j(replace), Long.valueOf(0), "StormPlugin", DownloadTaskInfo.TYPE_APP, Boolean.valueOf(true), "stormvideo");
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_DOWNLOAD_TASK, a));
        }
    }

    private static int a(Context context, String str) {
        int i;
        Exception e;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            String str2 = packageInfo.versionName;
            i = packageInfo.versionCode;
            try {
                g.a("StormPlayer", "version name:" + str2 + " code: " + i);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return i;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            i = 0;
            e = exception;
            e.printStackTrace();
            return i;
        }
        return i;
    }
}
