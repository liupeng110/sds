package c.a;

import com.tencent.stat.DeviceInfo;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* ActivateMsg */
public class g implements an<g, e>, Serializable, Cloneable {
    public static final Map<e, aw> b;
    private static final bm c = new bm("ActivateMsg");
    private static final bd d = new bd(DeviceInfo.TAG_TIMESTAMPS, (byte) 10, (short) 1);
    private static final Map<Class<? extends bo>, bp> e = new HashMap();
    public long a;
    private byte f;

    /* ActivateMsg */
    private static class a extends bq<g> {
        private a() {
        }

        public void a(bg bgVar, g gVar) throws ar {
            bgVar.f();
            while (true) {
                bd h = bgVar.h();
                if (h.b == (byte) 0) {
                    bgVar.g();
                    if (gVar.a()) {
                        gVar.b();
                        return;
                    }
                    throw new bh("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (h.c) {
                    case (short) 1:
                        if (h.b != (byte) 10) {
                            bk.a(bgVar, h.b);
                            break;
                        }
                        gVar.a = bgVar.t();
                        gVar.a(true);
                        break;
                    default:
                        bk.a(bgVar, h.b);
                        break;
                }
                bgVar.i();
            }
        }

        public void b(bg bgVar, g gVar) throws ar {
            gVar.b();
            bgVar.a(g.c);
            bgVar.a(g.d);
            bgVar.a(gVar.a);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* ActivateMsg */
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

    /* ActivateMsg */
    private static class c extends br<g> {
        public /* synthetic */ void a(bg bgVar, an anVar) throws ar {
            b(bgVar, (g) anVar);
        }

        public /* synthetic */ void b(bg bgVar, an anVar) throws ar {
            a(bgVar, (g) anVar);
        }

        private c() {
        }

        public void a(bg bgVar, g gVar) throws ar {
            ((bn) bgVar).a(gVar.a);
        }

        public void b(bg bgVar, g gVar) throws ar {
            gVar.a = ((bn) bgVar).t();
            gVar.a(true);
        }
    }

    /* ActivateMsg */
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

    /* ActivateMsg */
    public enum e implements as {
        TS((short) 1, DeviceInfo.TAG_TIMESTAMPS);
        
        private static final Map<String, e> b = null;
        private final short c;
        private final String d;

        static {
            b = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                b.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
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
            return (e) b.get(str);
        }

        private e(short s, String str) {
            this.c = s;
            this.d = str;
        }

        public short a() {
            return this.c;
        }

        public String b() {
            return this.d;
        }
    }

    static {
        e.put(bq.class, new b());
        e.put(br.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.TS, new aw(DeviceInfo.TAG_TIMESTAMPS, (byte) 1, new ax((byte) 10)));
        b = Collections.unmodifiableMap(enumMap);
        aw.a(g.class, b);
    }

    public g() {
        this.f = (byte) 0;
    }

    public g(long j) {
        this();
        this.a = j;
        a(true);
    }

    public boolean a() {
        return al.a(this.f, 0);
    }

    public void a(boolean z) {
        this.f = al.a(this.f, 0, z);
    }

    public void a(bg bgVar) throws ar {
        ((bp) e.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) e.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ActivateMsg(");
        stringBuilder.append("ts:");
        stringBuilder.append(this.a);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void b() throws ar {
    }
}
