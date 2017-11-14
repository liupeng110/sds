package com.ut.mini.internal;

import com.ut.mini.UTHitBuilders.UTHitBuilder;
import com.ut.mini.d.m;
import java.util.Map;

public class UTOriginalCustomHitBuilder extends UTHitBuilder {
    public UTOriginalCustomHitBuilder(String str, int i, String str2, String str3, String str4, Map<String, String> map) {
        if (!m.a(str)) {
            super.setProperty(UTHitBuilder.FIELD_PAGE, str);
        }
        super.setProperty(UTHitBuilder.FIELD_EVENT_ID, "" + i);
        if (!m.a(str2)) {
            super.setProperty(UTHitBuilder.FIELD_ARG1, str2);
        }
        if (!m.a(str3)) {
            super.setProperty(UTHitBuilder.FIELD_ARG2, str3);
        }
        if (!m.a(str4)) {
            super.setProperty(UTHitBuilder.FIELD_ARG3, str4);
        }
        super.setProperties(map);
    }
}
