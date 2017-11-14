package com.sds.android.ttpod.framework.a.b;

import com.sds.android.cloudapi.ttpod.data.NewUser;
import com.sds.android.sdk.core.statistic.KVStatisticEvent;
import com.sds.android.sdk.core.statistic.KVStatisticEvent.ValueOperateMode;
import com.sds.android.sdk.core.statistic.SessionStatisticEvent;
import com.sds.android.sdk.core.statistic.SingleStatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticEvent;
import com.sds.android.sdk.core.statistic.StatisticManager;
import com.sds.android.sdk.lib.util.g;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.support.e;
import java.util.HashMap;
import java.util.Map;

/* StatisticUtils */
public class w {
    public static final HashMap<String, Integer> a = new HashMap();

    static {
        a.put("startup", Integer.valueOf(1));
        a.put("push", Integer.valueOf(1));
        a.put("error", Integer.valueOf(1));
        a.put(NewUser.LOCAL_LOGIN, Integer.valueOf(1));
        a.put("theme", Integer.valueOf(1));
        a.put("search", Integer.valueOf(1));
        a.put("find_music", Integer.valueOf(1));
        a.put("discovery", Integer.valueOf(1));
        a.put("share", Integer.valueOf(1));
        a.put("lyric_pic", Integer.valueOf(1));
        a.put("recommend", Integer.valueOf(1));
        a.put("download_manager", Integer.valueOf(1));
        a.put("mv", Integer.valueOf(1));
        a.put("sdk_ad", Integer.valueOf(1));
        a.put("musicCircle", Integer.valueOf(1));
        a.put("update", Integer.valueOf(1));
        a.put("song", Integer.valueOf(1));
        a.put("download", Integer.valueOf(1));
        a.put("start_time", Integer.valueOf(1));
        a.put("splash", Integer.valueOf(1));
        a.put("360", Integer.valueOf(1));
        a.put("guide", Integer.valueOf(1));
        a.put("audio_effect", Integer.valueOf(1));
        a.put("ktv", Integer.valueOf(1));
        a.put("library_music", Integer.valueOf(1));
        a.put("app", Integer.valueOf(1));
        a.put("song_tj", Integer.valueOf(1));
        a.put("song_rank", Integer.valueOf(1));
    }

    public static void a(String str, String str2, String str3) {
        a(str, str2, str3, 0);
    }

    public static void a(String str, String str2, String str3, long j) {
        a(str, str2, str3, j, 0);
    }

    public static void a(String str, String str2, String str3, long j, long j2) {
        g.d("StatisticUtils", "onEvent_noMessage  moduleId:" + str + " type:" + str2 + " origin:" + str3 + " optValue:" + j + " optValue2:" + j2);
        int a = a(str, -1);
        if (a >= 0) {
            Object instance = SingleStatisticEvent.instance(str, str2, str3, 1, a, 2);
            instance.setOptValue(j);
            instance.setOptValue2(j2);
            a(instance);
        }
    }

    public static void a(String str, String str2, String str3, long j, long j2, String str4, String str5) {
        g.d("StatisticUtils", "onEvent_withMessage moduleId:" + str + " type:" + str2 + " origin:" + str3 + " optValue:" + j + " optValue2:" + j2 + " optMessage:" + str4 + " optMessage2:" + str5);
        int a = a(str, -1);
        if (a >= 0) {
            Object instance = SingleStatisticEvent.instance(str, str2, str3, 1, a, 3);
            instance.setOptValue(j);
            instance.setOptValue2(j2);
            instance.setOptMessage(str4);
            instance.setOptMessage2(str5);
            a(instance);
        }
    }

    public static void a(String str, String str2, String str3, long j, String str4, String str5) {
        a(str, str2, str3, j, 0, str4, str5);
    }

    public static void a(int i, int i2, long j) {
        a(i, i2, j, ValueOperateMode.ADD);
    }

    public static void a(int i, int i2, long j, ValueOperateMode valueOperateMode) {
        g.a("StatisticUtils", "onKVStatisticEvent keyCode = %d, controlCode = %d, value = %d", Integer.valueOf(i), Integer.valueOf(i2), Long.valueOf(j));
        Object instance = KVStatisticEvent.instance(i, i2);
        instance.setLongValue(j, valueOperateMode);
        a(instance);
    }

    public static void a(int i, int i2, Enum enumR, ValueOperateMode valueOperateMode) {
        Object instance = KVStatisticEvent.instance(i, i2);
        if (ValueOperateMode.UNIQUE == valueOperateMode) {
            instance.setEnumValue(enumR, ValueOperateMode.UNIQUE);
        } else {
            instance.setEnumValue(enumR);
        }
        a(instance);
    }

    public static void a(int i, int i2, String str, ValueOperateMode valueOperateMode) {
        Object instance = KVStatisticEvent.instance(i, i2);
        if (ValueOperateMode.UNIQUE == valueOperateMode) {
            instance.setStringValue(str, ValueOperateMode.UNIQUE);
        } else {
            instance.setStringValue(str);
        }
        a(instance);
    }

    protected static void a(Object obj) {
        try {
            if (BaseApplication.e().f()) {
                c(obj);
            } else {
                b(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void c(Object obj) {
        if (obj instanceof StatisticEvent) {
            e.a(BaseApplication.e()).a((StatisticEvent) obj);
        } else if (obj instanceof Map) {
            e.a(BaseApplication.e()).a((Map) obj);
        }
    }

    protected static void b(Object obj) {
        if (obj instanceof StatisticEvent) {
            StatisticManager.getInstance().addEvent((StatisticEvent) obj);
        } else {
            p.a(obj.toString());
        }
    }

    public static SessionStatisticEvent b(String str, String str2, String str3, long j) {
        return SessionStatisticEvent.instance(str, str2, str3, a(str, 1), j);
    }

    private static int a(String str, int i) {
        if (a == null) {
            return i;
        }
        Integer num = (Integer) a.get(str);
        if (num != null) {
            return num.intValue();
        }
        return i;
    }

    public static void a(SessionStatisticEvent sessionStatisticEvent) {
        g.d("StatisticUtils", "putSessionStatisticEvent");
        a((Object) sessionStatisticEvent);
    }

    public static void a(Map<String, String> map) {
        a((Object) map);
    }

    public static void a() {
        StatisticManager.destroyInstance();
    }
}
