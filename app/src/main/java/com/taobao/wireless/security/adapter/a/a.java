package com.taobao.wireless.security.adapter.a;

public final class a {
    public static boolean a(String... strArr) {
        for (Object obj : strArr) {
            if (obj == null || "".equals(obj)) {
                return true;
            }
        }
        return false;
    }
}
