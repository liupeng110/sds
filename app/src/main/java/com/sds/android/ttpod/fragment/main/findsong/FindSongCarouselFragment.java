package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.sds.android.cloudapi.ttpod.data.RecommendData;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.framework.modules.b.d;
import com.sds.android.ttpod.framework.modules.b.g;
import com.sds.android.ttpod.framework.storage.a.a;
import com.sds.android.ttpod.widget.carousel.CarouselViewGroup;
import com.sds.android.ttpod.widget.carousel.CarouselViewGroup.c;
import com.sds.android.ttpod.widget.carousel.PosterCarousel;
import java.util.Iterator;

public class FindSongCarouselFragment extends FindSongBaseViewFragment {
    public static final double POST_IMAGE_WIDTH_DISPLAY_RATIO = 0.875d;
    public static final double POST_IMAGE_WIDTH_HEIGHT_RATIO = 0.4d;
    private static final double POST_IMAGE_WIDTH_HEIGHT_RATIO_NARROW = 0.3d;
    private final int mLoadingHour = new g().a();
    private PosterCarousel mPosterCarousel;
    private d mScenePlayedRecord = a.a().S();

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mPosterCarousel == null) {
            createPosterCarousel(getCarouselDefaultIndex());
        }
        return this.mPosterCarousel;
    }

    private int getCarouselDefaultIndex() {
        Iterator it = getDataList().iterator();
        int i = 0;
        while (it.hasNext() && ((RecommendData) it.next()).getId() != this.mScenePlayedRecord.a(this.mLoadingHour)) {
            i++;
        }
        if (i == getDataListSize()) {
            return 0;
        }
        return i;
    }

    private void createPosterCarousel(int i) {
        this.mPosterCarousel = new PosterCarousel(getActivity());
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) ((((double) com.sds.android.ttpod.common.c.a.d()) * POST_IMAGE_WIDTH_DISPLAY_RATIO) * POST_IMAGE_WIDTH_HEIGHT_RATIO));
        int a = com.sds.android.ttpod.common.c.a.a(4);
        layoutParams.setMargins(0, a * 2, 0, a);
        this.mPosterCarousel.setLayoutParams(layoutParams);
        this.mPosterCarousel.setCreateOnItemPlayListener(new PosterCarousel.a(this) {
            final /* synthetic */ FindSongCarouselFragment a;

            {
                this.a = r1;
            }

            public OnClickListener a(int i) {
                return this.a.createQuickPlayListener(i);
            }
        });
        this.mPosterCarousel.setOnItemClickListener(new c(this) {
            final /* synthetic */ FindSongCarouselFragment a;

            {
                this.a = r1;
            }

            public void a(CarouselViewGroup<?> carouselViewGroup, View view, int i, long j) {
                this.a.clickItem(i);
            }
        });
        this.mPosterCarousel.setSelectedPositionInt(i);
        this.mPosterCarousel.a(getModuleData().getDataList());
    }

    protected void afterQuickPlay(int i) {
        this.mScenePlayedRecord.a(this.mLoadingHour, Long.valueOf(getItemData(i).getId()));
        a.a().a(this.mScenePlayedRecord);
    }
}
