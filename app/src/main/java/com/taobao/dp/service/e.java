package com.taobao.dp.service;

import com.taobao.dp.bean.b;
import com.taobao.dp.http.c;
import org.json.JSONException;
import org.json.JSONObject;

final class e implements IServerListener {
    private /* synthetic */ DevicePrintInitService a;

    e(DevicePrintInitService devicePrintInitService) {
        this.a = devicePrintInitService;
    }

    public final void onResDataReady(b bVar) {
        String string;
        new StringBuilder("DevicePrintInitService decryptUUIDFromServer ResCode=").append(bVar.a());
        if (bVar.a() == 200) {
            try {
                string = new JSONObject(bVar.c()).getString("uuid");
            } catch (JSONException e) {
                new StringBuilder("DevicePrintInitService decryptUUIDFromServer decypt uuid ").append(e);
                string = null;
            }
            if (string != null) {
                this.a.a(c.b, string);
                this.a.d.a(string, this.a.e, this.a.f, this.a.h);
                this.a.i.onInitFinished(this.a, bVar.a());
            }
        } else {
            string = null;
        }
        if (string == null) {
            this.a.f();
        }
    }
}
