package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.BillboardsResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* BillboardAPI */
public class c {
    public static o<BillboardsResult> a(int i) {
        return new i(BillboardsResult.class, "http://so.ard.iyyin.com/sug", "billboard").b("size", Integer.valueOf(i));
    }
}
