package c.a;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* Imprint */
public class r implements an<r, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("Imprint");
    private static final bd f = new bd("property", (byte) 13, (short) 1);
    private static final bd g = new bd("version", (byte) 8, (short) 2);
    private static final bd h = new bd("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public Map<String, s> a;
    public int b;
    public String c;
    private byte j = (byte) 0;

    /* Imprint */
    private static class a extends bq<r> {
        private a() {
        }

        public void a(bg bgVar, r rVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (rVar.c()) {
                        rVar.e();
                        return;
                    }
                    throw new bh("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 13) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        bf j = bgVar.j();
                        rVar.a = new HashMap(j.c * 2);
                        for (int i = 0; i < j.c; i++) {
                            String v = bgVar.v();
                            s sVar = new s();
                            sVar.a(bgVar);
                            rVar.a.put(v, sVar);
                        }
                        bgVar.k();
                        rVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        rVar.b = bgVar.s();
                        rVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        rVar.c = bgVar.v();
                        rVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, r rVar) throws ar {
            rVar.e();
            bgVar.a(r.e);
            if (rVar.a != null) {
                bgVar.a(r.f);
                bgVar.a(new bf((byte) 11, (byte) 12, rVar.a.size()));
                for (Entry entry : rVar.a.entrySet()) {
                    bgVar.a((String) entry.getKey());
                    ((s) entry.getValue()).b(bgVar);
                }
                bgVar.d();
                bgVar.b();
            }
            bgVar.a(r.g);
            bgVar.a(rVar.b);
            bgVar.b();
            if (rVar.c != null) {
                bgVar.a(r.h);
                bgVar.a(rVar.c);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* Imprint */
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

    /* Imprint */
    private static class c extends br<r> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (r) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (r) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, r rVar) throws ar {
            bgVar = (bn) bgVar;
            bgVar.a(rVar.a.size());
            for (Entry entry : rVar.a.entrySet()) {
                bgVar.a((String) entry.getKey());
                ((s) entry.getValue()).b(bgVar);
            }
            bgVar.a(rVar.b);
            bgVar.a(rVar.c);
        }

        public void b(bg bgVar, r rVar) throws ar {
            bgVar = (bn) bgVar;
            bf bfVar = new bf((byte) 11, (byte) 12, bgVar.s());
            rVar.a = new HashMap(bfVar.c * 2);
            for (int i = 0; i < bfVar.c; i++) {
                String v = bgVar.v();
                s sVar = new s();
                sVar.a(bgVar);
                rVar.a.put(v, sVar);
            }
            rVar.a(true);
            rVar.b = bgVar.s();
            rVar.b(true);
            rVar.c = bgVar.v();
            rVar.c(true);
        }
    }

    /* Imprint */
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

    /* Imprint */
    public enum e implements as {
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, "version"),
        CHECKSUM((short) 3, "checksum");
        
        private static final Map<String, e> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
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
            return (e) d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public short a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }
    }

    static {
        i.put(bq.class, new b());
        i.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new aw("property", (byte) 1, new az((byte) 13, new ax((byte) 11), new ba((byte) 12, s.class))));
        enumMap.put(e.VERSION, new aw("version", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.CHECKSUM, new aw("checksum", (byte) 1, new ax((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(r.class, d);
    }

    public Map<String, s> a() {
        return this.a;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int b() {
        return this.b;
    }

    public r a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public boolean c() {
        return al.a(this.j, 0);
    }

    public void b(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public String d() {
        return this.c;
    }

    public r a(String str) {
        this.c = str;
        return this;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public void a(bg bgVar) throws ar {
        ((bp) i.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) i.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void e() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bh("Required field 'checksum' was not present! Struct: " + toString());
        }
    }
}
