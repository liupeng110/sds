package com.ut.mini.core;

/* UTMCVariables */
public class e {
    public static final e a = new e();
    private boolean b = false;
    private boolean c = false;
    private String d = null;

    public static e a() {
        return a;
    }

    public synchronized void a(String str) {
        this.d = str;
    }

    public synchronized String b() {
        return this.d;
    }

    public synchronized boolean c() {
        return this.b;
    }

    public synchronized void d() {
        this.b = true;
    }

    public synchronized void e() {
        this.b = false;
    }

    public synchronized boolean f() {
        return this.c;
    }

    public synchronized void a(boolean z) {
        this.c = z;
    }
}
