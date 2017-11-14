package com.tencent.a.b;

import android.content.Context;
import java.io.File;

/* ProGuard */
public final class a {
    private static Context a;

    public static final Context a() {
        if (a == null) {
            return null;
        }
        return a;
    }

    public static final void a(Context context) {
        a = context;
    }

    public static final String b() {
        return a().getPackageName();
    }

    public static final File c() {
        return a().getFilesDir();
    }
}
