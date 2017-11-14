package com.ut.mini.core.a;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(14)
/* UTMCAppStatusMonitor */
public class b implements ActivityLifecycleCallbacks {
    private static b h = null;
    private int a = 0;
    private boolean b = false;
    private TimerTask c;
    private Object d = new Object();
    private Timer e = null;
    private List<a> f = new LinkedList();
    private Object g = new Object();

    /* UTMCAppStatusMonitor */
    private class a extends TimerTask {
        final /* synthetic */ b a;

        private a(b bVar) {
            this.a = bVar;
        }

        public void run() {
            this.a.b = false;
            synchronized (this.a.g) {
                for (a b : this.a.f) {
                    b.b();
                }
            }
        }
    }

    private b() {
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (h == null) {
                h = new b();
            }
            bVar = h;
        }
        return bVar;
    }

    public void a(a aVar) {
        if (aVar != null) {
            synchronized (this.g) {
                this.f.add(aVar);
            }
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            synchronized (this.g) {
                this.f.remove(aVar);
            }
        }
    }

    private void b() {
        synchronized (this.d) {
            if (this.e != null) {
                this.e.cancel();
                this.e = null;
            }
        }
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
        synchronized (this.g) {
            for (a a : this.f) {
                a.a(activity, bundle);
            }
        }
    }

    public void onActivityDestroyed(Activity activity) {
        synchronized (this.g) {
            for (a a : this.f) {
                a.a(activity);
            }
        }
    }

    public void onActivityPaused(Activity activity) {
        synchronized (this.g) {
            for (a b : this.f) {
                b.b(activity);
            }
        }
    }

    public void onActivityResumed(Activity activity) {
        synchronized (this.g) {
            for (a c : this.f) {
                c.c(activity);
            }
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        synchronized (this.g) {
            for (a b : this.f) {
                b.b(activity, bundle);
            }
        }
    }

    public void onActivityStarted(Activity activity) {
        b();
        this.a++;
        if (!this.b) {
            synchronized (this.g) {
                for (a c : this.f) {
                    c.c();
                }
            }
        }
        this.b = true;
    }

    public void onActivityStopped(Activity activity) {
        this.a--;
        if (this.a == 0) {
            b();
            this.c = new a();
            this.e = new Timer();
            this.e.schedule(this.c, 1000);
        }
    }
}
