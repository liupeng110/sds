package com.ttfm.android.sdk.http;

import android.util.Log;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.DSDManager;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpMusicInfo extends BaseHttp {
    public static final String LOG_TAG = HttpMusicInfo.class.getSimpleName();

    class HttpRequest implements RequestPackage {
        private String requestParams;
        private String requestUrl;

        HttpRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public void setUrl(String str) {
            this.requestUrl = str + "v1/music/get.json";
        }

        public String getRequestType() {
            return Constants.HTTP_GET;
        }

        public String getUrl() {
            return this.requestUrl;
        }

        public String getGetRequestParams() {
            return this.requestParams;
        }

        public HttpEntity getPostRequestEntity() {
            if (HttpMusicInfo.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpMusicInfo.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpMusicInfo(String str) {
        super(str);
    }

    public static HttpMusicInfo getInstance() {
        return new HttpMusicInfo("musicInfo");
    }

    public byte[] get(long j, int i, long j2, int i2) throws Exception {
        RequestPackage httpRequest = new HttpRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put("Quality", "" + i2);
            this.mMap.put("UserID", "" + j);
            this.mMap.put("ChannelID", "" + i);
            this.mMap.put("MusicID", "" + j2);
            this.mMap.putAll(TTFMEnvironmentUtils.getmParameters());
            this.mMap.put(ParamKey.UID, String.valueOf(j));
            httpRequest.setUrl(DSDManager.getPlayStreamDSDByFilter(this.mMap));
            httpRequest.setRequestParams("?" + getParams(this.mMap, Checking.md5key_nextsong));
            Log.i(LOG_TAG, httpRequest.getUrl() + httpRequest.getGetRequestParams());
            TTPodFMHttpClient.getInstance().request(httpRequest, defaultResponsePackage);
            return defaultResponsePackage.getContextData();
        } catch (Exception e) {
            throw e;
        }
    }
}
