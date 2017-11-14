package com.sds.android.ttpod.activities.musiccircle.a;

import com.sds.android.cloudapi.ttpod.a.u;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.ttpod.framework.storage.environment.b;

/* MusicCircleChecker */
public final class e extends b {
    protected e(a aVar) {
        super(aVar);
    }

    protected void e() {
        if (b.av()) {
            u.a(b.aw().getToken()).a((p) this);
        }
    }

    protected boolean a(BaseResult baseResult) {
        return true;
    }
}
