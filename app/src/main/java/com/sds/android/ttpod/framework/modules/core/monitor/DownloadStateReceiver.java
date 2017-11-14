package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.core.download.b.b;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.download.DownloadTaskInfo;

public class DownloadStateReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (Action.DOWNLOAD_TASK_STATE_CHANGED.equals(intent.getAction())) {
            DownloadTaskInfo downloadTaskInfo = (DownloadTaskInfo) intent.getParcelableExtra("download_task");
            Object obj = null;
            int intExtra = intent.getIntExtra("download_error", -1);
            if (intExtra != -1) {
                obj = b.values()[intExtra];
            }
            com.sds.android.ttpod.framework.base.a.b.a().b(new a(com.sds.android.ttpod.framework.modules.a.DOWNLOAD_STATE_CHANGED, downloadTaskInfo, obj), c.MONITOR);
        }
    }

    static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.DOWNLOAD_TASK_STATE_CHANGED);
        return intentFilter;
    }
}
