package com.ttfm.android.sdk.http;

public interface ResponsePackage {
    byte[] getContextData();

    int getStatusCode();

    void setContext(byte[] bArr);

    void setStatusCode(int i);
}
