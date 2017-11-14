package c.a;

import com.tencent.stat.DeviceInfo;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* Event */
public class m implements an<m, e>, Serializable, Cloneable {
    public static final Map<e, aw> f;
    private static final bm g = new bm("Event");
    private static final bd h = new bd("name", (byte) 11, (short) 1);
    private static final bd i = new bd("properties", (byte) 13, (short) 2);
    private static final bd j = new bd("duration", (byte) 10, (short) 3);
    private static final bd k = new bd("acc", (byte) 8, (short) 4);
    private static final bd l = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 5);
    private static final Map<Class<? extends bo>, bp> m = new HashMap();
    public String a;
    public Map<String, x> b;
    public long c;
    public int d;
    public long e;
    private byte n = (byte) 0;
    private e[] o = new e[]{e.DURATION, e.ACC};

    /* Event */
    private static class a extends bq<m> {
        private a() {
        }

        public void a(bg bgVar, m mVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (mVar.c()) {
                        mVar.d();
                        return;
                    }
                    throw new bh("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        mVar.a = bgVar.v();
                        mVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 13) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        bf j = bgVar.j();
                        mVar.b = new HashMap(j.c * 2);
                        for (int i = 0; i < j.c; i++) {
                            String v = bgVar.v();
                            x xVar = new x();
                            xVar.a(bgVar);
                            mVar.b.put(v, xVar);
                        }
                        bgVar.k();
                        mVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        mVar.c = bgVar.t();
                        mVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        mVar.d = bgVar.s();
                        mVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        mVar.e = bgVar.t();
                        mVar.e(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, m mVar) throws ar {
            mVar.d();
            bgVar.a(m.g);
            if (mVar.a != null) {
                bgVar.a(m.h);
                bgVar.a(mVar.a);
                bgVar.b();
            }
            if (mVar.b != null) {
                bgVar.a(m.i);
                bgVar.a(new bf((byte) 11, (byte) 12, mVar.b.size()));
                for (Entry entry : mVar.b.entrySet()) {
                    bgVar.a((String) entry.getKey());
                    ((x) entry.getValue()).b(bgVar);
                }
                bgVar.d();
                bgVar.b();
            }
            if (mVar.a()) {
                bgVar.a(m.j);
                bgVar.a(mVar.c);
                bgVar.b();
            }
            if (mVar.b()) {
                bgVar.a(m.k);
                bgVar.a(mVar.d);
                bgVar.b();
            }
            bgVar.a(m.l);
            bgVar.a(mVar.e);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* Event */
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

    /* Event */
    private static class c extends br<m> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (m) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (m) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, m mVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(mVar.a);
            bnVar.a(mVar.b.size());
            for (Entry entry : mVar.b.entrySet()) {
                bnVar.a((String) entry.getKey());
                ((x) entry.getValue()).b((bg) bnVar);
            }
            bnVar.a(mVar.e);
            BitSet bitSet = new BitSet();
            if (mVar.a()) {
                bitSet.set(0);
            }
            if (mVar.b()) {
                bitSet.set(1);
            }
            bnVar.a(bitSet, 2);
            if (mVar.a()) {
                bnVar.a(mVar.c);
            }
            if (mVar.b()) {
                bnVar.a(mVar.d);
            }
        }

        public void b(bg bgVar, m mVar) throws ar {
            bn bnVar = (bn) bgVar;
            mVar.a = bnVar.v();
            mVar.a(true);
            bf bfVar = new bf((byte) 11, (byte) 12, bnVar.s());
            mVar.b = new HashMap(bfVar.c * 2);
            for (int i = 0; i < bfVar.c; i++) {
                String v = bnVar.v();
                x xVar = new x();
                xVar.a((bg) bnVar);
                mVar.b.put(v, xVar);
            }
            mVar.b(true);
            mVar.e = bnVar.t();
            mVar.e(true);
            BitSet b = bnVar.b(2);
            if (b.get(0)) {
                mVar.c = bnVar.t();
                mVar.c(true);
            }
            if (b.get(1)) {
                mVar.d = bnVar.s();
                mVar.d(true);
            }
        }
    }

    /* Event */
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

    /* Event */
    public enum e implements as {
        NAME((short) 1, "name"),
        PROPERTIES((short) 2, "properties"),
        DURATION((short) 3, "duration"),
        ACC((short) 4, "acc"),
        TS((short) 5, DeviceInfo.TAG_TIMESTAMPS);
        
        private static final Map<String, e> f = null;
        private final short g;
        private final String h;

        static {
            f = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                f.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return NAME;
                case 2:
                    return PROPERTIES;
                case 3:
                    return DURATION;
                case 4:
                    return ACC;
                case 5:
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
            return (e) f.get(str);
        }

        private e(short s, String str) {
            this.g = s;
            this.h = str;
        }

        public short a() {
            return this.g;
        }

        public String b() {
            return this.h;
        }
    }

    static {
        m.put(bq.class, new b());
        m.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.NAME, new aw("name", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.PROPERTIES, new aw("properties", (byte) 1, new az((byte) 13, new ax((byte) 11), new ba((byte) 12, x.class))));
        enumMap.put(e.DURATION, new aw("duration", (byte) 2, new ax((byte) 10)));
        enumMap.put(e.ACC, new aw("acc", (byte) 2, new ax((byte) 8)));
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        f = Collections.unmodifiableMap(enumMap);
        aw.a(m.class, f);
    }

    public m a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public m a(Map<String, x> map) {
        this.b = map;
        return this;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public m a(long j) {
        this.c = j;
        c(true);
        return this;
    }

    public boolean a() {
        return al.a(this.n, 0);
    }

    public void c(boolean z) {
        this.n = al.a(this.n, 0, z);
    }

    public m a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public boolean b() {
        return al.a(this.n, 1);
    }

    public void d(boolean z) {
        this.n = al.a(this.n, 1, z);
    }

    public m b(long j) {
        this.e = j;
        e(true);
        return this;
    }

    public boolean c() {
        return al.a(this.n, 2);
    }

    public void e(boolean z) {
        this.n = al.a(this.n, 2, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) m.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) m.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Event(");
        stringBuilder.append("name:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("properties:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("duration:");
            stringBuilder.append(this.c);
        }
        if (b()) {
            stringBuilder.append(", ");
            stringBuilder.append("acc:");
            stringBuilder.append(this.d);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.e);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void d() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'name' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new bh("Required field 'properties' was not present! Struct: " + toString());
        }
    }
}
