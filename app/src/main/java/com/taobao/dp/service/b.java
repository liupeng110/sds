package com.taobao.dp.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.provider.Settings.System;
import com.taobao.dp.a.d;
import com.taobao.dp.bean.DeviceInfo;
import com.taobao.dp.bean.ServiceData;
import com.taobao.dp.bean.TDMessage;
import com.taobao.dp.util.e;
import org.json.JSONException;
import org.json.JSONObject;

public final class b {
    private Context a;

    public static class a {
        public String a = "";
        public String b = "";
    }

    public b(Context context) {
        this.a = context;
    }

    private SharedPreferences a(String str) {
        int i = 0;
        if (VERSION.SDK_INT >= 11) {
            i = 4;
        }
        return this.a.getSharedPreferences(str, i);
    }

    private static TDMessage a(TDMessage tDMessage) {
        try {
            String uuid = tDMessage.getUuid();
            com.taobao.dp.a.a.a();
            tDMessage.setUuid(com.taobao.dp.a.a.a(uuid));
            return tDMessage;
        } catch (Exception e) {
            return null;
        }
    }

    public static String a(Context context) {
        String str;
        Object obj;
        String str2 = null;
        try {
            str = (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke(null, new Object[]{context});
            if (str != null) {
                try {
                    if (str.contains("?")) {
                        str = "unknown utdid";
                    }
                } catch (Exception e) {
                    Exception exception = e;
                    str2 = str;
                    Exception exception2 = exception;
                    new StringBuilder("getUTDID2").append(obj);
                    str = str2;
                    return str != null ? str : "";
                }
            }
        } catch (Exception e2) {
            obj = e2;
            new StringBuilder("getUTDID2").append(obj);
            str = str2;
            if (str != null) {
            }
        }
        if (str != null) {
        }
    }

    private TDMessage b(String str, String str2, String str3) {
        String a = d.a(str, str2, str3, this.a);
        if (!(a == null || "".equals(a))) {
            a = c(a, str2, str3);
            if (a != null) {
                TDMessage tDMessage = new TDMessage();
                tDMessage.setUuid(a);
                tDMessage.setAppId(str2);
                return a(tDMessage);
            }
        }
        return null;
    }

    private void b(String str, String str2) {
        if (str2 != null) {
            try {
                System.putString(this.a.getContentResolver(), str, str2);
            } catch (SecurityException e) {
                new StringBuilder("insertSystemSettings cache uuid").append(e);
            }
        }
    }

    private String c() {
        String str;
        try {
            str = (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", new Class[]{Context.class}).invoke(null, new Object[]{this.a});
        } catch (Exception e) {
            new StringBuilder("getUTDID1").append(e);
            str = null;
        }
        return str != null ? str : "";
    }

    private String c(String str, String str2, String str3) {
        String str4 = null;
        if (str != null) {
            try {
                Object serviceData = new ServiceData();
                serviceData.setApp(str2);
                serviceData.setVersion(com.taobao.dp.client.b.PROTOCAL_VERSION);
                serviceData.setService(com.taobao.dp.client.b.SERVICE);
                serviceData.setOs(com.taobao.dp.client.b.OS);
                serviceData.setPayload(str);
                serviceData.setTimestamp(System.currentTimeMillis());
                serviceData.setSignature(d.c(serviceData.getService() + serviceData.getVersion() + serviceData.getApp() + serviceData.getOs() + str + serviceData.getTimestamp(), str2, str3, this.a));
                str4 = com.taobao.dp.util.d.a(e.a(serviceData));
            } catch (Exception e) {
            }
        }
        return str4;
    }

    public final DeviceInfo a() {
        DeviceInfo deviceInfo = new DeviceInfo();
        a aVar = new a(this.a);
        deviceInfo.setUtdid(c());
        aVar.a(deviceInfo);
        return deviceInfo;
    }

    public final String a(com.taobao.dp.client.a aVar) {
        return this.a != null ? System.getString(this.a.getContentResolver(), aVar.c()) : "";
    }

    public final String a(String str, String str2) {
        String string = a("HARD-INFO").getString("hardinfo", null);
        if (string != null) {
            string = d.b(string, str, str2, this.a);
            if (string != null) {
                try {
                    string = new JSONObject(string).getString("hardinfo");
                } catch (JSONException e) {
                    string = null;
                }
            }
        }
        if (string != null) {
            return string;
        }
        String a = g.a("hid.dat");
        if ("".equals(a)) {
            return string;
        }
        try {
            com.taobao.dp.a.a.a();
            return com.taobao.dp.a.a.b(a);
        } catch (Exception e2) {
            return null;
        }
    }

    public final String a(String str, String str2, com.taobao.dp.client.a aVar) {
        String str3;
        new StringBuilder("DeviceInfoManager getLocalUuid env.getUuidAppName()=").append(aVar.e());
        new StringBuilder("DeviceInfoManager getLocalUuid env.getUuidAuthCode()=").append(aVar.f());
        if (str == null || str.length() <= 0) {
            str3 = null;
        } else {
            try {
                str3 = a("UUID").getString(aVar.d(), null);
                if (str3 == null) {
                    return null;
                }
                new StringBuilder("DeviceInfoManager getLocalUuid context=").append(this.a);
                str3 = d.b(str3, str, str2, this.a);
                if (str3 != null) {
                    str3 = new JSONObject(str3).getString(aVar.d());
                }
            } catch (Exception e) {
                new StringBuilder("DeviceInfoManager getLocalUuid").append(e.getLocalizedMessage());
                return null;
            }
        }
        return str3;
    }

    public final void a(String str, String str2, String str3) {
        SharedPreferences a = a("HARD-INFO");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("hardinfo", str);
            Editor edit = a.edit();
            edit.putString("hardinfo", d.a(jSONObject.toString(), str2, str3, this.a));
            edit.putLong("updateTime", System.currentTimeMillis());
            edit.commit();
        } catch (JSONException e) {
        }
        try {
            com.taobao.dp.a.a.a();
            g.a("hid.dat", com.taobao.dp.a.a.a(str));
        } catch (Exception e2) {
        }
    }

    public final void a(String str, String str2, String str3, com.taobao.dp.client.a aVar) {
        if (str != null && !"".equals(str)) {
            SharedPreferences a = a("UUID");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(aVar.d(), str);
                Editor edit = a.edit();
                new StringBuilder("DeviceInfoManager saveUUIDLocal jsonObject=").append(jSONObject);
                new StringBuilder("DeviceInfoManager saveUUIDLocal env.getUuidAppName()=").append(aVar.e());
                new StringBuilder("DeviceInfoManager saveUUIDLocal env.getUuidAuthCode()=").append(aVar.f());
                new StringBuilder("DeviceInfoManager saveUUIDLocal context=").append(this.a);
                new StringBuilder("DeviceInfoManager saveUUIDLocal encryptedJsonObject=").append(d.a(jSONObject.toString(), str2, str3, this.a));
                edit.putString(aVar.d(), d.a(jSONObject.toString(), str2, str3, this.a));
                edit.commit();
                new StringBuilder("DeviceInfoManager setAppNameAndAuthCode env.getName()=").append(aVar.g());
                new StringBuilder("DeviceInfoManager setAppNameAndAuthCode env.getUuidAppName()=").append(aVar.e());
                new StringBuilder("DeviceInfoManager setAppNameAndAuthCode env.getUuidAuthCode()=").append(aVar.f());
                synchronized ("UUID_APP") {
                    a = a("UUID_APP");
                    if (a != null) {
                        edit = a.edit();
                        edit.putString(aVar.e(), str2);
                        edit.putString(aVar.f(), str3);
                        edit.commit();
                    }
                }
                TDMessage b = b(str, str2, str3);
                new StringBuilder("DeviceInfoManager saveUUIDLocal tdMsg.getUuid()=").append(b.getUuid());
                if (b != null) {
                    b(aVar.c(), b.getUuid());
                }
            } catch (JSONException e) {
                new StringBuilder("DeviceInfoManager saveUUIDLocal").append(e);
            }
        }
    }

