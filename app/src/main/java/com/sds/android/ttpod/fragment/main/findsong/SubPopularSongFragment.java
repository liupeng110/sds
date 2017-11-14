package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubPopularSongFragment extends SlidingClosableFragment {
    private String mID = "";
    private final PopularSongFragment mPopularSongFragment;

    public SubPopularSongFragment(long j) {
        this.mID = String.valueOf(j);
        this.mPopularSongFragment = new PopularSongFragment(j);
        initBundle(s.PAGE_ONLINE_POPULAR_SONG, this.mID);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_popular_song");
        trackPlaySong("post", this.mID, true);
        updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, this.mID);
        updateAlibabaProperty("name", this.mPopularSongFragment.onLoadTitleText());
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mPopularSongFragment, null).commitAllowingStateLoss();
        this.mPopularSongFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
