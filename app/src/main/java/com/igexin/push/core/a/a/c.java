package com.igexin.push.core.a.a;

import android.os.Process;
import com.igexin.push.a.a;
import com.igexin.push.a.k;
import com.igexin.push.core.a.f;
import com.igexin.push.core.b;
import com.igexin.push.core.bean.BaseAction;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.bean.e;
import com.igexin.push.core.g;
import java.io.File;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c implements a {
    private boolean a(e eVar) {
        String c = eVar.c();
        if (c != null) {
            File file = new File(g.ad + "/" + c);
            if (file.exists()) {
                return file.delete();
            }
        }
        return false;
    }

    public b a(PushTaskBean pushTaskBean, BaseAction baseAction) {
        return b.success;
    }

    public BaseAction a(JSONObject jSONObject) {
        if (jSONObject.has("ids")) {
            try {
                JSONArray jSONArray = new JSONArray(jSONObject.getString("ids"));
                if (jSONArray != null && jSONArray.length() > 0) {
                    int[] iArr = new int[jSONArray.length()];
                    for (int i = 0; i < jSONArray.length(); i++) {
                        iArr[i] = jSONArray.getInt(i);
                    }
                    BaseAction cVar = new com.igexin.push.core.bean.c();
                    cVar.setType("cleanext");
                    cVar.a(iArr);
                    cVar.setActionId(jSONObject.getString("actionid"));
                    cVar.setDoActionId(jSONObject.getString("do"));
                    return cVar;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean b(PushTaskBean pushTaskBean, BaseAction baseAction) {
        boolean z = false;
        if (k.x == null || k.x.b() == null || k.x.b().size() == 0) {
            return false;
        }
        if (!(pushTaskBean == null || baseAction == null)) {
            boolean z2;
            com.igexin.push.core.bean.c cVar = (com.igexin.push.core.bean.c) baseAction;
            Map b = k.x.b();
            int[] a = cVar.a();
            if (a == null || a.length <= 0) {
                z2 = false;
            } else {
                int i = 0;
                z2 = false;
                while (i < cVar.a().length) {
                    boolean z3;
                    if (b.containsKey(Integer.valueOf(a[i]))) {
                        a((e) b.get(Integer.valueOf(a[i])));
                        b.remove(Integer.valueOf(a[i]));
                        z2 = true;
                        z3 = true;
                    } else {
                        z3 = z2;
                        z2 = z;
                    }
                    i++;
                    z = z2;
                    z2 = z3;
                }
                if (z) {
                    a.a().g();
                }
            }
            if (z2) {
                Process.killProcess(Process.myPid());
            }
        }
        if (baseAction.getDoActionId().equals("")) {
            return true;
        }
        f.a().a(pushTaskBean.getTaskId(), pushTaskBean.getMessageId(), baseAction.getDoActionId());
        return true;
    }
}
