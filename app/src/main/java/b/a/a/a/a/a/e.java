package b.a.a.a.a.a;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* StringBody */
public class e extends a {
    private final byte[] a;
    private final Charset b;

    public e(String str, String str2, Charset charset) throws UnsupportedEncodingException {
        super(str2);
        if (str == null) {
            throw new IllegalArgumentException("Text may not be null");
        }
        if (charset == null) {
            charset = Charset.forName("US-ASCII");
        }
        this.a = str.getBytes(charset.name());
        this.b = charset;
    }

    public e(String str, Charset charset) throws UnsupportedEncodingException {
        this(str, "text/plain", charset);
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(this.a);
        byte[] bArr = new byte[4096];
        while (true) {
            int read = byteArrayInputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    public String d() {
        return "8bit";
    }

    public String c() {
        return this.b.name();
    }

    public long e() {
        return (long) this.a.length;
    }

    public String b() {
        return null;
    }
}
