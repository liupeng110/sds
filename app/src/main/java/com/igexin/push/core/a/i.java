package com.igexin.push.core.a;

import com.igexin.a.a.c.a;
import com.igexin.a.a.d.d;
import com.igexin.push.a.j;
import com.igexin.push.c.c.h;
import com.igexin.push.core.k;

public class i extends a {
    private static final String a = j.a;

    public boolean a(d dVar) {
        return false;
    }

    public boolean a(Object obj) {
        if (obj instanceof h) {
            a.a("heartbeatRsp");
            com.igexin.push.core.i.a().a(k.HEARTBEAT_OK);
        }
        return true;
    }
}
