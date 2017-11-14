package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpChannelListGet extends BaseHttp {

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
            return GlobalEnv.FMCenterUrl + "channel/list/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpChannelListGet.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpChannelListGet.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpChannelListGet(String str) {
        super(str);
    }

    public static HttpChannelListGet getInstance() {
        return new HttpChannelListGet("channellistget");
    }

    public byte[] get(long j, int i, int i2, int i3, String str) throws Exception {
        RequestPackage channelListGetRequest = new ChannelListGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            this.mMap.put("size", "" + i);
            this.mMap.put("page", "" + i2);
            if (i3 > 0) {
                this.mMap.put(SocialConstants.PARAM_TYPE, "" + i3);
            }
            this.mMap.put("orderBy", str);
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
