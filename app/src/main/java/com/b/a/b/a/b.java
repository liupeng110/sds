package com.b.a.b.a;

import com.b.a.b.c;
import com.b.a.b.h;
import com.b.a.f;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* CollectionTypeAdapterFactory */
public final class b implements x {
    private final c a;

    /* CollectionTypeAdapterFactory */
    private static final class a<E> extends w<Collection<E>> {
        private final w<E> a;
        private final h<? extends Collection<E>> b;

        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public a(f fVar, Type type, w<E> wVar, h<? extends Collection<E>> hVar) {
            this.a = new l(fVar, wVar, type);
            this.b = hVar;
        }

        public Collection<E> a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == com.b.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            Collection<E> collection = (Collection) this.b.a();
            aVar.a();
            while (aVar.e()) {
                collection.add(this.a.b(aVar));
            }
            aVar.b();
            return collection;
        }

        public void a(com.b.a.d.c cVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (E a : collection) {
                this.a.a(cVar, a);
            }
            cVar.c();
        }
    }

    public b(c cVar) {
        this.a = cVar;
    }

    public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
        Type b = aVar.b();
        Class a = aVar.a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        Type a2 = com.b.a.b.b.a(b, a);
        return new a(fVar, a2, fVar.a(com.b.a.c.a.a(a2)), this.a.a((com.b.a.c.a) aVar));
    }
}
