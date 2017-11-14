package com.sds.android.ttpod.a.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.sds.android.ttpod.common.b.a.a;
import com.tencent.connect.common.Constants;

/* BaseApi */
public abstract class b {
    private String a;
    private String b;
    private long c;

    protected abstract i a(a aVar, a aVar2);

    public b(String str) {
        this.a = str;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(long j) {
        this.c = j;
    }

    public boolean c() {
        return System.currentTimeMillis() + (this.c * 1000) <= System.currentTimeMillis();
    }

    public void a(Context context, Bundle bundle) {
        a(bundle);
        com.sds.android.ttpod.a.d.a.a(context, d(), bundle);
    }

    public void a(Context context) {
        Bundle bundle = new Bundle();
        com.sds.android.ttpod.a.d.a.b(context, d(), bundle);
        a(bundle);
    }

    public String d() {
        return "";
    }

    protected void a(Bundle bundle) {
        String string = bundle.getString("access_token");
        String string2 = bundle.getString(Constants.PARAM_EXPIRES_IN);
        a(string);
        try {
            a(Long.parseLong(string2));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void a(com.sds.android.ttpod.common.a.a aVar, final a aVar2, final a aVar3) {
        com.sds.android.sdk.lib.e.a.a((Object) aVar, new com.sds.android.sdk.lib.e.a.a(this, null) {
            final /* synthetic */ b c;

            protected Object onDoInBackground(Object obj) {
                return this.c.a(aVar2, aVar3);
            }

            protected void onPostExecuteForeground(Object obj) {
                this.c.a(aVar3, (i) obj);
            }
        });
    }

    public boolean e() {
        return false;
    }

    protected void a(a aVar, i iVar) {
        if (aVar != null && iVar != null) {
            aVar.a(iVar);
        }
    }

    public void a(Intent intent) {
    }
}
