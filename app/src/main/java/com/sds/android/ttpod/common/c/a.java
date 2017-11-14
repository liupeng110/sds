package com.sds.android.ttpod.common.c;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import com.ttfm.android.sdk.entity.TTFMSongEntity;
import com.ttfm.android.sdk.utils.TTFMImageUtils;

/* DisplayUtils */
public class a {
    private static DisplayMetrics a = null;
    private static Configuration b = null;
    private static Typeface c;
    private static Typeface d;
    private static float e;

    public static void a(Context context) {
        a = context.getResources().getDisplayMetrics();
        b = context.getResources().getConfiguration();
        b(context);
        try {
            c = Typeface.createFromAsset(context.getAssets(), "fonts/IconFont.ttf");
            d = Typeface.createFromAsset(context.getAssets(), "fonts/Uni Sans Light.ttf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static float a() {
        return e;
    }

    public static void b(Context context) {
        e = (float) a(25);
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            e = (float) context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("status_bar_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(Context context, Configuration configuration) {
        a = context.getResources().getDisplayMetrics();
        b = configuration;
    }

    public static Typeface b() {
        return c;
    }

    public static Typeface c() {
        return d;
    }

    public static int d() {
        return a.widthPixels;
    }

    public static int e() {
        return a.heightPixels;
    }

    public static float f() {
        return a.density;
    }

    public static int g() {
        return a.densityDpi;
    }

    public static int a(int i) {
        return (int) ((i > 0 ? TTFMImageUtils.Middle_Scale : -0.5f) + (a.density * ((float) i)));
    }

    public static String h() {
        switch (i()) {
            case 120:
                return "_ldpi";
            case 160:
                return "_mdpi";
            case 240:
                return "_hdpi";
            case TTFMSongEntity.BITRATE_DEFAULT /*320*/:
                return "_xhdpi";
            case 480:
                return "_xxhdpi";
            default:
                return "";
        }
    }

    public static int i() {
        int i = a.densityDpi;
        if (i <= 120) {
            return 120;
        }
        if (i <= 160) {
            return 160;
        }
        if (i <= 240) {
            return 240;
        }
        return i <= TTFMSongEntity.BITRATE_DEFAULT ? TTFMSongEntity.BITRATE_DEFAULT : i <= 400 ? TTFMSongEntity.BITRATE_DEFAULT : 480;
    }
}
