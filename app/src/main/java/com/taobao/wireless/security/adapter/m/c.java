package com.taobao.wireless.security.adapter.m;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.alibaba.wireless.security.SecExceptionCode;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.adapter.JNICLibrary;
import com.taobao.wireless.security.adapter.a.f;

public final class c implements b {
    private static Context a;
    private static JNICLibrary b;

    public c(Context context) {
        a = context;
        b = JNICLibrary.getInstance();
    }

    public static String c(String str) {
        if (a == null || str == null || str.length() <= 0) {
            return null;
        }
        String a = f.a(a, str, "StaticKey");
        if (a == null || a.length() <= 0 || b == null) {
            return a;
        }
        byte[] seed = b.getSeed(a.getBytes());
        return (seed == null || seed.length <= 0) ? a : a.a(seed);
    }

    public final int a(String str, byte[] bArr) throws SecException {
        if (str == null || str.length() <= 0 || bArr == null || bArr.length <= 0) {
            throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_INVALID_PARAM);
        }
        byte[] saveKeyEncrypt = b.saveKeyEncrypt(str.getBytes(), bArr);
        if (saveKeyEncrypt != null && saveKeyEncrypt.length > 0) {
            boolean z;
            String a = f.a(a, str, "StaticKey");
            Context context = a;
            String str2 = new String(saveKeyEncrypt);
            String str3 = "StaticKey";
            if (context != null && str != null && str.length() > 0 && str2.length() > 0) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(str3, 0);
                if (sharedPreferences != null) {
                    Editor putString = sharedPreferences.edit().putString(str, str2);
                    if (VERSION.SDK_INT >= 9) {
                        putString.apply();
                        z = true;
                    } else {
                        z = putString.commit();
                    }
                    if (z) {
                        return (a == null || a.length() <= 0) ? 1 : 2;
                    }
                }
            }
            z = false;
            if (z) {
                if (a == null) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public final boolean a(String str) {
        String a = f.a(a, str, "StaticKey");
        return a != null && a.length() > 0;
    }

    public final byte[] a(int i, String str, String str2) throws SecException {
        String a = f.a(a, str, "StaticKey");
        String a2 = f.a(a, str2, "StaticKey");
        if (a == null || a.length() == 0) {
            throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_KEY);
        } else if (a2 == null || a2.length() == 0) {
            throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_KEY);
        } else {
            byte[] encryptSecretData = b.encryptSecretData(i, a.getBytes(), a2.getBytes());
            return (encryptSecretData == null || encryptSecretData.length <= 0) ? encryptSecretData : a.a(new String(encryptSecretData));
        }
    }

    public final byte[] a(int i, String str, byte[] bArr) throws SecException {
        if (b == null) {
            return null;
        }
        String a = f.a(a, str, "StaticKey");
        if (a == null || a.length() <= 0) {
            throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_KEY);
        }
        byte[] seedEncrypt = b.seedEncrypt(i, a.getBytes(), bArr);
        return (seedEncrypt == null || seedEncrypt.length <= 0) ? seedEncrypt : a.a(new String(seedEncrypt));
    }

    public final int b(String str) {
        if (f.a(a, str, "StaticKey") == null) {
            return 2;
        }
        boolean z;
        Context context = a;
        String str2 = "StaticKey";
        if (!(context == null || str == null || str.length() <= 0)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(str2, 0);
            if (sharedPreferences != null) {
                Editor edit = sharedPreferences.edit();
                if (edit != null) {
                    edit = edit.remove(str);
                    if (VERSION.SDK_INT >= 9) {
                        edit.apply();
                        z = true;
                    } else {
                        z = edit.commit();
                    }
                    return z ? 0 : 1;
                }
            }
        }
        z = false;
        if (z) {
        }
    }

    public final byte[] b(int i, String str, byte[] bArr) throws SecException {
        if (b == null) {
            return null;
        }
        String a = f.a(a, str, "StaticKey");
        if (a == null || a.length() <= 0) {
            throw new SecException(SecExceptionCode.SEC_ERROR_STA_KEY_ENC_NO_KEY);
        }
        return b.seedDecrypt(i, a.getBytes(), a.a(bArr).getBytes());
    }
}
