package com.sds.android.ttpod.component.danmaku.c.b;

/* Duration */
public class f {
    public long a;
    private long b;
    private float c = 1.0f;

    public f(long j) {
        this.b = j;
        this.a = j;
    }

    public void a(long j) {
        this.b = j;
        this.a = (long) (((float) this.b) * this.c);
    }

    public void a(float f) {
        if (this.c != f) {
            this.c = f;
            this.a = (long) (((float) this.b) * f);
        }
    }
}
