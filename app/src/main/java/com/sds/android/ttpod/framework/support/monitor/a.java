package com.sds.android.ttpod.framework.support.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import com.sds.android.ttpod.framework.modules.core.monitor.CallStateReceiver;

/* CallMonitor */
public class a {
    private final BroadcastReceiver a = new CallStateReceiver(this.b);
    private final PhoneStateListener b = new PhoneStateListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            if (this.a.e != i) {
                this.a.e = i;
                if (this.a.c == null) {
                    return;
                }
                if (i == 0) {
                    this.a.c.a();
                } else {
                    this.a.c.b();
                }
            }
        }
    };
    private a c;
    private boolean d = false;
    private int e = -1;

    /* CallMonitor */
    public interface a {
        void a();

        void b();
    }

    public boolean a(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getCallState() == 0;
    }

    public void a(Context context, a aVar) {
        this.c = aVar;
        if (this.c != null && !this.d) {
            this.d = true;
            context.registerReceiver(this.a, CallStateReceiver.a());
            ((TelephonyManager) context.getSystemService("phone")).listen(this.b, 32);
        } else if (this.c == null && this.d) {
            this.d = false;
            context.unregisterReceiver(this.a);
            ((TelephonyManager) context.getSystemService("phone")).listen(this.b, 0);
            a();
        }
    }

    private void a() {
        this.e = -1;
    }
}
