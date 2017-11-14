package com.ttfm.android.sdk.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.sds.android.ttpod.R;
import com.ttfm.android.sdk.entity.FeatureClassifysEntity;
import com.ttfm.android.sdk.entity.FeatureClassifysEntity.FeatureClassifysItem;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.ArrayList;

public class MainSelectLayout1Adapter extends PagerAdapter {
    public static final double RATIO = 2.96d;
    private ArrayList<FeatureClassifysItem> mClassifyItems = new ArrayList();
    private View mContainer;
    private Context mContext = null;
    private FeatureClassifysEntity mFeatureClassifysEntity = null;
    private int mImgLayoutH = 0;
    private int mImgLayoutW = 0;
    private OnClickListener mOnClickListener = null;

    public MainSelectLayout1Adapter(Context context, View view, OnClickListener onClickListener) {
        this.mContext = context;
        this.mContainer = view;
        this.mOnClickListener = onClickListener;
        this.mImgLayoutW = (TTFMEnvironmentUtils.getmScreenW() - context.getResources().getDimensionPixelSize(R.dimen.nav_bar_size)) - context.getResources().getDimensionPixelSize(R.dimen.footer_padding);
        this.mImgLayoutH = (int) (((double) this.mImgLayoutW) / RATIO);
    }

    public View getView(int i) {
        FeatureClassifysItem featureClassifysItem = (FeatureClassifysItem) this.mClassifyItems.get(i);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_main_select_head_1_item, null);
        View findViewById = inflate.findViewById(R.id.select_head_1_item_close);
        findViewById.setOnClickListener(this.mOnClickListener);
        findViewById.setVisibility(8);
        if (this.mFeatureClassifysEntity != null && this.mFeatureClassifysEntity.isFfcCloseShow()) {
            findViewById.setVisibility(0);
            findViewById.setTag(this.mContainer);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.select_head_1_item_img);
        LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.height = this.mImgLayoutH;
        layoutParams.width = this.mImgLayoutW;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setOnClickListener(this.mOnClickListener);
        TTFMImageUtils.setImageView(imageView, featureClassifysItem.getFfiImg(), this.mImgLayoutW, null);
        imageView.setTag(featureClassifysItem);
        return inflate;
    }

    public int getCount() {
        if (this.mClassifyItems != null) {
            return this.mClassifyItems.size();
        }
        return 0;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = getView(i);
        ((ViewPager) viewGroup).addView(view, i);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView((View) obj);
    }

    public void setListData(FeatureClassifysEntity featureClassifysEntity, ArrayList<FeatureClassifysItem> arrayList) {
        this.mFeatureClassifysEntity = featureClassifysEntity;
        if (this.mClassifyItems == null) {
            this.mClassifyItems = new ArrayList();
        }
        this.mClassifyItems.clear();
        this.mClassifyItems.addAll(arrayList);
        notifyDataSetChanged();
    }
}
