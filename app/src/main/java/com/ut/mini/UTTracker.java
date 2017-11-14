package com.ut.mini;

import com.ut.mini.b.a;
import com.ut.mini.base.UTConstants.PrivateLogFields;
import com.ut.mini.base.UTLogFieldsScheme;
import com.ut.mini.core.c;
import com.ut.mini.d.m;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class UTTracker {
    private static Pattern c = Pattern.compile("(\\|\\||[\t\r\n])+");
    private String a = null;
    private Map<String, String> b = new HashMap();

    void setTrackId(String str) {
        this.a = str;
    }

    public synchronized void setGlobalProperty(String str, String str2) {
        if (m.a(str) || str2 == null) {
            a.c(1, "setGlobalProperty", "key is null or key is empty or value is null,please check it!");
        } else {
            this.b.put(str, str2);
        }
    }

    public synchronized String getGlobalProperty(String str) {
        String str2;
        if (str != null) {
            str2 = (String) this.b.get(str);
        } else {
            str2 = null;
        }
        return str2;
    }

    public synchronized void removeGlobalProperty(String str) {
        if (str != null) {
            if (this.b.containsKey(str)) {
                this.b.remove(str);
            }
        }
    }

    private static String _getStringNoBlankAndDLine(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        return c.matcher(str).replaceAll("");
    }

    private static String _checkField(String str) {
        return _getStringNoBlankAndDLine(str);
    }

    private Map<String, String> _checkMapFields(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        Iterator it = map.keySet().iterator();
        if (it != null) {
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str != null) {
                    hashMap.put(str, _checkField((String) map.get(str)));
                }
            }
        }
        return hashMap;
    }

    public void send(Map<String, String> map) {
        if (map != null) {
            Map hashMap = new HashMap();
            hashMap.putAll(this.b);
            hashMap.putAll(map);
            hashMap = _checkMapFields(hashMap);
            if (!m.a(this.a)) {
                hashMap.put(UTFields.TRACK_ID, this.a);
            }
            if (map.containsKey(PrivateLogFields.FLAG_USE_ALL_MAP_FIELDS)) {
                hashMap.remove(PrivateLogFields.FLAG_USE_ALL_MAP_FIELDS);
            } else {
                _dropAllIllegalKey(hashMap);
            }
            _translateFieldsName(hashMap);
            _fillReserve1Fields(hashMap);
            _fillReservesFields(hashMap);
            c.a().a(hashMap);
        }
    }

    private static void _dropAllIllegalKey(Map<String, String> map) {
        if (map != null) {
            if (map.containsKey(UTLogFieldsScheme.IMEI.toString())) {
                map.remove(UTLogFieldsScheme.IMEI.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.IMSI.toString())) {
                map.remove(UTLogFieldsScheme.IMSI.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.CARRIER.toString())) {
                map.remove(UTLogFieldsScheme.CARRIER.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.ACCESS.toString())) {
                map.remove(UTLogFieldsScheme.ACCESS.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.ACCESS_SUBTYPE.toString())) {
                map.remove(UTLogFieldsScheme.ACCESS_SUBTYPE.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.CHANNEL.toString())) {
                map.remove(UTLogFieldsScheme.CHANNEL.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.LL_USERNICK.toString())) {
                map.remove(UTLogFieldsScheme.LL_USERNICK.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.USERNICK.toString())) {
                map.remove(UTLogFieldsScheme.USERNICK.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.LL_USERID.toString())) {
                map.remove(UTLogFieldsScheme.LL_USERID.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.USERID.toString())) {
                map.remove(UTLogFieldsScheme.USERID.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.SDKVERSION.toString())) {
                map.remove(UTLogFieldsScheme.SDKVERSION.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.START_SESSION_TIMESTAMP.toString())) {
                map.remove(UTLogFieldsScheme.START_SESSION_TIMESTAMP.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.UTDID.toString())) {
                map.remove(UTLogFieldsScheme.UTDID.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.SDKTYPE.toString())) {
                map.remove(UTLogFieldsScheme.SDKTYPE.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.RESERVE2.toString())) {
                map.remove(UTLogFieldsScheme.RESERVE2.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.RESERVE3.toString())) {
                map.remove(UTLogFieldsScheme.RESERVE3.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.RESERVE4.toString())) {
                map.remove(UTLogFieldsScheme.RESERVE4.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.RESERVE5.toString())) {
                map.remove(UTLogFieldsScheme.RESERVE5.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.RESERVES.toString())) {
                map.remove(UTLogFieldsScheme.RESERVES.toString());
            }
            if (map.containsKey(UTLogFieldsScheme.RECORD_TIMESTAMP.toString())) {
                map.remove(UTLogFieldsScheme.RECORD_TIMESTAMP.toString());
            }
        }
    }

    private static void _translateFieldsName(Map<String, String> map) {
        if (map != null) {
            String str;
            if (map.containsKey(UTFields.OS)) {
                str = (String) map.get(UTFields.OS);
                map.remove(UTFields.OS);
                map.put(UTLogFieldsScheme.OS.toString(), str);
            }
            if (map.containsKey(UTFields.OS_VERSION)) {
                str = (String) map.get(UTFields.OS_VERSION);
                map.remove(UTFields.OS_VERSION);
                map.put(UTLogFieldsScheme.OSVERSION.toString(), str);
            }
        }
    }

    private static void _fillReserve1Fields(Map<String, String> map) {
        map.put(UTLogFieldsScheme.SDKTYPE.toString(), "mini");
    }

    private static void _fillReservesFields(Map<String, String> map) {
        Map hashMap = new HashMap();
        if (map.containsKey(UTFields.TRACK_ID)) {
            String str = (String) map.get(UTFields.TRACK_ID);
            map.remove(UTFields.TRACK_ID);
            if (!m.a(str)) {
                hashMap.put("_tkid", str);
            }
        }
        if (hashMap.size() > 0) {
            map.put(UTLogFieldsScheme.RESERVES.toString(), m.a(hashMap));
        }
        if (!map.containsKey(UTLogFieldsScheme.PAGE.toString())) {
            map.put(UTLogFieldsScheme.PAGE.toString(), "UT");
        }
    }
}
