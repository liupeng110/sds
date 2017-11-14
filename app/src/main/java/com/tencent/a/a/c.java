package com.tencent.a.a;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Environment;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.tencent.a.b.b.a;
import com.tencent.a.b.d;
import java.io.File;

/* ProGuard */
public class c implements OnSharedPreferenceChangeListener {
    protected static final h a;
    protected static final h b;
    protected a c;
    private volatile boolean d = false;
    private volatile boolean e = true;
    private volatile boolean f = true;

    public static File a() {
        Object obj = null;
        String str = a.a + File.separator + com.tencent.a.b.a.b();
        d b = com.tencent.a.b.c.b();
        if (b != null && b.c() > 8388608) {
            obj = 1;
        }
        if (obj != null) {
            return new File(Environment.getExternalStorageDirectory(), str);
        }
        return new File(com.tencent.a.b.a.c(), str);
    }

    static {
        File a = a();
        a = new h(a, 24, AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START, 8192, "OpenSDK.Client.File.Tracer", 10000, 10, ".app.log", 604800000);
        b = new h(a, 24, AccessibilityEventCompat.TYPE_GESTURE_DETECTION_START, 8192, "OpenSDK.File.Tracer", 10000, 10, ".OpenSDK.log", 604800000);
    }

    public void b() {
        if (this.c != null) {
            this.c.a();
            this.c.b();
        }
    }

    public void a(int i, String str, String str2, Throwable th) {
        if (c()) {
            if (d()) {
                if (this.c != null) {
                    this.c.b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
                } else {
                    return;
                }
            }
            if (e()) {
                f.a.b(i, Thread.currentThread(), System.currentTimeMillis(), str, str2, th);
            }
        }
    }

    public final boolean c() {
        return this.d;
    }

    public final boolean d() {
        return this.e;
    }

    public final boolean e() {
        return this.f;
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("debug.file.tracelevel".equals(str)) {
            int i = sharedPreferences.getInt("debug.file.tracelevel", 63);
            a(8, "WnsTracer", "File Trace Level Changed = " + i, null);
            this.c.a(i);
        }
    }
}
