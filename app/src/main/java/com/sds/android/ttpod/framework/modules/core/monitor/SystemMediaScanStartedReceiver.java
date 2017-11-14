package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.cloudapi.ttpod.data.FeedbackMessage;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.Set;

public class SystemMediaScanStartedReceiver extends BroadcastReceiver {
    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addDataScheme(FeedbackMessage.TYPE_FILE);
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        g.c("SystemMediaScanStartedReceiver", "receive:" + intent.getAction());
        if ("android.intent.action.MEDIA_SCANNER_STARTED".equals(intent.getAction())) {
            try {
                Set k = b.k();
                if (k != null && k.size() > 0) {
                    com.sds.android.ttpod.framework.base.a.b.a().a(new a(com.sds.android.ttpod.framework.modules.a.START_SCAN, k, MediaStorage.GROUP_ID_ALL_LOCAL));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
