package com.sds.android.ttpod.component.scaleimage;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/* RotateBitmap */
public class c {
    private Bitmap a;
    private int b = 0;

    public c(Bitmap bitmap) {
        this.a = bitmap;
    }

    public void a(int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public Bitmap b() {
        return this.a;
    }

    public void a(Bitmap bitmap) {
        this.a = bitmap;
    }

    public Matrix c() {
        Matrix matrix = new Matrix();
        if (this.b != 0) {
            matrix.preTranslate((float) (-(this.a.getWidth() / 2)), (float) (-(this.a.getHeight() / 2)));
            matrix.postRotate((float) this.b);
            matrix.postTranslate((float) (f() / 2), (float) (e() / 2));
        }
        return matrix;
    }

    public boolean d() {
        return (this.b / 90) % 2 != 0;
    }

    public int e() {
        if (d()) {
            return this.a.getWidth();
        }
        return this.a.getHeight();
    }

    public int f() {
        if (d()) {
            return this.a.getHeight();
        }
        return this.a.getWidth();
    }
}
