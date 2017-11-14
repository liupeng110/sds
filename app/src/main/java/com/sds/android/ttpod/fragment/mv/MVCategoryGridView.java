package com.sds.android.ttpod.fragment.mv;

import android.content.Context;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class MVCategoryGridView extends GridView {
    public MVCategoryGridView(Context context) {
        super(context);
    }

    public MVCategoryGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MVCategoryGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, ExploreByTouchHelper.INVALID_ID));
    }
}
