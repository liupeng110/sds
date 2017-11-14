package com.sds.android.ttpod.component.d;

import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.List;

/* MediaMenuListBuilder */
public class b {
    private MediaItem a;

    public b(MediaItem mediaItem) {
        this.a = mediaItem;
    }

    public final List<a> a() {
        List arrayList = new ArrayList();
        if (this.a != null) {
            if (this.a.getFav()) {
                arrayList.add(new a((int) R.id.media_menu_favor, (int) R.string.icon_media_menu_favor_true, (int) R.string.favorite).a((int) R.color.media_menu_icon_checked));
            } else {
                arrayList.add(new a((int) R.id.media_menu_favor, (int) R.string.icon_media_menu_favor, (int) R.string.favorite));
            }
            if (m.a(this.a.getLocalDataSource())) {
                arrayList.addAll(b());
            } else {
                arrayList.addAll(c());
            }
        }
        return arrayList;
    }

    private List<a> b() {
        List<a> arrayList = new ArrayList();
        arrayList.add(new a((int) R.id.media_menu_download, (int) R.string.icon_media_menu_download, (int) R.string.download_song));
        arrayList.add(new a((int) R.id.media_menu_share, (int) R.string.icon_media_menu_share, (int) R.string.share));
        if (this.a.containMV()) {
            arrayList.add(new a((int) R.id.media_menu_mv, (int) R.string.icon_media_menu_mv, (int) R.string.media_item_menu_mv));
        }
        if (this.a.getSingerSFlag() != 2) {
            arrayList.add(new a((int) R.id.media_menu_singer, (int) R.string.icon_media_menu_singer, (int) R.string.media_item_menu_singer));
        }
        if (this.a.getAlbumId() != 0) {
            arrayList.add(new a((int) R.id.media_menu_album, (int) R.string.icon_media_menu_album, (int) R.string.media_item_menu_album));
        }
        arrayList.add(new a((int) R.id.media_menu_related, (int) R.string.icon_media_menu_related, (int) R.string.media_item_menu_related));
        return arrayList;
    }

    private List<a> c() {
        List<a> arrayList = new ArrayList();
        arrayList.add(new a((int) R.id.media_menu_delete, (int) R.string.icon_media_menu_delete, (int) R.string.delete));
        arrayList.add(new a((int) R.id.media_menu_add, (int) R.string.icon_media_menu_add, (int) R.string.add));
        arrayList.add(new a((int) R.id.media_menu_ring, (int) R.string.icon_media_menu_ring, (int) R.string.ringtone));
        arrayList.add(new a((int) R.id.media_menu_share, (int) R.string.icon_media_menu_share, (int) R.string.share));
        arrayList.add(new a((int) R.id.media_menu_send, (int) R.string.icon_media_menu_send, (int) R.string.send));
        arrayList.add(new a((int) R.id.media_menu_info, (int) R.string.icon_media_menu_info, (int) R.string.media_info));
        return arrayList;
    }
}
