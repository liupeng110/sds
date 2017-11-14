package com.taobao.wireless.security.adapter.securityDNS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.taobao.wireless.security.adapter.a.b;
import com.taobao.wireless.security.adapter.a.c;
import com.ttfm.android.sdk.http.HttpChannelSongListGetV2;

public class SecurityDNSAdapter {
    public static final String SHARED_PREFERENCE_NAME = "SecurityDataPreferences";
    private Context a = null;

    public SecurityDNSAdapter(Context context) {
        this.a = context;
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        c a = b.a(str);
        return (a == null || a.a() != 0) ? null : (String) a.b();
    }

    private void a(String str, long j) {
        SharedPreferences sharedPreferences = this.a.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            edit.putString("routing_table_key", str);
            edit.putLong("routing_table_timestamp_key", j);
            edit.commit();
        }
    }

    public void SendDNSBroadCast(int i, byte[] bArr) {
        Intent intent = new Intent();
        intent.setAction("setaobao.bbox.DNS");
        intent.putExtra("DNSinfolocal", 0);
        intent.putExtra("DNSinfonet", i);
        if (bArr != null) {
            intent.putExtra("IPAddress", bArr);
        }
        this.a.sendBroadcast(intent);
    }

    public void checkSecurity(String[] strArr) {
        checkSecurityDNS(strArr);
    }

    public native void checkSecurityDNS(String[] strArr);

    public String getDNSRoutingTable(String str) {
        long j = 0;
        String str2 = null;
        SharedPreferences sharedPreferences = this.a.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        if (sharedPreferences != null) {
            str2 = sharedPreferences.getString("routing_table_key", null);
        }
        String a;
        if (str2 != null) {
            sharedPreferences = this.a.getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
            if (sharedPreferences != null) {
                j = sharedPreferences.getLong("routing_table_timestamp_key", 0);
            }
            if (str2 != null) {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - j > HttpChannelSongListGetV2.CACHE_TIME) {
                    a = a(str);
                    if (a != null) {
                        a(a, currentTimeMillis);
                        return a;
                    }
                }
            }
            return str2;
        }
        a = a(str);
        if (a == null) {
            return a;
        }
        a(a, System.currentTimeMillis());
        return a;
    }

    public native boolean initSecurityDNSComponent();

    public boolean initialize() {
        return initSecurityDNSComponent();
    }
}
