package com.igexin.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.igexin.push.core.a.f;
import com.igexin.sdk.a.d;

public class PushManagerReceiver extends BroadcastReceiver {
    private static String a = "PushSdk";

    public void onReceive(Context context, Intent intent) {
        String g = f.a().g("ss");
        if (g == null || !g.equals("1") || new d(context).c()) {
            if (PushConsts.ACTION_BROADCAST_PUSHMANAGER.equals(intent.getAction())) {
                Intent intent2 = new Intent(context.getApplicationContext(), PushService.class);
                intent2.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_BROADCAST_PUSHMANAGER);
                intent2.putExtra("bundle", intent.getExtras());
                context.getApplicationContext().startService(intent2);
            }
        }
    }
}
