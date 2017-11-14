package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.e;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* MultiChoiceListDialog */
public class j extends e<d> {
    private com.sds.android.ttpod.component.d.d a;

    protected /* synthetic */ Object a() {
        return g();
    }

    protected /* synthetic */ e c() {
        return g();
    }

    public j(Context context, List<d> list, a<j> aVar, a<j> aVar2) {
        super(context, (List) list, (a) aVar, (a) aVar2);
    }

    public j(Context context, d[] dVarArr, a<j> aVar, a<j> aVar2) {
        this(context, new ArrayList(Arrays.asList(dVarArr)), (a) aVar, (a) aVar2);
    }

    protected e a(Context context, List<d> list) {
        this.a = new com.sds.android.ttpod.component.d.d(context, list);
        return this.a;
    }

    protected void a(d dVar, int i) {
        dVar.setChecked(!dVar.isChecked());
        this.a.notifyDataSetChanged();
    }

    public List<d> d() {
        List<d> b = this.a.b();
        List<d> arrayList = new ArrayList();
        for (d dVar : b) {
            if (dVar.isChecked()) {
                arrayList.add(dVar);
            }
        }
        return arrayList;
    }

    protected j g() {
        return this;
    }
}
