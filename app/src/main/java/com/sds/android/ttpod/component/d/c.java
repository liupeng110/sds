package com.sds.android.ttpod.component.d;

import android.app.Activity;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.fragment.main.list.d;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* MediaMenuMoreClickEvent */
public class c {
    private BaseActivity a;
    private MediaItem b;
    private int c;

    public c(Activity activity, MediaItem mediaItem, int i) {
        this.a = (BaseActivity) activity;
        this.b = mediaItem;
        this.c = i;
    }

    public void a() {
        if (this.a != null && !this.a.isFinishing() && this.b != null) {
            boolean a = e.a(this.b.getLocalDataSource());
            if (this.b.isOnline() && !a) {
                c();
            } else if (a) {
                b();
            }
        }
    }

    private void b() {
        com.sds.android.ttpod.component.d.a.e eVar = new com.sds.android.ttpod.component.d.a.e(this.a, Arrays.asList(new a[]{new a((int) R.id.media_menu_share, (int) R.string.icon_media_menu_share, (int) R.string.share), new a((int) R.id.media_menu_send, (int) R.string.icon_media_menu_send, (int) R.string.send), new a((int) R.id.media_menu_info, (int) R.string.icon_media_menu_info, (int) R.string.media_info)}), (int) R.string.cancel, null);
        eVar.setTitle((int) R.string.more);
        eVar.a(new d(this.a, this.b, eVar, null, this.c));
        l.aG();
        eVar.show();
    }

    private void c() {
        Object obj = !m.a(this.b.getLocalDataSource()) ? 1 : null;
        List arrayList = new ArrayList();
        if (obj == null && this.b.containMV()) {
            arrayList.add(new a((int) R.id.media_menu_related, (int) R.string.icon_media_menu_related, (int) R.string.media_item_menu_related));
        }
        if (obj == null && this.b.getSingerSFlag() != 2) {
            arrayList.add(new a((int) R.id.media_menu_singer, (int) R.string.icon_media_menu_singer, (int) R.string.media_item_menu_singer));
        }
        if (this.b.getAlbumId() != 0) {
            arrayList.add(new a((int) R.id.media_menu_album, (int) R.string.icon_media_menu_album, (int) R.string.media_item_menu_album));
        }
        com.sds.android.ttpod.component.d.a.e eVar = new com.sds.android.ttpod.component.d.a.e(this.a, arrayList, (int) R.string.cancel, null);
        eVar.setTitle((int) R.string.more);
        eVar.a(new d(this.a, this.b, eVar, null, this.c));
        l.aG();
        eVar.show();
    }
}
