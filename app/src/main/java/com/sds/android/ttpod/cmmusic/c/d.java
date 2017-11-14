package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.ttpod.cmmusic.d.a;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* GetPreListen */
public class d {
    public static a a(String str) {
        try {
            List b = b.b();
            b.add(new BasicNameValuePair("musicId", str));
            JSONObject jSONObject = new JSONObject(b.a("http://open.ttpod.com/api/cl/query", b, "/1.0/crbt/prelisten"));
            String string = jSONObject.getString("resCode");
            String string2;
            a aVar;
            if (string.equals("999011")) {
                string2 = jSONObject.getString("resMsg");
                aVar = new a();
                aVar.a(string);
                aVar.b(string2);
                return aVar;
            }
            String string3 = jSONObject.getString("resMsg");
            string2 = jSONObject.getString("streamUrl");
            aVar = new a();
            aVar.a(string);
            aVar.b(string3);
            aVar.d(string2);
            return aVar;
        } catch (Exception e) {
            e.printStackTrace();
            return new a();
        }
    }
}
