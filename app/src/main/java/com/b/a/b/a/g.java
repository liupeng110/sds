package com.b.a.b.a;

import com.b.a.b.c;
import com.b.a.b.e;
import com.b.a.b.h;
import com.b.a.b.j;
import com.b.a.d.b;
import com.b.a.f;
import com.b.a.l;
import com.b.a.q;
import com.b.a.t;
import com.b.a.w;
import com.b.a.x;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* MapTypeAdapterFactory */
public final class g implements x {
    private final c a;
    private final boolean b;

    /* MapTypeAdapterFactory */
    private final class a<K, V> extends w<Map<K, V>> {
        final /* synthetic */ g a;
        private final w<K> b;
        private final w<V> c;
        private final h<? extends Map<K, V>> d;

        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public a(g gVar, f fVar, Type type, w<K> wVar, Type type2, w<V> wVar2, h<? extends Map<K, V>> hVar) {
            this.a = gVar;
            this.b = new l(fVar, wVar, type);
            this.c = new l(fVar, wVar2, type2);
            this.d = hVar;
        }

        public Map<K, V> a(com.b.a.d.a aVar) throws IOException {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            Object b;
            if (f == b.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    b = this.b.b(aVar);
                    if (map.put(b, this.c.b(aVar)) != null) {
                        throw new t("duplicate key: " + b);
                    }
                    aVar.b();
                }
                aVar.b();
                return map;
            }
            aVar.c();
            while (aVar.e()) {
                e.a.a(aVar);
                b = this.b.b(aVar);
                if (map.put(b, this.c.b(aVar)) != null) {
                    throw new t("duplicate key: " + b);
                }
            }
            aVar.d();
            return map;
        }

        public void a(com.b.a.d.c cVar, Map<K, V> map) throws IOException {
            int i = 0;
            if (map == null) {
                cVar.f();
            } else if (this.a.b) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    int i3;
                    l a = this.b.a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    if (a.g() || a.h()) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    cVar.b();
                    while (i < arrayList.size()) {
                        cVar.b();
                        j.a((l) arrayList.get(i), cVar);
                        this.c.a(cVar, arrayList2.get(i));
                        cVar.c();
                        i++;
                    }
                    cVar.c();
                    return;
                }
                cVar.d();
                while (i < arrayList.size()) {
                    cVar.a(a((l) arrayList.get(i)));
                    this.c.a(cVar, arrayList2.get(i));
                    i++;
                }
                cVar.e();
            } else {
                cVar.d();
                for (Entry entry2 : map.entrySet()) {
                    cVar.a(String.valueOf(entry2.getKey()));
                    this.c.a(cVar, entry2.getValue());
                }
                cVar.e();
            }
        }

        private String a(l lVar) {
            if (lVar.i()) {
                q m = lVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (lVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public g(c cVar, boolean z) {
        this.a = cVar;
        this.b = z;
    }

    public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
        Type b = aVar.b();
        if (!Map.class.isAssignableFrom(aVar.a())) {
            return null;
        }
        Type[] b2 = com.b.a.b.b.b(b, com.b.a.b.b.e(b));
        w a = a(fVar, b2[0]);
        w a2 = fVar.a(com.b.a.c.a.a(b2[1]));
        h a3 = this.a.a((com.b.a.c.a) aVar);
        return new a(this, fVar, b2[0], a, b2[1], a2, a3);
    }

    private w<?> a(f fVar, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? m.f : fVar.a(com.b.a.c.a.a(type));
    }
}
