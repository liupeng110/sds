package com.sds.android.ttpod.widget.carousel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.main.findsong.FindSongCarouselFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.widget.carousel.CarouselViewGroup.c;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;

public class PosterCarousel extends RelativeLayout implements b {
    private final Carousel a;
    private boolean[] b;
    private c c;
    private a d;

    public interface a {
        OnClickListener a(int i);
    }

    public PosterCarousel(Context context) {
        this(context, null);
    }

    public void setSelectedPositionInt(int i) {
        this.a.setSelectedPositionInt(i);
    }

    public PosterCarousel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PosterCarousel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.layout_poser_carousel, this, true);
        this.a = (Carousel) findViewById(R.id.carousel);
    }

    public void a(ArrayList<RecommendData> arrayList) {
        a(arrayList, 1, 5);
    }

    public void a(final ArrayList<RecommendData> arrayList, int i, int i2) {
        int size = arrayList.size();
        int min = Math.min(size % i == 0 ? size / i : (size / i) + 1, i2);
        SpinnerAdapter bVar = new b();
        a[] aVarArr = new a[min];
        this.b = new boolean[min];
        for (int i3 = 0; i3 < min; i3++) {
            aVarArr[i3] = a((RecommendData) arrayList.get(i3), i3);
            this.b[i3] = false;
        }
        this.a.setSelectChangedLister(new b(this) {
            final /* synthetic */ PosterCarousel b;

            public void a(int i) {
                RecommendData recommendData = (RecommendData) arrayList.get(i);
                g.a("PosterCarousel", "position = " + i + "  name = " + recommendData.getName());
                SEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_SCROLL_FIND_SONG_CAROUSEL.getValue(), s.PAGE_ONLINE_FIND_SONG.getValue());
                sUserEvent.append("item_id", Long.valueOf(recommendData.getId()));
                sUserEvent.append("item_name", recommendData.getName());
                sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, recommendData.getForwardAction().getValue());
                sUserEvent.append("song_list_name", recommendData.getName());
                sUserEvent.append("position", Integer.valueOf(i + 1));
                sUserEvent.post();
                if (!this.b.b[i]) {
                    new com.sds.android.ttpod.framework.a.b.c("show").a("location", String.valueOf(i)).a(SocialConstants.PARAM_TYPE, "channel").a(StarCategory.KEY_STAR_CATEGORY_ID, String.valueOf(recommendData.getId())).a("name", String.valueOf(recommendData.getName())).a();
                    this.b.b[i] = true;
                }
            }
        });
        this.a.setOnCarouselItemClickListener(this.c);
        bVar.a(aVarArr);
        this.a.setAdapter(bVar);
    }

    public void setOnItemClickListener(c cVar) {
        this.c = cVar;
    }

    private a a(RecommendData recommendData, int i) {
        ViewGroup aVar = new a(getContext());
        aVar.setLayoutParams(new LayoutParams(-2, -2));
        int d = (int) (((double) com.sds.android.ttpod.common.c.a.d()) * FindSongCarouselFragment.POST_IMAGE_WIDTH_DISPLAY_RATIO);
        int i2 = (int) (((double) d) * FindSongCarouselFragment.POST_IMAGE_WIDTH_HEIGHT_RATIO);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.carousel_item, aVar, true);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.rounded_image_view);
        imageView.setImageResource(R.drawable.img_background_publish_poster_gallery);
        inflate.findViewById(R.id.carousel_item_parent).setLayoutParams(new FrameLayout.LayoutParams(d, i2));
        inflate.findViewById(R.id.quick_play).setOnClickListener(a(i));
        ((TextView) aVar.findViewById(R.id.carousel_name)).setText(recommendData.getName());
        ((TextView) aVar.findViewById(R.id.carousel_description)).setText(recommendData.getDesc());
        aVar.setImageView(imageView);
        aVar.setIndex(i);
        imageView = aVar.getImageView();
        imageView.setTag(R.id.view_bind_data, recommendData);
        com.sds.android.ttpod.framework.a.g.a(imageView, recommendData.getPicUrl(), imageView.getWidth(), imageView.getHeight(), (int) R.drawable.img_background_publish_poster_gallery);
        return aVar;
    }

    private OnClickListener a(int i) {
        return this.d.a(i);
    }

    public void setCreateOnItemPlayListener(a aVar) {
        this.d = aVar;
    }

    public void onThemeLoaded() {
    }
}
