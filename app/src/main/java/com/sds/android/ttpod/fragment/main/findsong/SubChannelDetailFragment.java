package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubChannelDetailFragment extends SlidingClosableFragment {
    private final ChannelDetailFragment mChannelDetailFragment = new ChannelDetailFragment();

    public void onCreate(Bundle bundle) {
        String valueOf = String.valueOf(getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID));
        initBundle(s.PAGE_ONLINE_CHANNEL_DETAIL, valueOf);
        super.onCreate(bundle);
        setTBSPage("tt_channel_detail");
        trackPlaySong("channel", valueOf, true);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mChannelDetailFragment.setArguments(getArguments());
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mChannelDetailFragment, null).commitAllowingStateLoss();
        this.mChannelDetailFragment.setOnSetTitleListener(getActionBarController());
        return inflate;
    }

    protected boolean needSearchAction() {
        return false;
    }
}
