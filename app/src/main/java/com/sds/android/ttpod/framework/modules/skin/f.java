package com.sds.android.ttpod.framework.modules.skin;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.result.OnlineSkinListResult;
import com.sds.android.ttpod.framework.a;
import com.sds.android.ttpod.framework.a.v;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.c;

/* LocalSkinListLoader */
public class f implements Runnable {
    public void run() {
        a();
    }

    public void a() {
        String o = a.o();
        o = c.a(o, v.a(o, "list_"), "skin_list");
        OnlineSkinListResult onlineSkinListResult = TextUtils.isEmpty(o) ? null : (OnlineSkinListResult) com.sds.android.sdk.lib.util.f.a(o, OnlineSkinListResult.class);
        b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_RECOMMEND_SKIN_LIST, v.a(onlineSkinListResult)), c.SKIN);
    }
}
