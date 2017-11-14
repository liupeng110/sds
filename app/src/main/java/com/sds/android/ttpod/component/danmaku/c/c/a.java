package com.sds.android.ttpod.component.danmaku.c.c;

import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.danmaku.c.b.e;
import com.sds.android.ttpod.component.danmaku.c.b.k;
import com.sds.android.ttpod.component.danmaku.c.b.l;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* BaseDanmakuParser */
public abstract class a {
    protected c<?> a;
    protected e b;
    protected int c;
    protected int d;
    protected float e;
    protected float f;
    protected l g;
    private k h;
    private long i;
    private int j;

    protected abstract k f();

    public long a() {
        return this.i;
    }

    public void a(long j) {
        this.i = j;
    }

    public int b() {
        return this.j;
    }

    public void a(int i) {
        this.j = i;
    }

    public a a(l lVar) {
        this.g = lVar;
        this.c = lVar.c();
        this.d = lVar.d();
        this.e = lVar.e();
        this.f = lVar.g();
        b.a((float) this.c, (float) this.d, c());
        b.b();
        return this;
    }

    protected float c() {
        return 1.0f / (this.e - TTFMImageUtils.Large_Scale);
    }

    public a a(c<?> cVar) {
        this.a = cVar;
        return this;
    }

    public a a(e eVar) {
        this.b = eVar;
        return this;
    }

    public k d() {
        boolean z = true;
        String str = "BaseDanmakuParser";
        String str2 = "lookDanmaku getDanmakus mDanmakus!=null_%b";
        Object[] objArr = new Object[1];
        if (this.h == null) {
            z = false;
        }
        objArr[0] = Boolean.valueOf(z);
        g.d(str, str2, objArr);
        if (this.h != null) {
            return this.h;
        }
        b.a();
        this.h = f();
        e();
        b.b();
        return this.h;
    }

    protected void e() {
        if (this.a != null) {
            this.a.a();
        }
        this.a = null;
    }

    public void g() {
        e();
    }
}
