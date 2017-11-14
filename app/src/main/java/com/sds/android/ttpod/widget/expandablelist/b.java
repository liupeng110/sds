package com.sds.android.ttpod.widget.expandablelist;

import android.view.View;
import android.widget.ListAdapter;

/* ItemExpandableListAdapter */
public class b extends AbstractExpandableListAdapter {
    private int a;
    private int c;

    public b(ListAdapter listAdapter, int i, int i2) {
        super(listAdapter);
        if (i < 0) {
            throw new IllegalArgumentException("toggleButtonId can NOT be negative");
        } else if (i2 < 0) {
            throw new IllegalArgumentException("expandableViewId can NOT be negative");
        } else {
            this.a = i;
            this.c = i2;
        }
    }

    public View a(View view) {
        return this.a > 0 ? view.findViewById(this.a) : null;
    }

    public View b(View view) {
        return this.c > 0 ? view.findViewById(this.c) : null;
    }
}
