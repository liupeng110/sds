package b.a.a.a.a;

import b.a.a.a.a.a.b;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

/* MultipartEntity */
public class g implements HttpEntity {
    private static final char[] a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final c b;
    private final Header c;
    private long d;
    private volatile boolean e;

    public g(d dVar, String str, Charset charset) {
        if (str == null) {
            str = a();
        }
        if (dVar == null) {
            dVar = d.STRICT;
        }
        this.b = new c("form-data", charset, str, dVar);
        this.c = new BasicHeader(HttpClientProxy.HEADER_CONTENT_TYPE, a(str, charset));
        this.e = true;
    }

    public g() {
        this(d.STRICT, null, null);
    }

    protected String a(String str, Charset charset) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("multipart/form-data; boundary=");
        stringBuilder.append(str);
        if (charset != null) {
            stringBuilder.append("; charset=");
            stringBuilder.append(charset.name());
        }
        return stringBuilder.toString();
    }

    protected String a() {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int nextInt = random.nextInt(11) + 30;
        for (int i = 0; i < nextInt; i++) {
            stringBuilder.append(a[random.nextInt(a.length)]);
        }
        return stringBuilder.toString();
    }

    public void a(a aVar) {
        this.b.a(aVar);
        this.e = true;
    }

    public void a(String str, b bVar) {
        a(new a(str, bVar));
    }

    public boolean isRepeatable() {
        for (a b : this.b.a()) {
            if (b.b().e() < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isChunked() {
        return !isRepeatable();
    }

    public boolean isStreaming() {
        return !isRepeatable();
    }

    public long getContentLength() {
        if (this.e) {
            this.d = this.b.c();
            this.e = false;
        }
        return this.d;
    }

    public Header getContentType() {
        return this.c;
    }

    public Header getContentEncoding() {
        return null;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Multipart form entity does not implement #getContent()");
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.b.a(outputStream);
    }
}
