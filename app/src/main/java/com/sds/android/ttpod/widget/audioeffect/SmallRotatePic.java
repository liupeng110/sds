package com.sds.android.ttpod.widget.audioeffect;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;

public class SmallRotatePic extends RotatePic {
    public SmallRotatePic(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        this.b = a.a(15) / 3;
        this.c = a.a(6) / 3;
        this.d = R.drawable.effect_circle_green_small;
        this.a = 5;
    }
}
