package com.igexin.push.core.a;

import com.igexin.push.a.j;
import com.igexin.push.core.c.c;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import org.json.JSONException;
import org.json.JSONObject;

public class n extends b {
    private static final String a = j.a;

    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("received")) {
                try {
                    if (c.a().a(Long.parseLong(jSONObject.getString(StarCategory.KEY_STAR_CATEGORY_ID)))) {
                        f.a().g();
                    }
                } catch (NumberFormatException e) {
                }
            }
        } catch (JSONException e2) {
        }
        return true;
    }
}
