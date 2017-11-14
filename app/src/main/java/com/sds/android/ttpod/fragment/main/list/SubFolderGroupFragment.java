package com.sds.android.ttpod.fragment.main.list;

import android.os.Bundle;
import android.view.View;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.component.b.a;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.l;

public class SubFolderGroupFragment extends SubGroupListFragment {
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.findViewById(R.id.layout_create_playlist).setVisibility(8);
    }

    public void onDropDownMenuClicked(int i, a aVar) {
        super.onDropDownMenuClicked(i, aVar);
        switch (i) {
            case 4:
                b.a("my_menu_scan");
                l.as();
                return;
            default:
                return;
        }
    }
}
