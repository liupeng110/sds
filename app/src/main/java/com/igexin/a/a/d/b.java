package com.igexin.a.a.d;

import com.igexin.a.a.d.a.g;
import java.util.concurrent.TimeUnit;

public abstract class b implements g {
    protected boolean a = true;

    public void a() {
        this.a = false;
    }

    public boolean a(long j, d dVar) {
        return TimeUnit.SECONDS.toMillis((long) dVar.K) < j - dVar.I;
    }

    public long b(long j, d dVar) {
        return (TimeUnit.SECONDS.toMillis((long) dVar.K) + dVar.I) - j;
    }

    public boolean b() {
        return this.a;
    }
}
