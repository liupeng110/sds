package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.cloudapi.ttpod.result.OnlineMusicSubCategoryResult.SubCategoryData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubSongCategoryDetailFragment extends SlidingClosableFragment {
    private String mId;
    private final SongCategoryDetailFragment mSongCategoryDetailFragment;

    public SubSongCategoryDetailFragment(String str) {
        this.mId = str;
        this.mSongCategoryDetailFragment = new SongCategoryDetailFragment(str);
        initBundle(s.PAGE_ONLINE_SONG_CATEGORY, String.valueOf(str));
    }

    public SubSongCategoryDetailFragment(SubCategoryData subCategoryData) {
        this.mSongCategoryDetailFragment = new SongCategoryDetailFragment(subCategoryData);
        this.mId = String.valueOf(subCategoryData.getId());
        initBundle(s.PAGE_ONLINE_SONG_CATEGORY, this.mId);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_song_category");
        trackPlaySong("channel", this.mId, true);
        updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, this.mId);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mSongCategoryDetailFragment, null).commitAllowingStateLoss();
        this.mSongCategoryDetailFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
