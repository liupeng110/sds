package com.ttfm.android.sdk.http;

import com.ttfm.android.sdk.utils.Checking;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BaseHttp {
    protected byte[] mHttpRequestData;
    protected HashMap<String, String> mMap;
    protected String mMethod;

    public BaseHttp(String str) {
        this.mHttpRequestData = null;
        this.mMethod = "";
        this.mMap = null;
        this.mMap = new HashMap();
        this.mMethod = str;
    }

    public String getParams(Map<String, String> map, String str) throws UnsupportedEncodingException {
        String str2 = "";
        return Checking.MapOrderByKeyEncode(map) + "&sign=" + Checking.doCheckSun(URLEncoder.encode(Checking.MapOrderByKey(map), "UTF-8") + str);
    }

    public void MapValueEncode() throws UnsupportedEncodingException {
        if (this.mMap != null) {
            for (Entry entry : this.mMap.entrySet()) {
                if (entry != null) {
                    this.mMap.put((String) entry.getKey(), URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                }
            }
        }
    }
}
