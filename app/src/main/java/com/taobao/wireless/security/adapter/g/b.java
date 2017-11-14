package com.taobao.wireless.security.adapter.g;

import android.content.Context;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.a.a;
import java.util.HashSet;

public final class b implements a {
    private JNICLibrary a = JNICLibrary.getInstance();
    private Context b;

    public b(Context context) {
        this.b = context;
    }

    public final int a(String... strArr) throws SecException {
        HashSet hashSet = new HashSet();
        if (strArr != null) {
            for (Object obj : strArr) {
                if (!a.a(obj)) {
                    hashSet.add(strArr[r0]);
                }
            }
        }
        String[] strArr2 = new String[hashSet.size()];
        hashSet.toArray(strArr2);
        return this.a.checkEnvAndFilesNative(strArr2, strArr2.length, this.b);
    }

    public final String a(String str, String str2, int i) {
        if (a.a(str, str2)) {
            return null;
        }
        try {
            byte[] dexHashNative = this.a.getDexHashNative(str, str2, i);
            return dexHashNative != null ? new String(dexHashNative, "UTF-8") : null;
        } catch (Exception e) {
            return null;
        }
    }

    public final boolean a(String str) {
        boolean z = false;
        if (!a.a(str)) {
            try {
                z = this.a.isPackageValidNative(str);
            } catch (Exception e) {
            }
        }
        return z;
    }
}
