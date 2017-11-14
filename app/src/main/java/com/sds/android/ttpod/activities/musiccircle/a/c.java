package com.sds.android.ttpod.activities.musiccircle.a;

import android.os.Handler;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.storage.environment.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/* CheckerManager */
public final class c implements a, Runnable {
    private static c a;
    private static final Object[] c = new Object[0];
    private Handler b = new Handler();
    private ArrayList<b> d = new ArrayList();
    private ArrayList<a> e = new ArrayList();
    private int f = -1;
    private long g;

    public static c a() {
        if (a == null) {
            synchronized (c) {
                if (a == null) {
                    a = new c();
                }
            }
        }
        return a;
    }

    private c() {
        f();
        d();
    }

    public void b() {
        e();
        g.a("CheckerManager", "pauseCheckMessage");
    }

    public void c() {
        e();
        long max = Math.max(1000, (300000 - TimeUnit.NANOSECONDS.toMillis(System.nanoTime())) + this.g);
        g.a("CheckerManager", "resumeCheckMessage " + max);
        a(max);
    }

    private void f() {
        this.d.add(new d(this));
        this.d.add(new e(this));
    }

    public void d() {
        e();
        a(1000);
    }

    public void e() {
        this.b.removeCallbacks(this);
    }

    private void a(long j) {
        this.b.postDelayed(this, j);
    }

    public void a(b bVar, boolean z, BaseResult baseResult) {
        if (z) {
            a(bVar, baseResult);
        }
        run();
    }

    public void a(Class cls) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.getClass() == cls) {
                bVar.c();
                return;
            }
        }
    }

    public void a(a aVar) {
        if (!this.e.contains(aVar)) {
            this.e.add(aVar);
            c(aVar);
        }
    }

    public void b(a aVar) {
        this.e.remove(aVar);
    }

    private void a(b bVar, BaseResult baseResult) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((a) it.next()).onCheckFinished(bVar, baseResult);
        }
    }

    private void c(a aVar) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.a()) {
                aVar.onCheckFinished(bVar, bVar.b());
            }
        }
    }

    public void run() {
        if (b.av()) {
            int i = this.f + 1;
            this.f = i;
            if (i < this.d.size()) {
                g.a("CheckerManager", "will check %d task %s", Integer.valueOf(this.f), ((b) this.d.get(this.f)).getClass().getSimpleName());
                r0.d();
                this.g = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            }
        }
        this.f = -1;
        g.a("CheckerManager", "check complete, wait for next check.");
        a(300000);
        this.g = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
    }
}
