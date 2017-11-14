package c.a;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* ClientStats */
public class i implements an<i, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("ClientStats");
    private static final bd f = new bd("successful_requests", (byte) 8, (short) 1);
    private static final bd g = new bd("failed_requests", (byte) 8, (short) 2);
    private static final bd h = new bd("last_request_spent_ms", (byte) 8, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public int a = 0;
    public int b = 0;
    public int c;
    private byte j = (byte) 0;
    private e[] k = new e[]{e.LAST_REQUEST_SPENT_MS};

    /* ClientStats */
    private static class a extends bq<i> {
        private a() {
        }

        public void a(bg bgVar, i iVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!iVar.a()) {
                        throw new bh("Required field 'successful_requests' was not found in serialized data! Struct: " + toString());
                    } else if (iVar.b()) {
                        iVar.d();
                        return;
                    } else {
                        throw new bh("Required field 'failed_requests' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        iVar.a = bgVar.s();
                        iVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        iVar.b = bgVar.s();
                        iVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        iVar.c = bgVar.s();
                        iVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, i iVar) throws ar {
            iVar.d();
            bgVar.a(i.e);
            bgVar.a(i.f);
            bgVar.a(iVar.a);
            bgVar.b();
            bgVar.a(i.g);
            bgVar.a(iVar.b);
            bgVar.b();
            if (iVar.c()) {
                bgVar.a(i.h);
                bgVar.a(iVar.c);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* ClientStats */
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

    /* ClientStats */
    private static class c extends br<i> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (i) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (i) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, i iVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(iVar.a);
            bnVar.a(iVar.b);
            BitSet bitSet = new BitSet();
            if (iVar.c()) {
                bitSet.set(0);
            }
            bnVar.a(bitSet, 1);
            if (iVar.c()) {
                bnVar.a(iVar.c);
            }
        }

        public void b(bg bgVar, i iVar) throws ar {
            bn bnVar = (bn) bgVar;
            iVar.a = bnVar.s();
            iVar.a(true);
            iVar.b = bnVar.s();
            iVar.b(true);
            if (bnVar.b(1).get(0)) {
                iVar.c = bnVar.s();
                iVar.c(true);
            }
        }
    }

    /* ClientStats */
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

    /* ClientStats */
    public enum e implements as {
        SUCCESSFUL_REQUESTS((short) 1, "successful_requests"),
        FAILED_REQUESTS((short) 2, "failed_requests"),
        LAST_REQUEST_SPENT_MS((short) 3, "last_request_spent_ms");
        
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
                    return SUCCESSFUL_REQUESTS;
                case 2:
                    return FAILED_REQUESTS;
                case 3:
                    return LAST_REQUEST_SPENT_MS;
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
        enumMap.put(e.SUCCESSFUL_REQUESTS, new aw("successful_requests", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.FAILED_REQUESTS, new aw("failed_requests", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.LAST_REQUEST_SPENT_MS, new aw("last_request_spent_ms", (byte) 2, new ax((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(i.class, d);
    }

    public i a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public boolean a() {
        return al.a(this.j, 0);
    }

    public void a(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public i b(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public boolean b() {
        return al.a(this.j, 1);
    }

    public void b(boolean z) {
        this.j = al.a(this.j, 1, z);
    }

    public i c(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public boolean c() {
        return al.a(this.j, 2);
    }

    public void c(boolean z) {
        this.j = al.a(this.j, 2, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) i.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) i.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ClientStats(");
        stringBuilder.append("successful_requests:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("failed_requests:");
        stringBuilder.append(this.b);
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("last_request_spent_ms:");
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void d() throws ar {
    }
}
