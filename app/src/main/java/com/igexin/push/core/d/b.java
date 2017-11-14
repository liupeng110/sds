package com.igexin.push.core.d;

import com.igexin.push.c.c.a;
import com.igexin.push.c.c.c;
import com.igexin.push.c.c.e;
import com.igexin.push.core.bean.PushTaskBean;
import com.igexin.push.core.f;
import com.igexin.push.core.g;
import com.igexin.sdk.PushConsts;
import com.sds.android.cloudapi.ttpod.data.StarCategory;
import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends com.igexin.push.e.a.b {
    private String a;
    private String b;
    private a c;
    private PushTaskBean d;

    public b(String str, a aVar, PushTaskBean pushTaskBean) {
        super(str);
        this.b = str;
        this.a = pushTaskBean.getMessageId();
        this.c = aVar;
        this.d = pushTaskBean;
    }

    protected void a(PushTaskBean pushTaskBean, a aVar) {
        e cVar = new c();
        cVar.a();
        cVar.c = "RTV" + pushTaskBean.getMessageId() + "@" + pushTaskBean.getTaskId();
        cVar.d = g.u;
        cVar.a = (int) System.currentTimeMillis();
        f.a().e().a("C-" + g.u, cVar);
        com.igexin.a.a.c.a.a("cdnRetrieve|" + pushTaskBean.getMessageId() + "|" + pushTaskBean.getTaskId());
        if (aVar.c() < 2) {
            long k = com.igexin.push.core.a.f.a().k();
            Timer timer = new Timer();
            timer.schedule(new d(this, pushTaskBean, aVar), k);
            g.an.put(pushTaskBean.getTaskId(), timer);
        }
    }

    public void a(Exception exception) {
        if (this.c.a() < 2) {
            new Timer().schedule(new c(this), com.igexin.push.core.a.f.a().k());
            return;
        }
        a(this.d, this.c);
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            byte[] b = com.igexin.a.a.b.g.b(com.igexin.a.a.a.a.a(bArr, g.e));
            if (b != null) {
                JSONObject jSONObject = new JSONObject(new String(b, TTPodFMHttpClient.AppEncode));
                jSONObject.put(StarCategory.KEY_STAR_CATEGORY_ID, this.a);
                jSONObject.put("messageid", this.a);
                jSONObject.put("cdnType", true);
                b = null;
                try {
                    if ("pushmessage".equals(jSONObject.getString(PushConsts.CMD_ACTION))) {
                        if (jSONObject.has("extraData")) {
                            b = jSONObject.getString("extraData").getBytes();
                        }
                        com.igexin.push.core.a.f.a().a(jSONObject, b, true);
                        return;
                    }
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            throw new Exception("Get error CDNData, can not UnGzip it...");
        }
    }

    public int b() {
        return 0;
    }
}
