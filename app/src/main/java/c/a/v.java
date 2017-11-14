package c.a;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* MiscInfo */
public class v implements an<v, e>, Serializable, Cloneable {
    public static final Map<e, aw> l;
    private static final bm m = new bm("MiscInfo");
    private static final bd n = new bd("time_zone", (byte) 8, (short) 1);
    private static final bd o = new bd("language", (byte) 11, (short) 2);
    private static final bd p = new bd("country", (byte) 11, (short) 3);
    private static final bd q = new bd(ParamKey.LATITUDE, (byte) 4, (short) 4);
    private static final bd r = new bd(ParamKey.LONGITUDE, (byte) 4, (short) 5);
    private static final bd s = new bd("carrier", (byte) 11, (short) 6);
    private static final bd t = new bd("latency", (byte) 8, (short) 7);
    private static final bd u = new bd("display_name", (byte) 11, (short) 8);
    private static final bd v = new bd("access_type", (byte) 8, (short) 9);
    private static final bd w = new bd("access_subtype", (byte) 11, (short) 10);
    private static final bd x = new bd("user_info", (byte) 12, (short) 11);
    private static final Map<Class<? extends bo>, bp> y = new HashMap();
    private e[] A = new e[]{e.TIME_ZONE, e.LANGUAGE, e.COUNTRY, e.LATITUDE, e.LONGITUDE, e.CARRIER, e.LATENCY, e.DISPLAY_NAME, e.ACCESS_TYPE, e.ACCESS_SUBTYPE, e.USER_INFO};
    public int a;
    public String b;
    public String c;
    public double d;
    public double e;
    public String f;
    public int g;
    public String h;
    public f i;
    public String j;
    public af k;
    private byte z = (byte) 0;

    /* MiscInfo */
    private static class a extends bq<v> {
        private a() {
        }

