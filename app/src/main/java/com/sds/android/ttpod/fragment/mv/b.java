package com.sds.android.ttpod.fragment.mv;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.video.VideoPlayManager;
import com.sds.android.ttpod.framework.a.b.d.j;

/* OnMVListItemClickListener */
public class b implements OnItemClickListener {
    private Context a;
    private String b;

    public b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Object tag = view.getTag(R.id.tag_view_key);
        if (tag instanceof MvData) {
            MvData mvData = (MvData) tag;
            new com.sds.android.ttpod.framework.a.b.b().a("mv_id", String.valueOf(mvData.getId())).a("mv_name", mvData.getName()).a();
            j.a("mv_origin", this.b);
            VideoPlayManager.a(this.a, mvData);
        }
    }
}
