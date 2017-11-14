package com.tencent.a.a;

/* ProGuard */
public class d extends c {
    public static d d = null;

    public static d f() {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d();
                }
            }
        }
        return d;
    }

    public static final void a(String str, String str2) {
        f().a(1, str, str2, null);
    }

    public static final void b(String str, String str2) {
        f().a(2, str, str2, null);
    }

    public static final void c(String str, String str2) {
        f().a(4, str, str2, null);
    }

    public static final void d(String str, String str2) {
        f().a(16, str, str2, null);
    }

    public static final void a(String str, String str2, Throwable th) {
        f().a(16, str, str2, th);
    }

    public d() {
        this.c = new a(a);
    }

    public void b() {
        synchronized (d.class) {
            if (this.c != null) {
                this.c.a();
                this.c.b();
                this.c = null;
                d = null;
            }
        }
    }
}
