package com.sds.android.ttpod.component.landscape.temporary;

import java.util.ArrayList;
import java.util.Iterator;

/* ShowFrame */
public final class a {
    private static a d;
    private long a;
    private int b = 0;
    private ArrayList<a> c = new ArrayList();

    /* ShowFrame */
    public interface a {
        void a(float f);
    }

    public static a a() {
        if (d == null) {
            d = new a();
        }
        return d;
    }

    private a() {
    }

    public void b() {
        this.c.clear();
    }

    public void c() {
        b();
        d = null;
    }

    public void d() {
        this.b = 0;
    }

    public void e() {
        if (this.c.size() > 0) {
            if (this.b == 0) {
                this.a = System.currentTimeMillis();
                this.b = 1;
            } else if (this.b == 50) {
                long currentTimeMillis = System.currentTimeMillis();
                int i = 50000 / ((int) (currentTimeMillis - this.a));
                Iterator it = this.c.iterator();
                while (it.hasNext()) {
                    a aVar = (a) it.next();
                    if (aVar != null) {
                        aVar.a((float) i);
                    }
                }
                this.a = currentTimeMillis;
                this.b = 1;
            } else {
                this.b++;
            }
        }
    }
}
