package c.a;

import com.tencent.stat.DeviceInfo;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* IdSnapshot */
public class p implements an<p, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("IdSnapshot");
    private static final bd f = new bd("identity", (byte) 11, (short) 1);
    private static final bd g = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 2);
    private static final bd h = new bd("version", (byte) 8, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public String a;
    public long b;
    public int c;
    private byte j = (byte) 0;

    /* IdSnapshot */
    private static class a extends bq<p> {
        private a() {
        }

        public void a(bg bgVar, p pVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!pVar.c()) {
                        throw new bh("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (pVar.e()) {
                        pVar.f();
                        return;
                    } else {
                        throw new bh("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        pVar.a = bgVar.v();
                        pVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        pVar.b = bgVar.t();
                        pVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        pVar.c = bgVar.s();
                        pVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, p pVar) throws ar {
            pVar.f();
            bgVar.a(p.e);
            if (pVar.a != null) {
                bgVar.a(p.f);
                bgVar.a(pVar.a);
                bgVar.b();
            }
            bgVar.a(p.g);
            bgVar.a(pVar.b);
            bgVar.b();
            bgVar.a(p.h);
            bgVar.a(pVar.c);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* IdSnapshot */
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

    /* IdSnapshot */
    private static class c extends br<p> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (p) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (p) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, p pVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(pVar.a);
            bnVar.a(pVar.b);
            bnVar.a(pVar.c);
        }

        public void b(bg bgVar, p pVar) throws ar {
            bn bnVar = (bn) bgVar;
            pVar.a = bnVar.v();
            pVar.a(true);
            pVar.b = bnVar.t();
            pVar.b(true);
            pVar.c = bnVar.s();
            pVar.c(true);
        }
    }

    /* IdSnapshot */
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

    /* IdSnapshot */
    public enum e implements as {
        IDENTITY((short) 1, "identity"),
        TS((short) 2, DeviceInfo.TAG_TIMESTAMPS),
        VERSION((short) 3, "version");
        
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
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
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
        enumMap.put(e.IDENTITY, new aw("identity", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        enumMap.put(e.VERSION, new aw("version", (byte) 1, new ax((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(p.class, d);
    }

    public String a() {
        return this.a;
    }

    public p a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public long b() {
        return this.b;
    }

    public p a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public boolean c() {
        return al.a(this.j, 0);
    }

    public void b(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public int d() {
        return this.c;
    }

    public p a(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public boolean e() {
        return al.a(this.j, 1);
    }

    public void c(boolean z) {
        this.j = al.a(this.j, 1, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) i.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) i.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdSnapshot(");
        stringBuilder.append("identity:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.c);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void f() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'identity' was not present! Struct: " + toString());
        }
    }
}
