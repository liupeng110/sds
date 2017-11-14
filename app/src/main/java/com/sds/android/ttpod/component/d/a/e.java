package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.component.b.a.b;
import java.util.List;

/* ListDialog */
public class e<M extends a> extends com.sds.android.ttpod.common.a.a {
    private b a;
    private com.sds.android.ttpod.component.d.e<M> b;
    private ListView c;
    private M d;

    protected /* synthetic */ Object a() {
        return c();
    }

    public e(Context context, List<M> list, int i, com.sds.android.ttpod.common.a.a.a<? extends e> aVar, int i2, com.sds.android.ttpod.common.a.a.a<? extends e> aVar2) {
        super(context);
        a(i, (com.sds.android.ttpod.common.a.a.a) aVar, i2, (com.sds.android.ttpod.common.a.a.a) aVar2);
        this.b = a(context, (List) list);
        d();
        this.c.setAdapter(this.b);
    }

    public e(Context context, List<M> list, com.sds.android.ttpod.common.a.a.a<? extends e> aVar, com.sds.android.ttpod.common.a.a.a<? extends e> aVar2) {
        this(context, list, R.string.ok, aVar, R.string.cancel, aVar2);
    }

    public e(Context context, List<M> list, int i, com.sds.android.ttpod.common.a.a.a<? extends e> aVar) {
        super(context);
        b(i, aVar);
        this.b = a(context, (List) list);
        d();
        this.c.setAdapter(this.b);
    }

    protected void a(M m, int i) {
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    protected com.sds.android.ttpod.component.d.e<M> a(Context context, List<M> list) {
        return new com.sds.android.ttpod.component.d.e(context, list);
    }

    protected View a(Context context, ViewGroup viewGroup) {
        this.c = (ListView) View.inflate(context, R.layout.popups_body_list, null);
        this.c.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.d = this.a.b.a(i);
                if (this.a.a != null) {
                    this.a.a.a(this.a.d, i);
                }
                this.a.a(this.a.d, i);
                this.a.b.notifyDataSetChanged();
            }
        });
        return this.c;
    }

    protected e c() {
        return this;
    }

    private void d() {
        View b = b();
        if (b != null) {
            this.c.addFooterView(b);
        }
    }

    protected View b() {
        return null;
    }
}
