package c.a;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* AppInfo */
public class h implements an<h, e>, Serializable, Cloneable {
    public static final Map<e, aw> k;
    private static final bm l = new bm("AppInfo");
    private static final bd m = new bd("key", (byte) 11, (short) 1);
    private static final bd n = new bd("version", (byte) 11, (short) 2);
    private static final bd o = new bd("version_index", (byte) 8, (short) 3);
    private static final bd p = new bd("package_name", (byte) 11, (short) 4);
    private static final bd q = new bd("sdk_type", (byte) 8, (short) 5);
    private static final bd r = new bd("sdk_version", (byte) 11, (short) 6);
    private static final bd s = new bd("channel", (byte) 11, (short) 7);
    private static final bd t = new bd("wrapper_type", (byte) 11, (short) 8);
    private static final bd u = new bd("wrapper_version", (byte) 11, (short) 9);
    private static final bd v = new bd("vertical_type", (byte) 8, (short) 10);
    private static final Map<Class<? extends bo>, bp> w = new HashMap();
    public String a;
    public String b;
    public int c;
    public String d;
    public ab e;
    public String f;
    public String g;
    public String h;
    public String i;
    public int j;
    private byte x = (byte) 0;
    private e[] y = new e[]{e.VERSION, e.VERSION_INDEX, e.PACKAGE_NAME, e.WRAPPER_TYPE, e.WRAPPER_VERSION, e.VERTICAL_TYPE};

    /* AppInfo */
    private static class a extends bq<h> {
        private a() {
        }

