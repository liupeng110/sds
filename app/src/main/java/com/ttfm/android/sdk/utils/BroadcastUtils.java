package com.ttfm.android.sdk.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

public class BroadcastUtils {
    public static void registBroadcastReceiver(Context context, String[] strArr, BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            IntentFilter intentFilter = new IntentFilter();
            for (String addAction : strArr) {
                intentFilter.addAction(addAction);
            }
            LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public static void unregistBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
            }
        }
    }

    public static void sendBroadcast(Context context, String str, Bundle bundle) {
        Intent intent = new Intent(str);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
