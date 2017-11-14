package com.sds.android.ttpod.framework.a;

import android.os.Build;
import android.view.View;
import java.lang.reflect.Method;

/* SmartBarUtils */
public class w {
    public static void a(View view) {
        if (a()) {
            try {
                Method method = View.class.getMethod("setSystemUiVisibility", new Class[]{Integer.TYPE});
                Object[] objArr = new Object[1];
                try {
                    objArr[0] = View.class.getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION").get(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                method.invoke(view, objArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static boolean a() {
        try {
            return ((Boolean) Class.forName("android.os.Build").getMethod("hasSmartBar", new Class[0]).invoke(null, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            if (Build.DEVICE.equals("mx2")) {
                return true;
            }
            if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
                return false;
            }
            return false;
        }
    }
}
