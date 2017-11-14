package com.sds.android.sdk.lib.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/* StringUtils */
public class m {
    public static String a(String str, Collection<?> collection) {
        StringBuilder stringBuilder = new StringBuilder();
        if (collection != null) {
            a(stringBuilder, str, (Collection) collection);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(str)) {
            return stringBuilder2.substring(0, stringBuilder2.length() - str.length());
        }
        return stringBuilder2;
    }

    public static String a(String str, Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (objArr != null) {
            if (r3 > 0) {
                for (Object b : objArr) {
                    b(stringBuilder, str, b);
                }
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(str)) {
            return stringBuilder2.substring(0, stringBuilder2.length() - str.length());
        }
        return stringBuilder2;
    }

    private static void a(StringBuilder stringBuilder, String str, Object obj) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            b(stringBuilder, str, Array.get(obj, i));
        }
    }

    private static void a(StringBuilder stringBuilder, String str, Collection<?> collection) {
        for (Object b : collection) {
            b(stringBuilder, str, b);
        }
    }

    private static void b(StringBuilder stringBuilder, String str, Object obj) {
        if (obj != null) {
            if (obj.getClass().isArray()) {
                a(stringBuilder, str, obj);
            } else if (obj instanceof Collection) {
                a(stringBuilder, str, (Collection) obj);
            } else {
                String obj2 = obj.toString();
                stringBuilder.append(obj2);
                if (!a(obj2) && !obj2.endsWith(str)) {
                    stringBuilder.append(str);
                }
            }
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean a(String str, String str2) {
        return str == str2 || (str != null && str2 != null && str.length() == str2.length() && str.equals(str2));
    }

    public static List<Long> b(String str, String str2) {
        List<String> c = c(str, str2);
        List<Long> arrayList = new ArrayList(c.size());
        for (String parseLong : c) {
            arrayList.add(Long.valueOf(Long.parseLong(parseLong)));
        }
        return arrayList;
    }

    public static List<String> c(String str, String str2) {
        List<String> arrayList = new ArrayList();
        if (!a(str)) {
            int length = str.length();
            int i = 0;
            do {
                int indexOf = str.indexOf(str2, i);
                if (indexOf == -1) {
                    indexOf = length;
                }
                arrayList.add(str.substring(i, indexOf));
                i = indexOf + 1;
            } while (i < length);
        }
        return arrayList;
    }

    /* JADX err: Inconsistent code. */
    public static java.lang.String a(java.io.InputStream r4) {
        /*
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x0021, OutOfMemoryError -> 0x0034 }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ Exception -> 0x0021, OutOfMemoryError -> 0x0034 }
        r1.<init>();	 Catch:{ Exception -> 0x0021, OutOfMemoryError -> 0x0034 }
    L_0x0009:
        r2 = 0;
        r3 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r2 = r4.read(r0, r2, r3);	 Catch:{ Exception -> 0x0021, OutOfMemoryError -> 0x0034 }
        if (r2 > 0) goto L_0x001c;
    L_0x0012:
        r0 = "UTF-8";
        r0 = r1.toString(r0);	 Catch:{ Exception -> 0x0021, OutOfMemoryError -> 0x0034 }
        r4.close();	 Catch:{ Exception -> 0x002a }
    L_0x001b:
        return r0;
    L_0x001c:
        r3 = 0;
        r1.write(r0, r3, r2);	 Catch:{ Exception -> 0x0021, OutOfMemoryError -> 0x0034 }
        goto L_0x0009;
    L_0x0021:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0041 }
        r4.close();	 Catch:{ Exception -> 0x002f }
    L_0x0028:
        r0 = 0;
        goto L_0x001b;
    L_0x002a:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001b;
    L_0x002f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0028;
    L_0x0034:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0041 }
        r4.close();	 Catch:{ Exception -> 0x003c }
        goto L_0x0028;
    L_0x003c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0028;
    L_0x0041:
        r0 = move-exception;
        r4.close();	 Catch:{ Exception -> 0x0046 }
    L_0x0045:
        throw r0;
    L_0x0046:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.sdk.lib.util.m.a(java.io.InputStream):java.lang.String");
    }

    public static boolean b(String str) {
        if (a(str)) {
            return false;
        }
        return Pattern.matches("^([a-zA-Z0-9_\\.-]+)@([\\da-zA-Z\\.-]+)\\.([a-zA-Z\\.]{1,16})$", str);
    }

    public static boolean a(String str, int i, int i2) {
        return str != null && str.length() >= i && str.length() <= i2;
    }

    public static String c(String str) {
        return a(str) ? str : str.replaceAll("[\\\\/:\"*?<>|]+", "_");
    }
}
