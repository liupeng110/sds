package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.UserListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.j;
import com.sds.android.sdk.lib.request.o;
import java.util.Collection;

/* FriendsAPI */
public class k {
    public static o<BaseResult> a(String str, long j) {
        return new i(BaseResult.class, "http://v1.ard.y.itlily.com/friends", "add").a((Object) str).a(Long.valueOf(j));
    }

    public static o<BaseResult> b(String str, long j) {
        return new i(BaseResult.class, "http://v1.ard.y.itlily.com/friends", "del").a((Object) str).a(Long.valueOf(j));
    }

    public static o<j> a(long j) {
        return new i(j.class, "http://v1.ard.y.itlily.com/friends", "followings/by_ids").a((Object) Long.valueOf(j));
    }

    public static o<UserListResult> a(long j, int i, int i2, long j2) {
        return new i(UserListResult.class, "http://v1.ard.y.itlily.com/friends", "followings/tuid").a((Object) Long.valueOf(j)).a(Integer.valueOf(i)).a(Integer.valueOf(i2)).a(Long.valueOf(j2));
    }

    public static o<UserListResult> b(long j, int i, int i2, long j2) {
        return new i(UserListResult.class, "http://v1.ard.y.itlily.com/friends", "followers/tuid").a((Object) Long.valueOf(j)).a(Integer.valueOf(i)).a(Integer.valueOf(i2)).a(Long.valueOf(j2));
    }

    public static o<UserListResult> a(Collection<Long> collection) {
        return new i(UserListResult.class, "http://v1.ard.y.itlily.com/friends", "info").a((Object) collection);
    }
}
