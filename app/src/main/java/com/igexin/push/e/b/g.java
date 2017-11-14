package com.igexin.push.e.b;

import com.igexin.push.a.k;
import com.igexin.push.core.a.f;
import com.igexin.push.core.c;
import com.igexin.push.d.a;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class g extends h {
    private static g b;
    private boolean a;

    public g() {
        super(3600000);
        this.a = false;
        this.A = true;
    }

    public static g g() {
        if (b == null) {
            b = new g();
        }
        return b;
    }

    protected void a() {
        f.a().C();
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

    public void h() {
        long j = 3600000;
        long currentTimeMillis = System.currentTimeMillis();
        if (k.f != 0) {
            Calendar instance = Calendar.getInstance();
            a aVar;
            if (f.a().a(currentTimeMillis)) {
                if (!this.a) {
                    this.a = true;
                    aVar = new a();
                    aVar.a(c.stop);
                    com.igexin.push.core.f.a().f().a(aVar);
                }
                if (k.e + k.f > 24) {
                    instance.set(11, (k.e + k.f) - 24);
                } else {
                    instance.set(11, k.e + k.f);
                }
                instance.set(12, 0);
                instance.set(13, 0);
                if (instance.getTimeInMillis() < currentTimeMillis) {
                    instance.add(5, 1);
                }
            } else {
                if (this.a) {
                    this.a = false;
                    aVar = new a();
                    aVar.a(c.start);
                    com.igexin.push.core.f.a().f().a(aVar);
                }
                instance.set(11, k.e);
                instance.set(12, 0);
                instance.set(13, 0);
                if (instance.getTimeInMillis() < currentTimeMillis) {
                    instance.add(5, 1);
                }
            }
            j = instance.getTimeInMillis() - currentTimeMillis;
        } else if (this.a) {
            this.a = false;
            a aVar2 = new a();
            aVar2.a(c.start);
            com.igexin.push.core.f.a().f().a(aVar2);
        }
        if (k.g > currentTimeMillis + j) {
            j = k.g - currentTimeMillis;
            if (!this.a) {
                this.a = true;
                a aVar3 = new a();
                aVar3.a(c.stop);
                com.igexin.push.core.f.a().f().a(aVar3);
            }
        }
        a(j, TimeUnit.MILLISECONDS);
    }
}
