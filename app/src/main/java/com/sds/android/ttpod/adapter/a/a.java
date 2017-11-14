package com.sds.android.ttpod.adapter.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.sds.android.ttpod.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* ApShareBaseAdapter */
public abstract class a extends BaseAdapter implements com.sds.android.ttpod.adapter.a.e.a {
    protected ArrayList<com.sds.android.ttpod.fragment.apshare.a> a = new ArrayList();
    private Context b;

    protected abstract void a(e eVar, com.sds.android.ttpod.fragment.apshare.a aVar);

    protected abstract int b();

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context) {
        this.b = context;
    }

    public Context a() {
        return this.b;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public com.sds.android.ttpod.fragment.apshare.a a(int i) {
        return (com.sds.android.ttpod.fragment.apshare.a) this.a.get(i);
    }

    public int getCount() {
        return this.a.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        e eVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.apshare_media_list_item, null);
            eVar = new e(view, b(), this);
            view.setTag(eVar);
        } else {
            eVar = (e) view.getTag();
        }
        a(eVar, a(i));
        return view;
    }

    public void a(boolean z, com.sds.android.ttpod.fragment.apshare.a aVar) {
    }

    public void a(com.sds.android.ttpod.fragment.apshare.a aVar) {
    }

    public void b(com.sds.android.ttpod.fragment.apshare.a aVar) {
        if (aVar != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                if (aVar.b().equals(((com.sds.android.ttpod.fragment.apshare.a) it.next()).b())) {
                    return;
                }
            }
            this.a.add(aVar);
            notifyDataSetChanged();
        }
    }

    public void c(com.sds.android.ttpod.fragment.apshare.a aVar) {
        if (aVar != null) {
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                if (aVar.b().equals(((com.sds.android.ttpod.fragment.apshare.a) it.next()).b())) {
                    return;
                }
            }
            this.a.add(0, aVar);
            notifyDataSetChanged();
        }
    }

    public void a(List<com.sds.android.ttpod.fragment.apshare.a> list) {
        this.a.clear();
        if (list != null) {
            for (com.sds.android.ttpod.fragment.apshare.a add : list) {
                this.a.add(add);
            }
        }
        notifyDataSetChanged();
    }
}
