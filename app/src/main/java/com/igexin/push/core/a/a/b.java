package com.igexin.push.core.a.a;

import android.content.pm.PackageInfo;
import com.igexin.push.core.a.f;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.g;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class b implements a {
    public com.igexin.push.core.b a(PushTaskBean pushTaskBean, BaseAction baseAction) {
        return com.igexin.push.core.b.success;
    }

    public BaseAction a(JSONObject jSONObject) {
        try {
            if (jSONObject.has(SocialConstants.PARAM_TYPE) && jSONObject.has("actionid")) {
                BaseAction bVar = new com.igexin.push.core.bean.b();
                bVar.setType("checkapp");
                bVar.setActionId(jSONObject.getString("actionid"));
                if (jSONObject.has("appstartupid")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("appstartupid");
                    if (jSONObject2.has(com.taobao.dp.client.b.OS)) {
                        bVar.a(jSONObject2.getString(com.taobao.dp.client.b.OS));
                        if (jSONObject.has("do_installed") || jSONObject.has("do_uninstalled")) {
                            if (jSONObject.has("do_installed")) {
                                bVar.b(jSONObject.getString("do_installed"));
                            }
                            if (!jSONObject.has("do_uninstalled")) {
                                return bVar;
                            }
                            bVar.c(jSONObject.getString("do_uninstalled"));
                            return bVar;
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public boolean a(String str) {
        for (PackageInfo packageInfo : g.i.getPackageManager().getInstalledPackages(4096)) {
            if (packageInfo.packageName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean b(PushTaskBean pushTaskBean, BaseAction baseAction) {
        com.igexin.push.core.bean.b bVar = (com.igexin.push.core.bean.b) baseAction;
        String taskId = pushTaskBean.getTaskId();
        String messageId = pushTaskBean.getMessageId();
        if (a(bVar.a())) {
            if (!(bVar.b() == null || bVar.b().equals(""))) {
                f.a().a(taskId, messageId, bVar.b());
            }
        } else if (!(bVar.c() == null || bVar.c().equals(""))) {
            f.a().a(taskId, messageId, bVar.c());
        }
        return true;
    }
}
