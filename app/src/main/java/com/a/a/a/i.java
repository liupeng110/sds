package com.a.a.a;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import java.security.MessageDigest;

/* MD5 */
class i {
    private static MessageDigest a;

    static {
        a = null;
        try {
            a = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
        }
    }

    static byte[] a(byte[] bArr) {
        byte[] digest;
        synchronized (a) {
            digest = a.digest(bArr);
        }
        return digest;
    }

    static String b(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte toHexString : bArr) {
            String toUpperCase = Integer.toHexString(toHexString).toUpperCase();
            if (toUpperCase.length() == 1) {
                toUpperCase = new StringBuilder(FeedbackItem.STATUS_WAITING).append(toUpperCase).toString();
            } else if (toUpperCase.length() > 2) {
                toUpperCase = toUpperCase.substring(6, 8);
            }
            stringBuffer.append(toUpperCase);
            stringBuffer.append("");
        }
        return stringBuffer.toString();
    }
}
