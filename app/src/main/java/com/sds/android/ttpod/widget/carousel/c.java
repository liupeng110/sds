package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.view.animation.AnimationUtils;

/* Rotator */
public class c {
    private int a;
    private float b;
    private float c;
    private long d;
    private long e;
    private float f;
    private boolean g = true;
    private float h = 0.05f;
    private float i;
    private final float j = 120.0f;

    public c(Context context) {
    }

    public final boolean a() {
        return this.g;
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final float b() {
        return this.c;
    }

    public boolean c() {
        if (this.g) {
            return false;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis() - this.d;
        if (currentAnimationTimeMillis < this.e) {
            float f;
            switch (this.a) {
                case 0:
                    f = ((float) currentAnimationTimeMillis) / ((float) this.e);
                    this.c = ((float) Math.round(f * this.f)) + this.b;
                    break;
                case 1:
                    f = ((float) currentAnimationTimeMillis) / 1000.0f;
                    if (this.i < 0.0f) {
                        f = ((this.h * this.i) * f) - ((f * (120.0f * f)) / 2.0f);
                    } else {
                        f = (((-this.h) * this.i) * f) - ((f * (120.0f * f)) / 2.0f);
                    }
                    this.c = this.b - (((float) Math.round(f)) * Math.signum(this.i));
                    break;
            }
            return true;
        }
        this.g = true;
        return false;
    }

    public void a(float f, float f2, int i) {
        this.a = 0;
        this.g = false;
        this.e = (long) i;
        this.d = AnimationUtils.currentAnimationTimeMillis();
        this.b = f;
        this.f = f2;
    }
}
