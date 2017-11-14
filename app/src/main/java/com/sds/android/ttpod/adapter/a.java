package com.sds.android.ttpod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.util.ArrayList;
import java.util.List;

@Deprecated
/* BaseListAdapter */
public abstract class a<D> extends BaseAdapter {
    private D a;
    protected Context b;
    protected LayoutInflater c;
    protected List<D> d;

    protected abstract View a(LayoutInflater layoutInflater, ViewGroup viewGroup);

    protected abstract void a(View view, D d, int i);

    public a(Context context, List<D> list) {
        if (list == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        if (context == null) {
            context = BaseApplication.e();
        }
        this.b = context;
        this.d = list;
        this.c = LayoutInflater.from(this.b);
    }

    public a() {
        this(BaseApplication.e(), new ArrayList());
    }

    public Context a() {
        return this.b;
    }

    public List<D> b() {
        return this.d;
    }

    public void a(List<D> list) {
        if (list == null) {
            throw new IllegalArgumentException("data must not be null");
        }
        this.d = list;
        notifyDataSetChanged();
    }

    public D c() {
        return this.a;
    }

    public void a(D d) {
        this.a = d;
    }

    public int getCount() {
        return this.d.size();
    }

    public D getItem(int i) {
        return this.d.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = a(this.c, viewGroup);
        }
        a(view, getItem(i), i);
        return view;
    }
}
