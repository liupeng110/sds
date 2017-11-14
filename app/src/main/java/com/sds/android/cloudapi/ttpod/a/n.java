package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.MessageCollectListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.f;
import java.util.List;

/* MessageCollectAPI */
public class n {
    public static o<BaseResult> a(String str, List<Long> list) {
        return new i(BaseResult.class, "http://v1.ard.q.itlily.com/collect", "add_msg").b("access_token", str).b("msg_ids", f.a((Object) list));
    }

    public static o<BaseResult> b(String str, List<Long> list) {
        return new i(BaseResult.class, "http://v1.ard.q.itlily.com/collect", "cancel_msg").b("access_token", str).b("msg_ids", f.a((Object) list));
    }

    public static o<MessageCollectListResult> a(String str) {
        return new i(MessageCollectListResult.class, "http://v1.ard.q.itlily.com/collect", "get_msg_ids").b("access_token", str);
    }
}
