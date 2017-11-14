package com.igexin.sdk;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import com.igexin.push.a.l;
import com.igexin.push.core.g;
import com.igexin.sdk.a.a;
import com.igexin.sdk.a.d;

public class PushService extends Service {
    private static String a = "PushSdk";
    private IPushCore b;
    private boolean c = false;

    public IBinder onBind(Intent intent) {
        return this.b != null ? this.b.onServiceBind(intent) : null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        if (this.b != null) {
            this.b.onServiceDestroy();
        }
        Process.killProcess(Process.myPid());
        super.onDestroy();
    }

    public void onLowMemory() {
        super.onLowMemory();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        if (!this.c) {
            this.c = true;
            if (intent != null) {
                String stringExtra = intent.getStringExtra(PushConsts.CMD_ACTION);
                if (!(PushConsts.ACTION_SERVICE_INITIALIZE.equals(stringExtra) || PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE.equals(stringExtra))) {
                    l.a((Context) this);
                    if ("1".equals(g.c().get("ss")) && !new d(this).c()) {
                        stopSelf();
                        return 1;
                    }
                }
            }
            a.a().a((Context) this);
            this.b = a.a().b();
            this.b.start(this);
        }
        return this.b != null ? this.b.onServiceStartCommand(intent, i, i2) : 1;
    }
}
