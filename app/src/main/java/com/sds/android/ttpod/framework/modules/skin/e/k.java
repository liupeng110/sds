package com.sds.android.ttpod.framework.modules.skin.e;

import java.util.Collections;
import java.util.List;

/* LyricUtils */
public final class k {
    private static final f a = new f(0, "", 0, 0, 0);

    public static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean a(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z');
    }

    public static int a(List<? extends m> list, long j) {
        a.a(j);
        int binarySearch = Collections.binarySearch(list, a);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        int size = list.size();
        return binarySearch >= size ? size - 1 : binarySearch;
    }

    public static void a(List<? extends m> list, i iVar) {
        for (m a : list) {
            a.a(iVar);
        }
    }
}
