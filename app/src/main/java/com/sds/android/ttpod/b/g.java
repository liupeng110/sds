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
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* FavoriteUtils */
public class g {
    private static AnimationSet a = null;

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
        imageView2.setImageResource(R.drawable.img_favorite_large);
        if (a == null) {
            a = (AnimationSet) AnimationUtils.loadAnimation(c, R.anim.scale_in_out_addfavorite);
        } else if (j.a()) {
            a.cancel();
        }
        a.setAnimationListener(new AnimationListener() {
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
                        viewGroup.removeView(imageView2);
                    }
                });
            }
        });
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        viewGroup.addView(imageView2, layoutParams);
        imageView2.setAnimation(a);
        a.start();
    }

    public static void a(MediaItem mediaItem, boolean z) {
        if (z) {
            a();
        }
        f.a(BaseApplication.e().getString(R.string.collect_favorite));
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.ADD_FAVORITE_MEDIA_ITEM, mediaItem));
    }

    public static void b(MediaItem mediaItem, boolean z) {
        if (a != null && j.a()) {
            a.cancel();
        }
        f.a(BaseApplication.e().getString(R.string.favorite_canceled));
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.DELETE_FAVORITE_MEDIA_ITEM, mediaItem, Boolean.FALSE));
    }
}
