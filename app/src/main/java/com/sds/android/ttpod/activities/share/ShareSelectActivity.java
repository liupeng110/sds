package com.sds.android.ttpod.activities.share;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager.BadTokenException;
import com.sds.android.cloudapi.ttpod.data.Post;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.a;
import com.sds.android.ttpod.a.a.j;
import com.sds.android.ttpod.a.c.c;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.framework.a.b.d.s;
import com.sds.android.ttpod.framework.a.b.w;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.modules.h.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import java.io.Serializable;

public class ShareSelectActivity extends BaseActivity implements Response {
    public static final String KEY_EXTRA_DATA = "key_extra_data";
    private static final String TAG = "ShareSelectActivity";
    private static a mShareAction;
    private c mShareSelectDialog;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTBSPage("tt_share");
        Serializable serializableExtra = getIntent().getSerializableExtra(KEY_EXTRA_DATA);
        if (serializableExtra instanceof com.sds.android.ttpod.common.b.a.a) {
            com.sds.android.ttpod.common.b.a.a aVar = (com.sds.android.ttpod.common.b.a.a) serializableExtra;
            showShareDialogStatistic(aVar);
            s.a((((com.sds.android.ttpod.common.b.a.a) serializableExtra).k() == com.sds.android.ttpod.common.b.a.a.a.MV ? 1 : null) != null ? "mv" : "song");
            this.mShareSelectDialog = new c(this, aVar);
            e.b(this);
        } else if (serializableExtra instanceof Post) {
            s.a("songlist");
            this.mShareSelectDialog = new com.sds.android.ttpod.activities.musiccircle.e(this, (Post) serializableExtra);
        } else {
            finish();
            return;
        }
        if (this.mShareSelectDialog != null) {
            this.mShareSelectDialog.a(mShareAction);
        }
        this.mShareSelectDialog.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ ShareSelectActivity a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                if (this.a.mShareSelectDialog == null || this.a.mShareSelectDialog.h() != com.sds.android.ttpod.a.e.SINA_WEIBO) {
                    this.a.finish();
                } else if (!b.aD()) {
                    this.a.showFollowTTPodOfficialSinaWeiboDialog((j) this.a.mShareSelectDialog.i());
                    b.J(true);
                }
            }
        });
        this.mShareSelectDialog.show();
    }

    private void showShareDialogStatistic(com.sds.android.ttpod.common.b.a.a aVar) {
        w.a("share", "share", "showDialog", 0, (long) (aVar.q() ? 1 : 0), aVar.g(), aVar.q() ? aVar.f() : aVar.h());
    }

    public static void setShareAction(a aVar) {
        mShareAction = aVar;
    }

    private void showFollowTTPodOfficialSinaWeiboDialog(final j jVar) {
        try {
            h hVar = new h((Context) this, (int) R.string.share_follow_official_weibo_content, (int) R.string.share_follow_official_weibo_positive, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ ShareSelectActivity b;

                public void a(h hVar) {
                    jVar.f();
                }
            }, (int) R.string.cancel, null);
            hVar.setTitle((int) R.string.share_follow_official_weibo_title);
            hVar.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ ShareSelectActivity a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.finish();
                }
            });
            hVar.show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.mShareSelectDialog != null) {
            this.mShareSelectDialog.a(i, i2, intent);
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        g.a(TAG, "lookShare onNewIntent");
        if (this.mShareSelectDialog != null) {
            this.mShareSelectDialog.a(intent);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        g.a(TAG, "lookShare onDestroy");
    }

    public void onResponse(BaseResponse baseResponse) {
        g.a(TAG, "lookShare onResponse");
        if (this.mShareSelectDialog != null) {
            this.mShareSelectDialog.a(baseResponse);
        }
        finish();
    }
}
