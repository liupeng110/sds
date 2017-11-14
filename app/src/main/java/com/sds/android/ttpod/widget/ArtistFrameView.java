package com.sds.android.ttpod.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.framework.modules.skin.view.AnimTransView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.b.e;
import com.sds.android.ttpod.framework.modules.theme.c;

public class ArtistFrameView extends RelativeLayout {
    private AnimTransView a = ((AnimTransView) findViewById(R.id.imageview_playcontrolbar_artist));
    private ImageView b = ((ImageView) findViewById(R.id.imageview_play_bar_artist_mask));
    private int c;

    public ArtistFrameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.layout_artist_frame, this);
    }

    public AnimTransView getAnimTransView() {
        return this.a;
    }

    public ImageView getMaskView() {
        return this.b;
    }

    public void a() {
        e b = c.b(ThemeElement.PLAY_BAR_ARTIST);
        v.a(this.a, b);
        this.c = b != null ? b.g() : 0;
        v.a(this.b, c.a(ThemeElement.PLAY_BAR_ARTIS_MASK_IMAGE));
    }

    public int getCornerRadius() {
        return this.c;
    }
}
