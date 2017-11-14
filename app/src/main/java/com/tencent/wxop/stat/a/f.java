package com.tencent.wxop.stat.a;

import android.content.Context;
import com.tencent.wxop.stat.b.r;
import com.tencent.wxop.stat.c;
import com.tencent.wxop.stat.g;
import org.json.JSONObject;

public final class f extends d {
    public static final g bw;

    static {
        g gVar = new g();
        bw = gVar;
        gVar.s("A9VH9B8L4GX4");
    }

    public f(Context context) {
        super(context, 0, bw);
    }

    public final e ac() {
        return e.NETWORK_DETECTOR;
    }

    public final boolean b(JSONObject jSONObject) {
        r.a(jSONObject, "actky", c.d(this.bv));
        return true;
    }
}
