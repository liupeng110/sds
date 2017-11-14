package com.sds.android.ttpod.framework.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.sdk.lib.util.j;
import com.sds.android.ttpod.activities.user.utils.SelectCountryActivity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/* ActivityManager */
public class a {
    private static a e;
    private List<Activity> a = new ArrayList();
    private Activity b = null;
    private Activity c = null;
    private Activity d = null;

    public static a a() {
        if (e == null) {
            e = new a();
        }
        return e;
    }

    public void a(Activity activity) {
        this.a.add(activity);
    }

    public void b(Activity activity) {
        if (this.c == activity) {
            this.c = null;
        }
        if (this.d == activity) {
            this.d = null;
        }
        this.a.remove(activity);
    }

    @TargetApi(16)
    public void b() {
        g.a("ActivityManager", "will exit app, finishActivities");
        for (Activity activity : this.a) {
            if (j.g()) {
                activity.setResult(0);
                activity.finishAffinity();
            }
            activity.finish();
        }
        this.a.clear();
    }

    public void c(Activity activity) {
        this.b = activity;
        if (this.c == activity) {
            this.c = this.d;
        }
    }

    public void d(Activity activity) {
        this.d = this.c;
        this.c = activity;
    }

    public Activity c() {
        return this.b;
    }

    public boolean e(Activity activity) {
        return activity != null && activity == c();
    }

    public String d() {
        return f(this.b);
    }

    private String f(Activity activity) {
        if (activity == null) {
            return "null activity has no info";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(activity.getClass().getSimpleName());
        stringBuilder.append(":{");
        try {
            stringBuilder.append(", mToken").append(a(activity, "mToken"));
            stringBuilder.append(", isFinished=").append(activity.isFinishing());
            stringBuilder.append(", action=").append(activity.getIntent());
            stringBuilder.append(", mWindow=").append(activity.getWindow().toString());
            stringBuilder.append(", mWindowManager=").append(activity.getWindowManager());
            stringBuilder.append(", mCallingActivity=").append(activity.getCallingActivity());
            stringBuilder.append(", mWindowAdded=").append(a(activity, "mWindowAdded"));
            stringBuilder.append(", mVisibleFromClient=").append(a(activity, "mVisibleFromClient"));
            stringBuilder.append(", mActivityInfo=").append(a(activity, "mActivityInfo"));
            Bundle extras = activity.getIntent().getExtras();
            if (extras != null) {
                stringBuilder.append(", extra=");
                for (String str : extras.keySet()) {
                    stringBuilder.append(str).append(SelectCountryActivity.SPLITTER).append(extras.get(str)).append("&");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (activity instanceof BaseActivity) {
            f fragmentBackStackManager = ((BaseActivity) activity).getFragmentBackStackManager();
            if (!(fragmentBackStackManager == null || fragmentBackStackManager.c() == null)) {
                stringBuilder.append(", topFragment=").append(fragmentBackStackManager.c());
            }
        }
        stringBuilder.append("}\r\n");
        return stringBuilder.toString();
    }

    private String a(Activity activity, String str) throws Exception {
        Field declaredField = Activity.class.getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj = declaredField.get(activity);
        if (obj != null) {
            return obj.toString();
        }
        return "null";
    }
}
