package com.igexin.push.core.a;

import com.igexin.a.a.c.a;
import com.igexin.push.a.j;
import com.igexin.push.core.c.f;
import com.igexin.push.core.g;
import com.igexin.sdk.PushConsts;
import org.json.JSONException;
import org.json.JSONObject;

public class r extends b {
    private static final String a = j.a;

    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("response_deviceid")) {
                f.a().a(jSONObject.getString("deviceid"));
                if (g.ay != null) {
                    g.ay.t();
                    g.ay = null;
                }
                if (g.B != null) {
                    com.igexin.push.core.f.a().g().i();
                }
                a.a("deviceidRsp|" + g.B);
            }
        } catch (JSONException e) {
        }
        return true;
    }
}
