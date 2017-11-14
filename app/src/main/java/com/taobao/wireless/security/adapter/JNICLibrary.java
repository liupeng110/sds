package com.taobao.wireless.security.adapter;

import android.content.Context;

public class JNICLibrary {
    private static JNICLibrary a;

    private JNICLibrary() {
    }

    public static JNICLibrary getInstance() {
        if (a == null) {
            a = new JNICLibrary();
        }
        return a;
    }

    public native boolean addErrorRecord(char c, char c2, char c3, int i, int i2);

    public native byte[] analyzeOpenIdNative(String str, String str2, String str3, byte[] bArr, String str4);

    public native int checkEnvAndFilesNative(String[] strArr, int i, Context context);

    public native byte[] encryptSecretData(int i, byte[] bArr, byte[] bArr2);

    public native String getAppKeyByIndex(int i, String str);

    public native byte[] getDexHashNative(String str, String str2, int i);

    public native String getDynamicKeyNative(String str, String str2);

    public native byte[] getDynamicValueNative(String str, String[] strArr);

    public native byte[] getEncryptedDevAndEnvInfoNative(int i, String str);

    public native String getExtraData(String str, String str2);

    public native int getKeyType(String str, String str2);

    public native byte[] getOrgValueNative(String str, String[] strArr);

    public native String getSecurityBodyData(String str, String str2);

    public native byte[] getSeed(byte[] bArr);

    public native String indieKitRequestNative(String[] strArr, int i, String str, int i2);

    public native boolean initSecurityBody(String str, String str2);

    public native int initialize(Context context, boolean z);

    public native boolean isPackageValidNative(String str);

    public native boolean isRootNative();

    public native boolean isSimulator();

    public native boolean putUserActionRecord(String str, String str2, float f, float f2);

    public native boolean putUserTrackRecord(String str);

    public native byte[] saveKeyEncrypt(byte[] bArr, byte[] bArr2);

    public native byte[] seedDecrypt(int i, byte[] bArr, byte[] bArr2);

    public native byte[] seedEncrypt(int i, byte[] bArr, byte[] bArr2);

    public native String signRequestNative(String[] strArr, int i, String str, int i2, String str2);

    public native byte[] staticDecrypt(int i, byte[] bArr, byte[] bArr2, String str);

    public native byte[] staticEncrypt(int i, byte[] bArr, byte[] bArr2, String str);

    public native int testCpuArchCounterNative();

    public native boolean testDataCollectionAdapterNative();

    public native boolean testDataReportAdapterNative();

    public native void updateNickNative();
}
