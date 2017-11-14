package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.LabeledTTPodUserListResult;
import com.sds.android.cloudapi.ttpod.result.StarCategoryResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sina.weibo.sdk.component.WidgetRequestParam;

/* CelebriteAPI */
public class d {
    public static o<StarCategoryResult> a() {
        return new i(StarCategoryResult.class, "http://v2.ttus.ttpod.com", "categories");
    }

    public static o<LabeledTTPodUserListResult> a(int i, int i2, int i3) {
        return new i(LabeledTTPodUserListResult.class, "http://v2.ttus.ttpod.com", WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY).a((Object) Integer.valueOf(i)).a(Integer.valueOf(i2)).a(Integer.valueOf(i3));
    }

    public static o<LabeledTTPodUserListResult> b(int i, int i2, int i3) {
        return new i(LabeledTTPodUserListResult.class, "http://v2.ttus.ttpod.com", "celebrities").a((Object) Integer.valueOf(i)).a(Integer.valueOf(i2)).a(Integer.valueOf(i3));
    }
}
