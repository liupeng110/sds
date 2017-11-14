package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.sdk.lib.util.EnvironmentUtils.a;

/* UrlList */
public final class ad {
    public static final String a = (a() + "/recomm");
    public static final String b = (a() + "/module/category");
    public static final String c = (a() + "/category/sub_category");
    public static final String d = (a() + "/recomm/song_list");
    public static final String e = (a + "/recomm_modules");
    public static final String f = (a + "/optional_channels");

    public static String a() {
        if (a.l()) {
            return "http://pre.online.dongting.com";
        }
        return "http://online.dongting.com";
    }
}
