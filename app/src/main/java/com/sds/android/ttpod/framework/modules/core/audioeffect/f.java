package com.sds.android.ttpod.framework.modules.core.audioeffect;

import com.sds.android.ttpod.media.mediastore.MediaItem;

/* PrivateEffectItem */
public class f {
    private long a = 0;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private MediaItem g;
    private long h;
    private short[] i = new short[]{(short) 10};
    private int j = 0;
    private int k = 0;
    private int l = 0;

    public f(String str, a aVar) {
        this.a = aVar.b().longValue();
        this.b = aVar.d() + " - " + aVar.c();
        this.c = aVar.g();
        this.d = e.a(aVar.e());
        this.e = str;
        this.f = aVar.p();
        this.i = aVar.n();
        this.j = aVar.h();
        this.k = aVar.i();
        this.l = aVar.j();
    }

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public long e() {
        return this.a;
    }

    public MediaItem f() {
        return this.g;
    }

    public void a(MediaItem mediaItem) {
        this.g = mediaItem;
    }

    public long g() {
        return this.h;
    }

    public void a(long j) {
        this.h = j;
    }

    public short[] h() {
        return this.i;
    }

    public int i() {
        return this.j;
    }

    public int j() {
        return this.k;
    }

    public int k() {
        return this.l;
    }
}
