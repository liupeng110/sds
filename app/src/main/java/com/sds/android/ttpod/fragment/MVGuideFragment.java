package com.sds.android.ttpod.fragment;

import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.sds.android.ttpod.widget.guide.GuideView;

public class MVGuideFragment extends BaseGuideFragment {
    private int mDescriptionImageResId;
    protected GuideView mGuideView;
    private float mHeight;
    private float mWidth;
    private float mX;
    private float mY;

    public MVGuideFragment(RectF rectF, int i) {
        this.mX = rectF.left;
        this.mY = rectF.top;
        this.mWidth = rectF.right - rectF.left;
        this.mHeight = rectF.bottom - rectF.top;
        this.mDescriptionImageResId = i;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setSlidingCloseMode(0);
        setSlidingEnableScrollingMask(false);
        this.mGuideView = new GuideView(getActivity(), this.mX, this.mY, this.mWidth, this.mHeight, this.mDescriptionImageResId);
        this.mGuideView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MVGuideFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismissAllowingStateLoss();
            }
        });
        return this.mGuideView;
    }

    public void onDetach() {
        super.onDetach();
        if (this.mGuideView != null) {
            this.mGuideView.a();
        }
    }
}
