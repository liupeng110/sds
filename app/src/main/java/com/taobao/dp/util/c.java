package com.taobao.dp.util;

import android.support.v4.view.MotionEventCompat;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;

public final class c {
    public static String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & MotionEventCompat.ACTION_MASK);
            str = toHexString.length() == 1 ? str + FeedbackItem.STATUS_WAITING + toHexString : str + toHexString;
        }
        return str.toLowerCase();
    }
}
