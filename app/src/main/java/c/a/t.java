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

/* InstantMsg */
public class t implements an<t, e>, Serializable, Cloneable {
    public static final Map<e, aw> e;
    private static final bm f = new bm("InstantMsg");
    private static final bd g = new bd(StarCategory.KEY_STAR_CATEGORY_ID, (byte) 11, (short) 1);
    private static final bd h = new bd("errors", (byte) 15, (short) 2);
    private static final bd i = new bd("events", (byte) 15, (short) 3);
    private static final bd j = new bd("game_events", (byte) 15, (short) 4);
    private static final Map<Class<? extends bo>, bp> k = new HashMap();
    public String a;
    public List<k> b;
    public List<m> c;
    public List<m> d;
    private e[] l = new e[]{e.ERRORS, e.EVENTS, e.GAME_EVENTS};

    /* InstantMsg */
    private static class a extends bq<t> {
        private a() {
        }

        public void a(bg bgVar, t tVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    tVar.e();
                    return;
                }
                be l;
                int i;
                m mVar;
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        tVar.a = bgVar.v();
                        tVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        tVar.b = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            k kVar = new k();
                            kVar.a(bgVar);
                            tVar.b.add(kVar);
                        }
                        bgVar.m();
                        tVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        tVar.c = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            mVar = new m();
                            mVar.a(bgVar);
                            tVar.c.add(mVar);
                        }
                        bgVar.m();
                        tVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 15) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        l = bgVar.l();
                        tVar.d = new ArrayList(l.b);
                        for (i = 0; i < l.b; i++) {
                            mVar = new m();
                            mVar.a(bgVar);
                            tVar.d.add(mVar);
                        }
                        bgVar.m();
                        tVar.d(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, t tVar) throws ar {
            tVar.e();
            bgVar.a(t.f);
            if (tVar.a != null) {
                bgVar.a(t.g);
                bgVar.a(tVar.a);
                bgVar.b();
            }
            if (tVar.b != null && tVar.b()) {
                bgVar.a(t.h);
                bgVar.a(new be((byte) 12, tVar.b.size()));
                for (k b : tVar.b) {
                    b.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (tVar.c != null && tVar.c()) {
                bgVar.a(t.i);
                bgVar.a(new be((byte) 12, tVar.c.size()));
                for (m b2 : tVar.c) {
                    b2.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            if (tVar.d != null && tVar.d()) {
                bgVar.a(t.j);
                bgVar.a(new be((byte) 12, tVar.d.size()));
                for (m b22 : tVar.d) {
                    b22.b(bgVar);
                }
                bgVar.e();
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* InstantMsg */
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

    /* InstantMsg */
    private static class c extends br<t> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (t) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (t) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, t tVar) throws ar {
            bgVar = (bn) bgVar;
            bgVar.a(tVar.a);
            BitSet bitSet = new BitSet();
            if (tVar.b()) {
                bitSet.set(0);
            }
            if (tVar.c()) {
                bitSet.set(1);
            }
            if (tVar.d()) {
                bitSet.set(2);
            }
            bgVar.a(bitSet, 3);
            if (tVar.b()) {
                bgVar.a(tVar.b.size());
                for (k b : tVar.b) {
                    b.b(bgVar);
                }
            }
            if (tVar.c()) {
                bgVar.a(tVar.c.size());
                for (m b2 : tVar.c) {
                    b2.b(bgVar);
                }
            }
            if (tVar.d()) {
                bgVar.a(tVar.d.size());
                for (m b22 : tVar.d) {
                    b22.b(bgVar);
                }
            }
        }

        public void b(bg bgVar, t tVar) throws ar {
            be beVar;
            int i;
            int i2 = 0;
            bgVar = (bn) bgVar;
            tVar.a = bgVar.v();
            tVar.a(true);
            BitSet b = bgVar.b(3);
            if (b.get(0)) {
                beVar = new be((byte) 12, bgVar.s());
                tVar.b = new ArrayList(beVar.b);
                for (i = 0; i < beVar.b; i++) {
                    k kVar = new k();
                    kVar.a(bgVar);
                    tVar.b.add(kVar);
                }
                tVar.b(true);
            }
            if (b.get(1)) {
                beVar = new be((byte) 12, bgVar.s());
                tVar.c = new ArrayList(beVar.b);
                for (i = 0; i < beVar.b; i++) {
                    m mVar = new m();
                    mVar.a(bgVar);
                    tVar.c.add(mVar);
                }
                tVar.c(true);
            }
            if (b.get(2)) {
                be beVar2 = new be((byte) 12, bgVar.s());
                tVar.d = new ArrayList(beVar2.b);
                while (i2 < beVar2.b) {
                    m mVar2 = new m();
                    mVar2.a(bgVar);
                    tVar.d.add(mVar2);
                    i2++;
                }
                tVar.d(true);
            }
        }
    }

    /* InstantMsg */
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

    /* InstantMsg */
    public enum e implements as {
        ID((short) 1, StarCategory.KEY_STAR_CATEGORY_ID),
        ERRORS((short) 2, "errors"),
        EVENTS((short) 3, "events"),
        GAME_EVENTS((short) 4, "game_events");
        
        private static final Map<String, e> e = null;
        private final short f;
        private final String g;

        static {
            e = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return ID;
                case 2:
                    return ERRORS;
                case 3:
                    return EVENTS;
                case 4:
                    return GAME_EVENTS;
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
            return (e) e.get(str);
        }

        private e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public short a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    static {
        k.put(bq.class, new b());
        k.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.ID, new aw(StarCategory.KEY_STAR_CATEGORY_ID, (byte) 1, new ax((byte) 11)));
        enumMap.put(e.ERRORS, new aw("errors", (byte) 2, new ay((byte) 15, new ba((byte) 12, k.class))));
        enumMap.put(e.EVENTS, new aw("events", (byte) 2, new ay((byte) 15, new ba((byte) 12, m.class))));
        enumMap.put(e.GAME_EVENTS, new aw("game_events", (byte) 2, new ay((byte) 15, new ba((byte) 12, m.class))));
        e = Collections.unmodifiableMap(enumMap);
        aw.a(t.class, e);
    }

    public String a() {
        return this.a;
    }

    public t a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public void a(k kVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(kVar);
    }

    public boolean b() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public void a(m mVar) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(mVar);
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

    public void a(bg bgVar) throws ar {
        ((bp) k.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) k.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("InstantMsg(");
        stringBuilder.append("id:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("errors:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("events:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        if (d()) {
            stringBuilder.append(", ");
            stringBuilder.append("game_events:");
            if (this.d == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.d);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void e() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'id' was not present! Struct: " + toString());
        }
    }
}
