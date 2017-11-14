package com.sds.android.ttpod.component.danmaku.b;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.util.DisplayMetrics;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.danmaku.c.b.a.b;
import com.sds.android.ttpod.component.danmaku.c.b.e;
import java.util.LinkedList;

/* DrawHandler */
public class c extends Handler {
    public g a;
    private long b = 0;
    private boolean c = true;
    private long d;
    private boolean e;
    private a f;
    private e g = new e();
    private com.sds.android.ttpod.component.danmaku.c.c.a h;
    private f i;
    private boolean j = true;
    private com.sds.android.ttpod.component.danmaku.c.b.a<Canvas> k;
    private final com.sds.android.ttpod.component.danmaku.c.d.a.a l = new com.sds.android.ttpod.component.danmaku.c.d.a.a();
    private int m;
    private LinkedList<Long> n = new LinkedList();
    private h o;
    private final boolean p;
    private long q = 60;
    private long r = 120;
    private long s = 30;
    private long t;
    private long u;
    private boolean v;
    private long w;
    private boolean x;
    private boolean y;
    private boolean z;

    /* DrawHandler */
    public interface a {
        void a();

        void a(e eVar);

        void b();
    }

    public c(Looper looper, f fVar, boolean z) {
        boolean z2 = true;
        super(looper);
        this.p = Runtime.getRuntime().availableProcessors() > 3;
        if (com.sds.android.ttpod.component.danmaku.a.a.c()) {
            z2 = false;
        }
        this.z = z2;
        a(fVar);
        if (z) {
            b(null);
        } else {
            a(false);
        }
        this.j = z;
    }

    private void a(f fVar) {
        this.i = fVar;
    }

