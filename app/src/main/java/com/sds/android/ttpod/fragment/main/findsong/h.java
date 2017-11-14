package com.sds.android.ttpod.fragment.main.findsong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.b.v;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

/* TVShowViewHolder */
public class h {
    private View a;
    private IconTextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private TextView f;
    private TextView g;

    public h(View view) {
        this.c = (TextView) view.findViewById(R.id.mv_name);
        this.e = (ImageView) view.findViewById(R.id.mv_img);
        this.d = (TextView) view.findViewById(R.id.mv_description);
        this.a = view.findViewById(R.id.menu_view);
        this.b = (IconTextView) view.findViewById(R.id.menu_icon_image);
        this.b.setText((int) R.string.icon_arrow_down);
        this.f = (TextView) view.findViewById(R.id.mv_duration);
        this.g = (TextView) view.findViewById(R.id.textview_mv_quality);
    }

    public void a() {
        c.a(this.c, ThemeElement.CARD_TEXT);
        c.a(this.d, ThemeElement.CARD_SUB_TEXT);
        c.a(this.b, ThemeElement.SONG_LIST_ITEM_MENU_IMAGE);
        v.a(this.b, (int) R.string.icon_arrow_down, ThemeElement.SONG_LIST_ITEM_SUB_TEXT);
    }

    public View b() {
        return this.a;
    }

    public TextView c() {
        return this.c;
    }

    public TextView d() {
        return this.d;
    }

    public ImageView e() {
        return this.e;
    }

    public TextView f() {
        return this.f;
    }

    public TextView g() {
        return this.g;
    }
}
