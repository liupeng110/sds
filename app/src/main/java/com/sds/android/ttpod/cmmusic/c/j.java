package com.sds.android.ttpod.cmmusic.c;

import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.cmmusic.b.b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* UserSelectListenQuery */
public class j {
    public static List<b> a() {
        List<b> arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(b.a("http://open.ttpod.com/api/cl/query", b.a(), "/1.0/crbt/box/query")).getJSONArray("ToneInfo");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                arrayList.add(new b(jSONObject.getString("toneName"), jSONObject.getString("singerName"), jSONObject.getString("toneID"), jSONObject.getString("toneValidDay"), jSONObject.getString("tonePreListenAddress")));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    public static boolean b() {
        if (m.a(d.a("600907000003273431").h(), "000000")) {
            return true;
        }
        return false;
    }
}
