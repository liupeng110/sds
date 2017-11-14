package com.sds.android.ttpod.adapter.e;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.util.ArrayList;
import java.util.List;

/* ShareSelectAdapter */
public class b extends BaseAdapter {
    private List<a> a = new ArrayList();
    private Context b;

    /* ShareSelectAdapter */
    public static class a {
        private int a;
        private int b;
        private e c;

        public a(int i, int i2, e eVar) {
            this.a = i;
            this.b = i2;
            this.c = eVar;
        }

        public e a() {
            return this.c;
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public b(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        this.b = context;
    }

    public void a(List<a> list) {
        this.a = list;
    }

    public int getCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public a a(int i) {
        return (a) this.a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        if (view == null) {
            inflate = LayoutInflater.from(this.b).inflate(R.layout.share_select_item, viewGroup, false);
        } else {
            inflate = view;
        }
        TextView textView = (TextView) inflate;
        a a = a(i);
        textView.setText(a.a);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, a.b, 0, 0);
        return inflate;
    }
}
