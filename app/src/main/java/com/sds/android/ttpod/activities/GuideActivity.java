package com.sds.android.ttpod.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ThemeActivity;
import com.sds.android.ttpod.b.k;
import com.sds.android.ttpod.framework.a.b.v;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends ThemeActivity implements OnPageChangeListener, b {
    private static final int DURATION_ANIM = 300;
    private static final int GUIDE_DOT_IMAGE_WIDTH = 14;
    private static final String TAG = GuideActivity.class.getName();
    private a mAdapter;
    private int mDotPos = 0;
    private LinearLayout mGuideDotsView;
    private List<View> mGuideImages = new ArrayList();
    private ImageView mImageDot;
    private int mOffset;
    private boolean mShowGuidePage = false;
    private ViewPager mViewPager;

    public class a extends PagerAdapter {
        final /* synthetic */ GuideActivity a;
        private List<View> b;

        public a(GuideActivity guideActivity, List<View> list) {
            this.a = guideActivity;
            this.b = list;
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = (View) this.b.get(i);
            viewGroup.addView(view, 0);
            return view;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public int getCount() {
            return this.b.size();
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        }

        public Parcelable saveState() {
            return null;
        }
    }

    protected void onCreate(Bundle bundle) {
        getWindow().setFlags(1024, 1024);
        super.onCreate(bundle);
        setTBSPage("tt_guide");
        com.sds.android.ttpod.framework.storage.environment.b.q(com.sds.android.sdk.lib.util.EnvironmentUtils.a.f());
        setContentView((int) R.layout.guide_page_layout);
        this.mImageDot = (ImageView) findViewById(R.id.imageview_dot);
        this.mGuideDotsView = (LinearLayout) findViewById(R.id.layout_dots);
        initGuideImageView();
        initImageDot();
        setPageAdapter();
    }

    private void initGuideImageView() {
        this.mGuideImages.add(getNormalGuideView(R.drawable.guide_image01));
        this.mGuideImages.add(getEntryGuideView());
    }

    private View getNormalGuideView(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.layout_normal_guide_view, null);
        ((ImageView) inflate.findViewById(R.id.image_view_normal_guide)).setImageResource(i);
        return inflate;
    }

    private View getEntryGuideView() {
        View inflate = getLayoutInflater().inflate(R.layout.layout_entry_guide_view, null);
        ((TextView) inflate.findViewById(R.id.guide_jump_view)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ GuideActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!y.a()) {
                    this.a.startMainActivity();
                }
            }
        });
        return inflate;
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mGuideImages != null) {
            for (View view : this.mGuideImages) {
                k.a((ViewGroup) view);
            }
        }
        System.gc();
    }

    private void addImageDot(int i) {
        View imageView = new ImageView(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.width = (int) getResources().getDimension(R.dimen.guide_image_dot_size);
        layoutParams.weight = 1.0f;
        if (i != 0) {
            layoutParams.leftMargin = (int) getResources().getDimension(R.dimen.margin_guide_dot);
        }
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ScaleType.CENTER_INSIDE);
        imageView.setImageResource(R.drawable.img_guide_dot_normal);
        this.mGuideDotsView.addView(imageView);
    }

    private void initImageDot() {
        this.mGuideDotsView.removeAllViews();
        for (int i = 0; i < this.mGuideImages.size(); i++) {
            addImageDot(i);
        }
        this.mImageDot.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener(this) {
            final /* synthetic */ GuideActivity a;

            {
                this.a = r1;
            }

            public boolean onPreDraw() {
                this.a.mOffset = this.a.mImageDot.getWidth() + ((int) this.a.getResources().getDimension(R.dimen.margin_guide_dot));
                return true;
            }
        });
    }

    private void setPageAdapter() {
        this.mAdapter = new a(this, this.mGuideImages);
        this.mViewPager = (ViewPager) findViewById(R.id.contentPager);
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setOnPageChangeListener(this);
    }

    private void moveCursorTo(int i) {
        Animation translateAnimation = new TranslateAnimation((float) (this.mOffset * this.mDotPos), (float) (this.mOffset * i), 0.0f, 0.0f);
        translateAnimation.setDuration(300);
        translateAnimation.setFillAfter(true);
        this.mImageDot.startAnimation(translateAnimation);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        moveCursorTo(i);
        this.mDotPos = i;
        int count = this.mAdapter.getCount();
        if (!this.mShowGuidePage) {
            this.mShowGuidePage = i >= count + -1;
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onThemeLoaded() {
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class).setData(getIntent().getData()).putExtras(getIntent()));
        finish();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            v.a();
        }
    }

    public void onBackPressed() {
        startMainActivity();
    }
}
