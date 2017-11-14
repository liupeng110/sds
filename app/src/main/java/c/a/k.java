package c.a;

import com.tencent.stat.DeviceInfo;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Error */
public class k implements an<k, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("Error");
    private static final bd f = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 1);
    private static final bd g = new bd("context", (byte) 11, (short) 2);
    private static final bd h = new bd("source", (byte) 8, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public long a;
    public String b;
    public l c;
    private byte j = (byte) 0;
    private e[] k = new e[]{e.SOURCE};

    /* Error */
    private static class a extends bq<k> {
        private a() {
        }

        public void a(bg bgVar, k kVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (kVar.a()) {
                        kVar.c();
                        return;
                    }
                    throw new bh("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        kVar.a = bgVar.t();
                        kVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        kVar.b = bgVar.v();
                        kVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        kVar.c = l.a(bgVar.s());
                        kVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, k kVar) throws ar {
            kVar.c();
            bgVar.a(k.e);
            bgVar.a(k.f);
            bgVar.a(kVar.a);
            bgVar.b();
            if (kVar.b != null) {
                bgVar.a(k.g);
                bgVar.a(kVar.b);
                bgVar.b();
            }
            if (kVar.c != null && kVar.b()) {
                bgVar.a(k.h);
                bgVar.a(kVar.c.a());
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* Error */
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

    /* Error */
    private static class c extends br<k> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (k) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (k) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, k kVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(kVar.a);
            bnVar.a(kVar.b);
            BitSet bitSet = new BitSet();
            if (kVar.b()) {
                bitSet.set(0);
            }
            bnVar.a(bitSet, 1);
            if (kVar.b()) {
                bnVar.a(kVar.c.a());
            }
        }

        public void b(bg bgVar, k kVar) throws ar {
            bn bnVar = (bn) bgVar;
            kVar.a = bnVar.t();
            kVar.a(true);
            kVar.b = bnVar.v();
            kVar.b(true);
            if (bnVar.b(1).get(0)) {
                kVar.c = l.a(bnVar.s());
                kVar.c(true);
            }
        }
    }

    /* Error */
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

    /* Error */
    public enum e implements as {
        TS((short) 1, DeviceInfo.TAG_TIMESTAMPS),
        CONTEXT((short) 2, "context"),
        SOURCE((short) 3, "source");
        
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
                    return TS;
                case 2:
                    return CONTEXT;
                case 3:
                    return SOURCE;
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
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        enumMap.put(e.CONTEXT, new aw("context", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.SOURCE, new aw("source", (byte) 2, new av((byte) 16, l.class)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(k.class, d);
    }

    public k a(long j) {
        this.a = j;
        a(true);
        return this;
    }

    public boolean a() {
        return al.a(this.j, 0);
    }

    public void a(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public k a(String str) {
        this.b = str;
        return this;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public k a(l lVar) {
        this.c = lVar;
        return this;
    }

    public boolean b() {
        return this.c != null;
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
        StringBuilder stringBuilder = new StringBuilder("Error(");
        stringBuilder.append("ts:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("context:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("source:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void c() throws ar {
        if (this.b == null) {
            throw new bh("Required field 'context' was not present! Struct: " + toString());
        }
    }
}
