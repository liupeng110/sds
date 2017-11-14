package com.igexin.push.core.d;

import com.igexin.a.b.a;
import com.igexin.push.core.a.f;
import com.igexin.push.core.g;
import com.igexin.push.e.a.b;
import com.igexin.sdk.PushBuildConfig;
import com.igexin.sdk.PushConsts;
import org.json.JSONObject;

public class e extends b {
    public e(String str) {
        super(str);
        a();
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(PushConsts.CMD_ACTION, "sdkconfig");
            jSONObject.put("cid", g.u);
            jSONObject.put("appid", g.c);
            jSONObject.put("sdk_version", PushBuildConfig.sdk_conf_version);
            b(a.b(jSONObject.toString().getBytes()));
        } catch (Exception e) {
        }
    }

    public void a(byte[] bArr) {
        f.a().a(bArr);
    }

    public int b() {
        return 0;
    }
}
