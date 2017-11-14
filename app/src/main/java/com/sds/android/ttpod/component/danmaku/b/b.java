package com.sds.android.ttpod.component.danmaku.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/* DanmakuFilters */
public class b {
    private static b c = null;
    private static final Map<String, e<?>> d = Collections.synchronizedSortedMap(new TreeMap());
    public final Exception a = new Exception("not suuport this filter tag");
    e<?>[] b = new e[0];

    /* DanmakuFilters */
    public interface e<T> {
        void a();

        void a(T t);

        boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z);
    }

    /* DanmakuFilters */
    public static abstract class a<T> implements e<T> {
        public void a() {
        }
    }

    /* DanmakuFilters */
    public static class b extends a<Void> {
        protected final com.sds.android.ttpod.component.danmaku.c.b.k a = new com.sds.android.ttpod.component.danmaku.c.b.a.c(4);
        protected final LinkedHashMap<String, com.sds.android.ttpod.component.danmaku.c.b.c> b = new LinkedHashMap();
        private final com.sds.android.ttpod.component.danmaku.c.b.k c = new com.sds.android.ttpod.component.danmaku.c.b.a.c(4);

        private final void a(com.sds.android.ttpod.component.danmaku.c.b.k kVar, long j) {
            com.sds.android.ttpod.component.danmaku.c.b.j e = kVar.e();
            long currentTimeMillis = System.currentTimeMillis();
            while (e.b()) {
                try {
                    if (e.a().e()) {
                        e.c();
                        if (System.currentTimeMillis() - currentTimeMillis > j) {
                            return;
                        }
                    }
                    return;
                } catch (Exception e2) {
                    return;
                }
            }
        }

        private void a(LinkedHashMap<String, com.sds.android.ttpod.component.danmaku.c.b.c> linkedHashMap, int i) {
            Iterator it = linkedHashMap.entrySet().iterator();
            long currentTimeMillis = System.currentTimeMillis();
            while (it.hasNext()) {
                try {
                    if (((com.sds.android.ttpod.component.danmaku.c.b.c) ((Entry) it.next()).getValue()).e()) {
                        it.remove();
                        if (System.currentTimeMillis() - currentTimeMillis > ((long) i)) {
                            return;
                        }
                    }
                    return;
                } catch (Exception e) {
                    return;
                }
            }
        }

        public synchronized boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            boolean z2 = true;
            synchronized (this) {
                a(this.a, 2);
                a(this.c, 2);
                a(this.b, 3);
                if (!this.a.c(cVar) || cVar.f()) {
                    if (this.c.c(cVar)) {
                        z2 = false;
                    } else if (this.b.containsKey(cVar.b)) {
                        this.b.put(cVar.b, cVar);
                        this.a.b(cVar);
                        this.a.a(cVar);
                    } else {
                        this.b.put(cVar.b, cVar);
                        this.c.a(cVar);
                        z2 = false;
                    }
                }
            }
            return z2;
        }

        public void a(Void voidR) {
        }

        public synchronized void b() {
            this.c.b();
            this.a.b();
            this.b.clear();
        }

        public void a() {
            b();
        }
    }

    /* DanmakuFilters */
    public static class c extends a<Object> {
        long a = 20;
        protected final com.sds.android.ttpod.component.danmaku.c.b.k b = new com.sds.android.ttpod.component.danmaku.c.b.a.c();

        public synchronized boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            boolean z2 = true;
            synchronized (this) {
                if (this.b.d() != null && this.b.d().e()) {
                    this.b.b();
                }
                if (!this.b.c(cVar)) {
                    if (eVar != null) {
                        if (cVar.f()) {
                            if (System.currentTimeMillis() - eVar.a >= this.a) {
                                this.b.a(cVar);
                            } else {
                                z2 = false;
                            }
                        }
                    }
                    z2 = false;
                }
            }
            return z2;
        }

        public void a(Object obj) {
            b();
        }

        public synchronized void b() {
            this.b.b();
        }

        public void a() {
            b();
        }
    }

    /* DanmakuFilters */
    public static class d extends a<Boolean> {
        private Boolean a = Boolean.valueOf(false);

        public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            if (this.a.booleanValue()) {
                return cVar.v;
            }
            return false;
        }

        public void a(Boolean bool) {
            this.a = bool;
        }
    }

    /* DanmakuFilters */
    public static class f extends a<Integer> {
        protected int a = 40;
        protected final com.sds.android.ttpod.component.danmaku.c.b.k b = new com.sds.android.ttpod.component.danmaku.c.b.a.c();
        protected com.sds.android.ttpod.component.danmaku.c.b.c c = null;

        public synchronized boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            boolean z2 = true;
            synchronized (this) {
                com.sds.android.ttpod.component.danmaku.c.b.c d = this.b.d();
                if (d != null && d.e()) {
                    this.b.b();
                }
                if (this.a <= 0 || cVar.l() != 0 || cVar.s) {
                    z2 = false;
                } else if (!this.b.c(cVar)) {
                    if (i2 < this.a || cVar.d() || (this.c != null && cVar.a - this.c.a > 500)) {
                        this.c = cVar;
                        z2 = false;
                    } else if (i <= this.a || cVar.e()) {
                        this.c = cVar;
                        z2 = false;
                    } else {
                        this.b.a(cVar);
                    }
                }
            }
            return z2;
        }

        public void a(Integer num) {
            b();
            if (num != null && num.intValue() != this.a) {
                this.a = num.intValue();
            }
        }

        public synchronized void b() {
            this.b.b();
        }

        public void a() {
            b();
        }
    }

    /* DanmakuFilters */
    public static class g extends a<Integer> {
        protected com.sds.android.ttpod.component.danmaku.c.b.c a = null;
        protected com.sds.android.ttpod.component.danmaku.c.b.c b = null;

        public synchronized boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            boolean z2 = false;
            synchronized (this) {
                int l = cVar.l();
                if (l == 1 || l == 2) {
                    if (!cVar.s) {
                        if (l == 1) {
                            if (this.a == null || cVar.d() || cVar.a - this.a.a > 980) {
                                this.a = cVar;
                            } else {
                                z2 = true;
                            }
                        } else if (this.b == null || cVar.d() || cVar.a - this.b.a > 980) {
                            this.b = cVar;
                        } else {
                            z2 = true;
                        }
                    }
                }
            }
            return z2;
        }

        public void a(Integer num) {
            b();
        }

        public synchronized void b() {
            this.a = null;
            this.b = null;
        }

        public void a() {
            b();
        }
    }

    /* DanmakuFilters */
    public static class h extends a<List<Integer>> {
        public List<Integer> a = new ArrayList();

        private void a(Integer num) {
            if (!this.a.contains(num)) {
                this.a.add(num);
            }
        }

        public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            return (cVar == null || this.a.contains(Integer.valueOf(cVar.d))) ? false : true;
        }

        public void a(List<Integer> list) {
            b();
            if (list != null) {
                for (Integer a : list) {
                    a(a);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    /* DanmakuFilters */
    public static class i extends a<List<Integer>> {
        final List<Integer> a = Collections.synchronizedList(new ArrayList());

        public void a(Integer num) {
            if (!this.a.contains(num)) {
                this.a.add(num);
            }
        }

        public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            return cVar != null && this.a.contains(Integer.valueOf(cVar.l()));
        }

        public void a(List<Integer> list) {
            b();
            if (list != null) {
                for (Integer a : list) {
                    a(a);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    /* DanmakuFilters */
    public static abstract class j<T> extends a<List<T>> {
        public List<T> a = new ArrayList();

        private void b(T t) {
            if (!this.a.contains(t)) {
                this.a.add(t);
            }
        }

        public void a(List<T> list) {
            b();
            if (list != null) {
                for (T b : list) {
                    b(b);
                }
            }
        }

        public void b() {
            this.a.clear();
        }
    }

    /* DanmakuFilters */
    public static class k extends j<String> {
        public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            return cVar != null && this.a.contains(cVar.u);
        }
    }

    /* DanmakuFilters */
    public static class l extends j<Integer> {
        public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
            return cVar != null && this.a.contains(Long.valueOf(cVar.t));
        }
    }

    public boolean a(com.sds.android.ttpod.component.danmaku.c.b.c cVar, int i, int i2, com.sds.android.ttpod.component.danmaku.c.b.e eVar, boolean z) {
        for (e eVar2 : this.b) {
            if (eVar2 != null && eVar2.a(cVar, i, i2, eVar, z)) {
                return true;
            }
        }
        return false;
    }

    public e<?> a(String str) {
        return (e) d.get(str);
    }

    public e<?> b(String str) {
        if (str == null) {
            d();
            return null;
        }
        e<?> iVar;
        e<?> eVar = (e) d.get(str);
        if (eVar == null) {
            if ("1010_Filter".equals(str)) {
                iVar = new i();
            } else if ("1011_Filter".equals(str)) {
                r2 = new f();
            } else if ("1012_Filter".equals(str)) {
                r2 = new c();
            } else if ("1013_Filter".equals(str)) {
                r2 = new h();
            } else if ("1014_Filter".equals(str)) {
                r2 = new l();
            } else if ("1015_Filter".equals(str)) {
                r2 = new k();
            } else if ("1016_Filter".equals(str)) {
                r2 = new d();
            } else if ("1017_Filter".equals(str)) {
                r2 = new b();
            } else if ("1111_Filter".equals(str)) {
                r2 = new g();
            }
            if (iVar != null) {
                d();
                return null;
            }
            iVar.a(null);
            d.put(str, iVar);
            this.b = (e[]) d.values().toArray(this.b);
            return iVar;
        }
        iVar = eVar;
        if (iVar != null) {
            iVar.a(null);
            d.put(str, iVar);
            this.b = (e[]) d.values().toArray(this.b);
            return iVar;
        }
        d();
        return null;
    }

    public void c(String str) {
        e eVar = (e) d.remove(str);
        if (eVar != null) {
            eVar.a();
            this.b = (e[]) d.values().toArray(this.b);
        }
    }

    public void a() {
        for (e eVar : this.b) {
            if (eVar != null) {
                eVar.a();
            }
        }
    }

    public void b() {
        a();
        d.clear();
        this.b = new e[0];
    }

    private void d() {
        try {
            throw this.a;
        } catch (Exception e) {
        }
    }

    public static b c() {
        if (c == null) {
            c = new b();
        }
        return c;
    }
}
