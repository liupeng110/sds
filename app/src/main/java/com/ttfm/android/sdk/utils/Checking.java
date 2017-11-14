package com.ttfm.android.sdk.utils;

import com.ttfm.android.sdk.http.TTPodFMHttpClient;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class Checking {
    static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String md5key = "c9f699a911ec2e16ad52ca43b5fea4a5";
    public static final String md5key_fmchannel = "1e52b1976455fd7ea1494fbb89643728";
    public static final String md5key_nextsong = "33ece0e9171ef8cb30e50c5a02e4fdcd";
    public static final String md5key_plugins = "0311c213522bdcd2e95c175135b0256c";

    private Checking() {
    }

    public static String doCheckSunStrs(Object... objArr) {
        int i = 0;
        if (objArr == null) {
            try {
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        int i2;
        StringBuilder stringBuilder = new StringBuilder();
        for (i2 = 0; i2 < objArr.length; i2++) {
            if (objArr[i2] != null) {
                stringBuilder.append(objArr[i2]);
            }
        }
        stringBuilder.append(md5key);
        byte[] bytes = stringBuilder.toString().getBytes(TTPodFMHttpClient.AppEncode);
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bytes);
        byte[] digest = instance.digest();
        int length = digest.length;
        char[] cArr = new char[(length * 2)];
        i2 = 0;
        while (i < length) {
            byte b = digest[i];
            int i3 = i2 + 1;
            cArr[i2] = hexDigits[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = hexDigits[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public static String doCheckSun(String str) {
        int i = 0;
        if (str == null) {
            try {
                return "";
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        byte[] bytes = stringBuilder.toString().getBytes(TTPodFMHttpClient.AppEncode);
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(bytes);
        byte[] digest = instance.digest();
        int length = digest.length;
        char[] cArr = new char[(length * 2)];
        int i2 = 0;
        while (i < length) {
            byte b = digest[i];
            int i3 = i2 + 1;
            cArr[i2] = hexDigits[(b >>> 4) & 15];
            i2 = i3 + 1;
            cArr[i3] = hexDigits[b & 15];
            i++;
        }
        return new String(cArr);
    }

    public static String MapOrderByKey(Map<String, String> map) {
        TreeMap treeMap = new TreeMap(map);
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : treeMap.entrySet()) {
            stringBuilder.append(entry.getKey() + "=" + entry.getValue());
            if (!entry.equals(treeMap.lastEntry())) {
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }

    public static String MapOrderByKeyEncode(Map<String, String> map) throws UnsupportedEncodingException {
        TreeMap treeMap = new TreeMap(map);
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : treeMap.entrySet()) {
            if (entry != null) {
                stringBuilder.append(entry.getKey() + "=" + URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                if (!entry.equals(treeMap.lastEntry())) {
                    stringBuilder.append("&");
                }
            }
        }
        return stringBuilder.toString();
    }
}
