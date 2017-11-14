package com.sds.android.ttpod.cmmusic.c;

import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* SettingUserListenDefaultAndDelete */
public class i {
    public static String a(String str) {
        try {
            List a = b.a();
            a.add(new BasicNameValuePair("crbtId", str));
            JSONObject jSONObject = new JSONObject(b.a("http://open.ttpod.com/api/cl/query", a, "/1.0/crbt/box/default"));
            jSONObject.getString("resCode");
            return jSONObject.getString("resMsg");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
