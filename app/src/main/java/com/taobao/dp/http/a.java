package com.taobao.dp.http;

import android.content.Context;
import com.igexin.sdk.PushConsts;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.taobao.dp.a.d;
import com.taobao.dp.bean.ReqData;
import com.taobao.dp.bean.ServiceData;
import com.taobao.dp.bean.b;
import com.taobao.dp.service.IServerListener;
import com.taobao.dp.util.e;
import com.taobao.wireless.security.sdk.indiekit.IndieKitDefine;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class a {
    private IServerListener a;
    private IUrlRequestService b;
    private IResponseReceiver c = new b(this);
    private Context d;
    private String e;
    private String f;
    private com.taobao.dp.client.a g;

    public a(Context context, String str, String str2, com.taobao.dp.client.a aVar, IUrlRequestService iUrlRequestService, IServerListener iServerListener) {
        this.d = context;
        this.e = str;
        this.f = str2;
        this.b = iUrlRequestService;
        this.a = iServerListener;
        this.g = aVar;
    }

    private b a(int i, byte[] bArr) {
        b bVar = new b();
        if (bArr == null) {
            bVar.a(i);
        } else if (i == 200) {
            try {
                String str = new String(bArr, "UTF-8");
                new StringBuilder("NetAction httpRes Entity:").append(str).append(" ");
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("payload");
                str = d.c(jSONObject.getString("service") + jSONObject.getString("version") + jSONObject.getString("app") + string + jSONObject.getLong(IndieKitDefine.SG_KEY_INDIE_KIT_TIMESTAMP), this.e, this.f, this.d);
                String string2 = jSONObject.getString("signature");
                if (string2 == null || !string2.equals(str)) {
                    int i2 = 1001;
                    try {
                        i2 = new JSONObject(string).getInt("code");
                    } catch (Exception e) {
                    }
                    bVar.a(i2);
                } else {
                    str = d.b(string, this.e, this.f, this.d);
                    if (str != null) {
                        jSONObject = new JSONObject(str);
                        bVar.a(jSONObject.getInt("code"));
                        bVar.b(jSONObject.getString("data"));
                        bVar.a(jSONObject.getString("message"));
                    } else {
                        bVar.a((int) PushConsts.THIRDPART_FEEDBACK);
                    }
                }
            } catch (Exception e2) {
                new StringBuilder("NetAction httpResponse2ResData").append(e2);
                bVar.a(1002);
            }
        } else {
            bVar.a(i);
        }
        return bVar;
    }

    private String a(ReqData reqData) {
        String str = null;
        try {
            Object obj;
            String a = com.taobao.dp.util.d.a(e.a((Object) reqData));
            Object serviceData = new ServiceData();
            serviceData.setApp(this.e);
            serviceData.setVersion(com.taobao.dp.client.b.PROTOCAL_VERSION);
            serviceData.setService(com.taobao.dp.client.b.SERVICE);
            serviceData.setOs(com.taobao.dp.client.b.OS);
            serviceData.setTimestamp(System.currentTimeMillis());
            a = d.a(a, this.e, this.f, this.d);
            serviceData.setPayload(a);
            a = d.c(serviceData.getService() + serviceData.getVersion() + serviceData.getApp() + serviceData.getOs() + a + serviceData.getTimestamp(), this.e, this.f, this.d);
            if (a == null || a.length() == 0) {
                obj = null;
            } else {
                serviceData.setSignature(a);
                obj = com.taobao.dp.util.d.a(e.a(serviceData));
            }
            if (obj != null) {
                String b = this.g.b();
                Map hashMap = new HashMap();
                hashMap.put("c", obj);
                str = com.taobao.dp.http.DefaultUrlRequestService.a.a(b, hashMap);
            }
        } catch (Exception e) {
        }
        return str;
    }

    public final void a(String str, JSONObject jSONObject) {
        try {
            ReqData reqData = new ReqData();
            reqData.setAction(str);
            reqData.setData(jSONObject.toString());
            new StringBuilder("NetAction sendRequest reqData").append(reqData.getAction()).append(" : ").append(reqData.getData()).append(" : ").append(reqData.getDid());
            b bVar = new b();
            String a = a(reqData);
            if (a == null) {
                bVar.a(1003);
                this.a.onResDataReady(bVar);
            } else if (this.b != null) {
                this.b.sendRequest(this.g.a(), a, this.c);
            } else {
                bVar.a((int) VersionUpdateConst.UPDATE_TENCENT_TYPE);
                this.a.onResDataReady(bVar);
            }
        } catch (Exception e) {
            b bVar2 = new b();
            bVar2.a(1002);
            this.a.onResDataReady(bVar2);
        }
    }
}
