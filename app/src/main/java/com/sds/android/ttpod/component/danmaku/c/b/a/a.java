package com.sds.android.ttpod.component.danmaku.c.b.a;

import android.annotation.SuppressLint;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.text.TextPaint;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.component.danmaku.c.b.b;
import com.sds.android.ttpod.component.danmaku.c.b.c;
import java.util.HashMap;
import java.util.Map;

/* AndroidDisplayer */
public class a extends com.sds.android.ttpod.component.danmaku.c.b.a<Canvas> {
    private static boolean A = g;
    public static TextPaint a = new TextPaint();
    public static TextPaint b = new TextPaint(a);
    public static int c = com.sds.android.ttpod.common.c.a.a(1);
    public static boolean d = false;
    public static boolean e = true;
    public static boolean f = false;
    public static boolean g = true;
    private static final Map<Float, Float> k = new HashMap();
    private static float l;
    private static final Map<Float, Float> m = new HashMap(10);
    private static Paint p = new Paint();
    private static Paint q = new Paint();
    private static Paint r = new Paint();
    private static float s = 4.0f;
    private static float t = 3.5f;
    private static float u = 1.0f;
    private static float v = 1.0f;
    private static int w = SecExceptionCode.SEC_ERROR_STA_STORE_INCORRECT_DATA_FILE;
    private static boolean x = d;
    private static boolean y = e;
    private static boolean z = f;
    private int B;
    private int C;
    private float D = 1.0f;
    private int E = 160;
    private float F = 1.0f;
    private int G = 0;
    private boolean H = true;
    private int I = 2048;
    private int J = 2048;
    public Canvas h;
    private Camera i = new Camera();
    private Matrix j = new Matrix();
    private int n = 0;
    private int o = 0;

    public /* synthetic */ Object a() {
        return l();
    }

    static {
        a.setStrokeWidth(t);
        q.setStrokeWidth((float) c);
        q.setStyle(Style.STROKE);
        r.setStyle(Style.STROKE);
        r.setStrokeWidth(4.0f);
    }

    @SuppressLint({"NewApi"})
    private static final int b(Canvas canvas) {
        if (VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapWidth();
        }
        return canvas.getWidth();
    }

    @SuppressLint({"NewApi"})
    private static final int c(Canvas canvas) {
        if (VERSION.SDK_INT >= 14) {
            return canvas.getMaximumBitmapHeight();
        }
        return canvas.getHeight();
    }

    private void d(Canvas canvas) {
        this.h = canvas;
        if (canvas != null) {
            this.B = canvas.getWidth();
            this.C = canvas.getHeight();
            if (this.H) {
                this.I = b(canvas);
                this.J = c(canvas);
            }
        }
    }

    public int c() {
        return this.B;
    }

    public int d() {
        return this.C;
    }

    public float e() {
        return this.D;
    }

    public int f() {
        return this.E;
    }

    public int a(c cVar) {
        int i;
        boolean z = false;
        float i2 = cVar.i();
        float h = cVar.h();
        if (this.h != null) {
            Paint paint;
            int i3;
            if (cVar.l() != 7) {
                paint = null;
                i3 = 0;
            } else if (cVar.n() != b.b) {
                int i4;
                if (cVar.e == 0.0f && cVar.f == 0.0f) {
                    i4 = 0;
                } else {
                    a(cVar, this.h, h, i2);
                    i4 = true;
                }
                if (cVar.n() != b.a) {
                    Paint paint2 = p;
                    paint2.setAlpha(cVar.n());
                    paint = paint2;
                    i3 = i4;
                } else {
                    paint = null;
                    i3 = i4;
                }
            }
            if (paint == null || paint.getAlpha() != b.b) {
                if (cVar.c()) {
                    e h2 = ((d) cVar.r).h();
                    if (h2 != null) {
                        z = h2.a(this.h, h, i2, paint);
                    }
                }
                if (z) {
                    i = 1;
                } else {
                    if (paint != null) {
                        a.setAlpha(paint.getAlpha());
                    } else {
                        a(a);
                    }
                    a(cVar, this.h, h, i2, true);
                    i = 2;
                }
                if (i3 != 0) {
                    e(this.h);
                }
            }
        }
        return i;
    }

