package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.cloudapi.ttpod.data.MusicCircleFirstPublish;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.a.g;

public class SongPublishSectionView extends SimpleSongView {
    public SongPublishSectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SongPublishSectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected int a() {
        return R.string.new_song_publish;
    }

    protected void b(View view, Object obj) {
        int i;
        MusicCircleFirstPublish musicCircleFirstPublish = (MusicCircleFirstPublish) obj;
        TextView textView = (TextView) view.findViewById(R.id.item_name);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_picture);
        View findViewById = view.findViewById(R.id.first_publish_flag);
        textView.setText(musicCircleFirstPublish.getTitle());
        textView.setVisibility(8);
        if (musicCircleFirstPublish.isFirstPublish()) {
            i = 0;
        } else {
            i = 8;
        }
        findViewById.setVisibility(i);
        g.a(imageView, musicCircleFirstPublish.getPicUrl(), a.d() / 4, a.d() / 4, (int) R.drawable.img_background_song_publish);
    }
}
