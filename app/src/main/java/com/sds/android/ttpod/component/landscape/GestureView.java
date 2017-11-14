package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewConfiguration;
import com.sds.android.ttpod.framework.a.y;

public class GestureView extends View {
    private d a;
    private a b;
    private b c;
    private c d;
    private PointF e;
    private PointF f;
    private e g;
    private f h;
    private boolean i;

    public interface a {
        void a(double d);
    }

    public interface b {
        void b(double d);
    }

    public interface c {
        void a(int i);
    }

    public interface d {
        void a(float f, float f2, float f3, float f4);
    }

    public static class e {
        private View a;
        private OnClickListener b;
        private float c;
        private float d;
        private float e;
        private long f;
        private boolean g;

        public e(View view) {
            this.a = view;
            float f = view.getResources().getDisplayMetrics().density * 15.0f;
            this.e = f * f;
        }

        public void a(MotionEvent motionEvent) {
            this.c = motionEvent.getX();
            this.d = motionEvent.getY();
            this.f = System.currentTimeMillis();
            this.g = false;
        }

        public void b(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (((x - this.c) * (x - this.c)) + ((y - this.d) * (y - this.d)) >= this.e) {
                this.g = true;
            }
        }

        public void c(MotionEvent motionEvent) {
            if (!this.g && System.currentTimeMillis() - this.f < ((long) ViewConfiguration.getLongPressTimeout())) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (((x - this.c) * (x - this.c)) + ((y - this.d) * (y - this.d)) < this.e && this.b != null) {
                    this.b.onClick(this.a);
                }
            }
        }

        public void a() {
            this.g = true;
        }

        public void a(OnClickListener onClickListener) {
            this.b = onClickListener;
            this.a.setClickable(true);
        }
    }

    public static class f {
        private View a;
        private OnLongClickListener b;
        private float c;
        private float d;
        private float e;
        private boolean f;

        public f(View view) {
            this.a = view;
            float f = view.getResources().getDisplayMetrics().density * 15.0f;
            this.e = f * f;
        }

        public void a(MotionEvent motionEvent) {
            this.c = motionEvent.getX();
            this.d = motionEvent.getY();
            this.f = false;
        }

        public void b(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (((x - this.c) * (x - this.c)) + ((y - this.d) * (y - this.d)) >= this.e) {
                this.f = true;
            }
        }

        public boolean a() {
            if (this.f || this.b == null) {
                return false;
            }
            return this.b.onLongClick(this.a);
        }

        public void b() {
            this.f = true;
        }

        public void a(OnLongClickListener onLongClickListener) {
            this.b = onLongClickListener;
            this.a.setLongClickable(true);
        }
    }

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GestureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new PointF();
        this.f = new PointF();
        this.i = false;
    }

    public void setGestureTranslation(d dVar) {
        this.a = dVar;
    }

    public void setGestureRotate(a aVar) {
        this.b = aVar;
    }

    public void setGestureScale(b bVar) {
        this.c = bVar;
    }

    public void setGestureState(c cVar) {
        this.d = cVar;
    }

    public void setTTPodClickListener(OnClickListener onClickListener) {
        if (this.g == null) {
            this.g = new e(this);
        }
        this.g.a(onClickListener);
    }

    public void setTTPodLongClickListener(OnLongClickListener onLongClickListener) {
        if (this.h == null) {
            this.h = new f(this);
        }
        this.h.a(onLongClickListener);
    }

    public boolean performLongClick() {
        if (this.h != null) {
            this.h.a();
        }
        return super.performLongClick();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (this.g != null) {
                    this.g.a(motionEvent);
                }
                if (this.h != null) {
                    this.h.a(motionEvent);
                }
                if (this.d != null) {
                    this.d.a(33);
                    break;
                }
                break;
            case 1:
                if (this.g != null) {
                    this.g.c(motionEvent);
                }
                if (this.d != null) {
                    this.d.a(32);
                    break;
                }
                break;
            case 2:
                if (this.g != null) {
                    this.g.b(motionEvent);
                }
                if (this.h != null) {
                    this.h.b(motionEvent);
                }
                if (this.i && motionEvent.getPointerCount() >= 2) {
                    a(motionEvent);
                }
                y.a((View) this, true);
                break;
            case 6:
            case 262:
                this.i = false;
                if (this.d != null) {
                    this.d.a(31);
                    break;
                }
                break;
            case 261:
                if (this.g != null) {
                    this.g.a();
                }
                if (this.h != null) {
                    this.h.b();
                }
                if (motionEvent.getPointerCount() >= 2) {
                    this.i = true;
                    if (this.d != null) {
                        this.d.a(30);
                    }
                    a(motionEvent, this.e, this.f);
                    break;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void a(MotionEvent motionEvent, PointF pointF, PointF pointF2) {
        try {
            pointF.x = motionEvent.getX(motionEvent.getPointerId(0));
            pointF.y = motionEvent.getY(motionEvent.getPointerId(0));
            pointF2.x = motionEvent.getX(motionEvent.getPointerId(1));
            pointF2.y = motionEvent.getY(motionEvent.getPointerId(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PointF a(PointF pointF, PointF pointF2) {
        return new PointF(pointF2.x - pointF.x, pointF2.y - pointF.y);
    }

    private double a(PointF pointF) {
        return Math.sqrt((double) ((pointF.x * pointF.x) + (pointF.y * pointF.y)));
    }

    private double b(PointF pointF) {
        return (Math.atan2((double) pointF.y, (double) pointF.x) * 180.0d) / 3.141592653589793d;
    }

    private void a(MotionEvent motionEvent) {
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        a(motionEvent, pointF, pointF2);
        PointF a = a(this.e, pointF);
        PointF a2 = a(this.f, pointF2);
        PointF a3 = a(this.e, this.f);
        pointF = a(pointF, pointF2);
        double a4 = a(a3);
        double a5 = a(pointF);
        double b = b(a3);
        double b2 = b(pointF);
        if (this.a != null) {
            this.a.a(a.x, a.y, a2.x, a2.y);
        }
        if (this.c != null) {
            this.c.b(a5 - a4);
        }
        if (this.b != null) {
            b2 -= b;
            if (b2 > 180.0d) {
                b2 -= 360.0d;
            } else if (b2 < -180.0d) {
                b2 += 360.0d;
            }
            this.b.a(b2);
        }
    }
}
