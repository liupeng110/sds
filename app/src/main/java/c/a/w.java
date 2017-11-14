package c.a;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Page */
public class w implements an<w, e>, Serializable, Cloneable {
    public static final Map<e, aw> c;
    private static final bm d = new bm("Page");
    private static final bd e = new bd("page_name", (byte) 11, (short) 1);
    private static final bd f = new bd("duration", (byte) 10, (short) 2);
    private static final Map<Class<? extends bo>, bp> g = new HashMap();
    public String a;
    public long b;
    private byte h = (byte) 0;

    /* Page */
    private static class a extends bq<w> {
        private a() {
        }

        public void a(bg bgVar, w wVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (wVar.a()) {
                        wVar.b();
                        return;
                    }
                    throw new bh("Required field 'duration' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        wVar.a = bgVar.v();
                        wVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        wVar.b = bgVar.t();
                        wVar.b(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, w wVar) throws ar {
            wVar.b();
            bgVar.a(w.d);
            if (wVar.a != null) {
                bgVar.a(w.e);
                bgVar.a(wVar.a);
                bgVar.b();
            }
            bgVar.a(w.f);
            bgVar.a(wVar.b);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* Page */
    private static class b implements bp {
        public /* synthetic */ bo b() {
            return a();
        }

        private b() {
        }

        public a a() {
            return new a();
        }
    }

    /* Page */
    private static class c extends br<w> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (w) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (w) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, w wVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(wVar.a);
            bnVar.a(wVar.b);
        }

        public void b(bg bgVar, w wVar) throws ar {
            bn bnVar = (bn) bgVar;
            wVar.a = bnVar.v();
            wVar.a(true);
            wVar.b = bnVar.t();
            wVar.b(true);
        }
    }

    /* Page */
    private static class d implements bp {
        public /* synthetic */ bo b() {
            return a();
        }

        private d() {
        }

        public c a() {
            return new c();
        }
    }

    /* Page */
    public enum e implements as {
        PAGE_NAME((short) 1, "page_name"),
        DURATION((short) 2, "duration");
        
        private static final Map<String, e> c = null;
        private final short d;
        private final String e;

        static {
            c = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                c.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return PAGE_NAME;
                case 2:
                    return DURATION;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return (e) c.get(str);
        }

        private e(short s, String str) {
            this.d = s;
            this.e = str;
        }

        public short a() {
            return this.d;
        }

        public String b() {
            return this.e;
        }
    }

    static {
        g.put(bq.class, new b());
        g.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.PAGE_NAME, new aw("page_name", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.DURATION, new aw("duration", (byte) 1, new ax((byte) 10)));
        c = Collections.unmodifiableMap(enumMap);
        aw.a(w.class, c);
    }

    public w a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public w a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public boolean a() {
        return al.a(this.h, 0);
    }

    public void b(boolean z) {
        this.h = al.a(this.h, 0, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) g.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) g.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Page(");
        stringBuilder.append("page_name:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void b() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'page_name' was not present! Struct: " + toString());
        }
    }
}
