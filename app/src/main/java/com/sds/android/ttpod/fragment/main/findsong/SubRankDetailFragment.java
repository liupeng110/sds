package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubRankDetailFragment extends SlidingClosableFragment {
    private final RankDetailFragment mRankDetailFragment;
    private String mRankId;

    public SubRankDetailFragment(MusicRank musicRank) {
        this.mRankDetailFragment = new RankDetailFragment(musicRank);
        this.mRankId = String.valueOf(musicRank.getId());
        initBundle(s.PAGE_RANK_DETAIL, this.mRankId);
    }

    public SubRankDetailFragment(int i) {
        this.mRankId = String.valueOf(i);
        this.mRankDetailFragment = new RankDetailFragment(i);
        initBundle(s.PAGE_RANK_DETAIL, this.mRankId);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_rank_detail");
        trackPlaySong("rank", this.mRankId, true);
        updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, this.mRankId);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mRankDetailFragment, null).commitAllowingStateLoss();
        this.mRankDetailFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
