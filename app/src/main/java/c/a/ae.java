package c.a;

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

/* UALogEntry */
public class ae implements an<ae, e>, Serializable, Cloneable {
    public static final Map<e, aw> j;
    private static final bm k = new bm("UALogEntry");
    private static final bd l = new bd("client_stats", (byte) 12, (short) 1);
    private static final bd m = new bd("app_info", (byte) 12, (short) 2);
    private static final bd n = new bd("device_info", (byte) 12, (short) 3);
    private static final bd o = new bd("misc_info", (byte) 12, (short) 4);
    private static final bd p = new bd("activate_msg", (byte) 12, (short) 5);
    private static final bd q = new bd("instant_msgs", (byte) 15, (short) 6);
    private static final bd r = new bd("sessions", (byte) 15, (short) 7);
    private static final bd s = new bd("imprint", (byte) 12, (short) 8);
    private static final bd t = new bd("id_tracking", (byte) 12, (short) 9);
    private static final Map<Class<? extends bo>, bp> u = new HashMap();
    public i a;
    public h b;
    public j c;
    public v d;
    public g e;
    public List<t> f;
    public List<ac> g;
    public r h;
    public q i;
    private e[] v = new e[]{e.ACTIVATE_MSG, e.INSTANT_MSGS, e.SESSIONS, e.IMPRINT, e.ID_TRACKING};

    /* UALogEntry */
    private static class a extends bq<ae> {
        private a() {
        }

