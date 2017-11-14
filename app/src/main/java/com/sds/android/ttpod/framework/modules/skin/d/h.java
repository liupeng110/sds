package com.sds.android.ttpod.framework.modules.skin.d;

import android.text.TextUtils;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.modules.a;

/* OnlineBackgroundListDownloader */
public class h extends i {
    public h(Long l, String str, String str2, a aVar) {
        super(l, str, str2, aVar);
    }

    protected String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.sds.android.ttpod.framework.a.n();
        }
        return e.l(str);
    }
}
