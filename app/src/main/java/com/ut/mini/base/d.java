package com.ut.mini.base;

import com.ut.mini.c.a;

/* UTMIVariables */
public class d {
    private static d a = new d();
    private String b = null;
    private boolean c = false;
    private String d = null;
    private String e = null;
    private a f = null;

    public synchronized void a(a aVar) {
        this.f = aVar;
    }

    public synchronized a a() {
        return this.f;
    }

    public static d b() {
        return a;
    }

    public String c() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String d() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public boolean e() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }
}
