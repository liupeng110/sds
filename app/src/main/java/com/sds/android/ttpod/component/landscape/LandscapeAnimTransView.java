package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.skin.view.d;

public class LandscapeAnimTransView extends AnimTransView {
    public LandscapeAnimTransView(Context context) {
        super(context);
        setDefaultImageResource(R.raw.landscape_default);
    }

    public LandscapeAnimTransView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDefaultImageResource(R.raw.landscape_default);
    }

    public LandscapeAnimTransView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setDefaultImageResource(R.raw.landscape_default);
    }

    protected void a() {
        Animation dVar = new d(-90.0f, 0.0f, 0.0f, false);
        dVar.setDuration(500);
        dVar.setFillAfter(true);
        dVar.setInterpolator(new DecelerateInterpolator());
        setInAnimation(dVar);
        dVar = new d(0.0f, 90.0f, 0.0f, false);
        dVar.setDuration(500);
        dVar.setFillAfter(true);
        dVar.setInterpolator(new DecelerateInterpolator());
        setOutAnimation(dVar);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        setReflectionHeight(i2 / 3);
        super.onSizeChanged(i, i2, i3, i4);
    }
}
