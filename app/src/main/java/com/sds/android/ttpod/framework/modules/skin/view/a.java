package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* LineVisualization */
public class a extends View {
    private Drawable a;
    private Drawable b;
    private a c;
    private int d;
    private int e = 5;
    private int f = -1;
    private int g = 553648127;
    private int h = 1627389951;
    private final Rect i = new Rect();
    private final Rect j = new Rect();
    private int[] k;
    private long[] l;
    private int m = 5;
    private int n = 5;
    private int o = 0;
    private boolean p = false;
    private int q;
    private int r;
    private Bitmap s;
    private Bitmap t;
    private Bitmap u;
    private final Paint v = new Paint();
    private Canvas w = new Canvas();

    /* LineVisualization */
    public interface a {
        void a();

        void b();
    }

    public a(Context context) {
        super(context);
    }

    public void setNumberOfLine(int i) {
        int max = Math.max(Math.min(i, 128), 4);
        synchronized (this) {
            this.o = max;
            max <<= 1;
            if (this.k == null || this.k.length < max) {
                this.k = new int[max];
            }
            if (this.l == null || this.l.length < max) {
                this.l = new long[max];
            }
        }
    }

    public int getNumberOfLine() {
        return this.o;
    }

    public void setLineDrawable(Drawable drawable) {
        if (this.a != drawable) {
            this.a = drawable;
            d();
        }
    }

    public void setDotDrawable(Drawable drawable) {
        if (this.b != drawable) {
            this.b = drawable;
            d();
        }
    }

    public void setReflectionHeight(int i) {
        if (i != this.d) {
            this.d = i;
            d();
        }
    }

