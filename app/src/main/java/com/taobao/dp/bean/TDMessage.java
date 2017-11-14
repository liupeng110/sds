package com.taobao.dp.bean;

public class TDMessage {
    private String appId;
    private String securityKey;
    private String uuid;

    public String getAppId() {
        return this.appId;
    }

    public String getSecurityKey() {
        return this.securityKey;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setSecurityKey(String str) {
        this.securityKey = str;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }
}
