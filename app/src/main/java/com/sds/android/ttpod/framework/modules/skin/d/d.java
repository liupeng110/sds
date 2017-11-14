package com.sds.android.ttpod.framework.modules.skin.d;

import android.text.TextUtils;
import com.sds.android.cloudapi.ttpod.data.FeedbackItem;

/* DateTimeUtils */
public final class d {
    public static long a(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split(":");
        if (split == null || split.length != 3) {
            return b(str);
        }
        try {
            int parseInt;
            int parseInt2 = Integer.parseInt(split[0]);
            int parseInt3 = Integer.parseInt(split[1]);
            String str2 = split[2];
            int indexOf = str2.indexOf(46);
            if (indexOf < 0) {
                parseInt = Integer.parseInt(str2);
            } else {
                parseInt = Integer.parseInt(str2.substring(0, indexOf));
                i = c(str2.substring(indexOf + 1));
            }
            return (long) (i + ((parseInt + (((parseInt2 * 60) + parseInt3) * 60)) * 1000));
        } catch (Exception e) {
            return 0;
        }
    }

    private static long b(String str) {
        int i = 0;
        try {
            int indexOf = str.indexOf(":");
            if (indexOf > 0) {
                int i2;
                int i3;
                String trim = str.substring(0, indexOf).trim();
                if (trim.length() <= 0 || trim.charAt(0) != '-') {
                    i2 = 0;
                } else {
                    trim = trim.substring(1);
                    i2 = 1;
                }
                long parseLong = Long.parseLong(trim);
                int i4 = indexOf + 1;
                int indexOf2 = str.indexOf(46, i4);
                if (indexOf2 < 0) {
                    indexOf = str.indexOf(":", i4);
                } else {
                    indexOf = indexOf2;
                }
                if (indexOf > 0) {
                    String str2;
                    trim = str.substring(i4, indexOf).trim();
                    String str3;
                    if (trim.length() <= 0 || trim.charAt(0) != '-') {
                        str3 = trim;
                        indexOf2 = i2;
                        str2 = str3;
                    } else {
                        i2 = i2 == 0 ? 1 : 0;
                        str3 = trim.substring(1);
                        indexOf2 = i2;
                        str2 = str3;
                    }
                    i2 = Integer.parseInt(str2);
                    String trim2 = str.substring(indexOf + 1).trim();
                    i4 = trim2.length();
                    if (i4 > 0) {
                        String substring;
                        if (trim2.charAt(0) == '-') {
                            if (indexOf2 == 0) {
                                indexOf2 = 1;
                            } else {
                                indexOf2 = 0;
                            }
                            substring = trim2.substring(1);
                            int i5 = i4 - 1;
                        } else {
                            substring = trim2;
                        }
                        i = c(substring);
                    }
                    i3 = indexOf2;
                    indexOf2 = i2;
                } else {
                    trim = str.substring(i4).trim();
                    if (trim.length() > 0 && trim.charAt(0) == '-') {
                        if (i2 == 0) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        trim = trim.substring(1);
                    }
                    indexOf2 = Integer.parseInt(trim);
                    i3 = i2;
                }
                long j = ((long) i) + (((60 * parseLong) + ((long) indexOf2)) * 1000);
                if (i3 != 0) {
                    return -j;
                }
                return j;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return Long.MIN_VALUE;
    }

    private static int c(String str) {
        int length = str.length();
        int parseInt = length > 0 ? Integer.parseInt(str) : 0;
        if (length == 1) {
            return parseInt * 100;
        }
        if (length == 2) {
            return parseInt * 10;
        }
        return parseInt;
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

    public static String a(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            str = ":";
        }
        int i = (int) (j / 1000);
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        if ((i / 60) / 60 > 0) {
            return String.format("%02d" + str + "%02d" + str + "%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i2)});
        }
        return String.format("%02d" + str + "%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
    }
}
