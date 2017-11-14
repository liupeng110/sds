package com.sds.android.ttpod.activities.musiccircle.radar;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* RadarAnimation */
public class b extends Animation {
    private float a;
    private int b;
    private int c;
    private float d;
    private float e;
    private float f;
    private float g;
    private a h;

    /* RadarAnimation */
    public interface a {
        void a(float f);
    }

    public b() {
        this.b = 0;
        this.c = 0;
        this.d = 0.0f;
        this.e = 0.0f;
        this.a = 359.0f;
        this.b = 1;
        this.c = 1;
        this.d = TTFMImageUtils.Middle_Scale;
        this.e = 0.45f;
        setDuration(3000);
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.a * f;
        this.h.a(f2);
        transformation.getMatrix().setRotate(f2, this.f, this.g);
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f = resolveSize(this.b, this.d, i, i3);
        this.g = resolveSize(this.c, this.e, i2, i4);
    }
}
