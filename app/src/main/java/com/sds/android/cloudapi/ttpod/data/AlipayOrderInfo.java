package com.sds.android.cloudapi.ttpod.data;

import com.b.a.a.c;

public class AlipayOrderInfo {
    private static final String KEY_CONTENT = "content";
    private static final String KEY_SIGN = "sign";
    @c(a = "content")
    private String mContent;
    @c(a = "sign")
    private String mSign;

    public String getContent() {
        return this.mContent;
    }

    public String getSign() {
        return this.mSign;
    }
}
