package com.sds.android.ttpod.activities;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.skinmanager.BackgroundListFragment;

public class BackgroundActivity extends SlidingClosableActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_lightweight_entry);
        hideActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_entry, new BackgroundListFragment()).commitAllowingStateLoss();
    }

    public boolean needApplyStatusBarStyle() {
        return true;
    }

    public boolean needApplyNavigationBarStyle() {
        return false;
    }
}