        public void a(bg bgVar, v vVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    vVar.l();
                    return;
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.a = bgVar.s();
                        vVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.b = bgVar.v();
                        vVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.c = bgVar.v();
                        vVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 4) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.d = bgVar.u();
                        vVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 4) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.e = bgVar.u();
                        vVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.f = bgVar.v();
                        vVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.g = bgVar.s();
                        vVar.g(true);
                        break;
                    case (short) 8:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.h = bgVar.v();
                        vVar.h(true);
                        break;
                    case (short) 9:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.i = f.a(bgVar.s());
                        vVar.i(true);
                        break;
                    case (short) 10:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.j = bgVar.v();
                        vVar.j(true);
                        break;
                    case (short) 11:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        vVar.k = new af();
                        vVar.k.a(bgVar);
                        vVar.k(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, v vVar) throws ar {
            vVar.l();
            bgVar.a(v.m);
            if (vVar.a()) {
                bgVar.a(v.n);
                bgVar.a(vVar.a);
                bgVar.b();
            }
            if (vVar.b != null && vVar.b()) {
                bgVar.a(v.o);
                bgVar.a(vVar.b);
                bgVar.b();
            }
            if (vVar.c != null && vVar.c()) {
                bgVar.a(v.p);
                bgVar.a(vVar.c);
                bgVar.b();
            }
            if (vVar.d()) {
                bgVar.a(v.q);
                bgVar.a(vVar.d);
                bgVar.b();
            }
            if (vVar.e()) {
                bgVar.a(v.r);
                bgVar.a(vVar.e);
                bgVar.b();
            }
            if (vVar.f != null && vVar.f()) {
                bgVar.a(v.s);
                bgVar.a(vVar.f);
                bgVar.b();
            }
            if (vVar.g()) {
                bgVar.a(v.t);
                bgVar.a(vVar.g);
                bgVar.b();
            }
            if (vVar.h != null && vVar.h()) {
                bgVar.a(v.u);
                bgVar.a(vVar.h);
                bgVar.b();
            }
            if (vVar.i != null && vVar.i()) {
                bgVar.a(v.v);
                bgVar.a(vVar.i.a());
                bgVar.b();
            }
            if (vVar.j != null && vVar.j()) {
                bgVar.a(v.w);
                bgVar.a(vVar.j);
                bgVar.b();
            }
            if (vVar.k != null && vVar.k()) {
                bgVar.a(v.x);
                vVar.k.b(bgVar);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* MiscInfo */
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

    /* MiscInfo */
    private static class c extends br<v> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (v) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (v) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, v vVar) throws ar {
            bgVar = (bn) bgVar;
            BitSet bitSet = new BitSet();
            if (vVar.a()) {
                bitSet.set(0);
            }
            if (vVar.b()) {
                bitSet.set(1);
            }
            if (vVar.c()) {
                bitSet.set(2);
            }
            if (vVar.d()) {
                bitSet.set(3);
            }
            if (vVar.e()) {
                bitSet.set(4);
            }
            if (vVar.f()) {
                bitSet.set(5);
            }
            if (vVar.g()) {
                bitSet.set(6);
            }
            if (vVar.h()) {
                bitSet.set(7);
            }
            if (vVar.i()) {
                bitSet.set(8);
            }
            if (vVar.j()) {
                bitSet.set(9);
            }
            if (vVar.k()) {
                bitSet.set(10);
            }
            bgVar.a(bitSet, 11);
            if (vVar.a()) {
                bgVar.a(vVar.a);
            }
            if (vVar.b()) {
                bgVar.a(vVar.b);
            }
            if (vVar.c()) {
                bgVar.a(vVar.c);
            }
            if (vVar.d()) {
                bgVar.a(vVar.d);
            }
            if (vVar.e()) {
                bgVar.a(vVar.e);
            }
            if (vVar.f()) {
                bgVar.a(vVar.f);
            }
            if (vVar.g()) {
                bgVar.a(vVar.g);
            }
            if (vVar.h()) {
                bgVar.a(vVar.h);
            }
            if (vVar.i()) {
                bgVar.a(vVar.i.a());
            }
            if (vVar.j()) {
                bgVar.a(vVar.j);
            }
            if (vVar.k()) {
                vVar.k.b(bgVar);
            }
        }

        public void b(bg bgVar, v vVar) throws ar {
            bgVar = (bn) bgVar;
            BitSet b = bgVar.b(11);
            if (b.get(0)) {
                vVar.a = bgVar.s();
                vVar.a(true);
            }
            if (b.get(1)) {
                vVar.b = bgVar.v();
                vVar.b(true);
            }
            if (b.get(2)) {
                vVar.c = bgVar.v();
                vVar.c(true);
            }
            if (b.get(3)) {
                vVar.d = bgVar.u();
                vVar.d(true);
            }
            if (b.get(4)) {
                vVar.e = bgVar.u();
                vVar.e(true);
            }
            if (b.get(5)) {
                vVar.f = bgVar.v();
                vVar.f(true);
            }
            if (b.get(6)) {
                vVar.g = bgVar.s();
                vVar.g(true);
            }
            if (b.get(7)) {
                vVar.h = bgVar.v();
                vVar.h(true);
            }
            if (b.get(8)) {
                vVar.i = f.a(bgVar.s());
                vVar.i(true);
            }
            if (b.get(9)) {
                vVar.j = bgVar.v();
                vVar.j(true);
            }
            if (b.get(10)) {
                vVar.k = new af();
                vVar.k.a(bgVar);
                vVar.k(true);
            }
        }
    }

    /* MiscInfo */
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

    /* MiscInfo */
    public enum e implements as {
        TIME_ZONE((short) 1, "time_zone"),
        LANGUAGE((short) 2, "language"),
        COUNTRY((short) 3, "country"),
        LATITUDE((short) 4, ParamKey.LATITUDE),
        LONGITUDE((short) 5, ParamKey.LONGITUDE),
        CARRIER((short) 6, "carrier"),
        LATENCY((short) 7, "latency"),
        DISPLAY_NAME((short) 8, "display_name"),
        ACCESS_TYPE((short) 9, "access_type"),
        ACCESS_SUBTYPE((short) 10, "access_subtype"),
        USER_INFO((short) 11, "user_info");
        
        private static final Map<String, e> l = null;
        private final short m;
        private final String n;

