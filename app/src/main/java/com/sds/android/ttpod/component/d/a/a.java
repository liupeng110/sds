package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import com.sds.android.ttpod.component.d.e;
import java.util.List;

/* ActionListDialog */
public class a extends e<com.sds.android.ttpod.component.b.a> {
    private a a;
    private com.sds.android.ttpod.component.d.a b;

    /* ActionListDialog */
    public interface a {
        void a(com.sds.android.ttpod.component.b.a aVar);
    }

    public a(Context context, List<com.sds.android.ttpod.component.b.a> list, a aVar, com.sds.android.ttpod.common.a.a.a<? extends e> aVar2, com.sds.android.ttpod.common.a.a.a<? extends e> aVar3) {
        super(context, (List) list, (com.sds.android.ttpod.common.a.a.a) aVar2, (com.sds.android.ttpod.common.a.a.a) aVar3);
        this.a = aVar;
        this.b.a(this.a);
    }

    protected e<com.sds.android.ttpod.component.b.a> a(Context context, List<com.sds.android.ttpod.component.b.a> list) {
        this.b = new com.sds.android.ttpod.component.d.a(context, list);
        return this.b;
    }
}
