package com.igexin.push.core.a;

import com.igexin.a.a.c.a;
import com.igexin.push.a.j;
import com.igexin.push.core.c.r;
import com.igexin.push.core.c.u;
import com.igexin.push.core.f;
import com.igexin.push.core.g;
import com.igexin.sdk.PushConsts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class o extends b {
    private static final String a = j.a;

    public boolean a(Object obj, JSONObject jSONObject) {
        try {
            if (jSONObject.has(PushConsts.CMD_ACTION) && jSONObject.getString(PushConsts.CMD_ACTION).equals("redirect_server")) {
                long parseLong;
                String string = jSONObject.getString("delay");
                List arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject.getJSONArray("address_list");
                a.a("redirect|" + string + "|" + jSONArray.toString());
                for (int i = 0; i < jSONArray.length(); i++) {
                    String string2 = jSONArray.getString(i);
                    int indexOf = string2.indexOf(44);
                    if (indexOf > 0) {
                        String substring = string2.substring(0, indexOf);
                        string2 = string2.substring(indexOf + 1);
                        long currentTimeMillis = System.currentTimeMillis();
                        if (string2 != null) {
                            try {
                                long parseLong2 = Long.parseLong(string2);
                                u uVar = new u();
                                uVar.a = "socket://" + substring;
                                uVar.b = (parseLong2 * 1000) + currentTimeMillis;
                                arrayList.add(uVar);
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                }
                try {
                    parseLong = Long.parseLong(string) * 1000;
                } catch (NumberFormatException e2) {
                    parseLong = 0;
                }
                if (parseLong >= 0) {
                    g.F = parseLong;
                }
                r.a(arrayList);
                f.a().g().e();
            }
        } catch (JSONException e3) {
        }
        return true;
    }
}
