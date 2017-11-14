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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView.ScaleType;
import com.sds.android.ttpod.framework.a.r;
import com.sds.android.ttpod.framework.modules.skin.d.b;
import java.lang.ref.WeakReference;

public class AnimTransView extends MaskImageView {
    private final Rect a = new Rect();
    private Drawable b = null;
    private Drawable c = null;
    private Drawable d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j = 5;
    private int k = -1862270977;
    private int l = ViewCompat.MEASURED_SIZE_MASK;
    private Paint m;
    private Bitmap n;
    private Animation o;
    private Animation p;
    private Animation q;
    private a r = new a(this);

    static class a extends Handler {
        private WeakReference<AnimTransView> a;

        public a(AnimTransView animTransView) {
            this.a = new WeakReference(animTransView);
        }

        public void handleMessage(Message message) {
            AnimTransView animTransView = (AnimTransView) this.a.get();
            if (animTransView != null) {
                Bitmap bitmap = (Bitmap) message.obj;
                animTransView.clearAnimation();
                if (bitmap == null || bitmap.isRecycled()) {
                    animTransView.setImageDrawable(animTransView.c);
                } else {
                    animTransView.setImageBitmap(bitmap);
                }
            }
        }
    }

    public AnimTransView(Context context) {
        super(context);
        a(context);
        g();
    }

