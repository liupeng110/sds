package com.sds.android.ttpod.component.landscape.temporary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;

public class AudioFrequencyShowView extends View {
    private float a;
    private int b;
    private int c;
    private int[] d;
    private int e;
    private int f;
    private int g;
    private a h;
    private boolean i;
    private Handler j;

    public interface a {
        int[] a();
    }

    public AudioFrequencyShowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AudioFrequencyShowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 512;
        this.g = 32;
        this.j = new Handler(this) {
            final /* synthetic */ AudioFrequencyShowView a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 12:
                        if (this.a.h != null) {
                            this.a.d = this.a.h.a();
                            this.a.invalidate();
                        }
                        if (this.a.i) {
                            this.a.j.sendEmptyMessageDelayed(12, 40);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void setAudioFrequencyProvider(a aVar) {
        this.h = aVar;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j.removeMessages(12);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.b = i;
        this.c = i2;
        this.a = ((float) this.c) / 100.0f;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        if (this.d != null) {
            int i;
            paint.setColor(-16711936);
            this.e = this.d.length >= this.e ? this.e : this.d.length;
            this.f = this.f < this.e ? this.f : this.e - 1;
            if (this.f + this.g <= this.e) {
                i = this.g;
            } else {
                i = this.e - this.f;
            }
            this.g = i;
            float f = ((float) this.b) / ((float) this.e);
            float f2 = (float) this.c;
            int i2 = 0;
            int i3 = 0;
            float f3 = 0.0f;
            while (i2 < this.e) {
                int i4;
                float f4 = f3 + f;
                canvas.drawRect(f3, f2 - (((float) this.d[i2]) * this.a), f4, f2, paint);
                if (i2 < this.f || i2 >= this.f + this.g) {
                    i4 = i3;
                } else {
                    i4 = i3 + this.d[i2];
                }
                i2++;
                i3 = i4;
                f3 = f4;
            }
            paint.setColor(SupportMenu.CATEGORY_MASK);
            float f5 = f2 - ((((float) i3) * this.a) / ((float) this.g));
            canvas.drawLine(((float) this.f) * f, f5, ((float) (this.f + this.g)) * f, f5, paint);
        }
    }
}
