package com.sds.android.ttpod.activities.base;

import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.widget.FreezableViewPager;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.h;
import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPagerActivity extends SlidingClosableActivity implements OnPageChangeListener {
    protected int mCurrentPage = 0;
    protected e mPagerAdapter;
    protected FreezableViewPager mPagerContent;
    protected SlidingTabHost mPagerTitle;
    private h mTTViewPagerListener;

    protected abstract void onBuildActionBar(a aVar);

    protected abstract void onBuildFragmentBinderList(List<e.a> list);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_common_sliding_pager);
        this.mPagerTitle = (SlidingTabHost) findViewById(R.id.sliding_pager_title);
        this.mPagerContent = (FreezableViewPager) findViewById(R.id.sliding_pager_content);
        this.mPagerAdapter = new e(this, getSupportFragmentManager(), buildFragmentBinders());
        this.mPagerContent.setAdapter(this.mPagerAdapter);
        this.mPagerTitle.setTabLayoutAverageSpace(true);
        this.mPagerTitle.setViewPager(this.mPagerContent);
        onBuildActionBar(getActionBarController());
        this.mPagerTitle.setOnPageChangeListener(this);
        setCurrentPosition(0);
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

    private List<e.a> buildFragmentBinders() {
        List<e.a> arrayList = new ArrayList();
        onBuildFragmentBinderList(arrayList);
        return arrayList;
    }

    public void setTTViewPagerListener(h hVar) {
        this.mTTViewPagerListener = hVar;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (this.mTTViewPagerListener != null) {
            this.mTTViewPagerListener.onPageSelected(i);
        }
        setCurrentPosition(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    protected void onDestroy() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.a();
        }
        super.onDestroy();
    }
}
