package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpChannelInfo extends BaseHttp {

    class ChannelInfoRequest implements RequestPackage {
        private String requestParams;

        ChannelInfoRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "channel/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpChannelInfo.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpChannelInfo.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpChannelInfo(String str) {
        super(str);
    }

    public static HttpChannelInfo getInstance() {
        return new HttpChannelInfo("channelInfoGet");
    }

    public byte[] get(long j, long j2) throws Exception {
        RequestPackage channelInfoRequest = new ChannelInfoRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, String.valueOf(j));
            this.mMap.put("ciId", "" + j2);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            channelInfoRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println(channelInfoRequest.getUrl() + channelInfoRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(channelInfoRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
