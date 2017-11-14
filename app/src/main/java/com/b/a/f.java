package com.b.a;

import com.b.a.b.a.g;
import com.b.a.b.a.h;
import com.b.a.b.a.i;
import com.b.a.b.a.j;
import com.b.a.b.a.k;
import com.b.a.b.a.m;
import com.b.a.b.c;
import com.b.a.b.d;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Gson */
public final class f {
    final j a;
    final r b;
    private final ThreadLocal<Map<com.b.a.c.a<?>, b<?>>> c;
    private final Map<com.b.a.c.a<?>, w<?>> d;
    private boolean e;
    private Set<w<?>> f;
    private final ThreadLocal<Set<w<?>>> g;
    private final List<x> h;
    private final c i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final boolean m;

    /* Gson */
    public static final class a {
        public static void a(f fVar, w<?> wVar) {
            if (fVar.e) {
                fVar.f.add(wVar);
            } else {
                a(fVar).add(wVar);
            }
        }

        public static boolean b(f fVar, w<?> wVar) {
            boolean contains = fVar.f.contains(wVar);
            if (contains) {
                return contains;
            }
            return a(fVar).contains(wVar);
        }

        private static Set<w<?>> a(f fVar) {
            Set<w<?>> set = (Set) fVar.g.get();
            if (set == null) {
                set = new HashSet();
            }
            fVar.g.set(set);
            return set;
        }
    }

    /* Gson */
    static class b<T> extends w<T> {
        private w<T> a;

        b() {
        }

        public void a(w<T> wVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = wVar;
        }

        public T b(com.b.a.d.a aVar) throws IOException {
            if (this.a != null) {
                return this.a.b(aVar);
            }
            throw new IllegalStateException();
        }

