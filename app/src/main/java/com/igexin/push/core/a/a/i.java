package com.igexin.push.core.a.a;

import android.content.Intent;
import android.net.Uri;
import com.igexin.push.a.j;
import com.igexin.push.core.a.f;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.k;
import com.igexin.push.core.g;
import org.json.JSONException;
import org.json.JSONObject;

public class i implements a {
    private static final String a = j.a;

    private void a(k kVar, String str) {
        String a = kVar.a();
        if (a != null) {
            int indexOf = a.indexOf(str);
            if (indexOf != -1) {
                String str2 = "";
                String str3 = null;
                int indexOf2 = a.indexOf("&");
                if (indexOf2 == -1) {
                    str2 = a.substring(0, indexOf - 1);
                    a = a.substring(indexOf);
                    if (a.indexOf("=") != -1) {
                        str3 = a.substring(a.indexOf("=") + 1);
                    }
                } else if (a.charAt(indexOf - 1) == '?') {
                    str2 = a.substring(0, indexOf);
                    str2 = str2 + a.substring(indexOf2 + 1);
                    a = a.substring(indexOf, indexOf2);
                    if (a.indexOf("=") != -1) {
                        str3 = a.substring(a.indexOf("=") + 1);
                    }
                } else if (a.charAt(indexOf - 1) == '&') {
                    String substring = a.substring(0, indexOf - 1);
                    str2 = a.substring(indexOf);
                    str3 = "";
                    int indexOf3 = str2.indexOf("&");
                    if (indexOf3 != -1) {
                        str3 = str2.substring(indexOf3);
                        str2 = str2.substring(0, indexOf3);
                        str2 = str2.substring(str2.indexOf("=") + 1);
                    } else {
                        str2 = str2.substring(str2.indexOf("=") + 1);
                    }
                    String str4 = str2;
                    str2 = substring + str3;
                    str3 = str4;
                }
                kVar.a(str2);
                kVar.b(str3);
            }
        }
    }

    public b a(PushTaskBean pushTaskBean, BaseAction baseAction) {
        return b.success;
    }

    public BaseAction a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("url") && jSONObject.has("do") && jSONObject.has("actionid")) {
                String string = jSONObject.getString("url");
                if (!string.equals("")) {
                    BaseAction kVar = new k();
                    kVar.setType("startweb");
                    kVar.setActionId(jSONObject.getString("actionid"));
                    kVar.setDoActionId(jSONObject.getString("do"));
                    kVar.a(string);
                    if (jSONObject.has("is_withcid") && jSONObject.getString("is_withcid").equals("true")) {
                        kVar.a(true);
                    }
                    if (!jSONObject.has("is_withnettype") || !jSONObject.getString("is_withnettype").equals("true")) {
                        return kVar;
                    }
                    kVar.b(true);
                    return kVar;
                }
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public boolean b(PushTaskBean pushTaskBean, BaseAction baseAction) {
        k kVar = (k) baseAction;
        a(kVar, "targetpkgname");
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setPackage(kVar.b());
        intent.setData(Uri.parse(kVar.c()));
        try {
            g.i.startActivity(intent);
        } catch (Exception e) {
        }
        if (!baseAction.getDoActionId().equals("")) {
            f.a().a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), baseAction.getDoActionId());
        }
        return true;
    }
}
