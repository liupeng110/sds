package b.a.a.a.a.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* FileBody */
public class d extends a {
    private final File a;
    private final String b;
    private final String c;

    public d(File file, String str, String str2, String str3) {
        super(str2);
        if (file == null) {
            throw new IllegalArgumentException("File may not be null");
        }
        this.a = file;
        if (str != null) {
            this.b = str;
        } else {
            this.b = file.getName();
        }
        this.c = str3;
    }

    public d(File file, String str, String str2) {
        this(file, null, str, str2);
    }

    public d(File file, String str) {
        this(file, str, null);
    }

    public d(File file) {
        this(file, "application/octet-stream");
    }

    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        InputStream fileInputStream = new FileInputStream(this.a);
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
        } finally {
            fileInputStream.close();
        }
    }

    public String d() {
        return "binary";
    }

    public String c() {
        return this.c;
    }

    public long e() {
        return this.a.length();
    }

    public String b() {
        return this.b;
    }
}
