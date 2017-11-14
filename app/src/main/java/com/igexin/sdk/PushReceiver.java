package com.igexin.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.igexin.push.core.a.f;
import com.igexin.sdk.a.d;

public class PushReceiver extends BroadcastReceiver {
    private static String a = "PushSdk";

    public void onReceive(Context context, Intent intent) {
        String g = f.a().g("ss");
        if (g == null || !g.equals("1") || new d(context).c()) {
            g = intent.getAction();
            if (PushConsts.ACTION_BROADCAST_PUSHMANAGER.equals(g)) {
                Intent intent2 = new Intent(context.getApplicationContext(), PushService.class);
                intent2.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_BROADCAST_PUSHMANAGER);
                intent2.putExtra("bundle", intent.getExtras());
                context.getApplicationContext().startService(intent2);
            }
            if (PushConsts.ACTION_BROADCAST_REFRESHLS.equals(g)) {
                String stringExtra = intent.getStringExtra("callback_pkgname");
                String stringExtra2 = intent.getStringExtra("callback_classname");
                Intent intent3 = new Intent(context.getApplicationContext(), PushService.class);
                intent3.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_BROADCAST_REFRESHLS);
                intent3.putExtra("callback_pkgname", stringExtra);
                intent3.putExtra("callback_classname", stringExtra2);
                context.getApplicationContext().startService(intent3);
            }
            if (PushConsts.ACTION_BROADCAST_TO_BOOT.equals(g)) {
                context.startService(new Intent(context.getApplicationContext(), PushService.class));
            } else if (PushConsts.ACTION_BROADCAST_NETWORK_CHANGE.equals(g)) {
                r0 = new Intent(context.getApplicationContext(), PushService.class);
                r0.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
                context.startService(r0);
            } else if (PushConsts.ACTION_BROADCAST_USER_PRESENT.equals(g)) {
                r0 = new Intent(context.getApplicationContext(), PushService.class);
                r0.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_BROADCAST_USER_PRESENT);
                context.startService(r0);
            }
        }
    }
}
