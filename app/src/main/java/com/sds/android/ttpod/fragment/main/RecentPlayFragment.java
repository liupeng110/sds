package com.sds.android.ttpod.fragment.main;

import android.os.Bundle;
import com.sds.android.ttpod.fragment.main.list.SubMediaListFragment;

public class RecentPlayFragment extends SubMediaListFragment {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_recent_play");
        trackModule("recent_play");
    }
}
