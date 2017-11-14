package com.b.a;

import com.b.a.b.a.f;
import com.b.a.d.a;
import com.b.a.d.c;
import java.io.IOException;

/* TypeAdapter */
public abstract class w<T> {
    public abstract void a(c cVar, T t) throws IOException;

    public abstract T b(a aVar) throws IOException;

    public final l a(T t) {
        try {
            c fVar = new f();
            a(fVar, t);
            return fVar.a();
        } catch (Throwable e) {
            throw new m(e);
        }
    }
}
