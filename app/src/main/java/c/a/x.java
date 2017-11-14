package c.a;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* PropertyValue */
public class x extends au<x, a> {
    public static final Map<a, aw> a;
    private static final bm d = new bm("PropertyValue");
    private static final bd e = new bd("string_value", (byte) 11, (short) 1);
    private static final bd f = new bd("long_value", (byte) 10, (short) 2);
    private static /* synthetic */ int[] g;

    /* PropertyValue */
    public enum a implements as {
        STRING_VALUE((short) 1, "string_value"),
        LONG_VALUE((short) 2, "long_value");
        
        private static final Map<String, a> c = null;
        private final short d;
        private final String e;

        static {
            c = new HashMap();
            Iterator it = EnumSet.allOf(a.class).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                c.put(aVar.b(), aVar);
            }
        }

        public static a a(int i) {
            switch (i) {
                case 1:
                    return STRING_VALUE;
                case 2:
                    return LONG_VALUE;
                default:
                    return null;
            }
        }

        public static a b(int i) {
            a a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static a a(String str) {
            return (a) c.get(str);
        }

        private a(short s, String str) {
            this.d = s;
            this.e = str;
        }

        public short a() {
            return this.d;
        }

        public String b() {
            return this.e;
        }
    }

    protected /* synthetic */ as b(short s) {
        return a(s);
    }

    static /* synthetic */ int[] b() {
        int[] iArr = g;
        if (iArr == null) {
            iArr = new int[a.values().length];
            try {
                iArr[a.LONG_VALUE.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[a.STRING_VALUE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            g = iArr;
        }
        return iArr;
    }

    static {
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.STRING_VALUE, new aw("string_value", (byte) 3, new ax((byte) 11)));
        enumMap.put(a.LONG_VALUE, new aw("long_value", (byte) 3, new ax((byte) 10)));
        a = Collections.unmodifiableMap(enumMap);
        aw.a(x.class, a);
    }

    protected Object a(bg bgVar, bd bdVar) throws ar {
        a a = a.a(bdVar.c);
        if (a == null) {
            return null;
        }
        switch (b()[a.ordinal()]) {
            case 1:
                if (bdVar.b == e.b) {
                    return bgVar.v();
                }
                bk.a(bgVar, bdVar.b);
                return null;
            case 2:
                if (bdVar.b == f.b) {
                    return Long.valueOf(bgVar.t());
                }
                bk.a(bgVar, bdVar.b);
                return null;
            default:
                throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
        }
    }

    protected void c(bg bgVar) throws ar {
        switch (b()[((a) this.c).ordinal()]) {
            case 1:
                bgVar.a((String) this.b);
                return;
            case 2:
                bgVar.a(((Long) this.b).longValue());
                return;
            default:
                throw new IllegalStateException("Cannot write union with unknown field " + this.c);
        }
    }

    protected Object a(bg bgVar, short s) throws ar {
        a a = a.a((int) s);
        if (a != null) {
            switch (b()[a.ordinal()]) {
                case 1:
                    return bgVar.v();
                case 2:
                    return Long.valueOf(bgVar.t());
                default:
                    throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
            }
        }
        throw new bh("Couldn't find a field with field id " + s);
    }

    protected void d(bg bgVar) throws ar {
        switch (b()[((a) this.c).ordinal()]) {
            case 1:
                bgVar.a((String) this.b);
                return;
            case 2:
                bgVar.a(((Long) this.b).longValue());
                return;
            default:
                throw new IllegalStateException("Cannot write union with unknown field " + this.c);
        }
    }

    protected bd a(a aVar) {
        switch (b()[aVar.ordinal()]) {
            case 1:
                return e;
            case 2:
                return f;
            default:
                throw new IllegalArgumentException("Unknown field id " + aVar);
        }
    }

    protected bm a() {
        return d;
    }

    protected a a(short s) {
        return a.b(s);
    }

    public void a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.c = a.STRING_VALUE;
        this.b = str;
    }

    public void a(long j) {
        this.c = a.LONG_VALUE;
        this.b = Long.valueOf(j);
    }

    public boolean equals(Object obj) {
        if (obj instanceof x) {
            return a((x) obj);
        }
        return false;
    }

    public boolean a(x xVar) {
        return xVar != null && c() == xVar.c() && d().equals(xVar.d());
    }

    public int hashCode() {
        return 0;
    }
}
