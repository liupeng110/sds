package com.sds.android.ttpod.a.d;

import android.os.Bundle;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/* UrlUtil */
public class d {
    public static Bundle a(String str) {
        try {
            URL url = new URL(str);
            Bundle b = b(url.getQuery());
            b.putAll(b(url.getRef()));
            return b;
        } catch (MalformedURLException e) {
            return new Bundle();
        }
    }

    public static Bundle b(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2 != null && split2.length > 1) {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
        }
        return bundle;
    }
}
