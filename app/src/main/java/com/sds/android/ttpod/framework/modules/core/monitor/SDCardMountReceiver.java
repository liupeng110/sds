package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a;

public class SDCardMountReceiver extends BroadcastReceiver {
    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addDataScheme(FeedbackMessage.TYPE_FILE);
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            g.c("SDCardMountReceiver", action);
            a.a(true);
        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            g.c("SDCardMountReceiver", action);
        }
    }
}
