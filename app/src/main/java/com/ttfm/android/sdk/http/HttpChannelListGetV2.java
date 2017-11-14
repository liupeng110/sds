package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpChannelListGetV2 extends BaseHttp {

    class ChannelListGetRequest implements RequestPackage {
        private String requestParams;

        ChannelListGetRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "channel/list/v2/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpChannelListGetV2.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpChannelListGetV2.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpChannelListGetV2(String str) {
        super(str);
    }

    public static HttpChannelListGetV2 getInstance() {
        return new HttpChannelListGetV2("channellistgetv2");
    }

    public byte[] get(long j, int i, int i2, String str) throws Exception {
        RequestPackage channelListGetRequest = new ChannelListGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            this.mMap.put("size", "" + i);
            this.mMap.put("page", "" + i2);
            this.mMap.put("keyword", "" + str);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            channelListGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println(channelListGetRequest.getUrl() + channelListGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(channelListGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
