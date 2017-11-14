package com.sds.android.ttpod.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.ForwardAction;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a.b.c;
import com.sds.android.ttpod.framework.a.b.i;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;

public class PosterGallery extends RelativeLayout implements b {
    private ScrollableViewGroup a;
    private IconPageIndicator b;
    private a c;
    private ArrayList<RecommendData> d;
    private boolean[] e;

    public interface a {
        OnClickListener a(int i);
    }

    public PosterGallery(Context context) {
        this(context, null);
    }

    public PosterGallery(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PosterGallery(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = null;
        LayoutInflater.from(context).inflate(R.layout.find_song_poser_gallery, this, true);
        this.a = (ScrollableViewGroup) findViewById(R.id.layout_gallery);
        this.b = (IconPageIndicator) findViewById(R.id.page_indicator);
        this.b.setVisibility(0);
        this.b.a((int) R.drawable.img_page_indicator, (int) R.drawable.img_page_indicator_selected);
        this.a.setOnCurrentViewChangedListener(new com.sds.android.ttpod.widget.ScrollableViewGroup.a(this) {
            final /* synthetic */ PosterGallery a;

            {
                this.a = r1;
            }

            public void a(View view, int i) {
                this.a.b.a(i);
                if (this.a.d != null && i < this.a.d.size()) {
                    RecommendData recommendData = (RecommendData) this.a.d.get(i);
                    ForwardAction forwardAction = recommendData.getForwardAction();
                    i.a(recommendData.getId(), i + 1);
                    SEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_POST_GALLERY_SCROLL.getValue(), s.PAGE_ONLINE_FIND_SONG.getValue());
                    sUserEvent.append("item_id", Long.valueOf(recommendData.getId()));
                    sUserEvent.append("item_name", recommendData.getName());
                    if (forwardAction.getType() == 5) {
                        sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, forwardAction.getValue());
                        sUserEvent.append("song_list_name", recommendData.getName());
                    } else {
                        sUserEvent.append(Downloads.COLUMN_URI, forwardAction.getValue());
                    }
                    sUserEvent.post();
                    if (!this.a.e[i]) {
                        new c("show").a("location", String.valueOf(i)).a(SocialConstants.PARAM_TYPE, "post").a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(forwardAction.getValue())).a("name", String.valueOf(recommendData.getName())).a();
                        this.a.e[i] = true;
                    }
                }
            }
        });
        ViewCompat.setLayerType(this, 1, null);
    }

    public void a(ArrayList<RecommendData> arrayList, boolean z, double d) {
        a(arrayList, 1, 5, z, d);
    }

    private void a(ArrayList<RecommendData> arrayList, int i, int i2, boolean z, double d) {
        this.d = arrayList;
        int size = arrayList.size();
        if (size % i == 0) {
            size /= i;
        } else {
            size = (size / i) + 1;
        }
        int min = Math.min(size, i2);
        this.b.b(min);
        this.a.removeAllViews();
        this.e = new boolean[min];
        for (int i3 = 0; i3 < min; i3++) {
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.layout_null_relative_layout, null);
            viewGroup.addView(a((RecommendData) arrayList.get(i3), i3, z, d));
            size = ((RecommendData) arrayList.get(i3)).getTagIntValue().intValue();
            if (size >= 0 && size <= 7) {
                viewGroup.addView(a((RecommendData) arrayList.get(i3)));
            }
            this.a.addView(viewGroup);
            this.e[i3] = false;
        }
    }

    private ImageView a(RecommendData recommendData, int i, boolean z, double d) {
        ImageView roundedImageView = new RoundedImageView(getContext(), 16.0f);
        roundedImageView.setScaleType(ScaleType.CENTER_CROP);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(com.sds.android.ttpod.common.c.a.a(4), 0, com.sds.android.ttpod.common.c.a.a(4), 0);
        roundedImageView.setLayoutParams(layoutParams);
        roundedImageView.setOnClickListener(this.c.a(i));
        String picUrl = (z || m.a(recommendData.getLargePicUrl())) ? recommendData.getPicUrl() : recommendData.getLargePicUrl();
        roundedImageView.setTag(R.id.view_bind_data, recommendData);
        int d2 = com.sds.android.ttpod.common.c.a.d() - (com.sds.android.ttpod.common.c.a.a(4) * 2);
        int d3 = (int) (((double) com.sds.android.ttpod.common.c.a.d()) * d);
        g.a("PosterGallery", "poster url: " + picUrl + ", size: " + d2 + "*" + d3);
        com.sds.android.ttpod.framework.a.g.a(roundedImageView, picUrl, d2, d3, (int) R.drawable.img_background_publish_poster_gallery);
        return roundedImageView;
    }

    private ImageView a(RecommendData recommendData) {
        ImageView imageView = new ImageView(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(com.sds.android.ttpod.common.c.a.a(58), com.sds.android.ttpod.common.c.a.a(20));
        layoutParams.addRule(12);
        layoutParams.addRule(11);
        layoutParams.setMargins(0, 0, com.sds.android.ttpod.common.c.a.a(4), com.sds.android.ttpod.common.c.a.a(7));
        imageView.setLayoutParams(layoutParams);
        try {
            imageView.setBackgroundResource(com.sds.android.ttpod.b.i.c.a(recommendData.getTagIntValue().intValue()));
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return imageView;
    }

    public void setEnableAutoScroll(boolean z) {
        if (this.a != null) {
            this.a.setEnableAutoScroll(z);
        }
    }

    public void setPosterCallback(a aVar) {
        this.c = aVar;
    }

    public void onThemeLoaded() {
    }
}
