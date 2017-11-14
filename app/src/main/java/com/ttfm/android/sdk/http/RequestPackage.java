package com.ttfm.android.sdk.http;

import org.apache.http.HttpEntity;

public interface RequestPackage {
    public static final String CONTENT_TYPE_DEFAULT = "application/json";
    public static final String CONTENT_TYPE_TEXT = "text/html";

    String getContentType();

    String getGetRequestParams();

    HttpEntity getPostRequestEntity();

    String getRequestType();

    String getUrl();
}
