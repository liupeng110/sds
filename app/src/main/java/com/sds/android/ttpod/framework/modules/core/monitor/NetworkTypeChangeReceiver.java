package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.igexin.sdk.PushConsts;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;

public class NetworkTypeChangeReceiver extends BroadcastReceiver {
    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        if (PushConsts.ACTION_BROADCAST_NETWORK_CHANGE.equals(intent.getAction())) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.NET_WORK_TYPE_CHANGED, new Object[0]), c.MONITOR);
        }
    }
}
