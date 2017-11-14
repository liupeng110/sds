package com.sds.android.ttpod.component.landscape;

import com.sds.android.ttpod.component.landscape.b.i;
import java.util.ArrayList;
import java.util.Iterator;

/* ActionManager */
public final class a implements com.sds.android.ttpod.component.landscape.i.a {
    private static a a = null;
    private ArrayList<a> b = new ArrayList(5);
    private ArrayList<a> c = new ArrayList(5);
    private ArrayList<a> d = new ArrayList(5);

    /* ActionManager */
    private class a {
        final /* synthetic */ a a;
        private i b;
        private com.sds.android.ttpod.component.landscape.a.a c;

        private a(a aVar) {
            this.a = aVar;
        }
    }

    public static a a() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private a() {
        i.a().a(this, 5);
    }

    public void b() {
        i.a().a((com.sds.android.ttpod.component.landscape.i.a) this);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            i a = ((a) it.next()).b;
            if (a != null) {
                a.a();
            }
        }
        this.b.clear();
        it = this.c.iterator();
        while (it.hasNext()) {
            a = ((a) it.next()).b;
            if (a != null) {
                a.a();
            }
        }
        this.c.clear();
        it = this.d.iterator();
        while (it.hasNext()) {
            a = ((a) it.next()).b;
            if (a != null) {
                a.a();
            }
        }
        this.d.clear();
        a = null;
    }

    private a a(i iVar) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (iVar == aVar.b) {
                return aVar;
            }
        }
        return null;
    }

    public void a(i iVar, com.sds.android.ttpod.component.landscape.a.a aVar) {
        a a = a(iVar);
        if (a == null) {
            a = new a();
            a.b = iVar;
            a.c = aVar;
            this.c.add(a);
        } else if (a.c != aVar) {
            this.d.add(a);
            a = new a();
            a.b = iVar;
            a.c = aVar;
            this.c.add(a);
        }
        aVar.a(iVar);
    }

    public void a(float f) {
        Iterator it;
        if (this.d.size() > 0) {
            it = this.d.iterator();
            while (it.hasNext()) {
                this.b.remove((a) it.next());
            }
            this.d.clear();
        }
        if (this.c.size() > 0) {
            it = this.c.iterator();
            while (it.hasNext()) {
                this.b.add((a) it.next());
            }
            this.c.clear();
        }
        for (int size = this.b.size() - 1; size >= 0; size--) {
            a aVar = (a) this.b.get(size);
            aVar.c.a(f);
            if (aVar.c.a()) {
                this.b.remove(aVar);
            }
        }
    }
}
