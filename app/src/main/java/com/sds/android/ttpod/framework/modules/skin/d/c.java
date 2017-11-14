package com.sds.android.ttpod.framework.modules.skin.d;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;

/* ColorDrawableCreator */
public class c extends e {
    private int[] a;
    private Orientation b;

    public c(int[] iArr, int i) {
        this.a = iArr;
        a(i);
    }

    public Drawable a(Resources resources) {
        if (this.b == null || this.a.length <= 1) {
            return new ColorDrawable(this.a[0]);
        }
        return new GradientDrawable(this.b, this.a);
    }

    public void a(int i) {
        switch (i) {
            case 0:
                this.b = Orientation.LEFT_RIGHT;
                return;
            case 1:
                this.b = Orientation.TOP_BOTTOM;
                return;
            case 2:
                this.b = Orientation.RIGHT_LEFT;
                return;
            case 3:
                this.b = Orientation.BOTTOM_TOP;
                return;
            case 4:
                this.b = Orientation.TL_BR;
                return;
            case 5:
                this.b = Orientation.BL_TR;
                return;
            case 6:
                this.b = Orientation.TR_BL;
                return;
            case 7:
                this.b = Orientation.BR_TL;
                return;
            default:
                this.b = null;
                return;
        }
    }
}
