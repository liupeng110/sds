package com.sds.android.ttpod.b;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.SeekBar;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.a.a;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.framework.modules.skin.m;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.b.e;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.SlidingTabHost;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* ThemeUtils */
public class v {
    public static final ColorStateList a = ColorStateList.valueOf(-1888741);

    public static void a(a aVar, String str, int i, String str2) {
        if (aVar != null) {
            Drawable a = c.a(str);
            if (a != null) {
                aVar.b(a);
            } else {
                a(aVar, i, str2);
            }
        }
    }

    public static void a(a aVar, int i, String str) {
        if (i != 0) {
            aVar.e(i);
            ColorStateList colorStateList = (ColorStateList) c.a(str, false, true);
            if (colorStateList != null) {
                aVar.b(colorStateList);
            }
        }
    }

    public static void b(a aVar, String str, int i, String str2) {
        a(aVar, c.a(str), i, str2);
    }

    public static void a(a aVar, Drawable drawable, int i, String str) {
        if (aVar != null) {
            if (drawable != null) {
                aVar.a(drawable);
            } else if (i != 0) {
                aVar.a(i);
                ColorStateList colorStateList = (ColorStateList) c.a(str, false, true);
                if (colorStateList != null) {
                    aVar.a(colorStateList);
                }
            }
        }
    }

