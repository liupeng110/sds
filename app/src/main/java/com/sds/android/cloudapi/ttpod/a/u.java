package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.User;
import com.sds.android.cloudapi.ttpod.result.CommentListResult;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.cloudapi.ttpod.result.ListenerCountResult;
import com.sds.android.cloudapi.ttpod.result.PostResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.j;
import com.sds.android.sdk.lib.request.n;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.util.Collection;

/* PostAPI */
public class u {
    public static o<BaseResult> a(String str) {
        return new n(BaseResult.class, "http://v1.ard.q.itlily.com/share", "check_update").b("access_token", str);
    }

    public static o<j> b(String str) {
        return new n(j.class, "http://v1.ard.q.itlily.com/share", "user_timeline_ids").b("access_token", str);
    }

    public static o<CommentResult> a(String str, long j, String str2, long j2, long j3) {
        o<CommentResult> b = new n(CommentResult.class, "http://v1.ard.q.itlily.com/share", MediasColumns.COMMENT).b("access_token", str).b("msg_id", Long.valueOf(j)).b("tweet", str2).b("reply_to", Long.valueOf(j2));
        if (j3 != 0) {
            b.b("comment_id", Long.valueOf(j3));
        }
        return b;
    }

    public static o<BaseResult> a(String str, long j, long j2) {
        return new n(BaseResult.class, "http://v1.ard.q.itlily.com/share", "del_comment").b("access_token", str).b("msg_id", Long.valueOf(j)).b("comment_id", Long.valueOf(j2));
    }

    public static o<BaseResult> a(String str, long j, long j2, String str2) {
        o<BaseResult> b = new n(BaseResult.class, "http://v1.ard.q.itlily.com/share", "repost").b("access_token", str).b("msg_id", Long.valueOf(j)).b("reply_to", Long.valueOf(j2));
        if (!m.a(str2)) {
            b.b("tweet", str2);
        }
        return b;
    }

    public static o<j> a(String str, long j) {
        return new i(j.class, "http://v1.ard.q.itlily.com/share", "get_comment_ids").b("access_token", str).b("msg_id", Long.valueOf(j));
    }

    public static o<CommentListResult> a(String str, Collection<Long> collection) {
        return new i(CommentListResult.class, "http://v1.ard.q.itlily.com/share", "get_comment").b("access_token", str).b("comment_ids", collection);
    }

    public static o<j> b(String str, long j) {
        return new n(j.class, "http://v1.ard.q.itlily.com/share", "user_publish_ids").b("access_token", str).b(User.KEY_USER_ID, Long.valueOf(j));
    }

    public static o<PostResult> a(long j) {
        return new i(PostResult.class, "http://v1.ard.q.itlily.com/share", "get_celebrities").b(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, Long.valueOf(j));
    }

    public static o<j> a() {
        return new i(j.class, "http://star.dongting.com/uploads", "rcommend/index.html");
    }

    public static o<j> b() {
        return new i(j.class, "http://star.dongting.com/uploads", "firstpub/index.html");
    }

    public static o<PostResult> a(Collection<Long> collection) {
        return new i(PostResult.class, "http://v1.ard.q.itlily.com/share", "user_timeline").a(u.class).b("msg_ids", collection);
    }

    public static o<PostResult> a(long j, int i) {
        if (j <= 0) {
            return new i(PostResult.class, "http://v1.ard.q.itlily.com/share", "get_celebrities_by_cat").b("cat", Integer.valueOf(i));
        }
        return new i(PostResult.class, "http://v1.ard.q.itlily.com/share", "get_celebrities_by_cat").b(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP, Long.valueOf(j)).b("cat", Integer.valueOf(i));
    }

    public static o<ListenerCountResult> b(long j) {
        return new i(ListenerCountResult.class, "http://v1.ard.q.itlily.com/share/add_listener").b("msg_id", Long.valueOf(j));
    }
}
