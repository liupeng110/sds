package com.sds.android.ttpod.a.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.b.a.a;

/* OtherApi */
public class d extends b {
    private Context a;

    public d(Context context) {
        this.a = context;
        a(Long.MAX_VALUE);
    }

    public i a(a aVar, a aVar2) {
        a(this.a, "text/plain", aVar.f(), aVar.d(), aVar.k() == a.a.POST ? this.a.getString(R.string.forward_to) : this.a.getString(R.string.share_to));
        return null;
    }

    public void a(Context context, String str, String str2, String str3, String str4) {
        Intent type = new Intent("android.intent.action.SEND").setType(str);
        String string = this.a.getString(R.string.share);
        type.putExtra("sms_body", str2);
        type.putExtra("android.intent.extra.SUBJECT", string);
        type.putExtra("android.intent.extra.TEXT", str2);
        type.setFlags(805306368);
        ((Activity) context).startActivityForResult(Intent.createChooser(type, str4), 1);
    }
}
