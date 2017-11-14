package com.sds.android.ttpod.fragment;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.sds.android.ttpod.R;

public class PortraitMVGuideFragment extends MVGuideFragment {
    public PortraitMVGuideFragment(RectF rectF, int i) {
        super(rectF, i);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateContentView(layoutInflater, viewGroup, bundle);
        View relativeLayout = new RelativeLayout(getActivity());
        RelativeLayout relativeLayout2 = (RelativeLayout) layoutInflater.inflate(R.layout.layout_portrait_mv_guide, null, false);
        relativeLayout.addView(this.mGuideView, new LayoutParams(-1, -1));
        relativeLayout.addView(relativeLayout2);
        return relativeLayout;
    }
}
