package com.sds.android.ttpod.component.d;

import android.content.Context;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.d.e.a;
import java.util.List;

/* MultiChoicePopupsListAdapter */
public class d extends e<com.sds.android.ttpod.component.b.d> {
    public d(Context context, List<com.sds.android.ttpod.component.b.d> list) {
        super(context, list);
    }

    protected int a() {
        return R.layout.popups_list_item;
    }

    protected void a(a aVar, com.sds.android.ttpod.component.b.d dVar) {
        aVar.e().setVisibility(0);
        aVar.e().setImageResource(dVar.isChecked() ? R.drawable.icon_setting_menu_choice : R.drawable.icon_setting_menu_unchoice);
    }
}
