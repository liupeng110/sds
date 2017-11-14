package com.sds.android.ttpod.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.g;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String a = WXEntryActivity.class.getSimpleName();
    private static b b;
    private static a c;
    private IWXAPI d;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.d = WXAPIFactory.createWXAPI(getApplicationContext(), "wx35c4036acd33a2bc", true);
        this.d.registerApp("wx35c4036acd33a2bc");
        this.d.handleIntent(getIntent(), this);
    }

    public void onReq(BaseReq baseReq) {
        g.a(a, "wechat onReq transaction:" + baseReq.transaction);
        if (c != null) {
            c.a(baseReq);
        }
        finish();
    }

    public void onResp(BaseResp baseResp) {
        g.a(a, "wechat onResp code:" + baseResp.errCode);
        if (b != null) {
            b.a(baseResp);
        }
        finish();
    }

    public void onNewIntent(Intent intent) {
        this.d.handleIntent(intent, this);
    }

    public static void a(b bVar) {
        b = bVar;
    }
}
