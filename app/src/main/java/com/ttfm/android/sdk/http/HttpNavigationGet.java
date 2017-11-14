package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpNavigationGet extends BaseHttp {

    class NavigationGetRequest extends DefaultGetRequestPackage {
        NavigationGetRequest() {
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "navigation/list/get.do";
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpNavigationGet.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpNavigationGet.this.mHttpRequestData);
            }
            return null;
        }
    }

    public HttpNavigationGet(String str) {
        super(str);
    }

    public static HttpNavigationGet getInstance() {
        return new HttpNavigationGet("HttpNavigationGet");
    }

    public byte[] get(long j) throws Exception {
        RequestPackage navigationGetRequest = new NavigationGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            navigationGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println("nnnn:" + navigationGetRequest.getUrl() + navigationGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(navigationGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
