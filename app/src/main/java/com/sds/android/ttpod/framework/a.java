package com.sds.android.ttpod.framework;

import com.sds.android.sdk.lib.util.EnvironmentUtils.d;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.old.MediaStore;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.Medias;
import java.io.File;

/* TTPodConfig */
public class a {
    private static boolean a = false;
    private static String b = (c + File.separator + "cache");
    private static String c = (d.b() + File.separator + MediaStore.AUTHORITY);

    public static void a(boolean z) {
        if (z) {
            e.f(i());
            e.f(j());
            e.f(l());
            e.e(l() + File.separator + ".nomedia");
            e.f(m());
            e.f(o());
            e.f(g());
            e.e(g() + File.separator + ".nomedia");
            e.f(k());
            e.f(s());
            e.f(t());
            e.f(u());
            e.f(f());
            e.f(e());
            e.f(n());
            e.e(n() + File.separator + ".nomedia");
            e.f(a());
            e.f(h());
        }
    }

    public static String a() {
        return d.b() + File.separator + "ttpod/_LOG";
    }

    public static boolean b() {
        return a;
    }

    public static void c() {
        a = true;
    }

    public static String d() {
        return b;
    }

    public static String e() {
        return c + File.separator + "effect";
    }

    public static String f() {
        return b + File.separator + ".effect";
    }

    public static String g() {
        return b + File.separator + Medias.URI_PATH;
    }

    public static String h() {
        return b + File.separator + "tmpMedia";
    }

    public static String i() {
        return b + File.separator + "image";
    }

    public static String j() {
        return b + File.separator + "object";
    }

    public static String k() {
        return b + File.separator + ".tmp";
    }

    public static String l() {
        return c + File.separator + "splash";
    }

    public static String m() {
        return c + File.separator + "MyMusics";
    }

    public static String n() {
        return c + File.separator + "bkgs";
    }

    public static String o() {
        return c + File.separator + "skin";
    }

    public static String p() {
        return c + File.separator + "bkgs";
    }

    public static String q() {
        return "bkgs";
    }

    public static String r() {
        return c + File.separator + "song";
    }

    public static String s() {
        return c + File.separator + "lyric";
    }

    public static String t() {
        return c + File.separator + "artist";
    }

    public static String u() {
        return b + File.separator + "embed";
    }

    public static String v() {
        return c + File.separator + "art";
    }

    public static String w() {
        return c + File.separator + "Equalizer";
    }

    public static String x() {
        return c + File.separator + "app";
    }

    public static String y() {
        return c + File.separator + "landscape";
    }

    public static String z() {
        return c + File.separator + "mv";
    }

    public static String A() {
        return z() + File.separator + ".cache";
    }

    public static String B() {
        return c + File.separator + "image";
    }

    public static String C() {
        return c;
    }

    public static String a(Long l) {
        return b.bg() + File.separator + l;
    }

    public static String D() {
        return h() + File.separator + "audio" + ".tmp";
    }
}