    public final long b() {
        return a("HARD-INFO").getLong("updateTime", -1);
    }

    public final a b(com.taobao.dp.client.a aVar) {
        new StringBuilder("DeviceInfoManager getAppNameAndAuthCode env.getName()=").append(aVar.g());
        new StringBuilder("DeviceInfoManager getAppNameAndAuthCode env.getUuidAppName()=").append(aVar.e());
        new StringBuilder("DeviceInfoManager getAppNameAndAuthCode env.getUuidAuthCode()=").append(aVar.f());
        a aVar2 = new a();
        synchronized ("UUID_APP") {
            SharedPreferences a = a("UUID_APP");
            if (a != null) {
                aVar2.a = a.getString(aVar.e(), "");
                aVar2.b = a.getString(aVar.f(), "");
            }
        }
        if ("".equals(aVar2.a)) {
            aVar2.a = d.a(this.a);
        }
        new StringBuilder("DeviceInfoManager getAppNameAndAuthCode appName=").append(aVar2.a);
        new StringBuilder("DeviceInfoManager getAppNameAndAuthCode authCode=").append(aVar2.b);
        return aVar2;
    }

    public final void b(String str, String str2, String str3, com.taobao.dp.client.a aVar) {
        new StringBuilder("DeviceInfoManager compatUUIDCache env.getUuidAppName()=").append(aVar.e());
        new StringBuilder("DeviceInfoManager compatUUIDCache env.getUuidAuthCode()=").append(aVar.f());
        if (System.getString(this.a.getContentResolver(), aVar.c()) == null) {
            TDMessage b = b(str, str2, str3);
            if (b != null) {
                b(aVar.c(), b.getUuid());
            }
        }
    }
}