    private void a(Paint paint) {
        if (paint.getAlpha() != b.a) {
            paint.setAlpha(b.a);
        }
    }

    private void e(Canvas canvas) {
        canvas.restore();
    }

    private int a(c cVar, Canvas canvas, float f, float f2) {
        this.i.save();
        this.i.rotateY(-cVar.f);
        this.i.rotateZ(-cVar.e);
        this.i.getMatrix(this.j);
        this.j.preTranslate(-f, -f2);
        this.j.postTranslate(f, f2);
        this.i.restore();
        int save = canvas.save();
        canvas.concat(this.j);
        return save;
    }

    public static void a(c cVar, Canvas canvas, float f, float f2, boolean z) {
        float ascent;
        float f3 = f + ((float) cVar.k);
        float f4 = ((float) cVar.k) + f2;
        if (cVar.j != 0) {
            f3 += 4.0f;
            f4 += 4.0f;
        }
        y = e;
        x = d;
        z = f;
        boolean z2 = !z && g;
        A = z2;
        Paint a = a(cVar, z);
        float f5;
        if (cVar.c != null) {
            String[] strArr = cVar.c;
            if (strArr.length == 1) {
                if (f(cVar)) {
                    a(cVar, a, true);
                    ascent = f4 - a.ascent();
                    if (z) {
                        f5 = u + f3;
                        ascent += v;
                    } else {
                        f5 = f3;
                    }
                    canvas.drawText(strArr[0], f5, ascent, a);
                }
                a(cVar, a, false);
                canvas.drawText(strArr[0], f3, f4 - a.ascent(), a);
            } else {
                float length = (cVar.n - ((float) (cVar.k * 2))) / ((float) strArr.length);
                int i = 0;
                while (i < strArr.length) {
                    if (!(strArr[i] == null || strArr[i].length() == 0)) {
                        if (f(cVar)) {
                            float f6;
                            a(cVar, a, true);
                            float ascent2 = ((((float) i) * length) + f4) - a.ascent();
                            if (z) {
                                f6 = u + f3;
                                ascent2 += v;
                            } else {
                                f6 = f3;
                            }
                            canvas.drawText(strArr[i], f6, ascent2, a);
                        }
                        a(cVar, a, false);
                        canvas.drawText(strArr[i], f3, ((((float) i) * length) + f4) - a.ascent(), a);
                    }
                    i++;
                }
            }
        } else {
            if (f(cVar)) {
                a(cVar, a, true);
                ascent = f4 - a.ascent();
                if (z) {
                    f5 = u + f3;
                    ascent += v;
                } else {
                    f5 = f3;
                }
                canvas.drawText(cVar.b, f5, ascent, a);
            }
            a(cVar, a, false);
            canvas.drawText(cVar.b, f3, f4 - a.ascent(), a);
        }
        if (cVar.h != 0) {
            ascent = (cVar.n + f2) - ((float) c);
            canvas.drawLine(f, ascent, f + cVar.m, ascent, d(cVar));
        }
        if (cVar.j != 0) {
            canvas.drawRect(f, f2, f + cVar.m, f2 + cVar.n, c(cVar));
        }
    }

    private static boolean f(c cVar) {
        return (y || z) && t > 0.0f && cVar.g != 0;
    }

    public static Paint c(c cVar) {
        r.setColor(cVar.j);
        return r;
    }

    public static Paint d(c cVar) {
        q.setColor(cVar.h);
        return q;
    }

    private static TextPaint a(c cVar, boolean z) {
        Paint paint;
        if (z) {
            paint = b;
            paint.set(a);
        } else {
            paint = a;
        }
        paint.setTextSize(cVar.i);
        a(cVar, paint);
        if (!x || s <= 0.0f || cVar.g == 0) {
            paint.clearShadowLayer();
        } else {
            paint.setShadowLayer(s, 0.0f, 0.0f, cVar.g);
        }
        paint.setAntiAlias(A);
        return paint;
    }

    public static TextPaint e(c cVar) {
        return a(cVar, false);
    }

