package c.a;

import com.tencent.open.SocialConstants;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* Response */
public class aa implements an<aa, e>, Serializable, Cloneable {
    public static final Map<e, aw> d;
    private static final bm e = new bm("Response");
    private static final bd f = new bd("resp_code", (byte) 8, (short) 1);
    private static final bd g = new bd(SocialConstants.PARAM_SEND_MSG, (byte) 11, (short) 2);
    private static final bd h = new bd("imprint", (byte) 12, (short) 3);
    private static final Map<Class<? extends bo>, bp> i = new HashMap();
    public int a;
    public String b;
    public r c;
    private byte j = (byte) 0;
    private e[] k = new e[]{e.MSG, e.IMPRINT};

    /* Response */
    private static class a extends bq<aa> {
        private a() {
        }

        public void a(bg bgVar, aa aaVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (aaVar.a()) {
                        aaVar.f();
                        return;
                    }
                    throw new bh("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aaVar.a = bgVar.s();
                        aaVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aaVar.b = bgVar.v();
                        aaVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 12) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        aaVar.c = new r();
                        aaVar.c.a(bgVar);
                        aaVar.c(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, aa aaVar) throws ar {
            aaVar.f();
            bgVar.a(aa.e);
            bgVar.a(aa.f);
            bgVar.a(aaVar.a);
            bgVar.b();
            if (aaVar.b != null && aaVar.c()) {
                bgVar.a(aa.g);
                bgVar.a(aaVar.b);
                bgVar.b();
            }
            if (aaVar.c != null && aaVar.e()) {
                bgVar.a(aa.h);
                aaVar.c.b(bgVar);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* Response */
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

    /* Response */
    private static class c extends br<aa> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (aa) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (aa) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, aa aaVar) throws ar {
            bgVar = (bn) bgVar;
            bgVar.a(aaVar.a);
            BitSet bitSet = new BitSet();
            if (aaVar.c()) {
                bitSet.set(0);
            }
            if (aaVar.e()) {
                bitSet.set(1);
            }
            bgVar.a(bitSet, 2);
            if (aaVar.c()) {
                bgVar.a(aaVar.b);
            }
            if (aaVar.e()) {
                aaVar.c.b(bgVar);
            }
        }

        public void b(bg bgVar, aa aaVar) throws ar {
            bgVar = (bn) bgVar;
            aaVar.a = bgVar.s();
            aaVar.a(true);
            BitSet b = bgVar.b(2);
            if (b.get(0)) {
                aaVar.b = bgVar.v();
                aaVar.b(true);
            }
            if (b.get(1)) {
                aaVar.c = new r();
                aaVar.c.a(bgVar);
                aaVar.c(true);
            }
        }
    }

    /* Response */
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

    /* Response */
    public enum e implements as {
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, SocialConstants.PARAM_SEND_MSG),
        IMPRINT((short) 3, "imprint");
        
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
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
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
        enumMap.put(e.RESP_CODE, new aw("resp_code", (byte) 1, new ax((byte) 8)));
        enumMap.put(e.MSG, new aw(SocialConstants.PARAM_SEND_MSG, (byte) 2, new ax((byte) 11)));
        enumMap.put(e.IMPRINT, new aw("imprint", (byte) 2, new ba((byte) 12, r.class)));
        d = Collections.unmodifiableMap(enumMap);
        aw.a(aa.class, d);
    }

    public boolean a() {
        return al.a(this.j, 0);
    }

    public void a(boolean z) {
        this.j = al.a(this.j, 0, z);
    }

    public String b() {
        return this.b;
    }

    public boolean c() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public r d() {
        return this.c;
    }

    public boolean e() {
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
        StringBuilder stringBuilder = new StringBuilder("Response(");
        stringBuilder.append("resp_code:");
        stringBuilder.append(this.a);
        if (c()) {
            stringBuilder.append(", ");
            stringBuilder.append("msg:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (e()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void f() throws ar {
        if (this.c != null) {
            this.c.e();
        }
    }
}
