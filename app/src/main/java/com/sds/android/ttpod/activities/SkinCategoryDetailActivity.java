package com.sds.android.ttpod.activities;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.SlidingClosableActivity;
import com.sds.android.ttpod.fragment.skinmanager.SkinCategoryDetailFragment;
import com.sds.android.ttpod.framework.a.b.s;

public class SkinCategoryDetailActivity extends SlidingClosableActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_lightweight_entry);
        hideActionBar();
        setStatisticPage(s.PAGE_THEME_CATEGORY_DETAIL);
        setTBSPage("tt_theme_category_" + getIntent().getExtras().getString("name"));
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_entry, new SkinCategoryDetailFragment()).commitAllowingStateLoss();
    }

    public boolean needApplyNavigationBarStyle() {
        return false;
    }

    public boolean needApplyStatusBarStyle() {
        return false;
    }
}
