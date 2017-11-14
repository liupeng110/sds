package com.sds.android.ttpod.widget.carousel;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/* CarouselViewAdapter */
public class b extends BaseAdapter {
    private a[] a;

    public void a(a[] aVarArr) {
        this.a = aVarArr;
    }

    public int getCount() {
        if (this.a == null) {
            return 0;
        }
        return this.a.length;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return this.a[i];
    }
}
