package b.a.a.a.a;

import com.sds.android.sdk.core.statistic.HttpClientProxy;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.util.ByteArrayBuffer;

/* HttpMultipart */
public class c {
    private static final ByteArrayBuffer a = a(e.a, ": ");
    private static final ByteArrayBuffer b = a(e.a, "\r\n");
    private static final ByteArrayBuffer c = a(e.a, "--");
    private final String d;
    private final Charset e;
    private final String f;
    private final List<a> g;
    private final d h;

    private static ByteArrayBuffer a(Charset charset, String str) {
        ByteBuffer encode = charset.encode(CharBuffer.wrap(str));
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(encode.remaining());
        byteArrayBuffer.append(encode.array(), encode.position(), encode.remaining());
        return byteArrayBuffer;
    }

    private static void a(ByteArrayBuffer byteArrayBuffer, OutputStream outputStream) throws IOException {
        outputStream.write(byteArrayBuffer.buffer(), 0, byteArrayBuffer.length());
    }

    private static void a(String str, Charset charset, OutputStream outputStream) throws IOException {
        a(a(charset, str), outputStream);
    }

    private static void a(String str, OutputStream outputStream) throws IOException {
        a(a(e.a, str), outputStream);
    }

    private static void a(f fVar, OutputStream outputStream) throws IOException {
        a(fVar.a(), outputStream);
        a(a, outputStream);
        a(fVar.b(), outputStream);
        a(b, outputStream);
    }

    private static void a(f fVar, Charset charset, OutputStream outputStream) throws IOException {
        a(fVar.a(), charset, outputStream);
        a(a, outputStream);
        a(fVar.b(), charset, outputStream);
        a(b, outputStream);
    }

    public c(String str, Charset charset, String str2, d dVar) {
        if (str == null) {
            throw new IllegalArgumentException("Multipart subtype may not be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("Multipart boundary may not be null");
        } else {
            this.d = str;
            if (charset == null) {
                charset = e.a;
            }
            this.e = charset;
            this.f = str2;
            this.g = new ArrayList();
            this.h = dVar;
        }
    }

    public List<a> a() {
        return this.g;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.g.add(aVar);
        }
    }

    public String b() {
        return this.f;
    }

    private void a(d dVar, OutputStream outputStream, boolean z) throws IOException {
        ByteArrayBuffer a = a(this.e, b());
        for (a aVar : this.g) {
            a(c, outputStream);
            a(a, outputStream);
            a(b, outputStream);
            b c = aVar.c();
            switch (dVar) {
                case STRICT:
                    Iterator it = c.iterator();
                    while (it.hasNext()) {
                        a((f) it.next(), outputStream);
                    }
                    break;
                case BROWSER_COMPATIBLE:
                    a(aVar.c().a("Content-Disposition"), this.e, outputStream);
                    if (aVar.b().b() != null) {
                        a(aVar.c().a(HttpClientProxy.HEADER_CONTENT_TYPE), this.e, outputStream);
                        break;
                    }
                    break;
            }
            a(b, outputStream);
            if (z) {
                aVar.b().a(outputStream);
            }
            a(b, outputStream);
        }
        a(c, outputStream);
        a(a, outputStream);
        a(c, outputStream);
        a(b, outputStream);
    }

    public void a(OutputStream outputStream) throws IOException {
        a(this.h, outputStream, true);
    }

    public long c() {
        long j = 0;
        for (a b : this.g) {
            long e = b.b().e();
            if (e < 0) {
                return -1;
            }
            j = e + j;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(this.h, byteArrayOutputStream, false);
            return ((long) byteArrayOutputStream.toByteArray().length) + j;
        } catch (IOException e2) {
            return -1;
        }
    }
}
