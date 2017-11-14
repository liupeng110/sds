package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpSearchChannel extends BaseHttp {

    class SearchChannelGetRequest implements RequestPackage {
        private String requestParams;

        SearchChannelGetRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "channel/so/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpSearchChannel.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpSearchChannel.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpSearchChannel(String str) {
        super(str);
    }

    public static HttpSearchChannel getInstance() {
        return new HttpSearchChannel("searchchannelget");
    }

    public byte[] get(long j, int i, String str, int i2) throws Exception {
        RequestPackage searchChannelGetRequest = new SearchChannelGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            this.mMap.put("size", "" + i);
            this.mMap.put("query", "" + str);
            this.mMap.put(SocialConstants.PARAM_TYPE, "" + i2);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            searchChannelGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println(searchChannelGetRequest.getUrl() + searchChannelGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(searchChannelGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
