package com.sds.android.ttpod.b;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.a;
import com.sds.android.ttpod.framework.base.a.b;

/* EffectPickUtils */
public class e {
    public static void a() {
        Context c = a.a().c();
        final ViewGroup viewGroup = (ViewGroup) c.findViewById(16908290);
        ImageView imageView = (ImageView) viewGroup.findViewWithTag(g.class);
        if (imageView != null) {
            imageView.clearAnimation();
            viewGroup.removeView(imageView);
        }
        final View imageView2 = new ImageView(c);
        imageView2.setTag(g.class);
        imageView2.setImageResource(R.drawable.img_effect_recommand_pickcount_large);
        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(c, R.anim.scale_in_out_addfavorite);
        animationSet.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                viewGroup.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (viewGroup != null) {
                            viewGroup.removeView(imageView2);
                        }
                    }
                });
            }
        });
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        viewGroup.addView(imageView2, layoutParams);
        imageView2.setAnimation(animationSet);
        animationSet.start();
    }

    public static void a(String str) {
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.PICK_EFFECT, str));
    }
}
