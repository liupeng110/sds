package com.sds.android.ttpod.a.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.k.c;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.b.a.a;
import com.sds.android.ttpod.wxapi.WXEntryActivity;
import com.sds.android.ttpod.wxapi.b;
import com.sina.weibo.sdk.utils.LogUtil;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.io.File;

/* WeChatApi */
public class k extends b implements b {
    protected boolean a = false;
    private com.sds.android.sdk.lib.util.b b = new com.sds.android.sdk.lib.util.b();
    private IWXAPI c;
    private a d;
    private Activity e;
    private a f;

    public k(String str, Activity activity) {
        this.e = activity;
        a(Long.MAX_VALUE);
        this.c = WXAPIFactory.createWXAPI(activity.getApplicationContext(), str, true);
        this.c.registerApp(str);
        WXEntryActivity.a(this);
    }

    public i a(a aVar, a aVar2) {
        this.f = aVar2;
        this.d = aVar;
        String d = this.d.d();
        if (this.a && m.a(d)) {
            String str = com.sds.android.ttpod.framework.a.i() + File.separator + "share.png";
            if (!e.b(str)) {
                try {
                    e.a(this.e.getAssets().open("img_share_no_pic.png"), str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.d.c(str);
        }
        return null;
    }

    protected void a(a aVar, i iVar) {
        this.d.m();
        this.d.l();
        this.d.g();
        String d = this.d.d();
        this.d.h();
        this.d.f();
        a(com.sds.android.ttpod.a.d.b.a(this.d, this.a ? com.sds.android.ttpod.a.e.WECHAT_FRIENDS : com.sds.android.ttpod.a.e.WECHAT, true) + " " + this.e.getString(R.string.share_text_tail_info), d);
    }

    public boolean f() {
        return true;
    }

    public boolean g() {
        return this.c.isWXAppInstalled();
    }

    public IWXAPI h() {
        return this.c;
    }

    private void a(String str, String str2) {
        try {
            String a;
            Intent intent = new Intent();
            String a2 = c.a("4bc7b85088e99c5c06a89298cf4a");
            if (this.a) {
                a = c.a("4bc7b85088e99c5c06a89298cf4a7f2f583f9d304e6003f25d30efbbe903072ac9c86f48c0d7086f9f");
            } else {
                a = c.a("4bc7b85088e99c5c06a89298cf4a7f2f583f9d304e6003f25d30efbbe91e0519f5ec");
            }
            intent.setComponent(new ComponentName(a2, a));
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            if (this.a) {
                intent.putExtra(c.a("63ccb00d9ffe9b4f17af89d8"), str);
                intent.setType("image/*");
                if (m.a(str2)) {
                    str2 = "/no_pic_path";
                }
                intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str2)));
            }
            this.e.startActivity(intent);
        } catch (Exception e) {
            LogUtil.e("WeChatApi", "share to we chat failed: " + e.toString());
        }
    }

    public void a(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case -4:
                this.f.a(new i(0, "发送被拒绝"));
                return;
            case -2:
                this.f.a(new i(0, "取消分享"));
                return;
            case 0:
                this.f.a(new i(1, "分享成功"));
                return;
            default:
                this.f.a(new i(0, "发送返回"));
                return;
        }
    }
}
