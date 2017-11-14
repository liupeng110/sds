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

/* IdJournal */
public class o implements an<o, e>, Serializable, Cloneable {
    public static final Map<e, aw> e;
    private static final bm f = new bm("IdJournal");
    private static final bd g = new bd("domain", (byte) 11, (short) 1);
    private static final bd h = new bd("old_id", (byte) 11, (short) 2);
    private static final bd i = new bd("new_id", (byte) 11, (short) 3);
    private static final bd j = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 4);
    private static final Map<Class<? extends bo>, bp> k = new HashMap();
    public String a;
    public String b;
    public String c;
    public long d;
    private byte l = (byte) 0;
    private e[] m = new e[]{e.OLD_ID};

    /* IdJournal */
    private static class a extends bq<o> {
        private a() {
        }

        public void a(bg bgVar, o oVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (oVar.b()) {
                        oVar.c();
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
                        oVar.a = bgVar.v();
                        oVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        oVar.b = bgVar.v();
                        oVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        oVar.c = bgVar.v();
                        oVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        oVar.d = bgVar.t();
                        oVar.d(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, o oVar) throws ar {
            oVar.c();
            bgVar.a(o.f);
            if (oVar.a != null) {
                bgVar.a(o.g);
                bgVar.a(oVar.a);
                bgVar.b();
            }
            if (oVar.b != null && oVar.a()) {
                bgVar.a(o.h);
                bgVar.a(oVar.b);
                bgVar.b();
            }
            if (oVar.c != null) {
                bgVar.a(o.i);
                bgVar.a(oVar.c);
                bgVar.b();
            }
            bgVar.a(o.j);
            bgVar.a(oVar.d);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* IdJournal */
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

    /* IdJournal */
    private static class c extends br<o> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (o) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (o) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, o oVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(oVar.a);
            bnVar.a(oVar.c);
            bnVar.a(oVar.d);
            BitSet bitSet = new BitSet();
            if (oVar.a()) {
                bitSet.set(0);
            }
            bnVar.a(bitSet, 1);
            if (oVar.a()) {
                bnVar.a(oVar.b);
            }
        }

        public void b(bg bgVar, o oVar) throws ar {
            bn bnVar = (bn) bgVar;
            oVar.a = bnVar.v();
            oVar.a(true);
            oVar.c = bnVar.v();
            oVar.c(true);
            oVar.d = bnVar.t();
            oVar.d(true);
            if (bnVar.b(1).get(0)) {
                oVar.b = bnVar.v();
                oVar.b(true);
            }
        }
    }

    /* IdJournal */
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

    /* IdJournal */
    public enum e implements as {
        DOMAIN((short) 1, "domain"),
        OLD_ID((short) 2, "old_id"),
        NEW_ID((short) 3, "new_id"),
        TS((short) 4, DeviceInfo.TAG_TIMESTAMPS);
        
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
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
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
        enumMap.put(e.DOMAIN, new aw("domain", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.OLD_ID, new aw("old_id", (byte) 2, new ax((byte) 11)));
        enumMap.put(e.NEW_ID, new aw("new_id", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        aw.a(o.class, e);
    }

    public o a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public o b(String str) {
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

    public o c(String str) {
        this.c = str;
        return this;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public o a(long j) {
        this.d = j;
        d(true);
        return this;
    }

    public boolean b() {
        return al.a(this.l, 0);
    }

    public void d(boolean z) {
        this.l = al.a(this.l, 0, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) k.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) k.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (a()) {
            stringBuilder.append(", ");
            stringBuilder.append("old_id:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("new_id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.d);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void c() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bh("Required field 'new_id' was not present! Struct: " + toString());
        }
    }
}
