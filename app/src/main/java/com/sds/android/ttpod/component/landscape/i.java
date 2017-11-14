package com.sds.android.ttpod.component.landscape;

import java.util.ArrayList;
import java.util.Iterator;

/* Scheduler */
public final class i {
    private static i a = null;
    private ArrayList<b> b = new ArrayList(5);
    private ArrayList<b> c = new ArrayList(5);
    private ArrayList<b> d = new ArrayList(5);

    /* Scheduler */
    public interface a {
        void a(float f);
    }

    /* Scheduler */
    private class b {
        final /* synthetic */ i a;
        private a b;
        private int c;

        private b(i iVar) {
            this.a = iVar;
        }
    }

    public static i a() {
        if (a == null) {
            a = new i();
        }
        return a;
    }

    private i() {
    }

    public void b() {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a a = ((b) it.next()).b;
            if (a instanceof com.sds.android.ttpod.component.landscape.b.i) {
                ((com.sds.android.ttpod.component.landscape.b.i) a).a();
            }
        }
        this.b.clear();
        it = this.c.iterator();
        while (it.hasNext()) {
            a = ((b) it.next()).b;
            if (a instanceof com.sds.android.ttpod.component.landscape.b.i) {
                ((com.sds.android.ttpod.component.landscape.b.i) a).a();
            }
        }
        this.c.clear();
        it = this.d.iterator();
        while (it.hasNext()) {
            a = ((b) it.next()).b;
            if (a instanceof com.sds.android.ttpod.component.landscape.b.i) {
                ((com.sds.android.ttpod.component.landscape.b.i) a).a();
            }
        }
        this.d.clear();
        a = null;
    }

    private b b(a aVar) {
        if (this.b != null) {
            Iterator it = this.b.iterator();
            while (it.hasNext()) {
                b bVar = (b) it.next();
                if (bVar.b == aVar) {
                    return bVar;
                }
            }
        }
        return null;
    }

    public void a(a aVar, int i) {
        b b = b(aVar);
        if (b == null) {
            b = new b();
            b.b = aVar;
            b.c = i;
            synchronized (this.c) {
                this.c.add(b);
            }
        } else if (b.c != i) {
            synchronized (this.d) {
                this.d.add(b);
            }
            b = new b();
            b.b = aVar;
            b.c = i;
            synchronized (this.c) {
                this.c.add(b);
            }
        }
    }

    public void a(a aVar) {
        if (aVar != null) {
            b b = b(aVar);
            if (b != null) {
                synchronized (this.d) {
                    this.d.add(b);
                }
            }
        }
    }

    private void a(b bVar) {
        Object obj = null;
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((b) it.next()).c < bVar.c) {
                this.b.add(i, bVar);
                obj = 1;
                break;
            }
            i++;
        }
        if (obj == null) {
            this.b.add(bVar);
        }
    }

    public void a(float f) {
        Iterator it;
        Iterator it2 = this.b.iterator();
        while (it2.hasNext()) {
            ((b) it2.next()).b.a(f);
        }
        synchronized (this.d) {
            if (this.d.size() > 0) {
                it = this.d.iterator();
                while (it.hasNext()) {
                    this.b.remove((b) it.next());
                }
                this.d.clear();
            }
        }
        synchronized (this.c) {
            if (this.c.size() > 0) {
                it = this.c.iterator();
                while (it.hasNext()) {
                    a((b) it.next());
                }
                this.c.clear();
            }
        }
    }
}
