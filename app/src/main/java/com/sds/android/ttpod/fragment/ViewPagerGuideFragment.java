package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import com.sds.android.ttpod.R;

public class ViewPagerGuideFragment extends BaseGuideFragment {
    private static final int ANIMATION_DURATION = 1000;
    private static final int ANIMATION_INTERVAL = 5000;
    private static final float ANIMATION_LENGTH = 0.2f;
    private Handler mAnimationHandler = new Handler();
    private Runnable mAnimationRepeatRunnable = new Runnable(this) {
        final /* synthetic */ ViewPagerGuideFragment a;

        {
            this.a = r1;
        }

        public void run() {
            Animation access$000 = this.a.createAnimation();
            if (this.a.mHandView != null) {
                this.a.mHandView.startAnimation(access$000);
            }
            this.a.mAnimationHandler.postDelayed(this, 5000);
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
        Animation animationSet = new AnimationSet(true);
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 2, 0.2f, 0, 0.0f, 0, 0.0f);
        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);
        translateAnimation = new TranslateAnimation(1, 0.0f, 2, -0.4f, 0, 0.0f, 0, 0.0f);
        translateAnimation.setStartOffset(1000);
        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);
        translateAnimation = new TranslateAnimation(1, 0.0f, 2, 0.2f, 0, 0.0f, 0, 0.0f);
        translateAnimation.setStartOffset(2000);
        translateAnimation.setDuration(1000);
        animationSet.addAnimation(translateAnimation);
        return animationSet;
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setSlidingCloseMode(3);
        setSlidingEnableScrollingMask(false);
        View inflate = layoutInflater.inflate(R.layout.view_pager_ui_guide, viewGroup, false);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ViewPagerGuideFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                FragmentManager fragmentManager = this.a.getFragmentManager();
                if (fragmentManager != null) {
                    fragmentManager.beginTransaction().remove(this.a).commitAllowingStateLoss();
                }
            }
        });
        this.mHandView = inflate.findViewById(R.id.image_view_hand);
        return inflate;
    }
}
