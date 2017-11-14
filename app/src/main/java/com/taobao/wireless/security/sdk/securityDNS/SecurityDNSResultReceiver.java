package com.taobao.wireless.security.sdk.securityDNS;

import android.content.BroadcastReceiver;
import android.support.v4.view.MotionEventCompat;
import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class SecurityDNSResultReceiver extends BroadcastReceiver {
    public static final String DNS_ACTION = "setaobao.bbox.DNS";
    public static final String EXTRA_DNS_IP = "IPAddress";
    public static final String EXTRA_DNS_LOCAL = "DNSinfolocal";
    public static final String EXTRA_DNS_NET = "DNSinfonet";

    public static final ArrayList parserDomainIP(byte[] bArr) {
        ArrayList arrayList = null;
        if (bArr == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (i < bArr.length) {
            int i2 = bArr[i] & MotionEventCompat.ACTION_MASK;
            String trim = new String(bArr, i + 1, i2).trim();
            i2 = (i + i2) + 1;
            int i3 = bArr[i2] & MotionEventCompat.ACTION_MASK;
            if (i3 + i2 > bArr.length - 1) {
                break;
            }
            int i4 = i3 / 4;
            if (i4 > 0) {
                arrayList = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i4, 4});
                for (i = 0; i < i4; i++) {
                    System.arraycopy(bArr, (i2 + 1) + (i * 4), arrayList[i], 0, 4);
                }
            }
            i = (i2 + i3) + 1;
            arrayList2.add(new DomainIP(trim, arrayList));
        }
        return arrayList2;
    }
}
