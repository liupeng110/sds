package com.b.a;

import com.b.a.b.d;
import com.b.a.c.a;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* GsonBuilder */
public final class g {
    private d a = d.a;
    private u b = u.DEFAULT;
    private e c = d.IDENTITY;
    private final Map<Type, h<?>> d = new HashMap();
    private final List<x> e = new ArrayList();
    private final List<x> f = new ArrayList();
    private boolean g;
    private String h;
    private int i = 2;
    private int j = 2;
    private boolean k;
    private boolean l;
    private boolean m = true;
    private boolean n;
    private boolean o;

    public f a() {
        List arrayList = new ArrayList();
        arrayList.addAll(this.e);
        Collections.reverse(arrayList);
        arrayList.addAll(this.f);
        a(this.h, this.i, this.j, arrayList);
        return new f(this.a, this.c, this.d, this.g, this.k, this.o, this.m, this.n, this.l, this.b, arrayList);
    }

    private void a(String str, int i, int i2, List<x> list) {
        Object aVar;
        if (str != null && !"".equals(str.trim())) {
            aVar = new a(str);
        } else if (i != 2 && i2 != 2) {
            aVar = new a(i, i2);
        } else {
            return;
        }
        list.add(v.a(a.b(Date.class), aVar));
        list.add(v.a(a.b(Timestamp.class), aVar));
        list.add(v.a(a.b(java.sql.Date.class), aVar));
    }
}
