package com.igexin.a.a.d;

import android.os.PowerManager.WakeLock;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

final class g implements Runnable {
    final BlockingQueue a = new LinkedBlockingQueue();
    d b;
    d c;
    volatile int d;
    WakeLock e;
    final /* synthetic */ f f;

    public g(f fVar, d dVar) {
        this.f = fVar;
        this.b = dVar;
        this.e = fVar.i.p.newWakeLock(1, toString());
        this.e.setReferenceCounted(false);
    }

    public final void a() {
        this.a.clear();
        this.c = null;
        this.e = null;
    }

    public final void a(d dVar) {
        dVar.a(this.e);
        if (this.d == 0) {
            this.d = dVar.L;
        }
        Object obj = 1;
        while (obj != null) {
            if (dVar.U) {
                this.e.acquire();
            }
            try {
                dVar.a_();
                dVar.s();
                dVar.u();
                if (dVar.U) {
                    this.e.release();
                }
                if (!dVar.F) {
                    dVar.c();
                }
                if (!dVar.x && dVar.A) {
                }
            } catch (Exception e) {
                dVar.F = true;
                dVar.N = e;
                dVar.v();
                dVar.o();
                this.f.i.a((Object) dVar);
                this.f.i.g();
                if (dVar.U) {
                    this.e.release();
                }
                if (!dVar.F) {
                    dVar.c();
                }
                if (!dVar.x && dVar.A) {
                }
            } catch (Throwable th) {
                if (dVar.U) {
                    this.e.release();
                }
                if (!dVar.F) {
                    dVar.c();
                }
                if (dVar.x || !dVar.A) {
                }
            }
            obj = null;
            dVar = null;
        }
    }

    final d b() {
        while (this.d != 0) {
            ReentrantLock reentrantLock;
            try {
                d dVar = (d) this.a.poll(this.f.e, TimeUnit.NANOSECONDS);
                if (dVar != null) {
                    return dVar;
                }
                if (this.a.isEmpty()) {
                    reentrantLock = this.f.c;
                    reentrantLock.lock();
                    if (this.a.isEmpty()) {
                        this.f.b.remove(Integer.valueOf(this.d));
                        this.c.e();
                        this.d = 0;
                        reentrantLock.unlock();
                        return null;
                    }
                    reentrantLock.unlock();
                } else {
                    continue;
                }
            } catch (InterruptedException e) {
            } catch (Throwable th) {
                reentrantLock.unlock();
            }
        }
        return null;
    }

    public final void run() {
        boolean z = true;
        while (z) {
            try {
                d dVar = this.b;
                this.b = null;
                while (true) {
                    if (dVar == null) {
                        dVar = b();
                        if (dVar == null) {
                            dVar = this.f.a();
                            if (dVar == null) {
                                break;
                            }
                        }
                    }
                    this.c = null;
                    a(dVar);
                    this.c = dVar;
                    dVar = null;
                }
                z = this.f.a(this);
                if (!z) {
                    a();
                }
            } catch (Exception e) {
                z = this.f.a(this);
                if (!z) {
                    a();
                }
            } catch (Throwable th) {
                if (!this.f.a(this)) {
                    a();
                }
            }
        }
    }
}
