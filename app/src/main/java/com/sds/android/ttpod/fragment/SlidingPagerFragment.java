package com.sds.android.ttpod.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.ttpod.R;
import java.util.ArrayList;
import java.util.List;

public class SlidingPagerFragment extends Fragment {
    private static final long DELAY = 4000;
    private static final int DOT_WIDTH = 14;
    private static final int INT_100 = 100;
    private static final int WHAT_AUTO_SCROLL = 1;
    private boolean mAutoScroll = false;
    private List<ImageView> mDotList = new ArrayList();
    private LinearLayout mDotListLayout;
    private List<View> mGalleryList = new ArrayList();
    private Handler mHandler = new Handler(this) {
        final /* synthetic */ SlidingPagerFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            this.a.mViewPager.setCurrentItem(this.a.mViewPager.getCurrentItem() + 1);
            if (this.a.mAutoScroll) {
                this.a.mHandler.removeMessages(1);
                this.a.mHandler.sendEmptyMessageDelayed(1, SlidingPagerFragment.DELAY);
            }
        }
    };
    private int[] mImageIds;
    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener(this) {
        final /* synthetic */ SlidingPagerFragment a;

        {
            this.a = r1;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            int access$300 = i % this.a.mSize;
            for (int i2 = 0; i2 < this.a.mSize; i2++) {
                if (i2 == access$300) {
                    ((ImageView) this.a.mDotList.get(i2)).setImageResource(R.drawable.img_imageview_navigate_dot_focused);
                } else {
                    ((ImageView) this.a.mDotList.get(i2)).setImageResource(R.drawable.img_imageview_navigate_dot_normal);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    };
    private int mSize;
    private ViewPager mViewPager;

    class a extends PagerAdapter {
        final /* synthetic */ SlidingPagerFragment a;

        a(SlidingPagerFragment slidingPagerFragment) {
            this.a = slidingPagerFragment;
        }

        public int getCount() {
            return Entry.MAX_LIMIT;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(View view, int i, Object obj) {
            ((ViewPager) view).removeView((View) this.a.mGalleryList.get(i % this.a.mSize));
        }

        public Object instantiateItem(View view, int i) {
            ((ViewPager) view).addView((View) this.a.mGalleryList.get(i % this.a.mSize), 0);
            return this.a.mGalleryList.get(i % this.a.mSize);
        }
    }

    public SlidingPagerFragment(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("ids can not be null or empty");
        }
        this.mImageIds = iArr;
        this.mSize = iArr.length;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_common_navigate, viewGroup, false);
        initGalleryList();
        this.mDotListLayout = (LinearLayout) inflate.findViewById(R.id.layout_dotlist);
        initDotList();
        this.mViewPager = (ViewPager) inflate.findViewById(R.id.pager);
        this.mViewPager.setAdapter(new a(this));
        this.mViewPager.setOnPageChangeListener(this.mOnPageChangeListener);
        this.mViewPager.setCurrentItem(this.mSize * 100);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.mHandler.removeMessages(1);
        if (this.mAutoScroll) {
            this.mHandler.sendEmptyMessageDelayed(1, DELAY);
        }
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeMessages(1);
    }

    public void setAutoScroll(boolean z) {
        this.mAutoScroll = z;
    }

    private void initDotList() {
        int f = (int) (14.0f * com.sds.android.ttpod.common.c.a.f());
        for (int i = 0; i < this.mImageIds.length; i++) {
            View imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new LayoutParams(f, -2));
            imageView.setScaleType(ScaleType.CENTER_INSIDE);
            if (i == 0) {
                imageView.setImageResource(R.drawable.img_imageview_navigate_dot_focused);
            } else {
                imageView.setImageResource(R.drawable.img_imageview_navigate_dot_normal);
            }
            this.mDotList.add(imageView);
            this.mDotListLayout.addView(imageView);
        }
    }

    private void initGalleryList() {
        for (int imageResource : this.mImageIds) {
            ImageView imageView = new ImageView(getActivity());
            try {
                imageView.setImageResource(imageResource);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            imageView.setLayoutParams(new LayoutParams(-2, -2));
            imageView.setScaleType(ScaleType.CENTER_INSIDE);
            imageView.setVisibility(0);
            this.mGalleryList.add(imageView);
        }
    }
}
