package com.sds.android.ttpod.activities;

import android.os.Bundle;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.component.soundsearch.a;
import com.sds.android.ttpod.fragment.skinmanager.BackgroundRecommendFragment;
import com.sds.android.ttpod.fragment.skinmanager.MyBackgroundFragment;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.widget.h;
import java.util.ArrayList;
import java.util.List;

public class BackgroundManagementActivity extends ThemeManagementBaseActivity implements a, b {
    public static final int FRAGMENT_BACKGROUND_MORE = 1;
    public static final int FRAGMENT_BACKGROUND_MY = 2;
    public static final int FRAGMENT_BACKGROUND_RECOMMEND = 0;
    private static final ArrayList<h.a> LIST_THEME_PAGE = new ArrayList<h.a>() {
        {
            add(new h.a("theme", "tt_background_recommend", "tt_background_recommend"));
            add(new h.a("theme", "tt_background_my", "tt_background_my"));
        }
    };
    private static final ArrayList<com.sds.android.ttpod.framework.a.b.a> SLIST = new ArrayList<com.sds.android.ttpod.framework.a.b.a>(3) {
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getTitleString());
        setTBSPage(((h.a) LIST_THEME_PAGE.get(0)).c());
        setTTViewPagerListener(new h(this, 0, LIST_THEME_PAGE));
    }

    protected void onResume() {
        super.onResume();
        refreshEditButton();
    }

    protected void onBuildFragmentBinderList(List<e.a> list) {
        list.add(new e.a(0, (int) R.string.recommend, 0, new BackgroundRecommendFragment()));
        list.add(new e.a(2, (int) R.string.my, 0, new MyBackgroundFragment()));
    }

    public void toggleEditMode() {
        a editModeToggle = getEditModeToggle();
        if (editModeToggle != null) {
            this.mInEditMode = !this.mInEditMode;
            showEditActionItem();
            editModeToggle.toggleEditMode();
        }
    }

    protected void clickStatistic(int i) {
        switch (i) {
            case 0:
                x.v();
                break;
            case 1:
                x.u();
                break;
            case 2:
                x.w();
                break;
        }
        int i2 = this.mCurrentPage;
        this.mCurrentPage = i;
        t.a("PAGE_CLICK", ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(this.mCurrentPage)).a(), ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(i2)).b(), ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(i)).b());
    }

    protected String getOnSelectedCountChangedString(int i) {
        return getString(R.string.select_background_with_count, new Object[]{Integer.valueOf(i)});
    }

    protected String getTitleString() {
        return getString(R.string.background);
    }
}
