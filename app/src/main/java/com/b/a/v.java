package com.b.a;

import com.b.a.b.j;
import com.b.a.d.c;
import java.io.IOException;

/* TreeTypeAdapter */
final class v<T> extends w<T> {
    private final s<T> a;
    private final k<T> b;
    private final f c;
    private final com.b.a.c.a<T> d;
    private final x e;
    private w<T> f;

    /* TreeTypeAdapter */
    private static class a implements x {
        private final com.b.a.c.a<?> a;
        private final boolean b;
        private final Class<?> c;
        private final s<?> d;
        private final k<?> e;

        private a(Object obj, com.b.a.c.a<?> aVar, boolean z, Class<?> cls) {
            this.d = obj instanceof s ? (s) obj : null;
            if (obj instanceof k) {
                obj = (k) obj;
            } else {
                obj = null;
            }
            this.e = obj;
            boolean z2 = (this.d == null && this.e == null) ? false : true;
            com.b.a.b.a.a(z2);
            this.a = aVar;
            this.b = z;
            this.c = cls;
        }

        public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
            boolean isAssignableFrom = this.a != null ? this.a.equals(aVar) || (this.b && this.a.b() == aVar.a()) : this.c.isAssignableFrom(aVar.a());
            if (isAssignableFrom) {
                return new v(this.d, this.e, fVar, aVar, this);
            }
            return null;
        }
    }

    private v(s<T> sVar, k<T> kVar, f fVar, com.b.a.c.a<T> aVar, x xVar) {
        this.a = sVar;
        this.b = kVar;
        this.c = fVar;
        this.d = aVar;
        this.e = xVar;
    }

    public T b(com.b.a.d.a aVar) throws IOException {
        if (this.b == null) {
            return a().b(aVar);
        }
        l a = j.a(aVar);
        if (a.j()) {
            return null;
        }
        return this.b.b(a, this.d.b(), this.c.a);
    }

    public void a(c cVar, T t) throws IOException {
        if (this.a == null) {
            a().a(cVar, t);
        } else if (t == null) {
            cVar.f();
        } else {
            j.a(this.a.a(t, this.d.b(), this.c.b), cVar);
        }
    }

    private w<T> a() {
        w<T> wVar = this.f;
        if (wVar != null) {
            return wVar;
        }
        wVar = this.c.a(this.e, this.d);
        this.f = wVar;
        return wVar;
    }

    public static x a(com.b.a.c.a<?> aVar, Object obj) {
        return new a(obj, aVar, false, null);
    }
}
