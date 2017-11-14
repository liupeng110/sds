package c.a;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Resolution */
public class z implements an<z, e>, Serializable, Cloneable {
    public static final Map<e, aw> c;
    private static final bm d = new bm("Resolution");
    private static final bd e = new bd("height", (byte) 8, (short) 1);
    private static final bd f = new bd("width", (byte) 8, (short) 2);
    private static final Map<Class<? extends bo>, bp> g = new HashMap();
    public int a;
    public int b;
    private byte h;

    /* Resolution */
    private static class a extends bq<z> {
        private a() {
        }

        public void a(bg bgVar, z zVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!zVar.a()) {
                        throw new bh("Required field 'height' was not found in serialized data! Struct: " + toString());
                    } else if (zVar.b()) {
                        zVar.c();
                        return;
                    } else {
                        throw new bh("Required field 'width' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        zVar.a = bgVar.s();
                        zVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        zVar.b = bgVar.s();
                        zVar.b(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, z zVar) throws ar {
            zVar.c();
            bgVar.a(z.d);
            bgVar.a(z.e);
            bgVar.a(zVar.a);
            bgVar.b();
            bgVar.a(z.f);
            bgVar.a(zVar.b);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* Resolution */
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

    /* Resolution */
    private static class c extends br<z> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (z) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (z) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, z zVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(zVar.a);
            bnVar.a(zVar.b);
        }

        public void b(bg bgVar, z zVar) throws ar {
            bn bnVar = (bn) bgVar;
            zVar.a = bnVar.s();
            zVar.a(true);
            zVar.b = bnVar.s();
            zVar.b(true);
        }
    }

    /* Resolution */
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

    /* Resolution */
    public enum e implements as {
        HEIGHT((short) 1, "height"),
        WIDTH((short) 2, "width");
        
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
                    return HEIGHT;
                case 2:
                    return WIDTH;
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
        enumMap.put(e.HEIGHT, new aw("height", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.WIDTH, new aw("width", (byte) 1, new ax((byte) 8)));
        c = Collections.unmodifiableMap(enumMap);
        aw.a(z.class, c);
    }

    public z() {
        this.h = (byte) 0;
    }

    public z(int i, int i2) {
        this();
        this.a = i;
        a(true);
        this.b = i2;
        b(true);
    }

    public boolean a() {
        return al.a(this.h, 0);
    }

    public void a(boolean z) {
        this.h = al.a(this.h, 0, z);
    }

    public boolean b() {
        return al.a(this.h, 1);
    }

    public void b(boolean z) {
        this.h = al.a(this.h, 1, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) g.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) g.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Resolution(");
        stringBuilder.append("height:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("width:");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void c() throws ar {
    }
}
