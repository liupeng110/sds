package com.sds.android.ttpod.component.danmaku.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import com.sds.android.ttpod.component.danmaku.b.c;
import com.sds.android.ttpod.component.danmaku.b.c.a;
import com.sds.android.ttpod.component.danmaku.b.d;
import com.sds.android.ttpod.component.danmaku.b.f;
import java.util.LinkedList;
import java.util.Locale;

public class DanmakuSurfaceView extends SurfaceView implements Callback, f {
    protected int a = 0;
    private a b;
    private SurfaceHolder c;
    private c d;
    private boolean e;
    private boolean f = true;
    private boolean g;
    private boolean h = true;
    private LinkedList<Long> i;

    private void e() {
        setZOrderMediaOverlay(true);
        setWillNotCacheDrawing(true);
        setDrawingCacheEnabled(false);
        setWillNotDraw(true);
        this.c = getHolder();
        this.c.addCallback(this);
        this.c.setFormat(-2);
        d.a(true, true);
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e();
    }

    public DanmakuSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e();
    }

    public void setCallback(a aVar) {
        this.b = aVar;
        if (this.d != null) {
            this.d.a(aVar);
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.e = true;
        Canvas lockCanvas = surfaceHolder.lockCanvas();
        if (lockCanvas != null) {
            d.a(lockCanvas);
            surfaceHolder.unlockCanvasAndPost(lockCanvas);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.d != null) {
            this.d.a(i2, i3);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.e = false;
    }

    private float f() {
        long currentTimeMillis = System.currentTimeMillis();
        this.i.addLast(Long.valueOf(currentTimeMillis));
        float longValue = (float) (currentTimeMillis - ((Long) this.i.getFirst()).longValue());
        if (this.i.size() > 50) {
            this.i.removeFirst();
        }
        return longValue > 0.0f ? ((float) (this.i.size() * 1000)) / longValue : 0.0f;
    }

    public long b() {
        if (!this.e) {
            return 0;
        }
        if (!isShown()) {
            return -1;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Canvas lockCanvas = this.c.lockCanvas();
        if (lockCanvas != null) {
            if (this.d != null) {
                com.sds.android.ttpod.component.danmaku.c.d.a.a a = this.d.a(lockCanvas);
                if (this.g) {
                    if (this.i == null) {
                        this.i = new LinkedList();
                    }
                    long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    d.a(lockCanvas, String.format(Locale.getDefault(), "fps %.2f,time:%d s,cache:%d,miss:%d", new Object[]{Float.valueOf(f()), Long.valueOf(getCurrentTime() / 1000), Long.valueOf(a.m), Long.valueOf(a.n)}));
                }
            }
            if (this.e) {
                this.c.unlockCanvasAndPost(lockCanvas);
            }
        }
        return System.currentTimeMillis() - currentTimeMillis;
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

    public void d() {
        if (a()) {
            Canvas lockCanvas = this.c.lockCanvas();
            if (lockCanvas != null) {
                d.a(lockCanvas);
                this.c.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    public boolean isShown() {
        return this.d != null && a() && this.d.g();
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

    public boolean isHardwareAccelerated() {
        return false;
    }
}