        public void a(bg bgVar, h hVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    hVar.g();
                    return;
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.a = bgVar.v();
                        hVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.b = bgVar.v();
                        hVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.c = bgVar.s();
                        hVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.d = bgVar.v();
                        hVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.e = ab.a(bgVar.s());
                        hVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.f = bgVar.v();
                        hVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.g = bgVar.v();
                        hVar.g(true);
                        break;
                    case (short) 8:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.h = bgVar.v();
                        hVar.h(true);
                        break;
                    case (short) 9:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.i = bgVar.v();
                        hVar.i(true);
                        break;
                    case (short) 10:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        hVar.j = bgVar.s();
                        hVar.j(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, h hVar) throws ar {
            hVar.g();
            bgVar.a(h.l);
            if (hVar.a != null) {
                bgVar.a(h.m);
                bgVar.a(hVar.a);
                bgVar.b();
            }
            if (hVar.b != null && hVar.a()) {
                bgVar.a(h.n);
                bgVar.a(hVar.b);
                bgVar.b();
            }
            if (hVar.b()) {
                bgVar.a(h.o);
                bgVar.a(hVar.c);
                bgVar.b();
            }
            if (hVar.d != null && hVar.c()) {
                bgVar.a(h.p);
                bgVar.a(hVar.d);
                bgVar.b();
            }
            if (hVar.e != null) {
                bgVar.a(h.q);
                bgVar.a(hVar.e.a());
                bgVar.b();
            }
            if (hVar.f != null) {
                bgVar.a(h.r);
                bgVar.a(hVar.f);
                bgVar.b();
            }
            if (hVar.g != null) {
                bgVar.a(h.s);
                bgVar.a(hVar.g);
                bgVar.b();
            }
            if (hVar.h != null && hVar.d()) {
                bgVar.a(h.t);
                bgVar.a(hVar.h);
                bgVar.b();
            }
            if (hVar.i != null && hVar.e()) {
                bgVar.a(h.u);
                bgVar.a(hVar.i);
                bgVar.b();
            }
            if (hVar.f()) {
                bgVar.a(h.v);
                bgVar.a(hVar.j);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* AppInfo */
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

    /* AppInfo */
    private static class c extends br<h> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (h) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (h) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, h hVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(hVar.a);
            bnVar.a(hVar.e.a());
            bnVar.a(hVar.f);
            bnVar.a(hVar.g);
            BitSet bitSet = new BitSet();
            if (hVar.a()) {
                bitSet.set(0);
            }
            if (hVar.b()) {
                bitSet.set(1);
            }
            if (hVar.c()) {
                bitSet.set(2);
            }
            if (hVar.d()) {
                bitSet.set(3);
            }
            if (hVar.e()) {
                bitSet.set(4);
            }
            if (hVar.f()) {
                bitSet.set(5);
            }
            bnVar.a(bitSet, 6);
            if (hVar.a()) {
                bnVar.a(hVar.b);
            }
            if (hVar.b()) {
                bnVar.a(hVar.c);
            }
            if (hVar.c()) {
                bnVar.a(hVar.d);
            }
            if (hVar.d()) {
                bnVar.a(hVar.h);
            }
            if (hVar.e()) {
                bnVar.a(hVar.i);
            }
            if (hVar.f()) {
                bnVar.a(hVar.j);
            }
        }

        public void b(bg bgVar, h hVar) throws ar {
            bn bnVar = (bn) bgVar;
            hVar.a = bnVar.v();
            hVar.a(true);
            hVar.e = ab.a(bnVar.s());
            hVar.e(true);
            hVar.f = bnVar.v();
            hVar.f(true);
            hVar.g = bnVar.v();
            hVar.g(true);
            BitSet b = bnVar.b(6);
            if (b.get(0)) {
                hVar.b = bnVar.v();
                hVar.b(true);
            }
            if (b.get(1)) {
                hVar.c = bnVar.s();
                hVar.c(true);
            }
            if (b.get(2)) {
                hVar.d = bnVar.v();
                hVar.d(true);
            }
            if (b.get(3)) {
                hVar.h = bnVar.v();
                hVar.h(true);
            }
            if (b.get(4)) {
                hVar.i = bnVar.v();
                hVar.i(true);
            }
            if (b.get(5)) {
                hVar.j = bnVar.s();
                hVar.j(true);
            }
        }
    }

    /* AppInfo */
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

    /* AppInfo */
    public enum e implements as {
        KEY((short) 1, "key"),
        VERSION((short) 2, "version"),
        VERSION_INDEX((short) 3, "version_index"),
        PACKAGE_NAME((short) 4, "package_name"),
        SDK_TYPE((short) 5, "sdk_type"),
        SDK_VERSION((short) 6, "sdk_version"),
        CHANNEL((short) 7, "channel"),
        WRAPPER_TYPE((short) 8, "wrapper_type"),
        WRAPPER_VERSION((short) 9, "wrapper_version"),
        VERTICAL_TYPE((short) 10, "vertical_type");
        
        private static final Map<String, e> k = null;
        private final short l;
        private final String m;

        static {
            k = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return KEY;
                case 2:
                    return VERSION;
                case 3:
                    return VERSION_INDEX;
                case 4:
                    return PACKAGE_NAME;
                case 5:
                    return SDK_TYPE;
                case 6:
                    return SDK_VERSION;
                case 7:
                    return CHANNEL;
                case 8:
                    return WRAPPER_TYPE;
                case 9:
                    return WRAPPER_VERSION;
                case 10:
                    return VERTICAL_TYPE;
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
            return (e) k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public short a() {
            return this.l;
        }

        public String b() {
            return this.m;
        }
    }

    static {
        w.put(bq.class, new b());
        w.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.KEY, new aw("key", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.VERSION, new aw("version", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.VERSION_INDEX, new aw("version_index", (byte) 2, new ax((byte) 8)));
        enumMap.put(e.PACKAGE_NAME, new aw("package_name", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.SDK_TYPE, new aw("sdk_type", (byte) 1, new av((byte) 16, ab.class)));
        enumMap.put(e.SDK_VERSION, new aw("sdk_version", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.CHANNEL, new aw("channel", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.WRAPPER_TYPE, new aw("wrapper_type", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.WRAPPER_VERSION, new aw("wrapper_version", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.VERTICAL_TYPE, new aw("vertical_type", (byte) 2, new ax((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        aw.a(h.class, k);
    }

    public h a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public h b(String str) {
        this.b = str;
        return this;
    }

    public boolean a() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public h a(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public boolean b() {
        return al.a(this.x, 0);
    }

    public void c(boolean z) {
        this.x = al.a(this.x, 0, z);
    }

    public h c(String str) {
        this.d = str;
        return this;
    }

    public boolean c() {
        return this.d != null;
    }

    public void d(boolean z) {
        if (!z) {
            this.d = null;
        }
    }

    public h a(ab abVar) {
        this.e = abVar;
        return this;
    }

    public void e(boolean z) {
        if (!z) {
            this.e = null;
        }
    }

    public h d(String str) {
        this.f = str;
        return this;
    }

    public void f(boolean z) {
        if (!z) {
            this.f = null;
        }
    }

    public h e(String str) {
        this.g = str;
        return this;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public h f(String str) {
        this.h = str;
        return this;
    }

    public boolean d() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public h g(String str) {
        this.i = str;
        return this;
    }

    public boolean e() {
        return this.i != null;
    }

    public void i(boolean z) {
        if (!z) {
            this.i = null;
        }
    }

    public h b(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public boolean f() {
        return al.a(this.x, 1);
    }

    public void j(boolean z) {
        this.x = al.a(this.x, 1, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) w.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) w.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("AppInfo(");
        stringBuilder.append("key:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("version:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("version_index:");
            stringBuilder.append(this.c);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("package_name:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("sdk_type:");
        if (this.e == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.e);
        }
        stringBuilder.append(", ");
        stringBuilder.append("sdk_version:");
        if (this.f == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f);
        }
        stringBuilder.append(", ");
        stringBuilder.append("channel:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("wrapper_type:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("wrapper_version:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("vertical_type:");
            stringBuilder.append(this.j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void g() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'key' was not present! Struct: " + toString());
        } else if (this.e == null) {
            throw new bh("Required field 'sdk_type' was not present! Struct: " + toString());
        } else if (this.f == null) {
            throw new bh("Required field 'sdk_version' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new bh("Required field 'channel' was not present! Struct: " + toString());
        }
    }
}
