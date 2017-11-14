package com.sds.android.ttpod.activities.musiccircle;

import android.content.Intent;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import java.lang.reflect.Method;
import java.util.Map;

public class RepostInputActivity extends BaseInputActivity {
    private Post mPost;

    protected void onSend(String str) {
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.RE_POST, Long.valueOf(this.mPost.getId()), Long.valueOf(this.mPost.getUser().getUserId()), str, ""));
    }

    protected void onLoadCommandMap(Map<com.sds.android.ttpod.framework.modules.a, Method> map) throws NoSuchMethodException {
        super.onLoadCommandMap(map);
        map.put(com.sds.android.ttpod.framework.modules.a.UPDATE_RE_POSTED, i.a(getClass(), "onRepostFinished", BaseResult.class, String.class));
    }

    public void onRepostFinished(BaseResult baseResult, String str) {
        if (baseResult.isSuccess()) {
            onSendFinished();
            this.mPost.setRepostCount(this.mPost.getRepostCount() + 1);
            b.a().a(new a(com.sds.android.ttpod.framework.modules.a.CHANGE_POST_REPOST_COUNT, this.mPost));
            return;
        }
        onSendFail();
        f.a("转发失败");
    }

    protected String onLoadPicUrl() {
        return BaseInputActivity.getFirstPicInPost(this.mPost);
    }

    protected TTPodUser onLoadUser() {
        return this.mPost.getUser();
    }

    protected String onLoadTweet() {
        return this.mPost.getTweet();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            throw new IllegalArgumentException("invalid intent");
        }
        this.mPost = (Post) intent.getSerializableExtra("post");
    }
}
