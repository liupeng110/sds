package com.sds.android.sdk.lib.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* UrlUtils */
public class n {
    public static String a(String str, Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            return str;
        }
        List arrayList = new ArrayList(map.size());
        for (String str2 : map.keySet()) {
            arrayList.add(new BasicNameValuePair(str2, String.valueOf(map.get(str2))));
        }
        StringBuilder stringBuilder = new StringBuilder(str);
        if (arrayList.size() > 0) {
            stringBuilder.append(stringBuilder.indexOf("?") == -1 ? "?" : "&").append(URLEncodedUtils.format(arrayList, "UTF-8"));
        }
        return stringBuilder.toString();
    }

    public static String a(String str) {
        if (m.a(str) || str.indexOf(63) <= 0) {
            return str;
        }
        return str.substring(0, str.indexOf(63));
    }
}
