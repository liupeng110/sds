package com.sds.android.ttpod.b;

import com.sds.android.ttpod.framework.a;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/* FilenameUtils */
public class h {
    public static String a() {
        String a = a.a();
        return a + File.separator + b();
    }

    private static String b() {
        Date date = new Date();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String format = new DecimalFormat("0000").format((long) gregorianCalendar.get(1));
        String format2 = decimalFormat.format((long) (gregorianCalendar.get(2) + 1));
        String format3 = decimalFormat.format((long) gregorianCalendar.get(5));
        String format4 = decimalFormat.format((long) gregorianCalendar.get(11));
        String format5 = decimalFormat.format((long) gregorianCalendar.get(12));
        String format6 = decimalFormat.format((long) gregorianCalendar.get(13));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(format).append("-").append(format2).append("-").append(format3).append("-").append(format4).append("-").append(format5).append("-").append(format6);
        stringBuilder.append(".txt");
        return stringBuilder.toString();
    }
}
