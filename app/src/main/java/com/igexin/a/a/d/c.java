package com.igexin.a.a.d;

import com.igexin.a.a.c.a;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class c {
    static final /* synthetic */ boolean h = (!c.class.desiredAssertionStatus());
    final transient ReentrantLock a = new ReentrantLock();
    final transient Condition b = this.a.newCondition();
    final TreeSet c;
    final AtomicInteger d = new AtomicInteger(0);
    int e;
    e f;
    public final AtomicLong g = new AtomicLong(-1);

    public c(Comparator comparator, e eVar) {
        this.c = new TreeSet(comparator);
        this.f = eVar;
    }

    private d e() {
        d a = a();
        return (a != null && this.c.remove(a)) ? a : null;
    }

    public final int a(d dVar, long j, TimeUnit timeUnit) {
        ReentrantLock reentrantLock = this.a;
        reentrantLock.lock();
        try {
            if (!this.c.contains(dVar)) {
                return -1;
            }
            this.c.remove(dVar);
            dVar.G = System.currentTimeMillis() + TimeUnit.MILLISECONDS.convert(j, timeUnit);
            int i = a(dVar) ? 1 : -2;
            reentrantLock.unlock();
            return i;
        } finally {
            reentrantLock.unlock();
        }
    }

    d a() {
        try {
            return (d) this.c.first();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public final boolean a(d dVar) {
        if (dVar == null) {
            return false;
        }
        ReentrantLock reentrantLock = this.a;
        reentrantLock.lock();
        try {
            d a = a();
            int i = this.e + 1;
            this.e = i;
            dVar.H = i;
            if (this.c.add(dVar)) {
                dVar.m();
                if (a == null || this.c.comparator().compare(dVar, a) < 0) {
                    this.b.signalAll();
                }
                reentrantLock.unlock();
                return true;
            }
            dVar.H--;
            return false;
        } catch (Exception e) {
            a.a("ScheduleQueue|offer|error");
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }

    final boolean b() {
        ReentrantLock reentrantLock = this.a;
        reentrantLock.lock();
        try {
            boolean isEmpty = this.c.isEmpty();
            return isEmpty;
        } finally {
            reentrantLock.unlock();
        }
    }

    public final d c() {
        d e;
        ReentrantLock reentrantLock = this.a;
        reentrantLock.lockInterruptibly();
        while (true) {
            d a = a();
            if (a == null) {
                this.d.set(1);
                this.e = 0;
                this.b.await();
            } else {
                long a2 = a.a(TimeUnit.NANOSECONDS);
                Object obj = (a.x || a.y) ? 1 : null;
                if (a2 <= 0 || obj != null) {
                    e = e();
                } else {
                    try {
                        this.g.set(a.G);
                        if (this.f.y) {
                            this.f.a(a.G);
                        }
                        this.b.awaitNanos(a2);
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            }
        }
        e = e();
        if (h || e != null) {
            if (!b()) {
                this.b.signalAll();
            }
            this.g.set(-1);
            return e;
        }
        throw new AssertionError();
    }

    public final void d() {
        this.c.clear();
    }
}
