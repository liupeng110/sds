package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.data.AlbumItem;
import com.sds.android.cloudapi.ttpod.result.AlbumItemsResult;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.activities.musiccircle.SlidingAlbumDetailFragment;
import com.sds.android.ttpod.activities.musiccircle.b;
import com.sds.android.ttpod.adapter.e.a;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.main.e;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.a.y;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.g;
import java.lang.reflect.Method;
import java.util.Map;

public class SingerAlbumCategoryFragment extends BaseFragment implements a {
    private static final int HEADER_VIEW_HEIGHT_DP = 8;
    private static final int HOME_PAGE = 1;
    private static final int PAGE_SIZE = 10;
    private static final int SPACING_BETWEEN_ITEMS = 60;
    private static final int TEXT_HEIGHT = 50;
    private a mAlbumAdapter;
    private View mAlbumEmptyHeaderView;
    private AlbumItemsResult mAlbumItemsResult;
    private ListView mAlbumListView;
    private b mFooter;
    private View mHeaderView;
    private View mListLessItemFooterView;
    private boolean mLoading = false;
    private a.b mOnAlbumItemClickListener = new a.b(this) {
        final /* synthetic */ SingerAlbumCategoryFragment a;

        {
            this.a = r1;
        }

        public void a(int i) {
            if (!y.a()) {
                AlbumItem a = this.a.mAlbumAdapter.a(i);
                this.a.doAlbumItemClickStatistic(i, a);
                new com.sds.android.ttpod.framework.a.b.b().a("location", String.valueOf(i)).a("album_id", String.valueOf(a.getId())).a("album_name", a.getName()).a();
                this.a.launchFragment(SlidingAlbumDetailFragment.instantiate(a.getId(), a.getName()));
            }
        }
    };
    private q mPager = new q();
    private View mRootView;
    private int mSingerId;
    private StateView mStateView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        setStatisticPage(s.PAGE_SINGER_ALBUM);
        getSingerName();
        initViews(layoutInflater, viewGroup);
        requestAlbums(1);
        return this.mRootView;
    }

    private void getSingerName() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalArgumentException("singer album argument--singer name must not be null");
        }
        this.mSingerId = arguments.getInt(SingerDetailFragment.KEY_SINGER_ID);
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_SEARCH_ALBUM_SINGERID_FINISHED, i.a(getClass(), "updateSearchAlbumResult", AlbumItemsResult.class));
    }

    public void onLoadFinished() {
        super.onLoadFinished();
        updateSearchAlbumList(this.mAlbumItemsResult);
    }

    private void initViews(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_singer_album, viewGroup, false);
        this.mAlbumListView = (ListView) this.mRootView.findViewById(R.id.listview_album);
        this.mStateView = new g(getActivity()).b();
        this.mStateView.setOnRetryRequestListener(new StateView.a(this) {
            final /* synthetic */ SingerAlbumCategoryFragment a;

            {
                this.a = r1;
            }

            public void onRetryRequested() {
                this.a.requestAlbums(1);
            }
        });
        this.mFooter = new b(layoutInflater, new OnClickListener(this) {
            final /* synthetic */ SingerAlbumCategoryFragment a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.requestAlbums(this.a.mPager.a());
            }
        });
        this.mAlbumListView.addHeaderView(getHeaderView());
        this.mAlbumEmptyHeaderView = getEmptyHeaderView();
        this.mAlbumListView.addHeaderView(this.mAlbumEmptyHeaderView);
        this.mAlbumListView.addFooterView(this.mStateView);
        this.mAlbumAdapter = new a(getActivity());
        this.mAlbumAdapter.a(this.mOnAlbumItemClickListener);
        this.mAlbumListView.setAdapter(this.mAlbumAdapter);
    }

    private void updateEmptyHeaderHeight(boolean z) {
        LayoutParams layoutParams = this.mAlbumEmptyHeaderView.getLayoutParams();
        if (z) {
            layoutParams.height = com.sds.android.ttpod.common.c.a.a(8);
        } else {
            layoutParams.height = 0;
        }
        this.mAlbumEmptyHeaderView.setLayoutParams(layoutParams);
    }

    public void requestNextPage(AbsListView absListView, int i, int i2, int i3) {
        if (m.b(i, i2, i3) && !this.mLoading && this.mPager.h()) {
            requestAlbums(this.mPager.d());
        }
    }

    private View getHeaderView() {
        this.mHeaderView = new g(getActivity()).a();
        return this.mHeaderView;
    }

    private View getEmptyHeaderView() {
        View view = new View(getActivity());
        view.setLayoutParams(new AbsListView.LayoutParams(-1, 0));
        view.setBackgroundColor(0);
        return view;
    }

    private void doAlbumItemClickStatistic(int i, AlbumItem albumItem) {
        SEvent append = new SUserEvent("PAGE_CLICK", r.ACTION_SINGER_ALBUM_ITEM.getValue(), s.PAGE_NONE.getValue(), s.PAGE_ALBUM_DETAIL.getValue()).append("singer_id", Integer.valueOf(albumItem.getSingerId())).append("song_album_id", Long.valueOf(albumItem.getId())).append("position", Integer.valueOf(i + 1));
        append.setPageParameter(true);
        append.post();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mAlbumAdapter.notifyDataSetChanged();
        this.mStateView.onThemeLoaded();
        this.mFooter.onThemeLoaded();
        c.a(this.mAlbumListView, ThemeElement.BACKGROUND_MASK);
    }

    private void requestAlbums(int i) {
        this.mLoading = true;
        this.mPager.c(i);
        if (this.mPager.a() == 1) {
            this.mStateView.setState(StateView.b.LOADING);
        } else {
            this.mFooter.a(false, 0, getString(R.string.page_loading));
        }
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.START_SEARCH_ALBUM_SINGERID, Integer.valueOf(this.mSingerId), Integer.valueOf(i), Integer.valueOf(10), ""));
    }

    public void updateSearchAlbumResult(AlbumItemsResult albumItemsResult) {
        this.mAlbumItemsResult = albumItemsResult;
        e.a(this, albumItemsResult, new e.a<AlbumItemsResult>(this) {
            final /* synthetic */ SingerAlbumCategoryFragment a;

            {
                this.a = r1;
            }

            public void a(AlbumItemsResult albumItemsResult) {
                this.a.updateSearchAlbumList(albumItemsResult);
            }
        });
    }

    public void updateSearchAlbumList(AlbumItemsResult albumItemsResult) {
        this.mLoading = false;
        if (checkSuccess(albumItemsResult)) {
            this.mPager.b(albumItemsResult.getAllPage());
            updateSuccessStateView();
            if (this.mPager.a() > 1) {
                this.mAlbumAdapter.a().addAll(albumItemsResult.getDataList());
            } else {
                this.mAlbumAdapter.a(albumItemsResult.getDataList());
                updateEmptyHeaderHeight(true);
                if (!this.mPager.h()) {
                    int[] iArr = new int[2];
                    this.mAlbumListView.getLocationInWindow(iArr);
                    this.mListLessItemFooterView = new g(getActivity()).a((((com.sds.android.ttpod.common.c.a.d() - com.sds.android.ttpod.common.c.a.a(60)) / 2) + com.sds.android.ttpod.common.c.a.a(50)) * ((albumItemsResult.getDataList().size() + 1) / 2), iArr[1]);
                    this.mAlbumListView.addFooterView(this.mListLessItemFooterView);
                }
            }
            this.mAlbumAdapter.notifyDataSetChanged();
        }
    }

    private boolean checkSuccess(AlbumItemsResult albumItemsResult) {
        if (!isViewAccessAble() || albumItemsResult == null) {
            return false;
        }
        boolean z = !albumItemsResult.isSuccess();
        boolean a = j.a(albumItemsResult.getDataList());
        if (this.mPager.f()) {
            if (z) {
                updateEmptyHeaderHeight(false);
                this.mStateView.setState(StateView.b.FAILED);
                return false;
            } else if (a) {
                updateEmptyHeaderHeight(false);
                this.mStateView.setState(StateView.b.NO_DATA);
                return false;
            }
        } else if (z || a) {
            this.mFooter.a(true, 8, getString(R.string.loading_failed));
            return false;
        }
        return true;
    }

    private void updateSuccessStateView() {
        if (this.mPager.f()) {
            this.mStateView.setState(StateView.b.SUCCESS);
            this.mAlbumListView.removeFooterView(this.mStateView);
            this.mAlbumListView.addFooterView(this.mFooter.a());
        } else {
            this.mFooter.a(true, 8, "");
        }
        if (this.mPager.a() == this.mPager.g()) {
            this.mAlbumListView.removeFooterView(this.mFooter.a());
        }
    }

    public ListView getSingerListView() {
        return this.mAlbumListView;
    }

    public void requestRefreshView(Bundle bundle) {
        updateEmptyHeaderHeight(false);
        this.mAlbumListView.removeFooterView(this.mListLessItemFooterView);
        this.mAlbumListView.removeFooterView(this.mFooter.a());
        this.mAlbumListView.removeFooterView(this.mStateView);
        this.mAlbumListView.addFooterView(this.mStateView);
        if (!(this.mAlbumAdapter == null || this.mAlbumAdapter.a() == null)) {
            this.mAlbumAdapter = new a(getActivity());
            this.mAlbumAdapter.a(this.mOnAlbumItemClickListener);
            this.mAlbumListView.setAdapter(this.mAlbumAdapter);
        }
        if (bundle != null) {
            this.mSingerId = bundle.getInt(SingerDetailFragment.KEY_SINGER_ID);
            this.mPager.a(true);
            this.mPager.c(1);
            requestAlbums(1);
            return;
        }
        throw new IllegalArgumentException("getArguments() singerId must not be null");
    }

    public View getSingerListHeaderView() {
        return this.mHeaderView;
    }
}
