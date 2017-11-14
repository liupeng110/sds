package com.sds.android.ttpod.component.danmaku.c.b;

/* BaseDanmaku */
public abstract class c {
    public long a;
    public String b;
    public String[] c;
    public int d;
    public float e;
    public float f;
    public int g;
    public int h = 0;
    public float i = -1.0f;
    public int j = 0;
    public int k = 0;
    public byte l = (byte) 0;
    public float m = -1.0f;
    public float n = -1.0f;
    public f o;
    public int p;
    public int q;
    public m<?> r;
    public boolean s;
    public long t = 0;
    public String u;
    public boolean v;
    protected e w;
    protected int x = b.a;
    private int y = 0;
    private int z = 0;

    public abstract void a(l lVar, float f, float f2);

    public abstract float[] a(l lVar, long j);

    public abstract float h();

    public abstract float i();

    public abstract float j();

    public abstract float k();

    public abstract int l();

    public long a() {
        return this.o.a;
    }

    public int a(l lVar) {
        return lVar.a(this);
    }

    public boolean b() {
        return this.m >= 0.0f && this.n >= 0.0f && this.z == i.a;
    }

    public void b(l lVar) {
        lVar.b(this);
        this.z = i.a;
    }

    public boolean c() {
        return (this.r == null || this.r.a() == null) ? false : true;
    }

    public boolean d() {
        return this.q == 1 && this.y == i.b;
    }

    public boolean e() {
        return this.w == null || a(this.w.a);
    }

    public boolean a(long j) {
        return j - this.a >= this.o.a;
    }

    public boolean f() {
        return this.w == null || b(this.w.a);
    }

    public boolean b(long j) {
        long j2 = j - this.a;
        return j2 <= 0 || j2 >= this.o.a;
    }

    public boolean g() {
        return this.w == null || this.w.a < this.a;
    }

    public void a(boolean z) {
        if (z) {
            this.y = i.b;
            this.q = 1;
            return;
        }
        this.q = 0;
    }

    public e m() {
        return this.w;
    }

    public void a(e eVar) {
        this.w = eVar;
    }

    public int n() {
        return this.x;
    }
}
