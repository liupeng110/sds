package com.sds.android.ttpod.framework.modules.search.a;

import java.io.File;

/* LyrPicFileNameUtils */
public class b {
    public static String a(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        return Integer.toHexString(file.getName().hashCode()) + Integer.toHexString((int) file.length());
    }
}
