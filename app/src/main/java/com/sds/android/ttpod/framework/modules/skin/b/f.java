package com.sds.android.ttpod.framework.modules.skin.b;

import com.sds.android.ttpod.framework.modules.search.a.a;
import java.io.Serializable;

/* SBase */
abstract class f implements Serializable {
    protected String a;
    protected String b;

    f() {
    }

    f(a aVar) {
        this.a = aVar.getAttributeValue(null, "ID");
        this.b = aVar.getAttributeValue(null, "Name");
    }

    public String a() {
        return this.a;
    }
}
