package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import com.sds.android.ttpod.R;

public class SlidingGuideFragment extends BaseGuideFragment {
    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_INTERVAL = 3000;
    private static final float ANIMATION_LENGTH = 0.5f;
    private Handler mAnimationHandler = new Handler();
    private Runnable mAnimationRepeatRunnable = new Runnable(this) {
        final /* synthetic */ SlidingGuideFragment a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.mHandView.startAnimation(this.a.createAnimation());
            this.a.mAnimationHandler.postDelayed(this, 3000);
        }
    };
    private View mHandView;

    public void onResume() {
        super.onResume();
        this.mAnimationHandler.removeCallbacksAndMessages(null);
        this.mAnimationHandler.postDelayed(this.mAnimationRepeatRunnable, 1000);
    }

    public void onPause() {
        super.onPause();
        this.mAnimationHandler.removeCallbacksAndMessages(null);
    }

    private Animation createAnimation() {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 2, 0.5f, 0, 0.0f, 0, 0.0f);
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setSlidingCloseMode(2);
        setSlidingEnableScrollingMask(false);
        View inflate = layoutInflater.inflate(R.layout.sliding_ui_guide, viewGroup, false);
        this.mHandView = inflate.findViewById(R.id.image_view_hand);
        return inflate;
    }
}
