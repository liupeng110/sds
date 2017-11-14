package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;

public final class SubRelatedRecommendFragment extends SlidingClosableFragment {
    private RelatedRecommendFragment mFragment;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        updateTrack();
        setTBSPage("tt_similar");
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = 0;
        getActionBarController().a(getString(R.string.media_item_menu_related));
        setStatisticPage(s.PAGE_RELATED_POST);
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        this.mFragment = new RelatedRecommendFragment();
        this.mFragment.setArguments(getArguments());
        LayoutParams layoutParams = (LayoutParams) inflate.getLayoutParams();
        if (getArguments().getBoolean("show_play_control_bar", true)) {
            i = layoutParams.bottomMargin;
        }
        layoutParams.bottomMargin = i;
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mFragment, null).commitAllowingStateLoss();
        this.mFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }

    public static void launch(BaseActivity baseActivity, MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            BaseFragment subRelatedRecommendFragment = new SubRelatedRecommendFragment();
            Bundle bundle = new Bundle();
            bundle.putLong(MediasColumns.SONG_ID, mediaItem.getSongID().longValue());
            bundle.putBoolean("show_play_control_bar", z);
            subRelatedRecommendFragment.setArguments(bundle);
            baseActivity.launchFragment(subRelatedRecommendFragment);
        }
    }

    protected void onNewBundle(Bundle bundle) {
        if (this.mFragment != null) {
            updateTrack();
            this.mFragment.onNewBundle(bundle);
        }
    }

    private void updateTrack() {
        String valueOf = String.valueOf(getArguments().getLong(MediasColumns.SONG_ID));
        trackPlaySong("relate", valueOf, true);
        track("trigger_id", valueOf);
    }

    protected boolean needSingleTop() {
        return true;
    }
}
