package c.a;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* DeviceInfo */
public class j implements an<j, e>, Serializable, Cloneable {
    private static final bd A = new bd("os_version", (byte) 11, (short) 8);
    private static final bd B = new bd("resolution", (byte) 12, (short) 9);
    private static final bd C = new bd("is_jailbroken", (byte) 2, (short) 10);
    private static final bd D = new bd("is_pirated", (byte) 2, (short) 11);
    private static final bd E = new bd("device_board", (byte) 11, (short) 12);
    private static final bd F = new bd("device_brand", (byte) 11, (short) 13);
    private static final bd G = new bd("device_manutime", (byte) 10, (short) 14);
    private static final bd H = new bd("device_manufacturer", (byte) 11, (short) 15);
    private static final bd I = new bd("device_manuid", (byte) 11, (short) 16);
    private static final bd J = new bd("device_name", (byte) 11, (short) 17);
    private static final Map<Class<? extends bo>, bp> K = new HashMap();
    public static final Map<e, aw> r;
    private static final bm s = new bm("DeviceInfo");
    private static final bd t = new bd("device_id", (byte) 11, (short) 1);
    private static final bd u = new bd("idmd5", (byte) 11, (short) 2);
    private static final bd v = new bd("mac_address", (byte) 11, (short) 3);
    private static final bd w = new bd("open_udid", (byte) 11, (short) 4);
    private static final bd x = new bd("model", (byte) 11, (short) 5);
    private static final bd y = new bd("cpu", (byte) 11, (short) 6);
    private static final bd z = new bd("os", (byte) 11, (short) 7);
    private byte L = (byte) 0;
    private e[] M = new e[]{e.DEVICE_ID, e.IDMD5, e.MAC_ADDRESS, e.OPEN_UDID, e.MODEL, e.CPU, e.OS, e.OS_VERSION, e.RESOLUTION, e.IS_JAILBROKEN, e.IS_PIRATED, e.DEVICE_BOARD, e.DEVICE_BRAND, e.DEVICE_MANUTIME, e.DEVICE_MANUFACTURER, e.DEVICE_MANUID, e.DEVICE_NAME};
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public z i;
    public boolean j;
    public boolean k;
    public String l;
    public String m;
    public long n;
    public String o;
    public String p;
    public String q;

    /* DeviceInfo */
    private static class a extends bq<j> {
        private a() {
        }

