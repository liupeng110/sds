package com.sds.android.ttpod.activities;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.skinmanager.ThemeRankFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class ThemeRankActivity extends SlidingClosableActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_lightweight_entry);
        hideActionBar();
        setStatisticPage(s.PAGE_THEME_RANK);
        setTBSPage("tt_theme_rank");
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_entry, new ThemeRankFragment()).commitAllowingStateLoss();
    }

    public boolean needApplyNavigationBarStyle() {
        return false;
    }

    public boolean needApplyStatusBarStyle() {
        return false;
    }
}
