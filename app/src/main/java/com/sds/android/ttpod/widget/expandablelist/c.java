package com.sds.android.ttpod.widget.expandablelist;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

/* WrapperListAdapterImpl */
public abstract class c extends BaseAdapter implements WrapperListAdapter {
    protected ListAdapter b;

    public c(ListAdapter listAdapter) {
        this.b = listAdapter;
    }

    public ListAdapter getWrappedAdapter() {
        return this.b;
    }

    public boolean areAllItemsEnabled() {
        return this.b.areAllItemsEnabled();
    }

    public boolean isEnabled(int i) {
        return this.b.isEnabled(i);
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        this.b.registerDataSetObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        this.b.unregisterDataSetObserver(dataSetObserver);
    }

    public int getCount() {
        return this.b.getCount();
    }

    public Object getItem(int i) {
        return this.b.getItem(i);
    }

    public long getItemId(int i) {
        return this.b.getItemId(i);
    }

    public boolean hasStableIds() {
        return this.b.hasStableIds();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.b.getView(i, view, viewGroup);
    }

    public int getItemViewType(int i) {
        return this.b.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return this.b.getViewTypeCount();
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }
}
