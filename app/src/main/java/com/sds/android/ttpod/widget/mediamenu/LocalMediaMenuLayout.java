package com.sds.android.ttpod.widget.mediamenu;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import java.util.List;

public class LocalMediaMenuLayout extends OnlineMediaMenuLayout {
    public LocalMediaMenuLayout(Context context) {
        super(context);
    }

    public LocalMediaMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
        if (this.b != null) {
            boolean z = !m.a(((MediaItem) this.b).getLocalDataSource());
            a((int) R.id.media_menu_delete, z);
            a((int) R.id.media_menu_add, z);
            a((int) R.id.media_menu_ring, z);
        }
        super.a();
    }

    public List<a> b() {
        List<a> b = super.b();
        b.add(new a(R.id.media_menu_delete, 1, R.string.icon_media_menu_delete, R.string.delete));
        b.add(new a(R.id.media_menu_add, 2, R.string.icon_media_menu_add, R.string.add));
        b.add(new a(R.id.media_menu_ring, 3, R.string.icon_media_menu_ring, R.string.ringtone));
        return b;
    }
}
