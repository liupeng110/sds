package com.sds.android.ttpod.component.danmaku.c.b.a;

import com.sds.android.ttpod.component.danmaku.c.b.j;
import com.sds.android.ttpod.component.danmaku.c.b.k;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/* Danmakus */
public class c implements k {
    public Collection<com.sds.android.ttpod.component.danmaku.c.b.c> a;
    private c b;
    private com.sds.android.ttpod.component.danmaku.c.b.c c;
    private com.sds.android.ttpod.component.danmaku.c.b.c d;
    private com.sds.android.ttpod.component.danmaku.c.b.c e;
    private com.sds.android.ttpod.component.danmaku.c.b.c f;
    private b g;
    private int h;
    private int i;
    private a j;
    private boolean k;

    /* Danmakus */
    private class a implements Comparator<com.sds.android.ttpod.component.danmaku.c.b.c> {
        protected boolean a;
        final /* synthetic */ c b;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.sds.android.ttpod.component.danmaku.c.b.c) obj, (com.sds.android.ttpod.component.danmaku.c.b.c) obj2);
        }

        public a(c cVar, boolean z) {
            this.b = cVar;
            a(z);
        }

        public void a(boolean z) {
            this.a = z;
        }

        public int a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, com.sds.android.ttpod.component.danmaku.c.b.c cVar2) {
            if (this.a && com.sds.android.ttpod.component.danmaku.c.e.b.a(cVar, cVar2)) {
                return 0;
            }
            return com.sds.android.ttpod.component.danmaku.c.e.b.b(cVar, cVar2);
        }
    }

    /* Danmakus */
    private class b implements j {
        final /* synthetic */ c a;
        private Collection<com.sds.android.ttpod.component.danmaku.c.b.c> b;
        private Iterator<com.sds.android.ttpod.component.danmaku.c.b.c> c;
        private boolean d;

        public b(c cVar, Collection<com.sds.android.ttpod.component.danmaku.c.b.c> collection) {
            this.a = cVar;
            a(collection);
        }

        public synchronized void d() {
            if (this.d || this.c == null) {
                if (this.b == null || this.a.h <= 0) {
                    this.c = null;
                } else {
                    this.c = this.b.iterator();
                }
            }
        }

        public synchronized void a(Collection<com.sds.android.ttpod.component.danmaku.c.b.c> collection) {
            if (this.b != collection) {
                this.d = false;
                this.c = null;
            }
            this.b = collection;
        }

        public synchronized com.sds.android.ttpod.component.danmaku.c.b.c a() {
            this.d = true;
            return this.c != null ? (com.sds.android.ttpod.component.danmaku.c.b.c) this.c.next() : null;
        }

        public synchronized boolean b() {
            boolean z;
            z = this.c != null && this.c.hasNext();
            return z;
        }

        public synchronized void c() {
            this.d = true;
            if (this.c != null) {
                this.c.remove();
            }
        }
    }

    /* Danmakus */
    private class c extends a {
        final /* synthetic */ c c;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.sds.android.ttpod.component.danmaku.c.b.c) obj, (com.sds.android.ttpod.component.danmaku.c.b.c) obj2);
        }

        public c(c cVar, boolean z) {
            this.c = cVar;
            super(cVar, z);
        }

        public int a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, com.sds.android.ttpod.component.danmaku.c.b.c cVar2) {
            return super.a(cVar, cVar2);
        }
    }

    /* Danmakus */
    private class d extends a {
        final /* synthetic */ c c;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.sds.android.ttpod.component.danmaku.c.b.c) obj, (com.sds.android.ttpod.component.danmaku.c.b.c) obj2);
        }

        public d(c cVar, boolean z) {
            this.c = cVar;
            super(cVar, z);
        }

        public int a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, com.sds.android.ttpod.component.danmaku.c.b.c cVar2) {
            if (this.a && com.sds.android.ttpod.component.danmaku.c.e.b.a(cVar, cVar2)) {
                return 0;
            }
            int compare = Float.compare(cVar.i(), cVar2.i());
            return compare == 0 ? com.sds.android.ttpod.component.danmaku.c.e.b.b(cVar, cVar2) : compare;
        }
    }

    /* Danmakus */
    private class e extends a {
        final /* synthetic */ c c;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((com.sds.android.ttpod.component.danmaku.c.b.c) obj, (com.sds.android.ttpod.component.danmaku.c.b.c) obj2);
        }

        public e(c cVar, boolean z) {
            this.c = cVar;
            super(cVar, z);
        }

        public int a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, com.sds.android.ttpod.component.danmaku.c.b.c cVar2) {
            if (this.a && com.sds.android.ttpod.component.danmaku.c.e.b.a(cVar, cVar2)) {
                return 0;
            }
            int compare = Float.compare(cVar2.i(), cVar.i());
            return compare == 0 ? com.sds.android.ttpod.component.danmaku.c.e.b.b(cVar, cVar2) : compare;
        }
    }

    public c() {
        this(0, false);
    }

    public c(int i) {
        this(i, false);
    }

    public c(int i, boolean z) {
        this.h = 0;
        this.i = 0;
        Object obj = null;
        if (i == 0) {
            obj = new c(this, z);
        } else if (i == 1) {
            obj = new d(this, z);
        } else if (i == 2) {
            obj = new e(this, z);
        }
        if (i == 4) {
            this.a = new LinkedList();
        } else {
            this.k = z;
            obj.a(z);
            this.a = new TreeSet(obj);
            this.j = obj;
        }
        this.i = i;
        this.h = 0;
        this.g = new b(this, this.a);
    }

    public c(Collection<com.sds.android.ttpod.component.danmaku.c.b.c> collection) {
        this.h = 0;
        this.i = 0;
        a((Collection) collection);
    }

    public c(boolean z) {
        this(0, z);
    }

    public void a(Collection<com.sds.android.ttpod.component.danmaku.c.b.c> collection) {
        if (!this.k || this.i == 4) {
            this.a = collection;
        } else {
            this.a.clear();
            this.a.addAll(collection);
            collection = this.a;
        }
        if (collection instanceof List) {
            this.i = 4;
        }
        this.h = collection == null ? 0 : collection.size();
        if (this.g == null) {
            this.g = new b(this, collection);
        } else {
            this.g.a(collection);
        }
    }

    public j e() {
        this.g.d();
        return this.g;
    }

    public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (this.a != null) {
            try {
                if (this.a.add(cVar)) {
                    this.h++;
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean b(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (cVar == null) {
            return false;
        }
        if (cVar.f()) {
            cVar.a(false);
        }
        if (!this.a.remove(cVar)) {
            return false;
        }
        this.h--;
        return true;
    }

    private Collection<com.sds.android.ttpod.component.danmaku.c.b.c> c(long j, long j2) {
        if (this.i == 4 || this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            this.b = new c(this.k);
        }
        if (this.f == null) {
            this.f = a("start");
        }
        if (this.e == null) {
            this.e = a("end");
        }
        this.f.a = j;
        this.e.a = j2;
        return ((SortedSet) this.a).subSet(this.f, this.e);
    }

    public k a(long j, long j2) {
        return new c(c(j, j2));
    }

    public k b(long j, long j2) {
        if (this.i == 4 || this.a == null || this.a.size() == 0) {
            return null;
        }
        if (this.b == null) {
            this.b = new c(this.k);
        }
        if (this.c == null) {
            this.c = a("start");
        }
        if (this.d == null) {
            this.d = a("end");
        }
        if (this.b != null && j - this.c.a >= 0 && j2 <= this.d.a) {
            return this.b;
        }
        this.c.a = j;
        this.d.a = j2;
        this.b.a(((SortedSet) this.a).subSet(this.c, this.d));
        return this.b;
    }

    private com.sds.android.ttpod.component.danmaku.c.b.c a(String str) {
        return new com.sds.android.ttpod.component.danmaku.c.b.d(str);
    }

    public int a() {
        return this.h;
    }

    public void b() {
        if (this.a != null) {
            this.a.clear();
            this.h = 0;
        }
        if (this.b != null) {
            this.b.b();
        }
    }

    public com.sds.android.ttpod.component.danmaku.c.b.c c() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.i == 4) {
            return (com.sds.android.ttpod.component.danmaku.c.b.c) ((LinkedList) this.a).getFirst();
        }
        return (com.sds.android.ttpod.component.danmaku.c.b.c) ((SortedSet) this.a).first();
    }

    public com.sds.android.ttpod.component.danmaku.c.b.c d() {
        if (this.a == null || this.a.isEmpty()) {
            return null;
        }
        if (this.i == 4) {
            return (com.sds.android.ttpod.component.danmaku.c.b.c) ((LinkedList) this.a).getLast();
        }
        return (com.sds.android.ttpod.component.danmaku.c.b.c) ((SortedSet) this.a).last();
    }

    public boolean c(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        return this.a != null && this.a.contains(cVar);
    }

    public boolean f() {
        return this.a == null || this.a.isEmpty();
    }

    public int g() {
        return this.a != null ? this.a.size() : 0;
    }
}
