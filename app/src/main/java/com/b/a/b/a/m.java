package com.b.a.b.a;

import com.b.a.d.b;
import com.b.a.d.c;
import com.b.a.f;
import com.b.a.i;
import com.b.a.l;
import com.b.a.n;
import com.b.a.o;
import com.b.a.q;
import com.b.a.t;
import com.b.a.w;
import com.b.a.x;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;

/* TypeAdapters */
public final class m {
    public static final w<StringBuffer> A = new w<StringBuffer>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public StringBuffer a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return new StringBuffer(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, StringBuffer stringBuffer) throws IOException {
            cVar.b(stringBuffer == null ? null : stringBuffer.toString());
        }
    };
    public static final x B = a(StringBuffer.class, A);
    public static final w<URL> C = new w<URL>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public URL a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if ("null".equals(h)) {
                return null;
            }
            return new URL(h);
        }

        public void a(c cVar, URL url) throws IOException {
            cVar.b(url == null ? null : url.toExternalForm());
        }
    };
    public static final x D = a(URL.class, C);
    public static final w<URI> E = new w<URI>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public URI a(com.b.a.d.a aVar) throws IOException {
            URI uri = null;
            if (aVar.f() == b.NULL) {
                aVar.j();
            } else {
                try {
                    String h = aVar.h();
                    if (!"null".equals(h)) {
                        uri = new URI(h);
                    }
                } catch (Throwable e) {
                    throw new com.b.a.m(e);
                }
            }
            return uri;
        }

        public void a(c cVar, URI uri) throws IOException {
            cVar.b(uri == null ? null : uri.toASCIIString());
        }
    };
    public static final x F = a(URI.class, E);
    public static final w<InetAddress> G = new w<InetAddress>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public InetAddress a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return InetAddress.getByName(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, InetAddress inetAddress) throws IOException {
            cVar.b(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };
    public static final x H = b(InetAddress.class, G);
    public static final w<UUID> I = new w<UUID>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public UUID a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return UUID.fromString(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, UUID uuid) throws IOException {
            cVar.b(uuid == null ? null : uuid.toString());
        }
    };
    public static final x J = a(UUID.class, I);
    public static final x K = new x() {
        public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
            if (aVar.a() != Timestamp.class) {
                return null;
            }
            final w a = fVar.a(Date.class);
            return new w<Timestamp>(this) {
                final /* synthetic */ AnonymousClass15 b;

                public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
                    return a(aVar);
                }

                public Timestamp a(com.b.a.d.a aVar) throws IOException {
                    Date date = (Date) a.b(aVar);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void a(c cVar, Timestamp timestamp) throws IOException {
                    a.a(cVar, timestamp);
                }
            };
        }
    };
    public static final w<Calendar> L = new w<Calendar>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Calendar a(com.b.a.d.a aVar) throws IOException {
            int i = 0;
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            aVar.c();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (aVar.f() != b.END_OBJECT) {
                String g = aVar.g();
                int m = aVar.m();
                if (MediasColumns.YEAR.equals(g)) {
                    i6 = m;
                } else if ("month".equals(g)) {
                    i5 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i4 = m;
                } else if ("hourOfDay".equals(g)) {
                    i3 = m;
                } else if ("minute".equals(g)) {
                    i2 = m;
                } else if ("second".equals(g)) {
                    i = m;
                }
            }
            aVar.d();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public void a(c cVar, Calendar calendar) throws IOException {
            if (calendar == null) {
                cVar.f();
                return;
            }
            cVar.d();
            cVar.a(MediasColumns.YEAR);
            cVar.a((long) calendar.get(1));
            cVar.a("month");
            cVar.a((long) calendar.get(2));
            cVar.a("dayOfMonth");
            cVar.a((long) calendar.get(5));
            cVar.a("hourOfDay");
            cVar.a((long) calendar.get(11));
            cVar.a("minute");
            cVar.a((long) calendar.get(12));
            cVar.a("second");
            cVar.a((long) calendar.get(13));
            cVar.e();
        }
    };
    public static final x M = b(Calendar.class, GregorianCalendar.class, L);
    public static final w<Locale> N = new w<Locale>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Locale a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String nextToken;
            String nextToken2;
            String nextToken3;
            StringTokenizer stringTokenizer = new StringTokenizer(aVar.h(), "_");
            if (stringTokenizer.hasMoreElements()) {
                nextToken = stringTokenizer.nextToken();
            } else {
                nextToken = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken2 = stringTokenizer.nextToken();
            } else {
                nextToken2 = null;
            }
            if (stringTokenizer.hasMoreElements()) {
                nextToken3 = stringTokenizer.nextToken();
            } else {
                nextToken3 = null;
            }
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        public void a(c cVar, Locale locale) throws IOException {
            cVar.b(locale == null ? null : locale.toString());
        }
    };
    public static final x O = a(Locale.class, N);
    public static final w<l> P = new w<l>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public l a(com.b.a.d.a aVar) throws IOException {
            l iVar;
            switch (aVar.f()) {
                case NUMBER:
                    return new q(new com.b.a.b.f(aVar.h()));
                case BOOLEAN:
                    return new q(Boolean.valueOf(aVar.i()));
                case STRING:
                    return new q(aVar.h());
                case NULL:
                    aVar.j();
                    return n.a;
                case BEGIN_ARRAY:
                    iVar = new i();
                    aVar.a();
                    while (aVar.e()) {
                        iVar.a(a(aVar));
                    }
                    aVar.b();
                    return iVar;
                case BEGIN_OBJECT:
                    iVar = new o();
                    aVar.c();
                    while (aVar.e()) {
                        iVar.a(aVar.g(), a(aVar));
                    }
                    aVar.d();
                    return iVar;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void a(c cVar, l lVar) throws IOException {
            if (lVar == null || lVar.j()) {
                cVar.f();
            } else if (lVar.i()) {
                q m = lVar.m();
                if (m.p()) {
                    cVar.a(m.a());
                } else if (m.o()) {
                    cVar.a(m.f());
                } else {
                    cVar.b(m.b());
                }
            } else if (lVar.g()) {
                cVar.b();
                Iterator it = lVar.l().iterator();
                while (it.hasNext()) {
                    a(cVar, (l) it.next());
                }
                cVar.c();
            } else if (lVar.h()) {
                cVar.d();
                for (Entry entry : lVar.k().o()) {
                    cVar.a((String) entry.getKey());
                    a(cVar, (l) entry.getValue());
                }
                cVar.e();
            } else {
                throw new IllegalArgumentException("Couldn't write " + lVar.getClass());
            }
        }
    };
    public static final x Q = b(l.class, P);
    public static final x R = a();
    public static final w<Class> a = new w<Class>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public void a(c cVar, Class cls) throws IOException {
            if (cls == null) {
                cVar.f();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        public Class a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };
    public static final x b = a(Class.class, a);
    public static final w<BitSet> c = new w<BitSet>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public BitSet a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            BitSet bitSet = new BitSet();
            aVar.a();
            b f = aVar.f();
            int i = 0;
            while (f != b.END_ARRAY) {
                boolean z;
                switch (f) {
                    case NUMBER:
                        if (aVar.m() == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case BOOLEAN:
                        z = aVar.i();
                        break;
                    case STRING:
                        String h = aVar.h();
                        try {
                            if (Integer.parseInt(h) == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new t("Error: Expecting: bitset number value (1, 0), Found: " + h);
                        }
                    default:
                        throw new t("Invalid bitset value type: " + f);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                f = aVar.f();
            }
            aVar.b();
            return bitSet;
        }

        public void a(c cVar, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                cVar.f();
                return;
            }
            cVar.b();
            for (int i = 0; i < bitSet.length(); i++) {
                int i2;
                if (bitSet.get(i)) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                cVar.a((long) i2);
            }
            cVar.c();
        }
    };
    public static final x d = a(BitSet.class, c);
    public static final w<Boolean> e = new w<Boolean>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Boolean a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            } else if (aVar.f() == b.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(aVar.h()));
            } else {
                if (aVar.f() != b.NUMBER) {
                    return Boolean.valueOf(aVar.i());
                }
                return Boolean.valueOf(aVar.l() != 0);
            }
        }

        public void a(c cVar, Boolean bool) throws IOException {
            if (bool == null) {
                cVar.f();
            } else {
                cVar.a(bool.booleanValue());
            }
        }
    };
    public static final w<Boolean> f = new w<Boolean>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Boolean a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Boolean.valueOf(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Boolean bool) throws IOException {
            cVar.b(bool == null ? "null" : bool.toString());
        }
    };
    public static final x g = a(Boolean.TYPE, Boolean.class, e);
    public static final w<Number> h = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Byte.valueOf((byte) aVar.m());
            } catch (Throwable e) {
                throw new t(e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final x i = a(Byte.TYPE, Byte.class, h);
    public static final w<Number> j = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Short.valueOf((short) aVar.m());
            } catch (Throwable e) {
                throw new t(e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final x k = a(Short.TYPE, Short.class, j);
    public static final w<Number> l = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Integer.valueOf(aVar.m());
            } catch (Throwable e) {
                throw new t(e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final x m = a(Integer.TYPE, Integer.class, l);
    public static final w<Number> n = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return Long.valueOf(aVar.l());
            } catch (Throwable e) {
                throw new t(e);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final w<Number> o = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Float.valueOf((float) aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final w<Number> p = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return Double.valueOf(aVar.k());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final w<Number> q = new w<Number>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Number a(com.b.a.d.a aVar) throws IOException {
            b f = aVar.f();
            switch (f) {
                case NUMBER:
                    return new com.b.a.b.f(aVar.h());
                case NULL:
                    aVar.j();
                    return null;
                default:
                    throw new t("Expecting number, got: " + f);
            }
        }

        public void a(c cVar, Number number) throws IOException {
            cVar.a(number);
        }
    };
    public static final x r = a(Number.class, q);
    public static final w<Character> s = new w<Character>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public Character a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            String h = aVar.h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new t("Expecting character, got: " + h);
        }

        public void a(c cVar, Character ch) throws IOException {
            cVar.b(ch == null ? null : String.valueOf(ch));
        }
    };
    public static final x t = a(Character.TYPE, Character.class, s);
    public static final w<String> u = new w<String>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public String a(com.b.a.d.a aVar) throws IOException {
            b f = aVar.f();
            if (f == b.NULL) {
                aVar.j();
                return null;
            } else if (f == b.BOOLEAN) {
                return Boolean.toString(aVar.i());
            } else {
                return aVar.h();
            }
        }

        public void a(c cVar, String str) throws IOException {
            cVar.b(str);
        }
    };
    public static final w<BigDecimal> v = new w<BigDecimal>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public BigDecimal a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigDecimal(aVar.h());
            } catch (Throwable e) {
                throw new t(e);
            }
        }

        public void a(c cVar, BigDecimal bigDecimal) throws IOException {
            cVar.a((Number) bigDecimal);
        }
    };
    public static final w<BigInteger> w = new w<BigInteger>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public BigInteger a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() == b.NULL) {
                aVar.j();
                return null;
            }
            try {
                return new BigInteger(aVar.h());
            } catch (Throwable e) {
                throw new t(e);
            }
        }

        public void a(c cVar, BigInteger bigInteger) throws IOException {
            cVar.a((Number) bigInteger);
        }
    };
    public static final x x = a(String.class, u);
    public static final w<StringBuilder> y = new w<StringBuilder>() {
        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public StringBuilder a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return new StringBuilder(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, StringBuilder stringBuilder) throws IOException {
            cVar.b(stringBuilder == null ? null : stringBuilder.toString());
        }
    };
    public static final x z = a(StringBuilder.class, y);

    /* TypeAdapters */
    private static final class a<T extends Enum<T>> extends w<T> {
        private final Map<String, T> a = new HashMap();
        private final Map<T, String> b = new HashMap();

        public /* synthetic */ Object b(com.b.a.d.a aVar) throws IOException {
            return a(aVar);
        }

        public a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    Object a;
                    String name = enumR.name();
                    com.b.a.a.c cVar = (com.b.a.a.c) cls.getField(name).getAnnotation(com.b.a.a.c.class);
                    if (cVar != null) {
                        a = cVar.a();
                    } else {
                        String str = name;
                    }
                    this.a.put(a, enumR);
                    this.b.put(enumR, a);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError();
            }
        }

        public T a(com.b.a.d.a aVar) throws IOException {
            if (aVar.f() != b.NULL) {
                return (Enum) this.a.get(aVar.h());
            }
            aVar.j();
            return null;
        }

        public void a(c cVar, T t) throws IOException {
            cVar.b(t == null ? null : (String) this.b.get(t));
        }
    }

    public static x a() {
        return new x() {
            public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
                Class a = aVar.a();
                if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                    return null;
                }
                if (!a.isEnum()) {
                    a = a.getSuperclass();
                }
                return new a(a);
            }
        };
    }

    public static <TT> x a(final Class<TT> cls, final w<TT> wVar) {
        return new x() {
            public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
                return aVar.a() == cls ? wVar : null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + wVar + "]";
            }
        };
    }

    public static <TT> x a(final Class<TT> cls, final Class<TT> cls2, final w<? super TT> wVar) {
        return new x() {
            public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
                Class a = aVar.a();
                return (a == cls || a == cls2) ? wVar : null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + wVar + "]";
            }
        };
    }

    public static <TT> x b(final Class<TT> cls, final Class<? extends TT> cls2, final w<? super TT> wVar) {
        return new x() {
            public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
                Class a = aVar.a();
                return (a == cls || a == cls2) ? wVar : null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + wVar + "]";
            }
        };
    }

    public static <TT> x b(final Class<TT> cls, final w<TT> wVar) {
        return new x() {
            public <T> w<T> a(f fVar, com.b.a.c.a<T> aVar) {
                return cls.isAssignableFrom(aVar.a()) ? wVar : null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + wVar + "]";
            }
        };
    }
}
