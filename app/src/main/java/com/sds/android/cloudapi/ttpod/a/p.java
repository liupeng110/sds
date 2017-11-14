package com.sds.android.cloudapi.ttpod.a;

import com.sds.android.cloudapi.ttpod.data.StringResult;
import com.sds.android.cloudapi.ttpod.result.DailyHotMVDataResult;
import com.sds.android.cloudapi.ttpod.result.MVCategoryDimensionResult;
import com.sds.android.cloudapi.ttpod.result.MVResult;
import com.sds.android.cloudapi.ttpod.result.MvCategoryResult;
import com.sds.android.cloudapi.ttpod.result.SingerMvResult;
import com.sds.android.sdk.lib.b.d;
import com.sds.android.sdk.lib.b.l;
import com.sds.android.sdk.lib.request.i;
import com.sds.android.sdk.lib.request.o;
import com.sds.android.sdk.lib.util.EnvironmentUtils.b;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import java.util.HashMap;
import java.util.Map;

/* MvAPI */
public class p {
    public static l<MvCategoryResult> a(long j, int i, int i2, int i3) {
        return new d(MvCategoryResult.class, "http://api.dongting.com/song/video/type/" + j).a("order", (Object) Integer.valueOf(i3)).a("page", Integer.valueOf(i)).a("size", Integer.valueOf(i2));
    }

    public static l<MVCategoryDimensionResult> a() {
        return new d(MVCategoryDimensionResult.class, "http://api.dongting.com/song/video/type");
    }

    public static l<DailyHotMVDataResult> a(int i, int i2) {
        return new d(DailyHotMVDataResult.class, "http://api.dongting.com/channel/channel/mvs").a("page", (Object) Integer.valueOf(i)).a("size", Integer.valueOf(i2));
    }

    public static o<MVResult> a(String str) {
        return new i(MVResult.class, String.format("http://api.dongting.com/song/video/%s/", new Object[]{str}));
    }

    public static o<SingerMvResult> b(String str) {
        return new i(SingerMvResult.class, String.format("http://api.dongting.com/sim/mv/%s/similarity", new Object[]{str})).a(b());
    }

    public static o<StringResult> a(String str, int i) {
        return new i(StringResult.class, String.format("http://api.dongting.com/song/video/%s/operate", new Object[]{str})).b(SocialConstants.PARAM_TYPE, Integer.valueOf(i)).a(b());
    }

    private static Map<String, Object> b() {
        Map e = b.e();
        String str = "tid";
        String str2 = "v";
        String str3 = "f";
        String str4 = DeviceInfo.TAG_MID;
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(str, e.get(str));
        hashMap.put(str2, e.get(str2));
        hashMap.put(str3, e.get(str3));
        hashMap.put(str4, e.get(str4));
        return hashMap;
    }
}
