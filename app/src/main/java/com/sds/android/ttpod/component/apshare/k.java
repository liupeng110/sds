package com.sds.android.ttpod.component.apshare;

import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Handler;
import android.os.Message;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.component.apshare.j.a;
import java.util.List;

/* WifiStateImpl */
public class k implements a {
    private Handler a;

    public k(Handler handler) {
        this.a = handler;
    }

    public void a(List<ScanResult> list) {
        if (this.a != null) {
            this.a.sendMessage(a(5, (Object) list));
        }
    }

    public void a(SupplicantState supplicantState, int i) {
        if (supplicantState != null) {
            if (supplicantState.equals(SupplicantState.SCANNING)) {
                if (this.a != null) {
                    this.a.sendEmptyMessage(4);
                }
            } else if (supplicantState.equals(SupplicantState.DISCONNECTED) && this.a != null) {
                this.a.sendEmptyMessage(8);
            }
            g.d("WifiStateImpl", "supplicant state: " + supplicantState.toString());
        }
    }

    public void a(boolean z) {
    }

    public void a(int i, int i2) {
        g.d("WifiStateImpl", "wifiStateChanged: " + i);
    }

    public void a(j jVar, int i) {
        if (jVar != null) {
            WifiConfiguration b = jVar.b();
            if (i != j.d || this.a == null) {
                if (this.a != null && i == j.e) {
                    this.a.sendEmptyMessage(13);
                } else if (this.a != null && i == j.f) {
                    this.a.sendEmptyMessage(15);
                } else if (this.a != null && i == j.c) {
                    this.a.sendEmptyMessage(11);
                } else if (this.a != null && i == j.b) {
                    this.a.sendEmptyMessage(12);
                }
            } else if (b != null) {
                jVar.a(b);
                this.a.sendMessage(a(14, b.SSID));
            }
        }
    }

    public void a(int i) {
    }

    public void a(String str) {
        if (this.a != null) {
            this.a.sendMessage(a(6, (Object) str));
        }
        g.d("WifiStateImpl", "connection preparing: " + str);
    }

    public void a(boolean z, String str) {
        if (this.a != null) {
            this.a.sendMessage(a(6, z ? "正在连接热点" + str : "热点" + str + "不能连接"));
        }
    }

    public void a(NetworkInfo networkInfo, WifiInfo wifiInfo) {
        if (wifiInfo.getSSID().contains("TTPODShare-")) {
            g.d("WifiStateImpl", "Connect Network Succeeded");
            if (this.a != null) {
                this.a.sendMessage(a(7, wifiInfo.getSSID()));
            }
        }
    }

    public void a(NetworkInfo networkInfo) {
        if (this.a != null) {
            this.a.sendEmptyMessage(9);
        }
        g.d("WifiStateImpl", "Connect Network Failed");
    }

    private Message a(int i, Object obj) {
        if (this.a == null) {
            return null;
        }
        Message obtainMessage = this.a.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = obj;
        return obtainMessage;
    }
}
