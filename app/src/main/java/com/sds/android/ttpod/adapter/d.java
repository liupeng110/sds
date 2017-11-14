package com.sds.android.ttpod.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.util.ArrayList;

/* SectionListAdapter */
public abstract class d extends BaseAdapter {
    protected LayoutInflater a = LayoutInflater.from(BaseApplication.e());
    private ArrayList<Integer> b = new ArrayList();
    private int c = 0;

    protected abstract int a();

    protected abstract int a(int i);

    protected abstract View a(ViewGroup viewGroup);

    protected abstract Object a(int i, int i2);

    protected abstract void a(int i, int i2, View view);

    protected abstract void a(int i, View view);

    protected abstract View b(ViewGroup viewGroup);

    protected abstract Object c(int i);

    public final int getCount() {
        return this.c;
    }

    public final Object getItem(int i) {
        if (d(i)) {
            return c(e(i));
        }
        int e = e(i);
        return a(e, b(i, e));
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (d(i)) {
            return a(i, view, viewGroup);
        }
        return b(i, view, viewGroup);
    }

    protected void b() {
        int i = 0;
        int a = a();
        this.b.clear();
        int i2 = 0;
        while (i < a) {
            this.b.add(Integer.valueOf(i2));
            i2 = (i2 + 1) + a(i);
            i++;
        }
        this.c = i2;
    }

    protected final View a(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = a(viewGroup);
            view.setTag(R.id.tag_view_key, "section");
        } else if ("section" != view.getTag(R.id.tag_view_key)) {
            view = a(viewGroup);
            view.setTag(R.id.tag_view_key, "section");
        }
        a(e(i), view);
        return view;
    }

    protected final View b(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = b(viewGroup);
            view.setTag(R.id.tag_view_key, "subItem");
        } else if ("subItem" != view.getTag(R.id.tag_view_key)) {
            view = b(viewGroup);
            view.setTag(R.id.tag_view_key, "subItem");
        }
        int e = e(i);
        a(e, b(i, e), view);
        return view;
    }

    protected boolean d(int i) {
        return this.b.contains(Integer.valueOf(i));
    }

    protected int e(int i) {
        int size = this.b.size();
        int i2 = 0;
        while (i2 < size - 1) {
            if (i >= ((Integer) this.b.get(i2)).intValue() && i < ((Integer) this.b.get(i2 + 1)).intValue()) {
                return i2;
            }
            i2++;
        }
        return i2;
    }

    protected int b(int i, int i2) {
        return (i - ((Integer) this.b.get(i2)).intValue()) - 1;
    }

    protected int f(int i) {
        if (i < this.b.size()) {
            return ((Integer) this.b.get(i)).intValue();
        }
        return -1;
    }
}
