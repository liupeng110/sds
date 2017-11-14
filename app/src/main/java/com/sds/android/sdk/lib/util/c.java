package com.sds.android.sdk.lib.util;

import com.sds.android.cloudapi.ttpod.data.FeedbackItem;
import java.text.SimpleDateFormat;
import java.util.Date;

/* DateUtils */
public class c {
    static final /* synthetic */ boolean a;
    private static final String[] b = new String[]{"yyyy-MM-dd", "yyyy-MM-dd-HH", "yyyy-MM-dd-HH-mm", "yyyy-MM-dd-HH-mm-ss"};

    static {
        boolean z;
        if (c.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        a = z;
    }

    public static String a(int i) {
        return a(new Date(), i, "");
    }

    public static String a(int i, String str) {
        return a(new Date(), i, str);
    }

    public static String a(long j, int i, String str) {
        return a(new Date(j), i, str);
    }

    private static String a(Date date, int i, String str) {
        return b(i, str).format(date);
    }

    private static SimpleDateFormat b(int i, String str) {
        if (a || (i >= 0 && i < b.length)) {
            String str2 = b[i];
            if (str != null) {
                str2 = str2.replace("-", str);
            }
            return new SimpleDateFormat(str2);
        }
        throw new AssertionError();
    }

    public static String a(long j) {
        String str;
        String str2;
        int i = 0;
        int i2 = (int) (j / 1000);
        if (i2 > 60) {
            i = i2 / 60;
            i2 %= 60;
        }
        if (i / 10 == 0) {
            str = FeedbackItem.STATUS_WAITING + i;
        } else {
            str = "" + i;
        }
        if (i2 / 10 == 0) {
            str2 = FeedbackItem.STATUS_WAITING + i2;
        } else if (i2 == 60) {
            str = "" + i + 1;
            str2 = "00";
        } else {
            str2 = "" + i2;
        }
        return str + ":" + str2;
    }
}
