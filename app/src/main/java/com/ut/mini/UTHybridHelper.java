package com.ut.mini;

import android.net.Uri;
import android.webkit.WebView;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.ut.mini.UTHitBuilders.UTPageHitBuilder;
import com.ut.mini.b.a;
import com.ut.mini.base.d;
import com.ut.mini.d.m;
import com.ut.mini.internal.UTOriginalCustomHitBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UTHybridHelper {
    private static UTHybridHelper a = new UTHybridHelper();

    public static UTHybridHelper getInstance() {
        return a;
    }

    public void setH5Url(String str) {
        if (str != null) {
            d.b().a(str);
        }
    }

    public void h5UT(Map<String, String> map, WebView webView) {
        if (map == null || map.size() == 0) {
            a.c(1, "h5UT", "dataMap is empty");
            return;
        }
        String str = (String) map.get("functype");
        if (str == null) {
            a.c(1, "h5UT", "funcType is null");
            return;
        }
        String str2 = (String) map.get("utjstype");
        if (str2 == null || str2.equals(FeedbackItem.STATUS_WAITING) || str2.equals("1")) {
            map.remove("functype");
            Date date = new Date();
            if (str.equals("2001")) {
                h5Page(date, map, webView);
                return;
            } else if (str.equals("2101")) {
                h5Ctrl(date, map);
                return;
            } else {
                return;
            }
        }
        a.c(1, "h5UT", "utjstype should be 1 or 0 or null");
    }

    private void h5Page(Date date, Map<String, String> map, Object obj) {
        if (map != null && map.size() != 0) {
            String h5PageName = getH5PageName((String) map.get("urlpagename"), (String) map.get("url"));
            if (h5PageName == null || m.a(h5PageName)) {
                a.c(1, "h5Page", "pageName is null,return");
                return;
            }
            Map h5PageParseArgsWithAplus;
            String d = d.b().d();
            String str = (String) map.get("utjstype");
            map.remove("utjstype");
            if (str == null || str.equals(FeedbackItem.STATUS_WAITING)) {
                h5PageParseArgsWithAplus = h5PageParseArgsWithAplus(map);
            } else if (str.equals("1")) {
                h5PageParseArgsWithAplus = h5PageParseArgsWithOutAplus(map);
            } else {
                h5PageParseArgsWithAplus = null;
            }
            UTPageHitBuilder uTPageHitBuilder = new UTPageHitBuilder(h5PageName);
            uTPageHitBuilder.setReferPage(d).setProperties(h5PageParseArgsWithAplus);
            UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send(uTPageHitBuilder.build());
                d.b().b(h5PageName);
                d.b().a(true);
                return;
            }
            a.c(1, "h5Page event error", "Fatal Error,must call setRequestAuthentication method first.");
        }
    }

    private void h5Ctrl(Date date, Map<String, String> map) {
        if (map != null && map.size() != 0) {
            String h5PageName = getH5PageName((String) map.get("urlpagename"), (String) map.get("url"));
            if (h5PageName == null || m.a(h5PageName)) {
                a.b(1, "h5Ctrl", "pageName is null,return");
                return;
            }
            String str = (String) map.get("logkey");
            if (str == null || m.a(str)) {
                a.b(1, "h5Ctrl", "logkey is null,return");
                return;
            }
            Map h5CtrlParseArgsWithAplus;
            String str2 = (String) map.get("utjstype");
            map.remove("utjstype");
            if (str2 == null || str2.equals(FeedbackItem.STATUS_WAITING)) {
                h5CtrlParseArgsWithAplus = h5CtrlParseArgsWithAplus(map);
            } else if (str2.equals("1")) {
                h5CtrlParseArgsWithAplus = h5CtrlParseArgsWithOutAplus(map);
            } else {
                h5CtrlParseArgsWithAplus = null;
            }
            UTOriginalCustomHitBuilder uTOriginalCustomHitBuilder = new UTOriginalCustomHitBuilder(h5PageName, 2101, str, null, null, h5CtrlParseArgsWithAplus);
            UTTracker defaultTracker = UTAnalytics.getInstance().getDefaultTracker();
            if (defaultTracker != null) {
                defaultTracker.send(uTOriginalCustomHitBuilder.build());
            } else {
                a.c(1, "h5Ctrl event error", "Fatal Error,must call setRequestAuthentication method first.");
            }
        }
    }

    private Map<String, String> h5PageParseArgsWithAplus(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Object obj;
        String str;
        HashMap hashMap = new HashMap();
        String str2 = (String) map.get("url");
        String str3 = "_h5url";
        if (str2 == null) {
            obj = "";
        } else {
            str = str2;
        }
        hashMap.put(str3, obj);
        if (str2 != null) {
            Uri parse = Uri.parse(str2);
            str = parse.getQueryParameter("spm");
            if (str == null || m.a(str)) {
                hashMap.put("spm", "0.0.0.0");
            } else {
                hashMap.put("spm", str);
            }
            str2 = parse.getQueryParameter("scm");
            if (!(str2 == null || m.a(str2))) {
                hashMap.put("scm", str2);
            }
        } else {
            hashMap.put("spm", "0.0.0.0");
        }
        Object obj2 = (String) map.get("spmcnt");
        str = "_spmcnt";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("spmpre");
        str = "_spmpre";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("lzsid");
        str = "_lzsid";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("extendargs");
        str = "_h5ea";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        obj2 = (String) map.get("cna");
        str = "_cna";
        if (obj2 == null) {
            obj2 = "";
        }
        hashMap.put(str, obj2);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private Map<String, String> h5PageParseArgsWithOutAplus(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Object obj = (String) map.get("url");
        String str = "_h5url";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        obj = (String) map.get("extendargs");
        str = "_h5ea";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private Map<String, String> h5CtrlParseArgsWithAplus(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        Object obj = (String) map.get("logkeyargs");
        String str = "_lka";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        obj = (String) map.get("cna");
        str = "_cna";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        obj = (String) map.get("extendargs");
        str = "_h5ea";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private Map<String, String> h5CtrlParseArgsWithOutAplus(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        Object obj = (String) map.get("extendargs");
        String str = "_h5ea";
        if (obj == null) {
            obj = "";
        }
        hashMap.put(str, obj);
        hashMap.put("_ish5", "1");
        return hashMap;
    }

    private String getH5PageName(String str, String str2) {
        String str3 = "";
        if (str != null && !m.a(str)) {
            return str;
        }
        if (m.a(str2)) {
            return str3;
        }
        int indexOf = str2.indexOf("?");
        if (indexOf == -1) {
            return str2;
        }
        return str2.substring(0, indexOf);
    }
}
