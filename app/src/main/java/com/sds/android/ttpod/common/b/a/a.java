package com.sds.android.ttpod.common.b.a;

import java.io.Serializable;

/* ShareInfo */
public class a implements Serializable {
    private String a = "";
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private Long f = Long.valueOf(0);
    private Integer g = Integer.valueOf(0);
    private long h = 0;
    private String i = "";
    private String j = "";
    private String k = "";
    private String l = "";
    private boolean m;
    private String n = "";
    private String o = "";
    private a p = a.SONG;
    private String q = "";
    private String r = "";
    private long s;
    private long t;

    /* ShareInfo */
    public enum a {
        SONG,
        MV,
        POST,
        THIRDPARTY
    }

    public String a() {
        return this.o;
    }

    public void a(String str) {
        this.o = str;
    }

    public a(String str, String str2, Long l) {
        this.d = str;
        this.e = str2;
        this.m = true;
        a(l);
    }

    public String b() {
        return this.k;
    }

    public void b(String str) {
        this.k = str;
    }

    public boolean c() {
        return this.m;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public String d() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String e() {
        return this.a == null ? "" : this.a;
    }

    public void d(String str) {
        this.a = str;
    }

    public String f() {
        return this.c;
    }

    public void e(String str) {
        this.c = str;
    }

    public String g() {
        return this.d;
    }

    public void f(String str) {
        this.d = str;
    }

    public String h() {
        return this.e;
    }

    public Long i() {
        return this.f;
    }

    public void a(Long l) {
        if (l == null) {
            this.f = Long.valueOf(0);
        } else {
            this.f = l;
        }
    }

    public Long j() {
        return Long.valueOf(this.h);
    }

    public void a(long j) {
        this.h = j;
    }

    public a k() {
        return this.p;
    }

    public void a(a aVar) {
        this.p = aVar;
    }

    public String l() {
        return this.i;
    }

    public void g(String str) {
        this.i = str;
    }

    public String m() {
        return this.j;
    }

    public void h(String str) {
        this.j = str;
    }

    public long n() {
        return this.t;
    }

    public void b(long j) {
        this.t = j;
    }

    public long o() {
        return this.s;
    }

    public void c(long j) {
        this.s = j;
    }

    public String p() {
        return this.n;
    }

    public void i(String str) {
        if (str == null) {
            str = "";
        }
        this.n = str;
    }

    public void j(String str) {
        this.l = str;
    }

    public boolean q() {
        return this.p == a.THIRDPARTY;
    }

    public String toString() {
        return "ShareInfo{mImageUrl='" + this.a + '\'' + ", mMessage='" + this.c + '\'' + '}';
    }

    public String r() {
        return this.q;
    }

    public void k(String str) {
        this.q = str;
    }

    public String s() {
        return this.r;
    }

    public void l(String str) {
        this.r = str;
    }

    public Integer t() {
        return this.g;
    }

    public void a(Integer num) {
        this.g = num;
    }
}
