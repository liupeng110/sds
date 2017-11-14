package com.sds.android.ttpod.fragment.main.findsong.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.sds.android.sdk.lib.request.d;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.adapter.c;
import com.sds.android.ttpod.b.m;
import com.sds.android.ttpod.fragment.base.SlidingClosableFragment;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.widget.NetworkLoadView;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class GridViewFragment<D> extends SlidingClosableFragment implements OnItemClickListener {
    protected boolean mDataLoading;
    protected c<D> mGridListAdapter;
    private GridView mGridView;
    protected NetworkLoadView mLoadView;
    private int mNumColumns;

    protected abstract String onLoadTitleText();

    protected void setAdapter(c<D> cVar) {
        this.mGridListAdapter = cVar;
    }

    protected void setGridListAdapter(ListAdapter listAdapter) {
        this.mGridView.setAdapter(listAdapter);
    }

    public GridViewFragment(c<D> cVar) {
        this.mDataLoading = false;
        this.mNumColumns = 2;
        this.mGridListAdapter = cVar;
    }

    public GridViewFragment(c<D> cVar, int i) {
        this(cVar);
        this.mNumColumns = i;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(a.UPDATE_SINGER_CATEGORY_LIST, i.a(getClass(), "updateSingerCategoryList", d.class));
    }

    protected View onCreateContentView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_double_item_grid_list, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getActionBarController().a(onLoadTitleText());
        this.mLoadView = (NetworkLoadView) view.findViewById(R.id.loading_view);
        this.mGridView = (GridView) view.findViewById(R.id.double_item_list_grid_view);
        this.mGridView.setNumColumns(this.mNumColumns);
        this.mGridView.setAdapter(this.mGridListAdapter);
        this.mGridView.setOnItemClickListener(this);
        this.mGridView.setOnScrollListener(new m.a());
    }

    public void onThemeLoaded() {
        super.onThemeLoaded();
        this.mLoadView.onThemeLoaded();
        com.sds.android.ttpod.framework.modules.theme.c.a(this.mGridView, ThemeElement.BACKGROUND_MASK);
    }

    public void onLoadFinished() {
        this.mLoadView.setLoadState(NetworkLoadView.a.LOADING);
    }

    public void updateDataList(ArrayList<D> arrayList) {
        if (isViewAccessAble() && this.mLoadView != null && this.mGridListAdapter != null) {
            this.mDataLoading = false;
            if (arrayList == null || arrayList.size() == 0) {
                this.mLoadView.setLoadState(NetworkLoadView.a.FAILED);
                return;
            }
            this.mLoadView.setLoadState(NetworkLoadView.a.IDLE);
            this.mGridListAdapter.a((List) arrayList);
        }
    }

    public void updateSingerCategoryList(d dVar) {
        updateDataList(dVar.getDataList());
    }
}
