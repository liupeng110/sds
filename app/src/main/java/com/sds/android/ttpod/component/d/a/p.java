package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import com.sds.android.ttpod.common.a.a;

/* ScrollableDialog */
public abstract class p extends a {
    protected abstract View a(Context context);

    public p(Context context) {
        super(context);
    }

    protected final View a(Context context, ViewGroup viewGroup) {
        View scrollView = new ScrollView(context);
        scrollView.setLayoutParams(new LayoutParams(-1, -1));
        scrollView.addView(a(context));
        return scrollView;
    }
}
