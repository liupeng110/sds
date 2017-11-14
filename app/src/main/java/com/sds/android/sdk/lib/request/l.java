package com.sds.android.sdk.lib.request;

import com.sds.android.sdk.lib.a.a.a;
import java.util.HashMap;

/* PostContentRequest */
public class l<R extends BaseResult> extends o<R> {
    private String b;

    public l(Class<R> cls, String str, String str2) {
        super(cls, str);
        this.b = str2;
    }

    protected a a(String str, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        return com.sds.android.sdk.lib.a.a.a(str, (HashMap) hashMap, this.b);
    }
}
