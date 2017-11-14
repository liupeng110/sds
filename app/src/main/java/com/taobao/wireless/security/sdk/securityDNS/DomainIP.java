package com.taobao.wireless.security.sdk.securityDNS;

import android.support.v4.view.MotionEventCompat;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;

public class DomainIP {
    private String a;
    private byte[][] b;

    public DomainIP(String str, byte[][] bArr) {
        this.a = str;
        this.b = bArr;
    }

    private String a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.b == null) {
            return "null";
        }
        for (int i = 0; i < this.b.length; i++) {
            byte[] bArr = this.b[i];
            stringBuilder.append(" ip[" + i + "]=");
            if (bArr != null) {
                for (int i2 = 0; i2 < bArr.length; i2++) {
                    stringBuilder.append(bArr[i2] & MotionEventCompat.ACTION_MASK);
                    if (i2 != bArr.length - 1) {
                        stringBuilder.append('.');
                    }
                }
            } else {
                stringBuilder.append("null");
            }
        }
        return stringBuilder.toString();
    }

    public String getDomainName() {
        return this.a;
    }

    public byte[][] getIPAddress() {
        return this.b;
    }

    public String toString() {
        return "DoaminIP [name=" + this.a + SelectCountryActivity.SPLITTER + a() + "]";
    }
}
