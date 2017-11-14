package com.b.a.b;

import com.b.a.b.a.m;
import com.b.a.d.c;
import com.b.a.l;
import com.b.a.n;
import com.b.a.p;
import com.b.a.t;
import java.io.IOException;
import java.io.Writer;

/* Streams */
public final class j {

    /* Streams */
    private static final class a extends Writer {
        private final Appendable a;
        private final a b;

        /* Streams */
        static class a implements CharSequence {
            char[] a;

            a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }

        private a(Appendable appendable) {
            this.b = new a();
            this.a = appendable;
        }

        public void write(char[] cArr, int i, int i2) throws IOException {
            this.b.a = cArr;
            this.a.append(this.b, i, i + i2);
        }

        public void write(int i) throws IOException {
            this.a.append((char) i);
        }

        public void flush() {
        }

        public void close() {
        }
    }

    public static l a(com.b.a.d.a aVar) throws p {
        Object obj = 1;
        try {
            aVar.f();
            obj = null;
            return (l) m.P.b(aVar);
        } catch (Throwable e) {
            if (obj != null) {
                return n.a;
            }
            throw new t(e);
        } catch (Throwable e2) {
            throw new t(e2);
        } catch (Throwable e22) {
            throw new com.b.a.m(e22);
        } catch (Throwable e222) {
            throw new t(e222);
        }
    }

    public static void a(l lVar, c cVar) throws IOException {
        m.P.a(cVar, lVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
