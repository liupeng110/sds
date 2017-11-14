package com.sds.android.cloudapi.ttpod.a;

import com.igexin.download.Downloads;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import com.sds.android.sdk.core.statistic.HttpClientProxy;
import com.sds.android.sdk.lib.b.b;
import com.sds.android.sdk.lib.b.d;
import com.sds.android.sdk.lib.b.g;
import com.sds.android.sdk.lib.b.l;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.tencent.open.SocialConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

/* FeedbackAPI */
public class h {
    private static final String a = h.class.getSimpleName();
    private static final String b = ("http://api.feedback.ttpod.cn/feedback/feedback/" + b() + "/threads");
    private static final JSONObject c = a();

    public static l a(String str, String str2, String str3) {
        Map hashMap = new HashMap(2);
        hashMap.put("ip", str2);
        hashMap.put("proposalContent", str);
        if (!m.a(str3)) {
            hashMap.put("contactWay", str3);
        }
        l gVar = new g(b.class, b, a(hashMap));
        gVar.b(HttpClientProxy.HEADER_CONTENT_TYPE, "application/json");
        return gVar;
    }

    public static l a(Long l, String str) {
        l dVar = new d(b.class, b);
        if (l != null) {
            dVar.a("after", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(l.longValue())));
        }
        if (!m.a(str)) {
            dVar.a(Downloads.COLUMN_STATUS, (Object) str);
        }
        return dVar;
    }

    public static l a(Long l) {
        l dVar = new d(b.class, b);
        dVar.a("after", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(l.longValue())));
        dVar.a("source", Integer.valueOf(1));
        return dVar;
    }

    public static FeedbackItem a(String str) {
        b d = new d(b.class, b + "/" + str).d();
        if (d.isSuccess()) {
            return (FeedbackItem) f.a(d.getContent(), FeedbackItem.class);
        }
        return null;
    }

    public static l a(String str, Long l, String str2) {
        l dVar = new d(b.class, b(str));
        if (l != null) {
            dVar.a("after", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(l.longValue())));
        }
        if (!m.a(str2)) {
            dVar.a("isRead", (Object) str2);
        }
        return dVar;
    }

    public static l b(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SocialConstants.PARAM_TYPE, str2);
            jSONObject.put("content", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        l gVar = new g(b.class, b(str), jSONObject.toString());
        gVar.b(HttpClientProxy.HEADER_CONTENT_TYPE, "application/json");
        return gVar;
    }

    private static JSONObject a() {
        Map e = EnvironmentUtils.b.e();
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : e.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private static String a(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject(c.toString());
            for (Entry entry : map.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String b(String str) {
        return b + "/" + str + "/" + "messages";
    }

    private static String b() {
        String a = EnvironmentUtils.b.a();
        if (m.a(a)) {
            return null;
        }
        try {
            return URLEncoder.encode(a, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return a;
        }
    }
}
