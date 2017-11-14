package com.sds.android.ttpod.framework.modules.theme;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.b.e;
import java.lang.reflect.Field;

/* ThemeManager */
public class c {
    private static com.sds.android.ttpod.framework.modules.theme.b.b a = null;

    /* ThemeManager */
    public interface b {
        void onThemeLoaded();
    }

    /* ThemeManager */
    public static abstract class a {
        private String a = null;

        protected abstract void a();

        private boolean b(String str) {
            return !m.a(this.a, str);
        }

        public final void a(String str) {
            if (str == null) {
                throw new NullPointerException("Theme file path can't be null.");
            } else if (b(str)) {
                this.a = str;
                a();
            }
        }
    }

    public static ColorDrawable a() {
        if (a == null) {
            e();
        }
        return a.b();
    }

    public static ColorDrawable b() {
        if (a == null) {
            e();
        }
        return a.c();
    }

    public static ColorDrawable c() {
        if (a == null) {
            e();
        }
        return a.d();
    }

    public static boolean a(View view, String str) {
        if (view == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the theme id can not be empty");
        }
        String parsePanelId = ThemeElement.parsePanelId(str);
        String parseElementId = ThemeElement.parseElementId(str);
        if (ThemeElement.isTextElementId(parseElementId)) {
            return a(view, parsePanelId, parseElementId);
        }
        if ((view instanceof ListView) && parseElementId.equals("Separator")) {
            return b(view, parsePanelId, parseElementId);
        }
        if ((view instanceof ImageView) && ThemeElement.isImageElementId(parseElementId)) {
            return a((ImageView) view, parsePanelId, parseElementId);
        }
        if ((view instanceof ProgressBar) && parseElementId.equals("ProgressBar")) {
            return a((ProgressBar) view);
        }
        return c(view, parsePanelId, parseElementId);
    }

    public static Drawable a(String str) {
        Object a = a(str, true, true);
        return a instanceof Drawable ? (Drawable) a : null;
    }

    public static e b(String str) {
        return a(ThemeElement.parsePanelId(str), ThemeElement.parseElementId(str), false);
    }

