package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import com.sds.android.cloudapi.ttpod.data.VIPPolicy.Entry;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.e.d;
import com.sds.android.ttpod.adapter.e.d.a;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.a.q;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.SimpleSlidingTabHost;
import com.sds.android.ttpod.widget.StateView;
import com.sds.android.ttpod.widget.StateView.b;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;
import java.util.ArrayList;
import java.util.List;

public abstract class SingerTabFragment extends SlidingClosableFragment implements OnScrollListener, OnItemClickListener, OnItemLongClickListener {
    private static final int PAGE_1 = 1;
    private static final String TAG = "SingerTabFragment";
    private BaseAdapter mAdapter;
    private View mFailedView;
    protected DataListFooterView mFooterView;
    private String mGroupID;
    private View mHeaderView;
    private SparseBooleanArray mIsErrorNotFirstPage;
    protected ActionExpandableListView mListView;
    private SparseBooleanArray mLoadingStateArray;
    private String mModule;
    private View mNodataView;
    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener(this) {
        final /* synthetic */ SingerTabFragment a;

        {
            this.a = r1;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            this.a.pageSelected(i);
        }

        public void onPageScrollStateChanged(int i) {
        }
    };
    private String mOrigin;
    private SparseArray<q> mPagerArray;
    private View mRootView;
    private b mSingerHeader;
    private SimpleSlidingTabHost mSlidingTabHost;
    private StateView mStateView;
    protected int mTab = 0;
    private int mTabCount;

    protected abstract BaseAdapter getAdapter(int i);

    protected abstract b initHeaderSingerView();

    protected abstract List<a> onBuildTabBinders();

    protected abstract void onItemClick(int i, AdapterView<?> adapterView, View view, int i2, long j);

    protected abstract boolean onItemLongClick(int i, AdapterView<?> adapterView, View view, int i2, long j);

