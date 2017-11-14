package com.sds.android.ttpod.framework.modules.skin.b;

import com.sds.android.ttpod.framework.modules.search.a.a;

/* SEvent */
public final class m extends f implements a<r> {
    String[] c;
    r[] d;

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public m(a aVar) throws Exception {
        super(aVar);
        if (this.a != null) {
            this.c = this.a.split("\\|");
        }
    }

    public String[] b() {
        return this.c;
    }

    public r[] c() {
        return this.d;
    }

    public void a(r[] rVarArr) {
        this.d = rVarArr;
    }
}
