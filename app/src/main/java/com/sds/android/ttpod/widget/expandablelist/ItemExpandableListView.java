package com.sds.android.ttpod.widget.expandablelist;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import com.sds.android.ttpod.widget.DraggableListView;
import com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.a;

class ItemExpandableListView extends DraggableListView {
    private b a;

    public ItemExpandableListView(Context context) {
        super(context, null);
    }

    public ItemExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ItemExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
    }

    public boolean f() {
        return this.a != null && this.a.a(false);
    }

    public int e() {
        return this.a != null ? this.a.a() : -1;
    }

    public void a(ListAdapter listAdapter, int i, int i2) {
        this.a = listAdapter != null ? new b(listAdapter, i, i2) : null;
        super.setAdapter(this.a);
    }

    public void setItemExpandCollapseListener(a aVar) {
        this.a.a(aVar);
    }

    public Parcelable onSaveInstanceState() {
        return this.a.a(super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.a.a(savedState);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }
}
