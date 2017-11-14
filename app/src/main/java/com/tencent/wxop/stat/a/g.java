package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.b.l;
import com.tencent.wxop.stat.b.r;
import com.tencent.wxop.stat.h;
import org.json.JSONObject;

public final class g extends d {
    private static String a = null;
    private String aR = null;
    private String aS = null;

    public g(Context context, int i, com.tencent.wxop.stat.g gVar) {
        super(context, i, gVar);
        this.aR = h.r(context).b();
        if (a == null) {
            a = l.C(context);
        }
    }

    public final e ac() {
        return e.NETWORK_MONITOR;
    }

    public final void b(String str) {
        this.aS = str;
    }

    public final boolean b(JSONObject jSONObject) {
        r.a(jSONObject, "op", a);
        r.a(jSONObject, "cn", this.aR);
        jSONObject.put("sp", this.aS);
        return true;
    }
}
