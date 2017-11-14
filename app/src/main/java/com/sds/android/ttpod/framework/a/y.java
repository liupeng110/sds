package com.sds.android.ttpod.framework.a;

import android.view.View;
import android.view.ViewParent;
import com.sds.android.sdk.lib.util.j;
import java.util.concurrent.TimeUnit;

/* ViewUtils */
public final class y {
    private static long a;
    private static final long b = TimeUnit.MILLISECONDS.toNanos(500);

    public static void a(View view, boolean z) {
        ViewParent parent = view != null ? view.getParent() : null;
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    public static boolean a() {
        long nanoTime = System.nanoTime();
        if (nanoTime - a < b) {
            return true;
        }
        a = nanoTime;
        return false;
    }

    public static void a(long j, View view) {
        if (259200000 + j >= System.currentTimeMillis()) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    public static void a(View view) {
        if (view != null && view.getBackground() != null && !j.f()) {
            view.getBackground().setCallback(null);
        }
    }
}
