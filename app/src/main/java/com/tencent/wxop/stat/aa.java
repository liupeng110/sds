package com.tencent.wxop.stat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class aa extends BroadcastReceiver {
    final /* synthetic */ h cm;

    aa(h hVar) {
        this.cm = hVar;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.cm.be != null) {
            this.cm.be.a(new af(this));
        }
    }
}
