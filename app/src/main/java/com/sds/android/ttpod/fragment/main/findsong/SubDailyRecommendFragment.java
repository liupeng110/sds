package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubDailyRecommendFragment extends SlidingClosableFragment {
    private final DailyRecommendFragment mDailyRecommendFragment;
    private final String mItemId;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_daily_recommend");
        trackPlaySong("recomend_you_like", this.mItemId, true);
    }

    public SubDailyRecommendFragment(long j, String str, String str2) {
        this.mItemId = String.valueOf(j);
        initBundle(s.PAGE_ONLINE_DAILY_RECOMMEND, this.mItemId);
        this.mDailyRecommendFragment = new DailyRecommendFragment(j, str, str2);
        updateAlibabaProperty(StarCategory.KEY_STAR_CATEGORY_ID, this.mItemId);
        updateAlibabaProperty("name", String.valueOf(str2));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mDailyRecommendFragment, null).commitAllowingStateLoss();
        this.mDailyRecommendFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }
}
