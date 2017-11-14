package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.HotwordsResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;

/* HotWordsAPI */
public class m {
    public static o<HotwordsResult> a() {
        return new i(HotwordsResult.class, "http://ayyc.ttpod.com").a((Object) "search_words");
    }
}
