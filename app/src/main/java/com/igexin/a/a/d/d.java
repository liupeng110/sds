package com.igexin.a.a.d;

import android.os.PowerManager.WakeLock;
import com.igexin.a.a.d.a.a;
import com.igexin.a.a.d.a.e;
import com.igexin.a.a.d.a.g;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class d extends a implements a {
    protected static e Q;
    protected volatile boolean A;
    protected volatile boolean B;
    protected volatile boolean C;
    protected volatile boolean D;
    protected volatile boolean E;
    protected volatile boolean F;
    protected volatile long G;
    volatile int H;
    public long I;
    public int J;
    public int K;
    public int L;
    public int M;
    public Exception N;
    public Object O;
    public g P;
    protected final ReentrantLock R;
    protected final Condition S;
    Thread T;
    protected volatile boolean U;
    WakeLock V;
    int W;
    protected com.igexin.a.a.d.a.d X;
    private byte a;
    protected volatile boolean x;
    protected volatile boolean y;
    protected volatile boolean z;

    public d(int i) {
        this(i, null);
    }

    public d(int i, com.igexin.a.a.d.a.d dVar) {
        this.L = i;
        this.X = dVar;
        this.R = new ReentrantLock();
        this.S = this.R.newCondition();
    }

    public final int a(long j, TimeUnit timeUnit) {
        if (j <= 0) {
            return 0;
        }
        switch (Q.m.a(this, j, timeUnit)) {
            case -2:
                return -2;
            case -1:
                this.G = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(j, timeUnit);
                return -1;
            case 1:
                return 1;
            default:
                return 0;
        }
    }

    public long a(TimeUnit timeUnit) {
        return timeUnit.convert(n(), TimeUnit.MILLISECONDS);
    }

    public final void a(int i) {
        this.a = (byte) (this.a & 15);
        this.a = (byte) (this.a | ((i & 15) << 4));
    }

    public final void a(int i, g gVar) {
        if (i < 0) {
            throw new IllegalArgumentException("second must > 0");
        }
        this.K = i;
        this.P = gVar;
    }

    public final void a(long j) {
        this.I = j;
    }

    public void a(WakeLock wakeLock) {
        this.V = wakeLock;
    }

    public final void a(com.igexin.a.a.d.a.d dVar) {
        this.X = dVar;
    }

    public void a_() {
        this.T = Thread.currentThread();
        this.B = true;
    }

    public void c() {
        if (this.x || this.y) {
            f();
        }
    }

    public void d() {
        this.E = true;
    }

    protected abstract void e();

    public void f() {
        this.O = null;
        this.N = null;
        this.T = null;
    }

    public WakeLock l() {
        return this.V;
    }

    final void m() {
        this.W++;
        this.W &= 1090519038;
    }

    long n() {
        return this.G - System.currentTimeMillis();
    }

    public final void o() {
        this.x = true;
    }

    public final boolean p() {
        return this.z;
    }

    public final boolean q() {
        return this.y;
    }

    public final boolean r() {
        return this.E;
    }

    protected void s() {
    }

    public final void t() {
        this.y = true;
    }

    protected void u() {
        if (!this.A && !this.C && !this.D) {
            this.x = true;
            this.B = false;
        } else if (this.C && !this.x) {
            this.B = false;
        } else if (this.A && !this.z && !this.x) {
            this.B = false;
        }
    }

    protected void v() {
        if (this.X != null) {
            this.X.a(e.error);
        }
    }
}
