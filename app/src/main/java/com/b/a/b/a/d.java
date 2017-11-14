package com.b.a.b.a;

import com.b.a.a.b;
import com.b.a.b.c;
import com.b.a.c.a;
import com.b.a.f;
import com.b.a.w;
import com.b.a.x;

/* JsonAdapterAnnotationTypeAdapterFactory */
public final class d implements x {
    private final c a;

    public d(c cVar) {
        this.a = cVar;
    }

    public <T> w<T> a(f fVar, a<T> aVar) {
        b bVar = (b) aVar.a().getAnnotation(b.class);
        if (bVar == null) {
            return null;
        }
        return a(fVar, this.a, bVar);
    }

    static w<?> a(f fVar, c cVar, b bVar) {
        w<?> wVar = (w) cVar.a(a.b(bVar.a())).a();
        f.a.a(fVar, wVar);
        return wVar;
    }
}
