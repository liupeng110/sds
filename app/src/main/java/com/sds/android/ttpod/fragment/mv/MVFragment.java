package com.sds.android.ttpod.fragment.mv;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.h;
import com.sds.android.ttpod.widget.h.a;
import java.util.ArrayList;
import java.util.List;

public class MVFragment extends SlidingClosableFragment {
    private static final ArrayList<a> MV_LIST = new ArrayList<a>() {
        {
            add(new a("mv_daily", "tt_mv_daily", "mv_daily"));
            add(new a("mv_category", "tt_mv_category", "mv_category"));
        }
    };
    private int mCurrentItem = 0;
    private View mRootView;
    private SlidingTabHost mSlidingTabHost;
    private String mTitle = "";
    private ViewPager mViewPager;

    public MVFragment(String str) {
        this.mTitle = str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage(((a) MV_LIST.get(this.mCurrentItem)).c());
        trackModule(((a) MV_LIST.get(this.mCurrentItem)).b());
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setTitle();
        this.mRootView = layoutInflater.inflate(R.layout.fragment_mv, viewGroup, false);
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.sliding_tabs_host_mv);
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
        bindView();
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        return this.mRootView;
    }

    public void setCurrentPosition(int i) {
        if (i >= 0 && i < 2) {
            this.mCurrentItem = i;
        }
    }

    private void setTitle() {
        if (m.a(this.mTitle)) {
            getActionBarController().b((int) R.string.category_mv_zone);
        } else {
            getActionBarController().a(this.mTitle);
        }
    }

    private void bindView() {
        this.mViewPager.setOffscreenPageLimit(2);
        this.mViewPager.setAdapter(new e(getActivity(), getChildFragmentManager(), buildFragmentBinders()));
        attachSlidingTabHost(this.mSlidingTabHost, this.mViewPager);
    }

    private void attachSlidingTabHost(SlidingTabHost slidingTabHost, ViewPager viewPager) {
        slidingTabHost.setTabLayoutAverageSpace(true);
        slidingTabHost.setViewPager(viewPager);
        slidingTabHost.setOnPageChangeListener(new h(this, this, this.mCurrentItem, MV_LIST) {
            final /* synthetic */ MVFragment a;

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                super.onPageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        v.a(this.mSlidingTabHost);
        c.a(this.mRootView.findViewById(R.id.best_album_background), ThemeElement.BACKGROUND_MASK);
    }

    private List<e.a> buildFragmentBinders() {
        List<e.a> arrayList = new ArrayList();
        arrayList.add(new e.a(0, getString(R.string.daily_hot_mv), 0, new DailyHotMVFragment()));
        arrayList.add(new e.a(0, getString(R.string.mv_category), 0, new MVCategoryFragment()));
        return arrayList;
    }
}
