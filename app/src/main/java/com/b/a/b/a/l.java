package com.b.a.b.a;

import com.b.a.d.a;
import com.b.a.d.c;
import com.b.a.f;
import com.b.a.w;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* TypeAdapterRuntimeTypeWrapper */
final class l<T> extends w<T> {
    private final f a;
    private final w<T> b;
    private final Type c;

    l(f fVar, w<T> wVar, Type type) {
        this.a = fVar;
        this.b = wVar;
        this.c = type;
    }

    public T b(a aVar) throws IOException {
        return this.b.b(aVar);
    }

    public void a(c cVar, T t) throws IOException {
        w wVar = this.b;
        Type a = a(this.c, (Object) t);
        if (a != this.c) {
            wVar = this.a.a(com.b.a.c.a.a(a));
            if ((wVar instanceof i.a) && !(this.b instanceof i.a)) {
                wVar = this.b;
            }
        }
        wVar.a(cVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj == null) {
            return type;
        }
        if (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) {
            return obj.getClass();
        }
        return type;
    }
}
