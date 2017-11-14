package com.sds.android.ttpod.component.b;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.d.j;
import com.sds.android.ttpod.framework.a.f;
import com.sds.android.ttpod.framework.a.k;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseApplication;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.framework.modules.theme.c.a;
import com.sds.android.ttpod.media.mediastore.AudioQuality;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.player.PlayStatus;
import com.sds.android.ttpod.widget.expandablelist.ActionExpandableListView;

/* MediaItemViewHolder */
public class g extends a {
    private static int[] s = new int[]{R.style.ListItem_First, R.style.ListItem_Second, R.style.ListItem_Third};
    private View a;
    private View b;
    private IconTextView c;
    private IconTextView d;
    private IconTextView e;
    private ImageView f;
    private IconTextView g;
    private IconTextView h;
    private IconTextView i;
    private IconTextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private IconTextView n;
    private TextView o;
    private View p;
    private IconTextView q;
    private boolean r = false;

    public g(View view) {
        this.a = view;
        this.b = view.findViewById(R.id.view_play_state);
        this.d = (IconTextView) view.findViewById(R.id.menu_view);
        this.e = (IconTextView) view.findViewById(R.id.check_view);
        this.f = (ImageView) view.findViewById(R.id.flag_online_view);
        this.g = (IconTextView) view.findViewById(R.id.flag_mv_view);
        this.h = (IconTextView) view.findViewById(R.id.flag_quality_view);
        this.i = (IconTextView) view.findViewById(R.id.downloadstate_view);
        this.j = (IconTextView) view.findViewById(R.id.drag_handle);
        this.k = (TextView) view.findViewById(R.id.tv_number);
        this.l = (TextView) view.findViewById(R.id.title_view);
        this.m = (TextView) view.findViewById(R.id.tv_media_item_singer);
        this.n = (IconTextView) view.findViewById(R.id.iv_media_item_fav);
        this.o = (TextView) view.findViewById(R.id.tv_media_item_fav_count);
        this.c = (IconTextView) view.findViewById(R.id.third_party_view);
        if (this.e != null) {
            this.e.setCheckable(true);
        }
        this.p = view.findViewById(R.id.expandable);
        this.p.setTag(new f(this.p));
        this.q = (IconTextView) view.findViewById(R.id.tv_hottest_indicator);
    }

    public View b() {
        return this.a;
    }

    public View c() {
        return this.b;
    }

    public IconTextView d() {
        return this.d;
    }

    public ImageView e() {
        return this.f;
    }

    public IconTextView f() {
        return this.g;
    }

    public IconTextView g() {
        return this.h;
    }

    public View h() {
        return this.p;
    }

    public void b(MediaItem mediaItem) {
        ((f) this.p.getTag()).a(mediaItem);
    }

    public void a(MediaItem mediaItem) {
        int i = 1;
        if (mediaItem != null) {
            AudioQuality quality = mediaItem.quality();
            int censorLevel = mediaItem.getCensorLevel();
            if (censorLevel < 1 || censorLevel > 3) {
                i = 0;
            }
            if (i != 0 || quality.ordinal() < AudioQuality.HIGH.ordinal()) {
                this.h.setVisibility(8);
                return;
            }
            this.h.setVisibility(0);
            this.h.setText(quality == AudioQuality.LOSSLESS ? R.string.icon_text_wusun : R.string.icon_text_gaozhi);
            this.h.setTextColor(quality == AudioQuality.LOSSLESS ? -2185667 : -8665764);
        }
    }

    public void c(MediaItem mediaItem) {
        Object obj = 1;
        if (mediaItem.getCensorLevel() != 1) {
            obj = null;
        }
        c.a(this.l, obj != null ? ThemeElement.SONG_LIST_ITEM_SUB_TEXT : ThemeElement.SONG_LIST_ITEM_TEXT);
    }

    public IconTextView i() {
        return this.i;
    }

    public TextView j() {
        return this.l;
    }

    public TextView k() {
        return this.m;
    }

