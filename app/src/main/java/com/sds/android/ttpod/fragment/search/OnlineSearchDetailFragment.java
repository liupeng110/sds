package com.sds.android.ttpod.fragment.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.SearchType;
import com.sds.android.cloudapi.ttpod.result.SearchTypeResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.main.list.AbsMediaListFragment;
import com.sds.android.ttpod.fragment.main.list.OnlineMediaListFragment.c;
import com.sds.android.ttpod.framework.a.b.d;
import com.sds.android.ttpod.framework.a.b.j;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.b.u;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.h;
import com.sds.android.ttpod.widget.h.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OnlineSearchDetailFragment extends BaseFragment {
    private static final long ID_FRAGMENT_ALBUM = 2;
    private static final long ID_FRAGMENT_MV = 4;
    private static final long ID_FRAGMENT_SINGER = 1;
    private static final long ID_FRAGMENT_SONG = 0;
    private static final long ID_FRAGMENT_SONG_LIST = 3;
    private static final ArrayList<a> LIST_ALI_PAGE = new ArrayList<a>() {
        {
            add(new a("online_search", "tt_online_search_song", "tt_online_search_song"));
            add(new a("online_search", "tt_online_search_singer", "tt_online_search_singer"));
            add(new a("online_search", "tt_online_search_album", "tt_online_search_album"));
            add(new a("online_search", "tt_online_search_songlist", "tt_online_search_songlist"));
            add(new a("online_search", "tt_online_search_mv", "tt_online_search_mv"));
        }
    };
    private static final ArrayList<com.sds.android.ttpod.framework.a.b.a> SLIST = new ArrayList<com.sds.android.ttpod.framework.a.b.a>(4) {
    };
    private static final String TAG = "OnlineSearchDetailFragment";
    private int mAction;
    private AlbumSearchFragment mAlbumSearchFragment;
    private int mCurTabPosition;
    private String mInputWord;
    private MvSearchFragment mMvSearchFragment;
    private e mPagerAdapter;
    private View mRootView;
    private SingerSearchFragment mSingerSearchFragment;
    private SlidingTabHost mSlidingTabHost;
    private PlaylistSearchFragment mSongListSearchFragment;
    private SongSearchFragment mSongSearchFragment;
    private ViewPager mViewPager;
    private String mWord;

    public static OnlineSearchDetailFragment instantiate(String str, r rVar, String str2) {
        OnlineSearchDetailFragment onlineSearchDetailFragment = new OnlineSearchDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("search_word", str);
        bundle.putString("input_word", str2);
        bundle.putInt(PushConsts.CMD_ACTION, rVar.getValue());
        onlineSearchDetailFragment.setArguments(bundle);
        return onlineSearchDetailFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mWord = arguments.getString("search_word");
            this.mAction = arguments.getInt(PushConsts.CMD_ACTION, 0);
            this.mInputWord = arguments.getString("input_word");
        }
    }

    public void onDestroyView() {
        if (this.mPagerAdapter != null) {
            this.mPagerAdapter.a();
        }
        super.onDestroyView();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_online_search_detail, viewGroup, false);
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.tabhost);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
        this.mViewPager.setCurrentItem(this.mCurTabPosition);
        this.mPagerAdapter = new e(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mPagerAdapter);
        this.mSlidingTabHost.setViewPager(this.mViewPager);
        this.mSlidingTabHost.setOnPageChangeListener(new h(this, this, this.mCurTabPosition, LIST_ALI_PAGE) {
            final /* synthetic */ OnlineSearchDetailFragment a;

            public void onPageSelected(int i) {
                this.a.updateAlibabaProperty("keyword", this.a.mWord);
                this.a.updateAlibabaProperty("search_type", d.r.a());
                BaseFragment baseFragment = (BaseFragment) this.a.getParentFragment();
                baseFragment.updateAlibabaProperty("keyword", this.a.mWord);
                baseFragment.updateAlibabaProperty("search_type", d.r.a());
                super.onPageSelected(i);
                this.a.trackPlaySong("search", "search_" + this.a.mWord, true);
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", ((com.sds.android.ttpod.framework.a.b.a) OnlineSearchDetailFragment.SLIST.get(i)).a().getValue(), s.PAGE_NONE.getValue(), ((com.sds.android.ttpod.framework.a.b.a) OnlineSearchDetailFragment.SLIST.get(i)).b().getValue());
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
                this.a.mCurTabPosition = i;
                ((b) this.a.currentFragment()).onFragmentSelected(i, this.a.mWord, this.a.mInputWord);
                ((b) this.a.getParentFragment()).onFragmentSelected(i, this.a.mWord, this.a.mInputWord);
            }
        });
        setTBSPage("tt_online_search_song");
        trackPlaySong("search", "search_" + this.mWord, true);
        trackSearch(d.r.a(), this.mWord);
        ((BaseFragment) getParentFragment()).trackSearch(d.r.a(), this.mWord);
        this.mViewPager.setOffscreenPageLimit(this.mPagerAdapter.getCount());
        return this.mRootView;
    }

    public void onPause() {
        updateAlibabaProperty("keyword", this.mWord);
        updateAlibabaProperty("search_type", d.r.a());
        super.onPause();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.mSlidingTabHost.setTabLayoutAverageSpace(true);
        this.mSlidingTabHost.setShouldExpand(true);
        this.mSlidingTabHost.setBackgroundResource(R.color.dialog_background);
        this.mSlidingTabHost.setTextColorResource(R.color.xml_local_media_search_tab_text);
        this.mSlidingTabHost.setIndicatorColorResource(R.color.listview_item_title_selected);
        getSongFragment().setOnNextPageListener(new c(this) {
            final /* synthetic */ OnlineSearchDetailFragment a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.getSongFragment().requestSongList(this.a.mWord, i);
            }
        });
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        if (!m.a(this.mWord)) {
            requestDetailInfo(this.mWord, this.mAction, this.mInputWord);
        }
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mRootView, ThemeElement.BACKGROUND_MASK);
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mSlidingTabHost, ThemeElement.SUB_BAR_BACKGROUND);
        v.a(this.mSlidingTabHost);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_TYPES, i.a(cls, "updateSearchTypes", SearchTypeResult.class));
    }

    protected boolean isTheRightFragment(int i) {
        return this.mPagerAdapter != null && i == this.mPagerAdapter.getCount() - 1;
    }

    void hideSoftInputFromWindow() {
        ((OnlineSearchFragment) getParentFragment()).hideSoftInputFromWindow();
    }

    protected List<e.a> buildFragmentBinders() {
        List<e.a> arrayList = new ArrayList();
        arrayList.add(new e.a(0, (int) R.string.tab_song, 0, getSongFragment()));
        arrayList.add(new e.a(1, (int) R.string.tab_singer, 0, getSingerFragment()));
        arrayList.add(new e.a(2, (int) R.string.tab_album, 0, getAlbumFragment()));
        arrayList.add(new e.a(3, (int) R.string.tab_song_list, 0, getSongListSearchFragment()));
        arrayList.add(new e.a((long) ID_FRAGMENT_MV, (int) R.string.tab_mv, 0, getMvSearchFragment()));
        return arrayList;
    }

    private SongSearchFragment getSongFragment() {
        if (this.mSongSearchFragment == null) {
            this.mSongSearchFragment = new SongSearchFragment();
            Bundle bundle = new Bundle();
            bundle.putString(AbsMediaListFragment.KEY_GROUP_ID, MediaStorage.GROUP_ID_ONLINE_TEMPORARY);
            this.mSongSearchFragment.setArguments(bundle);
            this.mSongSearchFragment.setStatisticPage(s.PAGE_SEARCH_SINGLE_SONG);
        }
        return this.mSongSearchFragment;
    }

    private AlbumSearchFragment getAlbumFragment() {
        if (this.mAlbumSearchFragment == null) {
            this.mAlbumSearchFragment = new AlbumSearchFragment();
            this.mAlbumSearchFragment.setArguments(getArguments());
            this.mAlbumSearchFragment.setStatisticPage(s.PAGE_SEARCH_ALBUM);
        }
        return this.mAlbumSearchFragment;
    }

    private SingerSearchFragment getSingerFragment() {
        if (this.mSingerSearchFragment == null) {
            this.mSingerSearchFragment = new SingerSearchFragment();
            this.mSingerSearchFragment.setArguments(getArguments());
            this.mSingerSearchFragment.setStatisticPage(s.PAGE_SEARCH_SINGER);
        }
        return this.mSingerSearchFragment;
    }

    private PlaylistSearchFragment getSongListSearchFragment() {
        if (this.mSongListSearchFragment == null) {
            this.mSongListSearchFragment = new PlaylistSearchFragment();
            this.mSongListSearchFragment.setArguments(getArguments());
            this.mSongListSearchFragment.setStatisticPage(s.PAGE_SEARCH_SONG_LIST);
        }
        return this.mSongListSearchFragment;
    }

    private MvSearchFragment getMvSearchFragment() {
        if (this.mMvSearchFragment == null) {
            this.mMvSearchFragment = new MvSearchFragment();
            this.mMvSearchFragment.setArguments(getArguments());
            this.mMvSearchFragment.setStatisticPage(s.PAGE_SEARCH_SONG_LIST);
        }
        return this.mMvSearchFragment;
    }

    public Fragment currentFragment() {
        return this.mPagerAdapter.getItem(this.mViewPager.getCurrentItem());
    }

    private void tabFragmentSearch(String str) {
        u.d(str);
        j.a(str);
        Fragment currentFragment = currentFragment();
        if (currentFragment instanceof a) {
            ((a) currentFragment).search(str, this.mInputWord);
        }
    }

    protected void requestDetailInfo(String str, int i, String str2) {
        this.mAction = i;
        this.mWord = str;
        this.mInputWord = str2;
        b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SEARCH_TYPES, new Object[0]));
    }

    public void updateSearchTypes(SearchTypeResult searchTypeResult) {
        int currentItem = this.mViewPager.getCurrentItem();
        int appreciatedSearchTab = getAppreciatedSearchTab(searchTypeResult, currentItem);
        if (appreciatedSearchTab != currentItem) {
            this.mViewPager.setCurrentItem(appreciatedSearchTab);
        } else {
            tabFragmentSearch(this.mWord);
        }
    }

    private int getAppreciatedSearchTab(SearchTypeResult searchTypeResult, int i) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = searchTypeResult.getDataList().iterator();
        ArrayList arrayList3 = arrayList;
        arrayList = arrayList2;
        while (it.hasNext()) {
            SearchType searchType = (SearchType) it.next();
            if ("album".equals(searchType.getId())) {
                arrayList3 = searchType.getWords();
            }
            if ("playlist".equals(searchType.getId())) {
                arrayList2 = searchType.getWords();
            } else {
                arrayList2 = arrayList;
            }
            arrayList = arrayList2;
        }
        if (arrayList3.contains(this.mWord)) {
            return 1;
        }
        if (arrayList.contains(this.mWord)) {
            return 2;
        }
        return i;
    }
}
