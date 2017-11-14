package com.sds.android.ttpod.component.danmaku.c.b;

/* DanmakuTimer */
public class e {
    public long a;
    private long b;

    public long a(long j) {
        this.b = j - this.a;
        this.a = j;
        return this.b;
    }

    public long b(long j) {
        return a(this.a + j);
    }

    public String toString() {
        return "[" + this.a + ", " + this.b + "]";
    }
}
