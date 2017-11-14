package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.AroundUserListResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* ShakeAPI */
public class y {
    public static o<AroundUserListResult> a(String str, float f, float f2) {
        return new i(AroundUserListResult.class, "http://v1.ard.yy.itlily.com/shake").b("access_token", str).b("lng", Float.valueOf(f)).b("lat", Float.valueOf(f2));
    }
}
