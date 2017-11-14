package com.ttfm.android.sdk.http;

public class HttpCode {
    public static final int TTFM_HTTP_CODE_OK = 200;
    public static final int TTPOD_HTTP_CODE_ERR = 0;
    public static final int TTPOD_HTTP_CODE_OK = 1;

    public static boolean isOk(int i) {
        return i == 1 || i == 200;
    }

    public static String getTTPodHttpErrorText(int i) {
        return null;
    }

    public static String getTTFMHttpErrorText(int i) {
        return null;
    }
}
