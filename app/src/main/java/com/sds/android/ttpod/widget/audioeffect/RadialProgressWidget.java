package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

public class RadialProgressWidget extends View {
    private static int W = 50;
    private boolean A;
    private boolean B;
    private float C;
    private float D;
    private int E;
    private int F;
    private int G;
    private String H;
    private b I;
    private Bitmap J;
    private int K;
    private int L;
    private int M;
    private Bitmap N;
    private int O;
    private float P;
    private int Q;
    private int R;
    private int S;
    private Bitmap T;
    private float U;
    private float V;
    protected int a;
    private int aa;
    protected int b;
    protected int c;
    protected boolean d;
    protected a e;
    protected float f;
    protected float g;
    protected float h;
    protected float i;
    protected float j;
    protected float k;
    private RectF l;
    private RectF m;
    private RectF n;
    private float o;
    private int p;
    private int q;
    private Paint r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private float x;
    private float y;
    private String z;

    public interface b {
        void a(RadialProgressWidget radialProgressWidget, int i);
    }

    public interface a {
        void a();

        void b();

        void c();
    }

    public RadialProgressWidget(Context context) {
        this(context, null);
    }

    public RadialProgressWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialProgressWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = true;
        this.e = null;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 0.0f;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = 0.0f;
        this.q = 240;
        this.r = new Paint(1);
        this.s = Color.parseColor("#FF636363");
        this.t = ViewCompat.MEASURED_STATE_MASK;
        this.u = -1;
        this.v = -1;
        this.w = ViewCompat.MEASURED_STATE_MASK;
        this.x = 5.0f;
        this.y = 2.0f;
        this.z = null;
        this.A = false;
        this.B = false;
        this.C = 0.0f;
        this.D = 0.0f;
        this.E = 0;
        this.F = 0;
        this.G = W;
        this.H = null;
        this.J = null;
        this.K = 0;
        this.L = 0;
        this.O = Color.parseColor("#222222");
        this.P = 2.0f;
        this.Q = 2;
        this.R = 4;
        this.U = 1.0f;
        this.V = 1.0f;
        this.aa = 0;
        int i2 = R.drawable.img_radial_progress_pointer;
        int i3 = R.drawable.img_radial_progress_bg;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RadialProgressWidget, i, 0);
            i2 = obtainStyledAttributes.getResourceId(2, R.drawable.img_radial_progress_pointer);
            i3 = obtainStyledAttributes.getResourceId(0, R.drawable.img_radial_progress_bg);
            this.w = obtainStyledAttributes.getColor(3, ViewCompat.MEASURED_STATE_MASK);
            this.O = obtainStyledAttributes.getColor(4, Color.parseColor("#222222"));
            this.P = obtainStyledAttributes.getFloat(5, 2.0f);
            this.x = obtainStyledAttributes.getFloat(7, 5.0f);
            this.t = obtainStyledAttributes.getColor(6, ViewCompat.MEASURED_STATE_MASK);
            this.R = obtainStyledAttributes.getInteger(9, 4);
            this.Q = obtainStyledAttributes.getInteger(8, 2);
            this.S = obtainStyledAttributes.getResourceId(10, 0);
        }
        this.J = d(i2);
        this.N = d(i3);
        if (this.S != 0) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(getResources(), this.S, options);
            options.inSampleSize = options.outWidth / options.outHeight;
            options.outWidth = (int) (((float) options.outWidth) * 0.8f);
            options.outHeight = (int) (((float) options.outHeight) * 0.8f);
            options.inJustDecodeBounds = false;
            options.inPreferredConfig = Config.RGB_565;
            options.inInputShareable = true;
            options.inPurgeable = true;
            this.T = BitmapFactory.decodeResource(getResources(), this.S, options);
        }
    }

    protected void onDraw(Canvas canvas) {
        float f;
        super.onDraw(canvas);
        this.r.setStyle(Style.STROKE);
        this.r.setStrokeWidth(this.x * getResources().getDisplayMetrics().density);
        this.r.setColor(this.t);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        if (this.N != null) {
            int width = (int) (((float) (this.a / 2)) - ((((float) this.N.getWidth()) * this.U) / 2.0f));
            int height = (int) (((float) (this.b / 2)) - ((((float) this.N.getHeight()) * this.U) / 2.0f));
            canvas.save();
            canvas.scale(this.U, this.U);
            canvas.drawBitmap(this.N, (float) width, (float) height, paint);
            canvas.restore();
        }
        if (this.c <= W) {
            f = ((float) ((this.c * this.q) / W)) + 0.01f;
            canvas.drawArc(this.l, 30.0f, f - ((float) this.q), false, this.r);
        } else {
            f = 0.0f;
        }
        this.r.setStyle(Style.STROKE);
        this.r.setColor(this.O);
        this.r.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        this.r.setStrokeWidth(this.P * getResources().getDisplayMetrics().density);
        if (this.m != null) {
            canvas.drawArc(this.m, 153.0f, 234.0f, false, this.r);
        }
        if (this.n != null) {
            canvas.drawArc(this.n, 153.0f, 234.0f, false, this.r);
        }
        if (this.A || this.B) {
            this.r.setColor(this.u);
            this.r.setTextSize(this.C);
            if (this.H != null) {
                this.r.setTypeface(Typeface.createFromAsset(getContext().getAssets(), this.H));
            }
        }
        if (this.A) {
            canvas.drawText(String.valueOf(this.E) + "%", ((float) (this.a / 2)) - (this.r.measureText(String.valueOf(this.E) + "%") / 2.0f), ((float) (this.b / 2)) + (this.o / 8.0f), this.r);
        } else if (this.B) {
            this.r.setStrokeWidth(getResources().getDisplayMetrics().density * 3.0f);
            canvas.drawText(String.valueOf(this.c), ((float) (this.a / 2)) - (this.r.measureText(String.valueOf(this.c)) / 2.0f), ((float) (this.b / 2)) + (this.o / 8.0f), this.r);
        }
        if (this.z != null) {
            this.r.setColor(this.v);
            float measureText = this.r.measureText(this.z);
            this.r.setTextSize(this.D);
            canvas.drawText(this.z, ((float) (this.a / 2)) - (measureText / 5.0f), ((float) (this.b / 2)) + (this.o / 3.0f), this.r);
        }
        if (this.T != null) {
            canvas.drawBitmap(this.T, (float) (((this.a / 2) - (this.T.getWidth() / 2)) - 1), (float) (((this.b / 2) - (this.T.getHeight() / 2)) + 1), this.r);
        }
        if (this.J != null) {
            canvas.save();
            canvas.rotate((239.0f + f) % 360.0f, (float) (this.a / 2), (float) (this.a / 2));
            canvas.scale(this.V, this.V);
            canvas.drawBitmap(this.J, (float) ((int) (((float) (this.a / 2)) - (((((float) this.J.getWidth()) * this.V) - 4.0f) / 2.0f))), (float) ((int) (((float) (this.a / 2)) - (((((float) this.J.getHeight()) * this.V) + TitleBar.SHAREBTN_RIGHT_MARGIN) / 2.0f))), paint);
            canvas.restore();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > i2) {
            this.p = i2;
            this.o = (float) ((this.p / 2) - (getPaddingTop() + getPaddingBottom()));
        } else {
            this.p = i;
            this.o = (float) ((this.p / 2) - (getPaddingLeft() + getPaddingRight()));
        }
        this.a = getWidth();
        this.b = getHeight();
        int width = this.N.getWidth();
        int height = this.N.getHeight();
        if (this.a < width || this.b < height) {
            float min = Math.min((((float) this.a) + 0.01f) / ((float) width), (((float) this.b) + 0.01f) / ((float) height));
            Matrix matrix = new Matrix();
            matrix.postScale(min, min);
            Bitmap createBitmap = Bitmap.createBitmap(this.N, 0, 0, width, height, matrix, true);
            this.N.recycle();
            this.N = createBitmap;
        } else {
            this.U = (((float) this.a) + 0.01f) / ((float) width);
        }
        width = this.J.getWidth();
        height = this.J.getHeight();
        if (this.a < width || this.b < height) {
            min = Math.min((((float) this.a) + 0.01f) / ((float) width), (((float) this.b) + 0.01f) / ((float) height));
            matrix = new Matrix();
            matrix.postScale(min, min);
            createBitmap = Bitmap.createBitmap(this.J, 0, 0, width, height, matrix, true);
            this.J.recycle();
            this.J = createBitmap;
        } else {
            this.V = (((float) this.a) + 0.01f) / ((float) width);
        }
        int width2 = (int) ((((float) (this.a / 2)) - ((((float) this.N.getWidth()) * this.U) / 2.0f)) + ((float) getPaddingLeft()));
        int width3 = (int) ((((float) (this.a / 2)) + ((((float) this.N.getWidth()) * this.U) / 2.0f)) - ((float) getPaddingRight()));
        int width4 = (int) ((((float) (this.b / 2)) - ((((float) this.N.getWidth()) * this.U) / 2.0f)) + ((float) getPaddingTop()));
        width = (int) ((((float) (this.b / 2)) + ((((float) this.N.getWidth()) * this.U) / 2.0f)) - ((float) getPaddingBottom()));
        this.l = new RectF(new Rect(width2, width4, width3, width));
        if (this.Q != 0) {
            this.m = new RectF(new Rect(width2 - this.Q, width4 - this.Q, this.Q + width3, this.Q + width));
        }
        if (this.R != 0) {
            this.n = new RectF(new Rect(width2 + this.R, width4 + this.R, width3 - this.R, width - this.R));
        }
        this.C = this.o / 2.0f;
        this.D = this.o / 5.0f;
        this.L = (int) (this.o * TTFMImageUtils.Middle_Scale);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.J != null) {
            this.J.recycle();
            this.J = null;
        }
        if (this.N != null) {
            this.N.recycle();
            this.N = null;
        }
        if (this.T != null) {
            this.T.recycle();
            this.T = null;
        }
    }

    protected void a(int i) {
        if (i > W - 1) {
            i = W;
        } else if (i < 1) {
            i = 0;
        }
        if (this.M != i) {
            this.M = i;
            setCurrentValue(i);
            if (this.I != null) {
                this.I.a(this, i);
            }
            invalidate();
        }
    }

    private Bitmap d(int i) {
        try {
            return BitmapFactory.decodeResource(getResources(), i);
        } catch (OutOfMemoryError e) {
            Options options = new Options();
            options.inSampleSize = 4;
            return BitmapFactory.decodeResource(getResources(), i, options);
        }
    }

    protected final boolean a(int i, int i2) {
        return Math.pow((double) (i - (this.a / 2)), 2.0d) + Math.pow((double) (i2 - (this.a / 2)), 2.0d) > Math.pow((double) ((int) (this.l.width() * TTFMImageUtils.Middle_Scale)), 2.0d);
    }

    protected final boolean b(int i, int i2) {
        return Math.pow((double) (i - (this.a / 2)), 2.0d) + Math.pow((double) (i2 - (this.a / 2)), 2.0d) < Math.pow((double) ((int) (this.l.width() * TTFMImageUtils.Middle_Scale)), 2.0d);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        if (!this.d) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.f = motionEvent.getX();
                this.g = motionEvent.getY();
                if (this.e != null) {
                    this.e.a();
                }
                if (!a((int) this.f, (int) this.g)) {
                    this.K = ((int) (((double) this.a) * (Math.cos(Math.toRadians(30.0d)) + 1.0d))) / 2;
                    this.aa = ((int) (((double) this.a) * (1.0d - Math.sin(Math.toRadians(30.0d))))) / 2;
                    break;
                }
                break;
            case 1:
                if (this.e != null) {
                    this.e.c();
                }
                this.j = 0.0f;
                this.k = 0.0f;
                break;
            case 2:
                if (this.e != null) {
                    this.e.b();
                }
                int abs = Math.abs(((int) motionEvent.getX()) - ((int) this.f));
                int abs2 = Math.abs(((int) motionEvent.getY()) - ((int) this.g));
                if (Math.sqrt((double) ((abs * abs) + (abs2 * abs2))) >= 20.0d) {
                    if (this.j == 0.0f && this.k == 0.0f) {
                        this.j = this.f;
                        this.k = this.g;
                    }
                    this.h = motionEvent.getX() - this.j;
                    this.i = motionEvent.getY() - this.k;
                    this.j = motionEvent.getX();
                    this.k = motionEvent.getY();
                    if (!a((int) this.f, (int) this.g) && b((int) this.j, (int) this.k)) {
                        abs = a(new Point(this.K, this.aa), new Point(this.a / 2, this.b / 2), new Point((int) motionEvent.getX(), (int) motionEvent.getY()));
                        if (abs > this.q) {
                            if (this.c < 15) {
                                abs = 0;
                            } else if (this.c > 35) {
                                abs = this.q;
                            }
                        }
                        abs2 = (W * abs) / this.q;
                        if (abs2 > W) {
                            abs2 = W;
                        }
                        if (abs2 >= 0) {
                            i = abs2;
                        }
                        g.a("zz:", "tempValue= " + i + "   getCurrentValue= " + this.c + "   angle=  " + abs);
                        if (Math.abs(i - this.c) > 50) {
                            if (this.c != 0 && this.c != W) {
                                if (!a((int) this.j, (int) this.k)) {
                                    if (this.h >= 0.0f) {
                                        a(getCurrentValue() + (((int) Math.abs(this.h)) % 2));
                                        break;
                                    }
                                    a(getCurrentValue() - (((int) Math.abs(this.h)) % 2));
                                    break;
                                }
                            }
                            a(this.c);
                            break;
                        }
                        a(i);
                        break;
                    }
                }
                break;
        }
        return true;
    }

    private int a(Point point, Point point2, Point point3) {
        Point point4 = new Point(point2.x - point.x, point2.y - point.y);
        Point point5 = new Point(point2.x - point3.x, point2.y - point3.y);
        float atan2 = (float) Math.atan2((double) ((float) ((point4.x * point5.y) - (point4.y * point5.x))), (double) ((float) ((point4.x * point5.x) + (point4.y * point5.y))));
        if (((int) Math.toDegrees((double) atan2)) < 0) {
            return ((int) Math.toDegrees((double) atan2)) + 360;
        }
        return (int) Math.toDegrees((double) atan2);
    }

    public int getCurrentValue() {
        return this.c;
    }

    public void setOnRadialViewValueChanged(b bVar) {
        this.I = bVar;
    }

    public void setCurrentValue(int i) {
        if (this.c != i) {
            this.c = i;
            invalidate();
        }
    }

    public static int getMaxValue() {
        return W;
    }

    public static int b(int i) {
        return Math.min((W * i) / 1000, W);
    }

    public static int c(int i) {
        return Math.min((i * 1000) / W, 1000);
    }

    public void setMaxValue(int i) {
        W = i;
    }

    public int getBaseColor() {
        return this.s;
    }

    public void setBaseColor(int i) {
        this.s = i;
    }

    public int getBorderColor() {
        return this.t;
    }

    public void setBorderColor(int i) {
        this.t = i;
    }

    public int getCenterTextColor() {
        return this.u;
    }

    public void setCenterTextColor(int i) {
        this.u = i;
    }

    public int getSecondaryTextColor() {
        return this.v;
    }

    public void setSecondaryTextColor(int i) {
        this.v = i;
    }

    public int getShadowColor() {
        return this.w;
    }

    public void setShadowColor(int i) {
        this.w = i;
    }

    public float getBorderStrokeThickness() {
        return this.x;
    }

    public void setBorderStrokeThickness(float f) {
        this.x = f;
    }

    public float getShadowRadius() {
        return this.y;
    }

    public void setShadowRadius(float f) {
        this.y = f;
    }

    public String getSecondaryText() {
        return this.z;
    }

    public void setSecondaryText(String str) {
        this.z = str;
    }

    public void setShowPercentText(boolean z) {
        this.A = z;
    }

    public float getCenterTextSize() {
        return this.C;
    }

    public void setCenterTextSize(float f) {
        this.C = f;
    }

    public float getSecondaryTextSize() {
        return this.D;
    }

    public void setSecondaryTextSize(float f) {
        this.D = f;
    }

    public void setTouchEnabled(boolean z) {
        this.d = z;
    }

    public int getMinChangeValue() {
        return this.F;
    }

    public void setMinChangeValue(int i) {
        this.F = i;
    }

    public int getMaxChangeValue() {
        return this.G;
    }

    public void setMaxChangeValue(int i) {
        this.G = i;
    }

    public void setCircleBackgroundBitmap(Bitmap bitmap) {
        this.N = bitmap;
    }

    public void setFontName(String str) {
        this.H = str;
    }

    public void setMotionListener(a aVar) {
        this.e = aVar;
    }
}
