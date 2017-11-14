package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;
import java.util.ArrayList;

/* LocalSkinRankListLoader */
public class g implements Runnable {
    public void run() {
        a();
    }

    public void a() {
        String a = c.a(v.a(a.o(), "rank_"));
        OnlineSkinListResult onlineSkinListResult = TextUtils.isEmpty(a) ? null : (OnlineSkinListResult) f.a(a, OnlineSkinListResult.class);
        ArrayList arrayList = new ArrayList();
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_SKIN_RANK_LIST, v.a(onlineSkinListResult)), c.SKIN);
    }
}
