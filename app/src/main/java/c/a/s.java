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

/* ImprintValue */
public class s implements an<s, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("ImprintValue");
    private static final bd f = new bd("value", (byte) 11, (short) 1);
    private static final bd g = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 2);
    private static final bd h = new bd("guid", (byte) 11, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public String a;
    public long b;
    public String c;
    private byte j = (byte) 0;
    private e[] k = new e[]{e.VALUE};

    /* ImprintValue */
    private static class a extends bq<s> {
        private a() {
        }

        public void a(bg bgVar, s sVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (sVar.d()) {
                        sVar.f();
                        return;
                    }
                    throw new bh("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        sVar.a = bgVar.v();
                        sVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        sVar.b = bgVar.t();
                        sVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        sVar.c = bgVar.v();
                        sVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, s sVar) throws ar {
            sVar.f();
            bgVar.a(s.e);
            if (sVar.a != null && sVar.b()) {
                bgVar.a(s.f);
                bgVar.a(sVar.a);
                bgVar.b();
            }
            bgVar.a(s.g);
            bgVar.a(sVar.b);
            bgVar.b();
            if (sVar.c != null) {
                bgVar.a(s.h);
                bgVar.a(sVar.c);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* ImprintValue */
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

    /* ImprintValue */
    private static class c extends br<s> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (s) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (s) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, s sVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(sVar.b);
            bnVar.a(sVar.c);
            BitSet bitSet = new BitSet();
            if (sVar.b()) {
                bitSet.set(0);
            }
            bnVar.a(bitSet, 1);
            if (sVar.b()) {
                bnVar.a(sVar.a);
            }
        }

        public void b(bg bgVar, s sVar) throws ar {
            bn bnVar = (bn) bgVar;
            sVar.b = bnVar.t();
            sVar.b(true);
            sVar.c = bnVar.v();
            sVar.c(true);
            if (bnVar.b(1).get(0)) {
                sVar.a = bnVar.v();
                sVar.a(true);
            }
        }
    }

    /* ImprintValue */
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

    /* ImprintValue */
    public enum e implements as {
        VALUE((short) 1, "value"),
        TS((short) 2, DeviceInfo.TAG_TIMESTAMPS),
        GUID((short) 3, "guid");
        
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
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
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
        enumMap.put(e.VALUE, new aw("value", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        enumMap.put(e.GUID, new aw("guid", (byte) 1, new ax((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(s.class, d);
    }

    public String a() {
        return this.a;
    }

    public boolean b() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public long c() {
        return this.b;
    }

    public boolean d() {
        return al.a(this.j, 0);
    }

    public void b(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public String e() {
        return this.c;
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
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (b()) {
            stringBuilder.append("value:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void f() throws ar {
        if (this.c == null) {
            throw new bh("Required field 'guid' was not present! Struct: " + toString());
        }
    }
}
