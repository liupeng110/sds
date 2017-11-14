package com.sds.android.ttpod.framework.modules.skin.e;

/* LrcSentence */
public class f implements m {
    protected int a;
    protected String b;
    private long c;
    private int d;
    private int e;
    private i f;

    public /* synthetic */ int compareTo(Object obj) {
        return a((m) obj);
    }

    public f(f fVar) {
        this.c = fVar.c;
        this.a = fVar.a;
        this.b = fVar.b;
        this.d = fVar.d;
        this.e = fVar.e;
    }

    public void a(i iVar) {
        this.f = iVar;
    }

    public f(long j, String str, int i, int i2, int i3) {
        this.c = j;
        if (i < 1) {
            i = 1;
        }
        this.a = i;
        this.b = str;
        this.d = i2;
        this.e = i3;
    }

    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int b() {
        return this.d;
    }

    protected String c() {
        return this.b;
    }

    public String toString() {
        long abs = (Math.abs(this.c) / 1000) / 60;
        String str = this.c < 0 ? "-" : "";
        return String.format("[%s%02d:%02d.%03d]%s\n", new Object[]{str, Long.valueOf(abs), Long.valueOf(r4 - (abs * 60)), Long.valueOf(r2 - (r4 * 1000)), c()});
    }

    public void a(long j) {
        this.c = j;
    }

    public void b(int i) {
        if (i < 1) {
            i = 1;
        }
        this.a = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public long d() {
        return this.c;
    }

    public long e() {
        return (this.f != null ? this.f.d() : 0) + this.c;
    }

    public int f() {
        return this.a;
    }

    public String g() {
        return this.b;
    }

    public int a(m mVar) {
        return (int) (e() - mVar.e());
    }

    public int c(int i) {
        return k.a(this.b) ? 0 : (this.e * i) / this.a;
    }
}
