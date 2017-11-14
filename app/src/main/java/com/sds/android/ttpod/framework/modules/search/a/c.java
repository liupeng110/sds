package com.sds.android.ttpod.framework.modules.search.a;

import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.support.search.a.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;

/* SearchTaskInfoUtils */
public class c {
    public static b a(MediaItem mediaItem) {
        b bVar = new b();
        bVar.a((Object) mediaItem);
        bVar.a(mediaItem);
        return bVar;
    }

    public static b a(MediaItem mediaItem, String str, String str2) {
        b a = a(mediaItem);
        a.d(true);
        a.a(str);
        a.b(str2);
        return a;
    }

    public static com.sds.android.ttpod.framework.support.search.a.c b(MediaItem mediaItem) {
        com.sds.android.ttpod.framework.support.search.a.c cVar = new com.sds.android.ttpod.framework.support.search.a.c();
        cVar.a(mediaItem);
        cVar.a((Object) mediaItem);
        cVar.b(a.d());
        cVar.c(a.e());
        if ("artist".equals(mediaItem.getArtist())) {
            cVar.a(2);
        } else if ("album".equals(mediaItem.getAlbum())) {
            cVar.a(4);
        } else {
            cVar.a(6);
        }
        return cVar;
    }

    public static com.sds.android.ttpod.framework.support.search.a.c b(MediaItem mediaItem, String str, String str2) {
        com.sds.android.ttpod.framework.support.search.a.c b = b(mediaItem);
        b.d(true);
        b.a(str);
        b.b(str2);
        return b;
    }

    public static b c(MediaItem mediaItem) {
        b a = a(mediaItem);
        a.a(true);
        a.c(true);
        return a;
    }

    public static com.sds.android.ttpod.framework.support.search.a.c d(MediaItem mediaItem) {
        com.sds.android.ttpod.framework.support.search.a.c cVar = new com.sds.android.ttpod.framework.support.search.a.c();
        cVar.a(mediaItem);
        cVar.a((Object) mediaItem);
        cVar.a(true);
        cVar.c(true);
        return cVar;
    }
}
