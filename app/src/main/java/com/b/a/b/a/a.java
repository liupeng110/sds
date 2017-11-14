package com.b.a.b.a;

import com.b.a.b.b;
import com.b.a.d.c;
import com.b.a.f;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* ArrayTypeAdapter */
public final class a<E> extends w<Object> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
            Type b = aVar.b();
            if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
                return null;
            }
            b = b.g(b);
            return new a(fVar, fVar.a(com.b.a.c.a.a(b)), b.e(b));
        }
    };
    private final Class<E> b;
    private final w<E> c;

    public a(f fVar, w<E> wVar, Class<E> cls) {
        this.c = new l(fVar, wVar, cls);
        this.b = cls;
    }

    public Object b(com.b.a.d.a aVar) throws IOException {
        if (aVar.f() == com.b.a.d.b.NULL) {
            aVar.j();
            return null;
        }
        List arrayList = new ArrayList();
        aVar.a();
        while (aVar.e()) {
            arrayList.add(this.c.b(aVar));
        }
        aVar.b();
        Object newInstance = Array.newInstance(this.b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void a(c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.f();
            return;
        }
        cVar.b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.c.a(cVar, Array.get(obj, i));
        }
        cVar.c();
    }
}
