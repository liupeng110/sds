package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import com.sds.android.ttpod.R;
import com.tencent.open.yyb.TitleBar;

public class RotateButton extends View {
    static final /* synthetic */ boolean a;
    private static final double[] l = new double[]{225.0d, 198.0d, 171.0d, 144.0d, 117.0d, 90.0d, 63.0d, 36.0d, 9.0d, 342.0d, 315.0d, 288.0d, 261.0d, 234.0d, 207.0d};
    private static final float[] m = new float[]{225.0f, 252.0f, 279.0f, 306.0f, 333.0f, 360.0f, 27.0f, 54.0f, 81.0f, 108.0f, 135.0f};
    private Bitmap b = null;
    private Bitmap c = null;
    private Bitmap d = null;
    private Bitmap e = null;
    private Bitmap f = null;
    private Bitmap g = null;
    private Bitmap h = null;
    private Bitmap i = null;
    private Bitmap j = null;
    private Paint k = null;
    private Point[] n = new Point[14];
    private int o = 5;
    private b p = null;
    private float q = 360.0f;
    private boolean r = false;
    private Point s = new Point();
    private String t = null;
    private String u = null;
    private float v = getResources().getDisplayMetrics().density;
    private boolean w;
    private Bitmap x = null;
    private int y;

    private static class a {

        enum a {
            QUAD_NONE,
            QUAD_ONE,
            QUAD_TWO,
            QUAD_THREE,
            QUAD_FOUR
        }

        public static a a(Point point) {
            if (point.x == 0 || point.y == 0) {
                return a.QUAD_NONE;
            }
            if (point.x > 0) {
                if (point.y > 0) {
                    return a.QUAD_ONE;
                }
                return a.QUAD_TWO;
            } else if (point.y < 0) {
                return a.QUAD_THREE;
            } else {
                return a.QUAD_FOUR;
            }
        }

        public static int b(Point point) {
            return (int) (c(point) * 57.295780490442965d);
        }

        private static double c(Point point) {
            if (point.x == 0 && point.y == 0) {
                return 0.0d;
            }
            double asin = Math.asin(((double) point.x) / Math.sqrt((double) ((point.x * point.x) + (point.y * point.y))));
            switch (a(point)) {
                case QUAD_ONE:
                    return asin;
                case QUAD_TWO:
                    return 3.1415926d - asin;
                case QUAD_THREE:
                    return 3.1415926d - asin;
                case QUAD_FOUR:
                    return asin + 6.2831852d;
                default:
                    if (point.x == 0 && point.y == 0) {
                        return 0.0d;
                    }
                    if (point.x == 0) {
                        if (point.y > 0) {
                            return 0.0d;
                        }
                        return 3.1415926d;
                    } else if (point.y != 0) {
                        return asin;
                    } else {
                        if (point.x > 0) {
                            return 1.5707963d;
                        }
                        return 4.71238899230957d;
                    }
            }
        }
    }

    public interface b {
        void a(RotateButton rotateButton, int i, int i2);
    }

