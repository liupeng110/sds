package com.sds.android.ttpod.activities.mv;

import com.sds.android.cloudapi.ttpod.data.MvData;
import com.sds.android.cloudapi.ttpod.data.MvListItem;
import com.sds.android.sdk.lib.util.e;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.framework.a;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* MvPlayData */
public class c implements Serializable {
    @com.b.a.a.c(a = "mvData")
    private MvData a;
    @com.b.a.a.c(a = "mvForPlay")
    private MvListItem b;

    public c(MvData mvData, MvListItem mvListItem) {
        this.a = mvData;
        this.b = mvListItem;
    }

    public MvListItem a() {
        return this.b;
    }

    public void a(MvListItem mvListItem) {
        this.b = mvListItem;
    }

    public MvData b() {
        return this.a;
    }

    public void a(MvData mvData) {
        this.a = mvData;
    }

    public boolean c() {
        return e.b(d());
    }

    public String d() {
        return this.b.getUrl();
    }

    public int e() {
        return this.a.getId();
    }

    public long f() {
        return this.a.getSongId();
    }

    public String g() {
        return this.a.getName();
    }

    public String h() {
        return this.a.getSingerName();
    }

    public List<MvListItem> i() {
        return this.a.getMvList();
    }

    public String j() {
        return g() + " - " + h();
    }

    public String k() {
        return a.A() + File.separator + com.sds.android.ttpod.framework.a.e.a(this.a) + ".dm";
    }

    public int l() {
        return this.b.getType();
    }

    public List<Integer> m() {
        List arrayList = new ArrayList();
        for (MvListItem type : this.a.getMvList()) {
            arrayList.add(Integer.valueOf(type.getType()));
        }
        return arrayList;
    }

    public static int a(int i) {
        switch (i) {
            case 0:
                return R.string.mv_standard_definition;
            case 1:
                return R.string.mv_high_definition;
            case 2:
                return R.string.mv_super_definition;
            default:
                return R.string.unknown;
        }
    }

    public boolean b(int i) {
        return m().contains(Integer.valueOf(i));
    }
}
