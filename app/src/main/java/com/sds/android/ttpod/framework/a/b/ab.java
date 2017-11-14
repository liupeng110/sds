package com.sds.android.ttpod.framework.a.b;

import com.sds.android.ttpod.ThirdParty.update.VersionUpdateConst;

/* UpdateStatistic */
public class ab {
    private static boolean a = false;

    public static void a() {
        if (!a) {
            w.a("update", "update", "auto_update");
            a = true;
        }
    }

    public static void a(String str) {
        w.a("update", str, "dialog_show");
    }

    public static void b() {
        w.a("update", "update", "update_click");
    }

    public static void c() {
        w.a("update", "update", "cancel_click");
    }

    public static void d() {
        w.a("update", "360", "install_success");
    }

    public static void e() {
        w.a("update", VersionUpdateConst.TYPE_BAIDU_UPDATE, "install_success");
    }

    public static void f() {
        w.a("update", VersionUpdateConst.TYPE_WANDOUJIA_UPDATE, "install_success");
    }

    public static void g() {
        w.a("update", VersionUpdateConst.TYPE_HIAPK_UPDATE, "install_success");
    }
}
