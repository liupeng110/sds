package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicRank;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.a.g;

public class MusicRankSectionView extends SimpleSongView {
    public MusicRankSectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MusicRankSectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void b(View view, Object obj) {
        MusicRank musicRank = (MusicRank) obj;
        ImageView imageView = (ImageView) view.findViewById(R.id.item_picture);
        ((TextView) view.findViewById(R.id.item_name)).setText(musicRank.getTitle());
        g.a(imageView, musicRank.getPicUrl(), a.d() / 4, a.d() / 4, (int) R.drawable.img_background_song_publish);
    }

    protected int a() {
        return R.string.rank;
    }
}
