package com.igexin.push.core.a;

import com.igexin.push.a.j;
import com.igexin.push.core.b.e;
import com.igexin.push.core.b.h;
import com.igexin.push.core.c.f;
import com.igexin.sdk.PushConsts;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q extends b {
    private static final String a = j.a;

    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("response_ca_list")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ca_list");
                Map hashMap = new HashMap();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    h hVar = new h();
                    hVar.a(jSONObject2.getString("pkgname"));
                    hVar.c(jSONObject2.getString("signature"));
                    hVar.a(e.a().c(jSONObject2.getString("permissions")));
                    hashMap.put(hVar.a(), hVar);
                }
                f.a().e(System.currentTimeMillis());
                if (hashMap.size() > 0) {
                    e.a().a(hashMap);
                }
            }
        } catch (JSONException e) {
        }
        return true;
    }
}
