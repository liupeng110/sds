package com.tencent.map.a.a;

import android.content.Context;
import com.tencent.map.b.n;

public class a {
    private static n a = n.a();
    private static a b;

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                b = new a();
            }
            aVar = b;
        }
        return aVar;
    }

    public boolean a(Context context, b bVar) {
        return a.a(context, bVar);
    }

    public boolean a(String str, String str2) {
        return a.a(str, str2);
    }

    public void b() {
        a.b();
    }
}
