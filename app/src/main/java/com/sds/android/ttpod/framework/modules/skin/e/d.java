package com.sds.android.ttpod.framework.modules.skin.e;

import java.util.ArrayList;
import java.util.Iterator;

/* LrcMtvFormattedLyric */
public class d implements a {
    private final c a;
    private final l b;
    private ArrayList<f> c;
    private int d;

    public d(c cVar, int i, l lVar) {
        this.a = cVar;
        this.b = lVar;
    }

    public int a() {
        return this.c.size();
    }

    public m a(int i) {
        return i < 0 ? null : (f) this.c.get(i);
    }

    public a c() {
        if (this.a == null) {
            return null;
        }
        int b = this.a.b();
        long j = 0;
        this.c = new ArrayList(b);
        int i = 0;
        int i2 = 0;
        while (i < b) {
            long j2;
            int i3;
            f b2 = this.a.b(i);
            if (i == b - 1 || b2.g().length() > 0) {
                a(b2);
                j2 = j;
                i3 = i2;
            } else {
                if (i2 == 0) {
                    j = b2.d();
                }
                i2 += b2.f();
                if (this.a.b(i + 1).g().length() > 0) {
                    if (i2 >= 7000) {
                        a(j, i2);
                    }
                    j2 = j;
                    i3 = 0;
                } else {
                    j2 = j;
                    i3 = i2;
                }
            }
            i++;
            i2 = i3;
            j = j2;
        }
        k.a(this.c, this.a.g());
        return this;
    }

    private void a(f fVar) {
        f fVar2 = new f(fVar);
        this.c.add(fVar2);
        fVar2.a(this.b.a(fVar.g()));
    }

    private void a(long j, int i) {
        this.c.add(new f(j, "", i, 0, 0));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((f) it.next()).toString());
        }
        return stringBuilder.toString();
    }

    public int a(long j) {
        this.d = k.a(this.c, j);
        return this.d;
    }

    public int b() {
        return this.d;
    }
}
