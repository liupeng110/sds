package com.igexin.push.e.b;

import android.os.Message;
import com.igexin.push.core.a;
import com.igexin.push.core.a.f;
import java.util.concurrent.TimeUnit;

public class b extends h {
    private static b a;

    public b() {
        super(60000);
        this.A = true;
    }

    public static b g() {
        if (a == null) {
            a = new b();
        }
        return a;
    }

    private void h() {
        a(360000, TimeUnit.MILLISECONDS);
    }

    protected void a() {
        f.a().C();
        Message message = new Message();
        message.what = a.j;
        com.igexin.push.core.f.a().a(message);
    }

    public int b() {
        return 0;
    }

    public void c() {
        super.c();
        if (!this.x) {
            h();
        }
    }
}
