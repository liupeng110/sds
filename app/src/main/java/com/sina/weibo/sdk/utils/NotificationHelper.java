package com.sina.weibo.sdk.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import java.io.File;

public class NotificationHelper {
    private static final int NOTIFICATION_ID = 1;
    private static final String WEIBO = "Weibo";
    private static final String WEIBO_ZH_CN = "微博";
    private static final String WEIBO_ZH_TW = "微博";

    public static void showNotification(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            ((NotificationManager) context.getSystemService("notification")).notify(1, buildNotification(context, str, str2));
        }
    }

    private static Notification buildNotification(Context context, String str, String str2) {
        String str3 = "";
        str3 = "";
        CharSequence string = ResourceManager.getString(context, WEIBO, "微博", "微博");
        Builder builder = new Builder(context);
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setLargeIcon(((BitmapDrawable) ResourceManager.getDrawable(context, "ic_com_sina_weibo_sdk_weibo_logo.png")).getBitmap());
        builder.setSmallIcon(getNotificationIcon(context));
        builder.setContentTitle(string);
        builder.setTicker(str);
        builder.setContentText(str);
        builder.setContentIntent(buildInstallApkIntent(context, str2));
        return builder.build();
    }

    private static PendingIntent buildInstallApkIntent(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return PendingIntent.getActivity(context, 0, new Intent(), 16);
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        return PendingIntent.getActivity(context, 0, intent, 16);
    }

    private static int getNotificationIcon(Context context) {
        int resourceId = getResourceId(context, "com_sina_weibo_sdk_weibo_logo", "drawable");
        if (resourceId > 0) {
            return resourceId;
        }
        return 17301659;
    }

    private static int getResourceId(Context context, String str, String str2) {
        String packageName = context.getApplicationContext().getPackageName();
        try {
            return context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(str, str2, packageName);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
