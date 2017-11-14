package com.taobao.dp.bean;

public class ServiceData {
    private String app;
    private String os;
    private String payload;
    private String service;
    private String signature;
    private long timestamp;
    private String version;

    public String getApp() {
        return this.app;
    }

    public String getOs() {
        return this.os;
    }

    public String getPayload() {
        return this.payload;
    }

    public String getService() {
        return this.service;
    }

    public String getSignature() {
        return this.signature;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getVersion() {
        return this.version;
    }

    public void setApp(String str) {
        this.app = str;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public void setPayload(String str) {
        this.payload = str;
    }

    public void setService(String str) {
        this.service = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