    protected abstract void onRequestData(int i, int i2);

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mRootView == null) {
            this.mRootView = layoutInflater.inflate(R.layout.fragment_singer_tab, null, false);
            this.mStateView = (StateView) this.mRootView.findViewById(R.id.empty);
            this.mHeaderView = layoutInflater.inflate(R.layout.singer_header, null);
            this.mSingerHeader = initHeaderSingerView();
            ((ViewGroup) this.mHeaderView.findViewById(R.id.header_layout)).addView(this.mSingerHeader.b());
            this.mListView = (ActionExpandableListView) this.mRootView.findViewById(R.id.action_expandable_list_view);
            this.mSlidingTabHost = (SimpleSlidingTabHost) this.mHeaderView.findViewById(R.id.slidingtabhost);
            initSlidingTabHost(this.mSlidingTabHost);
            this.mListView.addHeaderView(this.mHeaderView);
            onCreateListFooterView(layoutInflater);
            this.mFailedView = onCreateFailedView(layoutInflater);
            this.mStateView.setFailedView(this.mFailedView);
            this.mNodataView = onCreateNodataView(layoutInflater);
            this.mStateView.setNodataView(this.mNodataView);
            this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ SingerTabFragment a;

                {
                    this.a = r1;
                }

                public void onGlobalLayout() {
                    ((MarginLayoutParams) this.a.mStateView.getLayoutParams()).setMargins(0, this.a.mHeaderView.getHeight(), 0, 0);
                    this.a.mStateView.setState(b.LOADING);
                    this.a.mHeaderView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
        }
        return this.mRootView;
    }

    protected void requestSingerImage() {
        if (this.mSingerHeader != null) {
            this.mSingerHeader.a();
        }
    }

    private void initSlidingTabHost(SimpleSlidingTabHost simpleSlidingTabHost) {
        simpleSlidingTabHost.setTabLayoutAverageSpace(true);
        g.a(TAG, "initSlidingTabHost slidingTabHost=" + simpleSlidingTabHost + ",mOnPageChangeListener=" + this.mOnPageChangeListener);
        simpleSlidingTabHost.setOnPageChangeListener(this.mOnPageChangeListener);
        List onBuildTabBinders = onBuildTabBinders();
        simpleSlidingTabHost.setPagerAdapter(new d(onBuildTabBinders));
        this.mTabCount = onBuildTabBinders.size();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initLoadingStateArray();
        initPagerArray();
        this.mAdapter = getAdapter(this.mTab);
        if (this.mTab == 0) {
            this.mListView.a(this.mAdapter, R.id.menu_view, R.id.expandable);
        } else {
            this.mListView.setAdapter(this.mAdapter);
        }
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.setOnScrollListener(this);
    }

    public void onLoadFinished() {
        requestData(1);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mStateView != null) {
            this.mStateView.onThemeLoaded();
        }
        if (this.mSingerHeader != null) {
            this.mSingerHeader.c();
        }
        c.a(this.mFooterView, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        c.a(this.mFooterView, ThemeElement.SUB_BAR_TEXT);
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        if (this.mSlidingTabHost != null) {
            c.a(this.mSlidingTabHost, ThemeElement.TILE_MASK);
            this.mSlidingTabHost.setTextColor(c.c(ThemeElement.SUB_BAR_TEXT));
            this.mSlidingTabHost.setIndicatorDrawable(c.a(ThemeElement.SUB_BAR_INDICATOR));
        }
    }

    private InsetDrawable makeDrawable(Drawable drawable, int i, int i2) {
        if (drawable != null) {
            return new InsetDrawable(drawable, i, 0, i2, 0);
        }
        return null;
    }

    private void initPagerArray() {
        int i;
        this.mLoadingStateArray = new SparseBooleanArray(this.mTabCount);
        for (i = 0; i < this.mTabCount; i++) {
            this.mLoadingStateArray.put(i, false);
        }
        this.mIsErrorNotFirstPage = new SparseBooleanArray(this.mTabCount);
        for (i = 0; i < this.mTabCount; i++) {
            this.mIsErrorNotFirstPage.put(i, false);
        }
    }

    private void initLoadingStateArray() {
        this.mPagerArray = new SparseArray(this.mTabCount);
        for (int i = 0; i < this.mTabCount; i++) {
            q qVar = new q();
            qVar.b(Entry.MAX_LIMIT);
            this.mPagerArray.put(i, qVar);
        }
    }

    private void pageSelected(int i) {
        g.a(TAG, "pageSelected tab=" + i + ",mTab=" + this.mTab);
        if (this.mTab != i) {
            this.mTab = i;
            onPageSelected(i);
        }
    }

    public void setCurrentItem(int i) {
        this.mSlidingTabHost.a(i);
    }

    protected void onPageSelected(int i) {
        this.mAdapter = getAdapter(i);
        if (this.mTab == 0) {
            this.mListView.a(this.mAdapter, R.id.menu_view, R.id.expandable);
        } else {
            this.mListView.setAdapter(this.mAdapter);
        }
        if (this.mAdapter != null) {
            q pager = getPager(i);
            if (this.mAdapter.getCount() == 0) {
                requestData(pager.a());
                this.mListView.removeFooterView(this.mFooterView);
                this.mStateView.setVisibility(0);
                this.mStateView.setState(b.LOADING);
            } else {
                this.mListView.removeFooterView(this.mFooterView);
                if (!this.mLoadingStateArray.get(i)) {
                    if (pager.a() == pager.g()) {
                        this.mListView.addFooterView(this.mFooterView);
                        showLastPageFooterText();
                    }
                    if (this.mIsErrorNotFirstPage.get(this.mTab)) {
                        this.mListView.addFooterView(this.mFooterView);
                        showNotFirstPageError();
                    }
                } else if (pager.a() > 1) {
                    this.mListView.addFooterView(this.mFooterView);
                    showFooterLoading();
                }
                this.mStateView.setVisibility(8);
            }
        }
        onThemeLoaded();
    }

    private View onCreateHeaderView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.singer_header, null);
    }

    protected void configFailedView(View view) {
        if (view != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SingerTabFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(1);
                }
            });
        }
    }

    protected void configListFooterView(View view) {
    }

    protected void configNodataView(View view) {
        if (view != null) {
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SingerTabFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(1);
                }
            });
        }
    }

    protected View onCreateNodataView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_nodata, null);
    }

    protected View onCreateFailedView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.loadingview_failed, null);
    }

    protected View onCreateListFooterView(LayoutInflater layoutInflater) {
        this.mFooterView = new DataListFooterView(getActivity());
        return this.mFooterView;
    }

    private void notifyDataSetChanged(int i) {
        this.mAdapter = getAdapter(i);
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged() {
        notifyDataSetChanged(this.mTab);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mAdapter != null && this.mListView != null) {
            int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
            if (a > -1) {
                onItemClick(this.mTab, adapterView, view, a, j);
            }
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!(this.mAdapter == null || this.mListView == null)) {
            int a = m.a(this.mListView.getHeaderViewsCount(), i, this.mAdapter.getCount());
            if (a > -1) {
                return onItemLongClick(this.mTab, adapterView, view, a, j);
            }
        }
        return false;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        g.a(TAG, "performOnScroll firstVisibleItem=" + i + ",visibleItemCount=" + i2 + ",totalItemCount=" + i3);
        if (m.b(i, i2, i3) && !this.mLoadingStateArray.get(this.mTab) && this.mStateView.getVisibility() != 0) {
            g.a(TAG, "onScroll mTab=" + this.mTab + ",mLoadingStateArray.get(mTab)=" + this.mLoadingStateArray.get(this.mTab));
            q pager = getPager(this.mTab);
            g.a(TAG, "onScroll mPager.getCurrent()=" + pager.a() + ",mPager.end()=" + pager.g());
            if (pager.a() == pager.g()) {
                showLastPageFooterText();
            } else {
                requestData(pager.d());
            }
        }
    }

    protected q getPager(int i) {
        return (q) this.mPagerArray.get(i);
    }

    protected void showLastPageFooterText() {
        g.a(TAG, "showLastPageFooterText");
        if (this.mFooterView != null) {
            this.mFooterView.a(getString(R.string.count_of_media, Integer.valueOf(this.mAdapter.getCount())));
            this.mFooterView.setOnClickListener(null);
        }
    }

    protected void showNotFirstPageError() {
        g.a(TAG, "showNotFirstPageError");
        if (this.mFooterView != null) {
            this.mFooterView.b();
            this.mFooterView.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SingerTabFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.requestData(this.a.getPager(this.a.mTab).d());
                }
            });
        }
    }

    private void requestData(int i) {
        if (!this.mLoadingStateArray.get(this.mTab)) {
            g.a(TAG, "requestData, page=" + i + ",mTab=" + this.mTab);
            this.mLoadingStateArray.put(this.mTab, true);
            this.mListView.removeFooterView(this.mFooterView);
            if (i > 1) {
                this.mListView.addFooterView(this.mFooterView);
                showFooterLoading();
            } else {
                this.mStateView.setVisibility(0);
                this.mStateView.setState(b.LOADING);
            }
            getPager(this.mTab).c(i);
            onRequestData(this.mTab, i);
        }
    }

    private void showFooterLoading() {
        if (this.mFooterView != null) {
            this.mFooterView.a();
        }
    }

    protected void updateStateView(int i, int i2, ArrayList arrayList, int i3) {
        if (isViewAccessAble()) {
            g.a(TAG, "updateStateView tab=" + i + ",resultCode=" + i2 + ",data=" + (arrayList == null ? 0 : arrayList.size()) + ",totalPage=" + i3);
            this.mLoadingStateArray.put(i, false);
            b state = getState(i2, arrayList);
            q pager = getPager(i);
            if (state == b.SUCCESS && i3 >= 1) {
                pager.b(i3);
            }
            if (this.mTab == i) {
                this.mIsErrorNotFirstPage.put(this.mTab, false);
                if (pager.a() > 1) {
                    switch (state) {
                        case SUCCESS:
                            this.mFooterView.c();
                            this.mListView.removeFooterView(this.mFooterView);
                            if (pager.a() == pager.g()) {
                                this.mListView.addFooterView(this.mFooterView);
                                return;
                            }
                            return;
                        case FAILED:
                        case NO_DATA:
                            pager.c(pager.a() - 1);
                            this.mIsErrorNotFirstPage.put(this.mTab, true);
                            showNotFirstPageError();
                            return;
                        default:
                            return;
                    }
                }
                this.mListView.removeFooterView(this.mFooterView);
                switch (state) {
                    case SUCCESS:
                        if (pager.a() == pager.g()) {
                            this.mListView.addFooterView(this.mFooterView);
                        }
                        this.mStateView.setVisibility(8);
                        break;
                    case FAILED:
                        this.mStateView.setVisibility(0);
                        configFailedView(this.mFailedView);
                        break;
                    case NO_DATA:
                        this.mStateView.setVisibility(0);
                        configNodataView(this.mNodataView);
                        break;
                }
                this.mStateView.setState(state);
            }
        }
    }

    protected b getState(int i, ArrayList arrayList) {
        if (i != 1) {
            return b.FAILED;
        }
        return j.a(arrayList) ? b.NO_DATA : b.SUCCESS;
    }

    public void setTabText(int i, String str) {
        setTabText((SimpleSlidingTabHost) this.mHeaderView.findViewById(R.id.slidingtabhost), i, str);
    }

    private void setTabText(SimpleSlidingTabHost simpleSlidingTabHost, int i, String str) {
        simpleSlidingTabHost.a(i, (CharSequence) str);
    }

    public void setGroupID(String str) {
        this.mGroupID = str;
    }

    public void setOrigin(String str) {
        this.mOrigin = str;
    }

    public void setModule(String str) {
        this.mModule = str;
    }

    public String getGroupID() {
        return this.mGroupID;
    }

    public String getOrigin() {
        return this.mOrigin;
    }

    public String getModule() {
        return this.mModule;
    }
}
