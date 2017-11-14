package c.a;

import com.sds.android.cloudapi.ttpod.data.StarCategory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* Session */
public class ac implements an<ac, e>, Serializable, Cloneable {
    public static final Map<e, aw> h;
    private static final bm i = new bm("Session");
    private static final bd j = new bd(StarCategory.KEY_STAR_CATEGORY_ID, (byte) 11, (short) 1);
    private static final bd k = new bd("start_time", (byte) 10, (short) 2);
    private static final bd l = new bd("end_time", (byte) 10, (short) 3);
    private static final bd m = new bd("duration", (byte) 10, (short) 4);
    private static final bd n = new bd("pages", (byte) 15, (short) 5);
    private static final bd o = new bd("locations", (byte) 15, (short) 6);
    private static final bd p = new bd("traffic", (byte) 12, (short) 7);
    private static final Map<Class<? extends bo>, bp> q = new HashMap();
    public String a;
    public long b;
    public long c;
    public long d;
    public List<w> e;
    public List<u> f;
    public ad g;
    private byte r = (byte) 0;
    private e[] s = new e[]{e.PAGES, e.LOCATIONS, e.TRAFFIC};

    /* Session */
    private static class a extends bq<ac> {
        private a() {
        }

        public void a(bg bgVar, ac acVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!acVar.a()) {
                        throw new bh("Required field 'start_time' was not found in serialized data! Struct: " + toString());
                    } else if (!acVar.b()) {
                        throw new bh("Required field 'end_time' was not found in serialized data! Struct: " + toString());
                    } else if (acVar.c()) {
                        acVar.h();
                        return;
                    } else {
                        throw new bh("Required field 'duration' was not found in serialized data! Struct: " + toString());
                    }
                }
                be l;
                int i;
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        acVar.a = bgVar.v();
                        acVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        acVar.b = bgVar.t();
                        acVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        acVar.c = bgVar.t();
                        acVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        acVar.d = bgVar.t();
                        acVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        acVar.e = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            w wVar = new w();
                            wVar.a(bgVar);
                            acVar.e.add(wVar);
                        }
                        bgVar.m();
                        acVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        acVar.f = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            u uVar = new u();
                            uVar.a(bgVar);
                            acVar.f.add(uVar);
                        }
                        bgVar.m();
                        acVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        acVar.g = new ad();
                        acVar.g.a(bgVar);
                        acVar.g(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, ac acVar) throws ar {
            acVar.h();
            bgVar.a(ac.i);
            if (acVar.a != null) {
                bgVar.a(ac.j);
                bgVar.a(acVar.a);
                bgVar.b();
            }
            bgVar.a(ac.k);
            bgVar.a(acVar.b);
            bgVar.b();
            bgVar.a(ac.l);
            bgVar.a(acVar.c);
            bgVar.b();
            bgVar.a(ac.m);
            bgVar.a(acVar.d);
            bgVar.b();
            if (acVar.e != null && acVar.e()) {
                bgVar.a(ac.n);
                bgVar.a(new be((byte) 12, acVar.e.size()));
                for (w b : acVar.e) {
                    b.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (acVar.f != null && acVar.f()) {
                bgVar.a(ac.o);
                bgVar.a(new be((byte) 12, acVar.f.size()));
                for (u b2 : acVar.f) {
                    b2.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (acVar.g != null && acVar.g()) {
                bgVar.a(ac.p);
                acVar.g.b(bgVar);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* Session */
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

    /* Session */
    private static class c extends br<ac> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (ac) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (ac) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, ac acVar) throws ar {
            bgVar = (bn) bgVar;
            bgVar.a(acVar.a);
            bgVar.a(acVar.b);
            bgVar.a(acVar.c);
            bgVar.a(acVar.d);
            BitSet bitSet = new BitSet();
            if (acVar.e()) {
                bitSet.set(0);
            }
            if (acVar.f()) {
                bitSet.set(1);
            }
            if (acVar.g()) {
                bitSet.set(2);
            }
            bgVar.a(bitSet, 3);
            if (acVar.e()) {
                bgVar.a(acVar.e.size());
                for (w b : acVar.e) {
                    b.b(bgVar);
                }
            }
            if (acVar.f()) {
                bgVar.a(acVar.f.size());
                for (u b2 : acVar.f) {
                    b2.b(bgVar);
                }
            }
            if (acVar.g()) {
                acVar.g.b(bgVar);
            }
        }

        public void b(bg bgVar, ac acVar) throws ar {
            int i = 0;
            bgVar = (bn) bgVar;
            acVar.a = bgVar.v();
            acVar.a(true);
            acVar.b = bgVar.t();
            acVar.b(true);
            acVar.c = bgVar.t();
            acVar.c(true);
            acVar.d = bgVar.t();
            acVar.d(true);
            BitSet b = bgVar.b(3);
            if (b.get(0)) {
                be beVar = new be((byte) 12, bgVar.s());
                acVar.e = new ArrayList(beVar.b);
                for (int i2 = 0; i2 < beVar.b; i2++) {
                    w wVar = new w();
                    wVar.a(bgVar);
                    acVar.e.add(wVar);
                }
                acVar.e(true);
            }
            if (b.get(1)) {
                be beVar2 = new be((byte) 12, bgVar.s());
                acVar.f = new ArrayList(beVar2.b);
                while (i < beVar2.b) {
                    u uVar = new u();
                    uVar.a(bgVar);
                    acVar.f.add(uVar);
                    i++;
                }
                acVar.f(true);
            }
            if (b.get(2)) {
                acVar.g = new ad();
                acVar.g.a(bgVar);
                acVar.g(true);
            }
        }
    }

    /* Session */
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

    /* Session */
    public enum e implements as {
        ID((short) 1, StarCategory.KEY_STAR_CATEGORY_ID),
        START_TIME((short) 2, "start_time"),
        END_TIME((short) 3, "end_time"),
        DURATION((short) 4, "duration"),
        PAGES((short) 5, "pages"),
        LOCATIONS((short) 6, "locations"),
        TRAFFIC((short) 7, "traffic");
        
        private static final Map<String, e> h = null;
        private final short i;
        private final String j;

        static {
            h = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                h.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return ID;
                case 2:
                    return START_TIME;
                case 3:
                    return END_TIME;
                case 4:
                    return DURATION;
                case 5:
                    return PAGES;
                case 6:
                    return LOCATIONS;
                case 7:
                    return TRAFFIC;
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
            return (e) h.get(str);
        }

        private e(short s, String str) {
            this.i = s;
            this.j = str;
        }

        public short a() {
            return this.i;
        }

        public String b() {
            return this.j;
        }
    }

    static {
        q.put(bq.class, new b());
        q.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.ID, new aw(StarCategory.KEY_STAR_CATEGORY_ID, (byte) 1, new ax((byte) 11)));
        enumMap.put(e.START_TIME, new aw("start_time", (byte) 1, new ax((byte) 10)));
        enumMap.put(e.END_TIME, new aw("end_time", (byte) 1, new ax((byte) 10)));
        enumMap.put(e.DURATION, new aw("duration", (byte) 1, new ax((byte) 10)));
        enumMap.put(e.PAGES, new aw("pages", (byte) 2, new ay((byte) 15, new ba((byte) 12, w.class))));
        enumMap.put(e.LOCATIONS, new aw("locations", (byte) 2, new ay((byte) 15, new ba((byte) 12, u.class))));
        enumMap.put(e.TRAFFIC, new aw("traffic", (byte) 2, new ba((byte) 12, ad.class)));
        h = Collections.unmodifiableMap(enumMap);
        aw.a(ac.class, h);
    }

    public ac a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public ac a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public boolean a() {
        return al.a(this.r, 0);
    }

    public void b(boolean z) {
        this.r = al.a(this.r, 0, z);
    }

    public ac b(long j) {
        this.c = j;
        c(true);
        return this;
    }

    public boolean b() {
        return al.a(this.r, 1);
    }

    public void c(boolean z) {
        this.r = al.a(this.r, 1, z);
    }

    public ac c(long j) {
        this.d = j;
        d(true);
        return this;
    }

    public boolean c() {
        return al.a(this.r, 2);
    }

    public void d(boolean z) {
        this.r = al.a(this.r, 2, z);
    }

    public int d() {
        return this.e == null ? 0 : this.e.size();
    }

    public ac a(List<w> list) {
        this.e = list;
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

    public void a(u uVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(uVar);
    }

    public ac b(List<u> list) {
        this.f = list;
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

    public ac a(ad adVar) {
        this.g = adVar;
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

    public void a(bg bgVar) throws ar {
        ((bp) q.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) q.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Session(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("start_time:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("end_time:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("duration:");
        stringBuilder.append(this.d);
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("pages:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("locations:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("traffic:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void h() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'id' was not present! Struct: " + toString());
        } else if (this.g != null) {
            this.g.c();
        }
    }
}
