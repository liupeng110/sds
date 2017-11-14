package com.sds.android.ttpod.a.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.k.c;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.d.b;
import com.sds.android.ttpod.a.e;
import com.sds.android.ttpod.common.b.a.a;
import java.io.File;

/* QQZoneApi */
public class g extends e {
    private String b;
    private int c;
    private Activity d;

    public g(Activity activity, String str) {
        super(str, activity);
        this.d = activity;
    }

    public i a(a aVar, a aVar2) {
        this.a = aVar;
        a(b.a(this.a, e.QZONE, true) + " " + this.d.getString(R.string.share_text_tail_info), this.a.d());
        return null;
    }

    public boolean f() {
        try {
            if (this.d.getPackageManager().getPackageInfo(c.a("4bc7b8508df69d5106"), 64) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private void a(String str, String str2) {
        Object obj = null;
        try {
            if (this.d.getPackageManager().getPackageInfo(c.a("4bc7b8508df69d5106"), 64) != null) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(c.a("4bc7b8508df69d5106"), c.a("4bc7b8508df69d5106bec8dbcd432436543f862f447e11a86737e0e7f93e462ffaca6461f9cc0f56bf32f92a36b828f99b4ab71fde8df0")));
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", str);
                if (str2 != null) {
                    File file = new File(str2);
                    if (file.exists()) {
                        intent.setType("image/*");
                        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
                    }
                }
                this.d.startActivity(intent);
                obj = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (obj == null) {
            try {
                this.d.startActivity(this.d.getPackageManager().getLaunchIntentForPackage(c.a("4bc7b85088e99c5c06a89298cf4833335d74982e")));
            } catch (Exception e2) {
                com.sds.android.sdk.lib.util.g.a("QQZoneApi", "share to qzone failed");
            }
        }
    }

    public void b(String str) {
        this.b = str;
    }

    protected void a(Bundle bundle) {
        super.a(bundle);
        b(bundle.getString("openid"));
    }

    public String d() {
        return "TENTCANT_TTPOD_TOKEN";
    }

    public boolean e() {
        if (this.c < 100013 || this.c > 100016) {
            return super.e();
        }
        return true;
    }
}
