package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.AlikeUserListResult;
import com.sds.android.cloudapi.ttpod.result.TTPodUserResult;
import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.m;
import com.sds.android.sdk.lib.request.o;

/* UserSystemAPI */
public class ae {
    public static o<UserListResult> a(String str, String str2) {
        return new m(UserListResult.class, "http://v2.ttus.ttpod.com/ttus/user", "search").c("access_token", str).b(User.KEY_NICK_NAME, str2);
    }

    public static o<TTPodUserResult> a(String str, long j) {
        return new m(TTPodUserResult.class, "http://v2.ttus.ttpod.com/ttus/user", "info").c("access_token", str).b(User.KEY_USER_ID, Long.valueOf(j));
    }

    public static o<AlikeUserListResult> a(String str) {
        return new i(AlikeUserListResult.class, "http://v1.ard.y.itlily.com/user", "alike_infos").b("access_token", str);
    }
}
