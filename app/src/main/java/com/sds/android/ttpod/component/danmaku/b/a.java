package com.sds.android.ttpod.component.danmaku.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.danmaku.c.b.a.c;
import com.sds.android.ttpod.component.danmaku.c.b.a.d;
import com.sds.android.ttpod.component.danmaku.c.b.a.f;
import com.sds.android.ttpod.component.danmaku.c.b.b.b;
import com.sds.android.ttpod.component.danmaku.c.b.e;
import com.sds.android.ttpod.component.danmaku.c.b.j;
import com.sds.android.ttpod.component.danmaku.c.b.k;
import com.sds.android.ttpod.component.danmaku.c.b.m;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* CacheManagingDrawTask */
public class a extends e {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private int k = 2;
    private a l;
    private e m;
    private final Object n = new Object();

    /* CacheManagingDrawTask */
    public class a {
        public HandlerThread a;
        c b = new c(4);
        f c = new f();
        b<d> d = com.sds.android.ttpod.component.danmaku.c.b.b.e.a(this.c, SecExceptionCode.SEC_ERROR_PKG_VALID);
        int e = 0;
        final /* synthetic */ a f;
        private int g;
        private int h = 0;
        private int i = 3;
        private a j;
        private boolean k = false;

        /* CacheManagingDrawTask */
        public class a extends Handler {
            final /* synthetic */ a a;
            private boolean b;
            private boolean c;
            private boolean d;

            public a(a aVar, Looper looper) {
                this.a = aVar;
                super(looper);
            }

            public void a() {
                this.d = true;
            }

            public void handleMessage(Message message) {
                int i = 0;
                switch (message.what) {
                    case 1:
                        this.a.i();
                        while (i < SecExceptionCode.SEC_ERROR_STA_ENC) {
                            this.a.d.a(new d());
                            i++;
                        }
                        break;
                    case 2:
                        synchronized (this.a.f.c) {
                            com.sds.android.ttpod.component.danmaku.c.b.c cVar = (com.sds.android.ttpod.component.danmaku.c.b.c) message.obj;
                            if (cVar.e()) {
                                return;
                            }
                            if (!cVar.c()) {
                                a(cVar);
                            }
                            if (cVar.s) {
                                this.a.f.m.a(this.a.f.g.a + (com.sds.android.ttpod.component.danmaku.c.c.b.f * ((long) this.a.i)));
                            }
                            super.a(cVar);
                            return;
                        }
                    case 3:
                        removeMessages(3);
                        boolean z = !(this.a.f.e == null || this.a.f.i) || this.c;
                        a(z);
                        if (z) {
                            this.c = false;
                        }
                        if (this.a.f.e != null && !this.a.f.i) {
                            this.a.f.e.a();
                            this.a.f.i = true;
                            return;
                        }
                        return;
                    case 4:
                        this.a.k();
                        return;
                    case 5:
                        Long l = (Long) message.obj;
                        if (l != null) {
                            this.a.f.m.a(l.longValue());
                            this.c = true;
                            this.a.i();
                            d();
                            return;
                        }
                        return;
                    case 6:
                        removeCallbacksAndMessages(null);
                        this.b = true;
                        this.a.h();
                        this.a.j();
                        getLooper().quit();
                        return;
                    case 7:
                        this.a.h();
                        this.a.f.m.a(this.a.f.g.a);
                        this.c = true;
                        return;
                    case 8:
                        this.a.a(true);
                        this.a.f.m.a(this.a.f.g.a - com.sds.android.ttpod.component.danmaku.c.c.b.f);
                        return;
                    case 9:
                        this.a.a(true);
                        this.a.f.m.a(this.a.f.g.a);
                        this.a.f.f();
                        return;
                    case 16:
                        break;
                    default:
                        return;
                }
                long e = e();
                if (e <= 0) {
                    e = com.sds.android.ttpod.component.danmaku.c.c.b.f / 2;
                }
                sendEmptyMessageDelayed(16, e);
            }

