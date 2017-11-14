package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.sdk.lib.util.f;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.component.b.a.b;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.text.TTTextUtils;

/* MediaInfoEditDialog */
public class g extends p {
    private View a;
    private EditText b;
    private EditText c;
    private EditText d;
    private EditText e;
    private EditText f;
    private EditText g;
    private EditText h;

    protected /* synthetic */ Object a() {
        return b();
    }

    public g(Context context, final MediaItem mediaItem, OnDismissListener onDismissListener) {
        super(context);
        a(mediaItem);
        setTitle((int) R.string.media_info);
        a((int) R.string.save, new a<g>(this) {
            final /* synthetic */ g b;

            public void a(g gVar) {
                l.aS();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_MUSIC_INFO_SAVE.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
                sUserEvent.setPageParameter(true);
                sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getLocalDataSource()).post();
                this.b.c(mediaItem);
            }
        }, (int) R.string.cancel, null);
        setOnDismissListener(onDismissListener);
    }

    private void a(final MediaItem mediaItem) {
        this.b = a(this.a, R.id.title, R.string.media_info_label_title, R.string.media_info_hint_title, mediaItem.getTitle());
        this.c = a(this.a, R.id.artist, R.string.media_info_label_artist, R.string.media_info_hint_artst, TTTextUtils.validateString(getContext(), mediaItem.getArtist()));
        this.d = a(this.a, R.id.album, R.string.media_info_label_album, R.string.media_info_hint_album, TTTextUtils.validateString(getContext(), mediaItem.getAlbum()));
        this.e = a(this.a, R.id.genre, R.string.media_info_label_genre, R.string.med_info_hint_genre, TTTextUtils.validateString(getContext(), mediaItem.getGenre()));
        this.f = a(this.a, R.id.track, R.string.media_info_label_track, R.string.media_info_hint_track, String.valueOf(mediaItem.getTrack()));
        this.g = a(this.a, R.id.year, R.string.media_info_label_year, R.string.media_info_hint_year, String.valueOf(mediaItem.getYear()));
        this.h = a(this.a, R.id.comment, R.string.media_info_label_comment, R.string.media_info_hint_comment, mediaItem.getComment());
        this.f.setInputType(2);
        this.g.setInputType(2);
        this.g.setFilters(new InputFilter[]{new LengthFilter(4)});
        this.a.findViewById(R.id.genre_spinner).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g b;

            public void onClick(View view) {
                this.b.b(mediaItem);
            }
        });
    }

    private void b(MediaItem mediaItem) {
        int i = 0;
        d[] dVarArr = new d[]{new d(0, R.string.media_info_genre_pop), new d(1, R.string.media_info_genre_rock), new d(2, R.string.media_info_genre_metal), new d(3, R.string.media_info_genre_dance), new d(4, R.string.media_info_genre_country), new d(5, R.string.media_info_genre_jazz), new d(6, R.string.media_info_genre_electronic), new d(7, R.string.media_info_genre_classical), new d(8, R.string.media_info_genre_bluce), new d(9, R.string.media_info_genre_opera), new d(10, R.string.media_info_genre_voice)};
        r rVar = new r(getContext(), dVarArr, null, null);
        int i2 = -1;
        Object obj = this.e.getText().toString();
        if (!TextUtils.isEmpty(obj)) {
            int length = dVarArr.length;
            while (i < length) {
                d dVar = dVarArr[i];
                if (obj.equals(dVar.d())) {
                    i2 = dVar.g();
                    break;
                }
                i++;
            }
        }
        rVar.setTitle((int) R.string.local_music_genre);
        rVar.c(i2);
        rVar.a(new b(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void a(com.sds.android.ttpod.component.b.a aVar, int i) {
                this.a.e.setText(aVar.d());
            }
        });
        rVar.show();
    }

    private void c(MediaItem mediaItem) {
        String a = com.sds.android.ttpod.framework.modules.core.audioeffect.d.a(mediaItem);
        mediaItem.setTitle(this.b.getText().toString());
        mediaItem.setArtist(a(this.c.getText().toString()));
        mediaItem.setAlbum(a(this.d.getText().toString()));
        mediaItem.setGenre(a(this.e.getText().toString()));
        mediaItem.setComment(this.h.getText().toString());
        try {
            mediaItem.setTrack(Integer.valueOf(Integer.parseInt(this.f.getText().toString())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mediaItem.setYear(Integer.valueOf(Integer.parseInt(this.g.getText().toString())));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        e.c(a, com.sds.android.ttpod.framework.modules.core.audioeffect.d.a(mediaItem));
        d(mediaItem);
        com.sds.android.ttpod.framework.base.a.b.a().a(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.UPDATE_MEDIA_ITEM, mediaItem));
    }

    private void d(MediaItem mediaItem) {
        com.sds.android.ttpod.framework.modules.core.audioeffect.a aVar;
        String i = e.i(com.sds.android.ttpod.framework.modules.core.audioeffect.d.a(mediaItem));
        try {
            if (!m.a(i)) {
                aVar = (com.sds.android.ttpod.framework.modules.core.audioeffect.a) f.a(i, com.sds.android.ttpod.framework.modules.core.audioeffect.a.class);
                if (aVar != null) {
                    aVar.b(mediaItem.getArtist());
                    aVar.c(mediaItem.getTitle());
                    com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT, mediaItem, aVar, Boolean.valueOf(false)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        aVar = null;
        if (aVar != null) {
            aVar.b(mediaItem.getArtist());
            aVar.c(mediaItem.getTitle());
            com.sds.android.ttpod.framework.base.a.b.a().b(new com.sds.android.ttpod.framework.base.a.a(com.sds.android.ttpod.framework.modules.a.SAVE_EFFECT, mediaItem, aVar, Boolean.valueOf(false)));
        }
    }

    protected View a(Context context) {
        this.a = View.inflate(context, R.layout.dialog_media_info_edit, null);
        return this.a;
    }

    private EditText a(View view, int i, int i2, int i3, CharSequence charSequence) {
        View findViewById = view.findViewById(i);
        ((TextView) findViewById.findViewById(R.id.label)).setText(i2);
        EditText editText = (EditText) findViewById.findViewById(R.id.content);
        editText.setText(charSequence);
        editText.setHint(i3);
        return editText;
    }

    protected g b() {
        return this;
    }

    private String a(String str) {
        if (getContext().getString(R.string.media_unknown).equals(str)) {
            return "<unknown>";
        }
        return str;
    }
}
