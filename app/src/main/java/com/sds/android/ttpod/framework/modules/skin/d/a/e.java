package com.sds.android.ttpod.framework.modules.skin.d.a;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* ZipPackHandle */
public class e extends a {
    private String a;
    private long b;
    private ZipFile c;
    private HashMap<String, ZipEntry> d = new HashMap();

    public e(String str) throws IOException {
        a(str);
    }

    public void close() throws IOException {
        if (this.c != null) {
            this.c.close();
            this.c = null;
        }
    }

    public void a(String str) throws IOException {
        File file = new File(str);
        this.c = new ZipFile(file);
        long lastModified = file.lastModified();
        if (!(!this.d.isEmpty() && TextUtils.equals(this.a, str) && lastModified == this.b)) {
            b();
        }
        this.a = str;
        this.b = lastModified;
    }

    public void a(InputStream inputStream, boolean z) throws IOException {
        throw new UnsupportedOperationException("zip pack cannot open in input stream");
    }

    public boolean a() {
        return this.c != null;
    }

    private void b() {
        if (a()) {
            this.d.clear();
            Enumeration entries = this.c.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) entries.nextElement();
                if (!zipEntry.isDirectory()) {
                    this.d.put("/" + zipEntry.getName(), zipEntry);
                }
            }
        }
    }

    public Iterator<String> iterator() {
        return this.d.keySet().iterator();
    }

    public byte[] b(String str) throws IOException {
        byte[] bArr = null;
        ZipEntry c = c(str);
        if (c != null) {
            int size = (int) c.getSize();
            if (size > 0) {
                bArr = new byte[size];
                InputStream inputStream = this.c.getInputStream(c);
                int i = 0;
                do {
                    try {
                        int read = inputStream.read(bArr, i, bArr.length - i);
                        i += read;
                        if (read >= bArr.length) {
                            break;
                        }
                    } catch (Throwable th) {
                        inputStream.close();
                    }
                } while (inputStream.available() > 0);
                inputStream.close();
            }
        }
        return bArr;
    }

    private ZipEntry c(String str) {
        return (ZipEntry) this.d.get(str.toLowerCase(Locale.US));
    }
}
