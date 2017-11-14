package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.igexin.sdk.PushConsts;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.lib.a.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.support.SupportService;

public class NetworkBroadcast extends BroadcastReceiver {
    private static final String a = NetworkBroadcast.class.getSimpleName();
    private SupportService b;

    public NetworkBroadcast(SupportService supportService) {
        this.b = supportService;
    }

    public void onReceive(Context context, Intent intent) {
        if (c.e() && e.f()) {
            c();
            e.a(context);
            b();
        }
    }

    private void b() {
        boolean g = e.g();
        HttpClientProxy.instance().setIsCalculateFlow(g);
        a.b(g);
    }

    private void c() {
        if (this.b != null) {
            String str = e.e() ? com.sds.android.ttpod.framework.modules.h.c.PROXY_WAP_HOST : com.sds.android.ttpod.framework.modules.h.c.PROXY_HOST;
            boolean d = e.d();
            g.a(a, "network change check unicom flow status isUseProxy:" + d);
            this.b.a(str, com.sds.android.ttpod.framework.modules.h.c.HTTP_PROXY_PORT.intValue(), com.sds.android.ttpod.framework.modules.h.c.TCP_PROXY_PORT.intValue(), com.sds.android.ttpod.framework.modules.h.c.USERNAME, com.sds.android.ttpod.framework.modules.h.c.PASSWORD, d);
        }
    }

    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
        return intentFilter;
    }
}
