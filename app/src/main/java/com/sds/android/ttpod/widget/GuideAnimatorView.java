package com.sds.android.ttpod.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import java.util.ArrayList;
import java.util.Iterator;

@TargetApi(11)
public class GuideAnimatorView extends View implements AnimatorUpdateListener {
    private Bitmap a;
    private Bitmap[] b;
    private final ArrayList<GuideHolder> c;
    private long d;

    private final class GuideHolder {
        private static final float FLOAT_AMEND_VALUE = 0.5f;
        private static final float MAX_COLOR_VALUE = 255.0f;
        private boolean mAnimationEnabled;
        private Rect mBitmapRect = new Rect(0, 0, this.mBitmaps[0].getWidth(), this.mBitmaps[0].getHeight());
        private Bitmap[] mBitmaps;
        private int mCurIndex;
        private int mHeight;
        private Paint mPaint;
        private int mWidth;
        private int mX;
        private int mY;

        public void setPaint(Paint paint) {
            this.mPaint = paint;
        }

        public Paint getPaint() {
            return this.mPaint;
        }

        public void setX(int i) {
            this.mX = i;
        }

        public int getX() {
            return this.mX;
        }

        public void setY(int i) {
            this.mY = i;
        }

        public int getY() {
            return this.mY;
        }

        public void setAlpha(float f) {
            this.mPaint.setAlpha((int) ((MAX_COLOR_VALUE * f) + 0.5f));
        }

        public int getWidth() {
            return this.mWidth;
        }

        public void setWidth(int i) {
            this.mWidth = i;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public void setHeight(int i) {
            this.mHeight = i;
        }

        public GuideHolder(Bitmap... bitmapArr) {
            this.mBitmaps = bitmapArr;
        }

        public void drawBitmap(Canvas canvas) {
            int i = this.mWidth >> 1;
            int i2 = this.mHeight >> 1;
            if (this.mWidth >= this.mBitmapRect.width()) {
                canvas.drawBitmap(this.mBitmaps[this.mCurIndex], (float) (-i), (float) (-i2), this.mPaint);
            } else {
                canvas.drawBitmap(this.mBitmaps[this.mCurIndex], this.mBitmapRect, new Rect(-i, -i2, i, i2), this.mPaint);
            }
            if (this.mAnimationEnabled) {
                this.mCurIndex++;
                if (this.mCurIndex >= this.mBitmaps.length) {
                    this.mCurIndex = 0;
                }
            }
        }

        public void setAnimationEnabled(boolean z) {
            this.mAnimationEnabled = z;
            if (!z) {
                this.mCurIndex = 0;
            }
        }
    }

    public void setDrawableResourceId(int... iArr) {
        int length;
        int i = 0;
        this.a = null;
        if (iArr != null) {
            length = iArr.length;
        } else {
            length = 0;
        }
        this.b = new Bitmap[length];
        while (i < length) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(iArr[i]);
            if (this.a == null) {
                this.a = bitmapDrawable.getBitmap();
            }
            this.b[i] = bitmapDrawable.getBitmap();
            i++;
        }
    }

    private GuideHolder b(int i, int i2) {
        GuideHolder guideHolder = new GuideHolder(this.b);
        guideHolder.setPaint(new Paint(1));
        guideHolder.setX(i);
        guideHolder.setY(i2);
        return guideHolder;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            GuideHolder guideHolder = (GuideHolder) it.next();
            canvas.save();
            canvas.translate((float) guideHolder.getX(), (float) guideHolder.getY());
            guideHolder.drawBitmap(canvas);
            canvas.restore();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            a((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return false;
    }

    public boolean a(int i, int i2) {
        GuideHolder b = b(i, i2);
        int y = b.getY();
        int height = this.a.getHeight();
        if ((getHeight() - (height >> 1)) - y < height) {
            return false;
        }
        this.c.add(b);
        Animator ofInt = ObjectAnimator.ofInt(b, "width", new int[]{0, this.a.getWidth()});
        ofInt.setDuration(300);
        ofInt.addUpdateListener(this);
        Animator ofInt2 = ObjectAnimator.ofInt(b, "height", new int[]{0, this.a.getHeight()});
        ofInt2.setDuration(300);
        ofInt2.addUpdateListener(this);
        Animator ofFloat = ObjectAnimator.ofFloat(b, "alpha", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(300);
        ofFloat.addUpdateListener(this);
        Animator ofInt3 = ObjectAnimator.ofInt(b, "y", new int[]{y, r3});
        ofInt3.setDuration(500);
        ofInt3.setInterpolator(new AccelerateInterpolator());
        ofInt3.addUpdateListener(this);
        Animator ofInt4 = ObjectAnimator.ofInt(b, "x", new int[]{b.getX(), (-this.a.getWidth()) / 2});
        ofInt4.setDuration(1500);
        ofInt4.setInterpolator(new AnticipateInterpolator());
        ofInt4.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ GuideAnimatorView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                ((GuideHolder) ((ObjectAnimator) animator).getTarget()).setAnimationEnabled(true);
            }

            public void onAnimationEnd(Animator animator) {
                this.a.c.remove(((ObjectAnimator) animator).getTarget());
                this.a.invalidate();
            }
        });
        ofInt4.addUpdateListener(this);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofInt3).after(ofFloat);
        animatorSet.play(ofFloat).with(ofInt);
        animatorSet.play(ofFloat).with(ofInt2);
        animatorSet.play(ofInt4).after(ofInt3);
        animatorSet.start();
        return true;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        long nanoTime = System.nanoTime();
        if ((nanoTime - this.d > 50000000 ? 1 : null) != null) {
            this.d = nanoTime;
            invalidate();
        }
    }
}
