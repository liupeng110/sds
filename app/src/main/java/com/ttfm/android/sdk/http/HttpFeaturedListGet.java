package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpFeaturedListGet extends BaseHttp {

    class FeaturedListGetRequest extends DefaultGetRequestPackage {
        FeaturedListGetRequest() {
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "featured/list/get.do";
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpFeaturedListGet.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpFeaturedListGet.this.mHttpRequestData);
            }
            return null;
        }
    }

    public HttpFeaturedListGet(String str) {
        super(str);
    }

    public static HttpFeaturedListGet getInstance() {
        return new HttpFeaturedListGet("HttpFeaturedListGet");
    }

    public byte[] get(long j, int i) throws Exception {
        RequestPackage featuredListGetRequest = new FeaturedListGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            if (i > 0) {
                this.mMap.put("version", "" + i);
            }
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            featuredListGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println(featuredListGetRequest.getUrl() + featuredListGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(featuredListGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
