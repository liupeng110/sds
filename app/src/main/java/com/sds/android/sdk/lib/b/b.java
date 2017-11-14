package com.sds.android.sdk.lib.b;

import java.io.Serializable;

/* BaseResultRest */
public class b extends com.sds.android.sdk.lib.request.b implements Serializable {
    private String mContent = "";
    private int mHttpStatus;
    private String mLocation = "";
    private int mTTL;

    public String getLocation() {
        return this.mLocation;
    }

    public void setLocation(String str) {
        this.mLocation = str;
    }

    public String toString() {
        return getClass().getSimpleName() + "{" + "mCode=" + this.mHttpStatus + ", mContent='" + this.mContent + '\'' + '}';
    }

    public b(int i, String str) {
        this.mHttpStatus = i;
        this.mContent = str;
    }

    public int getCode() {
        return this.mHttpStatus;
    }

    public String getMessage() {
        throw new UnsupportedOperationException();
    }

    public boolean isSuccess() {
        return parseCode(this.mHttpStatus);
    }

    protected boolean parseCode(int i) {
        return this.mHttpStatus == 200;
    }

    public void setCode(int i) {
        this.mHttpStatus = i;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String str) {
        this.mContent = str;
    }

    public int getTTL() {
        return this.mTTL;
    }
}
