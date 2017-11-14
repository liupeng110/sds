package com.tencent.a.a;

import com.tencent.a.c.c.a;

/* ProGuard */
public abstract class g {
    private volatile int a;
    private volatile boolean b;
    private b c;

    protected abstract void a(int i, Thread thread, long j, String str, String str2, Throwable th);

    public g() {
        this(63, true, b.a);
    }

    public g(int i, boolean z, b bVar) {
        this.a = 63;
        this.b = true;
        this.c = b.a;
        a(i);
        a(z);
        a(bVar);
    }

    public void b(int i, Thread thread, long j, String str, String str2, Throwable th) {
        if (d() && a.a(this.a, i)) {
            a(i, thread, j, str, str2, th);
        }
    }

    public void a(int i) {
        this.a = i;
    }

    public boolean d() {
        return this.b;
    }

    public void a(boolean z) {
        this.b = z;
    }

    public b e() {
        return this.c;
    }

    public void a(b bVar) {
        this.c = bVar;
    }
}
