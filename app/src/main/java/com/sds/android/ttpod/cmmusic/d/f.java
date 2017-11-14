package com.sds.android.ttpod.cmmusic.d;

import android.os.Handler;
import android.os.Looper;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.a.a;
import com.sds.android.ttpod.framework.base.a.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.MediaStorage;
import java.util.ArrayList;
import java.util.List;

/* PlayMedia */
public class f {
    private static Handler a = new Handler(Looper.getMainLooper());

    public static void a(final String str, final String str2, final String str3) {
        a.post(new Runnable() {
            public void run() {
                List arrayList = new ArrayList();
                if (!m.a(str)) {
                    arrayList.add(k.a(str, str2, str3, 0));
                    f.b(arrayList, 0);
                }
            }
        });
    }

    private static boolean b(List<MediaItem> list, int i) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        if (i >= list.size()) {
            i = 0;
        }
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.SYNC_NET_TEMPORARY_GROUP, list));
        b.a().a(new a(com.sds.android.ttpod.framework.modules.a.PLAY_GROUP, MediaStorage.GROUP_ID_ONLINE_TEMPORARY, list.get(i)));
        return true;
    }
}
