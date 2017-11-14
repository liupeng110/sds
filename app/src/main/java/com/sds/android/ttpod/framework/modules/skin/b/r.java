package com.sds.android.ttpod.framework.modules.skin.b;

import com.sds.android.ttpod.framework.modules.search.a.a;
import com.sds.android.ttpod.framework.modules.skin.c.e;

/* SMotion */
public final class r extends f {
    String[] c;
    int[] d;

    public /* bridge */ /* synthetic */ String a() {
        return super.a();
    }

    public r(a aVar) throws Exception {
        super(aVar);
        String attributeValue = aVar.getAttributeValue(null, "Component");
        if (attributeValue != null) {
            this.c = attributeValue.split("\\|");
            this.d = e.a(aVar.getAttributeValue(null, "Motion"));
        }
    }

    public String[] b() {
        return this.c;
    }

    public int[] c() {
        return this.d;
    }
}
