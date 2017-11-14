package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.PrivateMessageContentListResult;
import com.sds.android.cloudapi.ttpod.result.PrivateMessageOverViewListResult;
import com.sds.android.sdk.core.statistic.SEvent;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.n;
import com.sds.android.sdk.lib.request.o;

/* PrivateMessageAPI */
public class v {
    public static o<PrivateMessageOverViewListResult> a(String str, long j, int i) {
        o<PrivateMessageOverViewListResult> b = new i(PrivateMessageOverViewListResult.class, "http://v1.ard.q.itlily.com/privatemsg", "get_list").a(v.class).b("access_token", str).b("size", Integer.valueOf(i));
        if (j > 0) {
            b.b("last_modified", Long.valueOf(j));
        }
        return b;
    }

    public static o<PrivateMessageContentListResult> a(String str, long j, long j2, int i) {
        o<PrivateMessageContentListResult> b = new i(PrivateMessageContentListResult.class, "http://v1.ard.q.itlily.com/privatemsg", "get_pm").a(v.class).b("access_token", str).b(SEvent.FIELD_TO, Long.valueOf(j)).b("size", Integer.valueOf(i));
        if (j2 > 0) {
            b.b("last_modified", Long.valueOf(j2));
        }
        return b;
    }

    public static o<BaseResult> a(String str, long j, String str2) {
        return new n(BaseResult.class, "http://v1.ard.q.itlily.com/privatemsg", "send").b("access_token", str).b(SEvent.FIELD_TO, Long.valueOf(j)).b("message", str2);
    }

    public static o<BaseResult> a(String str, String str2) {
        return new n(BaseResult.class, "http://v1.ard.q.itlily.com/privatemsg", "delete_pm").b("access_token", str).b("pm_id", str2);
    }

    public static o<BaseResult> a(String str, long j) {
        return new n(BaseResult.class, "http://v1.ard.q.itlily.com/privatemsg", "delete_list").b("access_token", str).b(SEvent.FIELD_TO, Long.valueOf(j));
    }
}
