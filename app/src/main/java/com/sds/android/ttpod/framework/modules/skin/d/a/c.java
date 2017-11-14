package com.sds.android.ttpod.framework.modules.skin.d.a;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* RandomAccessFileInputStream */
public class c extends InputStream implements Closeable {
    private RandomAccessFile a;
    private long b;

    public c(File file, String str) throws FileNotFoundException {
        this.b = 0;
        this.a = new RandomAccessFile(file, str);
    }

    public c(String str, String str2) throws FileNotFoundException {
        this(new File(str), str2);
    }

    public void close() throws IOException {
        this.a.close();
    }

    public long skip(long j) throws IOException {
        return (long) this.a.skipBytes((int) j);
    }

    public int read() throws IOException {
        return this.a.read();
    }

    public int read(byte[] bArr) throws IOException {
        return this.a.read(bArr);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.a.read(bArr, i, i2);
    }

    public int available() throws IOException {
        return (int) (this.a.length() - this.a.getFilePointer());
    }

    public synchronized void mark(int i) {
        try {
            this.b = this.a.getFilePointer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void reset() throws IOException {
        this.a.seek(this.b);
    }

    public boolean markSupported() {
        return true;
    }
}
