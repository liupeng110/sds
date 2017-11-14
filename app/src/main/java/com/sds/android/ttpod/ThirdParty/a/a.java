package com.sds.android.ttpod.ThirdParty.a;

import java.lang.reflect.Method;

/* InstanceUtils */
public class a {
    public static <T> T a(String str) {
        T t = null;
        try {
            Class cls = Class.forName(str);
            try {
                Method declaredMethod = cls.getDeclaredMethod("getInstance", new Class[0]);
                if (declaredMethod != null) {
                    t = declaredMethod.invoke(null, null);
                    return t;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            t = cls.newInstance();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return t;
    }
}
