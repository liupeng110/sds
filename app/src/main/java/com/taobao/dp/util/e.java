package com.taobao.dp.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class e {
    private static String a(Date date) {
        String str = null;
        if (date != null) {
            try {
                str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static Map a(Object obj) {
        Class cls = obj.getClass();
        Map hashMap = new HashMap();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Field field : cls.getDeclaredFields()) {
            try {
                String simpleName = field.getType().getSimpleName();
                String name = field.getName();
                name = (name == null || "".equals(name)) ? null : "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                if (a(declaredMethods, name)) {
                    Object invoke = cls.getMethod(name, new Class[0]).invoke(obj, new Object[0]);
                    if ("Date".equals(simpleName)) {
                        invoke = a((Date) invoke);
                    } else if (invoke != null) {
                        invoke = String.valueOf(invoke);
                    }
                    hashMap.put(field.getName(), invoke);
                }
            } catch (Exception e) {
            }
        }
        return hashMap;
    }

    private static boolean a(Method[] methodArr, String str) {
        for (Method name : methodArr) {
            if (str.equals(name.getName())) {
                return true;
            }
        }
        return false;
    }
}
