package c.a;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Traffic */
public class ad implements an<ad, e>, Serializable, Cloneable {
    public static final Map<e, aw> c;
    private static final bm d = new bm("Traffic");
    private static final bd e = new bd("upload_traffic", (byte) 8, (short) 1);
    private static final bd f = new bd("download_traffic", (byte) 8, (short) 2);
    private static final Map<Class<? extends bo>, bp> g = new HashMap();
    public int a;
    public int b;
    private byte h = (byte) 0;

    /* Traffic */
    private static class a extends bq<ad> {
        private a() {
        }

        public void a(bg bgVar, ad adVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!adVar.a()) {
                        throw new bh("Required field 'upload_traffic' was not found in serialized data! Struct: " + toString());
                    } else if (adVar.b()) {
                        adVar.c();
                        return;
                    } else {
                        throw new bh("Required field 'download_traffic' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        adVar.a = bgVar.s();
                        adVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        adVar.b = bgVar.s();
                        adVar.b(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, ad adVar) throws ar {
            adVar.c();
            bgVar.a(ad.d);
            bgVar.a(ad.e);
            bgVar.a(adVar.a);
            bgVar.b();
            bgVar.a(ad.f);
            bgVar.a(adVar.b);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* Traffic */
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

    /* Traffic */
    private static class c extends br<ad> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (ad) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (ad) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, ad adVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(adVar.a);
            bnVar.a(adVar.b);
        }

        public void b(bg bgVar, ad adVar) throws ar {
            bn bnVar = (bn) bgVar;
            adVar.a = bnVar.s();
            adVar.a(true);
            adVar.b = bnVar.s();
            adVar.b(true);
        }
    }

    /* Traffic */
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

    /* Traffic */
    public enum e implements as {
        UPLOAD_TRAFFIC((short) 1, "upload_traffic"),
        DOWNLOAD_TRAFFIC((short) 2, "download_traffic");
        
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
                    return UPLOAD_TRAFFIC;
                case 2:
                    return DOWNLOAD_TRAFFIC;
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
        enumMap.put(e.UPLOAD_TRAFFIC, new aw("upload_traffic", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.DOWNLOAD_TRAFFIC, new aw("download_traffic", (byte) 1, new ax((byte) 8)));
        c = Collections.unmodifiableMap(enumMap);
        aw.a(ad.class, c);
    }

    public ad a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public boolean a() {
        return al.a(this.h, 0);
    }

    public void a(boolean z) {
        this.h = al.a(this.h, 0, z);
    }

    public ad b(int i) {
        this.b = i;
        b(true);
        return this;
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
        StringBuilder stringBuilder = new StringBuilder("Traffic(");
        stringBuilder.append("upload_traffic:");
        stringBuilder.append(this.a);
        stringBuilder.append(", ");
        stringBuilder.append("download_traffic:");
        stringBuilder.append(this.b);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void c() throws ar {
    }
}