    private void a() {
        if (this.t != null) {
            int height = this.t.getHeight();
            this.v.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
            this.v.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) height, this.h, this.g, TileMode.CLAMP));
        }
    }

    public void setDivideHeight(int i) {
        if (this.e != i) {
            this.e = i;
            b();
        }
    }

    public void a(int i, int i2) {
        if (this.g != i || this.h != i2) {
            this.g = i;
            this.h = i2;
            a();
        }
    }

    public void a(int[] iArr) {
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        float f = ((float) (height / 100)) * 0.9f;
        if (height > 0) {
            int[] iArr2;
            long[] jArr;
            int i;
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this) {
                iArr2 = this.k;
                jArr = this.l;
                i = this.o;
            }
            int i2;
            if (iArr != null && iArr.length > 0) {
                this.p = true;
                i = Math.min(iArr.length, i);
                for (height = 0; height < i; height++) {
                    float f2;
                    i2 = height << 1;
                    int i3 = i2 + 1;
                    int i4 = (int) (((float) iArr[height]) * f);
                    if (i4 > iArr2[i2]) {
                        iArr2[i2] = i4;
                        jArr[i2] = 0;
                    } else if (iArr2[i2] > 0) {
                        if (jArr[i2] == 0) {
                            jArr[i2] = currentTimeMillis;
                        } else {
                            f2 = ((float) (currentTimeMillis - jArr[i2])) * 0.05f;
                            if (f2 > 0.0f) {
                                iArr2[i2] = (short) Math.max(0, Math.round(((float) iArr2[i2]) - f2));
                            }
                        }
                    }
                    if (iArr2[i2] > iArr2[i3]) {
                        iArr2[i3] = iArr2[i2];
                        jArr[i3] = 0;
                    } else if (jArr[i3] == 0) {
                        jArr[i3] = 500 + currentTimeMillis;
                    } else {
                        f2 = ((float) (currentTimeMillis - jArr[i3])) * 0.05f;
                        if (f2 > 0.0f) {
                            iArr2[i3] = (short) Math.max(iArr2[i2], Math.round(((float) iArr2[i3]) - f2));
                        }
                    }
                }
            } else if (this.p) {
                this.p = false;
                for (height = 0; height < i; height++) {
                    int i5 = height << 1;
                    i2 = i5 + 1;
                    if (iArr2[i5] > 0) {
                        this.p = true;
                        if (jArr[i5] == 0) {
                            jArr[i5] = currentTimeMillis;
                        } else {
                            float f3 = ((float) (currentTimeMillis - jArr[i5])) * 0.05f;
                            if (f3 > 0.0f) {
                                iArr2[i5] = (short) Math.max(0, Math.round(((float) iArr2[i5]) - f3));
                            }
                        }
                    }
                    if (iArr2[i2] > 0) {
                        this.p = true;
                        if (jArr[i2] == 0) {
                            jArr[i2] = 500 + currentTimeMillis;
                        } else {
                            f = ((float) (currentTimeMillis - jArr[i2])) * 0.05f;
                            if (f > 0.0f) {
                                iArr2[i2] = (short) Math.max(0, Math.round(((float) iArr2[i2]) - f));
                            }
                        }
                    }
                }
                if (!this.p) {
                    return;
                }
            } else {
                return;
            }
            postInvalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        int[] iArr = this.k;
        if (iArr != null && this.s != null && this.t != null) {
            int min = Math.min(this.o, iArr.length >> 1);
            int height = this.s.getHeight();
            int width = this.s.getWidth();
            int abs = Math.abs(this.f);
            int i = 0;
            Canvas canvas2 = this.w;
            canvas2.setBitmap(this.t);
            this.t.eraseColor(0);
            for (int i2 = 0; i2 < min; i2++) {
                int i3 = i2 << 1;
                int i4 = i3 + 1;
                i3 = height - iArr[i3];
                this.j.set(i, i3, i + width, height);
                this.i.set(0, i3, width, height);
                canvas2.drawBitmap(this.s, this.i, this.j, null);
                if (abs > 0) {
                    i3 = height - iArr[i4];
                    this.j.set(i, i3 - abs, i + width, i3);
                    this.i.set(0, 0, width, abs);
                    canvas2.drawBitmap(this.s, this.i, this.j, null);
                }
                i += this.m + width;
            }
            canvas.drawBitmap(this.t, 0.0f, (float) getTopPaddingOffset(), null);
            if (this.d > 0) {
                canvas2.setBitmap(this.u);
                canvas2.drawBitmap(this.t, 0.0f, 0.0f, null);
                canvas2.drawRect(0.0f, 0.0f, (float) this.q, (float) height, this.v);
                float f = ((float) this.d) / ((float) height);
                canvas.save(1);
                canvas.translate(0.0f, (float) (((getTopPaddingOffset() + this.e) + height) + this.d));
                canvas.scale(1.0f, -f);
                canvas.drawBitmap(this.u, 0.0f, (float) getTopPaddingOffset(), null);
                canvas.restore();
            }
        }
    }

    public void setLineWidth(int i) {
        if (i > 0 && this.n != i) {
            int i2 = this.n;
            setNumberOfLine((this.q + this.m) / (this.n + this.m));
            b();
        }
    }

    public void setDotHeight(int i) {
        if (i > 0 && this.f != i) {
            this.f = i;
        }
    }

    public void setLineDivideWidth(int i) {
        if (i >= 0 && i != this.m) {
            this.m = i;
            setNumberOfLine((this.q + this.m) / (this.n + this.m));
        }
    }

    private void b() {
        int i = this.n;
        int i2 = this.r - (this.d > 0 ? this.e + this.d : 0);
        if (this.s == null || this.s.getWidth() != i || this.s.getHeight() != i2) {
            c();
            if (i > 0 && i2 > 0) {
                try {
                    this.s = Bitmap.createBitmap(i, i2, Config.ARGB_8888);
                } catch (OutOfMemoryError e) {
                    this.s = Bitmap.createBitmap(i, i2, Config.ARGB_4444);
                }
                try {
                    this.t = Bitmap.createBitmap(this.q, i2, Config.ARGB_8888);
                } catch (OutOfMemoryError e2) {
                    this.t = Bitmap.createBitmap(this.q, i2, Config.ARGB_4444);
                }
                if (this.d > 0) {
                    try {
                        this.u = Bitmap.createBitmap(this.q, i2, Config.ARGB_8888);
                    } catch (OutOfMemoryError e3) {
                        this.u = Bitmap.createBitmap(this.q, i2, Config.ARGB_4444);
                    }
                }
                d();
                a();
            }
        }
    }

    private void c() {
        if (this.s != null) {
            this.s.recycle();
            this.s = null;
        }
        if (this.t != null) {
            this.t.recycle();
            this.t = null;
        }
        if (this.u != null) {
            this.u.recycle();
            this.u = null;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        d();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    private void d() {
        if (this.s != null) {
            int width = this.s.getWidth();
            int height = this.s.getHeight();
            Canvas canvas = new Canvas(this.s);
            if (this.f < 0 && this.b != null) {
                this.f = -Math.max(this.b.getIntrinsicHeight(), Math.max(this.n >> 2, 1));
            }
            int abs = Math.abs(this.f);
            if (this.a != null) {
                this.a.setBounds(0, abs, width, height);
                this.a.draw(canvas);
            }
            if (this.b != null && abs > 0) {
                canvas.save(2);
                canvas.clipRect(0, 0, width, abs);
                this.b.setBounds(0, 0, width, abs);
                this.b.draw(canvas);
                canvas.restore();
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int paddingTop = (measuredHeight - getPaddingTop()) - getPaddingBottom();
        if (this.r != paddingTop || this.q != paddingLeft) {
            LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof com.sds.android.ttpod.framework.modules.skin.b.c.a) {
                com.sds.android.ttpod.framework.modules.skin.b.c.a aVar = (com.sds.android.ttpod.framework.modules.skin.b.c.a) layoutParams;
                this.e = aVar.g(measuredHeight);
                this.f = aVar.j(measuredHeight);
                this.m = aVar.h(measuredWidth);
                this.n = aVar.i(measuredWidth);
                this.d = aVar.f(measuredHeight);
            }
            if (paddingLeft != this.q) {
                this.q = measuredWidth;
                setNumberOfLine((this.q + this.m) / (this.n + this.m));
            }
            if (paddingTop != this.r) {
                this.r = measuredHeight;
                b();
            }
            invalidate();
        }
    }

    public void setVisibility(int i) {
        int visibility = getVisibility();
        super.setVisibility(i);
        a aVar = this.c;
        if (aVar != null && i != visibility) {
            if (i == 0) {
                aVar.a();
            } else if (visibility == 0) {
                aVar.b();
            }
        }
    }

    public void setEnabled(boolean z) {
        boolean isEnabled = isEnabled();
        super.setEnabled(z);
        a aVar = this.c;
        if (aVar != null && z != isEnabled) {
            if (z) {
                aVar.a();
            } else {
                aVar.b();
            }
        }
    }

    public void setOnActiveListener(a aVar) {
        this.c = aVar;
    }
}
