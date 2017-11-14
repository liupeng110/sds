package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.a.a.a;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.m;
import java.util.HashMap;

/* PostMethodRequestV2 */
public class n<R extends BaseResult> extends m<R> {
    public n(Class<R> cls, String str, String str2) {
        super(cls, str, str2);
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        try {
            return com.sds.android.sdk.lib.a.a.b(str, hashMap, hashMap2);
        } catch (Exception e) {
            g.c("PostMethodRequestV2", "%s create arguments error, cause by %s", e.getMessage());
            return null;
        }
    }

    protected String e() {
        String d = d();
        String e = super.e();
        if (m.a(d)) {
            return e;
        }
        return m.a("/", e, d);
    }
}
