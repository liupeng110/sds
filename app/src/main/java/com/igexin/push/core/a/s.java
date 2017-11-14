package com.igexin.push.core.a;

import com.igexin.sdk.PushConsts;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import org.json.JSONException;
import org.json.JSONObject;

public class s extends b {
    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("sendmessage_feedback")) {
                f.a().a(jSONObject.getString("appid"), jSONObject.getString("taskid"), jSONObject.getString("actionid"), jSONObject.getString("result"), jSONObject.getLong(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP));
            }
        } catch (JSONException e) {
        }
        return true;
    }
}
