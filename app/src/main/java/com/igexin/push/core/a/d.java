package com.igexin.push.core.a;

import com.igexin.push.a.a;
import com.igexin.push.a.j;
import com.igexin.push.a.k;
import com.igexin.push.e.b.g;
import com.igexin.sdk.PushConsts;
import org.json.JSONObject;

public class d extends b {
    private static final String a = j.a;

    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("block_client") && jSONObject.has("duration")) {
                long j = jSONObject.getLong("duration") * 1000;
                long currentTimeMillis = System.currentTimeMillis();
                if (j != 0) {
                    k.g = j + currentTimeMillis;
                    a.a().e();
                    g.g().h();
                }
            }
        } catch (Exception e) {
        }
        return true;
    }
}
