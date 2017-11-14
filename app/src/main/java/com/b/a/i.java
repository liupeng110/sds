package com.b.a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JsonArray */
public final class i extends l implements Iterable<l> {
    private final List<l> a = new ArrayList();

    public void a(l lVar) {
        if (lVar == null) {
            lVar = n.a;
        }
        this.a.add(lVar);
    }

    public Iterator<l> iterator() {
        return this.a.iterator();
    }

    public Number a() {
        if (this.a.size() == 1) {
            return ((l) this.a.get(0)).a();
        }
        throw new IllegalStateException();
    }

    public String b() {
        if (this.a.size() == 1) {
            return ((l) this.a.get(0)).b();
        }
        throw new IllegalStateException();
    }

    public double c() {
        if (this.a.size() == 1) {
            return ((l) this.a.get(0)).c();
        }
        throw new IllegalStateException();
    }

    public long d() {
        if (this.a.size() == 1) {
            return ((l) this.a.get(0)).d();
        }
        throw new IllegalStateException();
    }

    public int e() {
        if (this.a.size() == 1) {
            return ((l) this.a.get(0)).e();
        }
        throw new IllegalStateException();
    }

    public boolean f() {
        if (this.a.size() == 1) {
            return ((l) this.a.get(0)).f();
        }
        throw new IllegalStateException();
    }

    public boolean equals(Object obj) {
        return obj == this || ((obj instanceof i) && ((i) obj).a.equals(this.a));
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
