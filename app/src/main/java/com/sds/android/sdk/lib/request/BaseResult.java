package com.sds.android.sdk.lib.request;

import com.b.a.a.c;
import java.io.Serializable;

public class BaseResult extends b implements Serializable {
    @c(a = "code")
    private int mCode;
    @c(a = "msg")
    private String mMessage;
    @c(a = "ttl")
    private int mTTL;

    public String toString() {
        return getClass().getSimpleName() + "{" + "mCode=" + this.mCode + ", mMessage='" + this.mMessage + '\'' + '}';
    }

    public BaseResult(int i, String str) {
        this.mCode = i;
        this.mMessage = str;
    }

    public int getCode() {
        return this.mCode;
    }

    public boolean isSuccess() {
        return this.mCode == 1;
    }

    public boolean isError() {
        return this.mCode == 0;
    }

    public boolean isUnableConnectServer() {
        return this.mCode == -1;
    }

    public boolean isUnableParseResultData() {
        return this.mCode == -2;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public String getMessage() {
        return this.mMessage == null ? "" : this.mMessage;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public int getTTL() {
        return this.mTTL;
    }
}
