package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.lib.util.d;

public class LockScreenMonitor extends BroadcastReceiver {
    private a a;

    public interface a {
        void a(int i);
    }

    public LockScreenMonitor(a aVar) {
        d.a((Object) aVar, "screenStateChangeListener");
        this.a = aVar;
    }

    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)) {
            this.a.a(0);
        } else if ("android.intent.action.SCREEN_ON".equals(action)) {
            this.a.a(1);
        }
    }
}
