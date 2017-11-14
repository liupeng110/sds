package com.sds.android.ttpod.widget.mediamenu;

import android.content.Context;
import android.util.AttributeSet;
import com.sds.android.ttpod.R;
import java.util.ArrayList;
import java.util.List;

public class OnlineMVMenuLayout extends MediaMenuLayout {
    public OnlineMVMenuLayout(Context context) {
        super(context);
    }

    public OnlineMVMenuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public List<a> b() {
        List<a> arrayList = new ArrayList();
        arrayList.add(new a(R.id.media_menu_delete, 0, R.string.icon_media_menu_delete, R.string.delete));
        return arrayList;
    }
}
