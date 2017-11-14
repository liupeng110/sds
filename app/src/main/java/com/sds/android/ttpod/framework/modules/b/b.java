package com.sds.android.ttpod.framework.modules.b;

import com.sds.android.sdk.lib.util.i;
import com.sds.android.ttpod.framework.modules.a;
import com.sds.android.ttpod.framework.modules.c;
import com.sds.android.ttpod.framework.modules.e;
import java.lang.reflect.Method;
import java.util.Map;

/* MusicLibraryModule */
public class b extends com.sds.android.ttpod.framework.base.b {
    protected c id() {
        return c.MUSIC_LIBRARY;
    }

    protected void onLoadCommandMap(Map<a, Method> map) throws NoSuchMethodException {
        Class cls = getClass();
        map.put(a.GET_MUSIC_CATEGORY, i.a(cls, "getMusicCategory", Integer.class, Integer.class));
        map.put(a.GET_MUSIC_SUB_CATEGORY, i.a(cls, "getSubMusicCategory", Long.class, Integer.class, Integer.class));
    }

    public void getMusicCategory(Integer num, Integer num2) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(num.intValue(), num2.intValue()), a.UPDATE_MUSIC_CATEGORY, id(), null);
    }

    public void getSubMusicCategory(Long l, Integer num, Integer num2) {
        e.a(com.sds.android.cloudapi.ttpod.a.i.a(l.longValue(), num.intValue(), num2.intValue()), a.UPDATE_MUSIC_SUB_CATEGORY, id(), null);
    }
}
