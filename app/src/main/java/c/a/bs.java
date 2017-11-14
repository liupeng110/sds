package c.a;

import java.io.InputStream;
import java.io.OutputStream;

/* TIOStreamTransport */
public class bs extends bu {
    protected InputStream a = null;
    protected OutputStream b = null;

    protected bs() {
    }

    public bs(OutputStream outputStream) {
        this.b = outputStream;
    }

    public int a(byte[] bArr, int i, int i2) throws bv {
        if (this.a == null) {
            throw new bv(1, "Cannot read from null inputStream");
        }
        try {
            int read = this.a.read(bArr, i, i2);
            if (read >= 0) {
                return read;
            }
            throw new bv(4);
        } catch (Throwable e) {
            throw new bv(0, e);
        }
    }

    public void b(byte[] bArr, int i, int i2) throws bv {
        if (this.b == null) {
            throw new bv(1, "Cannot write to null outputStream");
        }
        try {
            this.b.write(bArr, i, i2);
        } catch (Throwable e) {
            throw new bv(0, e);
        }
    }
}
