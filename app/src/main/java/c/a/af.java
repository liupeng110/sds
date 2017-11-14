package c.a;

import com.sds.android.cloudapi.ttpod.data.StarCategory;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* UserInfo */
public class af implements an<af, e>, Serializable, Cloneable {
    public static final Map<e, aw> e;
    private static final bm f = new bm("UserInfo");
    private static final bd g = new bd("gender", (byte) 8, (short) 1);
    private static final bd h = new bd("age", (byte) 8, (short) 2);
    private static final bd i = new bd(StarCategory.KEY_STAR_CATEGORY_ID, (byte) 11, (short) 3);
    private static final bd j = new bd("source", (byte) 11, (short) 4);
    private static final Map<Class<? extends bo>, bp> k = new HashMap();
    public n a;
    public int b;
    public String c;
    public String d;
    private byte l = (byte) 0;
    private e[] m = new e[]{e.GENDER, e.AGE, e.ID, e.SOURCE};

    /* UserInfo */
    private static class a extends bq<af> {
        private a() {
        }

        public void a(bg bgVar, af afVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    afVar.e();
                    return;
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        afVar.a = n.a(bgVar.s());
                        afVar.a(true);
                        break;
                    case (short) 2:
                        if (h.b != (byte) 8) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        afVar.b = bgVar.s();
                        afVar.b(true);
                        break;
                    case (short) 3:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        afVar.c = bgVar.v();
                        afVar.c(true);
                        break;
                    case (short) 4:
                        if (h.b != (byte) 11) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        afVar.d = bgVar.v();
                        afVar.d(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, af afVar) throws ar {
            afVar.e();
            bgVar.a(af.f);
            if (afVar.a != null && afVar.a()) {
                bgVar.a(af.g);
                bgVar.a(afVar.a.a());
                bgVar.b();
            }
            if (afVar.b()) {
                bgVar.a(af.h);
                bgVar.a(afVar.b);
                bgVar.b();
            }
            if (afVar.c != null && afVar.c()) {
                bgVar.a(af.i);
                bgVar.a(afVar.c);
                bgVar.b();
            }
            if (afVar.d != null && afVar.d()) {
                bgVar.a(af.j);
                bgVar.a(afVar.d);
                bgVar.b();
            }
            bgVar.c();
            bgVar.a();
        }
    }

    /* UserInfo */
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

    /* UserInfo */
    private static class c extends br<af> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (af) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (af) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, af afVar) throws ar {
            bn bnVar = (bn) bgVar;
            BitSet bitSet = new BitSet();
            if (afVar.a()) {
                bitSet.set(0);
            }
            if (afVar.b()) {
                bitSet.set(1);
            }
            if (afVar.c()) {
                bitSet.set(2);
            }
            if (afVar.d()) {
                bitSet.set(3);
            }
            bnVar.a(bitSet, 4);
            if (afVar.a()) {
                bnVar.a(afVar.a.a());
            }
            if (afVar.b()) {
                bnVar.a(afVar.b);
            }
            if (afVar.c()) {
                bnVar.a(afVar.c);
            }
            if (afVar.d()) {
                bnVar.a(afVar.d);
            }
        }

        public void b(bg bgVar, af afVar) throws ar {
            bn bnVar = (bn) bgVar;
            BitSet b = bnVar.b(4);
            if (b.get(0)) {
                afVar.a = n.a(bnVar.s());
                afVar.a(true);
            }
            if (b.get(1)) {
                afVar.b = bnVar.s();
                afVar.b(true);
            }
            if (b.get(2)) {
                afVar.c = bnVar.v();
                afVar.c(true);
            }
            if (b.get(3)) {
                afVar.d = bnVar.v();
                afVar.d(true);
            }
        }
    }

    /* UserInfo */
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

    /* UserInfo */
    public enum e implements as {
        GENDER((short) 1, "gender"),
        AGE((short) 2, "age"),
        ID((short) 3, StarCategory.KEY_STAR_CATEGORY_ID),
        SOURCE((short) 4, "source");
        
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
                    return GENDER;
                case 2:
                    return AGE;
                case 3:
                    return ID;
                case 4:
                    return SOURCE;
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
        enumMap.put(e.GENDER, new aw("gender", (byte) 2, new av((byte) 16, n.class)));
        enumMap.put(e.AGE, new aw("age", (byte) 2, new ax((byte) 8)));
        enumMap.put(e.ID, new aw(StarCategory.KEY_STAR_CATEGORY_ID, (byte) 2, new ax((byte) 11)));
        enumMap.put(e.SOURCE, new aw("source", (byte) 2, new ax((byte) 11)));
        e = Collections.unmodifiableMap(enumMap);
        aw.a(af.class, e);
    }

    public af a(n nVar) {
        this.a = nVar;
        return this;
    }

    public boolean a() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public af a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public boolean b() {
        return al.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = al.a(this.l, 0, z);
    }

    public af a(String str) {
        this.c = str;
        return this;
    }

    public boolean c() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public af b(String str) {
        this.d = str;
        return this;
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
        Object obj = null;
        StringBuilder stringBuilder = new StringBuilder("UserInfo(");
        Object obj2 = 1;
        if (a()) {
            stringBuilder.append("gender:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj2 = null;
        }
        if (b()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("age:");
            stringBuilder.append(this.b);
            obj2 = null;
        }
        if (c()) {
            if (obj2 == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("id:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        } else {
            obj = obj2;
        }
        if (d()) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("source:");
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
    }
}
