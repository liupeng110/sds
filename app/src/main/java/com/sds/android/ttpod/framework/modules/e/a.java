package com.sds.android.ttpod.framework.modules.e;

import android.content.Intent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.base.Action;
import com.sds.android.ttpod.framework.base.b;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.player.PlayStatus;
import java.lang.reflect.Method;
import java.util.Map;

/* LockScreenModule */
public class a extends b {
    private static final String a = a.class.getSimpleName();
    private boolean b = false;

    protected c id() {
        return c.LOCK_SCREEN;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.START_LOCK_SCREEN, i.a(cls, "startLockScreen", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.STOP_LOCK_SCREEN, i.a(cls, "stopLockScreen", new Class[0]));
        map.put(com.sds.android.ttpod.framework.modules.a.RECEIVED_LOCK_SCREEN_ACTION, i.a(cls, "onReceive", Intent.class));
    }

    public void startLockScreen() {
        if (com.sds.android.ttpod.framework.storage.environment.b.j(isAllowDefaultLockScreen()) && PlayStatus.STATUS_PLAYING == e.a(sContext).n()) {
            sContext.startActivity(new Intent(Action.LOCK_SCREEN_ACTIVITY_ENTRY).setFlags(268697600));
            this.b = true;
        }
    }

    public void stopLockScreen() {
        if (this.b) {
            stopSystemLock();
            this.b = false;
            g.d(a, "stopLockScreen looklockscreen statistic");
            l.b();
        }
    }

    public void stopSystemLock() {
        try {
            if (!j.e()) {
                b.a();
                b.a(new com.sds.android.ttpod.framework.modules.e.b.a(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        g.c(a.a, "onReceive unlock success!");
                    }
                });
            }
        } catch (Throwable th) {
            g.c(a, "onReceive Throwable = " + th.toString());
        }
    }

    public static boolean isAllowDefaultLockScreen() {
        String k = com.sds.android.sdk.lib.util.EnvironmentUtils.a.k();
        if (!m.a(k)) {
            String[] split = k.split("_");
            String b = com.sds.android.sdk.lib.util.EnvironmentUtils.a.b();
            if (!(split == null || m.a(b))) {
                for (String equals : split) {
                    if (equals.equals(b)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void onReceive(Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)) {
            startLockScreen();
        } else if ("android.intent.action.SCREEN_ON".equals(action) && this.b) {
            stopLockScreen();
            this.b = false;
        } else if (Action.START_ENTRY.equals(action) || Action.CALL_STATE_RINGING.equals(action) || Action.STOP_LOCK_SCREEN.equals(action)) {
            stopLockScreen();
        }
    }
}
