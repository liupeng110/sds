package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.a.a.a;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import java.util.HashMap;

/* GetMethodRequest */
public class i<R extends BaseResult> extends k<R> {
    public i(Class<R> cls, String str, String str2) {
        super(cls, str, str2);
    }

    public i(Class<R> cls, String str) {
        super(cls, str, null);
    }

    protected String c() {
        return m.a("/", super.c(), d());
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        g.d("GetMethodRequest", "doHttpRequest in lookNetProblem url=%s", str);
        try {
            return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, (HashMap) hashMap2);
        } catch (Exception e) {
            g.d("GetMethodRequest", "doHttpRequest exception lookNetProblem url=%s exception=%s", str, e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
