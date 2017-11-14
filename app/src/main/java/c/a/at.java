package c.a;

import c.a.bc.a;
import java.io.ByteArrayOutputStream;

/* TSerializer */
public class at {
    private final ByteArrayOutputStream a;
    private final bs b;
    private bg c;

    public at() {
        this(new a());
    }

    public at(bj bjVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new bs(this.a);
        this.c = bjVar.a(this.b);
    }

    public byte[] a(an anVar) throws ar {
        this.a.reset();
        anVar.b(this.c);
        return this.a.toByteArray();
    }
}
