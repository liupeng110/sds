package com.sds.android.sdk.lib.b;

import com.sds.android.sdk.lib.a.a.a;
import com.sds.android.sdk.lib.util.g;
import java.util.HashMap;

/* GetRequestRest */
public class d<R extends b> extends l<R> {
    public d(Class<R> cls, String str) {
        super(new e(cls), str);
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        g.d("GetRequestRest", "doHttpRequest in lookNetProblem url=%s", str);
        try {
            return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, (HashMap) hashMap2);
        } catch (Exception e) {
            g.d("GetRequestRest", "doHttpRequest exception lookNetProblem url=%s exception=%s", str, e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
