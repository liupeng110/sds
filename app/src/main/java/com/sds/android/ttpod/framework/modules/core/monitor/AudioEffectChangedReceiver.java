package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;

public class AudioEffectChangedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (Action.AUDIOEFFECT_CHANGED.equals(intent.getAction())) {
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.AUDIOEFFECT_CHANGED, new Object[0]), c.MONITOR);
        }
    }

    static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.AUDIOEFFECT_CHANGED);
        return intentFilter;
    }
}
