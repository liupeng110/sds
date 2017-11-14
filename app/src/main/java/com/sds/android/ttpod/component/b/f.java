package com.sds.android.ttpod.component.b;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import com.igexin.download.Downloads;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.g;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.a.b.p;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.storage.environment.b;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.widget.mediamenu.MediaMenuLayout;

/* MediaItemMenuHolder */
public class f {
    private a a;
    private MediaMenuLayout b = null;

    /* MediaItemMenuHolder */
    public interface a {
        void a(MediaItem mediaItem, boolean z);
    }

    public f(View view) {
        if (view instanceof MediaMenuLayout) {
            this.b = (MediaMenuLayout) view;
        }
    }

    public void a(OnClickListener onClickListener) {
        if (this.b != null && this.b.getVisibility() == 0) {
            this.b.setMenuItemClickListener(onClickListener);
        }
    }

    public void a(MediaItem mediaItem) {
        if (this.b != null) {
            this.b.setData(mediaItem);
        }
        b(mediaItem);
    }

    public void b(MediaItem mediaItem) {
        boolean z = mediaItem != null && mediaItem.getFav();
        if (this.b != null && this.b.getVisibility() == 0) {
            IconTextView iconTextView = (IconTextView) this.b.a((int) R.id.media_menu_favor_icon);
            if (iconTextView != null) {
                iconTextView.setChecked(z);
                iconTextView.setTextColor(z ? Color.parseColor("#FF617C") : Color.parseColor("#FFFFFF"));
            }
        }
    }

    public void a(MediaItem mediaItem, int i) {
        a(mediaItem, i, 0);
    }

    private void a(MediaItem mediaItem, int i, int i2) {
        boolean z = true;
        if (mediaItem.isThirdParty()) {
            b(mediaItem);
            com.sds.android.ttpod.component.d.f.a("第三方无法歌曲不能收藏！");
            return;
        }
        boolean fav = mediaItem.getFav();
        if (!mediaItem.isOnline() || b.av()) {
            mediaItem.setFav(!fav);
            if (fav) {
                g.b(mediaItem, false);
            } else {
                g.a(mediaItem, true);
            }
            p.a(i + 1);
            if (this.a != null) {
                boolean z2;
                a aVar = this.a;
                if (fav) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                aVar.a(mediaItem, z2);
            }
            b(mediaItem);
            s sVar = s.PAGE_NONE;
            if (fav) {
                z = false;
            }
            a(sVar, mediaItem, z, i2);
            return;
        }
        b(mediaItem);
        com.sds.android.ttpod.b.f.a(true);
        a(s.PAGE_CIRCLE_LOGIN, mediaItem, false, i2);
    }

    private void a(s sVar, MediaItem mediaItem, boolean z, int i) {
        SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_FAVORITE.getValue(), s.PAGE_NONE.getValue(), sVar.getValue());
        sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getSongID());
        sUserEvent.append(Downloads.COLUMN_STATUS, Integer.valueOf(z ? 1 : 0));
        sUserEvent.append("press_type", Integer.valueOf(i));
        sUserEvent.setPageParameter(true);
        sUserEvent.post();
    }
}
