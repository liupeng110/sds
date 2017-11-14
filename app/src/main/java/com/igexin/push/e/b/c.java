package com.igexin.push.e.b;

import com.igexin.push.core.a.f;
import com.igexin.push.core.bean.PushTaskBean;

public class c extends h {
    private PushTaskBean a;
    private String b;

    public c(PushTaskBean pushTaskBean, String str, long j) {
        super(j);
        this.A = false;
        this.a = pushTaskBean;
        this.b = str;
    }

    protected void a() {
        f.a().b(this.a, this.b);
    }

    public int b() {
        return 0;
    }

    public void c() {
        super.c();
    }
}