    static {
        boolean z;
        if (RotateButton.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    public void setLeftText(String str) {
        this.t = str;
    }

    public void setRightText(String str) {
        this.u = str;
    }

    public void setOnRotateChangeListener(b bVar) {
        this.p = bVar;
    }

    private void a(int i, int i2) {
        float b = (float) a.b(new Point(i, i2));
        if (b <= m[m.length - 1] || b >= 210.0f) {
            this.q = b;
        }
    }

    public void a(Bitmap bitmap, Bitmap bitmap2) {
        this.b = bitmap;
        this.c = bitmap2;
        invalidate();
    }

    public void b(Bitmap bitmap, Bitmap bitmap2) {
        this.d = bitmap;
        this.e = bitmap2;
        if (this.d != null) {
            a(((float) Math.min(this.d.getWidth() / 2, this.d.getHeight() / 2)) - (((float) this.y) * this.v));
        }
        invalidate();
    }

    public void setHalfReference(Bitmap bitmap) {
        this.x = bitmap;
    }

    public void c(Bitmap bitmap, Bitmap bitmap2) {
        this.f = bitmap;
        this.g = bitmap2;
        invalidate();
    }

    public void setLeftRightBalance(boolean z) {
        this.w = z;
    }

    public int getNumberOfCalibration() {
        return 11;
    }

    public void setCalibration(int i) {
        if (!a && m.length != 11) {
            throw new AssertionError();
        } else if (i >= 0 && i < 11) {
            this.q = m[i];
            this.o = i;
            postInvalidate();
        }
    }

    public RotateButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ RotateButton a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        ViewParent parent = this.a.getParent();
                        if (parent != null) {
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        this.a.r = true;
                        this.a.postInvalidate();
                        ((Vibrator) this.a.getContext().getSystemService("vibrator")).vibrate(50);
                        break;
                    case 1:
                        if (this.a.p != null) {
                            this.a.p.a(this.a, this.a.o, 10);
                        }
                        this.a.r = false;
                        this.a.postInvalidate();
                        this.a.setCalibration(this.a.o);
                        break;
                    case 2:
                        this.a.a((int) (motionEvent.getX() - ((float) this.a.s.x)), (int) (-(motionEvent.getY() - ((float) this.a.s.y))));
                        this.a.o = this.a.a();
                        this.a.postInvalidate();
                        this.a.setCalibration(this.a.o);
                        break;
                    case 3:
                        this.a.r = false;
                        this.a.postInvalidate();
                        this.a.setCalibration(this.a.o);
                        break;
                }
                return true;
            }
        });
        setLongClickable(true);
        com.sds.android.sdk.lib.util.b bVar = new com.sds.android.sdk.lib.util.b();
        bVar.a(false);
        Resources resources = getResources();
        b(bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_normal), bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_pressed));
        a(bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_indicator_normal), bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_indicator_pressed));
        c(bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_graduation_normal), bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_graduation_pressed));
        this.t = "-";
        this.u = "+";
        this.y = 2;
        this.h = bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_up);
        this.i = bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_down);
        this.j = bVar.a(resources, (int) R.drawable.img_background_effect_rotatebutton_ring);
        this.k = new Paint();
        this.k.setAntiAlias(true);
        this.k.setStyle(Style.FILL);
        this.k.setStrokeWidth(4.0f);
        this.k.setColor(Color.parseColor("#1f2223"));
    }

    private void a(float f) {
        for (int i = 0; i < 14; i++) {
            double cos = Math.cos(Math.toRadians(l[i])) * ((double) f);
            double sin = Math.sin(Math.toRadians(l[i])) * ((double) f);
            this.n[i] = null;
            this.n[i] = new Point(((int) cos) + this.s.x, this.s.y - ((int) sin));
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.s.x = ((i3 - i) + 1) / 2;
        this.s.y = ((i4 - i2) + 1) / 2;
        if (this.d != null) {
            a(((float) Math.min(this.d.getWidth() / 2, this.d.getHeight() / 2)) - (((float) this.y) * this.v));
        }
    }

    public void setRadiusOffset(int i) {
        this.y = i;
    }

    private void a(Canvas canvas) {
        if (this.d != null && this.e != null) {
            canvas.save();
            if (this.r) {
                canvas.drawBitmap(this.e, (float) (this.s.x - (this.e.getWidth() / 2)), (float) (this.s.y - (this.e.getHeight() / 2)), null);
            } else {
                canvas.drawBitmap(this.d, (float) (this.s.x - (this.d.getWidth() / 2)), (float) (this.s.y - (this.d.getHeight() / 2)), null);
            }
            canvas.restore();
        }
    }

    private void b(Canvas canvas) {
        if (this.j != null) {
            canvas.save();
            int width = this.s.x - (this.j.getWidth() / 2);
            int height = this.s.y - (this.j.getHeight() / 2);
            canvas.drawBitmap(this.j, (float) width, (float) height, null);
            canvas.drawArc(new RectF((float) (width - 2), (float) (height - 2), (float) ((width + this.j.getWidth()) + 4), (float) ((height + this.j.getHeight()) + 4)), 30.0f, (float) ((this.o * 24) - 240), true, this.k);
            canvas.restore();
        }
    }

    private int a() {
        int i = 0;
        while (i < 14) {
            if (Math.abs(((double) this.q) - (360.0d - l[i])) < 13.5d || 360.0d - Math.abs(((double) this.q) - (360.0d - l[i])) < 13.5d) {
                break;
            }
            i++;
        }
        i = 0;
        if (i == 0) {
            return 10;
        }
        i -= 3;
        if (i >= 0) {
            return i;
        }
        return 0;
    }

    private void c(Canvas canvas) {
        if (this.x != null) {
            canvas.save();
            canvas.drawBitmap(this.x, (float) (this.s.x - (this.x.getWidth() / 2)), (float) 0, null);
            canvas.restore();
        }
    }

    private void d(Canvas canvas) {
        if (this.b != null && this.c != null) {
            canvas.save();
            canvas.rotate(this.q, (float) this.s.x, (float) this.s.y);
            Bitmap bitmap = this.r ? this.c : this.b;
            int width = this.s.x - ((bitmap.getWidth() + 1) / 2);
            int height = this.s.y - ((bitmap.getHeight() + 1) / 2);
            Paint paint = new Paint();
            paint.setFilterBitmap(true);
            canvas.drawBitmap(bitmap, (float) width, (float) height, paint);
            canvas.restore();
        }
    }

    private void e(Canvas canvas) {
        if (this.h != null || this.i != null) {
            canvas.save();
            float left = (float) getLeft();
            float bottom = ((float) (getBottom() - this.i.getHeight())) - (TitleBar.SHAREBTN_RIGHT_MARGIN * this.v);
            canvas.drawBitmap(this.i, left, bottom, null);
            canvas.drawBitmap(this.h, ((float) (getWidth() - this.h.getWidth())) - left, bottom, null);
            canvas.restore();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
        a(canvas);
        c(canvas);
        d(canvas);
        e(canvas);
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (this.d != null) {
            size = this.d.getWidth();
            size2 = this.d.getHeight();
        }
        setMeasuredDimension((int) (((float) size) + (this.v * TitleBar.BACKBTN_LEFT_MARGIN)), (int) (((float) size2) + (this.v * TitleBar.BACKBTN_LEFT_MARGIN)));
    }
}
