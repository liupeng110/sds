package com.sds.android.ttpod.a.a;

import com.sds.android.cloudapi.ttpod.data.ShortUrl;
import com.sds.android.sdk.lib.a.a.a;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import java.util.HashMap;

/* ShareAPI */
public final class h {
    public static ShortUrl a(String str) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("realUrl", str);
        a b = com.sds.android.sdk.lib.a.a.b("http://api.shorturl.dongting.com/shorturl", hashMap, hashMap2);
        if (b == null || b.c() != 200) {
            return null;
        }
        ShortUrl shortUrl = (ShortUrl) f.a(m.a(b.e()), ShortUrl.class);
        shortUrl.setCode(b.c());
        return shortUrl;
    }
}
