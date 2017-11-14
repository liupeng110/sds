package com.sds.android.ttpod.activities.musiccircle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SlidingAlbumDetailFragment extends SlidingClosableFragment {
    public static SlidingAlbumDetailFragment instantiate(long j, String str) {
        return instantiate(j, str, true);
    }

    public static SlidingAlbumDetailFragment instantiate(long j, String str, boolean z) {
        SlidingAlbumDetailFragment slidingAlbumDetailFragment = new SlidingAlbumDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(StarCategory.KEY_STAR_CATEGORY_ID, j);
        bundle.putString("title", str);
        bundle.putBoolean("show_play_control_bar", z);
        slidingAlbumDetailFragment.setArguments(bundle);
        slidingAlbumDetailFragment.initBundle(s.PAGE_ALBUM_DETAIL, null);
        slidingAlbumDetailFragment.setStatisticPageProperties("song_album_id", Long.valueOf(j));
        return slidingAlbumDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_album_detail");
        trackPlaySong("album", String.valueOf(getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID, 0)), true);
    }

    protected void onNewBundle(Bundle bundle) {
        super.onNewBundle(bundle);
        FragmentManager childFragmentManager = getChildFragmentManager();
        if (childFragmentManager != null) {
            AlbumDetailFragment albumDetailFragment = (AlbumDetailFragment) childFragmentManager.findFragmentById(R.id.sub_fragment_container);
            if (albumDetailFragment != null) {
                trackPlaySong("album", String.valueOf(getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID, 0)), true);
                albumDetailFragment.onNewBundle(bundle);
            }
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = 0;
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            getActionBarController().a(arguments.getString("title"));
        }
        LayoutParams layoutParams = (LayoutParams) inflate.getLayoutParams();
        if (getArguments().getBoolean("show_play_control_bar", true)) {
            i = layoutParams.bottomMargin;
        }
        layoutParams.bottomMargin = i;
        Fragment albumDetailFragment = new AlbumDetailFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, albumDetailFragment, null).commitAllowingStateLoss();
        albumDetailFragment.setOnSetTitleListener(getActionBarController());
        albumDetailFragment.setArguments(arguments);
        return inflate;
    }

    protected boolean needSingleTop() {
        return true;
    }
}
