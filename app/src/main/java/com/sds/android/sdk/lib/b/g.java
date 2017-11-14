package com.sds.android.sdk.lib.b;

import com.sds.android.sdk.lib.a.a.a;
import java.util.HashMap;

/* PostContentRequestRest */
public class g<R extends b> extends l<R> {
    private String a;

    public g(Class<R> cls, String str, String str2) {
        super(new e(cls), str);
        this.a = str2;
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, this.a);
    }
}
