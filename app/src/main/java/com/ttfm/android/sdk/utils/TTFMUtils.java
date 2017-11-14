package com.ttfm.android.sdk.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import java.util.ArrayList;
import java.util.List;

public class TTFMUtils {
    public static void measureView(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    public static boolean isAvilible(Context context, String str) {
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        List arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(((PackageInfo) installedPackages.get(i)).packageName);
            }
        }
        return arrayList.contains(str);
    }

    public static long getLoginUserId() {
        return b.g();
    }

    public static float countRating(int i) {
        float f = 0.0f;
        for (float f2 = ((float) (i * 5)) / 100.0f; f2 >= TTFMImageUtils.Middle_Scale; f2 -= TTFMImageUtils.Middle_Scale) {
            f += TTFMImageUtils.Middle_Scale;
        }
        return f;
    }
}
