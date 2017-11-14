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
import java.util.Map.Entry;

/* IdTracking */
public class q implements an<q, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("IdTracking");
    private static final bd f = new bd("snapshots", (byte) 13, (short) 1);
    private static final bd g = new bd("journals", (byte) 15, (short) 2);
    private static final bd h = new bd("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public Map<String, p> a;
    public List<o> b;
    public String c;
    private e[] j = new e[]{e.JOURNALS, e.CHECKSUM};

    /* IdTracking */
    private static class a extends bq<q> {
        private a() {
        }

        public void a(bg bgVar, q qVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    qVar.e();
                    return;
                }
                int i;
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 13) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        bf j = bgVar.j();
                        qVar.a = new HashMap(j.c * 2);
                        for (i = 0; i < j.c; i++) {
                            String v = bgVar.v();
                            p pVar = new p();
                            pVar.a(bgVar);
                            qVar.a.put(v, pVar);
                        }
                        bgVar.k();
                        qVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        be l = bgVar.l();
                        qVar.b = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            o oVar = new o();
                            oVar.a(bgVar);
                            qVar.b.add(oVar);
                        }
                        bgVar.m();
                        qVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        qVar.c = bgVar.v();
                        qVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, q qVar) throws ar {
            qVar.e();
            bgVar.a(q.e);
            if (qVar.a != null) {
                bgVar.a(q.f);
                bgVar.a(new bf((byte) 11, (byte) 12, qVar.a.size()));
                for (Entry entry : qVar.a.entrySet()) {
                    bgVar.a((String) entry.getKey());
                    ((p) entry.getValue()).b(bgVar);
                }
                bgVar.d();
                bgVar.b();
            }
            if (qVar.b != null && qVar.c()) {
                bgVar.a(q.g);
                bgVar.a(new be((byte) 12, qVar.b.size()));
                for (o b : qVar.b) {
                    b.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (qVar.c != null && qVar.d()) {
                bgVar.a(q.h);
                bgVar.a(qVar.c);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* IdTracking */
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

    /* IdTracking */
    private static class c extends br<q> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (q) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (q) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, q qVar) throws ar {
            bgVar = (bn) bgVar;
            bgVar.a(qVar.a.size());
            for (Entry entry : qVar.a.entrySet()) {
                bgVar.a((String) entry.getKey());
                ((p) entry.getValue()).b(bgVar);
            }
            BitSet bitSet = new BitSet();
            if (qVar.c()) {
                bitSet.set(0);
            }
            if (qVar.d()) {
                bitSet.set(1);
            }
            bgVar.a(bitSet, 2);
            if (qVar.c()) {
                bgVar.a(qVar.b.size());
                for (o b : qVar.b) {
                    b.b(bgVar);
                }
            }
            if (qVar.d()) {
                bgVar.a(qVar.c);
            }
        }

        public void b(bg bgVar, q qVar) throws ar {
            int i = 0;
            bgVar = (bn) bgVar;
            bf bfVar = new bf((byte) 11, (byte) 12, bgVar.s());
            qVar.a = new HashMap(bfVar.c * 2);
            for (int i2 = 0; i2 < bfVar.c; i2++) {
                String v = bgVar.v();
                p pVar = new p();
                pVar.a(bgVar);
                qVar.a.put(v, pVar);
            }
            qVar.a(true);
            BitSet b = bgVar.b(2);
            if (b.get(0)) {
                be beVar = new be((byte) 12, bgVar.s());
                qVar.b = new ArrayList(beVar.b);
                while (i < beVar.b) {
                    o oVar = new o();
                    oVar.a(bgVar);
                    qVar.b.add(oVar);
                    i++;
                }
                qVar.b(true);
            }
            if (b.get(1)) {
                qVar.c = bgVar.v();
                qVar.c(true);
            }
        }
    }

    /* IdTracking */
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

    /* IdTracking */
    public enum e implements as {
        SNAPSHOTS((short) 1, "snapshots"),
        JOURNALS((short) 2, "journals"),
        CHECKSUM((short) 3, "checksum");
        
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
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
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
        enumMap.put(e.SNAPSHOTS, new aw("snapshots", (byte) 1, new az((byte) 13, new ax((byte) 11), new ba((byte) 12, p.class))));
        enumMap.put(e.JOURNALS, new aw("journals", (byte) 2, new ay((byte) 15, new ba((byte) 12, o.class))));
        enumMap.put(e.CHECKSUM, new aw("checksum", (byte) 2, new ax((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(q.class, d);
    }

    public Map<String, p> a() {
        return this.a;
    }

    public q a(Map<String, p> map) {
        this.a = map;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public List<o> b() {
        return this.b;
    }

    public q a(List<o> list) {
        this.b = list;
        return this;
    }

    public boolean c() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public boolean d() {
        return this.c != null;
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
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("checksum:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void e() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }
}
