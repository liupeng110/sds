package com.sds.android.sdk.lib.util;

import android.content.SharedPreferences.Editor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* SharedPreferencesUtils */
public class l {
    private static final Method a = a();

    private static Method a() {
        try {
            return Editor.class.getMethod("apply", new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void a(Editor editor) {
        if (a != null) {
            try {
                a.invoke(editor, new Object[0]);
                return;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
        editor.commit();
    }
}
