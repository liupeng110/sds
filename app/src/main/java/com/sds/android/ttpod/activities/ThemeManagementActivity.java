package com.sds.android.ttpod.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.base.ThemeManagementBaseActivity;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.component.soundsearch.a;
import com.sds.android.ttpod.fragment.skinmanager.MyThemeFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeCategoryFragment;
import com.sds.android.ttpod.fragment.skinmanager.ThemeRecommendFragment;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.t;
import com.sds.android.ttpod.framework.a.b.x;
import com.sds.android.ttpod.framework.modules.theme.c.b;
import com.sds.android.ttpod.widget.h;
import java.util.ArrayList;
import java.util.List;

public class ThemeManagementActivity extends ThemeManagementBaseActivity implements a, b {
    public static final int FRAGMENT_THEME_CATEGORY = 1;
    public static final int FRAGMENT_THEME_MY = 2;
    public static final int FRAGMENT_THEME_RECOMMEND = 0;
    private static final ArrayList<h.a> LIST_THEME_PAGE = new ArrayList<h.a>() {
        {
            add(new h.a("theme", "tt_theme_recommend", "tt_theme_recommend"));
            add(new h.a("theme", "tt_theme_category", "tt_theme_category"));
            add(new h.a("theme", "tt_theme_my", "tt_theme_my"));
        }
    };
    private static final ArrayList<com.sds.android.ttpod.framework.a.b.a> SLIST = new ArrayList<com.sds.android.ttpod.framework.a.b.a>(3) {
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(getTitleString());
        setTBSPage(((h.a) LIST_THEME_PAGE.get(0)).c());
        trackModule("theme");
        setTTViewPagerListener(new h(this, 0, LIST_THEME_PAGE));
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onBuildFragmentBinderList(List<e.a> list) {
        Fragment themeRecommendFragment = new ThemeRecommendFragment();
        themeRecommendFragment.setStatisticPage(s.PAGE_THEME_BACKGROUND);
        list.add(new e.a(0, (int) R.string.recommend, 0, themeRecommendFragment));
        themeRecommendFragment = new ThemeCategoryFragment();
        themeRecommendFragment.setStatisticPage(s.PAGE_THEME_CATEGORY);
        list.add(new e.a(1, (int) R.string.category_tab, 0, themeRecommendFragment));
        themeRecommendFragment = new MyThemeFragment();
        themeRecommendFragment.setStatisticPage(s.PAGE_MY_THEME);
        list.add(new e.a(2, (int) R.string.my, 0, themeRecommendFragment));
    }

    public void toggleEditMode() {
        a editModeToggle = getEditModeToggle();
        if (editModeToggle != null) {
            this.mInEditMode = !this.mInEditMode;
            if ((editModeToggle instanceof MyThemeFragment) && this.mInEditMode) {
                x.b();
                new SUserEvent("PAGE_CLICK", r.ACTION_CLICK_THEME_MY_EDIT.getValue(), s.PAGE_MY_THEME.getValue(), s.PAGE_NONE.getValue()).post();
            }
            showEditActionItem();
            editModeToggle.toggleEditMode();
        }
    }

    protected void clickStatistic(int i) {
        int i2 = this.mCurrentPage;
        this.mCurrentPage = i;
        t.a("PAGE_CLICK", ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(this.mCurrentPage)).a(), ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(i2)).b(), ((com.sds.android.ttpod.framework.a.b.a) SLIST.get(i)).b());
        switch (i) {
            case 0:
                x.t();
                x.s();
                return;
            case 1:
                x.e();
                return;
            default:
                x.d();
                return;
        }
    }

    protected String getOnSelectedCountChangedString(int i) {
        return getString(R.string.select_theme_with_count, new Object[]{Integer.valueOf(i)});
    }

    protected String getTitleString() {
        return getString(R.string.theme_background);
    }
}