            private long e() {
                float d = this.a.d();
                com.sds.android.ttpod.component.danmaku.c.b.c c = this.a.b.c();
                long j = c != null ? c.a - this.a.f.g.a : 0;
                long j2 = com.sds.android.ttpod.component.danmaku.c.c.b.f * 2;
                if (d < TTFMImageUtils.Large_Scale && j > com.sds.android.ttpod.component.danmaku.c.c.b.f) {
                    this.a.f.m.a(this.a.f.g.a);
                    removeMessages(3);
                    sendEmptyMessage(3);
                } else if (d > 0.4f && j < (-j2)) {
                    removeMessages(4);
                    sendEmptyMessage(4);
                } else if (d < 0.9f) {
                    j = this.a.f.m.a - this.a.f.g.a;
                    if (j < 0) {
                        this.a.f.m.a(this.a.f.g.a);
                        sendEmptyMessage(8);
                        sendEmptyMessage(3);
                    } else if (j <= j2) {
                        removeMessages(3);
                        sendEmptyMessage(3);
                    }
                }
                return 0;
            }

            private void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, d dVar) {
                if (dVar == null) {
                    com.sds.android.ttpod.component.danmaku.c.b.b.c cVar2 = (d) cVar.r;
                } else {
                    Object obj = dVar;
                }
                cVar.r = null;
                if (cVar2 != null) {
                    cVar2.b();
                    this.a.d.a(cVar2);
                }
            }

            private long a(boolean z) {
                long j = this.a.f.m.a;
                long b = j + ((com.sds.android.ttpod.component.danmaku.c.c.b.f * ((long) this.a.i)) * 3);
                if (b < this.a.f.g.a) {
                    return 0;
                }
                long currentTimeMillis = System.currentTimeMillis();
                k a = this.a.f.c.a(j, b);
                if (a == null || a.f()) {
                    this.a.f.m.a(b);
                    return 0;
                }
                long j2;
                com.sds.android.ttpod.component.danmaku.c.b.c c = a.c();
                com.sds.android.ttpod.component.danmaku.c.b.c d = a.d();
                long min = Math.min(100, (((c.a - this.a.f.g.a) * 10) / com.sds.android.ttpod.component.danmaku.c.c.b.f) + 30);
                if (z) {
                    j2 = 0;
                } else {
                    j2 = min;
                }
                j e = a.e();
                com.sds.android.ttpod.component.danmaku.c.b.c cVar = null;
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                int a2 = a.a();
                b c2 = b.c();
                b.e a3 = c2.a("1111_Filter");
                if (a3 != null) {
                    a3.a();
                }
                while (!this.b && !this.d && e.b()) {
                    try {
                        cVar = e.a();
                        int i4 = i + 1;
                        if (d.a >= this.a.f.g.a) {
                            if (!cVar.c()) {
                                if (!z) {
                                    if (cVar.e()) {
                                        i = i4;
                                    } else if (!cVar.f()) {
                                        i = i4;
                                    }
                                }
                                if (!c2.a(cVar, i2, a2, null, true)) {
                                    if (cVar.l() == 0) {
                                        i = (int) ((cVar.a - j) / com.sds.android.ttpod.component.danmaku.c.c.b.f);
                                        if (i3 == i) {
                                            i = i2 + 1;
                                            i2 = i3;
                                        } else {
                                            int i5 = i;
                                            i = 0;
                                            i2 = i5;
                                        }
                                    } else {
                                        i = i2;
                                        i2 = i3;
                                    }
                                    if (!z) {
                                        try {
                                            synchronized (this.a.f.n) {
                                                this.a.f.n.wait(j2);
                                            }
                                        } catch (InterruptedException e2) {
                                            e2.printStackTrace();
                                        }
                                    }
                                    if (a(cVar) == (byte) 1 || (!z && System.currentTimeMillis() - currentTimeMillis >= 6000 * ((long) this.a.i))) {
                                        break;
                                    }
                                    i3 = i2;
                                    i2 = i;
                                    i = i4;
                                } else {
                                    i = i4;
                                }
                            } else {
                                i = i4;
                            }
                        } else {
                            break;
                        }
                    } catch (Exception e3) {
                    }
                }
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                if (cVar != null) {
                    this.a.f.m.a(cVar.a);
                } else {
                    this.a.f.m.a(b);
                }
                return currentTimeMillis2;
            }

