package com.sds.android.ttpod.b;

import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* LogcatUtils */
public class n {
    private static String a;

    public static void a() {
        List d = d();
        try {
            Runtime.getRuntime().exec((String[]) d.toArray(new String[d.size()]));
        } catch (IOException e) {
            g.a("LOGCAT", "something wrong with logcat");
        }
    }

    private static List<String> d() {
        a = h.a();
        List<String> arrayList = new ArrayList(Arrays.asList(new String[]{"logcat", "-dv", "time", "-f"}));
        arrayList.add(a);
        arrayList.add("&");
        return arrayList;
    }

    public static String b() {
        return a;
    }

    public static void c() {
        int length;
        int i = 0;
        String a = a.a();
        String[] list = new File(a).list();
        if (list != null) {
            length = list.length;
        } else {
            length = 0;
        }
        while (i < length) {
            e.h(a + File.separator + list[i]);
            i++;
        }
    }
}
