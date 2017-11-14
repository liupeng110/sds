package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;

public class HeadsetPlugReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Action.PLAY_HEADSET_PLUG.equals(action)) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_HEADSET_PLUGGED, new Object[0]), c.MONITOR);
        } else if (Action.PLAY_HEADSET_UNPLUG.equals(action)) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.UPDATE_HEADSET_UNPLUGGED, new Object[0]), c.MONITOR);
        }
    }

    static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.PLAY_HEADSET_PLUG);
        intentFilter.addAction(Action.PLAY_HEADSET_UNPLUG);
        return intentFilter;
    }
}
