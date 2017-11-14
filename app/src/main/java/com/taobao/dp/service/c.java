package com.taobao.dp.service;

import com.taobao.dp.bean.b;
import org.json.JSONException;
import org.json.JSONObject;

final class c implements IServerListener {
    private /* synthetic */ DevicePrintInitService a;

    c(DevicePrintInitService devicePrintInitService) {
        this.a = devicePrintInitService;
    }

    public final void onResDataReady(b bVar) {
        new StringBuilder("DevicePrintInitService initUuidFromServer data.getCode()=").append(bVar.a());
        new StringBuilder("DevicePrintInitService initUuidFromServer success").append(bVar.a() == 200);
        if (bVar.a() == 200) {
            try {
                String string = new JSONObject(bVar.c()).getString("uuid");
                this.a.a(com.taobao.dp.http.c.a, string);
                this.a.d.a(string, this.a.e, this.a.f, this.a.h);
            } catch (JSONException e) {
                new StringBuilder("DevicePrintInitService initUuidFromServer JSONException:").append(e);
            }
        }
        this.a.i.onInitFinished(this.a, bVar.a());
    }
}
