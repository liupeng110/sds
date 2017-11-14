package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.a;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SubSelectChannelFragment extends SlidingClosableFragment {
    private final SelectChannelFragment mSelectChannelFragment = new SelectChannelFragment();

    public void onCreate(Bundle bundle) {
        initBundle(s.PAGE_ONLINE_SELECT_CHANNEL, String.valueOf(getArguments().getLong(StarCategory.KEY_STAR_CATEGORY_ID)));
        setTBSPage("tt_recommendation");
        super.onCreate(bundle);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mSelectChannelFragment.setArguments(getArguments());
        View inflate = layoutInflater.inflate(R.layout.sub_fragment_container_layout, viewGroup, false);
        getChildFragmentManager().beginTransaction().replace(R.id.sub_fragment_container, this.mSelectChannelFragment, null).commitAllowingStateLoss();
        CharSequence string = getArguments().getString("name");
        a actionBarController = getActionBarController();
        if (m.a((String) string)) {
            string = getString(R.string.your_tags);
        }
        actionBarController.a(string);
        return inflate;
    }

    protected boolean needSearchAction() {
        return false;
    }
}
