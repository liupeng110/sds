package com.taobao.dp.service;

import android.content.Context;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.taobao.dp.bean.DeviceInfo;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public final class f implements Runnable {
    private Context a;
    private b b;
    private String c;
    private String d;

    public f(Context context, String str, String str2) {
        this.a = context;
        this.b = new b(context);
        this.c = str;
        this.d = str2;
    }

    public final void a() {
        try {
            String str;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(ParamKey.NICK, this.c);
            jSONObject.put("did", this.d);
            try {
                Class cls = Class.forName("com.taobao.tao.util.TBSUtil");
                Class cls2 = Class.forName("android.taobao.locate.LocationInfo");
                Method method = cls2.getMethod("getLongitude", new Class[0]);
                Method method2 = cls2.getMethod("getOffsetLongitude", new Class[0]);
                Method method3 = cls2.getMethod("getLatitude", new Class[0]);
                Method method4 = cls2.getMethod("getOffsetLatitude", new Class[0]);
                if (cls != null) {
                    Method declaredMethod = cls.getDeclaredMethod("getLocationInfo", new Class[]{Context.class});
                    if (declaredMethod != null) {
                        Object invoke = declaredMethod.invoke(cls, new Object[]{this.a});
                        if (invoke != null) {
                            double doubleValue = ((Double) method.invoke(invoke, new Object[0])).doubleValue();
                            double doubleValue2 = !Double.isNaN(doubleValue) ? doubleValue : ((Double) method2.invoke(invoke, new Object[0])).doubleValue();
                            doubleValue = ((Double) method3.invoke(invoke, new Object[0])).doubleValue();
                            if (Double.isNaN(doubleValue)) {
                                doubleValue = ((Double) method4.invoke(invoke, new Object[0])).doubleValue();
                            }
                            str = Double.toString(doubleValue2) + SelectCountryActivity.SPLITTER + Double.toString(doubleValue);
                            if (str != null && str.length() > 0) {
                                jSONObject.put("location", str);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
            DeviceInfo a = this.b.a();
            String bssid = a.getBssid();
            String ssid = a.getSsid();
            String providername = a.getProvidername();
            str = a.getCellId();
            if (bssid != null && bssid.length() > 1) {
                jSONObject.put("bssid", bssid);
            }
            if (ssid != null && ssid.length() > 1) {
                jSONObject.put("ssid", ssid);
            }
            if (providername != null && providername.length() > 1) {
                jSONObject.put("providername", providername);
            }
            if (str != null && str.length() > 1) {
                jSONObject.put("cellId", str);
            }
        } catch (JSONException e2) {
        }
    }

    public final void run() {
        a();
    }
}
