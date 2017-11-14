package com.sds.android.ttpod.component.landscape;

import android.content.Context;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import com.sds.android.ttpod.framework.modules.skin.view.LyricView;

public class LandscapeLyricShow extends LyricView {
    public LandscapeLyricShow(Context context) {
        super(context);
        j();
    }

    public LandscapeLyricShow(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j();
    }

    public LandscapeLyricShow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        j();
    }

    private void j() {
        setColorNormal(-1);
        setColorHighlight(-14434053);
        setTextSizeHighlight(18.0f);
        setTextSizeNormal(18.0f);
        setAlign(Align.LEFT);
        setEnabled(true);
    }
}
