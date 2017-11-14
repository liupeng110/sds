package com.tencent.a.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/* ProGuard */
public class a extends g implements Callback {
    private h a;
    private FileWriter b;
    private File c;
    private char[] d;
    private volatile e e;
    private volatile e f;
    private volatile e g;
    private volatile e h;
    private volatile boolean i;
    private HandlerThread j;
    private Handler k;

    public a(h hVar) {
        this(63, true, b.a, hVar);
    }

    public a(int i, boolean z, b bVar, h hVar) {
        super(i, z, bVar);
        this.i = false;
        a(hVar);
        this.e = new e();
        this.f = new e();
        this.g = this.e;
        this.h = this.f;
        this.d = new char[hVar.f()];
        hVar.b();
        h();
        this.j = new HandlerThread(hVar.c(), hVar.i());
        if (this.j != null) {
            this.j.start();
        }
        if (this.j.isAlive() && this.j.getLooper() != null) {
            this.k = new Handler(this.j.getLooper(), this);
        }
        f();
    }

    public void a() {
        if (this.k.hasMessages(1024)) {
            this.k.removeMessages(1024);
        }
    }

    public void b() {
        i();
        this.j.quit();
    }

    protected void a(int i, Thread thread, long j, String str, String str2, Throwable th) {
        a(e().a(i, thread, j, str, str2, th));
    }

    protected void a(String str) {
        this.g.a(str);
        if (this.g.a() >= c().f()) {
            a();
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1024:
                g();
                f();
                break;
        }
        return true;
    }

    private void f() {
        if (this.k != null) {
            this.k.sendEmptyMessageDelayed(1024, c().g());
        }
    }

    private void g() {
        if (Thread.currentThread() == this.j && !this.i) {
            this.i = true;
            j();
            try {
                this.h.a(h(), this.d);
            } catch (IOException e) {
            } finally {
                this.h.b();
            }
            this.i = false;
        }
    }

    private Writer h() {
        File a = c().a();
        if (!(a == null || a.equals(this.c))) {
            this.c = a;
            i();
            try {
                this.b = new FileWriter(this.c, true);
            } catch (IOException e) {
                return null;
            }
        }
        return this.b;
    }

    private void i() {
        try {
            if (this.b != null) {
                this.b.flush();
                this.b.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void j() {
        synchronized (this) {
            if (this.g == this.e) {
                this.g = this.f;
                this.h = this.e;
            } else {
                this.g = this.e;
                this.h = this.f;
            }
        }
    }

    public h c() {
        return this.a;
    }

    public void a(h hVar) {
        this.a = hVar;
    }
}
