package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import com.alibaba.wireless.security.SecExceptionCode;
import com.tencent.open.yyb.TitleBar;

public class RadialProgress extends RadialProgressWidget {
    private boolean l;
    private boolean m;
    private int n;
    private final float o;
    private final int p;
    private final int q;
    private int r;
    private final int s;
    private int t;
    private int u;
    private int v;
    private boolean w;

    public RadialProgress(Context context) {
        this(context, null);
    }

    public RadialProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = false;
        this.m = false;
        this.n = 100;
        this.o = getResources().getDisplayMetrics().density;
        this.p = (int) (this.o * 4.0f);
        this.q = (int) (this.o * TitleBar.BACKBTN_LEFT_MARGIN);
        this.r = 1;
        this.s = (int) (this.o * 280.0f);
        this.t = 0;
        this.u = 0;
        this.v = 100;
        this.w = false;
        a();
    }

    private void a(MotionEvent motionEvent) {
        this.f = (float) ((int) motionEvent.getX());
        this.g = (float) ((int) motionEvent.getY());
        if (this.e != null) {
            this.e.a();
        }
        if (a((int) this.f, (int) this.g)) {
            this.l = true;
        } else {
            this.l = false;
            ViewParent parent = getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
        this.w = false;
    }

    private void a() {
        if (this.o * TitleBar.SHAREBTN_RIGHT_MARGIN == TitleBar.SHAREBTN_RIGHT_MARGIN) {
            this.t = 50;
            this.r = 1;
            this.v = 78;
        } else if (this.o * TitleBar.SHAREBTN_RIGHT_MARGIN == 15.0f) {
            this.t = 110;
            this.r = 0;
        } else if (this.o * TitleBar.SHAREBTN_RIGHT_MARGIN == TitleBar.BACKBTN_LEFT_MARGIN) {
            this.t = 240;
            this.r = 2;
            this.v = 100;
        } else if (this.o * TitleBar.SHAREBTN_RIGHT_MARGIN == 30.0f) {
            this.t = SecExceptionCode.SEC_ERROR_STA_ENC;
            this.r = 3;
            this.v = 400;
        } else {
            this.t = 0;
            this.r = 0;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (!this.d) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                a(motionEvent);
                return true;
            case 1:
                if (this.e != null) {
                    this.e.c();
                }
                this.j = 0.0f;
                this.k = 0.0f;
                this.l = false;
                this.m = false;
                this.w = false;
                return true;
            case 2:
                if (this.e != null) {
                    this.e.b();
                }
                if (this.l) {
                    return true;
                }
                if (a((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.m = true;
                    this.u = this.s;
                } else {
                    this.m = false;
                    this.u = this.t;
                }
                int abs = Math.abs(((int) motionEvent.getX()) - ((int) this.f));
                int abs2 = Math.abs(((int) motionEvent.getY()) - ((int) this.g));
                if (Math.sqrt((double) ((abs * abs) + (abs2 * abs2))) < 20.0d) {
                    return true;
                }
                this.h = motionEvent.getX() - this.j;
                this.i = motionEvent.getY() - this.k;
                abs = (int) (Math.sqrt((double) ((this.h * this.h) + (this.i * this.i))) * 100.0d);
                if (abs != 0 && this.w) {
                    abs2 = a(new Point((int) this.j, (int) this.k), new Point(this.a / 2, this.b / 2), new Point((int) motionEvent.getX(), (int) motionEvent.getY()));
                    int i2 = abs2 / this.n;
                    if ((i2 >= this.r && Math.abs(abs2) > this.u) || (abs2 > 0 && i2 == 0 && abs > this.v)) {
                        i = 1;
                    } else if ((i2 <= (-this.r) && Math.abs(abs2) > this.u) || (abs2 < 0 && i2 == 0 && abs > this.v)) {
                        i = -1;
                    }
                    if (i != 0) {
                        a(i + this.c);
                    }
                }
                this.j = motionEvent.getX();
                this.k = motionEvent.getY();
                this.w = true;
                return true;
            default:
                return true;
        }
    }

    private int a(Point point, Point point2, Point point3) {
        Point point4 = new Point(point2.x - point.x, point2.y - point.y);
        Point point5 = new Point(point2.x - point3.x, point2.y - point3.y);
        return (point4.x * point5.y) - (point4.y * point5.x);
    }
}
