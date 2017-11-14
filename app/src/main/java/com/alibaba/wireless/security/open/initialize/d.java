package com.alibaba.wireless.security.open.initialize;

import android.content.Context;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class d {
    private static d b;
    private String a;
    private ArrayList c = new ArrayList();

    private d() {
        this.c.add("libsecuritysdk-2.3.39.so");
    }

    public static d a() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    private String a(String str) {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader == null) {
            return null;
        }
        try {
            String str2;
            Method method = classLoader.getClass().getMethod("findLibrary", new Class[]{String.class});
            if (method != null) {
                Object invoke = method.invoke(classLoader, new Object[]{str});
                if (invoke != null && (invoke instanceof String)) {
                    str2 = (String) invoke;
                    return str2;
                }
            }
            str2 = null;
            return str2;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (IllegalArgumentException e3) {
            return null;
        } catch (InvocationTargetException e4) {
            return null;
        } catch (Exception e5) {
            return null;
        }
    }

    public final int a(Context context, String str) {
        int i = 1;
        synchronized (this) {
            if (str != null) {
                if (str.length() != 0) {
                    File file = new File(str);
                    if (!file.exists()) {
                        i = 3;
                    } else if (!file.canRead()) {
                        i = 4;
                    } else if (this.c.contains(file.getName() + "x")) {
                        i = 5;
                    } else if (new Bitmap().loadBitmap(context, str, true)) {
                        this.c.add(file.getName() + "x");
                    } else {
                        i = 6;
                    }
                }
            }
            i = 2;
        }
        return i;
    }

    public final String b() {
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    String a = a("securitysdk-2.3.39");
                    if (a != null) {
                        this.a = a;
                    }
                }
            }
        }
        return this.a;
    }
}
