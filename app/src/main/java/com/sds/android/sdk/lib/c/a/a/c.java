package com.sds.android.sdk.lib.c.a.a;

import com.sds.android.sdk.lib.c.a.b;
import java.lang.reflect.Method;

/* FieldAnnotationReader */
public abstract class c {
    public abstract boolean a(b bVar, Method method);

    public static c a() {
        return new c() {
            public boolean a(b bVar, Method method) {
                b bVar2 = (b) method.getAnnotation(b.class);
                if (bVar2 != null) {
                    bVar.a(bVar2.a());
                    bVar.b(bVar2.b());
                    bVar.a(bVar2.c());
                    bVar.c(bVar2.d());
                    bVar.b(bVar2.e());
                    bVar.a(bVar2.f());
                    bVar.b(bVar2.g());
                }
                return bVar2 != null;
            }
        };
    }
}
