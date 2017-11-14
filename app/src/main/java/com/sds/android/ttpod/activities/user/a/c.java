package com.sds.android.ttpod.activities.user.a;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.R;
import com.sds.android.ttpod.common.a.a;
import com.sds.android.ttpod.widget.wheelview.WheelView;
import com.sds.android.ttpod.widget.wheelview.b;
import com.sds.android.ttpod.widget.wheelview.d;
import java.util.List;

/* CitySelectorDialog */
public class c extends a implements b, d {
    private WheelView a;
    private WheelView b;
    private String[] c;
    private String[][] d;
    private boolean e = false;
    private String f = "";
    private String g = "";
    private int h;
    private int i;

    protected /* synthetic */ Object a() {
        return b();
    }

    public c(Context context, int i, a.a<c> aVar, int i2, a.a<c> aVar2) {
        super(context);
        a(i, (a.a) aVar, i2, (a.a) aVar2);
    }

    private void a(List<b> list) {
        int size = list.size();
        this.c = new String[size];
        this.d = new String[size][];
        for (int i = 0; i < list.size(); i++) {
            b bVar = (b) list.get(i);
            if (bVar != null) {
                if (m.a(bVar.b(), this.f)) {
                    this.h = i;
                }
                this.c[i] = bVar.b();
                List a = bVar.a();
                this.d[i] = new String[a.size()];
                for (int i2 = 0; i2 < a.size(); i2++) {
                    if (m.a((String) a.get(i2), this.g)) {
                        this.i = i2;
                    }
                    this.d[i][i2] = (String) a.get(i2);
                }
            }
        }
    }

    protected View a(Context context, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_city_selector, null);
        this.a = (WheelView) inflate.findViewById(R.id.wheel_state);
        this.b = (WheelView) inflate.findViewById(R.id.wheel_citys);
        this.a.a((b) this);
        this.a.a((d) this);
        this.b.a((b) this);
        this.b.a((d) this);
        g();
        return inflate;
    }

    private void g() {
        com.sds.android.sdk.lib.e.a.a(new com.sds.android.sdk.lib.e.a.a<Object, List<b>>(this, null) {
            final /* synthetic */ c a;

            protected /* synthetic */ Object onDoInBackground(Object obj) {
                return a(obj);
            }

            protected /* synthetic */ void onPostExecuteForeground(Object obj) {
                a((List) obj);
            }

            protected List<b> a(Object obj) {
                return a.a(this.a.getContext());
            }

            protected void a(List<b> list) {
                this.a.a((List) list);
                this.a.a(this.a.a, this.a.h, this.a.c);
                this.a.a(this.a.b, this.a.i, this.a.d[this.a.h]);
            }
        });
    }

    public void a(WheelView wheelView, int i, int i2) {
        if (!this.e) {
            a(wheelView, i2);
        }
    }

    public void a(WheelView wheelView) {
        this.e = true;
    }

    public void b(WheelView wheelView) {
        this.e = false;
        a(wheelView, wheelView.getCurrentItem());
    }

    private void a(WheelView wheelView, int i) {
        if (wheelView == this.a) {
            this.h = i;
            a(this.b, 0, this.d[this.h]);
        } else if (wheelView == this.b) {
            this.i = i;
        }
    }

    private void a(WheelView wheelView, int i, String[] strArr) {
        com.sds.android.ttpod.widget.wheelview.a.d cVar = new com.sds.android.ttpod.widget.wheelview.a.c(getContext(), strArr);
        cVar.a(17);
        cVar.d(14);
        cVar.c(Color.parseColor("#e0000000"));
        wheelView.setViewAdapter(cVar);
        wheelView.setCurrentItem(i);
    }

    public void a(String str) {
        this.f = str;
    }

    public void b(String str) {
        this.g = str;
    }

    protected c b() {
        return this;
    }

    public String c() {
        if (this.c != null) {
            return this.c[this.h];
        }
        return "";
    }

    public String d() {
        if (this.d != null) {
            return this.d[this.h][this.i];
        }
        return "";
    }
}
