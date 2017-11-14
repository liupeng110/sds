package com.sds.android.ttpod.activities.musiccircle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.AlbumData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

/* AlbumDetailHeader */
public class a extends d {
    private TextView a = ((TextView) c().findViewById(R.id.album_publish_time));

    public a(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(context, layoutInflater, viewGroup);
        c().findViewById(R.id.header_layout_comment).setVisibility(8);
        c().findViewById(R.id.header_layout_favorite).setVisibility(8);
        c().findViewById(R.id.header_layout_share).setVisibility(8);
        this.a.setVisibility(0);
        c.a(this.a, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
        TextView textView = (TextView) c().findViewById(R.id.post_header_tweet);
        textView.setSingleLine(true);
        textView.setMaxLines(1);
    }

    public void a(AlbumData albumData) {
        a(albumData.getPicUrl());
        d(albumData.getDescription());
        c(albumData.getSingerName());
        a(null);
        b(albumData.getSingerPicUrl());
        this.a.setText(this.a.getContext().getString(R.string.album_publish_time, new Object[]{albumData.getPublishDate()}));
    }

    public void a() {
        super.a();
        c.a(this.a, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
    }
}
