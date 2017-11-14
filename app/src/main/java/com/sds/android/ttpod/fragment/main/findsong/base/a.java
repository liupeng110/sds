package com.sds.android.ttpod.fragment.main.findsong.base;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.modules.theme.ThemeElement;
import com.sds.android.ttpod.framework.modules.theme.c;

/* DimenLayoutHolder */
public class a {
    private final View a;
    private final TextView b = ((TextView) this.a.findViewById(R.id.select_dimension_name));
    private final View c = this.a.findViewById(R.id.select_dimension_indicate);
    private final GridView d = ((GridView) this.a.findViewById(R.id.select_dimension_grid));

    public a(View view) {
        this.a = view;
    }

    public void a() {
        c.a(this.b, ThemeElement.TILE_TEXT);
        c.a(this.c, ThemeElement.SONG_LIST_ITEM_INDICATOR);
        if (this.d.getAdapter() instanceof BaseAdapter) {
            ((BaseAdapter) this.d.getAdapter()).notifyDataSetChanged();
        }
    }

    public View b() {
        return this.a;
    }
}
