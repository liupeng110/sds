package com.sds.android.ttpod.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import java.util.ArrayList;

public class NewSongPublishHeaderView extends SongPublishSectionView {
    public NewSongPublishHeaderView(Context context) {
        this(context, null);
    }

    public NewSongPublishHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NewSongPublishHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onThemeLoaded() {
        ColorStateList c = c.c(ThemeElement.COMMON_CONTENT_TEXT);
        if (c != null) {
            this.c = c;
        }
        setSectionItemTextColor(this.c);
    }

    public void a(ArrayList arrayList, int i) {
        super.a(arrayList, i);
        this.a.setVisibility(8);
    }

    protected void a(Context context) {
        inflate(context, R.layout.new_song_publish_list_header, this);
        this.a = (TextView) findViewById(R.id.new_song_publish_list_header_title);
        this.b = (SimpleGridView) findViewById(R.id.new_song_publish_list_header_grid_view);
        this.a.setText(R.string.select);
    }
}
