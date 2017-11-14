package com.ttfm.android.sdk.http;

public class DefaultResponsePackage implements ResponsePackage {
    private int mStateCode = 200;
    private byte[] rdata = null;

    public void setContext(byte[] bArr) {
        this.rdata = bArr;
    }

    public byte[] getContextData() {
        return this.rdata;
    }

    public int getStatusCode() {
        return this.mStateCode;
    }

    public void setStatusCode(int i) {
        this.mStateCode = i;
    }
}
