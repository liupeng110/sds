package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.AppVersionResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.m;

/* AppVersionAPI */
public class b {
    public o<AppVersionResult> a(String str, String str2, String str3, boolean z) {
        return a(str, str2, str3, z, null);
    }

    public o<AppVersionResult> a(String str, String str2, String str3, boolean z, String str4) {
        o<AppVersionResult> b = new i(AppVersionResult.class, "http://0.0.0.0", "check").b("st", "other_update").b("vip", Integer.valueOf(z ? 1 : 0)).b("s", str2).b("v", str).b("f", str3);
        if (!m.a(str4)) {
            b.b("app", str4);
        }
        return b;
    }
}
