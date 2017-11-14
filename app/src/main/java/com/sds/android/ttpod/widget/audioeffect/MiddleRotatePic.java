package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;

public class MiddleRotatePic extends RotatePic {
    public MiddleRotatePic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        this.b = a.a(10);
        this.c = a.a(4);
        this.d = R.drawable.effect_circle_green_middle;
        this.a = 5;
    }
}
