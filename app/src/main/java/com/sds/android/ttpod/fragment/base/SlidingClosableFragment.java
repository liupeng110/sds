package com.sds.android.ttpod.fragment.base;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.a;
import com.sds.android.ttpod.widget.SlidingClosableRelativeLayout.c;
import java.lang.reflect.Method;
import java.util.Map;

public abstract class SlidingClosableFragment extends ActionBarFragment {
    private static Boolean mIsShowSlidingGuideEnabled = null;
    private SlidingClosableRelativeLayout mSlidingClosableRelativeLayout;

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSlidingClosableRelativeLayout = new SlidingClosableRelativeLayout(getActivity());
        this.mSlidingClosableRelativeLayout.setSlidingCloseMode(3);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(new a(this) {
            final /* synthetic */ SlidingClosableFragment a;

            {
                this.a = r1;
            }

            public void a() {
                if (this.a.mSlidingClosableRelativeLayout != null) {
                    this.a.mSlidingClosableRelativeLayout.setVisibility(8);
                }
                this.a.onSlidingClosed();
            }
        });
        this.mSlidingClosableRelativeLayout.setOnSlidingScrollListener(new c(this) {
            final /* synthetic */ SlidingClosableFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.showPreviousFragment();
            }

            public void b() {
                this.a.hidePreviousFragment();
            }
        });
        this.mSlidingClosableRelativeLayout.addView(super.onCreateView(layoutInflater, this.mSlidingClosableRelativeLayout, bundle));
        return this.mSlidingClosableRelativeLayout;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_BACKGROUND, i.a(getClass(), "updateBackground", Drawable.class));
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        updateBackground(v.a());
    }

    public void updateBackground(Drawable drawable) {
        if (drawable == null) {
            g.c("SlidingClosableFragment", "SlidingClosableFragment.updateBackground background is null");
        } else {
            com.sds.android.ttpod.framework.modules.theme.c.b(getRootView(), drawable);
        }
    }

    public void setSlidingCloseMode(int i) {
        if (this.mSlidingClosableRelativeLayout != null) {
            this.mSlidingClosableRelativeLayout.setSlidingCloseMode(i);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mSlidingClosableRelativeLayout.setVisibility(8);
        this.mSlidingClosableRelativeLayout.setOnSlidingCloseListener(null);
        this.mSlidingClosableRelativeLayout.setOnSlidingScrollListener(null);
        this.mSlidingClosableRelativeLayout = null;
    }

    protected void onSlidingClosed() {
        super.finish();
    }

    public final void finish() {
        if (this.mSlidingClosableRelativeLayout != null) {
            setSlidingCloseMode(2);
            this.mSlidingClosableRelativeLayout.a(true);
            return;
        }
        super.finish();
    }

    public SlidingClosableRelativeLayout getSlidingContainer() {
        return this.mSlidingClosableRelativeLayout;
    }
}
