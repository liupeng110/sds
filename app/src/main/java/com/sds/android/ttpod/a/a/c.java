package com.sds.android.ttpod.a.a;

import com.sds.android.cloudapi.ttpod.a.u;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.ttpod.common.b.a.a;

/* MusicCircleApi */
public class c extends b {
    public c() {
        a(Long.MAX_VALUE);
    }

    public i a(a aVar, a aVar2) {
        BaseResult g = u.a(b(), aVar.o(), aVar.n(), aVar.f()).g();
        i iVar = new i();
        if (g == null) {
            iVar.a("error");
        } else {
            iVar.a(g.getCode());
            iVar.a(g.getMessage());
        }
        return iVar;
    }
}
