package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.sds.android.ttpod.common.c.a;
import java.util.Timer;
import java.util.TimerTask;

public class SignalView extends View {
    private boolean a;
    private final int b = 2;
    private int c = Color.parseColor("#7ccded");
    private int d = Color.parseColor("#F5FDFD");
    private final int e = 4;
    private int f;
    private int[] g;
    private int h;
    private int i;
    private int j;
    private TimerTask k;
    private Timer l;

    public void setLoop(boolean z) {
        this.a = z;
    }

    public SignalView(Context context) {
        super(context);
        a(context);
    }

    public SignalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SignalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a() {
        int i = this.h / 3;
        for (int i2 = 0; i2 < 4; i2++) {
            this.g[i2] = -(i * i2);
        }
    }

    protected void onDetachedFromWindow() {
        if (this.l != null) {
            this.l.cancel();
        }
        super.onDetachedFromWindow();
    }

    private void a(Context context) {
        this.f = a.a(60);
        this.h = 20;
        this.g = new int[4];
        a();
        this.a = true;
        b();
    }

    private void b() {
        this.k = new TimerTask(this) {
            final /* synthetic */ SignalView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.a) {
                    this.a.postInvalidate();
                }
            }
        };
        this.l = new Timer();
        this.l.schedule(this.k, 50, 50);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int min = Math.min(width, height) >> 1;
        this.j = height >> 1;
        this.i = width >> 1;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(2.0f);
        paint.setColor(this.c);
        min = (min - this.f) / this.h;
        for (height = 0; height < 4; height++) {
            a(canvas, paint, height, min);
        }
        if (this.g[3] > this.h) {
            a();
            this.l.cancel();
            b();
        }
    }

    private void a(Canvas canvas, Paint paint, int i, int i2) {
        int i3 = this.g[i];
        if (i3 <= this.h) {
            if (i3 > 0) {
                int i4 = (i2 * i3) + this.f;
                int i5 = this.h >> 1;
                if (i3 <= i5) {
                    i3 = (int) ((((float) i3) * 255.0f) / ((float) i5));
                } else {
                    i3 = (int) ((((float) (this.h - i3)) * 255.0f) / ((float) i5));
                }
                paint.setAlpha(i3);
                canvas.drawCircle((float) this.i, (float) this.j, (float) i4, paint);
            }
            int[] iArr = this.g;
            iArr[i] = iArr[i] + 1;
        }
    }
}
