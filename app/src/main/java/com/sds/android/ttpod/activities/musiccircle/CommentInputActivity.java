package com.sds.android.ttpod.activities.musiccircle;

import android.content.Intent;
import com.sds.android.cloudapi.ttpod.a.u;
import com.sds.android.cloudapi.ttpod.data.Notice;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.cloudapi.ttpod.data.TTPodUser;
import com.sds.android.cloudapi.ttpod.result.CommentResult;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.ttpod.component.d.f;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.storage.environment.b;

public class CommentInputActivity extends BaseInputActivity {
    private Notice mNotice;

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mNotice = (Notice) intent.getSerializableExtra("notice");
        if (this.mNotice == null) {
            finish();
        }
    }

    protected void onSend(String str) {
        if (str.length() == 0) {
            f.a("请输入有效评论");
            return;
        }
        Post a = com.sds.android.ttpod.framework.modules.f.f.a(this.mNotice.getOriginPost());
        if (b.av()) {
            final o a2 = u.a(b.aw().getToken(), a.getPostId(), str, this.mNotice.getComment().getUser().getUserId(), this.mNotice.getComment().getId());
            a2.a(new p<CommentResult>(this) {
                final /* synthetic */ CommentInputActivity b;

                public /* synthetic */ void onRequestFailure(BaseResult baseResult) {
                    b((CommentResult) baseResult);
                }

                public /* synthetic */ void onRequestSuccess(BaseResult baseResult) {
                    a((CommentResult) baseResult);
                }

                public void a(CommentResult commentResult) {
                    this.b.onSendFinished();
                }

                public void b(CommentResult commentResult) {
                    this.b.onSendFail();
                    f.a("评论失败");
                    g.g(a2.b());
                }
            });
        }
    }

    protected String onLoadPicUrl() {
        return BaseInputActivity.getFirstPicInPost(com.sds.android.ttpod.framework.modules.f.f.a(this.mNotice.getOriginPost()));
    }

    protected TTPodUser onLoadUser() {
        return this.mNotice.getComment().getUser();
    }

    protected String onLoadTweet() {
        return this.mNotice.getComment().getTweet();
    }
}
