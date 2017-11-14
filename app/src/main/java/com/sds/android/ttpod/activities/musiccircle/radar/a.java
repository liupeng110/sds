package com.sds.android.ttpod.activities.musiccircle.radar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;

/* DrawView */
public class a extends View {
    private final int a;
    private Paint b = new Paint();
    private a c;
    private float d;
    private float e;
    private float f;
    private float g;
    private float h;
    private float i;
    private Timer j;
    private Handler k;

    /* DrawView */
    public interface a {
        void a(int i, int i2, int i3);
    }

    static /* synthetic */ float a(a aVar, double d) {
        float f = (float) (((double) aVar.h) - d);
        aVar.h = f;
        return f;
    }

    static /* synthetic */ float b(a aVar, double d) {
        float f = (float) (((double) aVar.h) + d);
        aVar.h = f;
        return f;
    }

    static /* synthetic */ float c(a aVar, double d) {
        float f = (float) (((double) aVar.i) - d);
        aVar.i = f;
        return f;
    }

    static /* synthetic */ float d(a aVar, double d) {
        float f = (float) (((double) aVar.i) + d);
        aVar.i = f;
        return f;
    }

    public a(Context context, int i, a aVar) {
        super(context);
        this.b.setAntiAlias(true);
        this.b.setColor(-1);
        this.b.setStrokeWidth(3.0f);
        this.j = new Timer();
        this.k = new Handler(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (((double) (this.a.d - this.a.e)) > 1.0E-5d) {
                            if (((double) Math.abs(this.a.h - this.a.e)) > 2.5d) {
                                a.a(this.a, 1.0d);
                            }
                        } else if (((double) Math.abs(this.a.h - this.a.e)) > 2.5d) {
                            a.b(this.a, 1.0d);
                        }
                        if (((double) (this.a.f - this.a.g)) > 1.0E-5d) {
                            if (((double) Math.abs(this.a.i - this.a.g)) > 2.5d) {
                                a.c(this.a, 1.0d);
                            }
                        } else if (((double) Math.abs(this.a.i - this.a.g)) > 2.5d) {
                            a.d(this.a, 1.0d);
                        }
                        this.a.invalidate();
                        return;
                    default:
                        return;
                }
            }
        };
        this.c = aVar;
        this.a = i;
    }

    public void a() {
        this.j.cancel();
        this.k.removeMessages(1);
    }

    public void a(float f, float f2, float f3, float f4) {
        this.d = f;
        this.e = f2;
        this.f = f3;
        this.g = f4;
        this.h = this.d;
        this.i = this.f;
        this.j.schedule(new TimerTask(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.k.sendMessage(this.a.k.obtainMessage(1));
            }
        }, 0, 5);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = this.b;
        canvas.drawLine(this.d, this.f, this.h, this.i, paint);
        if (((double) Math.abs(this.i - this.g)) < 2.5d && ((double) Math.abs(this.h - this.e)) < 2.5d) {
            canvas.drawCircle(this.h, this.i, 3.0f, paint);
            this.j.cancel();
            this.c.a(this.a, (int) this.h, (int) this.i);
        }
    }
}
