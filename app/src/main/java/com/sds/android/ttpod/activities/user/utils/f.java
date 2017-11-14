package com.sds.android.ttpod.activities.user.utils;

import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.util.regex.Pattern;

/* ValidateUtil */
public class f {
    public static final a a = new a() {
        public boolean a(String str) {
            return m.b(str);
        }
    };
    public static final a b = new a() {
        public boolean a(String str) {
            return !m.a(str);
        }
    };
    public static final a c = new a() {
        public boolean a(String str) {
            return TextUtils.isDigitsOnly(str);
        }
    };
    public static final a d = new a() {
        public boolean a(String str) {
            return TextUtils.isDigitsOnly(str) && str.length() == 11;
        }
    };
    public static final a e = new a() {
        public boolean a(String str) {
            boolean z = true;
            if (Pattern.compile("[=<>!'@\"*?]").matcher(str).find()) {
                return false;
            }
            int i = 0;
            for (int length = str.length() - 1; length >= 0; length--) {
                char charAt = str.charAt(length);
                int i2 = (TTTextUtils.isLetterOrDigit(charAt) || charAt == ' ' || charAt == '-' || charAt == '_') ? 1 : 2;
                i += i2;
            }
            if (i < 1 || i > 30) {
                z = false;
            }
            return z;
        }
    };
    public static final a f = new a() {
        public boolean a(String str) {
            return m.a(str, 4, 20);
        }
    };

    /* ValidateUtil */
    public interface a {
        boolean a(String str);
    }

    public static boolean a(String str, int i, int i2, View view, int i3, a aVar) {
        if (aVar == null) {
            return true;
        }
        if (m.a(str)) {
            a(view, i3);
            com.sds.android.ttpod.component.d.f.a(i);
            return false;
        } else if (aVar.a(str)) {
            return true;
        } else {
            a(view, i3);
            com.sds.android.ttpod.component.d.f.a(i2);
            return false;
        }
    }

    private static void a(View view, int i) {
        if (view != null) {
            view.requestFocus();
            view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), i));
        }
    }
}
