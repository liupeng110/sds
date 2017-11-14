package com.sds.android.ttpod.fragment.main.findsong;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.cloudapi.ttpod.result.FirstPublishNewSongCategoryResult;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.PostDetailFragment;
import com.sds.android.ttpod.adapter.e;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SlidingTabHost;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.h;
import com.sds.android.ttpod.widget.h.a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BestAlbumFromNewSongFragment extends SlidingClosableFragment {
    private ArrayList<a> mBestList = new ArrayList();
    private int mCurrentItem = 0;
    private e mMainFragmentPagerAdapter;
    private String mModuleId = "";
    private FirstPublishNewSongCategoryResult mResult;
    private View mRootView;
    private SlidingTabHost mSlidingTabHost;
    private StateView mStateLoadingView;
    private ViewPager mViewPager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatisticPage(s.PAGE_NONE);
        this.mModuleId = t.a().b();
        trackModule(this.mModuleId);
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_best_album_from_new_song, viewGroup, false);
        this.mViewPager = (ViewPager) this.mRootView.findViewById(R.id.viewpager);
        this.mViewPager.setCurrentItem(this.mCurrentItem);
        this.mSlidingTabHost = (SlidingTabHost) this.mRootView.findViewById(R.id.sliding_tabs_host_best_album);
        this.mStateLoadingView = (StateView) this.mRootView.findViewById(R.id.best_album_load_view);
        this.mStateLoadingView.setOnRetryRequestListener(new StateView.a(this) {
            final /* synthetic */ BestAlbumFromNewSongFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.loadData();
            }
        });
        getActionBarController().b((int) R.string.best_album);
        loadData();
        return this.mRootView;
    }

    private void loadData() {
        this.mStateLoadingView.setState(b.LOADING);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.REQUEST_NEW_SONG_CATEGORY_PUBLISH_LIST, new Object[0]));
    }

    private void bindView() {
        this.mMainFragmentPagerAdapter = new e(getActivity(), getChildFragmentManager(), buildFragmentBinders());
        this.mViewPager.setAdapter(this.mMainFragmentPagerAdapter);
        attachSlidingTabHost(this.mSlidingTabHost, this.mViewPager);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_NEW_SONG_CATEGORY_PUBLISH_LIST, i.a(getClass(), "updateNewSongCategory", FirstPublishNewSongCategoryResult.class));
    }

    public void updateNewSongCategory(FirstPublishNewSongCategoryResult firstPublishNewSongCategoryResult) {
        if (firstPublishNewSongCategoryResult.isSuccess()) {
            this.mResult = firstPublishNewSongCategoryResult;
            bindView();
            this.mStateLoadingView.setState(b.SUCCESS);
            this.mStateLoadingView.setVisibility(8);
            return;
        }
        this.mStateLoadingView.setState(b.FAILED);
    }

    private void attachSlidingTabHost(SlidingTabHost slidingTabHost, ViewPager viewPager) {
        slidingTabHost.setTabLayoutAverageSpace(true);
        slidingTabHost.setViewPager(viewPager);
        slidingTabHost.setOnPageChangeListener(new h(this, this, this.mCurrentItem, this.mBestList) {
            final /* synthetic */ BestAlbumFromNewSongFragment a;

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                super.onPageSelected(i);
                this.a.doStatisticWhenPageSelected(i);
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    private void doStatisticWhenPageSelected(int i) {
        if (this.mResult != null && this.mResult.getSingleList().size() >= i + 1) {
            MusicCircleFirstPublish musicCircleFirstPublish = (MusicCircleFirstPublish) this.mResult.getSingleList().get(i);
            String valueOf = String.valueOf(musicCircleFirstPublish.getMsgId());
            String title = musicCircleFirstPublish.getTitle();
            SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_ONLINE_BEST_ALBUM_PAGE_SELECTED.getValue(), String.valueOf(s.PAGE_ONLINE_BEST_ALBUM_FROM_NEW_SONG.getValue()), title);
            sUserEvent.setPageParameter(true);
            sUserEvent.append("position", Integer.valueOf(i + 1));
            sUserEvent.append(BaseFragment.KEY_SONG_LIST_ID, valueOf);
            sUserEvent.append("song_list_name", title);
            sUserEvent.post();
            trackPlaySong("post", valueOf, true);
        }
    }

    public void onDestroyView() {
        if (this.mMainFragmentPagerAdapter != null) {
            this.mMainFragmentPagerAdapter.a();
        }
        super.onDestroyView();
        if (this.mResult == null || this.mResult.getSingleList() != null) {
        }
    }

    private List<e.a> buildFragmentBinders() {
        List<e.a> arrayList = new ArrayList();
        ArrayList singleList = this.mResult.getSingleList();
        this.mBestList.clear();
        Iterator it = singleList.iterator();
        while (it.hasNext()) {
            MusicCircleFirstPublish musicCircleFirstPublish = (MusicCircleFirstPublish) it.next();
            Fragment createById = PostDetailFragment.createById(musicCircleFirstPublish.getMsgId(), "BestAlbum");
            createById.initBundle(musicCircleFirstPublish.getTitle(), String.valueOf(musicCircleFirstPublish.getMsgId()));
            createById.setStatisticPage(musicCircleFirstPublish.getTitle());
            arrayList.add(new e.a(0, musicCircleFirstPublish.getTitle(), 0, createById));
            this.mBestList.add(new a(this.mModuleId, "tt_best_album_" + musicCircleFirstPublish.getTitle()));
        }
        setTBSPage(((a) this.mBestList.get(this.mCurrentItem)).c());
        this.mAlibabaStatsPageContext.c();
        if (singleList.size() > 0) {
            trackPlaySong("post", String.valueOf(((MusicCircleFirstPublish) singleList.get(0)).getMsgId()), true);
        }
        this.mViewPager.setOffscreenPageLimit(singleList.size());
        return arrayList;
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mStateLoadingView.onThemeLoaded();
        v.a(this.mSlidingTabHost);
        c.a(this.mRootView.findViewById(R.id.best_album_background), ThemeElement.BACKGROUND_MASK);
    }
}
