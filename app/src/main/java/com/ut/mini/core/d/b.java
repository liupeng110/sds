package com.ut.mini.core.d;

import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.base.c;
import com.ut.mini.core.a;
import com.ut.mini.d.m;
import java.util.HashMap;
import java.util.Map;

/* UTMCLogAssemble */
public class b {
    private static long s_default_session_timestamp = System.currentTimeMillis();

    private static String _checkField(String str) {
        if (m.a(str)) {
            return "-";
        }
        return str;
    }

    public static String assemble(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            if (!m.a(c.a().i())) {
                map.put(UTLogFieldsScheme.USERNICK.toString(), c.a().i());
            }
            if (!m.a(c.a().f())) {
                map.put(UTLogFieldsScheme.LL_USERNICK.toString(), c.a().f());
            }
            if (!m.a(c.a().j())) {
                map.put(UTLogFieldsScheme.USERID.toString(), c.a().j());
            }
            if (!m.a(c.a().g())) {
                map.put(UTLogFieldsScheme.LL_USERID.toString(), c.a().g());
            }
            if (!map.containsKey(UTLogFieldsScheme.SDKVERSION.toString())) {
                map.put(UTLogFieldsScheme.SDKVERSION.toString(), c.a().c().getFullSDKVersion());
            }
            if (!map.containsKey(UTLogFieldsScheme.APPKEY.toString())) {
                map.put(UTLogFieldsScheme.APPKEY.toString(), c.a().l());
            }
            if (!m.a(c.a().h())) {
                map.put(UTLogFieldsScheme.CHANNEL.toString(), c.a().h());
            }
            if (!m.a(c.a().b())) {
                map.put(UTLogFieldsScheme.APPVERSION.toString(), c.a().b());
            }
            if (!map.containsKey(UTLogFieldsScheme.RECORD_TIMESTAMP.toString())) {
                map.put(UTLogFieldsScheme.RECORD_TIMESTAMP.toString(), "" + System.currentTimeMillis());
            }
            if (!map.containsKey(UTLogFieldsScheme.START_SESSION_TIMESTAMP.toString())) {
                map.put(UTLogFieldsScheme.START_SESSION_TIMESTAMP.toString(), "" + s_default_session_timestamp);
            }
            Map a = a.a(c.a().k());
            if (a != null) {
                String str;
                Object format;
                Map hashMap = new HashMap();
                hashMap.putAll(a);
                for (String str2 : map.keySet()) {
                    if (!(str2.equals(UTLogFieldsScheme.BRAND.toString()) || str2.equals(UTLogFieldsScheme.DEVICE_MODEL.toString()) || str2.equals(UTLogFieldsScheme.RESOLUTION.toString()) || str2.equals(UTLogFieldsScheme.OS.toString()) || str2.equals(UTLogFieldsScheme.OSVERSION.toString()) || str2.equals(UTLogFieldsScheme.UTDID.toString()))) {
                        str = (String) map.get(str2);
                        if (!(m.a(str2) || str == null)) {
                            hashMap.put(str2, str);
                        }
                    }
                }
                String str22 = (String) hashMap.get(UTLogFieldsScheme.RESERVES.toString());
                if (((String) hashMap.get("_mac")) != null) {
                    if (str22 != null) {
                        str22 = String.format("%s,_mac=%s", new Object[]{str22, str});
                    } else {
                        str22 = String.format("_mac=%s", new Object[]{str});
                    }
                    hashMap.remove("_mac");
                }
                str = str22;
                if (((String) hashMap.get(com.ut.mini.base.b.DEVICE_ID.toString())) != null) {
                    if (str != null) {
                        format = String.format("%s,_did=%s", new Object[]{str, str22});
                    } else {
                        format = String.format("_did=%s", new Object[]{str22});
                    }
                    hashMap.remove(com.ut.mini.base.b.DEVICE_ID.toString());
                } else {
                    str22 = str;
                }
                str = c.a(c.a().k());
                if (str != null) {
                    if (hashMap.containsKey(UTLogFieldsScheme.UTDID.toString()) && str.equals(hashMap.get(UTLogFieldsScheme.UTDID.toString()))) {
                        str = "utdid";
                    }
                    if (format != null) {
                        format = String.format("%s,_umid=%s", new Object[]{format, str});
                    } else {
                        format = String.format("_umid=%s", new Object[]{str});
                    }
                }
                if (format != null) {
                    hashMap.put(UTLogFieldsScheme.RESERVES.toString(), format);
                }
                return assembleWithFullFields(hashMap);
            }
        }
        return null;
    }

    public static String assembleWithFullFields(Map<String, String> map) {
        String a;
        StringBuffer stringBuffer = new StringBuffer();
        for (UTLogFieldsScheme uTLogFieldsScheme : UTLogFieldsScheme.values()) {
            if (uTLogFieldsScheme == UTLogFieldsScheme.ARGS) {
                break;
            }
            if (map.containsKey(uTLogFieldsScheme.toString())) {
                a = m.a(map.get(uTLogFieldsScheme.toString()));
                map.remove(uTLogFieldsScheme.toString());
            } else {
                a = null;
            }
            stringBuffer.append(_checkField(a)).append("||");
        }
        Object obj = 1;
        if (map.containsKey(UTLogFieldsScheme.ARGS.toString())) {
            stringBuffer.append(_checkField(m.a(map.get(UTLogFieldsScheme.ARGS.toString()))));
            map.remove(UTLogFieldsScheme.ARGS.toString());
            obj = null;
        }
        Object obj2 = obj;
        for (String a2 : map.keySet()) {
            String a3;
            if (map.containsKey(a2)) {
                a3 = m.a(map.get(a2));
            } else {
                a3 = null;
            }
            if (obj2 != null) {
                if ("StackTrace".equals(a2)) {
                    stringBuffer.append("StackTrace=====>").append(a3);
                } else {
                    stringBuffer.append(_checkField(a2)).append("=").append(a3);
                }
                obj = null;
            } else if ("StackTrace".equals(a2)) {
                stringBuffer.append(SelectCountryActivity.SPLITTER).append("StackTrace=====>").append(a3);
                obj = obj2;
            } else {
                stringBuffer.append(SelectCountryActivity.SPLITTER).append(_checkField(a2)).append("=").append(a3);
                obj = obj2;
            }
            obj2 = obj;
        }
        a2 = stringBuffer.toString();
        if (m.a(a2) || !a2.endsWith("||")) {
            return a2;
        }
        return a2 + "-";
    }

    public static Map<String, String> disassemble(String str) {
        int i = 0;
        if (m.a(str)) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        String[] split = str.split("\\|\\|");
        if (split == null || split.length <= 0) {
            return hashMap;
        }
        UTLogFieldsScheme[] values = UTLogFieldsScheme.values();
        int length = values.length;
        int i2 = 0;
        while (i < length) {
            UTLogFieldsScheme uTLogFieldsScheme = values[i];
            if (i2 < split.length && split[i2] != null) {
                hashMap.put(uTLogFieldsScheme.toString(), split[i2]);
            }
            i2++;
            i++;
        }
        return hashMap;
    }
}
