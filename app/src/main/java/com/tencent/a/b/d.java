package com.tencent.a.b;

import android.os.StatFs;
import java.io.File;

/* ProGuard */
public class d {
    private File a;
    private long b;
    private long c;

    public File a() {
        return this.a;
    }

    public void a(File file) {
        this.a = file;
    }

    public long b() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public long c() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public static d b(File file) {
        d dVar = new d();
        dVar.a(file);
        StatFs statFs = new StatFs(file.getAbsolutePath());
        long blockSize = (long) statFs.getBlockSize();
        long availableBlocks = (long) statFs.getAvailableBlocks();
        dVar.a(((long) statFs.getBlockCount()) * blockSize);
        dVar.b(blockSize * availableBlocks);
        return dVar;
    }

    public String toString() {
        return String.format("[%s : %d / %d]", new Object[]{a().getAbsolutePath(), Long.valueOf(c()), Long.valueOf(b())});
    }
}
