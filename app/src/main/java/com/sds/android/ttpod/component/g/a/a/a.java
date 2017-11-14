package com.sds.android.ttpod.component.g.a.a;

import android.content.Context;
import android.os.Process;
import android.view.View;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.e;
import com.sds.android.ttpod.media.player.PlayStatus;

/* BasePlayerViewController */
public abstract class a extends c {
    private static final int[] ac = new int[0];
    private boolean Z = false;
    private final Object a = new Object();
    private boolean aa = false;
    private boolean ab = false;
    private int[] ad;
    private a b;
    protected com.sds.android.ttpod.framework.modules.skin.view.a c;

    /* BasePlayerViewController */
    private final class a extends Thread {
        final /* synthetic */ a a;
        private boolean b = false;

        public a(a aVar) {
            this.a = aVar;
            super("visualization thread");
            Process.setThreadPriority(0);
        }

        public void run() {
            while (!this.b) {
                long currentTimeMillis = System.currentTimeMillis();
                if (this.a.C() && this.a.F()) {
                    int numberOfLine = this.a.c.getNumberOfLine();
                    if (this.a.ad == null || this.a.ad.length != numberOfLine) {
                        this.a.ad = new int[numberOfLine];
                    }
                    if (this.a.V == PlayStatus.STATUS_PLAYING && e.a(BaseApplication.e()).a(this.a.ad, numberOfLine)) {
                        this.a.c.a(this.a.ad);
                    } else {
                        this.a.c.a(a.ac);
                    }
                    try {
                        Thread.sleep(Math.max(50 - (System.currentTimeMillis() - currentTimeMillis), 20));
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                try {
                    Thread.sleep(50);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                synchronized (this.a.a) {
                    if (!this.a.F()) {
                        try {
                            this.a.a.wait();
                        } catch (InterruptedException e3) {
                            return;
                        }
                    }
                }
            }
        }

        public void interrupt() {
            this.b = true;
            super.interrupt();
        }

        public boolean isInterrupted() {
            return this.b || super.isInterrupted();
        }
    }

    public abstract View a();

    public a(Context context, String str) {
        super(context, str);
    }

    public void A() {
        this.ab = true;
    }

    public void B() {
        this.ab = false;
        E();
    }

    public boolean C() {
        return this.aa;
    }

    public void b() {
        synchronized (this.a) {
            if (this.b != null) {
                this.b.interrupt();
                this.b = null;
            }
        }
        this.l = null;
        this.m = null;
        this.q = null;
        this.L = null;
        super.b();
    }

    public void u() {
        if (!C()) {
            this.aa = true;
            super.u();
            D();
        }
    }

    protected void D() {
        if (this.c == null) {
            this.c = this.L;
        }
        if (this.c == null) {
            return;
        }
        if (this.b == null || this.b.isInterrupted()) {
            this.c.setOnActiveListener(new com.sds.android.ttpod.framework.modules.skin.view.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.E();
                }

                public void b() {
                }
            });
            this.b = new a(this);
            this.b.start();
            return;
        }
        E();
    }

    protected final void E() {
        if (C() && F()) {
            synchronized (this.a) {
                this.a.notifyAll();
            }
        }
    }

    public void t() {
        if (C()) {
            this.aa = false;
            super.t();
        }
    }

    protected boolean F() {
        return !this.ab && d(this.c) && this.c.isEnabled();
    }

    public void c(boolean z) {
        this.Z = z;
    }

    public int e() {
        View a = a();
        return a == null ? 0 : a.getWidth();
    }

    public int f() {
        View a = a();
        return a == null ? 0 : a.getHeight();
    }

    public void h() {
    }
}
