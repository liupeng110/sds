package c.a;

import com.tencent.stat.DeviceInfo;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Location */
public class u implements an<u, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("Location");
    private static final bd f = new bd("lat", (byte) 4, (short) 1);
    private static final bd g = new bd("lng", (byte) 4, (short) 2);
    private static final bd h = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public double a;
    public double b;
    public long c;
    private byte j;

    /* Location */
    private static class a extends bq<u> {
        private a() {
        }

        public void a(bg bgVar, u uVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!uVar.a()) {
                        throw new bh("Required field 'lat' was not found in serialized data! Struct: " + toString());
                    } else if (!uVar.b()) {
                        throw new bh("Required field 'lng' was not found in serialized data! Struct: " + toString());
                    } else if (uVar.c()) {
                        uVar.d();
                        return;
                    } else {
                        throw new bh("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 4) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        uVar.a = bgVar.u();
                        uVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 4) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        uVar.b = bgVar.u();
                        uVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        uVar.c = bgVar.t();
                        uVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, u uVar) throws ar {
            uVar.d();
            bgVar.a(u.e);
            bgVar.a(u.f);
            bgVar.a(uVar.a);
            bgVar.b();
            bgVar.a(u.g);
            bgVar.a(uVar.b);
            bgVar.b();
            bgVar.a(u.h);
            bgVar.a(uVar.c);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* Location */
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

    /* Location */
    private static class c extends br<u> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (u) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (u) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, u uVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(uVar.a);
            bnVar.a(uVar.b);
            bnVar.a(uVar.c);
        }

        public void b(bg bgVar, u uVar) throws ar {
            bn bnVar = (bn) bgVar;
            uVar.a = bnVar.u();
            uVar.a(true);
            uVar.b = bnVar.u();
            uVar.b(true);
            uVar.c = bnVar.t();
            uVar.c(true);
        }
    }

    /* Location */
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

    /* Location */
    public enum e implements as {
        LAT((short) 1, "lat"),
        LNG((short) 2, "lng"),
        TS((short) 3, DeviceInfo.TAG_TIMESTAMPS);
        
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
                    return LAT;
                case 2:
                    return LNG;
                case 3:
                    return TS;
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
        enumMap.put(e.LAT, new aw("lat", (byte) 1, new ax((byte) 4)));
        enumMap.put(e.LNG, new aw("lng", (byte) 1, new ax((byte) 4)));
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(u.class, d);
    }

    public u() {
        this.j = (byte) 0;
    }

    public u(double d, double d2, long j) {
        this();
        this.a = d;
        a(true);
        this.b = d2;
        b(true);
        this.c = j;
        c(true);
    }

    public boolean a() {
        return al.a(this.j, 0);
    }

    public void a(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public boolean b() {
        return al.a(this.j, 1);
    }

    public void b(boolean z) {
        this.j = al.a(this.j, 1, z);
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
        StringBuilder stringBuilder = new StringBuilder("Location(");
        stringBuilder.append("lat:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("lng:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.c);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void d() throws ar {
    }
}
