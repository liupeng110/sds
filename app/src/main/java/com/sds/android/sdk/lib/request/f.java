package com.sds.android.sdk.lib.request;

import com.b.a.a.c;
import java.io.Serializable;

/* Extra */
public class f implements Serializable {
    @c(a = "version")
    private Object a;
    @c(a = "all_page")
    private int b;
    @c(a = "curr_page")
    private int c;
    @c(a = "size")
    private int d;
    @c(a = "is_update")
    private String e = "";
    @c(a = "all_count")
    private int f;

    public f(int i, int i2) {
        this.b = i;
        this.f = i2;
    }

    public f(int i, int i2, int i3, int i4, Object obj) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.f = i4;
        this.a = obj;
    }

    public String a() {
        return this.e;
    }

    public int b() {
        return this.b;
    }

    public Object c() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        f fVar = (f) obj;
        if (this.b != fVar.b) {
            return false;
        }
        if (this.c != fVar.c) {
            return false;
        }
        if (this.d != fVar.d) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((this.b * 31) + this.c) * 31) + this.d;
    }
}
