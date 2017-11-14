package com.sds.android.ttpod.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.util.List;

public abstract class BaseSlidingTabFragment extends SlidingClosableFragment implements OnPageChangeListener {
    public static final int BLUR_RADIUS = 60;
    public static final float DEFAULT_RATIO = 2.13f;
    public static final int SINGER_AVATAR_WIDTH = 64;
    protected int mCurrentItem;
    protected View mHeaderView;
    protected e mPagerAdapter;
    protected View mRootView;
    protected SlidingTabHost mSlidingTabHost;
    protected ViewPager mViewPager;

    public interface a {
        void a(int i);
    }

    protected abstract List<com.sds.android.ttpod.adapter.e.a> buildFragmentBinders();

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_base_sliding_tab, viewGroup, false);
            this.mHeaderView = this.mRootView.findViewById(R.id.header_layout);
            this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.slidingtabhost);
            this.mSlidingTabHost.setTabLayoutAverageSpace(true);
            this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
            this.mPagerAdapter = new e(getActivity(), getChildFragmentManager(), buildFragmentBinders());
            onConfigHeader((ViewGroup) this.mHeaderView);
            this.mViewPager.setAdapter(this.mPagerAdapter);
            this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
            this.mSlidingTabHost.setViewPager(this.mViewPager);
            this.mSlidingTabHost.setOnPageChangeListener(this);
            setCurrentPosition(0);
        }
        return this.mRootView;
    }

    private void setCurrentPosition(int i) {
        int i2 = 0;
        if (isSlidingAtTheLeftEdge(i)) {
            i2 = 2;
        } else if (isSlidingAtTheRightEdge(i)) {
            i2 = 1;
        }
        setSlidingCloseMode(i2);
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mPagerAdapter.getCount() + -1;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.mCurrentItem = i;
        setCurrentPosition(i);
    }

    public Fragment currentFragment() {
        return this.mPagerAdapter.getItem(this.mViewPager.getCurrentItem());
    }

    public void onPageScrollStateChanged(int i) {
    }

    protected void onConfigHeader(ViewGroup viewGroup) {
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mSlidingTabHost != null) {
            c.a(this.mSlidingTabHost, ThemeElement.SUB_BAR_BACKGROUND);
            this.mSlidingTabHost.setTextColor(c.c(ThemeElement.SUB_BAR_TEXT));
            this.mSlidingTabHost.setIndicatorDrawable(c.a(ThemeElement.SUB_BAR_INDICATOR));
        }
    }

    public void onDestroyView() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.a();
        }
        super.onDestroyView();
    }
}
