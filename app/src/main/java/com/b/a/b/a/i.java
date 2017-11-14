package com.b.a.b.a;

import com.b.a.b.c;
import com.b.a.b.d;
import com.b.a.b.h;
import com.b.a.e;
import com.b.a.f;
import com.b.a.t;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/* ReflectiveTypeAdapterFactory */
public final class i implements x {
    private final c a;
    private final e b;
    private final d c;

    /* ReflectiveTypeAdapterFactory */
    static abstract class b {
        final String f;
        final boolean g;
        final boolean h;

        abstract void a(com.b.a.d.a aVar, Object obj) throws IOException, IllegalAccessException;

        abstract void a(com.b.a.d.c cVar, Object obj) throws IOException, IllegalAccessException;

        protected b(String str, boolean z, boolean z2) {
            this.f = str;
            this.g = z;
            this.h = z2;
        }
    }

    /* ReflectiveTypeAdapterFactory */
    public static final class a<T> extends w<T> {
        private final h<T> a;
        private final Map<String, b> b;

        private a(h<T> hVar, Map<String, b> map) {
            this.a = hVar;
            this.b = map;
        }

        public T b(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == com.b.a.d.b.NULL) {
                aVar.j();
                return null;
            }
            T a = this.a.a();
            try {
                aVar.c();
                while (aVar.e()) {
                    b bVar = (b) this.b.get(aVar.g());
                    if (bVar == null || !bVar.h) {
                        aVar.n();
                    } else {
                        bVar.a(aVar, (Object) a);
                    }
                }
                aVar.d();
                return a;
            } catch (Throwable e) {
                throw new t(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void a(com.b.a.d.c cVar, T t) throws IOException {
            if (t == null) {
                cVar.f();
                return;
            }
            cVar.d();
            try {
                for (b bVar : this.b.values()) {
                    if (bVar.g) {
                        cVar.a(bVar.f);
                        bVar.a(cVar, (Object) t);
                    }
                }
                cVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError();
            }
        }
    }

    public i(c cVar, e eVar, d dVar) {
        this.a = cVar;
        this.b = eVar;
        this.c = dVar;
    }

    public boolean a(Field field, boolean z) {
        return (this.c.a(field.getType(), z) || this.c.a(field, z)) ? false : true;
    }

    private String a(Field field) {
        com.b.a.a.c cVar = (com.b.a.a.c) field.getAnnotation(com.b.a.a.c.class);
        return cVar == null ? this.b.translateName(field) : cVar.a();
    }

    public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
        Class a = aVar.a();
        if (!Object.class.isAssignableFrom(a)) {
            return null;
        }
        w<T> aVar2 = new a(this.a.a((com.b.a.c.a) aVar), a(fVar, (com.b.a.c.a) aVar, a));
        com.b.a.f.a.a(fVar, aVar2);
        return aVar2;
    }

    private b a(f fVar, Field field, String str, com.b.a.c.a<?> aVar, boolean z, boolean z2) {
        com.b.a.b.i.a(aVar.a());
        final f fVar2 = fVar;
        final Field field2 = field;
        final com.b.a.c.a<?> aVar2 = aVar;
        return new b(this, str, z, z2) {
            final w<?> a = this.e.a(fVar2, field2, aVar2);
            final /* synthetic */ i e;

            void a(com.b.a.d.c cVar, Object obj) throws IOException, IllegalAccessException {
                new l(fVar2, this.a, aVar2.b()).a(cVar, field2.get(obj));
            }

            void a(com.b.a.d.a aVar, Object obj) throws IOException, IllegalAccessException {
                try {
                    Object b = this.a.b(aVar);
                    if (b != null) {
                        field2.set(obj, b);
                    }
                } catch (Exception e) {
                    aVar.n();
                }
            }
        };
    }

    private w<?> a(f fVar, Field field, com.b.a.c.a<?> aVar) {
        w<?> a = fVar.a((com.b.a.c.a) aVar);
        if (!com.b.a.f.a.b(fVar, a) || !field.isAnnotationPresent(com.b.a.a.b.class)) {
            return a;
        }
        return d.a(fVar, this.a, (com.b.a.a.b) field.getAnnotation(com.b.a.a.b.class));
    }

    private Map<String, b> a(f fVar, com.b.a.c.a<?> aVar, Class<?> cls) {
        Map<String, b> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = aVar.b();
        Class a;
        while (a != Object.class) {
            for (Field field : a.getDeclaredFields()) {
                boolean a2 = a(field, true);
                boolean a3 = a(field, false);
                if (a2 || a3) {
                    field.setAccessible(true);
                    b a4 = a(fVar, field, a(field), com.b.a.c.a.a(com.b.a.b.b.a(aVar.b(), a, field.getGenericType())), a2, a3);
                    a4 = (b) linkedHashMap.put(a4.f, a4);
                    if (a4 != null) {
                        throw new IllegalArgumentException(b + " declares multiple JSON fields named " + a4.f);
                    }
                }
            }
            aVar = com.b.a.c.a.a(com.b.a.b.b.a(aVar.b(), a, a.getGenericSuperclass()));
            a = aVar.a();
        }
        return linkedHashMap;
    }
}
