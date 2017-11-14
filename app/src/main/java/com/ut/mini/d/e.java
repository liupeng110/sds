package com.ut.mini.d;

import java.util.Arrays;
import java.util.Comparator;

/* UTMCKeyArraySorter */
public class e {
    private static e a = null;
    private b b = new b();
    private a c = new a();

    /* UTMCKeyArraySorter */
    private class a implements Comparator<String> {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((String) obj, (String) obj2);
        }

        public int a(String str, String str2) {
            if (m.a(str) || m.a(str2)) {
                return 0;
            }
            return str.compareTo(str2);
        }
    }

    /* UTMCKeyArraySorter */
    private class b implements Comparator<String> {
        final /* synthetic */ e a;

        private b(e eVar) {
            this.a = eVar;
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((String) obj, (String) obj2);
        }

        public int a(String str, String str2) {
            if (m.a(str) || m.a(str2)) {
                return 0;
            }
            return str.compareTo(str2) * -1;
        }
    }

    private e() {
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (a == null) {
                a = new e();
            }
            eVar = a;
        }
        return eVar;
    }

    public String[] a(String[] strArr, boolean z) {
        Comparator comparator;
        if (z) {
            comparator = this.c;
        } else {
            comparator = this.b;
        }
        if (comparator == null || strArr == null || strArr.length <= 0) {
            return null;
        }
        Arrays.sort(strArr, comparator);
        return strArr;
    }
}
