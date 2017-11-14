package com.sds.android.ttpod.b;

import android.content.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* TimeUtils */
public class w {
    public static CharSequence a(Context context, long j) {
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - j;
        long j2 = toSeconds / 31536000;
        if (j2 != 0) {
            return j2 + "年前";
        }
        j2 = toSeconds / 2592000;
        if (j2 != 0) {
            return j2 + "月前";
        }
        j2 = toSeconds / 86400;
        if (j2 != 0) {
            return j2 + "天前";
        }
        j2 = toSeconds / 3600;
        if (j2 != 0) {
            return j2 + "小时前";
        }
        toSeconds /= 60;
        if (toSeconds != 0) {
            return toSeconds + "分钟前";
        }
        return "刚刚";
    }

    public static String a(long j) {
        try {
            Date date = new Date(TimeUnit.SECONDS.toMillis(j));
            Date date2 = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds(simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime()) - j;
            if (toSeconds > 86400 || toSeconds + 86400 < 0) {
                simpleDateFormat = new SimpleDateFormat("yyyy");
                if (simpleDateFormat.format(date2).equals(simpleDateFormat.format(date))) {
                    return new SimpleDateFormat("MM-dd E").format(date);
                }
                return new SimpleDateFormat("yyyy-MM-dd E").format(date);
            }
            String format = new SimpleDateFormat("E").format(date);
            if (toSeconds <= 0) {
                return "今天 " + format;
            }
            return "昨天 " + format;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(long j) {
        return new SimpleDateFormat("kk:mm").format(Long.valueOf(TimeUnit.SECONDS.toMillis(j)));
    }

    public static String a(long j, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }
}
