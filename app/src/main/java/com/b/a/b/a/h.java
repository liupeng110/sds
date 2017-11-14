package com.b.a.b.a;

import com.b.a.b.g;
import com.b.a.c.a;
import com.b.a.d.c;
import com.b.a.f;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* ObjectTypeAdapter */
public final class h extends w<Object> {
    public static final x a = new x() {
        public <T> w<T> a(f fVar, a<T> aVar) {
            if (aVar.a() == Object.class) {
                return new h(fVar);
            }
            return null;
        }
    };
    private final f b;

    private h(f fVar) {
        this.b = fVar;
    }

    public Object b(com.b.a.d.a aVar) throws IOException {
        switch (aVar.f()) {
            case BEGIN_ARRAY:
                List arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(b(aVar));
                }
                aVar.b();
                return arrayList;
            case BEGIN_OBJECT:
                Object gVar = new g();
                aVar.c();
                while (aVar.e()) {
                    gVar.put(aVar.g(), b(aVar));
                }
                aVar.d();
                return gVar;
            case STRING:
                return aVar.h();
            case NUMBER:
                return Double.valueOf(aVar.k());
            case BOOLEAN:
                return Boolean.valueOf(aVar.i());
            case NULL:
                aVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void a(c cVar, Object obj) throws IOException {
        if (obj == null) {
            cVar.f();
            return;
        }
        w a = this.b.a(obj.getClass());
        if (a instanceof h) {
            cVar.d();
            cVar.e();
            return;
        }
        a.a(cVar, obj);
    }
}
