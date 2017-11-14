package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.e;
import com.sds.android.ttpod.component.d.g;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* SingleChoiceListDialog */
public class r extends e<d> {
    private g a;

    protected /* synthetic */ Object a() {
        return d();
    }

    protected /* synthetic */ e c() {
        return d();
    }

    public r(Context context, List<d> list, a<r> aVar, a<r> aVar2) {
        super(context, (List) list, (a) aVar, (a) aVar2);
    }

    public r(Context context, d[] dVarArr, a<r> aVar, a<r> aVar2) {
        this(context, new ArrayList(Arrays.asList(dVarArr)), (a) aVar, (a) aVar2);
    }

    public r(Context context, d[] dVarArr, a<r> aVar) {
        super(context, Arrays.asList(dVarArr), (int) R.string.cancel, (a) aVar);
    }

    public void c(int i) {
        this.a.b(i);
    }

    protected void a(d dVar, int i) {
        c(dVar.g());
    }

    protected e a(Context context, List<d> list) {
        this.a = new g(this, context, list) {
            final /* synthetic */ r a;

            public View getView(int i, View view, ViewGroup viewGroup) {
                return super.getView(i, view, viewGroup);
            }

            protected void a(e.a aVar, d dVar) {
                super.a(aVar, dVar);
            }
        };
        return this.a;
    }

    protected r d() {
        return this;
    }
}
