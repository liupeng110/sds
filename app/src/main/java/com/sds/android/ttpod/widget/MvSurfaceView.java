package com.sds.android.ttpod.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class MvSurfaceView extends SurfaceView {
    private a a;
    private int b;
    private int c;

    public interface a {
        void a(int i, int i2);
    }

    public MvSurfaceView(Context context) {
        super(context);
    }

    public MvSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MvSurfaceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public MvSurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.b, i);
        int defaultSize2 = getDefaultSize(this.c, i2);
        if (this.b > 0 && this.c > 0) {
            if (this.b * defaultSize2 > this.c * defaultSize) {
                defaultSize2 = (this.c * defaultSize) / this.b;
            } else if (this.b * defaultSize2 < this.c * defaultSize) {
                defaultSize = (this.b * defaultSize2) / this.c;
            }
        }
        if (this.a != null) {
            this.a.a(defaultSize, defaultSize2);
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    public void a(int i, int i2) {
        this.b = i;
        this.c = i2;
        if (this.b != 0 && this.c != 0) {
            requestLayout();
        }
    }

    public void setOnSizeChanged(a aVar) {
        this.a = aVar;
    }
}
