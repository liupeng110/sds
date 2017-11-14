package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.result.NewFollowersListResult;
import com.sds.android.cloudapi.ttpod.result.NewNoticeCountResult;
import com.sds.android.cloudapi.ttpod.result.NoticeListResult;
import com.sds.android.cloudapi.ttpod.result.SystemNoticeListResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import com.tencent.open.SocialConstants;

/* NoticeAPI */
public class q {
    public static o<NewNoticeCountResult> a(String str) {
        return new i(NewNoticeCountResult.class, "http://v1.ard.q.itlily.com/notice", "get_notice_tips").b("access_token", str);
    }

    public static o<BaseResult> a(String str, int i) {
        return new i(BaseResult.class, "http://v1.ard.q.itlily.com/notice", "clear_unread").b("access_token", str).b(SocialConstants.PARAM_TYPE, Integer.valueOf(i));
    }

    public static o<BaseResult> a(String str, String str2) {
        return new i(BaseResult.class, "http://v1.ard.q.itlily.com/notice", "del_notice").b("access_token", str).b("notice_id", str2);
    }

    public static o<NoticeListResult> a(String str, int i, int i2, int i3) {
        return new i(NoticeListResult.class, "http://v1.ard.q.itlily.com/notice", "get_notice").b("access_token", str).b(SocialConstants.PARAM_TYPE, Integer.valueOf(i)).b(ParamKey.OFFSET, Integer.valueOf(i2)).b("size", Integer.valueOf(i3));
    }

    public static o<NewFollowersListResult> b(String str) {
        return new i(NewFollowersListResult.class, "http://v1.ard.q.itlily.com/notice", "get_new_followers").b("access_token", str);
    }

    public static o<SystemNoticeListResult> a(String str, long j, int i) {
        o<SystemNoticeListResult> b = new i(SystemNoticeListResult.class, "http://v1.ard.q.itlily.com/notice", "get_system_msg").b("access_token", str).b("size", Integer.valueOf(i));
        if (j > 0) {
            b.b(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, Long.valueOf(j));
        }
        return b;
    }
}
