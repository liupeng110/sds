package c.a;

import android.content.Context;
import android.telephony.TelephonyManager;

/* ImeiTracker */
public class bw extends a {
    private Context a;

    public bw(Context context) {
        super("imei");
        this.a = context;
    }

    public String f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.a.getSystemService("phone");
        if (telephonyManager == null) {
        }
        try {
            if (ah.a(this.a, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
