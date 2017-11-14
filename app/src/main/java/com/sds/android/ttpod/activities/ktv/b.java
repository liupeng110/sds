package com.sds.android.ttpod.activities.ktv;

import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import java.util.Map;

/* KtvApi */
public class b {
    public static o<e> a(String str) {
        return new i(e.class, str, null);
    }

    public static o<e> a(String str, Map<String, Object> map) {
        return new i(e.class, str, "bind").a((Map) map);
    }
}
