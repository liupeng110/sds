package com.sds.android.ttpod.framework.modules.skin.d;

import android.graphics.Rect;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.text.Layout.Alignment;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.common.c.a;
import java.util.ArrayList;

/* ValueParser */
public final class m {
    public static int a(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            str = str.trim();
            if (str.startsWith("#")) {
                return (int) Long.parseLong(str.substring("#".length()), 16);
            }
            if (str.startsWith("0x") || str.startsWith("0X")) {
                return (int) Long.parseLong(str.substring("0X".length()), 16);
            }
            return (int) Long.parseLong(str);
        } catch (Exception e) {
            g.a("ValueParser", "parse int error, parse %s", str);
            return i;
        }
    }

    public static float a(String str, float f) {
        try {
            f = Float.parseFloat(str);
        } catch (Exception e) {
            g.a("ValueParser", "parse float error, parse %s", str);
        }
        return f;
    }

    public static int b(String str, int i) {
        int i2 = 1;
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        int a;
        String toLowerCase = str.trim().trim().toLowerCase();
        if (toLowerCase.endsWith("dp")) {
            a = a(toLowerCase, "dp", i);
        } else if (toLowerCase.endsWith("dip")) {
            a = a(toLowerCase, "dip", i);
        } else if (toLowerCase.endsWith("px")) {
            a = a(toLowerCase, "px", i);
            i2 = 0;
        } else if (toLowerCase.endsWith("%")) {
            i2 = 2;
            a = a(toLowerCase, "%", i);
        } else {
            a = a(toLowerCase, i);
            i2 = 0;
        }
        return (a & SupportMenu.USER_MASK) | (i2 << 16);
    }

    private static int a(String str, String str2, int i) {
        return a(str.substring(0, str.length() - str2.length()), i);
    }

    public static int a(int i, int i2) {
        int a = a(i);
        switch ((SupportMenu.CATEGORY_MASK & i) >> 16) {
            case 1:
                return a.a(a);
            case 2:
                return (int) (((float) a) * (((float) i2) / 100.0f));
            default:
                return a;
        }
    }

    public static int a(int i) {
        return (short) (SupportMenu.USER_MASK & i);
    }

    public static Rect a(String str, Rect rect) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return rect;
        }
        int i2;
        int i3;
        int i4;
        String trim = str.trim();
        if (rect != null) {
            i2 = rect.left;
            i3 = rect.top;
            i4 = rect.right;
            i = rect.bottom;
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
        }
        SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(' ');
        simpleStringSplitter.setString(trim);
        if (simpleStringSplitter.hasNext()) {
            i2 = b(simpleStringSplitter.next(), i2);
        }
        if (simpleStringSplitter.hasNext()) {
            i3 = b(simpleStringSplitter.next(), i3);
        }
        if (simpleStringSplitter.hasNext()) {
            i4 = b(simpleStringSplitter.next(), i4);
        }
        if (simpleStringSplitter.hasNext()) {
            i = b(simpleStringSplitter.next(), i);
        }
        return new Rect(i2, i3, i4, i);
    }

    public static int c(String str, int i) {
        if (str == null) {
            return i;
        }
        SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(' ');
        simpleStringSplitter.setString(str);
        int i2 = 0;
        while (simpleStringSplitter.hasNext()) {
            i2 = (i2 << 8) | a(simpleStringSplitter.next(), (int) ViewCompat.MEASURED_STATE_MASK);
        }
        return (ViewCompat.MEASURED_SIZE_MASK & i2) | (ViewCompat.MEASURED_STATE_MASK - (i2 & ViewCompat.MEASURED_STATE_MASK));
    }

    public static int[] a(String str, int[] iArr) {
        if (str != null) {
            SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(',');
            simpleStringSplitter.setString(str);
            ArrayList arrayList = new ArrayList();
            while (simpleStringSplitter.hasNext()) {
                arrayList.add(Integer.valueOf(c(simpleStringSplitter.next(), 0)));
            }
            int size = arrayList.size();
            if (size > 0) {
                iArr = new int[size];
                for (int i = 0; i < size; i++) {
                    iArr[i] = ((Integer) arrayList.get(i)).intValue();
                }
            }
        }
        return iArr;
    }

    public static Alignment a(String str, Alignment alignment) {
        if ("Center".equals(str)) {
            return Alignment.ALIGN_CENTER;
        }
        if ("Normal".equals(str)) {
            return Alignment.ALIGN_NORMAL;
        }
        if ("Opposite".equals(str)) {
            return Alignment.ALIGN_OPPOSITE;
        }
        return alignment;
    }

    public static boolean a(String str, boolean z) {
        if ("false".equalsIgnoreCase(str)) {
            return false;
        }
        if ("true".equalsIgnoreCase(str)) {
            return true;
        }
        if (a(str, z ? 1 : 0) != 1) {
            return false;
        }
        return true;
    }
}
