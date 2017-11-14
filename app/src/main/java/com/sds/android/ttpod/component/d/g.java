package com.sds.android.ttpod.component.d;

import android.content.Context;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.d;
import com.sds.android.ttpod.component.d.e.a;
import java.util.List;

/* SingleChoicePopupsListAdapter */
public class g extends e<d> {
    private int a;

    public g(Context context, List<d> list) {
        super(context, list);
    }

    public void b(int i) {
        this.a = i;
        notifyDataSetChanged();
    }

    protected int a() {
        return R.layout.popups_list_item;
    }

    protected void a(a aVar, d dVar) {
        if (this.a == dVar.g()) {
            aVar.e().setImageResource(R.drawable.img_setting_single_choice_checked);
            aVar.e().setVisibility(0);
            return;
        }
        aVar.e().setVisibility(4);
    }
}
