package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class p extends BroadcastReceiver {
    private /* synthetic */ n a;

    p(n nVar) {
        this.a = nVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (!intent.getBooleanExtra("noConnectivity", false) && this.a.q != null) {
            this.a.q.sendEmptyMessage(256);
        }
    }
}