    public void a(CharSequence charSequence, int i, boolean z) {
        int i2 = 0;
        if (charSequence != null) {
            if (charSequence.length() > 40) {
                charSequence = charSequence.subSequence(0, 40);
            }
            try {
                this.m.setText(charSequence);
                this.o.setText(f.a(i));
                this.o.setVisibility(z ? 0 : 4);
                IconTextView iconTextView = this.n;
                if (!z) {
                    i2 = 4;
                }
                iconTextView.setVisibility(i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(ActionExpandableListView actionExpandableListView, MediaItem mediaItem, int i, boolean z, Context context) {
        this.k.setText(String.valueOf(i + 1));
        this.l.setText(mediaItem.getTitle());
        this.c.setVisibility(mediaItem.hasCopyright() ? 8 : 0);
        this.f.setVisibility(8);
        a(context, mediaItem);
        a(mediaItem);
        if (mediaItem.hasCopyright() && !z) {
            mediaItem.setFav(k.a(mediaItem));
        }
        a(v.b());
        if (actionExpandableListView != null) {
            this.d.setText(actionExpandableListView.e() == i ? R.string.icon_arrow_top : R.string.icon_arrow_down);
        }
    }

    private void a(final Context context, final MediaItem mediaItem) {
        this.g.setVisibility(mediaItem.containMV() ? 0 : 8);
        if (mediaItem.containMV()) {
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ g c;

                public void onClick(View view) {
                    j.a("mv_origin", "mv_button");
                    b.a((BaseActivity) context, mediaItem, "mv_button");
                    VideoPlayManager.a(context, mediaItem);
                }
            });
        }
    }

    public void a(MediaItem mediaItem, PlayStatus playStatus, boolean z) {
        a(z, playStatus);
        if (mediaItem.hasCopyright()) {
            a(mediaItem.getArtist(), mediaItem.getUseCount().intValue(), true);
        } else {
            this.m.setText(mediaItem.getArtist());
        }
        this.l.setSelected(z);
        this.m.setSelected(z);
    }

    public IconTextView l() {
        return this.e;
    }

    public IconTextView m() {
        return this.j;
    }

    public void a(Integer num) {
        if (num == null || num.intValue() == 0) {
            this.l.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = BaseApplication.e().getResources().getDrawable(R.drawable.song_erro);
        int textSize = (int) this.l.getTextSize();
        drawable.setBounds(0, 0, textSize, textSize);
        this.l.setCompoundDrawables(drawable, null, null, null);
    }

    public void a(boolean z, PlayStatus playStatus) {
        this.l.setSelected(z);
        this.b.setVisibility(z ? 0 : 4);
    }

    public void a(Context context, int i) {
        if (i >= s.length) {
            this.k.setTextAppearance(context, R.style.ListItem_Normal_Style);
            this.k.setTextColor(c.d(ThemeElement.SONG_LIST_ITEM_SUB_TEXT));
        } else if (i >= 0) {
            this.k.setTextAppearance(context, s[i]);
        }
    }

    public void b(boolean z) {
        this.r = z;
    }

    public void a() {
        if (this.r) {
            o();
        } else {
            n();
        }
    }

    private void n() {
        if (this.e != null) {
            v.a(this.e, R.string.icon_unchecked, R.string.icon_checked, "", "", ThemeElement.SONG_LIST_ITEM_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
        v.a(this.d, (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        v.a(this.n, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, 153);
        c.a(this.a, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        c.a(this.k, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(this.l, ThemeElement.SONG_LIST_ITEM_TEXT);
        c.a(this.m, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(this.o, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        c.a(this.b, ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }

    private void o() {
        if (this.e != null) {
            v.a(this.e, R.string.icon_unchecked, R.string.icon_checked, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        }
        v.a(this.d, (int) R.string.icon_arrow_down, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        v.a(this.a, ThemeElement.PLAYER_MUSIC_LIST_BACKGROUND, ThemeElement.SONG_LIST_ITEM_BACKGROUND);
        v.a(this.k, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        v.a(this.l, ThemeElement.PLAYER_MUSIC_LIST_TEXT, ThemeElement.SONG_LIST_ITEM_TEXT);
        v.a(this.m, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        v.a(this.o, ThemeElement.PLAYER_MUSIC_LIST_SUB_TEXT, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        v.a(this.b, ThemeElement.PLAYER_MUSIC_LIST_INDICATOR, ThemeElement.SONG_LIST_ITEM_INDICATOR);
    }
}