    public static boolean a(View view, int i, int i2, String str) {
        if (view == null) {
            return false;
        }
        String parsePanelId = ThemeElement.parsePanelId(str);
        String parseElementId = ThemeElement.parseElementId(str);
        e b = b(str);
        if (b != null) {
            int e = b.e();
            int f = b.f();
            if (e > 0) {
                i = e;
            }
            if (f > 0) {
                i2 = f;
            }
        }
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = (view.getPaddingLeft() + i) + view.getPaddingRight();
            layoutParams.height = (view.getPaddingTop() + i2) + view.getPaddingBottom();
            view.setLayoutParams(layoutParams);
        }
        return c(view, parsePanelId, parseElementId);
    }

    public static ColorStateList c(String str) {
        Object a = a(str, false, true);
        return a instanceof ColorStateList ? (ColorStateList) a : null;
    }

    public static int d(String str) {
        Object a = a(str, false, true);
        if (a instanceof ColorStateList) {
            return ((ColorStateList) a).getDefaultColor();
        }
        if (a instanceof ColorDrawable) {
            return a((ColorDrawable) a);
        }
        if (a instanceof StateListDrawable) {
            Drawable current = ((StateListDrawable) a).getCurrent();
            if (current instanceof ColorDrawable) {
                return a((ColorDrawable) current);
            }
        }
        return 0;
    }

    @TargetApi(11)
    public static int a(ColorDrawable colorDrawable) {
        if (j.c()) {
            return colorDrawable.getColor();
        }
        try {
            Field declaredField = ColorDrawable.class.getDeclaredField("mState");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(colorDrawable);
            Field declaredField2 = obj.getClass().getDeclaredField("mUseColor");
            declaredField2.setAccessible(true);
            return declaredField2.getInt(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Object a(String str, boolean z, boolean z2) {
        return a(ThemeElement.parsePanelId(str), ThemeElement.parseElementId(str), z, z2);
    }

    private static Object a(String str, String str2, boolean z, boolean z2) {
        if (a == null) {
            e();
        }
        Object obj = null;
        if (a == null) {
            g.c("Theme", "no theme in the system");
            return null;
        } else if (str2.equals(ThemeElement.BACKGROUND_MASK)) {
            return a.b();
        } else {
            if (str2.equals(ThemeElement.STATUS_BAR_BACKGROUND)) {
                return a.c();
            }
            if (str2.equals(ThemeElement.NAVIGATION_BAR_BACKGROUND)) {
                return a.d();
            }
            if (z) {
                obj = a.b(str, str2);
            }
            if (obj != null) {
                return obj;
            }
            obj = a.c(str, str2);
            if (z2 && obj == null && !str.equals(ThemeElement.PANEL_COMMON)) {
                return a.a(str, str2);
            }
            return obj;
        }
    }

    private static e a(String str, String str2, boolean z) {
        if (a == null) {
            e();
        }
        if (a != null) {
            return a.d(str, str2);
        }
        g.c("Theme", "no theme in the system");
        return null;
    }

    public static void a(View view, Drawable drawable) {
        a(view, drawable, false);
    }

    public static void a(View view, Drawable drawable, boolean z) {
        if (view == null) {
            g.c("ThemeManager", "ThemeManager.setViewBackground view is null");
        } else if (z || drawable != null) {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void b(View view, Drawable drawable) {
        if (view != null && drawable != null) {
            Drawable c = c(view, drawable);
            if (c != null) {
                view.setBackgroundDrawable(c);
            } else {
                view.setBackgroundDrawable(drawable);
            }
        }
    }

    public static com.sds.android.ttpod.framework.modules.skin.d.b c(View view, Drawable drawable) {
        if (!(drawable instanceof BitmapDrawable)) {
            return null;
        }
        com.sds.android.ttpod.framework.modules.skin.d.b bVar = new com.sds.android.ttpod.framework.modules.skin.d.b(BaseApplication.e().getResources(), ((BitmapDrawable) drawable).getBitmap());
        bVar.a((float) view.getWidth(), (float) view.getHeight());
        return bVar;
    }

    public static void d() {
        if (a != null) {
            a.a();
        }
        a = null;
    }

    private static void e() {
        String Y = com.sds.android.ttpod.framework.storage.environment.b.Y();
        if (TextUtils.isEmpty(Y)) {
            Y = com.sds.android.ttpod.framework.storage.environment.b.Z();
        }
        a = com.sds.android.ttpod.framework.modules.theme.b.b.a(new com.sds.android.ttpod.framework.modules.skin.j(Y));
    }

    private static boolean a(View view, String str, String str2) {
        if (view instanceof TextView) {
            ColorStateList colorStateList = (ColorStateList) a(str, str2, false, true);
            if (colorStateList != null) {
                ((TextView) view).setTextColor(colorStateList);
                return true;
            }
            g.c("Theme", "colorStateList is null " + str + " " + str2);
            return false;
        }
        throw new IllegalArgumentException("the view is not TextView, it can't supported to set text color");
    }

    private static boolean b(View view, String str, String str2) {
        Drawable drawable = (Drawable) a(str, str2, false, true);
        if (drawable != null) {
            ((ListView) view).setDivider(drawable);
            ((ListView) view).setDividerHeight(1);
            return true;
        }
        g.c("Theme", "divider is null " + str + " " + str2);
        return false;
    }

    private static boolean a(ImageView imageView, String str, String str2) {
        Drawable drawable = (Drawable) a(str, str2, true, true);
        if (drawable == null) {
            return false;
        }
        imageView.setImageDrawable(drawable);
        return true;
    }

    private static boolean a(ProgressBar progressBar) {
        boolean z = false;
        Drawable progressDrawable = progressBar.getProgressDrawable();
        if (progressDrawable == null || !(progressDrawable instanceof LayerDrawable)) {
            return false;
        }
        boolean z2;
        LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
        Drawable e = e("Progress");
        Drawable e2 = e("SecondProgress");
        Drawable a = a("ProgressBar");
        if (e != null) {
            layerDrawable.setDrawableByLayerId(16908301, e);
            z = true;
        }
        if (e2 != null) {
            layerDrawable.setDrawableByLayerId(16908303, e2);
            z2 = true;
        } else {
            z2 = z;
        }
        if (a == null) {
            return z2;
        }
        a((View) progressBar, a);
        return true;
    }

    private static Drawable e(String str) {
        Drawable a = a(str);
        if (a != null) {
            return new ClipDrawable(a, 3, 1);
        }
        return null;
    }

    private static boolean a(View view, String str, String str2, boolean z) {
        Drawable drawable = (Drawable) a(str, str2, true, true);
        if (drawable != null || z) {
            a(view, drawable, z);
            return true;
        }
        g.c("Theme", "background is null: " + str + " " + str2);
        return false;
    }

    private static boolean c(View view, String str, String str2) {
        return a(view, str, str2, false);
    }
}
