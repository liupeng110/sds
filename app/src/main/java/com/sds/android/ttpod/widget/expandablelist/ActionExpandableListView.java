package com.sds.android.ttpod.widget.expandablelist;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class ActionExpandableListView extends ItemExpandableListView {
    private a a;
    private int[] b = null;

    public interface a {
        void a(View view, View view2, int i);
    }

    public /* bridge */ /* synthetic */ int e() {
        return super.e();
    }

    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }

    public /* bridge */ /* synthetic */ void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
    }

    public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public /* bridge */ /* synthetic */ void setItemExpandCollapseListener(com.sds.android.ttpod.widget.expandablelist.AbstractExpandableListAdapter.a aVar) {
        super.setItemExpandCollapseListener(aVar);
    }

    public ActionExpandableListView(Context context) {
        super(context);
    }

    public ActionExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActionExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setAdapter(ListAdapter listAdapter) {
        a(listAdapter, 0, 0);
    }

    public void a(ListAdapter listAdapter, int i, int i2) {
        if (listAdapter != null) {
            super.a(new c(this, listAdapter) {
                final /* synthetic */ ActionExpandableListView a;

                public View getView(final int i, View view, ViewGroup viewGroup) {
                    final View view2 = this.b.getView(i, view, viewGroup);
                    if (!(this.a.b == null || view2 == null)) {
                        for (int i2 : this.a.b) {
                            View findViewById = view2.findViewById(i2);
                            if (findViewById != null) {
                                findViewById.findViewById(i2).setOnClickListener(new OnClickListener(this) {
                                    final /* synthetic */ AnonymousClass1 c;

                                    public void onClick(View view) {
                                        if (this.c.a.a != null) {
                                            this.c.a.a.a(view2, view, i);
                                        }
                                    }
                                });
                            }
                        }
                    }
                    return view2;
                }
            }, i, i2);
        } else {
            super.a(null, i, i2);
        }
    }
}
