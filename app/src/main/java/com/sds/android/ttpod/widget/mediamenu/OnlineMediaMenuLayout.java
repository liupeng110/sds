package com.sds.android.ttpod.widget.mediamenu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.ArrayList;
import java.util.List;

public class OnlineMediaMenuLayout extends MediaMenuLayout<MediaItem> {
    public OnlineMediaMenuLayout(Context context) {
        super(context);
    }

    public OnlineMediaMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        boolean z = true;
        int i = 0;
        if (this.b != null) {
            boolean a = m.a(((MediaItem) this.b).getLocalDataSource());
            List a2 = new b((MediaItem) this.b).a();
            SparseBooleanArray sparseBooleanArray = new SparseBooleanArray(a2.size());
            sparseBooleanArray.put(R.id.media_menu_favor, true);
            sparseBooleanArray.put(R.id.media_menu_download, a);
            sparseBooleanArray.put(R.id.media_menu_share, a);
            boolean z2 = a && ((MediaItem) this.b).containMV();
            sparseBooleanArray.put(R.id.media_menu_mv, z2);
            if (!a || sparseBooleanArray.get(R.id.media_menu_mv)) {
                z2 = false;
            } else {
                z2 = true;
            }
            sparseBooleanArray.put(R.id.media_menu_related, z2);
            if (!a || ((MediaItem) this.b).getSingerSFlag() == 2 || a2.size() > 5) {
                z2 = false;
            } else {
                z2 = true;
            }
            sparseBooleanArray.put(R.id.media_menu_singer, z2);
            if (!a || ((MediaItem) this.b).getAlbumId() <= 0 || sparseBooleanArray.get(R.id.media_menu_mv) || sparseBooleanArray.get(R.id.media_menu_related)) {
                z2 = false;
            } else {
                z2 = true;
            }
            sparseBooleanArray.put(R.id.media_menu_album, z2);
            if (a2.size() <= 5) {
                z = false;
            }
            sparseBooleanArray.put(R.id.media_menu_more, z);
            while (i < sparseBooleanArray.size()) {
                int keyAt = sparseBooleanArray.keyAt(i);
                a(keyAt, sparseBooleanArray.get(keyAt));
                i++;
            }
        }
    }

    public List<a> b() {
        List<a> arrayList = new ArrayList();
        arrayList.add(new a(R.id.media_menu_favor, 0, R.string.icon_media_menu_favor, R.string.icon_media_menu_favor_true, R.string.media_item_menu_favor));
        arrayList.add(new a(R.id.media_menu_download, 1, R.string.icon_media_menu_download, R.string.media_item_menu_download));
        arrayList.add(new a(R.id.media_menu_share, 2, R.string.icon_media_menu_share, R.string.share));
        arrayList.add(new a(R.id.media_menu_mv, 3, R.string.icon_media_menu_mv, R.string.media_item_menu_mv));
        arrayList.add(new a(R.id.media_menu_singer, 6, R.string.icon_media_menu_singer, R.string.media_item_menu_singer));
        arrayList.add(new a(R.id.media_menu_more, 10, R.string.icon_media_menu_more, R.string.media_item_menu_more));
        arrayList.add(new a(R.id.media_menu_album, 5, R.string.icon_media_menu_album, R.string.media_item_menu_album));
        arrayList.add(new a(R.id.media_menu_related, 3, R.string.icon_media_menu_related, R.string.media_item_menu_related));
        return arrayList;
    }
}
