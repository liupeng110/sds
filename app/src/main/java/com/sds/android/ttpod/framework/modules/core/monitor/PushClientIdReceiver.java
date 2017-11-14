package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.c;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.storage.environment.b;

public class PushClientIdReceiver extends BroadcastReceiver {
    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Action.PUSH_CLIENT_ID_BROADCAST);
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Action.PUSH_CLIENT_ID_BROADCAST)) {
            String stringExtra = intent.getStringExtra("client_id");
            if (!m.a(stringExtra)) {
                String a = c.a(0, "-");
                if (!m.a(a, b.aF())) {
                    w.a("push", "login", "gexin", 0, 0, stringExtra, null);
                    new SUserEvent("PAGE_CLICK", r.ACTION_PUSH_RECEIVE_CID.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue()).append("cid", stringExtra).post();
                    b.n(a);
                    d.c.a("cid", stringExtra);
                }
                if (!m.a(stringExtra, b.aG())) {
                    b.o(stringExtra);
                }
            }
        }
    }
}
