package com.b.a.b.a;

import com.b.a.d.c;
import com.b.a.i;
import com.b.a.l;
import com.b.a.n;
import com.b.a.o;
import com.b.a.q;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* JsonTreeWriter */
public final class f extends c {
    private static final Writer a = new Writer() {
        public void write(char[] cArr, int i, int i2) {
            throw new AssertionError();
        }

        public void flush() throws IOException {
            throw new AssertionError();
        }

        public void close() throws IOException {
            throw new AssertionError();
        }
    };
    private static final q b = new q("closed");
    private final List<l> c = new ArrayList();
    private String d;
    private l e = n.a;

    public f() {
        super(a);
    }

    public l a() {
        if (this.c.isEmpty()) {
            return this.e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.c);
    }

    private l j() {
        return (l) this.c.get(this.c.size() - 1);
    }

    private void a(l lVar) {
        if (this.d != null) {
            if (!lVar.j() || i()) {
                ((o) j()).a(this.d, lVar);
            }
            this.d = null;
        } else if (this.c.isEmpty()) {
            this.e = lVar;
        } else {
            l j = j();
            if (j instanceof i) {
                ((i) j).a(lVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public c b() throws IOException {
        l iVar = new i();
        a(iVar);
        this.c.add(iVar);
        return this;
    }

    public c c() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof i) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public c d() throws IOException {
        l oVar = new o();
        a(oVar);
        this.c.add(oVar);
        return this;
    }

    public c e() throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof o) {
            this.c.remove(this.c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public c a(String str) throws IOException {
        if (this.c.isEmpty() || this.d != null) {
            throw new IllegalStateException();
        } else if (j() instanceof o) {
            this.d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public c b(String str) throws IOException {
        if (str == null) {
            return f();
        }
        a(new q(str));
        return this;
    }

    public c f() throws IOException {
        a(n.a);
        return this;
    }

    public c a(boolean z) throws IOException {
        a(new q(Boolean.valueOf(z)));
        return this;
    }

    public c a(long j) throws IOException {
        a(new q(Long.valueOf(j)));
        return this;
    }

    public c a(Number number) throws IOException {
        if (number == null) {
            return f();
        }
        if (!g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        a(new q(number));
        return this;
    }

    public void flush() throws IOException {
    }

    public void close() throws IOException {
        if (this.c.isEmpty()) {
            this.c.add(b);
            return;
        }
        throw new IOException("Incomplete document");
    }
}
