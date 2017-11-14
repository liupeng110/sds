package com.sds.android.ttpod.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.c.a;
import com.sds.android.ttpod.framework.base.BaseApplication;

/* SingerPageHeaderFooterView */
public class g {
    private View a;
    private View b;
    private StateView c;
    private LayoutInflater d;
    private Context e;

    public g(Context context) {
        this.a = new View(context);
        this.b = new View(context);
        this.c = new StateView(context);
        this.e = context;
    }

    public View a() {
        this.a.setLayoutParams(new LayoutParams(-1, (int) BaseApplication.e().getResources().getDimension(R.dimen.singer_header_image_height)));
        return this.a;
    }

    public View a(int i, int i2) {
        int e = ((a.e() - i2) - a.a(50)) - a.a(50);
        if (e < i) {
            e = 0;
        } else {
            e -= i;
        }
        this.b.setLayoutParams(new LayoutParams(-1, e));
        return this.b;
    }

    public StateView b() {
        this.c.setLayoutParams(new LayoutParams(-1, ((a.e() - a.a(50)) - a.a(48)) - a.a(50)));
        this.c.setLoadingView(a(R.layout.singer_loadingview_load));
        this.c.setFailedView(a(R.layout.singer_loadingview_failed));
        this.c.setNodataView(a(R.layout.singer_loadingview_nodata));
        return this.c;
    }

    private View a(int i) {
        int e = a.e();
        this.d = LayoutInflater.from(this.e);
        View inflate = this.d.inflate(i, null);
        inflate.setLayoutParams(new LayoutParams(-1, e));
        return inflate;
    }
}
