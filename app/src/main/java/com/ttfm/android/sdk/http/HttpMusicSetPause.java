package com.ttfm.android.sdk.http;

import android.util.Log;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.common.Constants;
import com.ttfm.android.sdk.core.DSDManager;
import com.ttfm.android.sdk.utils.Checking;
import com.ttfm.android.sdk.utils.TTFMEnvironmentUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;

public class HttpMusicSetPause extends BaseHttp {
    public static final String LOG_TAG = HttpMusicSetPause.class.getSimpleName();

    class HttpRequest implements RequestPackage {
        private String requestParams;
        private String requestUrl;

        HttpRequest() {
        }

        public void setRequestParams(String str) {
            this.requestParams = str;
        }

        public void setUrl(String str) {
            this.requestUrl = str + "v2/SetPause/put.json";
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
            if (HttpMusicSetPause.this.mHttpRequestData != null) {
                return new ByteArrayEntity(HttpMusicSetPause.this.mHttpRequestData);
            }
            return null;
        }

        public String getContentType() {
            return RequestPackage.CONTENT_TYPE_TEXT;
        }
    }

    private HttpMusicSetPause(String str) {
        super(str);
    }

    public static HttpMusicSetPause getInstance() {
        return new HttpMusicSetPause("setPause");
    }

    public byte[] get(long j, int i, long j2, long j3, long j4, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4) throws Exception {
        RequestPackage httpRequest = new HttpRequest();
        Object defaultResponsePackage = new DefaultResponsePackage();
        try {
            this.mMap.put("UserID", "" + j);
            this.mMap.put("ChannelID", "" + i);
            this.mMap.put("OldUserID", "" + j2);
            this.mMap.put("OldMusicID", "" + j3);
            this.mMap.put("OldSerialNo", "" + j4);
            this.mMap.put("OldDuration", "" + i2);
            this.mMap.put("OldPlayedMSeconds", "" + i3);
            this.mMap.put("OldSkipped", "" + (z ? 1 : 0));
            this.mMap.put("OldCollected", "" + (z2 ? 1 : 0));
            this.mMap.put("OldLoved", "" + (z3 ? 1 : 0));
            this.mMap.put("OldHated", "" + (z4 ? 1 : 0));
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
