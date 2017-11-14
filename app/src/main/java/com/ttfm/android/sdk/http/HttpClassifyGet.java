package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpClassifyGet extends BaseHttp {

    class ClassifyGetRequest implements RequestPackage {
        private String requestParams;

        ClassifyGetRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "label/select/list/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpClassifyGet.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpClassifyGet.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    public HttpClassifyGet(String str) {
        super(str);
    }

    public static HttpClassifyGet getInstance() {
        return new HttpClassifyGet("HttpClassifyGet");
    }

    public byte[] get(long j) throws Exception {
        RequestPackage classifyGetRequest = new ClassifyGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            classifyGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println(classifyGetRequest.getUrl() + classifyGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(classifyGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