        public void a(bg bgVar, j jVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    jVar.r();
                    return;
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.a = bgVar.v();
                        jVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.b = bgVar.v();
                        jVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.c = bgVar.v();
                        jVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.d = bgVar.v();
                        jVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.e = bgVar.v();
                        jVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.f = bgVar.v();
                        jVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.g = bgVar.v();
                        jVar.g(true);
                        break;
                    case (short) 8:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.h = bgVar.v();
                        jVar.h(true);
                        break;
                    case (short) 9:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.i = new z();
                        jVar.i.a(bgVar);
                        jVar.i(true);
                        break;
                    case (short) 10:
                        if (h.b != (byte) 2) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.j = bgVar.p();
                        jVar.j(true);
                        break;
                    case (short) 11:
                        if (h.b != (byte) 2) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.k = bgVar.p();
                        jVar.k(true);
                        break;
                    case (short) 12:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.l = bgVar.v();
                        jVar.l(true);
                        break;
                    case (short) 13:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.m = bgVar.v();
                        jVar.m(true);
                        break;
                    case (short) 14:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.n = bgVar.t();
                        jVar.n(true);
                        break;
                    case (short) 15:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.o = bgVar.v();
                        jVar.o(true);
                        break;
                    case (short) 16:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.p = bgVar.v();
                        jVar.p(true);
                        break;
                    case (short) 17:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        jVar.q = bgVar.v();
                        jVar.q(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, j jVar) throws ar {
            jVar.r();
            bgVar.a(j.s);
            if (jVar.a != null && jVar.a()) {
                bgVar.a(j.t);
                bgVar.a(jVar.a);
                bgVar.b();
            }
            if (jVar.b != null && jVar.b()) {
                bgVar.a(j.u);
                bgVar.a(jVar.b);
                bgVar.b();
            }
            if (jVar.c != null && jVar.c()) {
                bgVar.a(j.v);
                bgVar.a(jVar.c);
                bgVar.b();
            }
            if (jVar.d != null && jVar.d()) {
                bgVar.a(j.w);
                bgVar.a(jVar.d);
                bgVar.b();
            }
            if (jVar.e != null && jVar.e()) {
                bgVar.a(j.x);
                bgVar.a(jVar.e);
                bgVar.b();
            }
            if (jVar.f != null && jVar.f()) {
                bgVar.a(j.y);
                bgVar.a(jVar.f);
                bgVar.b();
            }
            if (jVar.g != null && jVar.g()) {
                bgVar.a(j.z);
                bgVar.a(jVar.g);
                bgVar.b();
            }
            if (jVar.h != null && jVar.h()) {
                bgVar.a(j.A);
                bgVar.a(jVar.h);
                bgVar.b();
            }
            if (jVar.i != null && jVar.i()) {
                bgVar.a(j.B);
                jVar.i.b(bgVar);
                bgVar.b();
            }
            if (jVar.j()) {
                bgVar.a(j.C);
                bgVar.a(jVar.j);
                bgVar.b();
            }
            if (jVar.k()) {
                bgVar.a(j.D);
                bgVar.a(jVar.k);
                bgVar.b();
            }
            if (jVar.l != null && jVar.l()) {
                bgVar.a(j.E);
                bgVar.a(jVar.l);
                bgVar.b();
            }
            if (jVar.m != null && jVar.m()) {
                bgVar.a(j.F);
                bgVar.a(jVar.m);
                bgVar.b();
            }
            if (jVar.n()) {
                bgVar.a(j.G);
                bgVar.a(jVar.n);
                bgVar.b();
            }
            if (jVar.o != null && jVar.o()) {
                bgVar.a(j.H);
                bgVar.a(jVar.o);
                bgVar.b();
            }
            if (jVar.p != null && jVar.p()) {
                bgVar.a(j.I);
                bgVar.a(jVar.p);
                bgVar.b();
            }
            if (jVar.q != null && jVar.q()) {
                bgVar.a(j.J);
                bgVar.a(jVar.q);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* DeviceInfo */
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

    /* DeviceInfo */
    private static class c extends br<j> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (j) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (j) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, j jVar) throws ar {
            bgVar = (bn) bgVar;
            BitSet bitSet = new BitSet();
            if (jVar.a()) {
                bitSet.set(0);
            }
            if (jVar.b()) {
                bitSet.set(1);
            }
            if (jVar.c()) {
                bitSet.set(2);
            }
            if (jVar.d()) {
                bitSet.set(3);
            }
            if (jVar.e()) {
                bitSet.set(4);
            }
            if (jVar.f()) {
                bitSet.set(5);
            }
            if (jVar.g()) {
                bitSet.set(6);
            }
            if (jVar.h()) {
                bitSet.set(7);
            }
            if (jVar.i()) {
                bitSet.set(8);
            }
            if (jVar.j()) {
                bitSet.set(9);
            }
            if (jVar.k()) {
                bitSet.set(10);
            }
            if (jVar.l()) {
                bitSet.set(11);
            }
            if (jVar.m()) {
                bitSet.set(12);
            }
            if (jVar.n()) {
                bitSet.set(13);
            }
            if (jVar.o()) {
                bitSet.set(14);
            }
            if (jVar.p()) {
                bitSet.set(15);
            }
            if (jVar.q()) {
                bitSet.set(16);
            }
            bgVar.a(bitSet, 17);
            if (jVar.a()) {
                bgVar.a(jVar.a);
            }
            if (jVar.b()) {
                bgVar.a(jVar.b);
            }
            if (jVar.c()) {
                bgVar.a(jVar.c);
            }
            if (jVar.d()) {
                bgVar.a(jVar.d);
            }
            if (jVar.e()) {
                bgVar.a(jVar.e);
            }
            if (jVar.f()) {
                bgVar.a(jVar.f);
            }
            if (jVar.g()) {
                bgVar.a(jVar.g);
            }
            if (jVar.h()) {
                bgVar.a(jVar.h);
            }
            if (jVar.i()) {
                jVar.i.b(bgVar);
            }
            if (jVar.j()) {
                bgVar.a(jVar.j);
            }
            if (jVar.k()) {
                bgVar.a(jVar.k);
            }
            if (jVar.l()) {
                bgVar.a(jVar.l);
            }
            if (jVar.m()) {
                bgVar.a(jVar.m);
            }
            if (jVar.n()) {
                bgVar.a(jVar.n);
            }
            if (jVar.o()) {
                bgVar.a(jVar.o);
            }
            if (jVar.p()) {
                bgVar.a(jVar.p);
            }
            if (jVar.q()) {
                bgVar.a(jVar.q);
            }
        }

        public void b(bg bgVar, j jVar) throws ar {
            bgVar = (bn) bgVar;
            BitSet b = bgVar.b(17);
            if (b.get(0)) {
                jVar.a = bgVar.v();
                jVar.a(true);
            }
            if (b.get(1)) {
                jVar.b = bgVar.v();
                jVar.b(true);
            }
            if (b.get(2)) {
                jVar.c = bgVar.v();
                jVar.c(true);
            }
            if (b.get(3)) {
                jVar.d = bgVar.v();
                jVar.d(true);
            }
            if (b.get(4)) {
                jVar.e = bgVar.v();
                jVar.e(true);
            }
            if (b.get(5)) {
                jVar.f = bgVar.v();
                jVar.f(true);
            }
            if (b.get(6)) {
                jVar.g = bgVar.v();
                jVar.g(true);
            }
            if (b.get(7)) {
                jVar.h = bgVar.v();
                jVar.h(true);
            }
            if (b.get(8)) {
                jVar.i = new z();
                jVar.i.a(bgVar);
                jVar.i(true);
            }
            if (b.get(9)) {
                jVar.j = bgVar.p();
                jVar.j(true);
            }
            if (b.get(10)) {
                jVar.k = bgVar.p();
                jVar.k(true);
            }
            if (b.get(11)) {
                jVar.l = bgVar.v();
                jVar.l(true);
            }
            if (b.get(12)) {
                jVar.m = bgVar.v();
                jVar.m(true);
            }
            if (b.get(13)) {
                jVar.n = bgVar.t();
                jVar.n(true);
            }
            if (b.get(14)) {
                jVar.o = bgVar.v();
                jVar.o(true);
            }
            if (b.get(15)) {
                jVar.p = bgVar.v();
                jVar.p(true);
            }
            if (b.get(16)) {
                jVar.q = bgVar.v();
                jVar.q(true);
            }
        }
    }

    /* DeviceInfo */
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

    /* DeviceInfo */
    public enum e implements as {
        DEVICE_ID((short) 1, "device_id"),
        IDMD5((short) 2, "idmd5"),
        MAC_ADDRESS((short) 3, "mac_address"),
        OPEN_UDID((short) 4, "open_udid"),
        MODEL((short) 5, "model"),
        CPU((short) 6, "cpu"),
        OS((short) 7, "os"),
        OS_VERSION((short) 8, "os_version"),
        RESOLUTION((short) 9, "resolution"),
        IS_JAILBROKEN((short) 10, "is_jailbroken"),
        IS_PIRATED((short) 11, "is_pirated"),
        DEVICE_BOARD((short) 12, "device_board"),
        DEVICE_BRAND((short) 13, "device_brand"),
        DEVICE_MANUTIME((short) 14, "device_manutime"),
        DEVICE_MANUFACTURER((short) 15, "device_manufacturer"),
        DEVICE_MANUID((short) 16, "device_manuid"),
        DEVICE_NAME((short) 17, "device_name");
        
        private static final Map<String, e> r = null;
        private final short s;
        private final String t;

        static {
            r = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                r.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DEVICE_ID;
                case 2:
                    return IDMD5;
                case 3:
                    return MAC_ADDRESS;
                case 4:
                    return OPEN_UDID;
                case 5:
                    return MODEL;
                case 6:
                    return CPU;
                case 7:
                    return OS;
                case 8:
                    return OS_VERSION;
                case 9:
                    return RESOLUTION;
                case 10:
                    return IS_JAILBROKEN;
                case 11:
                    return IS_PIRATED;
                case 12:
                    return DEVICE_BOARD;
                case 13:
                    return DEVICE_BRAND;
                case 14:
                    return DEVICE_MANUTIME;
                case 15:
                    return DEVICE_MANUFACTURER;
                case 16:
                    return DEVICE_MANUID;
                case 17:
                    return DEVICE_NAME;
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
            return (e) r.get(str);
        }

        private e(short s, String str) {
            this.s = s;
            this.t = str;
        }

        public short a() {
            return this.s;
        }

        public String b() {
            return this.t;
        }
    }

    static {
        K.put(bq.class, new b());
        K.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.DEVICE_ID, new aw("device_id", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.IDMD5, new aw("idmd5", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.MAC_ADDRESS, new aw("mac_address", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.OPEN_UDID, new aw("open_udid", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.MODEL, new aw("model", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.CPU, new aw("cpu", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.OS, new aw("os", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.OS_VERSION, new aw("os_version", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.RESOLUTION, new aw("resolution", (byte) 2, new ba((byte) 12, z.class)));
        enumMap.put(e.IS_JAILBROKEN, new aw("is_jailbroken", (byte) 2, new ax((byte) 2)));
        enumMap.put(e.IS_PIRATED, new aw("is_pirated", (byte) 2, new ax((byte) 2)));
        enumMap.put(e.DEVICE_BOARD, new aw("device_board", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.DEVICE_BRAND, new aw("device_brand", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.DEVICE_MANUTIME, new aw("device_manutime", (byte) 2, new ax((byte) 10)));
        enumMap.put(e.DEVICE_MANUFACTURER, new aw("device_manufacturer", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.DEVICE_MANUID, new aw("device_manuid", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.DEVICE_NAME, new aw("device_name", (byte) 2, new ax((byte) 11)));
        r = Collections.unmodifiableMap(enumMap);
        aw.a(j.class, r);
    }

    public j a(String str) {
        this.a = str;
        return this;
    }

    public boolean a() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public j b(String str) {
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

    public j c(String str) {
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
        return this.d != null;
    }

    public void d(boolean z) {
        if (!z) {
            this.d = null;
        }
    }

    public j d(String str) {
        this.e = str;
        return this;
    }

    public boolean e() {
        return this.e != null;
    }

    public void e(boolean z) {
        if (!z) {
            this.e = null;
        }
    }

    public j e(String str) {
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

    public j f(String str) {
        this.g = str;
        return this;
    }

    public boolean g() {
        return this.g != null;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public j g(String str) {
        this.h = str;
        return this;
    }

    public boolean h() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public j a(z zVar) {
        this.i = zVar;
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

    public boolean j() {
        return al.a(this.L, 0);
    }

    public void j(boolean z) {
        this.L = al.a(this.L, 0, z);
    }

    public boolean k() {
        return al.a(this.L, 1);
    }

    public void k(boolean z) {
        this.L = al.a(this.L, 1, z);
    }

    public j h(String str) {
        this.l = str;
        return this;
    }

    public boolean l() {
        return this.l != null;
    }

    public void l(boolean z) {
        if (!z) {
            this.l = null;
        }
    }

    public j i(String str) {
        this.m = str;
        return this;
    }

    public boolean m() {
        return this.m != null;
    }

    public void m(boolean z) {
        if (!z) {
            this.m = null;
        }
    }

    public j a(long j) {
        this.n = j;
        n(true);
        return this;
    }

    public boolean n() {
        return al.a(this.L, 2);
    }

    public void n(boolean z) {
        this.L = al.a(this.L, 2, z);
    }

    public j j(String str) {
        this.o = str;
        return this;
    }

    public boolean o() {
        return this.o != null;
    }

    public void o(boolean z) {
        if (!z) {
            this.o = null;
        }
    }

    public j k(String str) {
        this.p = str;
        return this;
    }

    public boolean p() {
        return this.p != null;
    }

    public void p(boolean z) {
        if (!z) {
            this.p = null;
        }
    }

    public j l(String str) {
        this.q = str;
        return this;
    }

    public boolean q() {
        return this.q != null;
    }

    public void q(boolean z) {
        if (!z) {
            this.q = null;
        }
    }

    public void a(bg bgVar) throws ar {
        ((bp) K.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) K.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("DeviceInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("device_id:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("idmd5:");
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
            stringBuilder.append("mac_address:");
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
            stringBuilder.append("open_udid:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
            obj2 = null;
        }
        if (e()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("model:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
            obj2 = null;
        }
        if (f()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("cpu:");
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
            stringBuilder.append("os:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
            obj2 = null;
        }
        if (h()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("os_version:");
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
            stringBuilder.append("resolution:");
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
            stringBuilder.append("is_jailbroken:");
            stringBuilder.append(this.j);
            obj2 = null;
        }
        if (k()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("is_pirated:");
            stringBuilder.append(this.k);
            obj2 = null;
        }
        if (l()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_board:");
            if (this.l == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.l);
            }
            obj2 = null;
        }
        if (m()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_brand:");
            if (this.m == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.m);
            }
            obj2 = null;
        }
        if (n()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_manutime:");
            stringBuilder.append(this.n);
            obj2 = null;
        }
        if (o()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_manufacturer:");
            if (this.o == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.o);
            }
            obj2 = null;
        }
        if (p()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_manuid:");
            if (this.p == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.p);
            }
        } else {
            obj = obj2;
        }
        if (q()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("device_name:");
            if (this.q == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.q);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void r() throws ar {
        if (this.i != null) {
            this.i.c();
        }
    }
}
