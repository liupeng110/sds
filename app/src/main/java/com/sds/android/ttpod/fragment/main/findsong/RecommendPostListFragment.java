package com.sds.android.ttpod.fragment.main.findsong;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.a.r;
import com.sds.android.cloudapi.ttpod.data.OnlineMediaItem;
import com.sds.android.cloudapi.ttpod.data.RecommendPost;
import com.sds.android.cloudapi.ttpod.result.OnlineMediaItemsResult;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SubPostDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.b.d.t;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.a.o;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.support.a.f;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.dragupdatelist.DragUpdateListView;
import com.sds.android.ttpod.widget.dragupdatelist.a.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class RecommendPostListFragment extends SlidingClosableFragment implements OnScrollListener, OnItemClickListener {
    public static final int PAGE_SIZE = 10;
    private c mAdapter;
    private b mFooter;
    private a mLoadStatus = a.IDLE;
    private q mPager = new q();
    private com.sds.android.ttpod.fragment.musiccircle.a mProxy;

    public enum a {
        IDLE,
        FIRST_LOAD,
        RELOAD,
        NEXT_PAGE
    }

    protected abstract void onLoadData();

    public abstract void playMediaItemStatistic(long j, int i);

    public abstract void startPostDetailStatistic(long j, int i);

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mProxy = new com.sds.android.ttpod.fragment.musiccircle.a(new OnClickListener(this) {
            final /* synthetic */ RecommendPostListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.reloadData();
            }
        }, new c(this) {
            final /* synthetic */ RecommendPostListFragment a;

            {
                this.a = r1;
            }

            public void onStartRefreshEvent() {
                this.a.reloadData();
            }
        });
        View a = this.mProxy.a(layoutInflater, viewGroup);
        initView(layoutInflater, this.mProxy.a());
        return a;
    }

    private void reloadData() {
        this.mLoadStatus = a.RELOAD;
        onLoadData();
    }

    private void initView(LayoutInflater layoutInflater, ListView listView) {
        this.mAdapter = onCreateAdapter();
        listView.setAdapter(this.mAdapter);
        initFooterView(layoutInflater, listView);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (m.b(i, i2, i3) && this.mLoadStatus == a.IDLE && getDataCount() > 0) {
            onRequestNextPage();
        }
    }

    private void initFooterView(LayoutInflater layoutInflater, ListView listView) {
        this.mFooter = new b(layoutInflater, new OnClickListener(this) {
            final /* synthetic */ RecommendPostListFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onRequestNextPage();
            }
        });
        listView.addFooterView(this.mFooter.a());
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mFooter != null) {
            this.mFooter.onThemeLoaded();
        }
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mProxy.a(), ThemeElement.BACKGROUND_MASK);
        StateView b = this.mProxy.b();
        if (b != null) {
            b.onThemeLoaded();
        }
        DragUpdateListView dragUpdateListView = (DragUpdateListView) this.mProxy.a();
        if (dragUpdateListView != null) {
            ColorStateList c = com.sds.android.ttpod.framework.modules.theme.c.c(ThemeElement.COMMON_CONTENT_TEXT);
            if (c != null) {
                dragUpdateListView.setLoadingTitleColor(c);
            }
            refreshListViewTheme();
        }
    }

    protected void refreshListViewTheme() {
        if (this.mProxy.a() != null) {
            int childCount = this.mProxy.a().getChildCount();
            for (int i = 0; i < childCount; i++) {
                Object tag = this.mProxy.a().getChildAt(i).getTag();
                if (tag instanceof d) {
                    this.mAdapter.a((d) tag);
                }
            }
        }
    }

    protected c onCreateAdapter() {
        return new c(this, getActivity()) {
            final /* synthetic */ RecommendPostListFragment a;

            protected void a(RecommendPost recommendPost, d dVar, int i) {
                this.a.playMediaItemStatistic(recommendPost.getId(), i);
                a((Object) recommendPost);
                this.a.requestPlayMediaView(recommendPost);
            }
        };
    }

    public List<Long> getPlaySongId(RecommendPost recommendPost) {
        List<Long> arrayList = new ArrayList();
        Iterator it = recommendPost.getSongList().iterator();
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((OnlineMediaItem) it.next()).getSongId()));
        }
        return arrayList;
    }

    private List<MediaItem> convertMediaList(List<OnlineMediaItem> list) {
        List<MediaItem> arrayList = new ArrayList();
        for (OnlineMediaItem a : list) {
            arrayList.add(k.a(a));
        }
        return arrayList;
    }

    public void requestPlayMediaView(final RecommendPost recommendPost) {
        com.sds.android.sdk.lib.e.a.a((Object) this, new com.sds.android.sdk.lib.e.a.a<List<Long>, List<MediaItem>>(this, getPlaySongId(recommendPost)) {
            final /* synthetic */ RecommendPostListFragment b;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a((List) obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                b((List) obj);
            }

            protected List<MediaItem> a(List<Long> list) {
                return this.b.convertMediaList(((OnlineMediaItemsResult) r.a((Collection) list).g()).getDataList());
            }

            protected void b(List<MediaItem> list) {
                if (!(list == null || list.isEmpty() || recommendPost == null)) {
                    t.a().a("post", String.valueOf(recommendPost.getId()), true);
                }
                this.b.playMediaList(list, recommendPost.getId());
            }
        });
    }

    public void playMediaList(List<MediaItem> list, long j) {
        if (!list.isEmpty()) {
            o.a((List) list, com.sds.android.ttpod.component.c.c.c(String.valueOf(j)), j);
            com.sds.android.ttpod.framework.storage.environment.b.a(f.REPEAT);
        }
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        this.mLoadStatus = a.FIRST_LOAD;
        setLoadingState(StateView.b.LOADING);
        onLoadData();
    }

    public void setLoadingState(StateView.b bVar) {
        this.mProxy.a(bVar);
    }

    public void updateDataListView(List<RecommendPost> list) {
        String string;
        this.mProxy.c();
        if (this.mLoadStatus == a.NEXT_PAGE) {
            this.mAdapter.b().addAll(list);
        } else {
            this.mAdapter.a((List) list);
        }
        this.mAdapter.notifyDataSetChanged();
        if (list.isEmpty()) {
            string = getString(R.string.loading_failed);
        } else {
            string = getString(R.string.num_loaded_data, Integer.valueOf(this.mAdapter.getCount()));
        }
        this.mFooter.a(list.isEmpty(), 8, string);
        this.mLoadStatus = a.IDLE;
    }

    protected void handleResult(List<RecommendPost> list, com.sds.android.sdk.lib.request.f fVar, int i) {
        if (j.a(list) && a.NEXT_PAGE == getLoadStatus()) {
            updateFooterView(i == -1 ? StateView.b.FAILED : StateView.b.NO_DATA);
        } else if (j.a(list)) {
            setLoadingState(StateView.b.FAILED);
        } else {
            setLoadingState(StateView.b.SUCCESS);
            handleExtra(fVar);
            updateDataListView(list);
        }
    }

    private void updateFooterView(StateView.b bVar) {
        if (StateView.b.LOADING == bVar) {
            this.mFooter.a(false, 0, getString(R.string.loading));
        } else if (StateView.b.FAILED == bVar) {
            this.mFooter.a(true, 8, getString(R.string.network_error));
        } else {
            this.mFooter.a(false, 8, getString(R.string.num_loaded_data, Integer.valueOf(getDataCount())));
            this.mProxy.a().setOnScrollListener(null);
        }
    }

    public q getPager() {
        return this.mPager;
    }

    private void handleExtra(com.sds.android.sdk.lib.request.f fVar) {
        if (fVar != null) {
            this.mPager.b(fVar.b());
            this.mPager.e();
            if (!this.mPager.h()) {
                updateFooterView(StateView.b.NO_DATA);
            }
        }
    }

    public List<RecommendPost> getDataList() {
        return this.mAdapter.b();
    }

    public int getDataCount() {
        return this.mAdapter.getCount();
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mAdapter != null) {
            int a = m.a(((ListView) adapterView).getHeaderViewsCount(), i, getDataCount());
            RecommendPost recommendPost = (RecommendPost) this.mAdapter.getItem(a);
            startPostDetailStatistic(recommendPost.getId(), a);
            this.mAdapter.a((Object) recommendPost);
            launchFragment(SubPostDetailFragment.createById(recommendPost.getId(), getClass().getSimpleName()));
        }
    }

    protected void onRequestNextPage() {
        updateFooterView(StateView.b.LOADING);
        this.mLoadStatus = a.NEXT_PAGE;
        onLoadData();
    }

    public a getLoadStatus() {
        return this.mLoadStatus;
    }
}
