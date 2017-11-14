package com.sds.android.ttpod.ThirdParty;

import com.sds.android.sdk.lib.util.m;
import java.io.Serializable;

/* ThirdPartyApp */
public class c implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private long e;
    private long f;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void e() {
        this.f = System.currentTimeMillis();
    }

    public long f() {
        return this.f;
    }

    public boolean g() {
        if (m.a(this.b) || m.a(this.c) || m.a(this.d)) {
            return false;
        }
        return true;
    }

    public long h() {
        return this.e;
    }
}
