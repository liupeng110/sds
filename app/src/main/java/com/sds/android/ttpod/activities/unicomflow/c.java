package com.sds.android.ttpod.activities.unicomflow;

import android.content.Context;
import android.os.Build.VERSION;
import com.igexin.download.Downloads;
import com.sds.android.sdk.lib.util.g;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.http.HttpHost;

/* WebViewProxy */
public class c {
    private static final String a = c.class.getSimpleName();
    private static boolean b = false;

    private static Object a(Object obj, String str) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static Object a(Context context) throws Exception {
        Class cls = Class.forName("android.webkit.Network");
        if (cls == null) {
            return null;
        }
        Object a = a(cls, "getInstance", new Object[]{context}, Context.class);
        if (a != null) {
            return a(a, "mRequestQueue");
        }
        return null;
    }

    public static boolean a() {
        return false;
    }

    private static Object a(Object obj, String str, Object[] objArr, Class... clsArr) throws Exception {
        Class cls = obj instanceof Class ? (Class) obj : obj.getClass();
        if (clsArr != null) {
            return cls.getMethod(str, clsArr).invoke(obj, objArr);
        }
        return cls.getMethod(str, new Class[0]).invoke(obj, new Object[0]);
    }

    public static void b(Context context) {
        b = false;
        try {
            Object a = a(context);
            if (a != null) {
                a(a, "mProxyHost", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void a(Object obj, String str, Object obj2) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }

    public static boolean a(Context context, String str, int i) {
        boolean z = false;
        try {
            if (VERSION.SDK_INT < 14) {
                Object a = a(context);
                if (a != null) {
                    a(a, "mProxyHost", new HttpHost(str, i, "http"));
                    z = true;
                }
            } else {
                z = a(str, i);
            }
        } catch (Throwable e) {
            g.b(a, "error setting up webkit proxying", e);
        }
        b = z;
        return z;
    }

    private static boolean a(String str, int i) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class cls = Class.forName("android.webkit.WebViewCore");
        Class cls2 = Class.forName("android.net.ProxyProperties");
        if (cls == null || cls2 == null) {
            return false;
        }
        Method declaredMethod = cls.getDeclaredMethod("sendStaticMessage", new Class[]{Integer.TYPE, Object.class});
        Constructor constructor = cls2.getConstructor(new Class[]{String.class, Integer.TYPE, String.class});
        declaredMethod.setAccessible(true);
        constructor.setAccessible(true);
        Object newInstance = constructor.newInstance(new Object[]{str, Integer.valueOf(i), null});
        declaredMethod.invoke(null, new Object[]{Integer.valueOf(Downloads.STATUS_RUNNING_PAUSED), newInstance});
        return true;
    }
}
