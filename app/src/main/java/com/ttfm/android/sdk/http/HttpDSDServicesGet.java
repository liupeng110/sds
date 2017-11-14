package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpDSDServicesGet extends BaseHttp {

    class DSDServicesGetRequest implements RequestPackage {
        private String requestParams;

        DSDServicesGetRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return GlobalEnv.FMDSDUrl + "services/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpDSDServicesGet.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpDSDServicesGet.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpDSDServicesGet(String str) {
        super(str);
    }

    public static HttpDSDServicesGet getInstance() {
        return new HttpDSDServicesGet("DSDServicesGet");
    }

    public byte[] get(long j) throws Exception {
        RequestPackage dSDServicesGetRequest = new DSDServicesGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put("time", "" + System.currentTimeMillis());
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            this.mMap.put(ParamKey.UID, String.valueOf(j));
            dSDServicesGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_plugins));
            System.out.println(dSDServicesGetRequest.getUrl() + dSDServicesGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(dSDServicesGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