    private static void a(c cVar, Paint paint, boolean z) {
        if (b.a.d) {
            if (z) {
                paint.setStyle(z ? Style.FILL : Style.STROKE);
                paint.setColor(cVar.g & ViewCompat.MEASURED_SIZE_MASK);
                paint.setAlpha(z ? (int) (((float) w) * (((float) b.a.c) / ((float) b.a))) : b.a.c);
                return;
            }
            paint.setStyle(Style.FILL);
            paint.setColor(cVar.d & ViewCompat.MEASURED_SIZE_MASK);
            paint.setAlpha(b.a.c);
        } else if (z) {
            paint.setStyle(z ? Style.FILL : Style.STROKE);
            paint.setColor(cVar.g & ViewCompat.MEASURED_SIZE_MASK);
            paint.setAlpha(z ? w : b.a);
        } else {
            paint.setStyle(Style.FILL);
            paint.setColor(cVar.d & ViewCompat.MEASURED_SIZE_MASK);
            paint.setAlpha(b.a);
        }
    }

    private static void a(c cVar, Paint paint) {
        if (b.a.f) {
            Float f = (Float) m.get(Float.valueOf(cVar.i));
            if (f == null || l != b.a.e) {
                l = b.a.e;
                f = Float.valueOf(cVar.i * b.a.e);
                m.put(Float.valueOf(cVar.i), f);
            }
            paint.setTextSize(f.floatValue());
        }
    }

    public void b(c cVar) {
        Paint e = e(cVar);
        if (y) {
            a(cVar, e, true);
        }
        a(cVar, (TextPaint) e);
        if (y) {
            a(cVar, e, false);
        }
    }

    private void a(c cVar, TextPaint textPaint) {
        float f = 0.0f;
        Float valueOf = Float.valueOf(a(textPaint));
        if (cVar.c == null) {
            if (cVar.b != null) {
                f = textPaint.measureText(cVar.b);
            }
            a(cVar, f, valueOf.floatValue());
            return;
        }
        for (String str : cVar.c) {
            if (str.length() > 0) {
                f = Math.max(textPaint.measureText(str), f);
            }
        }
        a(cVar, f, ((float) cVar.c.length) * valueOf.floatValue());
    }

    private void a(c cVar, float f, float f2) {
        float f3 = f + ((float) (cVar.k * 2));
        float f4 = ((float) (cVar.k * 2)) + f2;
        if (cVar.j != 0) {
            f3 += 8.0f;
            f4 += 8.0f;
        }
        cVar.m = f3 + m();
        cVar.n = f4;
    }

    private static float a(TextPaint textPaint) {
        Float valueOf = Float.valueOf(textPaint.getTextSize());
        Float f = (Float) k.get(valueOf);
        if (f == null) {
            FontMetrics fontMetrics = textPaint.getFontMetrics();
            f = Float.valueOf(fontMetrics.leading + (fontMetrics.descent - fontMetrics.ascent));
            k.put(valueOf, f);
        }
        return f.floatValue();
    }

    public static void k() {
        k.clear();
        m.clear();
    }

    public float g() {
        return this.F;
    }

    public void a(float f) {
        Math.max(this.D, this.F);
        float max = Math.max(f, ((float) c()) / 682.0f) * 25.0f;
        this.G = (int) max;
        if (f > 1.0f) {
            this.G = (int) (max * f);
        }
    }

    public int h() {
        return this.G;
    }

    public void a(float f, int i, float f2) {
        this.D = f;
        this.E = i;
        this.F = f2;
    }

    public void a(int i, int i2) {
        this.B = i;
        this.C = i2;
    }

    public void a(Canvas canvas) {
        d(canvas);
    }

    public Canvas l() {
        return this.h;
    }

    public float m() {
        if (x && y) {
            return Math.max(s, t);
        }
        if (x) {
            return s;
        }
        if (y) {
            return t;
        }
        return 0.0f;
    }

    public void a(boolean z) {
        this.H = z;
    }

    public boolean b() {
        return this.H;
    }

    public int i() {
        return this.I;
    }

    public int j() {
        return this.J;
    }
}
