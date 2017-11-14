package com.ttfm.android.sdk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.tencent.open.yyb.TitleBar;
import com.ttfm.android.sdk.entity.FeatureClassifysEntity.FeatureClassifysItem;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import com.ttfm.android.sdk.utils.TTFMImageUtils;
import java.util.ArrayList;

public class SelectAdapter extends BaseAdapter {
    private ArrayList<FeatureClassifysItem> mClassifyItems = new ArrayList();
    private int mColumn = 1;
    private Context mContext = null;
    private OnClickListener mOnClickListener = null;
    private int mTagImgW = 0;

    public SelectAdapter(Context context, OnClickListener onClickListener, int i) {
        this.mContext = context;
        this.mOnClickListener = onClickListener;
        this.mColumn = i;
        if (this.mColumn == 0) {
            this.mColumn = 1;
        }
        this.mTagImgW = (TTFMEnvironmentUtils.getmScreenW() - (TTFMImageUtils.Dp2Px(context, TitleBar.SHAREBTN_RIGHT_MARGIN) * (this.mColumn + 1))) / this.mColumn;
    }

    public int getCount() {
        if (this.mClassifyItems != null) {
            return this.mClassifyItems.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (this.mClassifyItems != null) {
            return this.mClassifyItems.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        FeatureClassifysItem featureClassifysItem = (FeatureClassifysItem) this.mClassifyItems.get(i);
        if (view == null) {
            inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_select_list_item, null);
        } else {
            inflate = view;
        }
        if (featureClassifysItem == null) {
            return inflate;
        }
        ((TextView) inflate.findViewById(R.id.select_list_item_title)).setText(featureClassifysItem.getFfiName());
        ImageView imageView = (ImageView) inflate.findViewById(R.id.select_list_item_tip);
        imageView.setVisibility(8);
        if (featureClassifysItem.getFfiTypeImg() != null && featureClassifysItem.getFfiTypeImg().length() > 0) {
            imageView.setVisibility(0);
            imageView.setImageResource(R.drawable.img_select_tip);
            TTFMImageUtils.setImageView(imageView, featureClassifysItem.getFfiTypeImg());
        }
        imageView = (ImageView) inflate.findViewById(R.id.select_list_item_img);
        imageView.setImageResource(R.drawable.img_channel_default_mini);
        if (this.mColumn > 2) {
            LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = this.mTagImgW;
            layoutParams.width = this.mTagImgW;
            imageView.setLayoutParams(layoutParams);
        }
        TTFMImageUtils.setImageView(imageView, featureClassifysItem.getFfiImg(), this.mTagImgW, null);
        imageView = (ImageView) inflate.findViewById(R.id.select_list_item_play);
        imageView.setTag(featureClassifysItem);
        imageView.setOnClickListener(this.mOnClickListener);
        inflate.setTag(featureClassifysItem);
        return inflate;
    }

    public void setListData(ArrayList<FeatureClassifysItem> arrayList) {
        if (this.mClassifyItems == null) {
            this.mClassifyItems = new ArrayList();
        }
        this.mClassifyItems.clear();
        this.mClassifyItems.addAll(arrayList);
        notifyDataSetChanged();
    }
}
