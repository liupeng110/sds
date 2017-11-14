package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.text.TextPaint;
import com.sds.android.ttpod.framework.modules.skin.e.l;
import com.sds.android.ttpod.framework.modules.skin.e.m;

/* LyricProvider */
public class g {
    private com.sds.android.ttpod.framework.modules.skin.e.g a;
    private com.sds.android.ttpod.framework.modules.skin.e.a b;
    private a c;
    private float d;
    private int e;
    private int f;
    private TextPaint g = new TextPaint();
    private l h = new l(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public int a(String str) {
            return (int) (this.a.g.measureText(str) + 0.96f);
        }

        public float a() {
            return this.a.d;
        }
    };

    /* LyricProvider */
    public interface a {
        void a(String str, String str2, boolean z);
    }

    public g(Context context, float f, int i) {
        this.d = f;
        this.e = i;
        this.g.setTextSize(this.d);
        this.f = -1;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public a a() {
        return this.c;
    }

    public void a(com.sds.android.ttpod.framework.modules.skin.e.g gVar) {
        this.a = gVar;
        b();
    }

    private void b() {
        this.b = null;
        if (this.a != null && this.e > 10 && this.d > 5.0f) {
            this.b = this.a.a(1, this.e, this.h);
        }
    }

    private void a(String str, String str2, float f, boolean z) {
        if (this.c != null) {
            this.c.a(str, str2, z);
        }
    }

    public void a(long j) {
        if (this.b != null && j >= 0) {
            int a = this.b.a(j);
            m a2 = this.b.a(a);
            String g = a2 == null ? "" : a2.g();
            if (this.f != a) {
                a(g, null, (float) j, true);
            } else {
                com.sds.android.sdk.lib.util.g.e("LyricProvider", "setPlayPos");
            }
            this.f = a;
        } else if (this.f != -1) {
            if (this.c != null) {
                this.c.a(null, null, true);
            }
            this.f = -1;
        }
    }
}
