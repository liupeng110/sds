package com.igexin.push.core.a.a;

import android.content.Intent;
import android.content.pm.PackageManager;
import com.igexin.push.core.a.f;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.g;
import org.json.JSONException;
import org.json.JSONObject;

public class j implements a {
    private static final String a = com.igexin.push.a.j.a;

    public b a(PushTaskBean pushTaskBean, BaseAction baseAction) {
        return b.success;
    }

    public BaseAction a(JSONObject jSONObject) {
        try {
            BaseAction jVar = new com.igexin.push.core.bean.j();
            jVar.setType("startapp");
            jVar.setActionId(jSONObject.getString("actionid"));
            jVar.setDoActionId(jSONObject.getString("do"));
            if (jSONObject.has("appstartupid")) {
                jVar.a(jSONObject.getJSONObject("appstartupid").getString(com.taobao.dp.client.b.OS));
            }
            if (jSONObject.has("is_autostart")) {
                jVar.d(jSONObject.getString("is_autostart"));
            }
            if (jSONObject.has("appid")) {
                jVar.b(jSONObject.getString("appid"));
            }
            if (!jSONObject.has("noinstall_action")) {
                return jVar;
            }
            jVar.c(jSONObject.getString("noinstall_action"));
            return jVar;
        } catch (JSONException e) {
            return null;
        }
    }

    public boolean b(PushTaskBean pushTaskBean, BaseAction baseAction) {
        Object obj = null;
        if (!(pushTaskBean == null || baseAction == null)) {
            Object obj2;
            com.igexin.push.core.bean.j jVar = (com.igexin.push.core.bean.j) baseAction;
            PackageManager packageManager = g.i.getPackageManager();
            String b = jVar.b();
            if (b.equals("")) {
                b = g.c;
                obj2 = 1;
            } else if (g.c.equals(jVar.b())) {
                int i = 1;
            } else {
                obj2 = null;
            }
            Intent launchIntentForPackage;
            if (obj2 != null) {
                try {
                    f.a().b(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), b, null);
                    if (((com.igexin.push.core.bean.j) baseAction).d().equals("true")) {
                        launchIntentForPackage = packageManager.getLaunchIntentForPackage(g.g);
                        if (launchIntentForPackage == null) {
                            return false;
                        }
                        g.i.startActivity(launchIntentForPackage);
                    }
                    if (jVar.getDoActionId() != null) {
                        f.a().a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), jVar.getDoActionId());
                    }
                } catch (Exception e) {
                }
            } else {
                f.a().b(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), b, null);
                if (!jVar.d().equals("true")) {
                    int i2 = 1;
                } else if (f.a().d(jVar.a())) {
                    launchIntentForPackage = packageManager.getLaunchIntentForPackage(((com.igexin.push.core.bean.j) baseAction).a());
                    if (launchIntentForPackage == null) {
                        return false;
                    }
                    g.i.startActivity(launchIntentForPackage);
                    obj = 1;
                }
                if (obj != null) {
                    if (jVar.getDoActionId() != null) {
                        f.a().a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), jVar.getDoActionId());
                    }
                } else if (jVar.c() != null) {
                    f.a().a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), jVar.c());
                }
            }
        }
        return true;
    }
}