    public static Drawable a() {
        Drawable drawable = (Drawable) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_BACKGROUND, new Object[0]), Drawable.class);
        if (drawable == null) {
            return null;
        }
        return drawable;
    }

    public static String b() {
        return (String) b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.GET_SKIN_PROTOCOL_PATH, new Object[0]), String.class);
    }

    public static void a(View view, String str, String str2) {
        if (!c.a(view, str)) {
            c.a(view, str2);
        }
    }

    public static Drawable a(String str, int i) {
        Drawable a = c.a(str);
        if (a == null) {
            return BaseApplication.e().getResources().getDrawable(i);
        }
        return a;
    }

    public static void a(IconTextView iconTextView, int i, int i2, String str, String str2, String str3, String str4) {
        iconTextView.a(i, i2);
        ColorStateList colorStateList = (ColorStateList) c.a(str, false, true);
        ColorStateList colorStateList2 = (ColorStateList) c.a(str2, false, true);
        if (colorStateList == null || colorStateList2 == null) {
            colorStateList = (ColorStateList) c.a(str3, false, true);
            colorStateList2 = (ColorStateList) c.a(str4, false, true);
        }
        iconTextView.a(colorStateList, colorStateList2);
    }

    public static boolean a(SlidingTabHost slidingTabHost) {
        if (slidingTabHost == null) {
            return false;
        }
        slidingTabHost.setTextColor(c.c(ThemeElement.SUB_BAR_TEXT));
        slidingTabHost.setIndicatorColor(c.d(ThemeElement.SUB_BAR_INDICATOR));
        return c.a((View) slidingTabHost, -1, BaseApplication.e().getResources().getDimensionPixelSize(R.dimen.tab_label_height), ThemeElement.SUB_BAR_BACKGROUND);
    }

    public static boolean a(ArrayList<m> arrayList, ArrayList<m> arrayList2) {
        if (arrayList == null || arrayList2 == null || arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (!((m) arrayList.get(i)).a((m) arrayList2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int c() {
        int i = 0;
        try {
            return BaseApplication.e().getAssets().list("skin").length;
        } catch (IOException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static int d() {
        int intValue = com.sds.android.ttpod.framework.storage.a.a.a().v().intValue();
        if (intValue != 0) {
            return intValue;
        }
        intValue = c();
        com.sds.android.ttpod.framework.storage.a.a.a().a(Integer.valueOf(intValue));
        return intValue;
    }

    public static void a(View view, e eVar) {
        if (eVar != null) {
            int h = eVar.h();
            view.setPadding(h, h, h, h);
            return;
        }
        view.setPadding(0, 0, 0, 0);
    }

    public static int a(e eVar, int i) {
        int i2 = 0;
        if (eVar != null) {
            i2 = eVar.f();
        }
        if (i2 > 0) {
            return i2;
        }
        return i;
    }

    public static void a(View view, Drawable drawable) {
        if (view != null) {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void a(SeekBar seekBar) {
        Rect bounds = seekBar.getProgressDrawable().getBounds();
        Drawable a = c.a(ThemeElement.COMMON_PROGRESS_BAR);
        if (a == null) {
            a = new ColorDrawable(com.sds.android.ttpod.framework.modules.theme.b.c.a("#000000 0"));
        }
        seekBar.setBackgroundDrawable(a);
        a = c.a(ThemeElement.COMMON_PROGRESS_DRAWABLE);
        if (a == null) {
            a = new ColorDrawable(com.sds.android.ttpod.framework.modules.theme.b.c.a("#4eccf6 80"));
        }
        ClipDrawable clipDrawable = new ClipDrawable(a, 19, 1);
        a = c.a(ThemeElement.COMMON_SECOND_PROGRESS_DRAWABLE);
        if (a == null) {
            a = new ColorDrawable(com.sds.android.ttpod.framework.modules.theme.b.c.a("#4eccf6 20"));
        }
        ClipDrawable clipDrawable2 = new ClipDrawable(a, 19, 1);
        Drawable layerDrawable = new LayerDrawable(new Drawable[]{clipDrawable, clipDrawable2});
        layerDrawable.setId(0, 16908301);
        layerDrawable.setId(1, 16908303);
        layerDrawable.setBounds(bounds);
        seekBar.setProgressDrawable(layerDrawable);
        seekBar.setProgress(0);
        seekBar.setSecondaryProgress(0);
    }

    public static void a(IconTextView iconTextView, String str) {
        ColorStateList colorStateList = (ColorStateList) c.a(str, false, true);
        if (colorStateList != null) {
            iconTextView.setTextColor(colorStateList);
        }
    }

    public static void a(IconTextView iconTextView, String str, int i) {
        ColorStateList colorStateList = (ColorStateList) c.a(str, false, true);
        if (colorStateList != null && i > 0) {
            int defaultColor = colorStateList.getDefaultColor();
            iconTextView.setTextColor(Color.argb(i, Color.red(defaultColor), Color.green(defaultColor), Color.blue(defaultColor)));
        }
    }

    public static void a(IconTextView iconTextView, String str, String str2) {
        ColorStateList colorStateList = (ColorStateList) c.a(str, false, true);
        if (colorStateList == null) {
            a(iconTextView, str2);
        } else {
            iconTextView.setTextColor(colorStateList);
        }
    }

    public static void a(IconTextView iconTextView, String str, int i, String str2) {
        Drawable a = c.a(str);
        if (a == null) {
            iconTextView.setText(i);
            a(iconTextView, str2);
            return;
        }
        iconTextView.setImageDrawable(a);
    }

    public static void a(IconTextView iconTextView, int i, String str, String str2) {
        iconTextView.setText(i);
        a(iconTextView, str, str2);
    }

    public static void a(IconTextView iconTextView, int i, String str) {
        iconTextView.setText(i);
        a(iconTextView, str);
    }

    public static void a(View view) {
        view.findViewById(R.id.action_bar_controller).setVisibility(8);
        ((MarginLayoutParams) view.findViewById(R.id.activity_body).getLayoutParams()).topMargin = 0;
    }

    public static void b(View view) {
        c.a(view, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND);
        a((IconTextView) view.findViewById(R.id.no_media_icon), ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        a(view.findViewById(R.id.textview_load_failed), ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
    }

    public static AnimationDrawable e() {
        String str = "spectrum";
        AnimationDrawable animationDrawable = new AnimationDrawable();
        int i = 0;
        while (true) {
            StringBuilder append = new StringBuilder().append("spectrum").append(File.separator);
            int i2 = i + 1;
            Drawable drawable = (Drawable) c.a(append.append(i2).toString(), true, false);
            if (drawable == null) {
                animationDrawable.setOneShot(false);
                return animationDrawable;
            }
            animationDrawable.addFrame(drawable, 100);
            i = i2;
        }
    }
}
