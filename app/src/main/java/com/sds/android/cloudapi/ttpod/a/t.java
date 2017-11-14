package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.MvDataResult;
import com.sds.android.cloudapi.ttpod.result.OnlineSearchResult;
import com.sds.android.cloudapi.ttpod.result.SearchSingerResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sina.weibo.sdk.component.WidgetRequestParam;

/* OnlineSearchAPI */
public class t {
    public static o<OnlineSearchResult> a(String str) {
        return new i(OnlineSearchResult.class, "http://so.ard.iyyin.com/sug/sugAll").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str);
    }

    public static o<SearchSingerResult> a(String str, int i, int i2) {
        return new i(SearchSingerResult.class, "http://so.ard.iyyin.com/s/artist").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }

    public static o<MvDataResult> b(String str, int i, int i2) {
        return new i(MvDataResult.class, "http://so.ard.iyyin.com/s/video").b(WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, str).b("page", Integer.valueOf(i)).b("size", Integer.valueOf(i2));
    }
}
