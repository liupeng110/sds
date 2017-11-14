package c.a;

/* TSet */
public final class bl {
    public final byte a;
    public final int b;

    public bl() {
        this((byte) 0, 0);
    }

    public bl(byte b, int i) {
        this.a = b;
        this.b = i;
    }

    public bl(be beVar) {
        this(beVar.a, beVar.b);
    }
}
