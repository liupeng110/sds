package com.sds.android.ttpod.fragment.main.findsong.singer;

import android.view.View;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.fragment.base.BaseSlidingTabFragment;
import com.sds.android.ttpod.framework.a.g;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;
import com.sds.android.ttpod.widget.RectangleImageView;
import com.sds.android.ttpod.widget.RoundedImageView;

/* SingerHeader */
public class b {
    private View a;
    private RectangleImageView b;
    private RoundedImageView c;
    private TextView d;
    private String e;

    public void a() {
        if (this.e != null) {
            int d = a.d();
            g.b(this.b, this.e, d, (int) (((float) d) / BaseSlidingTabFragment.DEFAULT_RATIO), R.drawable.img_background_ttpod_music_large_logo, 60);
            int a = a.a(64);
            g.a(this.c, this.e, a, a, (int) R.drawable.img_avatar_default);
        }
    }

    public View b() {
        return this.a;
    }

    public void c() {
        c.a(this.d, ThemeElement.TILE_TEXT);
    }
}
