package com.igexin.push.core.a;

import com.igexin.a.a.c.a;
import com.igexin.a.a.d.d;
import com.igexin.push.a.j;
import com.igexin.push.c.c.e;
import com.igexin.push.c.c.o;
import com.igexin.push.core.c.f;
import com.igexin.push.core.g;

public class p extends a {
    private static final String a = j.a;

    public boolean a(d dVar) {
        return false;
    }

    public boolean a(Object obj) {
        boolean z = false;
        if (obj instanceof o) {
            o oVar = (o) obj;
            a.a("registerReq|" + oVar.a + "|" + g.t);
            if (oVar.a != g.t) {
                g.p = false;
                f.a().a(oVar.a);
                g.I = 0;
                z = true;
            }
            e c = f.a().c();
            a.a("loginReqAfterRegister|" + c.a);
            a.a("newtoken|" + com.igexin.a.b.a.a(String.valueOf(g.t)));
            com.igexin.push.core.f.a().e().a("S-" + c.a, c);
            if (z) {
                return true;
            }
        }
        return true;
    }
}
