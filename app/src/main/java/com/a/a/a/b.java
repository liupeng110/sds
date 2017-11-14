package com.a.a.a;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

/* DeviceUuidFactory */
public class b {
    protected static b a;
    protected static UUID b;
    protected String c;

    public static b a() {
        if (a != null) {
            return a;
        }
        b bVar = new b();
        a = bVar;
        return bVar;
    }

    public String a(Context context) {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("device_id.xml", 0);
                    String string = sharedPreferences.getString("device_id", null);
                    if (string != null) {
                        b = UUID.fromString(string);
                    } else {
                        b = UUID.randomUUID();
                        sharedPreferences.edit().putString("device_id", b.toString()).commit();
                    }
                }
            }
        }
        this.c = b.toString().replace("-", "");
        return this.c;
    }
}
