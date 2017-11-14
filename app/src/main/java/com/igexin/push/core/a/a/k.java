package com.igexin.push.core.a.a;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import com.igexin.push.core.a;
import com.igexin.push.core.a.f;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.m;
import com.igexin.push.core.g;
import com.igexin.sdk.PushConsts;
import com.tencent.open.SocialConstants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class k implements a {
    private String a(String str) {
        List<PackageInfo> installedPackages = g.i.getPackageManager().getInstalledPackages(4);
        if (installedPackages != null) {
            for (PackageInfo packageInfo : installedPackages) {
                if (str.equals(packageInfo.packageName)) {
                    for (ServiceInfo serviceInfo : packageInfo.services) {
                        if (a.n.equals(serviceInfo.name) || a.o.equals(serviceInfo.name) || a.p.equals(serviceInfo.name)) {
                            return serviceInfo.name;
                        }
                    }
                    continue;
                }
            }
        }
        return null;
    }

    public b a(PushTaskBean pushTaskBean, BaseAction baseAction) {
        return b.success;
    }

    public BaseAction a(JSONObject jSONObject) {
        try {
            if (com.igexin.push.a.k.v && jSONObject.has("do") && jSONObject.has("actionid") && jSONObject.has(SocialConstants.PARAM_TYPE) && jSONObject.has("pkgname")) {
                BaseAction mVar = new m();
                mVar.setType("wakeupsdk");
                mVar.setActionId(jSONObject.getString("actionid"));
                mVar.setDoActionId(jSONObject.getString("do"));
                mVar.a(jSONObject.getString("pkgname"));
                if (!jSONObject.has("is_forcestart")) {
                    return mVar;
                }
                mVar.a(jSONObject.getBoolean("is_forcestart"));
                return mVar;
            }
        } catch (JSONException e) {
        }
        return null;
    }

    public boolean b(PushTaskBean pushTaskBean, BaseAction baseAction) {
        if (!(pushTaskBean == null || baseAction == null)) {
            m mVar = (m) baseAction;
            String b = mVar.b();
            String a = a(b);
            if (a != null) {
                Intent intent;
                if (a.equals(a.n)) {
                    if (mVar.a()) {
                        intent = new Intent();
                        intent.setClassName(b, a);
                        intent.putExtra(PushConsts.CMD_ACTION, PushConsts.ACTION_SERVICE_INITIALIZE_SLAVE);
                        intent.putExtra("op_app", g.g);
                        intent.putExtra("isSlave", true);
                        g.i.startService(intent);
                    } else {
                        intent = new Intent();
                        intent.setClassName(b, a);
                        g.i.startService(intent);
                    }
                } else if (a.equals(a.o) || a.equals(a.p)) {
                    intent = new Intent();
                    intent.setClassName(b, a);
                    g.i.startService(intent);
                }
            }
            if (!baseAction.getDoActionId().equals("")) {
                f.a().a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), baseAction.getDoActionId());
            }
        }
        return true;
    }
}
