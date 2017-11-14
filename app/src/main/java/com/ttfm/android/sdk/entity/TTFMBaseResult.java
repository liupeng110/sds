package com.ttfm.android.sdk.entity;

import java.io.Serializable;

public class TTFMBaseResult implements Serializable {
    private static final long serialVersionUID = 1;
    private int code;
    private String msg = "";
    private long time;

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public boolean isSuccess() {
        return 200 == this.code;
    }

    public boolean isExists() {
        return 1102 == this.code;
    }
}
