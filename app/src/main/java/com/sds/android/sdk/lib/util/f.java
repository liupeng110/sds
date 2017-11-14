package com.sds.android.sdk.lib.util;

import com.b.a.g;
import com.b.a.t;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.io.InputStream;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* JSONUtils */
public class f {
    private static com.b.a.f a = new g().a();

    public static com.b.a.f a() {
        return a;
    }

    public static <T> T a(String str, Class<T> cls) {
        try {
            return a().a(str, (Class) cls);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static <T> T a(String str, Type type) {
        try {
            return a().a(str, type);
        } catch (t e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Object obj) {
        try {
            return a().a(obj);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static JSONObject a(InputStream inputStream) {
        try {
            return new JSONObject(m.a(inputStream));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String b(Object obj) {
        String a = m.a(SelectCountryActivity.SPLITTER, obj);
        return new StringBuilder(a.length() + 2).append('[').append(a).append(']').toString();
    }
}
