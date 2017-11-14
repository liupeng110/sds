package com.sds.android.ttpod.activities.mv;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.sds.android.sdk.lib.util.g;

/* VideoGestureDetector */
public class d {
    private static final String a = d.class.getSimpleName();
    private int b;
    private int c;
    private long d;
    private long e;
    private int f;
    private float g;
    private float h;
    private boolean i;
    private a j;
    private a k;
    private MotionEvent l;
    private MotionEvent m;
    private MotionEvent n;

    /* VideoGestureDetector */
    private enum a {
        SETTING_CLICK,
        SETTING_DOUBLE_CLICK,
        SETTING_PROGRESS,
        SETTING_VOLUME,
        SETTING_BRIGHTNESS,
        IDLE
    }

    public d(Context context, a aVar, int i, int i2) {
        this.j = aVar;
        this.b = i;
        this.c = i2;
        a(context);
    }

    public void a(int i) {
        this.b = i;
    }

    public void b(int i) {
        this.c = i;
    }

    private void a(Context context) {
        int touchSlop;
        if (context == null) {
            touchSlop = ViewConfiguration.getTouchSlop();
        } else {
            touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        }
        this.f = touchSlop * touchSlop;
    }

    private boolean a(int i, int i2) {
        return this.f < (i * i) + (i2 * i2);
    }

    public boolean a(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEventCompat.ACTION_MASK) {
            case 0:
                this.m = MotionEvent.obtain(motionEvent);
                this.l = MotionEvent.obtain(motionEvent);
                this.k = a.IDLE;
                this.i = true;
                this.d = System.currentTimeMillis();
                this.j.onEnteringTouchMode();
                g.a(a, "action down");
                if (this.n == null) {
                    this.n = this.l;
                    break;
                }
                break;
            case 1:
            case 3:
                this.e = System.currentTimeMillis();
                long downTime = this.l.getDownTime() - this.n.getDownTime();
                this.n = this.l;
                g.a(a, "downEventInterval = " + downTime);
                if (this.i && downTime < 250 && 0 != downTime) {
                    this.k = a.SETTING_DOUBLE_CLICK;
                } else if (this.i && this.e - this.d < 250) {
                    this.k = a.SETTING_CLICK;
                }
                g.a(a, "end touch mode:" + this.k);
                switch (this.k) {
                    case SETTING_CLICK:
                        this.j.performClicked();
                        break;
                    case SETTING_PROGRESS:
                        this.j.onLeaveSetProgressMode(this.l, motionEvent, this.b);
                        break;
                    case SETTING_BRIGHTNESS:
                        this.j.onLeaveSetBrightnessMode(this.l, motionEvent, this.c);
                        break;
                    case SETTING_VOLUME:
                        this.j.onLeaveSetVolumeMode(this.l, motionEvent, this.c);
                        break;
                    case SETTING_DOUBLE_CLICK:
                        g.a(a, "double_click");
                        this.j.switchOrientation();
                        break;
                }
                this.j.onEndGesture();
                break;
            case 2:
                this.g = motionEvent.getX() - this.m.getX();
                this.h = motionEvent.getY() - this.m.getY();
                g.a(a, " onScroll");
                if (a((int) this.g, (int) this.h)) {
                    this.i = false;
                }
                switch (this.k) {
                    case SETTING_PROGRESS:
                        if (this.j.performSetProgress(this.m, motionEvent, this.l, this.b)) {
                            this.m = MotionEvent.obtain(motionEvent);
                            break;
                        }
                        break;
                    case SETTING_BRIGHTNESS:
                        if (this.j.performSetBrightness(this.m, motionEvent, this.c)) {
                            this.m = MotionEvent.obtain(motionEvent);
                            break;
                        }
                        break;
                    case SETTING_VOLUME:
                        if (this.j.performSetVolume(this.m, motionEvent, this.c)) {
                            this.m = MotionEvent.obtain(motionEvent);
                            break;
                        }
                        break;
                    case IDLE:
                        if (a((int) this.g, (int) this.h)) {
                            if (Math.abs(this.g) > Math.abs(this.h)) {
                                this.k = a.SETTING_PROGRESS;
                                this.j.onEnteringSetProgressMode();
                            } else if (motionEvent.getX() < ((float) (this.b >> 1))) {
                                this.k = a.SETTING_BRIGHTNESS;
                                this.j.onEnteringSetBrightnessMode();
                            } else if (motionEvent.getX() > ((float) (this.b >> 1))) {
                                this.k = a.SETTING_VOLUME;
                                this.j.onEnteringSetVolumeMode();
                            }
                            a(this.k);
                            break;
                        }
                        break;
                    default:
                        break;
                }
        }
        return true;
    }

    private void a(a aVar) {
        switch (aVar) {
            case SETTING_PROGRESS:
                this.j.beforeHorizontalTouchMove();
                return;
            case SETTING_BRIGHTNESS:
                this.j.beforeVerticalTouchMove();
                break;
            case SETTING_VOLUME:
                break;
            default:
                return;
        }
        this.j.beforeVerticalTouchMove();
    }
}
