package com.sds.android.ttpod.component.landscape;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.sds.android.ttpod.component.landscape.a.p;
import com.sds.android.ttpod.component.landscape.b.i;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* LandscapeGestureHelper */
public class c implements OnClickListener, OnLongClickListener, com.sds.android.ttpod.component.landscape.GestureView.a, com.sds.android.ttpod.component.landscape.GestureView.b, com.sds.android.ttpod.component.landscape.GestureView.c, com.sds.android.ttpod.component.landscape.GestureView.d, com.sds.android.ttpod.component.landscape.i.a {
    private float a = 0.0f;
    private float b = 0.0f;
    private boolean c = false;
    private float d = 0.0f;
    private float e = 0.0f;
    private boolean f = false;
    private boolean g;
    private boolean h;
    private boolean i;

    /* LandscapeGestureHelper */
    public interface c {
        void d_();
    }

    /* LandscapeGestureHelper */
    public interface d {
        i[] c_();
    }

    /* LandscapeGestureHelper */
    public interface e {
        i[] c();
    }

    /* LandscapeGestureHelper */
    public interface a {
        void a();
    }

    /* LandscapeGestureHelper */
    public interface b {
        void a();
    }

    public c() {
        i.a().a(this, 15);
    }

    private void a(i[] iVarArr) {
        for (int i = 0; i < iVarArr.length; i++) {
            float j;
            i iVar = iVarArr[i];
            float b = iVar.m().b();
            switch (i) {
                case 0:
                    float j2 = b.j() * 0.4f;
                    j = b.j() * -0.5f;
                    if (b <= j2) {
                        j2 = 10000.0f;
                    }
                    if (b >= j) {
                        j = j2;
                        break;
                    }
                    break;
                default:
                    j = 10000.0f;
                    break;
            }
            if (j != 10000.0f) {
                iVar.b(new p(0.4f, j));
            }
        }
    }

    private void a(i[] iVarArr, float f) {
        for (int i = 0; i < iVarArr.length; i++) {
            i iVar = iVarArr[i];
            float b = iVar.m().b();
            switch (i) {
                case 0:
                    float f2 = b + f;
                    float j = TTFMImageUtils.Middle_Scale * b.j();
                    b = b.j() * -1.0f;
                    if (f2 <= j) {
                        j = f2;
                    }
                    if (j < b) {
                        break;
                    }
                    b = j;
                    break;
                default:
                    break;
            }
            iVar.m().a(b);
        }
    }

    /* JADX err: Inconsistent code. */
    private void b(com.sds.android.ttpod.component.landscape.b.i[] r9) {
        /*
        r8 = this;
        r4 = 1176256512; // 0x461c4000 float:10000.0 double:5.811479333E-315;
        r3 = 1101004800; // 0x41a00000 float:20.0 double:5.439686476E-315;
        r2 = 1092616192; // 0x41200000 float:10.0 double:5.398241246E-315;
        r0 = 0;
    L_0x0008:
        r1 = r9.length;
        if (r0 >= r1) goto L_0x0036;
    L_0x000b:
        r5 = r9[r0];
        r1 = r5.m();
        r1 = r1.c();
        switch(r0) {
            case 0: goto L_0x002a;
            default: goto L_0x0018;
        };
    L_0x0018:
        r1 = r4;
    L_0x0019:
        r6 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1));
        if (r6 == 0) goto L_0x0027;
    L_0x001d:
        r6 = new com.sds.android.ttpod.component.landscape.a.m;
        r7 = 1048576000; // 0x3e800000 float:0.25 double:5.180653787E-315;
        r6.<init>(r7, r1);
        r5.b(r6);
    L_0x0027:
        r0 = r0 + 1;
        goto L_0x0008;
    L_0x002a:
        r6 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1));
        if (r6 >= 0) goto L_0x0030;
    L_0x002e:
        r1 = r2;
        goto L_0x0019;
    L_0x0030:
        r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r1 <= 0) goto L_0x0018;
    L_0x0034:
        r1 = r3;
        goto L_0x0019;
    L_0x0036:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.landscape.c.b(com.sds.android.ttpod.component.landscape.b.i[]):void");
    }

    private void b(i[] iVarArr, float f) {
        for (int i = 0; i < iVarArr.length; i++) {
            i iVar = iVarArr[i];
            float c = iVar.m().c();
            switch (i) {
                case 0:
                    c += 0.2f * f;
                    if (c < 0.0f) {
                        c = 0.0f;
                    }
                    if (c <= 30.0f) {
                        break;
                    }
                    c = 30.0f;
                    break;
                default:
                    break;
            }
            iVar.m().b(c);
        }
    }

    public void onClick(View view) {
        synchronized (this) {
            this.h = true;
        }
    }

    public boolean onLongClick(View view) {
        synchronized (this) {
            this.i = true;
        }
        return true;
    }

    public void a(float f, float f2, float f3, float f4) {
        synchronized (this) {
            this.a = (f2 + f4) / 2.0f;
        }
    }

    public void a(double d) {
    }

    public void b(double d) {
        synchronized (this) {
            this.d = (float) d;
        }
    }

    /* JADX err: Inconsistent code. */
    public void a(int r2) {
        /*
        r1 = this;
        monitor-enter(r1);
        switch(r2) {
            case 30: goto L_0x0006;
            case 31: goto L_0x0004;
            case 32: goto L_0x0016;
            case 33: goto L_0x0020;
            default: goto L_0x0004;
        };
    L_0x0004:
        monitor-exit(r1);	 Catch:{ all -> 0x0013 }
        return;
    L_0x0006:
        r0 = 0;
        r1.a = r0;	 Catch:{ all -> 0x0013 }
        r0 = 0;
        r1.b = r0;	 Catch:{ all -> 0x0013 }
        r0 = 0;
        r1.d = r0;	 Catch:{ all -> 0x0013 }
        r0 = 0;
        r1.e = r0;	 Catch:{ all -> 0x0013 }
        goto L_0x0004;
    L_0x0013:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0013 }
        throw r0;
    L_0x0016:
        r0 = 1;
        r1.c = r0;	 Catch:{ all -> 0x0013 }
        r0 = 1;
        r1.f = r0;	 Catch:{ all -> 0x0013 }
        r0 = 1;
        r1.g = r0;	 Catch:{ all -> 0x0013 }
        goto L_0x0004;
    L_0x0020:
        r0 = 0;
        r1.c = r0;	 Catch:{ all -> 0x0013 }
        r0 = 0;
        r1.f = r0;	 Catch:{ all -> 0x0013 }
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.landscape.c.a(int):void");
    }

    public void a(float f) {
        synchronized (this) {
            float f2;
            i b = h.a().b();
            if (b != null && (b instanceof d)) {
                f2 = this.a;
                if (f2 != this.b) {
                    b(((d) b).c_(), f2 - this.b);
                    this.b = f2;
                } else if (this.c) {
                    b(((d) b).c_());
                    this.c = false;
                }
            }
            if (b != null && (b instanceof e)) {
                f2 = this.d;
                if (f2 != this.e) {
                    a(((e) b).c(), f2 - this.e);
                    this.e = f2;
                } else if (this.f) {
                    a(((e) b).c());
                    this.f = false;
                }
            }
            if (b != null && (b instanceof c) && this.i) {
                ((c) b).d_();
                this.i = false;
            }
            if (b != null && (b instanceof b) && this.h) {
                ((b) b).a();
                this.h = false;
            }
            if (b != null && (b instanceof a) && this.g) {
                ((a) b).a();
                this.g = false;
            }
        }
    }
}
