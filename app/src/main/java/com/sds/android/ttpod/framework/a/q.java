package com.sds.android.ttpod.framework.a;

import java.util.ArrayList;
import java.util.List;

/* Pager */
public class q {
    private int a = 1;
    private int b = this.a;
    private int c = 1;
    private boolean d = true;

    public void a(boolean z) {
        this.d = z;
    }

    public void a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("startIndex must >= 0");
        }
        this.a = i;
    }

    public void b(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("total must >= 1");
        }
        this.c = i;
    }

    public void c(int i) {
        if (i < this.a || i > g()) {
            throw new IllegalArgumentException("currentIndex must be >= PageStart and <= pageEnd");
        }
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.a;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        this.d = false;
        return this.b + 1;
    }

    public void e() {
        this.b++;
        this.d = false;
    }

    public boolean d(int i) {
        return i > g();
    }

    public boolean f() {
        return this.d && this.b == this.a;
    }

    public int g() {
        return (this.a + this.c) - 1;
    }

    public boolean h() {
        return this.b < this.c;
    }

    public static <Data> List<List<Data>> a(List<Data> list, int i) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("invalid datas");
        } else if (i <= 0) {
            throw new IllegalArgumentException("pageSize must be > 0");
        } else {
            int size = list.size();
            int ceil = (int) Math.ceil(((double) size) / ((double) i));
            List<List<Data>> arrayList = new ArrayList();
            for (int i2 = 0; i2 < ceil - 1; i2++) {
                arrayList.add(list.subList(i2 * i, (i2 + 1) * i));
            }
            arrayList.add(list.subList((ceil - 1) * i, size));
            return arrayList;
        }
    }
}
