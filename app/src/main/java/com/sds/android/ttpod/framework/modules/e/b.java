package com.sds.android.ttpod.framework.modules.e;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.KeyguardManager.OnKeyguardExitResult;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a.s;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* SystemLockManager */
public class b {
    private static final String a = b.class.getSimpleName();
    private static KeyguardManager b = null;
    private static KeyguardLock c = null;

    /* SystemLockManager */
    public interface a {
        void a();
    }

    private static synchronized void f() {
        synchronized (b.class) {
            if (b == null) {
                b = (KeyguardManager) BaseApplication.e().getSystemService("keyguard");
            }
        }
    }

    public static synchronized void a() {
        synchronized (b.class) {
            f();
            if (b.inKeyguardRestrictedInputMode()) {
                c = b.newKeyguardLock(a);
                c.disableKeyguard();
                g.a(a, "--Keyguard disabled");
            } else {
                c = null;
            }
        }
    }

    public static synchronized void b() {
        synchronized (b.class) {
            if (!(b == null || c == null)) {
                c.reenableKeyguard();
                c = null;
            }
        }
    }

    public static synchronized boolean c() {
        boolean inKeyguardRestrictedInputMode;
        synchronized (b.class) {
            if (b != null) {
                inKeyguardRestrictedInputMode = b.inKeyguardRestrictedInputMode();
            } else {
                inKeyguardRestrictedInputMode = false;
            }
        }
        return inKeyguardRestrictedInputMode;
    }

    public static synchronized void a(final a aVar) {
        synchronized (b.class) {
            if (c()) {
                b.exitKeyguardSecurely(new OnKeyguardExitResult() {
                    public void onKeyguardExitResult(boolean z) {
                        b.g();
                        if (z) {
                            g.a(b.a, "--Keyguard exited success");
                            aVar.a();
                            return;
                        }
                        g.a(b.a, "--Keyguard exited failed");
                    }
                });
            } else {
                aVar.a();
            }
        }
    }

    private static void g() {
        if (!s.a()) {
            g.a(a, "reenable keyguard... ");
            b();
        }
    }
}
