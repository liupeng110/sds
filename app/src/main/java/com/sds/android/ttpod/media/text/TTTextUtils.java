package com.sds.android.ttpod.media.text;

import android.content.Context;
import android.text.TextUtils;
import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;
import com.sds.android.ttpod.media.R;

public class TTTextUtils {
    private static final String DEFAUTERROR = "0000";
    private static final String UNKNOWN = "unknown";

    private static native String nativeDecryptLyricKey(String str, String str2, int i);

    private static native String nativeDecryptPictureKey(int i, int i2, int i3, String str);

    static {
        try {
            System.loadLibrary("tttextutils");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static boolean equalsIgnoreCase(String str, String str2) {
        return str == str2 || ((str == null && str2.length() == 0) || ((str2 == null && str.length() == 0) || (str != null && str2 != null && str.length() == str2.length() && str.equalsIgnoreCase(str2))));
    }

    public static CharSequence validateString(Context context, CharSequence charSequence) {
        if (charSequence != null && (charSequence instanceof String)) {
            charSequence = ((String) charSequence).trim();
        }
        if (isValidateMediaString(charSequence)) {
            return charSequence;
        }
        return context.getString(R.string.media_unknown);
    }

    public static boolean isValidateMediaString(CharSequence charSequence) {
        return (TextUtils.isEmpty(charSequence) || TextUtils.equals("<unknown>", charSequence) || TextUtils.equals("unknown", charSequence)) ? false : true;
    }

    public static String decryptLyricKey(String str, String str2, int i) {
        String str3 = DEFAUTERROR;
        try {
            str3 = nativeDecryptLyricKey(str, str2, i);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            System.loadLibrary("tttextutils");
        }
        return str3;
    }

    public static String decryptPictureKey(int i, int i2, int i3, String str) {
        String str2 = DEFAUTERROR;
        try {
            str2 = nativeDecryptPictureKey(i, i2, i3, str);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            System.loadLibrary("tttextutils");
        }
        return str2;
    }

    public static String trim(String str) {
        return str != null ? str.trim() : null;
    }

    public static boolean isLetterOrDigit(char c) {
        return ('A' <= c && c <= 'Z') || (('a' <= c && c <= 'z') || ('0' <= c && c <= '9'));
    }

    public static String readableByte(long j) {
        if (j < ((long) 1024)) {
            return j + VersionUpdateConst.KEY_BAIDU_UPDATE_CATEGORY;
        }
        char charAt = "KMGTPE".charAt(((int) (Math.log((double) j) / Math.log((double) 1024))) - 1);
        return String.format("%.1f%cB", new Object[]{Double.valueOf(((double) j) / Math.pow((double) 1024, (double) r1)), Character.valueOf(charAt)});
    }

    public static String validateVisualString(String str) {
        return str == null ? null : str.replaceAll("([\\u0000-\\u001f\\uD7B0-\\uFEFF\\uFFF0-\\uFFFF]+)", "");
    }

    public static boolean containLetterDigitBlankChar(String str) {
        String str2 = ".*?[A-Za-z\\d]+.*?\\s+.*?[A-Za-z\\d]+.*?";
        return str != null && str.matches(".*?[A-Za-z\\d]+.*?\\s+.*?[A-Za-z\\d]+.*?");
    }

    public static String addDoubleQuotationWhenContainLetterDigitBlankChar(String str) {
        return containLetterDigitBlankChar(str) ? "\"" + str + "\"" : str;
    }
}
