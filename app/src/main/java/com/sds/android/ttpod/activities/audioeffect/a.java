package com.sds.android.ttpod.activities.audioeffect;

import android.graphics.Color;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.widget.IconTextView;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* ActionBarUtils */
public class a {
    public static void a(com.sds.android.ttpod.component.a aVar) {
        aVar.a(Color.parseColor("#1b1b1b"));
        aVar.c(-1);
        IconTextView b = aVar.b();
        b.setText((int) R.string.icon_arrow_left);
        b.setTextColor(-1);
    }

    public static void b(com.sds.android.ttpod.component.a aVar) {
        aVar.a(Color.parseColor("#f6fafb"));
        aVar.c(BaseApplication.e().getResources().getColor(R.color.apshare_text_color_blue));
        aVar.b().setText((int) R.string.icon_arrow_left);
    }
}
