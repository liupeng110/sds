package com.sds.android.ttpod.component.d;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.sds.android.ttpod.R;
import java.util.List;

/* ActionListAdapter */
public class a extends e<com.sds.android.ttpod.component.b.a> {
    private com.sds.android.ttpod.component.d.a.a.a a;
    private OnClickListener b = new OnClickListener(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            com.sds.android.ttpod.component.b.a aVar = (com.sds.android.ttpod.component.b.a) view.getTag();
            List b = this.a.b();
            b.remove(aVar);
            this.a.a(b);
            if (this.a.a != null) {
                this.a.a.a(aVar);
            }
        }
    };

    public a(Context context, List<com.sds.android.ttpod.component.b.a> list) {
        super(context, list);
    }

    public void a(com.sds.android.ttpod.component.d.a.a.a aVar) {
        this.a = aVar;
    }

    protected int a() {
        return R.layout.popups_action_list_item;
    }

    protected void a(com.sds.android.ttpod.component.d.e.a aVar) {
        super.a(aVar);
        aVar.a().setOnClickListener(this.b);
    }

    protected void a(com.sds.android.ttpod.component.d.e.a aVar, com.sds.android.ttpod.component.b.a aVar2) {
        super.a(aVar, aVar2);
        aVar.a().setTag(aVar2);
    }
}
