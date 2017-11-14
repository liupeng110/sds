package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.fragment.main.FindSongBaseViewFragment;
import com.sds.android.ttpod.widget.PosterGallery;

public class FindSongPostFragment extends FindSongBaseViewFragment {
    private static final double POST_IMAGE_WIDTH_HEIGHT_RATIO = 0.469d;
    private static final double POST_IMAGE_WIDTH_HEIGHT_RATIO_NARROW = 0.3d;
    private boolean mAutoScroll = true;
    private PosterGallery mFindSongPostView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mFindSongPostView == null) {
            double d = getModuleData().getStyle() == 0 ? POST_IMAGE_WIDTH_HEIGHT_RATIO : POST_IMAGE_WIDTH_HEIGHT_RATIO_NARROW;
            this.mFindSongPostView = createPostGallery(d);
            this.mFindSongPostView.a(getModuleData().getDataList(), getModuleData().getStyle() == 0, d);
            this.mFindSongPostView.setEnableAutoScroll(true);
        }
        return this.mFindSongPostView;
    }

    private PosterGallery createPostGallery(double d) {
        PosterGallery posterGallery = new PosterGallery(getActivity());
        int a = a.a(4);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, (int) (((double) (a.d() - (a * 2))) * d));
        layoutParams.setMargins(a, a, a, a);
        posterGallery.setLayoutParams(layoutParams);
        posterGallery.setPosterCallback(new PosterGallery.a(this) {
            final /* synthetic */ FindSongPostFragment a;

            {
                this.a = r1;
            }

            public OnClickListener a(int i) {
                return this.a.createItemOnClickListener(i);
            }
        });
        return posterGallery;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.mAutoScroll = z;
        if (this.mFindSongPostView != null) {
            this.mFindSongPostView.setEnableAutoScroll(this.mAutoScroll);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mAutoScroll && this.mFindSongPostView != null) {
            this.mFindSongPostView.setEnableAutoScroll(true);
        }
    }

    public void onPause() {
        super.onPause();
        if (this.mFindSongPostView != null) {
            this.mFindSongPostView.setEnableAutoScroll(false);
        }
    }
}
