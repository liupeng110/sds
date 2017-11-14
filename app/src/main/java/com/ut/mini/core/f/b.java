package com.ut.mini.core.f;

import android.content.Context;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.tencent.utils.SystemUtils;
import com.ut.mini.base.IUTMCBuildInfo;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.base.a;
import com.ut.mini.base.c;
import com.ut.mini.core.sign.IUTRequestAuthentication;
import com.ut.mini.core.sign.UTSecuritySDKRequestAuthentication;
import com.ut.mini.d.e;
import com.ut.mini.d.f;
import com.ut.mini.d.m;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/* UTMCUrlWrapper */
public class b {
    public static String a(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2 = "";
        if (map2 != null && map2.size() > 0) {
            Set keySet = map2.keySet();
            String[] strArr = new String[keySet.size()];
            keySet.toArray(strArr);
            String str3 = str2;
            for (String str4 : e.a().a(strArr, true)) {
                str3 = str3 + str4 + f.b((byte[]) map2.get(str4));
            }
            str2 = str3;
        }
        try {
            str2 = a(str, null, null, str2);
        } catch (Exception e) {
            str2 = a(a.a(), null, null, str2);
        }
        if (com.ut.mini.core.e.a().b() == null) {
            return str2;
        }
        return String.format("%s&dk=%s", new Object[]{str2, URLEncoder.encode(com.ut.mini.core.e.a().b(), "UTF-8")});
    }

    public static String b(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2;
        String format = String.format("dd=%s&nsgs=1", new Object[]{URLEncoder.encode(a(), "UTF-8")});
        String a = a();
        String str3 = "";
        if (map2 != null && map2.size() > 0) {
            str2 = (String) map2.get("cf");
            if (str2 != null) {
                str2 = f.b(str2.getBytes());
                return a(str, format, a, str2);
            }
        }
        str2 = str3;
        return a(str, format, a, str2);
    }

    public static String c(String str, Map<String, Object> map, Map<String, Object> map2) throws Exception {
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (map != null && map.size() > 0) {
            str3 = (String) map.get("logid");
            if (m.a(str3)) {
                com.ut.mini.b.a.b(1, "getSignedABTestUrl", "logid is null,return abtest!");
                return null;
            }
            str2 = String.format("logid=%s", new Object[]{str3});
        }
        return a(str, str2, str3, str4);
    }

    private static String a() {
        String f = c.a().f();
        if (f != null) {
            try {
                byte[] c = f.c(f.getBytes("UTF-8"));
                if (c != null && c.length > 0) {
                    return com.ut.mini.d.b.b(c, 2);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static String a(String str, String str2, String str3, String str4) throws Exception {
        String str5;
        String shortSDKVersion;
        Context k = c.a().k();
        String l = c.a().l();
        String h = c.a().h();
        if (h == null) {
            str5 = "";
        } else {
            str5 = h;
        }
        h = (String) com.ut.mini.core.a.a(k).get(UTLogFieldsScheme.APPVERSION.toString());
        String str6 = (String) com.ut.mini.core.a.a(k).get(UTLogFieldsScheme.OS.toString());
        String str7 = SystemUtils.QQ_VERSION_NAME_4_1_0;
        IUTMCBuildInfo c = c.a().c();
        if (c != null) {
            shortSDKVersion = c.getShortSDKVersion();
            if (!m.a(shortSDKVersion)) {
                str7 = shortSDKVersion;
            }
        }
        shortSDKVersion = (String) com.ut.mini.core.a.a(k).get(UTLogFieldsScheme.UTDID.toString());
        String str8 = "3.0";
        String valueOf = String.valueOf(System.currentTimeMillis());
        IUTRequestAuthentication d = c.a().d();
        String str9 = FeedbackItem.STATUS_WAITING;
        if (d instanceof UTSecuritySDKRequestAuthentication) {
            str9 = "1";
        }
        String str10 = "%s%s%s%s%s%s%s%s%s%s%s";
        Object[] objArr = new Object[11];
        objArr[0] = l;
        objArr[1] = str5;
        objArr[2] = h;
        objArr[3] = str6;
        objArr[4] = str7;
        objArr[5] = shortSDKVersion;
        objArr[6] = valueOf;
        objArr[7] = str8;
        objArr[8] = str9;
        if (str3 == null) {
            str3 = "";
        }
        objArr[9] = str3;
        if (str4 == null) {
            str4 = "";
        }
        objArr[10] = str4;
        str10 = d.getSign(f.b(String.format(str10, objArr).getBytes()));
        String str11 = "";
        if (!m.a(str2)) {
            str11 = str2 + "&";
        }
        return String.format("%s?%sak=%s&av=%s&c=%s&v=%s&s=%s&d=%s&sv=%s&p=%s&t=%s&u=%s&is=%s", new Object[]{str, str11, a(l), a(h), a(str5), a(str8), a(str10), a(shortSDKVersion), str7, str6, valueOf, "", str9});
    }

    private static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
