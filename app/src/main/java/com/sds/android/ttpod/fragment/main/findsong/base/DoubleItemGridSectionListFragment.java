package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.sds.android.sdk.lib.request.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.b;
import com.sds.android.ttpod.adapter.b.a;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.a.j;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

public abstract class DoubleItemGridSectionListFragment<D> extends SlidingClosableFragment {
    protected b<D> mAdapter;
    private boolean mDataLoading = false;
    private ArrayList<a<D>> mListDataList;
    private ListView mListView;
    private NetworkLoadView mLoadingView;
    private NetworkLoadView.b mOnLoadingViewStartLoadingListener = new NetworkLoadView.b(this) {
        final /* synthetic */ DoubleItemGridSectionListFragment a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.mListDataList != null) {
                this.a.mAdapter.a(this.a.mListDataList);
            } else {
                this.a.requestDataList();
            }
        }
    };
    private com.sds.android.ttpod.framework.modules.a mRequestId = null;
    private com.sds.android.ttpod.framework.modules.a mResponseId = null;

    protected abstract ArrayList<a<D>> convertDataList(ArrayList arrayList);

    protected abstract String onLoadTitleText();

    public DoubleItemGridSectionListFragment(com.sds.android.ttpod.framework.modules.a aVar, com.sds.android.ttpod.framework.modules.a aVar2, b bVar) {
        this.mRequestId = aVar;
        this.mResponseId = aVar2;
        this.mAdapter = bVar;
    }

    public b<D> getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(b<D> bVar) {
        this.mAdapter = bVar;
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        Class cls = getClass();
        if (this.mResponseId != null) {
            map.put(this.mResponseId, i.a(cls, "updateData", ArrayList.class));
        }
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RADIO_CATEGORY_LIST, i.a(cls, "updateRadioList", d.class));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_double_item_grid_section_list, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().a(onLoadTitleText());
        this.mLoadingView = (NetworkLoadView) view.findViewById(R.id.market_load_view);
        this.mListView = (ListView) view.findViewById(R.id.market_app_list);
        this.mListView.setOnScrollListener(new m.a());
        this.mLoadingView.setOnStartLoadingListener(this.mOnLoadingViewStartLoadingListener);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setDividerHeight(0);
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        if (this.mLoadingView != null) {
            this.mLoadingView.onThemeLoaded();
        }
        c.a(this.mListView, ThemeElement.BACKGROUND_MASK);
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
    }

    protected void requestDataList() {
        if (!this.mDataLoading) {
            com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(this.mRequestId, new Object[0]));
            this.mDataLoading = true;
        }
    }

    public void updateData(ArrayList arrayList) {
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (arrayList == null) {
                this.mLoadingView.setLoadState(NetworkLoadView.a.FAILED);
                return;
            }
            this.mLoadingView.setLoadState(NetworkLoadView.a.IDLE);
            this.mListDataList = arrayList;
            this.mListDataList = convertDataList(arrayList);
            if (this.mListDataList == null) {
                this.mLoadingView.setLoadState(NetworkLoadView.a.FAILED);
            } else {
                this.mAdapter.a(this.mListDataList);
            }
        }
    }

    public void updateRadioList(d dVar) {
        ArrayList dataList = dVar.getDataList();
        if (isViewAccessAble()) {
            this.mDataLoading = false;
            if (j.a(dataList)) {
                this.mLoadingView.setLoadState(NetworkLoadView.a.FAILED);
                return;
            }
            this.mLoadingView.setLoadState(NetworkLoadView.a.IDLE);
            this.mListDataList = dataList;
            this.mListDataList = convertDataList(dataList);
            if (this.mListDataList == null) {
                this.mLoadingView.setLoadState(NetworkLoadView.a.FAILED);
            } else {
                this.mAdapter.a(this.mListDataList);
            }
        }
    }

    public void onLoadFinished() {
        this.mLoadingView.setLoadState(NetworkLoadView.a.LOADING);
    }
}
