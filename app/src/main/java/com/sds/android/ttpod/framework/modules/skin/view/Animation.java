package com.sds.android.ttpod.framework.modules.skin.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class Animation extends MaskImageView {
    private boolean a;
    private boolean b;
    private AnimationDrawable c;
    private Drawable d;
    private boolean e;

    public Animation(Context context) {
        super(context);
    }

    public Animation(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Animation(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setIgnoreFocusChanged(boolean z) {
        this.e = z;
    }

    public void a() {
        this.b = true;
        c();
    }

    public void b() {
        this.b = false;
        d();
    }

    public void setImageDrawable(Drawable drawable) {
        if (this.d != drawable) {
            this.d = drawable;
            if (!this.b) {
                super.setImageDrawable(drawable);
            }
        }
    }

    public void setAnimationDrawable(AnimationDrawable animationDrawable) {
        if (this.c != animationDrawable) {
            this.c = animationDrawable;
            if (this.b) {
                c();
            }
        }
    }

    public void setAnimationResource(int i) {
        Drawable drawable = getResources().getDrawable(i);
        if (drawable instanceof AnimationDrawable) {
            setAnimationDrawable((AnimationDrawable) drawable);
        }
    }

    private void c() {
        post(new Runnable(this) {
            final /* synthetic */ Animation a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.c != null) {
                    if (this.a.getDrawable() != this.a.c) {
                        super.setImageDrawable(this.a.c);
                        this.a.a = this.a.c.isRunning();
                    }
                    if (!this.a.a) {
                        this.a.c.start();
                        this.a.a = true;
                    }
                }
            }
        });
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.e) {
            a(z);
        }
    }

    private void d() {
        post(new Runnable(this) {
            final /* synthetic */ Animation a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.getDrawable() == this.a.c) {
                    if (this.a.c != null) {
                        this.a.c.stop();
                    }
                    if (this.a.d != null) {
                        super.setImageDrawable(this.a.d);
                    }
                    this.a.a = false;
                }
            }
        });
    }

    private void a(boolean z) {
        if (!z) {
            d();
        } else if (this.b) {
            c();
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        a(z);
    }
}
