package com.sds.android.sdk.core.b;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Debug;
import com.sds.android.sdk.lib.request.BaseResult;
import com.sds.android.sdk.lib.request.p;
import com.sds.android.sdk.lib.util.EnvironmentUtils;
import com.tencent.stat.DeviceInfo;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/* ExceptionReporter */
public class b {

    /* ExceptionReporter */
    public interface a {
        void a(boolean z);
    }

    public static void a(String str, String str2, final a aVar) {
        try {
            a.a(a(str, str2)).a(new p<BaseResult>() {
                public void onRequestSuccess(BaseResult baseResult) {
                    aVar.a(baseResult.isSuccess());
                }

                public void onRequestFailure(BaseResult baseResult) {
                    aVar.a(baseResult.isSuccess());
                }
            });
        } catch (Exception e) {
            aVar.a(false);
            e.printStackTrace();
        }
    }

    private static HashMap<String, Object> a(String str, String str2) {
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("time", b());
        hashMap.put("package", EnvironmentUtils.a());
        hashMap.put("v", com.sds.android.sdk.lib.util.EnvironmentUtils.a.f());
        hashMap.put("f", "f" + com.sds.android.sdk.lib.util.EnvironmentUtils.a.b());
        hashMap.put(DeviceInfo.TAG_MID, Build.MANUFACTURER + "#" + Build.MODEL);
        hashMap.put("splus", VERSION.RELEASE);
        hashMap.put("s", com.sds.android.sdk.lib.util.EnvironmentUtils.b.e().get("s"));
        hashMap.put("rom", Build.PRODUCT);
        hashMap.put("build", "#" + com.sds.android.sdk.lib.util.EnvironmentUtils.a.d());
        hashMap.put("memory", a());
        hashMap.put("message", str2);
        hashMap.put("name", str);
        return hashMap;
    }

    private static String a() {
        double totalMemory = ((double) Runtime.getRuntime().totalMemory()) / 1048576.0d;
        double freeMemory = ((double) Runtime.getRuntime().freeMemory()) / 1048576.0d;
        double maxMemory = ((double) Runtime.getRuntime().maxMemory()) / 1048576.0d;
        double nativeHeapAllocatedSize = ((double) Debug.getNativeHeapAllocatedSize()) / 1048576.0d;
        DecimalFormat decimalFormat = new DecimalFormat("0.000");
        return "Runtime:{total:" + decimalFormat.format(totalMemory) + ",free:" + decimalFormat.format(freeMemory) + ",max:" + decimalFormat.format(maxMemory) + "},HeapAllocated:" + decimalFormat.format(nativeHeapAllocatedSize);
    }

    private static String b() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
