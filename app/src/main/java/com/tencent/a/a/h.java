package com.tencent.a.a;

import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.tencent.a.c.a;
import com.tencent.a.c.b;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

/* ProGuard */
public class h {
    private static SimpleDateFormat a = b.a("yyyy-MM-dd");
    private static FileFilter b = new i();
    private String c = "Tracer.File";
    private int d = Entry.MAX_LIMIT;
    private int e = Entry.MAX_LIMIT;
    private int f = 4096;
    private long g = 10000;
    private File h;
    private int i = 10;
    private String j = ".log";
    private long k = Long.MAX_VALUE;
    private FileFilter l = new j(this);
    private Comparator<? super File> m = new k(this);

    public static long a(File file) {
        try {
            return a.parse(file.getName()).getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    public h(File file, int i, int i2, int i3, String str, long j, int i4, String str2, long j2) {
        c(file);
        b(i);
        a(i2);
        c(i3);
        a(str);
        b(j);
        d(i4);
        b(str2);
        c(j2);
    }

    public File a() {
        return d(System.currentTimeMillis());
    }

    private File d(long j) {
        return e(a(j));
    }

    public File a(long j) {
        File file = new File(h(), a.format(Long.valueOf(j)));
        file.mkdirs();
        return file;
    }

    private File e(File file) {
        File[] b = b(file);
        if (b == null || b.length == 0) {
            return new File(file, "1" + j());
        }
        a(b);
        File file2 = b[b.length - 1];
        int length = b.length - e();
        if (((int) file2.length()) > d()) {
            file2 = new File(file, (f(file2) + 1) + j());
            length++;
        }
        for (int i = 0; i < length; i++) {
            b[i].delete();
        }
        return file2;
    }

    public File[] b(File file) {
        return file.listFiles(this.l);
    }

    public void b() {
        if (h() != null) {
            File[] listFiles = h().listFiles(b);
            if (listFiles != null) {
                for (File file : listFiles) {
                    if (System.currentTimeMillis() - a(file) > k()) {
                        a.a(file);
                    }
                }
            }
        }
    }

    public File[] a(File[] fileArr) {
        Arrays.sort(fileArr, this.m);
        return fileArr;
    }

    private static int f(File file) {
        try {
            String name = file.getName();
            return Integer.parseInt(name.substring(0, name.indexOf(46)));
        } catch (Exception e) {
            return -1;
        }
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public int d() {
        return this.d;
    }

    public void a(int i) {
        this.d = i;
    }

    public int e() {
        return this.e;
    }

    public void b(int i) {
        this.e = i;
    }

    public int f() {
        return this.f;
    }

    public void c(int i) {
        this.f = i;
    }

    public long g() {
        return this.g;
    }

    public void b(long j) {
        this.g = j;
    }

    public File h() {
        return this.h;
    }

    public void c(File file) {
        this.h = file;
    }

    public int i() {
        return this.i;
    }

    public void d(int i) {
        this.i = i;
    }

    public String j() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public long k() {
        return this.k;
    }

    public void c(long j) {
        this.k = j;
    }
}
