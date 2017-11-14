package com.taobao.dp.service;

import com.taobao.dp.bean.b;
import com.taobao.dp.http.c;
import org.json.JSONObject;

final class d implements IServerListener {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ DevicePrintInitService c;

    d(DevicePrintInitService devicePrintInitService, String str, String str2) {
        this.c = devicePrintInitService;
        this.a = str;
        this.b = str2;
    }

    public final void onResDataReady(b bVar) {
        new StringBuilder("DevicePrintInitService updateDeviceInfoOnServer data.getCode()=").append(bVar.a());
        new StringBuilder("DevicePrintInitService updateDeviceInfoOnServer UPDATE=").append(bVar.a() == 200);
        if (bVar.a() == 200) {
            this.c.d.a(this.a, this.c.e, this.c.f);
            try {
                JSONObject jSONObject = new JSONObject(bVar.c());
                new StringBuilder("DevicePrintInitService updateDeviceInfoOnServer retjson=").append(jSONObject);
                this.c.a(c.c, jSONObject.getString("uuid"));
                this.c.d.a(this.b, this.c.e, this.c.f, this.c.h);
            } catch (Exception e) {
                new StringBuilder("DevicePrintInitService updateDeviceInfoOnServer Exception=").append(e.toString());
            }
        }
    }
}
