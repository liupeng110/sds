package com.sds.android.ttpod.component.scaleimage;

import android.content.Context;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* ImageScaleGestureDetector */
public class b {
    private final Context a;
    private final a b;
    private boolean c;
    private MotionEvent d;
    private MotionEvent e;
    private float f;
    private float g;
    private float h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private float n;
    private float o;
    private float p;
    private long q;
    private final float r;
    private float s;
    private float t;
    private boolean u;

    /* ImageScaleGestureDetector */
    public interface a {
        boolean a(b bVar);

        boolean a(b bVar, float f, float f2);

        void b(b bVar);
    }

    /* ImageScaleGestureDetector */
    public static class b implements a {
        public boolean a(b bVar, float f, float f2) {
            return false;
        }

        public boolean a(b bVar) {
            return true;
        }

        public void b(b bVar) {
        }
    }

    public b(Context context, a aVar) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.a = context;
        this.b = aVar;
        this.r = (float) viewConfiguration.getScaledEdgeSlop();
    }

    /* JADX err: Inconsistent code. */
    public boolean a(android.view.MotionEvent r9) {
        /*
        r8 = this;
        r3 = 2;
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = 1;
        r0 = r9.getAction();
        r1 = r8.c;	 Catch:{ Exception -> 0x0072 }
        if (r1 != 0) goto L_0x00bd;
    L_0x000c:
        r0 = r0 & 255;
        switch(r0) {
            case 2: goto L_0x0080;
            case 3: goto L_0x0011;
            case 4: goto L_0x0011;
            case 5: goto L_0x0012;
            default: goto L_0x0011;
        };	 Catch:{ Exception -> 0x0072 }
    L_0x0011:
        return r7;
    L_0x0012:
        r0 = r9.getPointerCount();	 Catch:{ Exception -> 0x0072 }
        if (r0 < r3) goto L_0x0011;
    L_0x0018:
        r0 = r8.a;	 Catch:{ Exception -> 0x0072 }
        r0 = r0.getResources();	 Catch:{ Exception -> 0x0072 }
        r0 = r0.getDisplayMetrics();	 Catch:{ Exception -> 0x0072 }
        r1 = r0.widthPixels;	 Catch:{ Exception -> 0x0072 }
        r1 = (float) r1;	 Catch:{ Exception -> 0x0072 }
        r2 = r8.r;	 Catch:{ Exception -> 0x0072 }
        r1 = r1 - r2;
        r8.s = r1;	 Catch:{ Exception -> 0x0072 }
        r0 = r0.heightPixels;	 Catch:{ Exception -> 0x0072 }
        r0 = (float) r0;	 Catch:{ Exception -> 0x0072 }
        r1 = r8.r;	 Catch:{ Exception -> 0x0072 }
        r0 = r0 - r1;
        r8.t = r0;	 Catch:{ Exception -> 0x0072 }
        r8.c();	 Catch:{ Exception -> 0x0072 }
        r0 = android.view.MotionEvent.obtain(r9);	 Catch:{ Exception -> 0x0072 }
        r8.d = r0;	 Catch:{ Exception -> 0x0072 }
        r0 = 0;
        r8.q = r0;	 Catch:{ Exception -> 0x0072 }
        r8.b(r9);	 Catch:{ Exception -> 0x0072 }
        r1 = r9.getRawX();	 Catch:{ Exception -> 0x0072 }
        r2 = r9.getRawY();	 Catch:{ Exception -> 0x0072 }
        r3 = r8.r;	 Catch:{ Exception -> 0x0072 }
        r4 = r8.s;	 Catch:{ Exception -> 0x0072 }
        r5 = r8.t;	 Catch:{ Exception -> 0x0072 }
        r0 = r8;
        r6 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0072 }
        r0 = 1;
        r1 = a(r9, r0);	 Catch:{ Exception -> 0x0072 }
        r0 = 1;
        r2 = b(r9, r0);	 Catch:{ Exception -> 0x0072 }
        r3 = r8.r;	 Catch:{ Exception -> 0x0072 }
        r4 = r8.s;	 Catch:{ Exception -> 0x0072 }
        r5 = r8.t;	 Catch:{ Exception -> 0x0072 }
        r0 = r8;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0072 }
        if (r6 != 0) goto L_0x006e;
    L_0x006c:
        if (r0 == 0) goto L_0x0077;
    L_0x006e:
        r0 = 1;
        r8.u = r0;	 Catch:{ Exception -> 0x0072 }
        goto L_0x0011;
    L_0x0072:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0011;
    L_0x0077:
        r0 = r8.b;	 Catch:{ Exception -> 0x0072 }
        r0 = r0.a(r8);	 Catch:{ Exception -> 0x0072 }
        r8.c = r0;	 Catch:{ Exception -> 0x0072 }
        goto L_0x0011;
    L_0x0080:
        r0 = r8.u;	 Catch:{ Exception -> 0x0072 }
        if (r0 == 0) goto L_0x0011;
    L_0x0084:
        r1 = r9.getRawX();	 Catch:{ Exception -> 0x0072 }
        r2 = r9.getRawY();	 Catch:{ Exception -> 0x0072 }
        r3 = r8.r;	 Catch:{ Exception -> 0x0072 }
        r4 = r8.s;	 Catch:{ Exception -> 0x0072 }
        r5 = r8.t;	 Catch:{ Exception -> 0x0072 }
        r0 = r8;
        r6 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0072 }
        r0 = 1;
        r1 = a(r9, r0);	 Catch:{ Exception -> 0x0072 }
        r0 = 1;
        r2 = b(r9, r0);	 Catch:{ Exception -> 0x0072 }
        r3 = r8.r;	 Catch:{ Exception -> 0x0072 }
        r4 = r8.s;	 Catch:{ Exception -> 0x0072 }
        r5 = r8.t;	 Catch:{ Exception -> 0x0072 }
        r0 = r8;
        r0 = r0.a(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0072 }
        if (r6 != 0) goto L_0x0011;
    L_0x00ae:
        if (r0 != 0) goto L_0x0011;
    L_0x00b0:
        r0 = 0;
        r8.u = r0;	 Catch:{ Exception -> 0x0072 }
        r0 = r8.b;	 Catch:{ Exception -> 0x0072 }
        r0 = r0.a(r8);	 Catch:{ Exception -> 0x0072 }
        r8.c = r0;	 Catch:{ Exception -> 0x0072 }
        goto L_0x0011;
    L_0x00bd:
        r0 = r0 & 255;
        switch(r0) {
            case 2: goto L_0x00c4;
            case 3: goto L_0x011f;
            case 4: goto L_0x00c2;
            case 5: goto L_0x0011;
            case 6: goto L_0x010e;
            default: goto L_0x00c2;
        };	 Catch:{ Exception -> 0x0072 }
    L_0x00c2:
        goto L_0x0011;
    L_0x00c4:
        r0 = r9.getPointerCount();	 Catch:{ Exception -> 0x0072 }
        if (r0 < r3) goto L_0x0011;
    L_0x00ca:
        r8.b(r9);	 Catch:{ Exception -> 0x0072 }
        r0 = 0;
        r0 = r9.getX(r0);	 Catch:{ Exception -> 0x0072 }
        r1 = 1;
        r1 = r9.getX(r1);	 Catch:{ Exception -> 0x0072 }
        r0 = r0 + r1;
        r0 = r0 / r2;
        r8.f = r0;	 Catch:{ Exception -> 0x0072 }
        r0 = 0;
        r0 = r9.getY(r0);	 Catch:{ Exception -> 0x0072 }
        r1 = 1;
        r1 = r9.getY(r1);	 Catch:{ Exception -> 0x0072 }
        r0 = r0 + r1;
        r0 = r0 / r2;
        r8.g = r0;	 Catch:{ Exception -> 0x0072 }
        r0 = r8.o;	 Catch:{ Exception -> 0x0072 }
        r1 = r8.p;	 Catch:{ Exception -> 0x0072 }
        r0 = r0 / r1;
        r1 = 1059816735; // 0x3f2b851f float:0.67 double:5.236190397E-315;
        r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
        if (r0 <= 0) goto L_0x0011;
    L_0x00f5:
        r0 = r8.b;	 Catch:{ Exception -> 0x0072 }
        r1 = r8.f;	 Catch:{ Exception -> 0x0072 }
        r2 = r8.g;	 Catch:{ Exception -> 0x0072 }
        r0 = r0.a(r8, r1, r2);	 Catch:{ Exception -> 0x0072 }
        if (r0 == 0) goto L_0x0011;
    L_0x0101:
        r0 = r8.d;	 Catch:{ Exception -> 0x0072 }
        r0.recycle();	 Catch:{ Exception -> 0x0072 }
        r0 = android.view.MotionEvent.obtain(r9);	 Catch:{ Exception -> 0x0072 }
        r8.d = r0;	 Catch:{ Exception -> 0x0072 }
        goto L_0x0011;
    L_0x010e:
        r8.b(r9);	 Catch:{ Exception -> 0x0072 }
        r0 = r8.u;	 Catch:{ Exception -> 0x0072 }
        if (r0 != 0) goto L_0x011a;
    L_0x0115:
        r0 = r8.b;	 Catch:{ Exception -> 0x0072 }
        r0.b(r8);	 Catch:{ Exception -> 0x0072 }
    L_0x011a:
        r8.c();	 Catch:{ Exception -> 0x0072 }
        goto L_0x0011;
    L_0x011f:
        r0 = r8.u;	 Catch:{ Exception -> 0x0072 }
        if (r0 != 0) goto L_0x0128;
    L_0x0123:
        r0 = r8.b;	 Catch:{ Exception -> 0x0072 }
        r0.b(r8);	 Catch:{ Exception -> 0x0072 }
    L_0x0128:
        r8.c();	 Catch:{ Exception -> 0x0072 }
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.ttpod.component.scaleimage.b.a(android.view.MotionEvent):boolean");
    }

    private boolean a(float f, float f2, float f3, float f4, float f5) {
        if (f < f3 || f2 < f3 || f > f4 || f2 > f5) {
            return true;
        }
        return false;
    }

    private static float a(MotionEvent motionEvent, int i) {
        return (motionEvent.getRawX() - motionEvent.getX()) + motionEvent.getX(i);
    }

    private static float b(MotionEvent motionEvent, int i) {
        return (motionEvent.getRawY() - motionEvent.getY()) + motionEvent.getY(i);
    }

    private void b(MotionEvent motionEvent) {
        if (this.e != null) {
            this.e.recycle();
        }
        this.e = MotionEvent.obtain(motionEvent);
        this.l = -1.0f;
        this.m = -1.0f;
        this.n = -1.0f;
        MotionEvent motionEvent2 = this.d;
        float x = motionEvent2.getX(0);
        float y = motionEvent2.getY(0);
        float x2 = motionEvent2.getX(1);
        float y2 = motionEvent2.getY(1);
        float x3 = motionEvent.getX(0);
        float y3 = motionEvent.getY(0);
        x = x2 - x;
        y = y2 - y;
        x2 = motionEvent.getX(1) - x3;
        y2 = motionEvent.getY(1) - y3;
        this.h = x;
        this.i = y;
        this.j = x2;
        this.k = y2;
        this.q = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.o = motionEvent.getPressure(0) + motionEvent.getPressure(1);
        this.p = motionEvent2.getPressure(1) + motionEvent2.getPressure(0);
    }

    private void c() {
        if (this.d != null) {
            this.d.recycle();
            this.d = null;
        }
        if (this.e != null) {
            this.e.recycle();
            this.e = null;
        }
        this.u = false;
        this.c = false;
    }

    public boolean a() {
        return this.c;
    }

    public float b() {
        if (this.l == -1.0f) {
            float f = this.j;
            float f2 = this.k;
            this.l = FloatMath.sqrt((f * f) + (f2 * f2));
        }
        if (this.m == -1.0f) {
            f = this.h;
            f2 = this.i;
            this.m = FloatMath.sqrt((f * f) + (f2 * f2));
        }
        if (this.n == -1.0f) {
            this.n = this.l / this.m;
        }
        return this.n;
    }
}
