package com.sds.android.ttpod.framework.modules.skin.view;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* Rotate3dAnimation */
public class d extends Animation {
    private float a;
    private float b;
    private float c;
    private float d;
    private float e;
    private boolean f;
    private Camera g;
    private int h = 0;
    private int i = 0;
    private float j = 0.0f;
    private float k = 0.0f;

    public d(float f, float f2, float f3, float f4, float f5, boolean z) {
        this.a = f;
        this.b = f2;
        this.j = f3;
        this.k = f4;
        this.c = this.j;
        this.d = this.k;
        this.e = f5;
        this.f = z;
    }

    public d(float f, float f2, float f3, boolean z) {
        this.a = f;
        this.b = f2;
        this.h = 1;
        this.i = 1;
        this.j = TTFMImageUtils.Middle_Scale;
        this.k = TTFMImageUtils.Middle_Scale;
        this.e = f3;
        this.f = z;
    }

    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.g = new Camera();
        this.c = resolveSize(this.h, this.j, i, i3);
        this.d = resolveSize(this.i, this.k, i2, i4);
    }

    protected void applyTransformation(float f, Transformation transformation) {
        float f2 = this.a;
        f2 += (this.b - f2) * f;
        float f3 = this.c;
        float f4 = this.d;
        Camera camera = this.g;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if (this.f) {
            camera.translate(0.0f, 0.0f, this.e * f);
        } else {
            camera.translate(0.0f, 0.0f, this.e * (1.0f - f));
        }
        camera.rotateY(f2);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f3, -f4);
        matrix.postTranslate(f3, f4);
    }
}