    public AnimTransView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        g();
    }

    public AnimTransView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        g();
    }

    private void a(Context context) {
        if (this.c == null) {
            this.c = getDrawable();
        }
        if (this.p == null) {
            this.p = AnimationUtils.loadAnimation(context, 17432576);
        }
        if (this.q == null) {
            this.q = AnimationUtils.loadAnimation(context, 17432577);
        }
    }

    private void b() {
        if (!r.a()) {
            return;
        }
        if (this.i <= 0 || (this.k == 0 && this.l == 0)) {
            this.m = null;
            this.n = null;
            return;
        }
        if (this.m == null) {
            this.m = new Paint();
            this.m.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
        }
        this.m.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, (float) this.i, this.k, this.l, TileMode.CLAMP));
        int i = this.g - this.e;
        int i2 = this.h - this.f;
        if (this.n == null || this.n.getWidth() < i || this.n.getHeight() < i2) {
            c();
            if (i > 0) {
                this.n = Bitmap.createBitmap(i, this.i, Config.ARGB_8888);
            }
        }
    }

    private void c() {
        if (this.n != null) {
            this.n.recycle();
            this.n = null;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        g();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
        this.r.removeCallbacksAndMessages(null);
    }

    private void d() {
        int i = (this.i > 0 ? this.j : 0) + this.i;
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        i = ((getHeight() - getPaddingTop()) - getPaddingBottom()) - i;
        this.e = getPaddingLeft();
        this.f = getPaddingTop();
        this.g = this.e + width;
        this.h = this.f + i;
        if (width > 0 && i > 0) {
            e();
        }
    }

    private void e() {
        Drawable drawable = this.d;
        super.setImageDrawable(null);
        setImageDrawableInner(drawable);
    }

    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(scaleType);
        setWillNotCacheDrawing(false);
        g();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
        g();
    }

    private void f() {
        if (r.a() && this.n != null && this.i > 0) {
            this.n.eraseColor(0);
            Drawable drawable = getDrawable();
            if (drawable != null) {
                Canvas canvas = new Canvas(this.n);
                int i = this.h - this.f;
                canvas.save(1);
                canvas.translate(0.0f, (float) i);
                canvas.scale(1.0f, -1.0f);
                a(canvas, drawable);
                canvas.restore();
                if (this.m != null) {
                    canvas.drawRect(0.0f, 0.0f, (float) (this.g - this.e), (float) this.i, this.m);
                }
            }
        }
    }

    private void g() {
        d();
        b();
        f();
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        e();
    }

    public void setImageDrawable(Drawable drawable) {
        if (drawable == null || ((drawable instanceof BitmapDrawable) && ((BitmapDrawable) drawable).getBitmap() == null)) {
            drawable = this.c;
        }
        if (drawable == this.d) {
            return;
        }
        if (getWidth() <= 0 || getHeight() <= 0) {
            setImageDrawableInner(drawable);
            return;
        }
        Animation animation = getAnimation();
        if (animation == null || animation == this.p || animation == this.q || !animation.hasStarted() || animation.hasEnded()) {
            clearAnimation();
            a();
            this.b = null;
            if (!isShown() || !getGlobalVisibleRect(this.a)) {
                setImageDrawableInner(drawable);
                return;
            } else if (this.d == null) {
                setImageDrawableInner(drawable);
                startAnimation(this.p);
                return;
            } else if (this.q != null) {
                this.b = drawable;
                startAnimation(this.q);
                return;
            } else {
                setImageDrawableInner(drawable);
                startAnimation(this.p);
                return;
            }
        }
        this.d = drawable;
    }

    private void setImageDrawableInner(Drawable drawable) {
        if (drawable == null) {
            this.d = this.c;
        } else if (drawable != this.d) {
            this.d = drawable;
        }
        setScaledDrawable(this.d);
        f();
    }

    private void setScaledDrawable(Drawable drawable) {
        Drawable drawable2;
        if (!(drawable instanceof BitmapDrawable) || (drawable instanceof b)) {
            drawable2 = drawable;
        } else {
            drawable2 = new b(getResources(), ((BitmapDrawable) drawable).getBitmap());
        }
        if (drawable2 instanceof b) {
            ((b) drawable2).a((float) (this.g - this.e), (float) (this.h - this.f));
        }
        super.setImageDrawable(drawable2);
    }

    public void setImageBitmapDelay(Bitmap bitmap) {
        this.r.removeCallbacksAndMessages(null);
        Message obtainMessage = this.r.obtainMessage(0);
        obtainMessage.obj = bitmap;
        this.r.sendMessageDelayed(obtainMessage, bitmap == null ? 600 : 300);
    }

    public void setInAnimation(Animation animation) {
        this.p = animation;
    }

    public void setOutAnimation(Animation animation) {
        this.q = animation;
    }

    public void setInAnimation(int i) {
        setInAnimation(AnimationUtils.loadAnimation(getContext(), i));
    }

    public void setOutAnimation(int i) {
        setOutAnimation(AnimationUtils.loadAnimation(getContext(), i));
    }

    public void startAnimation(Animation animation) {
        if (animation != null) {
            if (animation == this.p || animation == this.q) {
                this.o = animation;
            } else {
                this.o = null;
            }
            super.startAnimation(animation);
        }
    }

    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            canvas.save(1);
            canvas.translate((float) this.e, (float) this.f);
            a(canvas, drawable);
            if (this.i > 0 && this.n != null && r.a()) {
                canvas.translate(0.0f, (float) ((this.h - this.f) + this.j));
                canvas.drawBitmap(this.n, 0.0f, 0.0f, null);
            }
            canvas.restore();
        }
    }

    private void a(Canvas canvas, Drawable drawable) {
        drawable.setBounds(0, 0, this.g - this.e, this.h - this.f);
        drawable.draw(canvas);
    }

    protected void onAnimationEnd() {
        super.onAnimationEnd();
        Drawable drawable = this.b;
        this.b = null;
        if (this.o != null && this.o == this.q) {
            setImageDrawableInner(drawable);
            if (getDrawable() != null && this.p != null) {
                startAnimation(this.p);
            }
        } else if (this.d != getDrawable()) {
            setImageDrawable(this.d);
        }
    }

    public void setDefaultImageDrawable(Drawable drawable) {
        if (drawable != this.c) {
            this.c = drawable;
            if (getDrawable() == null) {
                setImageDrawableInner(null);
            }
        }
    }

    public void setDefaultImageBitmap(Bitmap bitmap) {
        setDefaultImageDrawable(new BitmapDrawable(getContext().getResources(), bitmap));
    }

    public void setDefaultImageResource(int i) {
        setDefaultImageDrawable(getContext().getResources().getDrawable(i));
    }

    public Drawable getDefaultDrawable() {
        return this.c;
    }

    protected void a() {
    }

    public void setReflectionHeight(int i) {
        this.i = i;
        g();
    }

    public void setDivideHeight(int i) {
        this.j = i;
        d();
    }

    public void a(int i, int i2) {
        this.k = i;
        this.l = i2;
        f();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        g();
    }
}
