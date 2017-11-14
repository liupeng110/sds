package com.sds.android.sdk.lib.util;

/* ReflectUtils */
public class i {
    /* JADX err: Inconsistent code. */
    public static java.lang.reflect.Method a(java.lang.Class r6, java.lang.String r7, java.lang.Class<?>... r8) throws java.lang.NoSuchMethodException {
        /*
        r0 = "cls";
        com.sds.android.sdk.lib.util.d.a(r6, r0);
        r0 = "methodName";
        com.sds.android.sdk.lib.util.d.a(r7, r0);
        r0 = r6.getMethod(r7, r8);	 Catch:{ Exception -> 0x000f }
    L_0x000e:
        return r0;
    L_0x000f:
        r0 = move-exception;
        r0 = r6.getDeclaredMethod(r7, r8);	 Catch:{ Exception -> 0x0015 }
        goto L_0x000e;
    L_0x0015:
        r0 = move-exception;
        r0 = r6;
    L_0x0017:
        r1 = r0.getMethods();	 Catch:{ Exception -> 0x0039 }
        r3 = r1;
    L_0x001c:
        r4 = r3.length;	 Catch:{ Exception -> 0x0044 }
        r1 = 0;
        r2 = r1;
    L_0x001f:
        if (r2 >= r4) goto L_0x0048;
    L_0x0021:
        r1 = r3[r2];	 Catch:{ Exception -> 0x0044 }
        r5 = r1.getName();	 Catch:{ Exception -> 0x0044 }
        r5 = r5.equals(r7);	 Catch:{ Exception -> 0x0044 }
        if (r5 == 0) goto L_0x0040;
    L_0x002d:
        r5 = r1.getParameterTypes();	 Catch:{ Exception -> 0x0044 }
        r5 = a(r8, r5);	 Catch:{ Exception -> 0x0044 }
        if (r5 == 0) goto L_0x0040;
    L_0x0037:
        r0 = r1;
        goto L_0x000e;
    L_0x0039:
        r1 = move-exception;
        r1 = r0.getDeclaredMethods();	 Catch:{ Exception -> 0x0044 }
        r3 = r1;
        goto L_0x001c;
    L_0x0040:
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x001f;
    L_0x0044:
        r1 = move-exception;
        r1.printStackTrace();
    L_0x0048:
        r0 = r0.getSuperclass();
        if (r0 != 0) goto L_0x0017;
    L_0x004e:
        r0 = new java.lang.NoSuchMethodException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r1 = r1.append(r7);
        r2 = " not found in class ";
        r1 = r1.append(r2);
        r2 = r6.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sds.android.sdk.lib.util.i.a(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    private static boolean a(Class[] clsArr, Class[] clsArr2) {
        if (clsArr == null && clsArr2 == null) {
            return true;
        }
        if (clsArr == null || clsArr2 == null || clsArr.length != clsArr2.length) {
            return false;
        }
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            if (!clsArr[i].getName().equals(clsArr2[i].getName())) {
                return false;
            }
        }
        return true;
    }
}
