package c.a;

import java.io.Serializable;

/* FieldValueMetaData */
public class ax implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public ax(byte b, boolean z) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public ax(byte b) {
        this(b, false);
    }
}
