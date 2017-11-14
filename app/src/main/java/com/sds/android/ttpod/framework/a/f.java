package com.sds.android.ttpod.framework.a;

import android.content.Context;
import com.sds.android.ttpod.framework.R;
import com.sds.android.ttpod.framework.base.BaseApplication;
import java.text.DecimalFormat;
import java.util.Locale;

/* FormatUtils */
public class f {
    private static final DecimalFormat a = new DecimalFormat(".#");

    public static String a(int i) {
        Context e = BaseApplication.e();
        String string;
        if (Locale.getDefault().equals(Locale.CHINA)) {
            string = e.getString(R.string.unit_ten_thousand);
            if (i < 10000) {
                return i + "";
            }
            if (i < 100000) {
                return a.format(((double) i) / 10000.0d) + string;
            }
            return (i / 10000) + string;
        }
        string = e.getString(R.string.unit_thousand);
        if (i < 1000) {
            return i + "";
        }
        return (i / 1000) + string;
    }

    public static String a(Long l) {
        try {
            return a(Integer.parseInt(l.toString()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return l.toString();
        }
    }
}
