package c.a;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* TUnion */
public abstract class au<T extends au<?, ?>, F extends as> implements an<T, F> {
    private static final Map<Class<? extends bo>, bp> a = new HashMap();
    protected Object b = null;
    protected F c = null;

    /* TUnion */
    private static class a extends bq<au> {
        private a() {
        }

        public void a(bg bgVar, au auVar) throws ar {
            auVar.c = null;
            auVar.b = null;
            bgVar.f();
            bd h = bgVar.h();
            auVar.b = auVar.a(bgVar, h);
            if (auVar.b != null) {
                auVar.c = auVar.b(h.c);
            }
            bgVar.i();
            bgVar.h();
            bgVar.g();
        }

        public void b(bg bgVar, au auVar) throws ar {
            if (auVar.c() == null || auVar.d() == null) {
                throw new bh("Cannot write a TUnion with no set value!");
            }
            bgVar.a(auVar.a());
            bgVar.a(auVar.a(auVar.c));
            auVar.c(bgVar);
            bgVar.b();
            bgVar.c();
            bgVar.a();
        }
    }

    /* TUnion */
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

    /* TUnion */
    private static class c extends br<au> {
        private c() {
        }

        public void a(bg bgVar, au auVar) throws ar {
            auVar.c = null;
            auVar.b = null;
            short r = bgVar.r();
            auVar.b = auVar.a(bgVar, r);
            if (auVar.b != null) {
                auVar.c = auVar.b(r);
            }
        }

        public void b(bg bgVar, au auVar) throws ar {
            if (auVar.c() == null || auVar.d() == null) {
                throw new bh("Cannot write a TUnion with no set value!");
            }
            bgVar.a(auVar.c.a());
            auVar.d(bgVar);
        }
    }

    /* TUnion */
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

    protected abstract bd a(F f);

    protected abstract bm a();

    protected abstract Object a(bg bgVar, bd bdVar) throws ar;

    protected abstract Object a(bg bgVar, short s) throws ar;

    protected abstract F b(short s);

    protected abstract void c(bg bgVar) throws ar;

    protected abstract void d(bg bgVar) throws ar;

    protected au() {
    }

    static {
        a.put(bq.class, new b());
        a.put(br.class, new d());
    }

    public F c() {
        return this.c;
    }

    public Object d() {
        return this.b;
    }

    public boolean e() {
        return this.c != null;
    }

    public void a(bg bgVar) throws ar {
        ((bp) a.get(bgVar.y())).b().a(bgVar, this);
    }

    public void b(bg bgVar) throws ar {
        ((bp) a.get(bgVar.y())).b().b(bgVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" ");
        if (c() != null) {
            Object d = d();
            stringBuilder.append(a(c()).a);
            stringBuilder.append(":");
            if (d instanceof ByteBuffer) {
                ap.a((ByteBuffer) d, stringBuilder);
            } else {
                stringBuilder.append(d.toString());
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
