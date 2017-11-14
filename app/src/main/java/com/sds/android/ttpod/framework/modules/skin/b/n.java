package com.sds.android.ttpod.framework.modules.skin.b;

import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.d.m;

/* SFont */
public class n extends f {
    int c = 15;
    int d = 0;
    String e;

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    n(a aVar) {
        super(aVar);
        this.c = m.a(aVar.getAttributeValue(null, "Size"), this.c);
        this.d = m.a(aVar.getAttributeValue(null, "Style"), this.d);
        this.e = aVar.getAttributeValue(null, "FamilyName");
    }

    public n(String str) {
        this.e = str;
    }

    public n(n nVar) {
        this.e = nVar.e;
        this.d = nVar.d;
        this.c = nVar.c;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }
}
