package com.sds.android.ttpod.a.a;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.k.c;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.a.d.b;
import com.sds.android.ttpod.common.b.a.a;

/* QQApi */
public class e extends b {
    protected a a;
    private Activity b;
    private int c = 0;
    private boolean d = false;
    private boolean e = false;

    public e(String str, Activity activity) {
        super(str);
        this.b = activity;
    }

    public i a(a aVar, a aVar2) {
        this.a = aVar;
        a(b.a(this.a, com.sds.android.ttpod.a.e.QQ, true) + " " + this.b.getString(R.string.share_text_tail_info), this.a.d());
        return null;
    }

    protected void a(a aVar, i iVar) {
    }

    private void a(String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(c.a("4bc7b85088e99c5c06a89298cf4833335d74982e"), c.a("4bc7b85088e99c5c06a89298cf4833335d74982e0f6d13a8672ee7bdf579220bcdd54b67ddd01b53a238")));
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.setType("text/*");
            this.b.startActivity(intent);
        } catch (Exception e) {
            g.c("QQApi", "Share Failed To QQ: " + e.toString());
        }
    }

    public static boolean b(Context context) {
        try {
            if (context.getPackageManager().getPackageInfo(c.a("4bc7b85088e99c5c06a89298cf4833335d74982e"), 64) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
