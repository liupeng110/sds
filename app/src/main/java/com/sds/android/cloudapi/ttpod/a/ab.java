package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.SplashDataResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;

/* SplashAPI */
public final class ab {
    private static final String a = (ad.a() + "/splash/splashes");

    public static o<SplashDataResult> a(int i) {
        return new i(SplashDataResult.class, a).b("version", Integer.valueOf(i)).b("s", b.b()).b("f", b.d()).b("v", b.c());
    }
}