        public void a(bg bgVar, ae aeVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    aeVar.i();
                    return;
                }
                be l;
                int i;
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.a = new i();
                        aeVar.a.a(bgVar);
                        aeVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.b = new h();
                        aeVar.b.a(bgVar);
                        aeVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.c = new j();
                        aeVar.c.a(bgVar);
                        aeVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.d = new v();
                        aeVar.d.a(bgVar);
                        aeVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.e = new g();
                        aeVar.e.a(bgVar);
                        aeVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        aeVar.f = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            t tVar = new t();
                            tVar.a(bgVar);
                            aeVar.f.add(tVar);
                        }
                        bgVar.m();
                        aeVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        aeVar.g = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            ac acVar = new ac();
                            acVar.a(bgVar);
                            aeVar.g.add(acVar);
                        }
                        bgVar.m();
                        aeVar.g(true);
                        break;
                    case (short) 8:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.h = new r();
                        aeVar.h.a(bgVar);
                        aeVar.h(true);
                        break;
                    case (short) 9:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aeVar.i = new q();
                        aeVar.i.a(bgVar);
                        aeVar.i(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, ae aeVar) throws ar {
            aeVar.i();
            bgVar.a(ae.k);
            if (aeVar.a != null) {
                bgVar.a(ae.l);
                aeVar.a.b(bgVar);
                bgVar.b();
            }
            if (aeVar.b != null) {
                bgVar.a(ae.m);
                aeVar.b.b(bgVar);
                bgVar.b();
            }
            if (aeVar.c != null) {
                bgVar.a(ae.n);
                aeVar.c.b(bgVar);
                bgVar.b();
            }
            if (aeVar.d != null) {
                bgVar.a(ae.o);
                aeVar.d.b(bgVar);
                bgVar.b();
            }
            if (aeVar.e != null && aeVar.a()) {
                bgVar.a(ae.p);
                aeVar.e.b(bgVar);
                bgVar.b();
            }
            if (aeVar.f != null && aeVar.d()) {
                bgVar.a(ae.q);
                bgVar.a(new be((byte) 12, aeVar.f.size()));
                for (t b : aeVar.f) {
                    b.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (aeVar.g != null && aeVar.f()) {
                bgVar.a(ae.r);
                bgVar.a(new be((byte) 12, aeVar.g.size()));
                for (ac b2 : aeVar.g) {
                    b2.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (aeVar.h != null && aeVar.g()) {
                bgVar.a(ae.s);
                aeVar.h.b(bgVar);
                bgVar.b();
            }
            if (aeVar.i != null && aeVar.h()) {
                bgVar.a(ae.t);
                aeVar.i.b(bgVar);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* UALogEntry */
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

    /* UALogEntry */
    private static class c extends br<ae> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (ae) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (ae) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, ae aeVar) throws ar {
            bgVar = (bn) bgVar;
            aeVar.a.b(bgVar);
            aeVar.b.b(bgVar);
            aeVar.c.b(bgVar);
            aeVar.d.b(bgVar);
            BitSet bitSet = new BitSet();
            if (aeVar.a()) {
                bitSet.set(0);
            }
            if (aeVar.d()) {
                bitSet.set(1);
            }
            if (aeVar.f()) {
                bitSet.set(2);
            }
            if (aeVar.g()) {
                bitSet.set(3);
            }
            if (aeVar.h()) {
                bitSet.set(4);
            }
            bgVar.a(bitSet, 5);
            if (aeVar.a()) {
                aeVar.e.b(bgVar);
            }
            if (aeVar.d()) {
                bgVar.a(aeVar.f.size());
                for (t b : aeVar.f) {
                    b.b(bgVar);
                }
            }
            if (aeVar.f()) {
                bgVar.a(aeVar.g.size());
                for (ac b2 : aeVar.g) {
                    b2.b(bgVar);
                }
            }
            if (aeVar.g()) {
                aeVar.h.b(bgVar);
            }
            if (aeVar.h()) {
                aeVar.i.b(bgVar);
            }
        }

        public void b(bg bgVar, ae aeVar) throws ar {
            int i = 0;
            bgVar = (bn) bgVar;
            aeVar.a = new i();
            aeVar.a.a(bgVar);
            aeVar.a(true);
            aeVar.b = new h();
            aeVar.b.a(bgVar);
            aeVar.b(true);
            aeVar.c = new j();
            aeVar.c.a(bgVar);
            aeVar.c(true);
            aeVar.d = new v();
            aeVar.d.a(bgVar);
            aeVar.d(true);
            BitSet b = bgVar.b(5);
            if (b.get(0)) {
                aeVar.e = new g();
                aeVar.e.a(bgVar);
                aeVar.e(true);
            }
            if (b.get(1)) {
                be beVar = new be((byte) 12, bgVar.s());
                aeVar.f = new ArrayList(beVar.b);
                for (int i2 = 0; i2 < beVar.b; i2++) {
                    t tVar = new t();
                    tVar.a(bgVar);
                    aeVar.f.add(tVar);
                }
                aeVar.f(true);
            }
            if (b.get(2)) {
                be beVar2 = new be((byte) 12, bgVar.s());
                aeVar.g = new ArrayList(beVar2.b);
                while (i < beVar2.b) {
                    ac acVar = new ac();
                    acVar.a(bgVar);
                    aeVar.g.add(acVar);
                    i++;
                }
                aeVar.g(true);
            }
            if (b.get(3)) {
                aeVar.h = new r();
                aeVar.h.a(bgVar);
                aeVar.h(true);
            }
            if (b.get(4)) {
                aeVar.i = new q();
                aeVar.i.a(bgVar);
                aeVar.i(true);
            }
        }
    }

    /* UALogEntry */
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

    /* UALogEntry */
    public enum e implements as {
        CLIENT_STATS((short) 1, "client_stats"),
        APP_INFO((short) 2, "app_info"),
        DEVICE_INFO((short) 3, "device_info"),
        MISC_INFO((short) 4, "misc_info"),
        ACTIVATE_MSG((short) 5, "activate_msg"),
        INSTANT_MSGS((short) 6, "instant_msgs"),
        SESSIONS((short) 7, "sessions"),
        IMPRINT((short) 8, "imprint"),
        ID_TRACKING((short) 9, "id_tracking");
        
        private static final Map<String, e> j = null;
        private final short k;
        private final String l;

        static {
            j = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                j.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return CLIENT_STATS;
                case 2:
                    return APP_INFO;
                case 3:
                    return DEVICE_INFO;
                case 4:
                    return MISC_INFO;
                case 5:
                    return ACTIVATE_MSG;
                case 6:
                    return INSTANT_MSGS;
                case 7:
                    return SESSIONS;
                case 8:
                    return IMPRINT;
                case 9:
                    return ID_TRACKING;
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
            return (e) j.get(str);
        }

        private e(short s, String str) {
            this.k = s;
            this.l = str;
        }

        public short a() {
            return this.k;
        }

        public String b() {
            return this.l;
        }
    }

    static {
        u.put(bq.class, new b());
        u.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.CLIENT_STATS, new aw("client_stats", (byte) 1, new ba((byte) 12, i.class)));
        enumMap.put(e.APP_INFO, new aw("app_info", (byte) 1, new ba((byte) 12, h.class)));
        enumMap.put(e.DEVICE_INFO, new aw("device_info", (byte) 1, new ba((byte) 12, j.class)));
        enumMap.put(e.MISC_INFO, new aw("misc_info", (byte) 1, new ba((byte) 12, v.class)));
        enumMap.put(e.ACTIVATE_MSG, new aw("activate_msg", (byte) 2, new ba((byte) 12, g.class)));
        enumMap.put(e.INSTANT_MSGS, new aw("instant_msgs", (byte) 2, new ay((byte) 15, new ba((byte) 12, t.class))));
        enumMap.put(e.SESSIONS, new aw("sessions", (byte) 2, new ay((byte) 15, new ba((byte) 12, ac.class))));
        enumMap.put(e.IMPRINT, new aw("imprint", (byte) 2, new ba((byte) 12, r.class)));
        enumMap.put(e.ID_TRACKING, new aw("id_tracking", (byte) 2, new ba((byte) 12, q.class)));
        j = Collections.unmodifiableMap(enumMap);
        aw.a(ae.class, j);
    }

    public ae a(i iVar) {
        this.a = iVar;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public ae a(h hVar) {
        this.b = hVar;
        return this;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public ae a(j jVar) {
        this.c = jVar;
        return this;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public ae a(v vVar) {
        this.d = vVar;
        return this;
    }

    public void d(boolean z) {
        if (!z) {
            this.d = null;
        }
    }

    public ae a(g gVar) {
        this.e = gVar;
        return this;
    }

    public boolean a() {
        return this.e != null;
    }

    public void e(boolean z) {
        if (!z) {
            this.e = null;
        }
    }

    public int b() {
        return this.f == null ? 0 : this.f.size();
    }

    public void a(t tVar) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(tVar);
    }

    public List<t> c() {
        return this.f;
    }

    public boolean d() {
        return this.f != null;
    }

    public void f(boolean z) {
        if (!z) {
            this.f = null;
        }
    }

    public void a(ac acVar) {
        if (this.g == null) {
            this.g = new ArrayList();
        }
        this.g.add(acVar);
    }

    public List<ac> e() {
        return this.g;
    }

    public boolean f() {
        return this.g != null;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public ae a(r rVar) {
        this.h = rVar;
        return this;
    }

    public boolean g() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public ae a(q qVar) {
        this.i = qVar;
        return this;
    }

    public boolean h() {
        return this.i != null;
    }

    public void i(boolean z) {
        if (!z) {
            this.i = null;
        }
    }

    public void a(bg bgVar) throws ar {
        ((bp) u.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) u.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UALogEntry(");
        stringBuilder.append("client_stats:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("app_info:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("device_info:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("misc_info:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.d);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("activate_msg:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("instant_msgs:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        if (f()) {
            stringBuilder.append(", ");
            stringBuilder.append("sessions:");
            if (this.g == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.g);
            }
        }
        if (g()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("id_tracking:");
            if (this.i == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.i);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void i() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'client_stats' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new bh("Required field 'app_info' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bh("Required field 'device_info' was not present! Struct: " + toString());
        } else if (this.d == null) {
            throw new bh("Required field 'misc_info' was not present! Struct: " + toString());
        } else {
            if (this.a != null) {
                this.a.d();
            }
            if (this.b != null) {
                this.b.g();
            }
            if (this.c != null) {
                this.c.r();
            }
            if (this.d != null) {
                this.d.l();
            }
            if (this.e != null) {
                this.e.b();
            }
            if (this.h != null) {
                this.h.e();
            }
            if (this.i != null) {
                this.i.e();
            }
        }
    }
}
