package com.sds.android.ttpod.framework.modules.skin.e;

import java.util.ArrayList;
import java.util.Iterator;

/* TrcMtvFormattedLyric */
public class p implements a {
    private final o a;
    private final l b;
    private ArrayList<r> c;
    private int d;

    public p(o oVar, int i, l lVar) {
        this.a = oVar;
        this.b = lVar;
    }

    public m a(int i) {
        return i < 0 ? null : (r) this.c.get(i);
    }

    public int a() {
        return this.c.size();
    }

    public a c() {
        if (this.a == null) {
            return null;
        }
        int b = this.a.b();
        long j = 0;
        this.c = new ArrayList(b);
        int i = 0;
        for (int i2 = 0; i2 < b; i2++) {
            r rVar = (r) this.a.b(i2);
            if (i2 == b - 1) {
                a(rVar);
            } else {
                int d;
                int i3;
                int f = rVar.f();
                f b2 = this.a.b(i2 + 1);
                if (rVar.g().length() > 0) {
                    a(rVar);
                    d = (int) (b2.d() - (((long) rVar.i()) + rVar.d()));
                } else {
                    d = f;
                }
                if (d > 0) {
                    long d2;
                    if (i == 0) {
                        d2 = b2.d() - ((long) d);
                    } else {
                        d2 = j;
                    }
                    j = d2;
                    i3 = i + d;
                } else {
                    i3 = i;
                }
                if (b2.g().length() > 0) {
                    if (i3 >= 7000) {
                        a(j, i3);
                    }
                    i = 0;
                } else {
                    i = i3;
                }
            }
        }
        k.a(this.c, this.a.g());
        return this;
    }

    private void a(r rVar) {
        r rVar2 = new r(rVar);
        this.c.add(rVar2);
        rVar2.a(this.b.a(rVar.g()));
        rVar2.a(this.b);
    }

    private void a(long j, int i) {
        this.c.add(new r(j, "", i, 0, 0));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((f) it.next()).toString());
        }
        return stringBuilder.toString();
    }

    public int b() {
        return this.d;
    }

    public int a(long j) {
        this.d = k.a(this.c, j);
        return this.d;
    }
}
