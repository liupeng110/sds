package com.ut.mini;

import android.app.Activity;

/* UTInterfaceCallDelegate */
public class a {
    public static void a(Activity activity) {
        UTPageHitHelper.getInstance().pageDisAppearByAuto(activity);
    }

    public static void b(Activity activity) {
        UTPageHitHelper.getInstance().pageAppearByAuto(activity);
    }
}
