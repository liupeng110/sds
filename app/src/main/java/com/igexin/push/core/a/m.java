package com.igexin.push.core.a;

import com.igexin.push.a.j;
import com.igexin.push.c.c.n;
import com.igexin.push.core.g;
import com.igexin.sdk.PushConsts;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

public class m extends b {
    private static final String a = j.a;

    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            n nVar = (n) obj;
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("pushmessage")) {
                byte[] bArr = (nVar.f == null || !(nVar.f instanceof byte[])) ? null : (byte[]) nVar.f;
                String string = jSONObject.getString("taskid");
                if (g.an.containsKey(string)) {
                    ((Timer) g.an.get(string)).cancel();
                    g.an.remove(string);
                }
                f.a().a(jSONObject, bArr, true);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
