package com.sds.android.ttpod.adapter.d.a;

import com.sds.android.cloudapi.ttpod.data.NewUser;

/* PrivateMessageItem */
public class e {
    private NewUser a;
    private long b;
    private String c;
    private int d;
    private String e;

    public e(NewUser newUser, String str, long j, String str2, int i) {
        this.a = newUser;
        this.b = j;
        this.c = str2;
        this.d = i;
        this.e = str;
    }

    public String a() {
        return this.c;
    }

    public long b() {
        return this.b;
    }

    public int c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public NewUser e() {
        return this.a;
    }
}
