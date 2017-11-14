package com.mradar.sdk.a;

import android.content.Context;
import com.mradar.sdk.record.c;
import com.mradar.sdk.record.f;
import java.util.HashMap;
import java.util.Map;

/* HttpMRadarSdk */
public class b {
    private f a = null;
    private Map<String, String> b = null;
    private String c = "";

    public b(String str) {
        this.c = str;
    }

    public void a(Map<String, String> map) {
        if (map == null) {
            this.b = new HashMap();
        } else {
            this.b = map;
        }
        String str = (String) this.b.get("compress_quality");
        if (str == null || Integer.parseInt(str) > 10) {
            this.b.put("compress_quality", "10");
        }
    }

    public void a(Context context) {
        a(c.a(context));
        d dVar = new d("start");
        dVar.a("appkey", this.c);
        if (this.b != null) {
            for (String str : this.b.keySet()) {
                dVar.a(str, this.b.get(str));
            }
        } else {
            dVar.a("compress_quality", "10");
        }
        if (this.a == null) {
            this.a = new f(context);
        }
        this.a.a(dVar);
        this.a.start();
    }

    public int a(byte[] bArr) {
        d dVar = new d("resume");
        dVar.a("sample", bArr);
        this.a.a(dVar);
        return this.a.b() ? 1 : 0;
    }

    public void a() {
        this.a.a(new d("stop"));
        try {
            this.a.join();
        } catch (Exception e) {
            if (!this.a.b()) {
                this.a.a("[{\"error\":\"client join work thread error.\", \"errorcode\":\"21007\"}]");
            }
        }
    }

    public void b() {
        if (this.a != null) {
            this.a.a(false);
        }
    }

    public String c() {
        if (this.a != null) {
            return this.a.a();
        }
        return "";
    }

    public void d() {
        if (this.a != null) {
            this.a.a(false);
            this.a.c();
            this.a = null;
        }
        f.g = false;
    }
}
