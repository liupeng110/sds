package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.graphics.Matrix;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* CarouselItemView */
public class a extends FrameLayout implements Comparable<a> {
    private ImageView a;
    private int b;
    private float c;
    private float d;
    private float e;
    private float f;
    private boolean g;
    private Matrix h = new Matrix();

    public /* synthetic */ int compareTo(Object obj) {
        return a((a) obj);
    }

    public a(Context context) {
        super(context);
    }

    public void setIndex(int i) {
        this.b = i;
    }

    public int getIndex() {
        return this.b;
    }

    public void setCurrentAngle(float f) {
        this.c = f;
    }

    public float getCurrentAngle() {
        return this.c;
    }

    public int a(a aVar) {
        return (int) (aVar.f - this.f);
    }

    public void setItemX(float f) {
        this.d = f;
    }

    public float getItemX() {
        return this.d;
    }

    public void setItemY(float f) {
        this.e = f;
    }

    public float getItemY() {
        return this.e;
    }

    public void setItemZ(float f) {
        this.f = f;
    }

    public float getItemZ() {
        return this.f;
    }

    public void setDrawn(boolean z) {
        this.g = z;
    }

    public boolean a() {
        return this.g;
    }

    public ImageView getImageView() {
        return this.a;
    }

    public void setImageView(ImageView imageView) {
        this.a = imageView;
    }

    Matrix getCarouseItemMatrix() {
        return this.h;
    }

    void setCarouseItemMatrix(Matrix matrix) {
        this.h = matrix;
    }
}
