package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import com.sds.android.cloudapi.ttpod.result.SingerListResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.a;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.DataListFooterView;
import com.sds.android.ttpod.widget.NetworkLoadView;
import com.sds.android.ttpod.widget.NetworkLoadView.b;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ListLoadingFragment<D> extends SlidingClosableFragment implements OnItemClickListener, OnItemLongClickListener {
    protected static final int HOME_PAGE = 1;
    protected int mCurrentPage = 1;
    protected ArrayList<D> mDataList = null;
    protected boolean mDataLoading = false;
    protected DataListFooterView mFooterView;
    protected boolean mIsReqSuccess = false;
    protected a<D> mListAdapter;
    protected ListView mListView;
    protected NetworkLoadView mLoadingView;
    protected OnScrollListener mOnScrollListener = new OnScrollListener(this) {
        final /* synthetic */ ListLoadingFragment a;

        {
            this.a = r1;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (!this.a.mDataLoading && this.a.mCurrentPage == this.a.mTotalPages) {
                this.a.showLastPageFooterText();
                if (this.a.mFooterView != null) {
                    this.a.mFooterView.setOnClickListener(null);
                }
            } else if (i2 > 0 && i3 >= i2 && i + i2 >= i3 && !this.a.mDataLoading && this.a.mCurrentPage < this.a.mTotalPages && m.b(i, i2, i3)) {
                if (this.a.mIsReqSuccess) {
                    ListLoadingFragment listLoadingFragment = this.a;
                    ListLoadingFragment listLoadingFragment2 = this.a;
                    int i4 = listLoadingFragment2.mCurrentPage + 1;
                    listLoadingFragment2.mCurrentPage = i4;
                    listLoadingFragment.requestDataList(i4);
                } else {
                    this.a.requestDataList(this.a.mCurrentPage);
                }
                this.a.mDataLoading = true;
            }
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }
    };
    protected b mOnStartLoadingListener = new b(this) {
        final /* synthetic */ ListLoadingFragment a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.mDataList != null) {
                this.a.updateDataListView(this.a.mDataList);
                if (this.a.mLoadingView != null) {
                    this.a.mLoadingView.setLoadState(NetworkLoadView.a.IDLE);
                }
                this.a.hideFooter();
                return;
            }
            this.a.requestDataList(1);
        }
    };
    protected com.sds.android.ttpod.framework.modules.a mRequestId = null;
    protected com.sds.android.ttpod.framework.modules.a mResponseId = null;
    protected int mTotalPages;

    protected abstract String onLoadTitleText();

    public ListLoadingFragment(com.sds.android.ttpod.framework.modules.a aVar, com.sds.android.ttpod.framework.modules.a aVar2, a<D> aVar3) {
        this.mRequestId = aVar;
        this.mResponseId = aVar2;
        this.mListAdapter = aVar3;
    }

    public void setAdapter(a<D> aVar) {
        this.mListAdapter = aVar;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        if (this.mResponseId != null) {
            map.put(this.mResponseId, i.a(getClass(), "updateSingerData", SingerListResult.class));
        }
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_base_list, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initViews(view);
        setupTitleText();
        setupLoadingView();
        setupListView();
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mFooterView != null) {
            c.a(this.mFooterView, ThemeElement.BACKGROUND_MASK);
            c.a(this.mFooterView, ThemeElement.COMMON_SUB_TITLE_TEXT);
        }
        if (this.mLoadingView != null) {
            this.mLoadingView.onThemeLoaded();
        }
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        c.a(this.mListView, ThemeElement.COMMON_SEPARATOR);
        if (this.mListAdapter != null) {
            this.mListAdapter.notifyDataSetChanged();
        }
    }

    public void onLoadFinished() {
        showLoadingAnim();
    }

    public void updateData(ArrayList<D> arrayList, Integer num) {
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (arrayList == null || arrayList.size() == 0) {
                showNetworkError();
                this.mIsReqSuccess = false;
                if (this.mDataList != null) {
                    this.mFooterView.b();
                    this.mFooterView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ ListLoadingFragment a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            if (!this.a.mIsReqSuccess && !this.a.mDataLoading) {
                                this.a.mDataLoading = true;
                                this.a.requestDataList(this.a.mCurrentPage);
                            }
                        }
                    });
                    return;
                }
                return;
            }
            hideLoadingAnim();
            hideFooter();
            if (1 == this.mCurrentPage) {
                if (this.mDataList != null) {
                    this.mDataList.clear();
                }
                this.mDataList = arrayList;
                this.mTotalPages = num.intValue();
            } else {
                this.mDataList.addAll(arrayList);
            }
            if (this.mFooterView != null) {
                this.mFooterView.setOnClickListener(null);
            }
            this.mIsReqSuccess = true;
            updateDataListView(this.mDataList);
        }
    }

    public void updateSingerData(SingerListResult singerListResult) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(singerListResult.getDataList());
        updateData(arrayList, Integer.valueOf(singerListResult.getExtra().b()));
    }

    protected void requestDataList(int i) {
        if (!this.mDataLoading && this.mRequestId != null) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(this.mRequestId, Integer.valueOf(i)));
            this.mDataLoading = true;
        }
    }

    protected void initViews(View view) {
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mListView = (ListView) view.findViewById(R.id.market_app_list);
    }

    protected void setupTitleText() {
        getActionBarController().a(onLoadTitleText());
    }

    protected void setupLoadingView() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setOnStartLoadingListener(this.mOnStartLoadingListener);
        }
    }

    protected void setupListView() {
        this.mListView.setEmptyView(this.mLoadingView);
        this.mListView.setOnScrollListener(this.mOnScrollListener);
        this.mListView.setOnItemClickListener(this);
        this.mListView.setOnItemLongClickListener(this);
        this.mListView.setAdapter(this.mListAdapter);
        setupListHeader();
        setupListFooter();
    }

    protected void setupListHeader() {
    }

    protected void setupListFooter() {
        this.mFooterView = new DataListFooterView(getActivity());
        this.mListView.addFooterView(this.mFooterView);
    }

    protected void updateDataListView(ArrayList<D> arrayList) {
        this.mListAdapter.a((List) arrayList);
    }

    protected void showLastPageFooterText() {
        if (this.mFooterView != null) {
            this.mFooterView.a(BaseApplication.e().getString(R.string.last_page_prompt));
        }
    }

    private void hideFooter() {
        if (this.mFooterView != null) {
            this.mFooterView.c();
        }
    }

    private void showLoadingAnim() {
        if (this.mLoadingView != null && j.a(this.mDataList)) {
            this.mLoadingView.setLoadState(NetworkLoadView.a.LOADING);
        }
    }

    private void hideLoadingAnim() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setLoadState(NetworkLoadView.a.IDLE);
        }
    }

    private void showNetworkError() {
        if (this.mLoadingView != null) {
            this.mLoadingView.setLoadState(NetworkLoadView.a.FAILED);
        }
    }
}