        public void a(com.b.a.d.c cVar, T t) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(cVar, t);
        }
    }

    public f() {
        this(d.a, d.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, u.DEFAULT, Collections.emptyList());
    }

    f(d dVar, e eVar, Map<Type, h<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, u uVar, List<x> list) {
        this.c = new ThreadLocal();
        this.d = Collections.synchronizedMap(new HashMap());
        this.e = true;
        this.f = new HashSet();
        this.g = new ThreadLocal();
        this.a = new j(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }
        };
        this.b = new r(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }
        };
        this.i = new c(map);
        this.j = z;
        this.l = z3;
        this.k = z4;
        this.m = z5;
        List arrayList = new ArrayList();
        arrayList.add(m.Q);
        arrayList.add(h.a);
        arrayList.add(dVar);
        arrayList.addAll(list);
        arrayList.add(m.x);
        arrayList.add(m.m);
        arrayList.add(m.g);
        arrayList.add(m.i);
        arrayList.add(m.k);
        arrayList.add(m.a(Long.TYPE, Long.class, a(uVar)));
        arrayList.add(m.a(Double.TYPE, Double.class, a(z6)));
        arrayList.add(m.a(Float.TYPE, Float.class, b(z6)));
        arrayList.add(m.r);
        arrayList.add(m.t);
        arrayList.add(m.z);
        arrayList.add(m.B);
        arrayList.add(m.a(BigDecimal.class, m.v));
        arrayList.add(m.a(BigInteger.class, m.w));
        arrayList.add(m.D);
        arrayList.add(m.F);
        arrayList.add(m.J);
        arrayList.add(m.O);
        arrayList.add(m.H);
        arrayList.add(m.d);
        arrayList.add(com.b.a.b.a.c.a);
        arrayList.add(m.M);
        arrayList.add(k.a);
        arrayList.add(j.a);
        arrayList.add(m.K);
        arrayList.add(com.b.a.b.a.a.a);
        arrayList.add(m.R);
        arrayList.add(m.b);
        arrayList.add(new com.b.a.b.a.b(this.i));
        arrayList.add(new g(this.i, z2));
        arrayList.add(new com.b.a.b.a.d(this.i));
        arrayList.add(new i(this.i, eVar, dVar));
        this.h = Collections.unmodifiableList(arrayList);
        this.f = Collections.unmodifiableSet(this.f);
        this.e = false;
    }

    private w<Number> a(boolean z) {
        if (z) {
            return m.p;
        }
        return new w<Number>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
                return a(aVar);
            }

            public Double a(com.b.a.d.a aVar) throws IOException {
                if (aVar.f() != com.b.a.d.b.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.b.a.d.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.f();
                    return;
                }
                this.a.a(number.doubleValue());
                cVar.a(number);
            }
        };
    }

    private w<Number> b(boolean z) {
        if (z) {
            return m.o;
        }
        return new w<Number>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
                return a(aVar);
            }

            public Float a(com.b.a.d.a aVar) throws IOException {
                if (aVar.f() != com.b.a.d.b.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.b.a.d.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.f();
                    return;
                }
                this.a.a((double) number.floatValue());
                cVar.a(number);
            }
        };
    }

    private void a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this" + " behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private w<Number> a(u uVar) {
        if (uVar == u.DEFAULT) {
            return m.n;
        }
        return new w<Number>(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
                return a(aVar);
            }

            public Number a(com.b.a.d.a aVar) throws IOException {
                if (aVar.f() != com.b.a.d.b.NULL) {
                    return Long.valueOf(aVar.l());
                }
                aVar.j();
                return null;
            }

            public void a(com.b.a.d.c cVar, Number number) throws IOException {
                if (number == null) {
                    cVar.f();
                } else {
                    cVar.b(number.toString());
                }
            }
        };
    }

    public <T> w<T> a(com.b.a.c.a<T> aVar) {
        w<T> wVar = (w) this.d.get(aVar);
        if (wVar == null) {
            Map map;
            Map map2 = (Map) this.c.get();
            Object obj = null;
            if (map2 == null) {
                HashMap hashMap = new HashMap();
                this.c.set(hashMap);
                map = hashMap;
                obj = 1;
            } else {
                map = map2;
            }
            b bVar = (b) map.get(aVar);
            if (bVar == null) {
                try {
                    b bVar2 = new b();
                    map.put(aVar, bVar2);
                    for (x a : this.h) {
                        wVar = a.a(this, aVar);
                        if (wVar != null) {
                            bVar2.a(wVar);
                            this.d.put(aVar, wVar);
                            map.remove(aVar);
                            if (obj != null) {
                                this.c.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + aVar);
                } catch (Throwable th) {
                    map.remove(aVar);
                    if (obj != null) {
                        this.c.remove();
                    }
                }
            }
        }
        return wVar;
    }

    public <T> w<T> a(x xVar, com.b.a.c.a<T> aVar) {
        Object obj = null;
        for (x xVar2 : this.h) {
            if (obj != null) {
                w<T> a = xVar2.a(this, aVar);
                if (a != null) {
                    return a;
                }
            } else if (xVar2 == xVar) {
                obj = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    public <T> w<T> a(Class<T> cls) {
        return a(com.b.a.c.a.b(cls));
    }

    public String a(Object obj) {
        if (obj == null) {
            return a(n.a);
        }
        return a(obj, obj.getClass());
    }

    public String a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void a(Object obj, Type type, Appendable appendable) throws m {
        try {
            a(obj, type, a(com.b.a.b.j.a(appendable)));
        } catch (Throwable e) {
            throw new m(e);
        }
    }

    public void a(Object obj, Type type, com.b.a.d.c cVar) throws m {
        w a = a(com.b.a.c.a.a(type));
        boolean g = cVar.g();
        cVar.b(true);
        boolean h = cVar.h();
        cVar.c(this.k);
        boolean i = cVar.i();
        cVar.d(this.j);
        try {
            a.a(cVar, obj);
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        } catch (Throwable e) {
            throw new m(e);
        } catch (Throwable th) {
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        }
    }

    public String a(l lVar) {
        Appendable stringWriter = new StringWriter();
        a(lVar, stringWriter);
        return stringWriter.toString();
    }

    public void a(l lVar, Appendable appendable) throws m {
        try {
            a(lVar, a(com.b.a.b.j.a(appendable)));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private com.b.a.d.c a(Writer writer) throws IOException {
        if (this.l) {
            writer.write(")]}'\n");
        }
        com.b.a.d.c cVar = new com.b.a.d.c(writer);
        if (this.m) {
            cVar.c("  ");
        }
        cVar.d(this.j);
        return cVar;
    }

    public void a(l lVar, com.b.a.d.c cVar) throws m {
        boolean g = cVar.g();
        cVar.b(true);
        boolean h = cVar.h();
        cVar.c(this.k);
        boolean i = cVar.i();
        cVar.d(this.j);
        try {
            com.b.a.b.j.a(lVar, cVar);
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        } catch (Throwable e) {
            throw new m(e);
        } catch (Throwable th) {
            cVar.b(g);
            cVar.c(h);
            cVar.d(i);
        }
    }

    public <T> T a(String str, Class<T> cls) throws t {
        return com.b.a.b.i.a((Class) cls).cast(a(str, (Type) cls));
    }

    public <T> T a(String str, Type type) throws t {
        if (str == null) {
            return null;
        }
        return a(new StringReader(str), type);
    }

    public <T> T a(Reader reader, Type type) throws m, t {
        com.b.a.d.a aVar = new com.b.a.d.a(reader);
        Object a = a(aVar, type);
        a(a, aVar);
        return a;
    }

    private static void a(Object obj, com.b.a.d.a aVar) {
        if (obj != null) {
            try {
                if (aVar.f() != com.b.a.d.b.END_DOCUMENT) {
                    throw new m("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new t(e);
            } catch (Throwable e2) {
                throw new m(e2);
            }
        }
    }

    public <T> T a(com.b.a.d.a aVar, Type type) throws m, t {
        boolean z = true;
        boolean p = aVar.p();
        aVar.a(true);
        try {
            aVar.f();
            z = false;
            T b = a(com.b.a.c.a.a(type)).b(aVar);
            aVar.a(p);
            return b;
        } catch (Throwable e) {
            if (z) {
                aVar.a(p);
                return null;
            }
            throw new t(e);
        } catch (Throwable e2) {
            throw new t(e2);
        } catch (Throwable e22) {
            throw new t(e22);
        } catch (Throwable th) {
            aVar.a(p);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.j + "factories:" + this.h + ",instanceCreators:" + this.i + "}";
    }
}
