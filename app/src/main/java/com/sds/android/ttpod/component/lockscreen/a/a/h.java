package com.sds.android.ttpod.component.lockscreen.a.a;

import android.view.View;
import com.sds.android.ttpod.component.lockscreen.a.b.c;
import com.sds.android.ttpod.component.lockscreen.a.c.a.a;
import java.util.HashMap;
import java.util.Map;

/* ObjectAnimator */
public final class h extends l {
    private static final Map<String, c> h = new HashMap();
    private Object i;
    private String j;
    private c k;

    public /* synthetic */ l b(long j) {
        return a(j);
    }

    public /* synthetic */ a c() {
        return e();
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public /* synthetic */ l f() {
        return e();
    }

    static {
        h.put("alpha", i.a);
        h.put("pivotX", i.b);
        h.put("pivotY", i.c);
        h.put("translationX", i.d);
        h.put("translationY", i.e);
        h.put("rotation", i.f);
        h.put("rotationX", i.g);
        h.put("rotationY", i.h);
        h.put("scaleX", i.i);
        h.put("scaleY", i.j);
        h.put("scrollX", i.k);
        h.put("scrollY", i.l);
        h.put("x", i.m);
        h.put("y", i.n);
    }

    public void a(String str) {
        if (this.f != null) {
            j jVar = this.f[0];
            String c = jVar.c();
            jVar.a(str);
            this.g.remove(c);
            this.g.put(str, jVar);
        }
        this.j = str;
        this.e = false;
    }

    public void a(c cVar) {
        if (this.f != null) {
            j jVar = this.f[0];
            String c = jVar.c();
            jVar.a(cVar);
            this.g.remove(c);
            this.g.put(this.j, jVar);
        }
        if (this.k != null) {
            this.j = cVar.a();
        }
        this.k = cVar;
        this.e = false;
    }

    private h(Object obj, String str) {
        this.i = obj;
        a(str);
    }

    public static h a(Object obj, String str, int... iArr) {
        h hVar = new h(obj, str);
        hVar.a(iArr);
        return hVar;
    }

    public static h a(Object obj, String str, float... fArr) {
        h hVar = new h(obj, str);
        hVar.a(fArr);
        return hVar;
    }

    public void a(int... iArr) {
        if (this.f != null && this.f.length != 0) {
            super.a(iArr);
        } else if (this.k != null) {
            a(j.a(this.k, iArr));
        } else {
            a(j.a(this.j, iArr));
        }
    }

    public void a(float... fArr) {
        if (this.f != null && this.f.length != 0) {
            super.a(fArr);
        } else if (this.k != null) {
            a(j.a(this.k, fArr));
        } else {
            a(j.a(this.j, fArr));
        }
    }

    public void a() {
        super.a();
    }

    void d() {
        if (!this.e) {
            if (this.k == null && a.a && (this.i instanceof View) && h.containsKey(this.j)) {
                a((c) h.get(this.j));
            }
            for (j a : this.f) {
                a.a(this.i);
            }
            super.d();
        }
    }

    public h a(long j) {
        super.b(j);
        return this;
    }

    void a(float f) {
        super.a(f);
        for (j b : this.f) {
            b.b(this.i);
        }
    }

    public h e() {
        return (h) super.f();
    }

    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.i;
        if (this.f != null) {
            j[] jVarArr = this.f;
            int length = jVarArr.length;
            int i = 0;
            while (i < length) {
                i++;
                str = str + "\n    " + jVarArr[i].toString();
            }
        }
        return str;
    }
}
