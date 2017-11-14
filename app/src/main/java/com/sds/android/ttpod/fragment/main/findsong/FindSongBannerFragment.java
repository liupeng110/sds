package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.a.b.i;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.widget.RoundedImageView;

public class FindSongBannerFragment extends FindSongBaseViewFragment {
    private ViewGroup mFindSongBannerViewContainer;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFindSongBannerViewContainer == null) {
            int a = a.a(8);
            this.mFindSongBannerViewContainer = (ViewGroup) layoutInflater.inflate(R.layout.layout_null_relative_layout, null);
            this.mFindSongBannerViewContainer.setPadding(a, a, a, a);
            this.mFindSongBannerViewContainer.addView(createBannerView());
            this.mFindSongBannerViewContainer.addView(createClickView());
            this.mFindSongBannerViewContainer.addView(createCloseButton());
        }
        return this.mFindSongBannerViewContainer;
    }

    private RoundedImageView createBannerView() {
        ImageView roundedImageView = new RoundedImageView(getActivity());
        roundedImageView.setCornerRadius((float) a.a(4));
        int d = a.d();
        int i = (int) (((double) d) * 0.2d);
        roundedImageView.setLayoutParams(new LayoutParams(-1, i));
        roundedImageView.setScaleType(ScaleType.FIT_XY);
        requestImage(roundedImageView, getItemData(0).getPicUrl(), d, i, R.drawable.img_background_publish_poster_gallery);
        return roundedImageView;
    }

    private ImageView createClickView() {
        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, (int) (((double) a.d()) * 0.2d)));
        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.grid_view_item_click_bg));
        imageView.setOnClickListener(createItemOnClickListener(0));
        return imageView;
    }

    private ImageView createCloseButton() {
        ImageView imageView = new ImageView(getActivity());
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(11);
        layoutParams.addRule(10);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.banner_close_button_click_bg));
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FindSongBannerFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.mFindSongBannerViewContainer.setVisibility(8);
                i.a(348, this.a.getModuleData().getId());
                i.a(324, this.a.getItemData(0).getId());
                this.a.doFindSongStatistic(0, r.ACTION_CLICK_ONLINE_FIND_SONG_ITEM_CLOSE);
            }
        });
        return imageView;
    }
}
