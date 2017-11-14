package com.ttfm.android.sdk.http;

import com.tencent.connect.common.Constants;
import org.apache.http.HttpEntity;

public abstract class DefaultGetRequestPackage implements RequestPackage {
    private String requestParams;

    public abstract HttpEntity getPostRequestEntity();

    public abstract String getUrl();

    public void setRequestParams(String str) {
        this.requestParams = str;
    }

    public String getGetRequestParams() {
        return this.requestParams;
    }

    public String getRequestType() {
        return Constants.HTTP_GET;
    }

    public String getContentType() {
        return RequestPackage.CONTENT_TYPE_TEXT;
    }
}
