package c.a;

/* TField */
public class bd {
    public final String a;
    public final byte b;
    public final short c;

    public bd() {
        this("", (byte) 0, (short) 0);
    }

    public bd(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
    }
}
