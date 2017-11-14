package com.sds.android.ttpod.activities.musiccircle.a;

import com.sds.android.cloudapi.ttpod.a.q;
import com.sds.android.cloudapi.ttpod.data.NewNoticeCount;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.ttpod.framework.storage.environment.b;

/* MessageChecker */
public final class d extends b {
    protected d(a aVar) {
        super(aVar);
    }

    protected void e() {
        if (b.av()) {
            q.a(b.aw().getToken()).a((p) this);
        }
    }

    protected boolean a(BaseResult baseResult) {
        NewNoticeCount newNoticeCount = (NewNoticeCount) ((NewNoticeCountResult) baseResult).getData();
        return newNoticeCount != null && newNoticeCount.getNewMessageTotalCount() > 0;
    }
}
