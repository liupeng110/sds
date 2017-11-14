package com.sds.android.ttpod.component.danmaku.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.component.danmaku.b.c;
import com.sds.android.ttpod.component.danmaku.b.c.a;
import com.sds.android.ttpod.component.danmaku.b.d;
import com.sds.android.ttpod.component.danmaku.b.f;
import java.util.LinkedList;
import java.util.Locale;

public class DanmakuView extends View implements f {
    protected int a = 0;
    private a b;
    private HandlerThread c;
    private c d;
    private boolean e;
    private boolean f = true;
    private boolean g;
    private boolean h = true;
    private Object i = new Object();
    private boolean j = false;
    private boolean k = false;
    private long l;
    private LinkedList<Long> m;
    private boolean n;

    public DanmakuView(Context context) {
        super(context);
        q();
    }

    private void q() {
        this.l = Thread.currentThread().getId();
        setBackgroundColor(0);
        setDrawingCacheBackgroundColor(0);
        d.a(true, false);
    }

    public DanmakuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        q();
    }

    public DanmakuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        q();
    }

    public void a(com.sds.android.ttpod.component.danmaku.c.b.c cVar) {
        if (this.d != null) {
            this.d.a(cVar);
        }
    }

    public void e() {
        if (this.d != null) {
            this.d.h();
        }
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.d != null) {
            this.d.a(aVar);
        }
    }

    public void f() {
        g();
        if (this.m != null) {
            this.m.clear();
        }
    }

    public void g() {
        r();
    }

    private void r() {
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
        if (this.c != null) {
            try {
                this.c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.c.quit();
            this.c = null;
        }
    }

    protected Looper a(int i) {
        int i2;
        if (this.c != null) {
            this.c.quit();
            this.c = null;
        }
        switch (i) {
            case 1:
                return Looper.getMainLooper();
            case 2:
                i2 = -8;
                break;
            case 3:
                i2 = 19;
                break;
            default:
                i2 = 0;
                break;
        }
        this.c = new HandlerThread("Danmaku Handler Thread #" + i2, i2);
        this.c.start();
        return this.c.getLooper();
    }

    private void s() {
        if (this.d == null) {
            this.d = new c(a(this.a), this, this.h);
        }
    }

    public void a(com.sds.android.ttpod.component.danmaku.c.c.a aVar) {
        s();
        this.d.a(aVar);
        this.d.a(this.b);
        this.d.e();
    }

    public boolean h() {
        return this.d != null && this.d.c();
    }

    public void a(boolean z) {
        this.g = z;
    }

    private float t() {
        long currentTimeMillis = System.currentTimeMillis();
        this.m.addLast(Long.valueOf(currentTimeMillis));
        float longValue = (float) (currentTimeMillis - ((Long) this.m.getFirst()).longValue());
        if (this.m.size() > 50) {
            this.m.removeFirst();
        }
        return longValue > 0.0f ? ((float) (this.m.size() * 1000)) / longValue : 0.0f;
    }

    public long b() {
        if (!this.e) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        v();
        return System.currentTimeMillis() - currentTimeMillis;
    }

    @SuppressLint({"NewApi"})
    private void u() {
        this.k = true;
        if (VERSION.SDK_INT >= 16) {
            postInvalidateOnAnimation();
        } else {
            postInvalidate();
        }
    }

    private void v() {
        if (this.h) {
            u();
            synchronized (this.i) {
                while (!this.j && this.d != null) {
                    try {
                        this.i.wait(200);
                    } catch (InterruptedException e) {
                        if (!this.h || this.d == null || this.d.b()) {
                            break;
                        }
                        Thread.currentThread().interrupt();
                    }
                }
                this.j = false;
            }
        }
    }

    private void w() {
        this.n = true;
        v();
    }

    private void x() {
        synchronized (this.i) {
            this.j = true;
            this.i.notifyAll();
        }
    }

    protected void onDraw(Canvas canvas) {
        if (this.h || this.k) {
            if (this.n) {
                d.a(canvas);
                this.n = false;
            } else if (this.d != null) {
                com.sds.android.ttpod.component.danmaku.c.d.a.a a = this.d.a(canvas);
                if (this.g) {
                    if (this.m == null) {
                        this.m = new LinkedList();
                    }
                    d.a(canvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(t()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.m), Long.valueOf(a.n)}));
                }
            }
            this.k = false;
            x();
            return;
        }
        super.onDraw(canvas);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.d != null) {
            this.d.a(i3 - i, i4 - i2);
        }
        this.e = true;
    }

    public void i() {
        if (this.d != null) {
            this.d.f();
        }
    }

    public void j() {
        if (this.d == null || !this.d.c()) {
            k();
        } else {
            this.d.d();
        }
    }

    public void k() {
        g();
        l();
    }

    public void l() {
        a(0);
    }

    public void a(long j) {
        if (this.d == null) {
            s();
        } else {
            this.d.removeCallbacksAndMessages(null);
        }
        this.d.obtainMessage(1, Long.valueOf(j)).sendToTarget();
    }

    public void a(Long l) {
        if (this.d != null) {
            this.d.a(l);
        }
    }

    public void b(boolean z) {
        this.f = z;
    }

    public boolean c() {
        return this.f;
    }

    public boolean a() {
        return this.e;
    }

    public View getView() {
        return this;
    }

    public void m() {
        b(null);
    }

    public void b(Long l) {
        this.h = true;
        this.n = false;
        if (this.d != null) {
            this.d.b(l);
        }
    }

    public void n() {
        this.h = false;
        if (this.d != null) {
            this.d.a(false);
        }
    }

    public void d() {
        if (!a()) {
            return;
        }
        if (!this.h || Thread.currentThread().getId() == this.l) {
            this.n = true;
            u();
            return;
        }
        w();
    }

    public boolean isShown() {
        return this.h && super.isShown();
    }

    public void setDrawingThreadType(int i) {
        this.a = i;
    }

    public long getCurrentTime() {
        if (this.d != null) {
            return this.d.j();
        }
        return 0;
    }

    @SuppressLint({"NewApi"})
    public boolean isHardwareAccelerated() {
        if (VERSION.SDK_INT >= 11) {
            return super.isHardwareAccelerated();
        }
        return false;
    }

    public void o() {
        if (this.d != null) {
            this.d.i();
        }
    }

    public void p() {
        setCallback(null);
        o();
        e();
        f();
    }
}
