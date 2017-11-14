package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import java.lang.reflect.Method;
import java.util.Map;

/* MonitorModule */
public final class b extends com.sds.android.ttpod.framework.base.b {
    private SDCardMountReceiver a;
    private SystemMediaScanStartedReceiver b;
    private NetworkTypeChangeReceiver c;
    private PushClientIdReceiver d;
    private CallStateReceiver e;
    private a f;
    private SearchEventReceiver g;
    private DownloadStateReceiver h;
    private HeadsetPlugReceiver i;
    private AudioEffectChangedReceiver j;
    private PhoneStateListener k = new PhoneStateListener(this) {
        final /* synthetic */ b a;
        private int b = -1;

        {
            this.a = r2;
        }

        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            if (i != this.b) {
                g.a("MonitorModule", "onCallStateChanged");
                this.b = i;
                switch (i) {
                    case 1:
                        b.sContext.sendBroadcast(new Intent(Action.CALL_STATE_RINGING));
                        return;
                    default:
                        return;
                }
            }
        }
    };

    protected c id() {
        return c.MONITOR;
    }

    public void onCreate() {
        super.onCreate();
        this.a = new SDCardMountReceiver();
        sContext.registerReceiver(this.a, SDCardMountReceiver.a());
        this.b = new SystemMediaScanStartedReceiver();
        sContext.registerReceiver(this.b, SystemMediaScanStartedReceiver.a());
        this.c = new NetworkTypeChangeReceiver();
        sContext.registerReceiver(this.c, NetworkTypeChangeReceiver.a());
        this.d = new PushClientIdReceiver();
        sContext.registerReceiver(this.d, PushClientIdReceiver.a());
        this.e = new CallStateReceiver(this.k);
        sContext.registerReceiver(this.e, CallStateReceiver.a());
        this.f = new a();
        sContext.registerReceiver(this.f, a.a());
        this.g = new SearchEventReceiver();
        sContext.registerReceiver(this.g, SearchEventReceiver.a());
        this.h = new DownloadStateReceiver();
        sContext.registerReceiver(this.h, DownloadStateReceiver.a());
        this.i = new HeadsetPlugReceiver();
        sContext.registerReceiver(this.i, HeadsetPlugReceiver.a());
        this.j = new AudioEffectChangedReceiver();
        sContext.registerReceiver(this.j, AudioEffectChangedReceiver.a());
        b();
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
    }

    public void onDestroy() {
        super.onDestroy();
        sContext.unregisterReceiver(this.b);
        sContext.unregisterReceiver(this.a);
        sContext.unregisterReceiver(this.c);
        sContext.unregisterReceiver(this.d);
        sContext.unregisterReceiver(this.e);
        sContext.unregisterReceiver(this.f);
        sContext.unregisterReceiver(this.g);
        sContext.unregisterReceiver(this.h);
        sContext.unregisterReceiver(this.i);
        sContext.unregisterReceiver(this.j);
        c();
    }

    private void b() {
        ((TelephonyManager) sContext.getSystemService("phone")).listen(this.k, 32);
    }

    private void c() {
        ((TelephonyManager) sContext.getSystemService("phone")).listen(this.k, 0);
    }
}
