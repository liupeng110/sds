package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.a.b;

/* LockScreenReceiver */
class a extends BroadcastReceiver {
    a() {
    }

    public void onReceive(Context context, Intent intent) {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.RECEIVED_LOCK_SCREEN_ACTION, intent));
    }

    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction(Action.STOP_LOCK_SCREEN);
        intentFilter.addAction(Action.START_ENTRY);
        intentFilter.addAction(Action.CALL_STATE_RINGING);
        return intentFilter;
    }
}
