package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.ttpod.cmmusic.d.a;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

/* ListenOrderInfo */
public class f {
    public static a a(String str, String str2, String str3) {
        try {
            List a = b.a();
            a.add(new BasicNameValuePair("musicId", str));
            JSONObject jSONObject = new JSONObject(b.a("http://open.ttpod.com/api/cl/query", a, "/1.0/crbt/prelisten"));
            String string = jSONObject.getString("resCode");
            String string2 = jSONObject.getString("resMsg");
            String string3 = jSONObject.getString("price");
            String string4 = jSONObject.getString("invalidDate");
            a aVar = new a();
            aVar.a(string);
            aVar.b(string2);
            aVar.g(str2);
            aVar.h(str3);
            aVar.c(str);
            aVar.f(string3);
            aVar.e(string4);
            return aVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static a a(a aVar) {
        try {
            List a = b.a();
            a.add(new BasicNameValuePair("musicId", aVar.j()));
            JSONObject jSONObject = new JSONObject(b.a("http://open.ttpod.com/api/cl/query", a, "/1.0/crbt/order"));
            String string = jSONObject.getString("resCode");
            String string2 = jSONObject.getString("resMsg");
            a aVar2 = new a();
            aVar2.a(string);
            aVar2.b(string2);
            return aVar2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
