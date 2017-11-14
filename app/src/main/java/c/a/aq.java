package c.a;

import c.a.bc.a;

/* TDeserializer */
public class aq {
    private final bg a;
    private final bt b;

    public aq() {
        this(new a());
    }

    public aq(bj bjVar) {
        this.b = new bt();
        this.a = bjVar.a(this.b);
    }

    public void a(an anVar, byte[] bArr) throws ar {
        try {
            this.b.a(bArr);
            anVar.a(this.a);
        } finally {
            this.b.a();
            this.a.x();
        }
    }
}