        static {
            l = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                l.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return TIME_ZONE;
                case 2:
                    return LANGUAGE;
                case 3:
                    return COUNTRY;
                case 4:
                    return LATITUDE;
                case 5:
                    return LONGITUDE;
                case 6:
                    return CARRIER;
                case 7:
                    return LATENCY;
                case 8:
                    return DISPLAY_NAME;
                case 9:
                    return ACCESS_TYPE;
                case 10:
                    return ACCESS_SUBTYPE;
                case 11:
                    return USER_INFO;
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
            return (e) l.get(str);
        }

        private e(short s, String str) {
            this.m = s;
            this.n = str;
        }

        public short a() {
            return this.m;
        }

        public String b() {
            return this.n;
        }
    }

    static {
        y.put(bq.class, new b());
        y.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.TIME_ZONE, new aw("time_zone", (byte) 2, new ax((byte) 8)));
        enumMap.put(e.LANGUAGE, new aw("language", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.COUNTRY, new aw("country", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.LATITUDE, new aw(ParamKey.LATITUDE, (byte) 2, new ax((byte) 4)));
        enumMap.put(e.LONGITUDE, new aw(ParamKey.LONGITUDE, (byte) 2, new ax((byte) 4)));
        enumMap.put(e.CARRIER, new aw("carrier", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.LATENCY, new aw("latency", (byte) 2, new ax((byte) 8)));
        enumMap.put(e.DISPLAY_NAME, new aw("display_name", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.ACCESS_TYPE, new aw("access_type", (byte) 2, new av((byte) 16, f.class)));
        enumMap.put(e.ACCESS_SUBTYPE, new aw("access_subtype", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.USER_INFO, new aw("user_info", (byte) 2, new ba((byte) 12, af.class)));
        l = Collections.unmodifiableMap(enumMap);
        aw.a(v.class, l);
    }

    public v a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public boolean a() {
        return al.a(this.z, 0);
    }

    public void a(boolean z) {
        this.z = al.a(this.z, 0, z);
    }

    public v a(String str) {
        this.b = str;
        return this;
    }

    public boolean b() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public v b(String str) {
        this.c = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public boolean d() {
        return al.a(this.z, 1);
    }

    public void d(boolean z) {
        this.z = al.a(this.z, 1, z);
    }

    public boolean e() {
        return al.a(this.z, 2);
    }

    public void e(boolean z) {
        this.z = al.a(this.z, 2, z);
    }

    public v c(String str) {
        this.f = str;
        return this;
    }

    public boolean f() {
        return this.f != null;
    }

    public void f(boolean z) {
        if (!z) {
            this.f = null;
        }
    }

    public boolean g() {
        return al.a(this.z, 3);
    }

    public void g(boolean z) {
        this.z = al.a(this.z, 3, z);
    }

    public boolean h() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public v a(f fVar) {
        this.i = fVar;
        return this;
    }

    public boolean i() {
        return this.i != null;
    }

    public void i(boolean z) {
        if (!z) {
            this.i = null;
        }
    }

    public v d(String str) {
        this.j = str;
        return this;
    }

    public boolean j() {
        return this.j != null;
    }

    public void j(boolean z) {
        if (!z) {
            this.j = null;
        }
    }

    public v a(af afVar) {
        this.k = afVar;
        return this;
    }

    public boolean k() {
        return this.k != null;
    }

    public void k(boolean z) {
        if (!z) {
            this.k = null;
        }
    }

    public void a(bg bgVar) throws ar {
        ((bp) y.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) y.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("MiscInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("time_zone:");
            stringBuilder.append(this.a);
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("language:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("country:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
            obj2 = null;
        }
        if (d()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("latitude:");
            stringBuilder.append(this.d);
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("longitude:");
            stringBuilder.append(this.e);
            obj2 = null;
        }
        if (f()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("carrier:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
            obj2 = null;
        }
        if (g()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("latency:");
            stringBuilder.append(this.g);
            obj2 = null;
        }
        if (h()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("display_name:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
            obj2 = null;
        }
        if (i()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("access_type:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
            obj2 = null;
        }
        if (j()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("access_subtype:");
            if (this.j == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.j);
            }
        } else {
            obj = obj2;
        }
        if (k()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("user_info:");
            if (this.k == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.k);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws ar {
        if (this.k != null) {
            this.k.e();
        }
    }
}
