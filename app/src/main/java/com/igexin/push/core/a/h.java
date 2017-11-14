package com.igexin.push.core.a;

import com.igexin.a.a.d.d;
import com.igexin.push.a.j;
import com.igexin.push.c.c.n;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class h extends a {
    private static final String a = j.a;
    private static Map b;

    public h() {
        b = new HashMap();
        b.put("redirect_server", new o());
        b.put("response_deviceid", new r());
        b.put("pushmessage", new m());
        b.put("response_ca_list", new q());
        b.put("bindappid_result", new c());
        b.put("received", new n());
        b.put("sendmessage_feedback", new s());
        b.put("block_client", new d());
    }

    public boolean a(d dVar) {
        return false;
    }

    public boolean a(Object obj) {
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (nVar.a() && nVar.e != null) {
                try {
                    JSONObject jSONObject = new JSONObject((String) nVar.e);
                    if (jSONObject.has(StarCategory.KEY_STAR_CATEGORY_ID)) {
                        f.a().a(jSONObject.getString(StarCategory.KEY_STAR_CATEGORY_ID));
                    }
                    if (jSONObject != null && jSONObject.has(PushConsts.CMD_ACTION)) {
                        b bVar = (b) b.get(jSONObject.getString(PushConsts.CMD_ACTION));
                        if (bVar != null) {
                            return bVar.a(obj, jSONObject);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return false;
    }
}
