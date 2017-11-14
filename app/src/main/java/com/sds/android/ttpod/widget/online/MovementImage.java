package com.sds.android.ttpod.widget.online;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import com.sds.android.ttpod.widget.RectangleImageView;

public class MovementImage extends RectangleImageView {
    private int a;
    private Bitmap b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private a j;
    private Matrix k;
    private boolean l;
    private Paint m;
    private Handler n;

    private enum a {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }

    static /* synthetic */ int b(MovementImage movementImage, int i) {
        int i2 = movementImage.g + i;
        movementImage.g = i2;
        return i2;
    }

    public void setMoveSpeed(int i) {
        this.a = i;
    }

    private int b(int i, int i2) {
        if (i2 <= 0) {
            return 0;
        }
        if (this.a + i >= i2) {
            return i2 - i;
        }
        return this.a;
    }

    public MovementImage(Context context) {
        this(context, null);
    }

    public MovementImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MovementImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 3;
        this.g = 0;
        this.j = a.UP;
        this.n = new Handler(this) {
            final /* synthetic */ MovementImage a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                int a;
                int i;
                switch (this.a.j) {
                    case DOWN:
                        a = this.a.b(this.a.g, this.a.h);
                        if (this.a.g >= this.a.h) {
                            this.a.j = a.RIGHT;
                            this.a.g = 0;
                        }
                        MovementImage.b(this.a, a);
                        i = 0;
                        break;
                    case UP:
                        a = this.a.b(this.a.g, this.a.h);
                        if (this.a.g >= this.a.h) {
                            this.a.j = a.LEFT;
                            this.a.g = 0;
                        }
                        MovementImage.b(this.a, a);
                        a = -a;
                        i = 0;
                        break;
                    case LEFT:
                        a = this.a.b(this.a.g, this.a.i);
                        if (this.a.g >= this.a.i) {
                            this.a.j = a.DOWN;
                            this.a.g = 0;
                        }
                        MovementImage.b(this.a, a);
                        i = -a;
                        a = 0;
                        break;
                    case RIGHT:
                        a = this.a.b(this.a.g, this.a.i);
                        if (this.a.g >= this.a.i) {
                            this.a.j = a.UP;
                            this.a.g = 0;
                        }
                        MovementImage.b(this.a, a);
                        i = a;
                        a = 0;
                        break;
                    default:
                        a = 0;
                        i = 0;
                        break;
                }
                this.a.k.postTranslate((float) i, (float) a);
                this.a.invalidate();
                this.a.n.sendEmptyMessageDelayed(0, 50);
            }
        };
        this.k = new Matrix();
        this.m = new Paint();
    }

    public void setAlpha(int i) {
        this.m.setAlpha(i);
    }

    public void setMoveMentBitmap(Bitmap bitmap) {
        this.b = bitmap;
        if (bitmap != null) {
            this.e = bitmap.getWidth();
            this.f = bitmap.getHeight();
        }
    }

    public void a(int i, int i2) {
        int i3 = 0;
        this.c = i;
        this.d = i2;
        this.h = this.f > i2 ? this.f - i2 : 0;
        if (this.e > i) {
            i3 = this.e - i;
        }
        this.i = i3;
        if (i > this.e && this.e > 0) {
            float f = ((float) i) / ((float) this.e);
            this.k.setScale(f, f);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.b != null) {
            canvas.drawBitmap(this.b, this.k, this.m);
        }
    }

    private boolean c() {
        return this.c < this.e || this.d < this.f;
    }

    public void a() {
        if (c() && !this.l) {
            this.l = true;
            this.n.sendEmptyMessage(0);
        }
    }

    public void b() {
        if (this.l) {
            this.l = false;
            this.n.removeMessages(0);
        }
    }
}
