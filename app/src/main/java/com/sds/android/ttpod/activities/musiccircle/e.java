package com.sds.android.ttpod.activities.musiccircle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.a.c.c;
import com.sds.android.ttpod.b.f;
import com.sds.android.ttpod.b.s;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.base.BaseFragment;
import com.sds.android.ttpod.framework.storage.environment.b;

/* SharePostSelectDialog */
public class e extends c {
    private Post c;

    public e(Activity activity, Post post) {
        super(activity, s.a(post, ""));
        if (post == null) {
            throw new IllegalArgumentException("post should not be null");
        }
        this.c = post;
    }

    protected void b() {
        super.b();
        if (b.av()) {
            Context context = getContext();
            context.startActivity(new Intent(context, RepostInputActivity.class).putExtra("post", this.c));
            if (context instanceof Activity) {
                ((Activity) context).finish();
                return;
            }
            return;
        }
        f.a(true);
    }

    protected void a(com.sds.android.ttpod.a.e eVar) {
        if (this.c != null) {
            new SUserEvent("PAGE_CLICK", b(eVar), com.sds.android.ttpod.framework.a.b.s.PAGE_SHARE_DIALOG.getValue(), com.sds.android.ttpod.framework.a.b.s.PAGE_NONE.getValue()).append(BaseFragment.KEY_SONG_LIST_ID, Long.valueOf(this.c.getSonglistId())).post();
        }
    }

    protected void c() {
        this.b.put(com.sds.android.ttpod.a.e.WECHAT, r.ACTION_SONG_LIST_SHARE_TO_WECHAT);
        this.b.put(com.sds.android.ttpod.a.e.WECHAT_FRIENDS, r.ACTION_SONG_LIST_SHARE_TO_WECHAT_FRIEND_CIRCLE);
        this.b.put(com.sds.android.ttpod.a.e.MUSIC_CYCLE, r.ACTION_SONG_LIST_SHARE_TO_MUSIC_CIRCLE);
        this.b.put(com.sds.android.ttpod.a.e.QQ, r.ACTION_SONG_LIST_SHARE_TO_QQ_FRIEND);
        this.b.put(com.sds.android.ttpod.a.e.QZONE, r.ACTION_SONG_LIST_SHARE_TO_QQ_ZONE);
        this.b.put(com.sds.android.ttpod.a.e.SINA_WEIBO, r.ACTION_SONG_LIST_SHARE_TO_SINA_WEIBO);
        this.b.put(com.sds.android.ttpod.a.e.QQ_WEIBO, r.ACTION_SONG_LIST_SHARE_TO_TENCENT_WEIBO);
        this.b.put(com.sds.android.ttpod.a.e.OTHER, r.ACTION_SONG_LIST_SHARE_TO_OTHER);
    }
}
