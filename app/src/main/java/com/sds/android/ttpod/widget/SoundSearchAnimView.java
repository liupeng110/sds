package com.sds.android.ttpod.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.alibaba.wireless.security.SecExceptionCode;
import com.sds.android.ttpod.common.c.a;

public class SoundSearchAnimView extends View {
    private static final int[] f = new int[]{0, 120, 240, 90, 210, 330, 10, TransportMediator.KEYCODE_MEDIA_RECORD, 250};
    private int a;
    private Paint b;
    private int c;
    private int d;
    private int e;
    private double g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;

    public SoundSearchAnimView(Context context) {
        this(context, null);
    }

    public SoundSearchAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 5;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.h = 100;
        this.i = 100;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.b = new Paint();
        this.b.setAntiAlias(true);
    }

    public void setVolume(double d) {
        this.g = d;
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        setMeasuredDimension(size, size2);
        this.k = size >> 1;
        this.l = size2 >> 1;
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int i2 = 0;
        this.c++;
        if (this.c >= 360) {
            this.c -= 360;
        }
        for (i = 0; i < 3; i++) {
            a(canvas, this.k, this.l, 85, this.c + f[i], 80, 20);
        }
        this.d += 2;
        if (this.d >= 360) {
            this.d -= 360;
        }
        for (i = 0; i < 3; i++) {
            a(canvas, this.k, this.l, 95, this.d + f[i + 3], 110, 15);
        }
        this.j++;
        if (this.j >= 4) {
            this.i = ((int) (this.g * 150.0d)) + 100;
            this.a = (this.i - this.h) / 4;
            this.j -= 4;
        } else {
            this.h += this.a;
        }
        if (this.h < 100) {
            this.h = 100;
        }
        if (this.h > SecExceptionCode.SEC_ERROR_STA_ENC) {
            this.h = SecExceptionCode.SEC_ERROR_STA_ENC;
        }
        this.e += 3;
        if (this.e >= 360) {
            this.e -= 360;
        }
        while (i2 < 3) {
            a(canvas, this.k, this.l, this.h + (i2 * 40), this.e + f[i2 + 6], 60, 30);
            i2++;
        }
    }

    private void a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6) {
        this.b.setColor(Color.argb(i6, 135, SecExceptionCode.SEC_ERROR_STA_STORE_KEY_NOT_EXSITED, 250));
        int a = a.a(i3);
        canvas.drawArc(new RectF((float) (i - a), (float) (i2 - a), (float) (i + a), (float) (a + i2)), (float) i4, (float) i5, true, this.b);
    }
}
