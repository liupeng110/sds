package com.ut.mini.core.d;

import android.content.Context;
import com.ut.mini.d.k;

/* UTMCLogAssembleHelper */
public class c {
    private static String a = null;
    private static boolean b = true;

    public static String a(Context context) {
        if (a != null) {
            return a;
        }
        if (context != null && b) {
            try {
                Class cls = Class.forName("com.taobao.dp.DeviceSecuritySDK");
                if (cls != null) {
                    Object a = k.a(cls, "getInstance", new Object[]{context}, Context.class);
                    if (a != null) {
                        Object a2 = k.a(a, "getSecurityToken");
                        if (a2 != null) {
                            a = (String) a2;
                        }
                        return (String) a2;
                    }
                }
                b = false;
            } catch (Exception e) {
            }
        }
        return null;
    }
}
