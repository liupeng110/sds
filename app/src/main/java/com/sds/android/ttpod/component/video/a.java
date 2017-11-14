package com.sds.android.ttpod.component.video;

import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import java.util.ArrayList;
import java.util.List;

/* IdOnlyMvData */
public class a extends MvData {
    private List<MvListItem> a = new ArrayList();

    /* IdOnlyMvData */
    private static final class a extends MvListItem {
        public a(int i) {
            super(0, "FakeMvListItem", 0, 0, 0, "FakeMvListItem", 0, 0, "", i, "FakeMvListItem");
        }
    }

    public a(Integer num) {
        setId(num.intValue());
        this.a.add(new a(0));
        this.a.add(new a(1));
        this.a.add(new a(2));
    }

    public List<MvListItem> getMvList() {
        return this.a;
    }
}
