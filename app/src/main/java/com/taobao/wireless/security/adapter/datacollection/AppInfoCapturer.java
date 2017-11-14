package com.taobao.wireless.security.adapter.datacollection;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class AppInfoCapturer {
    private static Context a;

    private AppInfoCapturer() {
    }

    public static String getAppVersion() {
        Context context = a;
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                if (packageInfo != null) {
                    String str = packageInfo.versionName;
                    if (!(str == null || str.length() == 0)) {
                        return str;
                    }
                }
            } catch (NameNotFoundException e) {
            }
        }
        return null;
    }

    public static String getPackageName() {
        Context context = a;
        if (context != null) {
            String packageName = context.getPackageName();
            if (!(packageName == null || packageName.length() == 0)) {
                return packageName;
            }
        }
        return null;
    }

    public static void initialize(Context context) {
        a = context;
    }
}
