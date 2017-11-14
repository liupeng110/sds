package com.taobao.wireless.security.sdk.staticdataencrypt;

import android.content.ContextWrapper;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.SecurityGuardManager;

public final class a implements IStaticDataEncryptComponent {
    private ContextWrapper a;

    public a(ContextWrapper contextWrapper) {
        this.a = contextWrapper;
    }

    public final byte[] staticBinarySafeDecrypt(int i, String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                bArr2 = instance.getStaticDataEncryptComp().staticBinarySafeDecrypt(i, str, bArr, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return bArr2;
    }

    public final byte[] staticBinarySafeEncrypt(int i, String str, byte[] bArr) {
        byte[] bArr2 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                bArr2 = instance.getStaticDataEncryptComp().staticBinarySafeEncrypt(i, str, bArr, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return bArr2;
    }

    public final String staticSafeDecrypt(int i, String str, String str2) {
        String str3 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str3 = instance.getStaticDataEncryptComp().staticSafeDecrypt(i, str, str2, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str3;
    }

    public final String staticSafeEncrypt(int i, String str, String str2) {
        String str3 = null;
        try {
            SecurityGuardManager instance = SecurityGuardManager.getInstance(this.a);
            if (instance != null) {
                str3 = instance.getStaticDataEncryptComp().staticSafeEncrypt(i, str, str2, "");
            }
        } catch (SecException e) {
            e.printStackTrace();
        }
        return str3;
    }
}
