package com.sds.android.ttpod.framework.modules.skin.e;

import android.text.TextUtils;
import java.io.File;

/* LyricInfo */
public final class i {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private long f;
    private long g;
    private long h;
    private long i;

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.e == null ? 0 : this.e.hashCode()) + (((this.d == null ? 0 : this.d.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + 31) * 31)) * 31)) * 31)) * 31) + ((int) (this.f ^ (this.f >>> 1)))) * 31;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        return ((((hashCode + i) * 31) + ((int) (this.h ^ (this.h >>> 1)))) * 31) + ((int) (this.i ^ (this.i >>> 1)));
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        if (!TextUtils.equals(this.a, iVar.a) || !TextUtils.equals(this.b, iVar.b) || !TextUtils.equals(this.c, iVar.c) || !TextUtils.equals(this.d, iVar.d) || !TextUtils.equals(this.e, iVar.e) || this.f != iVar.f || this.i != iVar.i) {
            return false;
        }
        if (this.h != iVar.h) {
            z = false;
        }
        return z;
    }

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
        this.i = new File(str).lastModified();
    }

    public long b() {
        return this.i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!k.a(this.a)) {
            stringBuilder.append(String.format("[ti:%s]\n", new Object[]{this.a}));
        }
        if (!k.a(this.b)) {
            stringBuilder.append(String.format("[ar:%s]\n", new Object[]{this.b}));
        }
        if (!k.a(this.c)) {
            stringBuilder.append(String.format("[al:%s]\n", new Object[]{this.c}));
        }
        if (!k.a(this.d)) {
            stringBuilder.append(String.format("[by:%s]\n", new Object[]{this.d}));
        }
        if (this.f != 0) {
            stringBuilder.append(String.format("[offset:%d]\n", new Object[]{Long.valueOf(this.f)}));
        }
        if (this.h != 0) {
            stringBuilder.append(String.format("[total:%d]\n", new Object[]{Long.valueOf(this.h)}));
        }
        return stringBuilder.toString();
    }

    public void b(String str) {
        this.a = str;
    }

    public void c(String str) {
        this.b = str;
    }

    public void d(String str) {
        this.c = str;
    }

    public void e(String str) {
        this.d = str;
    }

    public void a(long j) {
        this.f = j;
    }

    public void b(long j) {
        this.g = j;
    }

    public void c(long j) {
        this.h = j;
    }

    public long c() {
        return this.f;
    }

    public long d() {
        return this.g;
    }

    public long e() {
        return this.h;
    }

    public int a(int i) {
        this.g += (long) i;
        return (int) (this.g - this.f);
    }

    public boolean f() {
        boolean z = this.f != this.g;
        if (z) {
            this.f = this.g;
        }
        return z;
    }

    public int g() {
        int i = (int) (this.f - this.g);
        this.g = this.f;
        return i;
    }
}
