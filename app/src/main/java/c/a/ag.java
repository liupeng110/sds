package c.a;

import com.igexin.download.Downloads;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* UMEnvelope */
public class ag implements an<ag, e>, Serializable, Cloneable {
    public static final Map<e, aw> j;
    private static final bm k = new bm("UMEnvelope");
    private static final bd l = new bd("version", (byte) 11, (short) 1);
    private static final bd m = new bd("address", (byte) 11, (short) 2);
    private static final bd n = new bd("signature", (byte) 11, (short) 3);
    private static final bd o = new bd("serial_num", (byte) 8, (short) 4);
    private static final bd p = new bd("ts_secs", (byte) 8, (short) 5);
    private static final bd q = new bd("length", (byte) 8, (short) 6);
    private static final bd r = new bd(Downloads.COLUMN_APP_DATA, (byte) 11, (short) 7);
    private static final bd s = new bd("guid", (byte) 11, (short) 8);
    private static final bd t = new bd("checksum", (byte) 11, (short) 9);
    private static final Map<Class<? extends bo>, bp> u = new HashMap();
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    private byte v = (byte) 0;

    /* UMEnvelope */
    private static class a extends bq<ag> {
        private a() {
        }

        public void a(bg bgVar, ag agVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (!agVar.a()) {
                        throw new bh("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!agVar.b()) {
                        throw new bh("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (agVar.c()) {
                        agVar.d();
                        return;
                    } else {
                        throw new bh("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.a = bgVar.v();
                        agVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.b = bgVar.v();
                        agVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.c = bgVar.v();
                        agVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.d = bgVar.s();
                        agVar.d(true);
                        break;
                    case (short) 5:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.e = bgVar.s();
                        agVar.e(true);
                        break;
                    case (short) 6:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.f = bgVar.s();
                        agVar.f(true);
                        break;
                    case (short) 7:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.g = bgVar.w();
                        agVar.g(true);
                        break;
                    case (short) 8:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.h = bgVar.v();
                        agVar.h(true);
                        break;
                    case (short) 9:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        agVar.i = bgVar.v();
                        agVar.i(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, ag agVar) throws ar {
            agVar.d();
            bgVar.a(ag.k);
            if (agVar.a != null) {
                bgVar.a(ag.l);
                bgVar.a(agVar.a);
                bgVar.b();
            }
            if (agVar.b != null) {
                bgVar.a(ag.m);
                bgVar.a(agVar.b);
                bgVar.b();
            }
            if (agVar.c != null) {
                bgVar.a(ag.n);
                bgVar.a(agVar.c);
                bgVar.b();
            }
            bgVar.a(ag.o);
            bgVar.a(agVar.d);
            bgVar.b();
            bgVar.a(ag.p);
            bgVar.a(agVar.e);
            bgVar.b();
            bgVar.a(ag.q);
            bgVar.a(agVar.f);
            bgVar.b();
            if (agVar.g != null) {
                bgVar.a(ag.r);
                bgVar.a(agVar.g);
                bgVar.b();
            }
            if (agVar.h != null) {
                bgVar.a(ag.s);
                bgVar.a(agVar.h);
                bgVar.b();
            }
            if (agVar.i != null) {
                bgVar.a(ag.t);
                bgVar.a(agVar.i);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* UMEnvelope */
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

    /* UMEnvelope */
    private static class c extends br<ag> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (ag) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (ag) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, ag agVar) throws ar {
            bn bnVar = (bn) bgVar;
            bnVar.a(agVar.a);
            bnVar.a(agVar.b);
            bnVar.a(agVar.c);
            bnVar.a(agVar.d);
            bnVar.a(agVar.e);
            bnVar.a(agVar.f);
            bnVar.a(agVar.g);
            bnVar.a(agVar.h);
            bnVar.a(agVar.i);
        }

        public void b(bg bgVar, ag agVar) throws ar {
            bn bnVar = (bn) bgVar;
            agVar.a = bnVar.v();
            agVar.a(true);
            agVar.b = bnVar.v();
            agVar.b(true);
            agVar.c = bnVar.v();
            agVar.c(true);
            agVar.d = bnVar.s();
            agVar.d(true);
            agVar.e = bnVar.s();
            agVar.e(true);
            agVar.f = bnVar.s();
            agVar.f(true);
            agVar.g = bnVar.w();
            agVar.g(true);
            agVar.h = bnVar.v();
            agVar.h(true);
            agVar.i = bnVar.v();
            agVar.i(true);
        }
    }

    /* UMEnvelope */
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

    /* UMEnvelope */
    public enum e implements as {
        VERSION((short) 1, "version"),
        ADDRESS((short) 2, "address"),
        SIGNATURE((short) 3, "signature"),
        SERIAL_NUM((short) 4, "serial_num"),
        TS_SECS((short) 5, "ts_secs"),
        LENGTH((short) 6, "length"),
        ENTITY((short) 7, Downloads.COLUMN_APP_DATA),
        GUID((short) 8, "guid"),
        CHECKSUM((short) 9, "checksum");
        
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
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
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
        enumMap.put(e.VERSION, new aw("version", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.ADDRESS, new aw("address", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.SIGNATURE, new aw("signature", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new aw("serial_num", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.TS_SECS, new aw("ts_secs", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.LENGTH, new aw("length", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.ENTITY, new aw(Downloads.COLUMN_APP_DATA, (byte) 1, new ax((byte) 11, true)));
        enumMap.put(e.GUID, new aw("guid", (byte) 1, new ax((byte) 11)));
        enumMap.put(e.CHECKSUM, new aw("checksum", (byte) 1, new ax((byte) 11)));
        j = Collections.unmodifiableMap(enumMap);
        aw.a(ag.class, j);
    }

    public ag a(String str) {
        this.a = str;
        return this;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public ag b(String str) {
        this.b = str;
        return this;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public ag c(String str) {
        this.c = str;
        return this;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public ag a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public boolean a() {
        return al.a(this.v, 0);
    }

    public void d(boolean z) {
        this.v = al.a(this.v, 0, z);
    }

    public ag b(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public boolean b() {
        return al.a(this.v, 1);
    }

    public void e(boolean z) {
        this.v = al.a(this.v, 1, z);
    }

    public ag c(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public boolean c() {
        return al.a(this.v, 2);
    }

    public void f(boolean z) {
        this.v = al.a(this.v, 2, z);
    }

    public ag a(byte[] bArr) {
        a(bArr == null ? null : ByteBuffer.wrap(bArr));
        return this;
    }

    public ag a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public ag d(String str) {
        this.h = str;
        return this;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public ag e(String str) {
        this.i = str;
        return this;
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
        StringBuilder stringBuilder = new StringBuilder("UMEnvelope(");
        stringBuilder.append("version:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("address:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signature:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serial_num:");
        stringBuilder.append(this.d);
        stringBuilder.append(", ");
        stringBuilder.append("ts_secs:");
        stringBuilder.append(this.e);
        stringBuilder.append(", ");
        stringBuilder.append("length:");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append("entity:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            ap.a(this.g, stringBuilder);
        }
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.i);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void d() throws ar {
        if (this.a == null) {
            throw new bh("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new bh("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new bh("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new bh("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new bh("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
            throw new bh("Required field 'checksum' was not present! Struct: " + toString());
        }
    }
}
