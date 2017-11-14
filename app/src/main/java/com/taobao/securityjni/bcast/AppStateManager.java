package com.taobao.securityjni.bcast;

import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AppStateManager {
    public static final String DNS_ACTION = "setaobao.bbox.DNS";
    public static final String EXTRA_DNS_IP = "IPAddress";
    public static final String EXTRA_DNS_LOCAL = "DNSinfolocal";
    public static final String EXTRA_DNS_NET = "DNSinfonet";
    public static final String EXTRA_RT = "RTinfo";
    public static final String EXTRA_SPITEP = "SPITEPinfo";
    private static final int IPV4_SIZE = 4;
    public static int Init = 0;
    public static final String RT_ACTION = "setaobao.bbox.RT";
    public static final int RT_VALUE_100_PERMISSION = 10;
    public static final int RT_VALUE_INVALID = -1;
    public static final int RT_VALUE_LIKELY_1 = 1;
    public static final int RT_VALUE_LIKELY_2 = 2;
    public static final int RT_VALUE_LIKELY_3 = 3;
    public static final int RT_VALUE_LIKELY_4 = 4;
    public static final int RT_VALUE_LIKELY_5 = 5;
    public static final int RT_VALUE_LIKELY_6 = 6;
    public static final int RT_VALUE_LIKELY_7 = 7;
    public static final int RT_VALUE_LIKELY_8 = 8;
    public static final int RT_VALUE_LIKELY_9 = 9;
    public static final int RT_VALUE_UNDETECTABLE = 0;
    public static final String SPITEP_ACTION = "setaobao.bbox.SPITEP";
    public static final int SPITEP_VALUE_NS_0 = 0;
    public static final int SPITEP_VALUE_NS_1 = 1;
    public static final int SPITEP_VALUE_NS_2 = 2;
    public static final int SPITEP_VALUE_NS_3 = 3;

    public static class DoaminIP {
        public byte[][] ip;
        public String name;

        private String IpToString() {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.ip == null) {
                return "null";
            }
            for (int i = 0; i < this.ip.length; i++) {
                byte[] bArr = this.ip[i];
                stringBuilder.append("ip[").append(i).append("]=");
                if (bArr != null) {
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        stringBuilder.append(bArr[i2] & MotionEventCompat.ACTION_MASK);
                        if (i2 != bArr.length - 1) {
                            stringBuilder.append(':');
                        }
                    }
                } else {
                    stringBuilder.append("null");
                }
                stringBuilder.append("  ");
            }
            return stringBuilder.toString();
        }

        public String toString() {
            return "DoaminIP [name=" + this.name + ", ip=" + IpToString() + "]";
        }
    }

    public static final ArrayList parserDomainIP(Intent intent) {
        ArrayList arrayList = null;
        Object byteArrayExtra = intent.getByteArrayExtra("IPAddress");
        if (byteArrayExtra == null) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        while (i < byteArrayExtra.length) {
            int i2 = byteArrayExtra[i] & MotionEventCompat.ACTION_MASK;
            String str = new String(byteArrayExtra, i + 1, i2);
            i2 = (i + i2) + 1;
            int i3 = byteArrayExtra[i2] & MotionEventCompat.ACTION_MASK;
            if (i3 + i2 > byteArrayExtra.length - 1) {
                break;
            }
            int i4 = i3 / 4;
            if (i4 > 0) {
                arrayList = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{i4, 4});
                for (i = 0; i < i4; i++) {
                    System.arraycopy(byteArrayExtra, (i2 + 1) + (i * 4), arrayList[i], 0, 4);
                }
            }
            i = (i2 + i3) + 1;
            DoaminIP doaminIP = new DoaminIP();
            doaminIP.name = str;
            doaminIP.ip = arrayList;
            arrayList2.add(doaminIP);
        }
        return arrayList2;
    }
}
