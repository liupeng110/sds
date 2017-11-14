package com.sds.android.ttpod.framework.modules.skin.c;

import com.sds.android.ttpod.framework.modules.skin.b.m;
import com.sds.android.ttpod.framework.modules.skin.b.r;
import java.util.ArrayList;
import java.util.Iterator;

/* EventExecutor */
public class f {
    private ArrayList<c> a = new ArrayList();
    private d b;
    private int c;
    private int d;

    f(m mVar, g gVar, int i, int i2) {
        r[] c = mVar.c();
        this.c = i;
        this.d = i2;
        if (c != null) {
            for (r cVar : c) {
                this.a.add(new c(cVar, gVar));
            }
        }
    }

    public void a(int i, int i2) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(i, i2);
        }
    }

    public void a(d dVar) {
        this.b = dVar;
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((c) it.next()).a(dVar);
        }
    }
}
