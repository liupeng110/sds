package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.sdk.core.statistic.SSystemEvent;

public class HeadsetPlugMonitor extends BroadcastReceiver {
    private static final long a = System.currentTimeMillis();
    private a b;

    public interface a {
        void c();

        void d();
    }

    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(Entry.MAX_LIMIT);
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.media.AUDIO_BECOMING_NOISY");
        return intentFilter;
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (this.b != null) {
            if ("android.intent.action.HEADSET_PLUG".equals(action)) {
                if (System.currentTimeMillis() - a <= 1500) {
                    return;
                }
                if (intent.getIntExtra("state", -1) == 1) {
                    this.b.d();
                    a("headset_plugged");
                    return;
                }
                this.b.c();
                a("headset_unplugged");
            } else if ("android.media.AUDIO_BECOMING_NOISY".equals(action)) {
                this.b.c();
                a("headset_unplugged");
            }
        }
    }

    private void a(String str) {
        new SSystemEvent("SYS_HEADSET", str).post();
    }
}