            /* JADX err: Inconsistent code. */
            private byte a(com.sds.android.ttpod.component.danmaku.c.b.c r8) {
                /*
                r7 = this;
                r3 = 0;
                r2 = 1;
                r1 = 0;
                r0 = r8.b();
                if (r0 != 0) goto L_0x0012;
            L_0x0009:
                r0 = r7.a;
                r0 = r0.f;
                r0 = r0.b;
                r8.b(r0);
            L_0x0012:
                r0 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00d7, Exception -> 0x00ca }
                r4 = 1;
                r0 = r0.a(r8, r4);	 Catch:{ OutOfMemoryError -> 0x00d7, Exception -> 0x00ca }
                if (r0 == 0) goto L_0x0020;
            L_0x001b:
                r0 = r0.r;	 Catch:{ OutOfMemoryError -> 0x00d7, Exception -> 0x00ca }
                r0 = (com.sds.android.ttpod.component.danmaku.c.b.a.d) r0;	 Catch:{ OutOfMemoryError -> 0x00d7, Exception -> 0x00ca }
                r3 = r0;
            L_0x0020:
                if (r3 == 0) goto L_0x003a;
            L_0x0022:
                r3.k();	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r8.r = r3;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0.f;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0.l;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = r4.b(r8);	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0.a(r8, r4);	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r1;
            L_0x0039:
                return r0;
            L_0x003a:
                r0 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = 0;
                r4 = r0.a(r8, r4);	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                if (r4 == 0) goto L_0x0048;
            L_0x0043:
                r0 = r4.r;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = (com.sds.android.ttpod.component.danmaku.c.b.a.d) r0;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r3 = r0;
            L_0x0048:
                if (r3 == 0) goto L_0x0067;
            L_0x004a:
                r0 = 0;
                r4.r = r0;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0.f;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0.b;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = com.sds.android.ttpod.component.danmaku.c.e.b.a(r8, r0, r3);	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r8.r = r0;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r3 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r3 = r3.f;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r3 = r3.l;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r4 = 0;
                r3.a(r8, r4);	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r0 = r1;
                goto L_0x0039;
            L_0x0067:
                r0 = r8.m;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = (int) r0;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = r8.n;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = (int) r4;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = com.sds.android.ttpod.component.danmaku.c.e.b.a(r0, r4);	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = r4.h;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0 + r4;
                r4 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r4 = r4.g;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                if (r0 <= r4) goto L_0x0082;
            L_0x0080:
                r0 = r2;
                goto L_0x0039;
            L_0x0082:
                r0 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0.d;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = r0.a();	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r0 = (com.sds.android.ttpod.component.danmaku.c.b.a.d) r0;	 Catch:{ OutOfMemoryError -> 0x00da, Exception -> 0x00ca }
                r3 = r7.a;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r3 = r3.f;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r4 = r3.c;	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                monitor-enter(r4);	 Catch:{ OutOfMemoryError -> 0x00dd, Exception -> 0x00d1 }
                r3 = r7.a;	 Catch:{ all -> 0x00df }
                r3 = r3.f;	 Catch:{ all -> 0x00df }
                r3 = r3.b;	 Catch:{ all -> 0x00df }
                r3 = com.sds.android.ttpod.component.danmaku.c.e.b.a(r8, r3, r0);	 Catch:{ all -> 0x00df }
                r8.r = r3;	 Catch:{ all -> 0x00bc }
                r0 = r7.a;	 Catch:{ all -> 0x00bc }
                r0 = r0.f;	 Catch:{ all -> 0x00bc }
                r0 = r0.l;	 Catch:{ all -> 0x00bc }
                r5 = r7.a;	 Catch:{ all -> 0x00bc }
                r5 = r5.b(r8);	 Catch:{ all -> 0x00bc }
                r0 = r0.a(r8, r5);	 Catch:{ all -> 0x00bc }
                if (r0 != 0) goto L_0x00b6;
            L_0x00b3:
                r7.a(r8, r3);	 Catch:{ all -> 0x00bc }
            L_0x00b6:
                if (r0 == 0) goto L_0x00c8;
            L_0x00b8:
                r0 = r1;
            L_0x00b9:
                monitor-exit(r4);	 Catch:{ all -> 0x00bc }
                goto L_0x0039;
            L_0x00bc:
                r0 = move-exception;
                r1 = r3;
            L_0x00be:
                monitor-exit(r4);	 Catch:{ all -> 0x00e4 }
                throw r0;	 Catch:{ OutOfMemoryError -> 0x00c0, Exception -> 0x00d4 }
            L_0x00c0:
                r0 = move-exception;
                r0 = r1;
            L_0x00c2:
                r7.a(r8, r0);
                r0 = r2;
                goto L_0x0039;
            L_0x00c8:
                r0 = r2;
                goto L_0x00b9;
            L_0x00ca:
                r0 = move-exception;
            L_0x00cb:
                r7.a(r8, r3);
                r0 = r2;
                goto L_0x0039;
            L_0x00d1:
                r1 = move-exception;
                r3 = r0;
                goto L_0x00cb;
            L_0x00d4:
                r0 = move-exception;
                r3 = r1;
                goto L_0x00cb;
            L_0x00d7:
                r0 = move-exception;
                r0 = r3;
                goto L_0x00c2;
            L_0x00da:
                r0 = move-exception;
                r0 = r3;
                goto L_0x00c2;
            L_0x00dd:
                r1 = move-exception;
                goto L_0x00c2;
            L_0x00df:
                r1 = move-exception;
                r6 = r1;
                r1 = r0;
                r0 = r6;
                goto L_0x00be;
            L_0x00e4:
                r0 = move-exception;
                goto L_0x00be;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.danmaku.b.a.a.a.a(com.sds.android.ttpod.component.danmaku.c.b.c):byte");
            }

            public void b() {
                sendEmptyMessage(1);
                sendEmptyMessageDelayed(4, com.sds.android.ttpod.component.danmaku.c.c.b.f);
            }

            public void c() {
                this.b = true;
                removeCallbacksAndMessages(null);
                sendEmptyMessage(6);
            }

            public void d() {
                this.d = false;
                this.b = false;
                removeMessages(16);
                sendEmptyMessage(16);
                sendEmptyMessageDelayed(4, com.sds.android.ttpod.component.danmaku.c.c.b.f);
            }

            public void a(long j) {
                removeMessages(3);
                this.c = true;
                this.d = false;
                this.a.f.m.a(this.a.f.g.a + j);
                sendEmptyMessage(3);
            }
        }

        public a(a aVar, int i, int i2) {
            this.f = aVar;
            this.g = i;
            this.i = i2;
        }

        public void a(long j) {
            if (this.j != null) {
                this.j.a();
                this.j.removeMessages(3);
                this.j.obtainMessage(5, Long.valueOf(j)).sendToTarget();
            }
        }

        public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
            if (this.j != null) {
                this.j.obtainMessage(2, cVar).sendToTarget();
            }
        }

        public void a() {
            if (this.a == null) {
                this.a = new HandlerThread("Danmaku Cache-Building Thread");
                this.a.start();
            }
            if (this.j == null) {
                this.j = new a(this, this.a.getLooper());
            }
            this.j.b();
        }

        public void b() {
            this.k = true;
            synchronized (this.f.n) {
                this.f.n.notifyAll();
            }
            if (this.j != null) {
                this.j.c();
                this.j = null;
            }
            if (this.a != null) {
                try {
                    this.a.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.a.quit();
                this.a = null;
            }
        }

        public void c() {
            if (this.j != null) {
                this.j.d();
            } else {
                a();
            }
        }

        public float d() {
            if (this.g == 0) {
                return 0.0f;
            }
            return ((float) this.h) / ((float) this.g);
        }

        private void h() {
            if (this.b != null) {
                j e = this.b.e();
                while (e.b()) {
                    a(true, e.a(), null);
                }
                this.b.b();
            }
            this.h = 0;
        }

        private void i() {
            a(false);
        }

        private void a(boolean z) {
            if (this.b != null) {
                j e = this.b.e();
                while (e.b()) {
                    com.sds.android.ttpod.component.danmaku.c.b.c a = e.a();
                    m mVar = a.r;
                    boolean z2 = mVar != null && mVar.f();
                    if (z && z2) {
                        if (mVar.a() != null) {
                            this.h -= mVar.c();
                            mVar.b();
                        }
                        a(true, a, null);
                        e.c();
                    } else if (!a.c() || a.f()) {
                        a(true, a, null);
                        e.c();
                    }
                }
            }
            this.h = 0;
        }

        protected void a(boolean z, com.sds.android.ttpod.component.danmaku.c.b.c cVar, com.sds.android.ttpod.component.danmaku.c.b.c cVar2) {
            if (cVar.r == null) {
                return;
            }
            if (cVar.r.f()) {
                cVar.r.g();
                cVar.r = null;
                return;
            }
            this.h -= b(cVar);
            cVar.r.b();
            this.d.a((d) cVar.r);
            cVar.r = null;
        }

        protected int b(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
            if (cVar.r == null || cVar.r.f()) {
                return 0;
            }
            return cVar.r.c();
        }

        private void j() {
            while (true) {
                d dVar = (d) this.d.a();
                if (dVar != null) {
                    dVar.b();
                } else {
                    return;
                }
            }
        }

        private boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i) {
            while (this.h + i > this.g && this.b.a() > 0) {
                com.sds.android.ttpod.component.danmaku.c.b.c c = this.b.c();
                if (!c.e()) {
                    return false;
                }
                a(false, c, cVar);
                this.b.b(c);
            }
            this.b.a(cVar);
            this.h += i;
            return true;
        }

        private void k() {
            c(this.f.g.a);
        }

        private void c(long j) {
            j e = this.b.e();
            while (e.b() && !this.k) {
                com.sds.android.ttpod.component.danmaku.c.b.c a = e.a();
                if (a.e()) {
                    synchronized (this.f.n) {
                        try {
                            this.f.n.wait(30);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                            return;
                        }
                    }
                    a(false, a, null);
                    e.c();
                } else {
                    return;
                }
            }
        }

        private com.sds.android.ttpod.component.danmaku.c.b.c a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, boolean z) {
            j e = this.b.e();
            int i = 0;
            if (!z) {
                i = this.f.b.h() * 2;
            }
            while (e.b()) {
                com.sds.android.ttpod.component.danmaku.c.b.c a = e.a();
                if (a.c()) {
                    if (a.m == cVar.m && a.n == cVar.n && a.h == cVar.h && a.j == cVar.j && a.d == cVar.d && a.b.equals(cVar.b)) {
                        return a;
                    }
                    if (z) {
                        continue;
                    } else if (!a.e()) {
                        break;
                    } else if (a.r.f()) {
                        continue;
                    } else {
                        float d = ((float) a.r.d()) - cVar.m;
                        float e2 = ((float) a.r.e()) - cVar.n;
                        if (d >= 0.0f && d <= ((float) r0) && e2 >= 0.0f && e2 <= ((float) r0)) {
                            return a;
                        }
                    }
                }
            }
            return null;
        }

        public void b(long j) {
            if (this.j != null) {
                this.j.a(j);
            }
        }

        public void e() {
            if (this.j != null) {
                this.j.removeMessages(3);
                this.j.a();
                this.j.removeMessages(7);
                this.j.sendEmptyMessage(7);
            }
        }

        public void f() {
            if (this.j != null) {
                this.j.removeMessages(9);
                this.j.sendEmptyMessage(9);
            }
        }

        public void g() {
            if (this.j != null) {
                this.j.removeMessages(4);
                this.j.sendEmptyMessage(4);
            }
        }

        public void a(Runnable runnable) {
            if (this.j != null) {
                this.j.post(runnable);
            }
        }
    }

    public a(e eVar, com.sds.android.ttpod.component.danmaku.c.b.a<?> aVar, com.sds.android.ttpod.component.danmaku.b.g.a aVar2, int i) {
        super(eVar, aVar, aVar2);
        this.k = i;
        if (com.sds.android.ttpod.component.danmaku.a.b.a()) {
            this.k = i * 3;
        }
        this.l = new a(this, i, 3);
    }

    protected void a(e eVar) {
        this.g = eVar;
        this.m = new e();
        this.m.a(eVar.a);
    }

    public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (this.l != null) {
            this.l.a(cVar);
        }
    }

    public com.sds.android.ttpod.component.danmaku.c.d.a.a a(com.sds.android.ttpod.component.danmaku.c.b.a<?> aVar) {
        com.sds.android.ttpod.component.danmaku.c.d.a.a a;
        synchronized (this.c) {
            a = super.a((com.sds.android.ttpod.component.danmaku.c.b.a) aVar);
        }
        synchronized (this.n) {
            this.n.notify();
        }
        if (!(a == null || this.l == null || a.g >= -20)) {
            this.l.g();
            this.l.b(-com.sds.android.ttpod.component.danmaku.c.c.b.f);
        }
        return a;
    }

    public void a() {
        if (this.f != null) {
            this.f.a();
        }
    }

    public void a(long j) {
        super.a(j);
        this.l.a(j);
    }

    public void b() {
        super.b();
        if (this.l == null) {
            this.l = new a(this, this.k, 3);
            this.l.a();
            return;
        }
        this.l.c();
    }

    public void c() {
        super.c();
        a();
        if (this.l != null) {
            this.l.b();
            this.l = null;
        }
    }

    public void d() {
        if (a || this.d != null) {
            g.d("CacheManagingDrawTask", "lookDanmaku prepare");
            a(this.d);
            this.l.a();
            return;
        }
        throw new AssertionError();
    }

    public boolean a(com.sds.android.ttpod.component.danmaku.c.b.a.b bVar, com.sds.android.ttpod.component.danmaku.c.b.a.b.c cVar, Object... objArr) {
        if (!super.b(bVar, cVar, objArr)) {
            if (com.sds.android.ttpod.component.danmaku.c.b.a.b.c.SCROLL_SPEED_FACTOR.equals(cVar)) {
                this.b.a(com.sds.android.ttpod.component.danmaku.c.b.a.b.a.e);
                f();
            } else if (cVar.isVisibilityRelatedTag()) {
                if (objArr != null && objArr.length > 0 && objArr[0] != null && ((!(objArr[0] instanceof Boolean) || ((Boolean) objArr[0]).booleanValue()) && this.l != null)) {
                    this.l.b(0);
                }
                f();
            } else if (com.sds.android.ttpod.component.danmaku.c.b.a.b.c.TRANSPARENCY.equals(cVar) || com.sds.android.ttpod.component.danmaku.c.b.a.b.c.SCALE_TEXTSIZE.equals(cVar) || com.sds.android.ttpod.component.danmaku.c.b.a.b.c.DANMAKU_STYLE.equals(cVar)) {
                if (com.sds.android.ttpod.component.danmaku.c.b.a.b.c.SCALE_TEXTSIZE.equals(cVar)) {
                    this.b.a(com.sds.android.ttpod.component.danmaku.c.b.a.b.a.e);
                }
                if (this.l != null) {
                    this.l.e();
                    this.l.b(-com.sds.android.ttpod.component.danmaku.c.c.b.f);
                }
            } else if (this.l != null) {
                this.l.f();
                this.l.b(0);
            }
        }
        if (!(this.e == null || this.l == null)) {
            this.l.a(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.e != null) {
                        this.a.e.c();
                    }
                }
            });
        }
        return true;
    }
}
