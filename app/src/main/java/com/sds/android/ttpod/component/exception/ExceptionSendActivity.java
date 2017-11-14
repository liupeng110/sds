package com.sds.android.ttpod.component.exception;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import com.sds.android.sdk.core.statistic.SSystemEvent;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.EnvironmentUtils.a;
import com.sds.android.sdk.lib.util.EnvironmentUtils.c;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.a.h;
import com.sds.android.ttpod.framework.a.b.g;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.storage.environment.b;

public class ExceptionSendActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("android.intent.extra.SUBJECT");
        String stringExtra2 = getIntent().getStringExtra("android.intent.extra.TEXT");
        String stringExtra3 = getIntent().getStringExtra("extra_default_logs");
        String stringExtra4 = getIntent().getStringExtra("extra_life_circles");
        String c = m.a(c.a()) ? c.c() : c.a();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("clientId:").append(b.aG()).append("\r\n").append("imei:").append(c).append("\r\n");
        stringBuilder.append("buildType:").append(a.e()).append("\r\n");
        if (b.av()) {
            stringBuilder.append("userInfo:").append(b.at().getUserName()).append(" ").append(b.at().getUserId()).append("\r\n");
        }
        stringBuilder.append(a.a());
        stringBuilder.append(stringExtra2);
        stringBuilder.append("\r\nlifeCircle:\r\n").append(stringExtra4);
        stringBuilder.append("\r\nlogs:\r\n").append(stringExtra3);
        com.sds.android.sdk.core.b.b.a(stringExtra, stringBuilder.toString(), new com.sds.android.sdk.core.b.b.a(this) {
            final /* synthetic */ ExceptionSendActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }
        });
        try {
            h hVar = new h((Context) this, (int) R.string.exception_content, null, null);
            hVar.setTitle((int) R.string.exception);
            hVar.a((int) R.string.send, new com.sds.android.ttpod.common.a.a.a<h>(this) {
                final /* synthetic */ ExceptionSendActivity a;

                {
                    this.a = r1;
                }

                public void a(h hVar) {
                    new SUserEvent("PAGE_CLICK", r.ACTION_EXCEPTION_SEND_CONFIRM.getValue(), s.PAGE_NONE.getValue()).post();
                    this.a.finish();
                }
            }, (int) R.string.cancel, new com.sds.android.ttpod.common.a.a.a(this) {
                final /* synthetic */ ExceptionSendActivity a;

                {
                    this.a = r1;
                }

                public void a(Object obj) {
                    new SUserEvent("PAGE_CLICK", r.ACTION_EXCEPTION_SEND_CANCEL.getValue(), s.PAGE_NONE.getValue()).post();
                    this.a.finish();
                }
            });
            hVar.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ ExceptionSendActivity a;

                {
                    this.a = r1;
                }

                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4) {
                        this.a.finish();
                    }
                    return false;
                }
            });
            hVar.show();
            g.a();
            new SSystemEvent("SYS_EXCEPTION", "crash").append("origin", "error").post();
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }
}
