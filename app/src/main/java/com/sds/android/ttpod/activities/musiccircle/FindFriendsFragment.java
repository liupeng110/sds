package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.radar.ScanRadarToFindFriendsFragment;
import com.sds.android.ttpod.activities.musiccircle.search.SearchFriendsFragment;
import com.sds.android.ttpod.activities.musiccircle.shake.ShakeToFindFriendsFragment;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.component.a.b;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarCategoryFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarListOfRecommendFragment;
import com.sds.android.ttpod.fragment.musiccircle.StarRankFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.h;
import java.util.ArrayList;
import java.util.Arrays;

public class FindFriendsFragment extends SlidingClosableFragment {
    private static final int FRAGMENT_ID_CATEGORY = 2;
    private static final int FRAGMENT_ID_RANK = 1;
    private static final int FRAGMENT_ID_RECOMMEND = 0;
    private static final ArrayList<com.sds.android.ttpod.widget.h.a> PAGE_LIST = new ArrayList<com.sds.android.ttpod.widget.h.a>() {
        {
            add(new com.sds.android.ttpod.widget.h.a("", "tt_music_circle_recommend"));
            add(new com.sds.android.ttpod.widget.h.a("", "tt_music_circle_rank"));
            add(new com.sds.android.ttpod.widget.h.a("", "tt_music_circle_category"));
        }
    };
    private static final int PAGE_MUSIC_CIRCLE_RECOMMEND_ID = 0;
    public static final String SOCIAL_CATEGORY = "social_category";
    public static final String SOCIAL_RANK = "social_rank";
    public static final String SOCIAL_RECOMMEND = "social_recommend";
    private int mCurrentItem = 0;
    private com.sds.android.ttpod.component.a.a mRadarAction;
    private View mRootView;
    private com.sds.android.ttpod.component.a.a mSearchAction;
    private com.sds.android.ttpod.component.a.a mShakeAction;
    private SlidingTabHost mSlidingTabHost;
    private a mTabsAdapter;
    private ViewPager mViewPager;

    public class a extends e {
        final /* synthetic */ FindFriendsFragment a;

        public a(FindFriendsFragment findFriendsFragment, Context context, FragmentManager fragmentManager, com.sds.android.ttpod.adapter.e.a[] aVarArr) {
            this.a = findFriendsFragment;
            super(context, fragmentManager, Arrays.asList(aVarArr));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage(((com.sds.android.ttpod.widget.h.a) PAGE_LIST.get(this.mCurrentItem)).c());
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.musiccircle_find_friend_layout, viewGroup, false);
        initView();
        c.f();
        return this.mRootView;
    }

    protected void addCustomActions() {
        com.sds.android.ttpod.component.a actionBarController = getActionBarController();
        this.mRadarAction = actionBarController.d((int) R.drawable.img_musiccircle_small_radar);
        this.mShakeAction = actionBarController.d((int) R.drawable.img_musiccircle_shake);
        this.mSearchAction = actionBarController.d((int) R.drawable.img_search);
        b anonymousClass2 = new b(this) {
            final /* synthetic */ FindFriendsFragment a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.a.a aVar) {
                if (!com.sds.android.ttpod.framework.storage.environment.b.av()) {
                    f.a(true);
                } else if (aVar == this.a.mRadarAction) {
                    this.a.launchFragment(new ScanRadarToFindFriendsFragment());
                } else if (aVar == this.a.mShakeAction) {
                    this.a.launchFragment(new ShakeToFindFriendsFragment());
                } else if (aVar == this.a.mSearchAction) {
                    this.a.launchFragment(new SearchFriendsFragment());
                }
            }
        };
        this.mRadarAction.a(anonymousClass2);
        this.mShakeAction.a(anonymousClass2);
        this.mSearchAction.a(anonymousClass2);
    }

    protected boolean needSearchAction() {
        return false;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        v.a(this.mRadarAction, "", (int) R.string.icon_radar, ThemeElement.TOP_BAR_TEXT);
        v.a(this.mShakeAction, "", (int) R.string.icon_shake_switch_song, ThemeElement.TOP_BAR_TEXT);
        v.a(this.mSearchAction, "", (int) R.string.icon_search, ThemeElement.TOP_BAR_TEXT);
        getActionBarController().onThemeLoaded();
    }

    private void initView() {
        getActionBarController().b((int) R.string.find_friend_title);
        Fragment starListOfRecommendFragment = new StarListOfRecommendFragment();
        this.mTabsAdapter = new a(this, getActivity(), getChildFragmentManager(), new com.sds.android.ttpod.adapter.e.a[]{new com.sds.android.ttpod.adapter.e.a(0, (int) R.string.findfriend_recommend, 0, starListOfRecommendFragment), new com.sds.android.ttpod.adapter.e.a(1, (int) R.string.findfriend_rank, 0, new StarRankFragment()), new com.sds.android.ttpod.adapter.e.a(2, (int) R.string.findfriend_category, 0, new StarCategoryFragment())});
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        this.mViewPager.setAdapter(this.mTabsAdapter);
        setSlidingCloseMode(2);
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.slidingtabshost);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mSlidingTabHost.setViewPager(this.mViewPager);
        this.mSlidingTabHost.setOnPageChangeListener(new h(this, this, this.mCurrentItem, PAGE_LIST) {
            final /* synthetic */ FindFriendsFragment a;

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                super.onPageSelected(i);
                this.a.mCurrentItem = i;
                int i2 = 0;
                if (this.a.isSlidingAtTheLeftEdge(i)) {
                    i2 = 2;
                } else if (this.a.isSlidingAtTheRightEdge(i)) {
                    i2 = 1;
                }
                this.a.setSlidingCloseMode(i2);
                switch (i) {
                    case 0:
                        c.p();
                        return;
                    case 1:
                        c.s();
                        return;
                    case 2:
                        c.t();
                        return;
                    default:
                        return;
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private boolean isSlidingAtTheLeftEdge(int i) {
        return i == 0;
    }

    private boolean isSlidingAtTheRightEdge(int i) {
        return i == this.mTabsAdapter.getCount() + -1;
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mRadarAction.a(null);
        this.mShakeAction.a(null);
        this.mSearchAction.a(null);
        this.mSlidingTabHost.setOnPageChangeListener(null);
        this.mViewPager.setOnPageChangeListener(null);
        this.mSlidingTabHost = null;
        this.mViewPager = null;
        this.mTabsAdapter.a();
        this.mTabsAdapter = null;
    }
}
