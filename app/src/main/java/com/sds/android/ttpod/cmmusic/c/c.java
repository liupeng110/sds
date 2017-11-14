package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.ttpod.cmmusic.d.a;
import org.json.JSONObject;

/* CaiLingFuctionOpenAndCancel */
public class c {
    public static a a() {
        try {
            return a(b.a("http://open.ttpod.com/api/cl/query", b.a(), "/1.0/crbt/open"));
        } catch (Exception e) {
            e.printStackTrace();
            return new a();
        }
    }

    public static a b() {
        try {
            return a(b.a("http://open.ttpod.com/api/cl/query", b.a(), "/1.0/crbt/open/check"));
        } catch (Exception e) {
            e.printStackTrace();
            return new a();
        }
    }

    private static a a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new a(jSONObject.getString("resCode"), jSONObject.getString("resMsg"), "5");
        } catch (Exception e) {
            e.printStackTrace();
            return new a();
        }
    }
}
