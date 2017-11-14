package com.sds.android.ttpod.framework.a.b;

/* MVStatistic */
public class n {
    private static String a;

    public static void a() {
        w.a("mv", "show", "down-storm-dlg");
    }

    public static void b() {
        w.a("mv", "click", "mv_menu");
    }

    public static void a(boolean z) {
        w.a("mv", "click", "down-storm-dlg", z ? 1 : 0);
    }

    public static void b(boolean z) {
        w.a("mv", "app", "is-storm-valid", z ? 1 : 0);
    }

    public static void c(boolean z) {
        w.a("mv", "app", "storm-installed", z ? 1 : 0);
    }

    public static void a(String str) {
        a = str;
    }
}
