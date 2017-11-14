package com.sds.android.ttpod.cmmusic.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sds.android.ttpod.cmmusic.R;
import com.sds.android.ttpod.framework.a.b.s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RecommendFragment extends BaseMusicFragment implements OnClickListener {
    private static final Integer PANDING = Integer.valueOf(20);
    private static final int TIMEOUT = 5;
    private ArrayList<HashMap<String, String>> mAdSeatContentInfos = new ArrayList();
    private int mCurrentItem = 0;
    private Handler mHandlers = new Handler(this) {
        final /* synthetic */ RecommendFragment a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 4:
                    this.a.mAdSeatContentInfos = (ArrayList) message.obj;
                    if (this.a.mAdSeatContentInfos != null && this.a.mAdSeatContentInfos.size() > 0) {
                        this.a.mIndicatorImgs = new ImageView[this.a.mAdSeatContentInfos.size()];
                        this.a.titleViewContentBind();
                        return;
                    }
                    return;
                case 5:
                    this.a.mViewPager.setCurrentItem(this.a.mCurrentItem);
                    return;
                default:
                    return;
            }
        }
    };
    private View mHeadView;
    private com.sds.android.ttpod.cmmusic.a.b mImageAdapter;
    private ImageView[] mIndicatorImgs;
    private LayoutInflater mInflater;
    private ScheduledExecutorService mScheduledExecutorService;
    private ViewPager mViewPager;

    private class a implements OnPageChangeListener {
        final /* synthetic */ RecommendFragment a;

        private a(RecommendFragment recommendFragment) {
            this.a = recommendFragment;
        }

        public void onPageScrollStateChanged(int i) {
            if (i != 0) {
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            for (ImageView backgroundResource : this.a.mIndicatorImgs) {
                backgroundResource.setBackgroundResource(R.drawable.cmmusic_indicator);
            }
            this.a.mIndicatorImgs[i].setBackgroundResource(R.drawable.cmmusic_indicator_focused);
        }
    }

    private class b implements Runnable {
        final /* synthetic */ RecommendFragment a;

        private b(RecommendFragment recommendFragment) {
            this.a = recommendFragment;
        }

        public void run() {
            this.a.mCurrentItem = (this.a.mCurrentItem + 1) % this.a.mIndicatorImgs.length;
            Message message = new Message();
            message.what = 5;
            this.a.mHandlers.sendMessage(message);
        }
    }

    public void onStart() {
        this.mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.mScheduledExecutorService.scheduleAtFixedRate(new b(), 5, 5, TimeUnit.SECONDS);
        super.onStart();
    }

    public void onStop() {
        this.mScheduledExecutorService.shutdown();
        super.onStop();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mHandlers.removeCallbacksAndMessages(null);
    }

    protected String getPageName() {
        return "tag_1";
    }

    protected int getPageCode() {
        return s.PAGE_CMMUSIC_RECOMMEND_CODE.getValue();
    }

    protected void updateViewContent() {
        getAdseatInfo();
        super.updateViewContent();
    }

    private void getAdseatInfo() {
        com.sds.android.sdk.lib.e.a.a((Object) this, new Runnable(this) {
            final /* synthetic */ RecommendFragment a;

            {
                this.a = r1;
            }

            public void run() {
                ArrayList a = com.sds.android.ttpod.cmmusic.c.a.a();
                Message message = new Message();
                message.what = 4;
                message.obj = a;
                this.a.mHandlers.sendMessage(message);
            }
        });
    }

    protected void initHeaderView(View view) {
        this.mHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.cmmusic_recommend_activity_imagebanner_head, null);
        getListView().addHeaderView(this.mHeadView);
        this.mViewPager = (ViewPager) view.findViewById(R.id.view_pager_recommendAcitvity);
    }

    private void titleViewContentBind() {
        try {
            List arrayList = new ArrayList();
            this.mInflater = LayoutInflater.from(getActivity());
            for (int i = 0; i < this.mIndicatorImgs.length; i++) {
                arrayList.add(this.mInflater.inflate(R.layout.cmmusic_recommend_activity_imagebanner_item, null));
            }
            this.mImageAdapter = new com.sds.android.ttpod.cmmusic.a.b(this.mAdSeatContentInfos, arrayList, getActivity());
            this.mViewPager.setAdapter(this.mImageAdapter);
            this.mViewPager.setOnPageChangeListener(new a());
            initIndicator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initIndicator() {
        View findViewById = getRootView().findViewById(R.id.indicator_recommendAcitvity);
        for (int i = 0; i < this.mIndicatorImgs.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            LayoutParams layoutParams = new LinearLayout.LayoutParams(PANDING.intValue(), PANDING.intValue());
            layoutParams.setMargins(3, 2, 3, 2);
            imageView.setLayoutParams(layoutParams);
            this.mIndicatorImgs[i] = imageView;
            if (i == 0) {
                this.mIndicatorImgs[i].setBackgroundResource(R.drawable.cmmusic_indicator_focused);
            } else {
                this.mIndicatorImgs[i].setBackgroundResource(R.drawable.cmmusic_indicator);
            }
            ((ViewGroup) findViewById).addView(this.mIndicatorImgs[i]);
        }
    }
}
