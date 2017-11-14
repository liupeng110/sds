package com.sds.android.ttpod.framework.modules.core.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sds.android.sdk.core.statistic.SSystemEvent;

public class CallStateReceiver extends BroadcastReceiver {
    private static final String a = CallStateReceiver.class.getSimpleName();
    private PhoneStateListener b;

    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        return intentFilter;
    }

    public CallStateReceiver(PhoneStateListener phoneStateListener) {
        this.b = phoneStateListener;
    }

    public void onReceive(Context context, Intent intent) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String stringExtra = intent.getStringExtra("android.intent.extra.PHONE_NUMBER");
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = intent.getStringExtra("incoming_number");
        }
        int callState = telephonyManager.getCallState();
        this.b.onCallStateChanged(callState, stringExtra);
        a(callState);
    }

    private void a(int i) {
        new SSystemEvent("SYS_PHONE_STATE", String.valueOf(i)).append("process_id", Integer.valueOf(Process.myPid())).post();
    }
}
