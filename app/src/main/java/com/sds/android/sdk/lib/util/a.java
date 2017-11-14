package com.sds.android.sdk.lib.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* AutoDelloc */
public final class a {
    private static final ArrayList<String> a = new ArrayList();
    private static HashMap<Object, Integer> b = new HashMap();
    private static ArrayList<Object> c = new ArrayList();

    static {
        a.add("android.");
        a.add("java.");
        a.add("org.");
        a.add("javax.");
        a.add("com.android.");
        a.add("junit.");
        a.add("dalvik.");
    }

    private static void c(Object obj) {
        if (b.containsKey(obj)) {
            c.add(obj);
            return;
        }
        Class cls = obj.getClass();
        while (!a(cls.getName())) {
            try {
                a(obj, cls);
                cls = cls.getSuperclass();
            } catch (IllegalAccessException e) {
                g.c("AutoDelloc", "IllegalAccessException innerAutoDelloc obj=%s e=%s", obj.getClass().getSimpleName(), e.toString());
                return;
            } catch (Exception e2) {
                g.c("AutoDelloc", "Exception innerAutoDelloc obj=%s e=%s", obj.getClass().getSimpleName(), e2.toString());
                return;
            }
        }
    }

    private static void a(Object obj, Class<?> cls) throws IllegalAccessException {
        Field[] declaredFields = cls.getDeclaredFields();
        if (declaredFields != null) {
            for (Field field : declaredFields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    Object obj2 = field.get(obj);
                    if (obj2 != null) {
                        if (obj2 instanceof View) {
                            a((View) obj2);
                        } else if (obj2 instanceof BaseAdapter) {
                            c(obj2);
                        } else if (obj2 instanceof Handler) {
                            ((Handler) obj2).removeCallbacksAndMessages(null);
                        }
                    }
                }
            }
        }
    }

    @TargetApi(16)
    private static void a(View view) {
        view.setOnLongClickListener(null);
        view.setOnFocusChangeListener(null);
        if (view instanceof AdapterView) {
            AdapterView adapterView = (AdapterView) view;
            adapterView.setOnItemClickListener(null);
            adapterView.setOnItemLongClickListener(null);
            adapterView.setOnItemSelectedListener(null);
            int childCount = adapterView.getChildCount();
            for (int i = 0; i < childCount; i++) {
                a(adapterView.getChildAt(i));
            }
            adapterView.setAdapter(null);
        } else {
            view.setOnClickListener(null);
        }
        b(view);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(null);
        }
        if (view.getBackground() != null) {
            if (!j.f()) {
                view.getBackground().setCallback(null);
            }
            if (j.g()) {
                view.setBackground(null);
            } else {
                view.setBackgroundDrawable(null);
            }
        }
    }

    private static void b(View view) {
        view.setTag(null);
        try {
            Field declaredField;
            if (j.c()) {
                declaredField = View.class.getDeclaredField("mKeyedTags");
                declaredField.setAccessible(true);
                declaredField.set(view, null);
                return;
            }
            declaredField = View.class.getDeclaredField("sTags");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(view);
            if (obj instanceof WeakHashMap) {
                obj = ((WeakHashMap) obj).get(view);
                if (obj instanceof SparseArray) {
                    ((SparseArray) obj).clear();
                }
            }
        } catch (Exception e) {
            g.c("AutoDelloc", "Exception clearViewTag e=%s", e.toString());
        }
    }

    public static void a(Dialog dialog) {
        c(dialog);
    }

    public static void a(Fragment fragment) {
        c(fragment);
    }

    public static void a(Activity activity) {
        c(activity);
    }

    private static boolean a(String str) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            if (str.startsWith((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static void a(Object obj) {
        if ((obj instanceof Activity) || (obj instanceof Fragment) || (obj instanceof Dialog)) {
            int intValue;
            Integer num = (Integer) b.get(obj);
            if (num != null) {
                intValue = num.intValue() + 1;
            } else {
                intValue = 1;
            }
            b.put(obj, Integer.valueOf(intValue));
        }
    }

    public static void b(Object obj) {
        if ((obj instanceof Activity) || (obj instanceof Fragment) || (obj instanceof Dialog)) {
            int intValue;
            Integer num = (Integer) b.get(obj);
            if (num != null) {
                intValue = num.intValue() - 1;
            } else {
                intValue = 0;
            }
            if (intValue <= 0) {
                b.remove(obj);
                intValue = c.indexOf(obj);
                if (intValue >= 0) {
                    c.remove(intValue);
                    c(obj);
                    return;
                }
                return;
            }
            b.put(obj, Integer.valueOf(intValue));
        }
    }
}