    public void a(com.sds.android.ttpod.component.danmaku.c.c.a aVar) {
        this.h = aVar;
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public void a() {
        sendEmptyMessage(6);
    }

    public boolean b() {
        return this.c;
    }

    /* JADX err: Inconsistent code. */
    public void handleMessage(android.os.Message r10) {
        /*
        r9 = this;
        r8 = 6;
        r7 = 2;
        r6 = 3;
        r5 = 1;
        r4 = 0;
        r1 = r10.what;
        switch(r1) {
            case 1: goto L_0x0027;
            case 2: goto L_0x00b2;
            case 3: goto L_0x0033;
            case 4: goto L_0x0073;
            case 5: goto L_0x000b;
            case 6: goto L_0x0152;
            case 7: goto L_0x014f;
            case 8: goto L_0x00d6;
            case 9: goto L_0x011d;
            case 10: goto L_0x00c0;
            case 11: goto L_0x0196;
            case 12: goto L_0x019b;
            case 100: goto L_0x01b2;
            default: goto L_0x000a;
        };
    L_0x000a:
        return;
    L_0x000b:
        r0 = r9.h;
        if (r0 == 0) goto L_0x0017;
    L_0x000f:
        r0 = r9.i;
        r0 = r0.a();
        if (r0 != 0) goto L_0x001e;
    L_0x0017:
        r0 = 5;
        r2 = 100;
        r9.sendEmptyMessageDelayed(r0, r2);
        goto L_0x000a;
    L_0x001e:
        r0 = new com.sds.android.ttpod.component.danmaku.b.c$1;
        r0.<init>(r9);
        r9.a(r0);
        goto L_0x000a;
    L_0x0027:
        r0 = r10.obj;
        r0 = (java.lang.Long) r0;
        if (r0 == 0) goto L_0x0065;
    L_0x002d:
        r0 = r0.longValue();
        r9.b = r0;
    L_0x0033:
        r9.c = r4;
        r0 = r9.e;
        if (r0 == 0) goto L_0x006d;
    L_0x0039:
        r0 = r9.p;
        if (r0 == 0) goto L_0x0044;
    L_0x003d:
        monitor-enter(r9);
        r0 = r9.n;	 Catch:{ all -> 0x006a }
        r0.clear();	 Catch:{ all -> 0x006a }
        monitor-exit(r9);	 Catch:{ all -> 0x006a }
    L_0x0044:
        r0 = java.lang.System.currentTimeMillis();
        r2 = r9.b;
        r0 = r0 - r2;
        r9.d = r0;
        r0 = r9.g;
        r2 = r9.b;
        r0.a(r2);
        r9.removeMessages(r6);
        r9.sendEmptyMessage(r7);
        r0 = r9.a;
        r0.b();
        r9.p();
        r9.v = r4;
        goto L_0x000a;
    L_0x0065:
        r0 = 0;
        r9.b = r0;
        goto L_0x0033;
    L_0x006a:
        r0 = move-exception;
        monitor-exit(r9);	 Catch:{ all -> 0x006a }
        throw r0;
    L_0x006d:
        r0 = 100;
        r9.sendEmptyMessageDelayed(r6, r0);
        goto L_0x000a;
    L_0x0073:
        r9.c = r5;
        r9.k();
        r0 = r10.obj;
        r0 = (java.lang.Long) r0;
        r0 = r0.longValue();
        r2 = r9.g;
        r2 = r2.a;
        r0 = r0 - r2;
        r2 = r9.d;
        r0 = r2 - r0;
        r9.d = r0;
        r0 = r9.g;
        r2 = java.lang.System.currentTimeMillis();
        r4 = r9.d;
        r2 = r2 - r4;
        r0.a(r2);
        r0 = r9.a;
        if (r0 == 0) goto L_0x00a4;
    L_0x009b:
        r0 = r9.a;
        r1 = r9.g;
        r2 = r1.a;
        r0.a(r2);
    L_0x00a4:
        r0 = r9.g;
        r0 = r0.a;
        r9.b = r0;
        r9.removeMessages(r6);
        r9.sendEmptyMessage(r6);
        goto L_0x000a;
    L_0x00b2:
        r0 = r9.p;
        if (r0 == 0) goto L_0x00bb;
    L_0x00b6:
        r9.m();
        goto L_0x000a;
    L_0x00bb:
        r9.l();
        goto L_0x000a;
    L_0x00c0:
        r0 = r9.k;
        com.sds.android.ttpod.component.danmaku.c.c.b.a(r0);
        r0 = r10.obj;
        r0 = (java.lang.Boolean) r0;
        if (r0 == 0) goto L_0x000a;
    L_0x00cb:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x000a;
    L_0x00d1:
        com.sds.android.ttpod.component.danmaku.c.b.i.c();
        goto L_0x000a;
    L_0x00d6:
        r0 = r10.obj;
        r0 = (java.lang.Long) r0;
        r1 = r9.a;
        if (r1 == 0) goto L_0x00ee;
    L_0x00de:
        if (r0 != 0) goto L_0x0102;
    L_0x00e0:
        r0 = r9.g;
        r2 = r9.j();
        r0.a(r2);
        r0 = r9.a;
        r0.f();
    L_0x00ee:
        r9.j = r5;
        r0 = r9.c;
        if (r0 == 0) goto L_0x00fd;
    L_0x00f4:
        r0 = r9.i;
        if (r0 == 0) goto L_0x00fd;
    L_0x00f8:
        r0 = r9.i;
        r0.b();
    L_0x00fd:
        r9.p();
        goto L_0x000a;
    L_0x0102:
        r1 = r9.a;
        r1.b();
        r1 = r9.a;
        r2 = r0.longValue();
        r1.a(r2);
        r1 = r9.a;
        r1.f();
        r0 = r9.obtainMessage(r5, r0);
        r0.sendToTarget();
        goto L_0x00ee;
    L_0x011d:
        r9.j = r4;
        r0 = r9.i;
        if (r0 == 0) goto L_0x0128;
    L_0x0123:
        r0 = r9.i;
        r0.d();
    L_0x0128:
        r0 = r9.a;
        if (r0 == 0) goto L_0x0136;
    L_0x012c:
        r0 = r9.a;
        r0.f();
        r0 = r9.a;
        r0.h();
    L_0x0136:
        r0 = r10.obj;
        r0 = (java.lang.Boolean) r0;
        r2 = r0.booleanValue();
        if (r2 == 0) goto L_0x0149;
    L_0x0140:
        r2 = r9.a;
        if (r2 == 0) goto L_0x0149;
    L_0x0144:
        r2 = r9.a;
        r2.c();
    L_0x0149:
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x000a;
    L_0x014f:
        r9.removeMessages(r7);
    L_0x0152:
        if (r1 != r8) goto L_0x0158;
    L_0x0154:
        r0 = 0;
        r9.removeCallbacksAndMessages(r0);
    L_0x0158:
        r9.c = r5;
        r9.n();
        r9.m = r4;
        r0 = r9.o;
        if (r0 == 0) goto L_0x0169;
    L_0x0163:
        r9.p();
        r9.k();
    L_0x0169:
        r0 = r9.g;
        r2 = r0.a;
        r9.b = r2;
        if (r1 != r8) goto L_0x000a;
    L_0x0171:
        r0 = r9.a;
        if (r0 == 0) goto L_0x017a;
    L_0x0175:
        r0 = r9.a;
        r0.c();
    L_0x017a:
        r0 = r9.h;
        if (r0 == 0) goto L_0x0183;
    L_0x017e:
        r0 = r9.h;
        r0.g();
    L_0x0183:
        r0 = r9.getLooper();
        r1 = android.os.Looper.getMainLooper();
        if (r0 == r1) goto L_0x000a;
    L_0x018d:
        r0 = r9.getLooper();
        r0.quit();
        goto L_0x000a;
    L_0x0196:
        r9.p();
        goto L_0x000a;
    L_0x019b:
        r0 = r9.c;
        if (r0 == 0) goto L_0x000a;
    L_0x019f:
        r0 = r9.i;
        if (r0 == 0) goto L_0x000a;
    L_0x01a3:
        r0 = r9.a;
        r0.f();
        r0 = r9.i;
        r0.b();
        r9.p();
        goto L_0x000a;
    L_0x01b2:
        r9.f();
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.danmaku.b.c.handleMessage(android.os.Message):void");
    }

    private void k() {
        if (this.o != null) {
            synchronized (this.a) {
                this.a.notifyAll();
            }
            this.o.a();
            try {
                this.o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.o = null;
        }
    }

    private void l() {
        if (!this.c) {
            long a = a(System.currentTimeMillis());
            if (a < 0) {
                removeMessages(2);
                sendEmptyMessageDelayed(2, 60 - a);
                return;
            }
            a = this.i.b();
            removeMessages(2);
            if (this.j) {
                if (this.l.k && this.z) {
                    long j = this.l.j - this.g.a;
                    if (j > 500) {
                        b(j - 400);
                        return;
                    }
                }
                if (a < this.s) {
                    sendEmptyMessageDelayed(2, this.s - a);
                    return;
                } else {
                    sendEmptyMessage(2);
                    return;
                }
            }
            b(10000000);
        }
    }

    private void m() {
        if (this.o == null) {
            this.o = new h(this, "Danmaku update") {
                final /* synthetic */ c a;

                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    g.a("DrawHandler", "lookDanmaku run at new update thread");
                    while (!b() && !this.a.c) {
                        long currentTimeMillis2 = System.currentTimeMillis();
                        long c = this.a.s - (currentTimeMillis2 - currentTimeMillis);
                        if (c > 1) {
                            SystemClock.sleep(Math.min(5, c));
                        } else {
                            currentTimeMillis = this.a.a(currentTimeMillis2);
                            if (currentTimeMillis < 0) {
                                SystemClock.sleep(60 - currentTimeMillis);
                                currentTimeMillis = currentTimeMillis2;
                            } else {
                                this.a.i.b();
                                if (!this.a.j) {
                                    this.a.b(10000000);
                                } else if (this.a.l.k && this.a.z) {
                                    currentTimeMillis = this.a.l.j - this.a.g.a;
                                    if (currentTimeMillis > 500) {
                                        this.a.p();
                                        this.a.b(currentTimeMillis - 400);
                                    }
                                }
                                currentTimeMillis = currentTimeMillis2;
                            }
                        }
                    }
                }
            };
            this.o.start();
        }
    }

    private final long a(long j) {
        long j2 = 0;
        if (this.v || this.x) {
            return 0;
        }
        this.x = true;
        long j3 = j - this.d;
        if (!this.j || this.l.k || this.y) {
            this.g.a(j3);
            this.w = 0;
            j3 = 0;
        } else {
            j3 -= this.g.a;
            long max = Math.max(this.s, q());
            if (j3 <= 2000 && this.l.h <= this.q && max <= this.q) {
                j2 = Math.min(this.q, Math.max(this.s, (j3 / this.s) + max));
                if (Math.abs(j2 - this.u) < 4 && j2 > this.s && this.u > this.s) {
                    j2 = this.u;
                }
                long j4 = j3 - j2;
                j3 = j2;
                j2 = j4;
            }
            this.u = j3;
            this.w = j2;
            this.g.b(j3);
        }
        if (this.f != null) {
            this.f.a(this.g);
        }
        this.x = false;
        return j3;
    }

    private void n() {
        if (this.y) {
            a(System.currentTimeMillis());
        }
    }

    private void o() {
        this.q = Math.max(60, (long) (((float) 16) * 2.5f));
        this.r = this.q * 2;
        this.s = Math.max(26, (16 / 15) * 15);
        this.u = this.s;
        this.t = this.s + 3;
    }

    private void a(final Runnable runnable) {
        String str = "DrawHandler";
        String str2 = "lookDanmaku prepare drawTask!=null_%b tId=%d";
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(this.a != null);
        objArr[1] = Long.valueOf(Thread.currentThread().getId());
        g.d(str, str2, objArr);
        if (this.a == null) {
            this.a = a(this.i.c(), this.g, this.i.getContext(), this.i.getWidth(), this.i.getHeight(), this.i.isHardwareAccelerated(), new com.sds.android.ttpod.component.danmaku.b.g.a(this) {
                final /* synthetic */ c b;

                public void a() {
                    this.b.o();
                    runnable.run();
                }

                public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
                    this.b.obtainMessage(11).sendToTarget();
                }

                public void b() {
                    if (this.b.f != null) {
                        this.b.f.b();
                    }
                }

                public void c() {
                    if (this.b.c && this.b.j) {
                        this.b.obtainMessage(12).sendToTarget();
                    }
                }
            });
        } else {
            runnable.run();
        }
    }

    public boolean c() {
        return this.e;
    }

    private g a(boolean z, e eVar, Context context, int i, int i2, boolean z2, com.sds.android.ttpod.component.danmaku.b.g.a aVar) {
        g.d("DrawHandler", "lookDanmaku createDrawTask");
        this.k = new com.sds.android.ttpod.component.danmaku.c.b.a.a();
        this.k.a(i, i2);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.k.a(displayMetrics.density, displayMetrics.densityDpi, displayMetrics.scaledDensity);
        this.k.a(b.a.e);
        this.k.a(z2);
        obtainMessage(10, Boolean.valueOf(false)).sendToTarget();
        g aVar2 = z ? new a(eVar, this.k, aVar, (AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START * com.sds.android.ttpod.component.danmaku.c.e.a.a(context)) / 3) : new e(eVar, this.k, aVar);
        aVar2.b(this.h);
        aVar2.d();
        return aVar2;
    }

    public void a(Long l) {
        this.v = true;
        if (b()) {
            sendEmptyMessageDelayed(100, 200);
        }
        removeMessages(2);
        removeMessages(3);
        removeMessages(4);
        obtainMessage(4, l).sendToTarget();
    }

    public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (this.a != null) {
            cVar.a(this.g);
            this.a.a(cVar);
            obtainMessage(11).sendToTarget();
        }
    }

    public void d() {
        sendEmptyMessage(3);
        if (hasMessages(100)) {
            removeMessages(100);
        }
    }

    public void e() {
        sendEmptyMessage(5);
    }

    public void f() {
        n();
        sendEmptyMessage(7);
    }

    public void b(Long l) {
        if (!this.j) {
            removeMessages(8);
            removeMessages(9);
            obtainMessage(8, l).sendToTarget();
        }
    }

    public long a(boolean z) {
        if (!this.j) {
            return this.g.a;
        }
        removeMessages(8);
        removeMessages(9);
        obtainMessage(9, Boolean.valueOf(z)).sendToTarget();
        return this.g.a;
    }

    public boolean g() {
        return this.j;
    }

    public com.sds.android.ttpod.component.danmaku.c.d.a.a a(Canvas canvas) {
        if (this.a == null) {
            return this.l;
        }
        this.k.a(canvas);
        this.l.a(this.a.a(this.k));
        r();
        return this.l;
    }

    private void p() {
        if (this.y) {
            if (this.a != null) {
                this.a.f();
            }
            this.m = 0;
            if (this.p) {
                synchronized (this) {
                    this.n.clear();
                }
                synchronized (this.a) {
                    this.a.notifyAll();
                }
            } else {
                this.n.clear();
                removeMessages(2);
                sendEmptyMessage(2);
            }
            this.y = false;
        }
    }

    private void b(long j) {
        this.l.l = System.currentTimeMillis();
        this.y = true;
        if (this.p) {
            try {
                synchronized (this.a) {
                    if (j == 10000000) {
                        this.a.wait();
                    } else {
                        this.a.wait(j);
                    }
                    sendEmptyMessage(11);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (j == 10000000) {
            removeMessages(11);
            removeMessages(2);
        } else {
            removeMessages(11);
            removeMessages(2);
            sendEmptyMessageDelayed(11, j);
        }
    }

    private synchronized long q() {
        long j;
        int size = this.n.size();
        if (size <= 0) {
            j = 0;
        } else {
            j = (((Long) this.n.getLast()).longValue() - ((Long) this.n.getFirst()).longValue()) / ((long) size);
        }
        return j;
    }

    private synchronized void r() {
        this.n.addLast(Long.valueOf(System.currentTimeMillis()));
        if (this.n.size() > SecExceptionCode.SEC_ERROR_DYN_STORE) {
            this.n.removeFirst();
        }
    }

    public void a(int i, int i2) {
        if (this.k != null) {
            if (this.k.c() != i || this.k.d() != i2) {
                this.k.a(i, i2);
                obtainMessage(10, Boolean.valueOf(true)).sendToTarget();
            }
        }
    }

    public void h() {
        if (this.a != null) {
            this.a.e();
        }
    }

    public void i() {
        if (this.a != null) {
            this.a.i();
        }
    }

    public long j() {
        if (this.c || !this.y) {
            return this.g.a - this.w;
        }
        return System.currentTimeMillis() - this.d;
    }
}
