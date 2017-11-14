package com.sds.android.ttpod.component.d.a;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import com.sds.android.sdk.core.statistic.SUserEvent;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a.a;
import com.sds.android.ttpod.framework.a.b.l;
import com.sds.android.ttpod.framework.a.b.r;
import com.sds.android.ttpod.framework.a.b.s;
import com.sds.android.ttpod.framework.modules.skin.d.d;
import com.sds.android.ttpod.media.mediastore.MediaItem;
import com.sds.android.ttpod.media.mediastore.old.MediaStore.MediasColumns;
import com.sds.android.ttpod.media.text.TTTextUtils;
import java.text.DecimalFormat;

/* MediaInfoDialog */
public class f extends p {
    private View a;

    protected /* synthetic */ Object a() {
        return b();
    }

    public f(Context context, final MediaItem mediaItem, final OnDismissListener onDismissListener) {
        super(context);
        a(context, mediaItem);
        setTitle((int) R.string.media_info);
        a((int) R.string.edit, new a<f>(this) {
            final /* synthetic */ f c;

            public void a(f fVar) {
                l.aR();
                SUserEvent sUserEvent = new SUserEvent("PAGE_CLICK", r.ACTION_RIGHT_MENU_MUSIC_INFO_EDIT.getValue(), s.PAGE_NONE.getValue(), s.PAGE_NONE.getValue());
                sUserEvent.append(MediasColumns.SONG_ID, mediaItem.getLocalDataSource());
                sUserEvent.setPageParameter(true);
                sUserEvent.post();
                com.sds.android.ttpod.component.d.f.b(this.c.getContext(), mediaItem, onDismissListener);
            }
        }, (int) R.string.cancel, null);
        setOnDismissListener(onDismissListener);
    }

    private void a(Context context, MediaItem mediaItem) {
        a(this.a, R.id.title, R.string.media_info_label_title, mediaItem.getTitle());
        a(this.a, R.id.artist, R.string.media_info_label_artist, TTTextUtils.validateString(context, mediaItem.getArtist()));
        a(this.a, R.id.album, R.string.media_info_label_album, TTTextUtils.validateString(context, mediaItem.getAlbum()));
        a(this.a, R.id.genre, R.string.media_info_label_genre, TTTextUtils.validateString(context, mediaItem.getGenre()));
        a(this.a, R.id.time, R.string.media_info_label_time, d.a((long) mediaItem.getDuration().intValue()));
        a(this.a, R.id.size, R.string.media_info_label_size, Formatter.formatFileSize(context, e.g(mediaItem.getLocalDataSource())));
        a(this.a, R.id.format, R.string.media_info_label_format, e.m(mediaItem.getLocalDataSource()));
        a(this.a, R.id.year, R.string.media_info_label_year, String.valueOf(mediaItem.getYear()));
        a(this.a, R.id.bitrate, R.string.media_info_label_bitrate, context.getString(R.string.media_info_content_bitrate, new Object[]{mediaItem.getBitRate()}));
        a(this.a, R.id.track, R.string.media_info_label_track, String.valueOf(mediaItem.getTrack()));
        String format = new DecimalFormat("####.0").format((double) (((float) mediaItem.getSampleRate().intValue()) / 1000.0f));
        a(this.a, R.id.samplerate, R.string.media_info_label_samplerate, context.getString(R.string.media_info_content_samplerate, new Object[]{format}));
        a(this.a, R.id.channel, R.string.media_info_label_channel, String.valueOf(mediaItem.getChannels()));
        TextView textView = (TextView) this.a.findViewById(R.id.comment_content);
        ((TextView) this.a.findViewById(R.id.path_content)).setText(mediaItem.getLocalDataSource());
        textView.setText(mediaItem.getComment());
    }

    protected View a(Context context) {
        this.a = View.inflate(context, R.layout.dialog_media_info, null);
        return this.a;
    }

    private void a(View view, int i, int i2, CharSequence charSequence) {
        View findViewById = view.findViewById(i);
        TextView textView = (TextView) findViewById.findViewById(R.id.label);
        TextView textView2 = (TextView) findViewById.findViewById(R.id.content);
        textView.setText(i2);
        textView2.setText(charSequence);
    }

    protected f b() {
        return this;
    }
}
