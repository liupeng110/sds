package com.b.a;

import com.b.a.b.j;
import com.b.a.d.c;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* JsonElement */
public abstract class l {
    public boolean g() {
        return this instanceof i;
    }

    public boolean h() {
        return this instanceof o;
    }

    public boolean i() {
        return this instanceof q;
    }

    public boolean j() {
        return this instanceof n;
    }

    public o k() {
        if (h()) {
            return (o) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public i l() {
        if (g()) {
            return (i) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public q m() {
        if (i()) {
            return (q) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public boolean f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    Boolean n() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public Number a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public double c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public long d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public int e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            c cVar = new c(stringWriter);
            cVar.b(true);
            j.a(this, cVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
