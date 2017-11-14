package com.ttfm.android.sdk.http;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.GlobalEnv;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpChannelSongListGet extends BaseHttp {

    class ChannelBBSSongListGetRequest implements RequestPackage {
        private String requestParams;

        ChannelBBSSongListGetRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return GlobalEnv.FMCenterUrl + "csong/list/get.do";
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpChannelSongListGet.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpChannelSongListGet.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpChannelSongListGet(String str) {
        super(str);
    }

    public static HttpChannelSongListGet getInstance() {
        return new HttpChannelSongListGet("channelSsonglistget");
    }

    public byte[] get(long j, long j2, int i, int i2) throws Exception {
        RequestPackage channelBBSSongListGetRequest = new ChannelBBSSongListGetRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put(ParamKey.UID, "" + j);
            this.mMap.put("ciId", "" + j2);
            this.mMap.put("size", "" + i);
            this.mMap.put("page", "" + i2);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            channelBBSSongListGetRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_fmchannel));
            System.out.println(channelBBSSongListGetRequest.getUrl() + channelBBSSongListGetRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(channelBBSSongListGetRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
