package com.ut.mini.d;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* UTMCMapUtils */
public class g {
    public static Map<String, String> a(Map<String, String> map) {
        if (map == null) {
            return map;
        }
        Map<String, String> hashMap = new HashMap();
        for (String str : map.keySet()) {
            if (str instanceof String) {
                String str2 = (String) map.get(str);
                if (!(m.a(str) || m.a(str2))) {
                    try {
                        hashMap.put(URLEncoder.encode(str, "UTF-8"), URLEncoder.encode(str2, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return hashMap;
    }
}
