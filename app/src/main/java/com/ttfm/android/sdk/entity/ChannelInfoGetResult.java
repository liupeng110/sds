package com.ttfm.android.sdk.entity;

import java.io.Serializable;

public class ChannelInfoGetResult implements Serializable {
    private int code;
    private int count;
    private ChannelData data = new ChannelData();
    private String msg;
    private long time;

    private class ChannelData implements Serializable {
        private ChannelEntity channel = new ChannelEntity();
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public ChannelEntity getChannel() {
        return this.data != null ? this.data.channel : null;
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

    public int getCount() {
        return this.count;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public boolean isSuccess() {
        return 200 == this.code;
    }
}
