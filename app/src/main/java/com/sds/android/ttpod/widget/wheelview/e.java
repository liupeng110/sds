package com.sds.android.ttpod.widget.wheelview;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

/* WheelRecycle */
public class e {
    private List<View> a;
    private List<View> b;
    private WheelView c;

    public e(WheelView wheelView) {
        this.c = wheelView;
    }

    public int a(LinearLayout linearLayout, int i, a aVar) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (aVar.a(i)) {
                i2++;
            } else {
                a(linearLayout.getChildAt(i2), i);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i3++;
                }
            }
            i++;
        }
        return i3;
    }

    public View a() {
        return a(this.a);
    }

    public View b() {
        return a(this.b);
    }

    public void c() {
        if (this.a != null) {
            this.a.clear();
        }
        if (this.b != null) {
            this.b.clear();
        }
    }

    private List<View> a(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList();
        }
        list.add(view);
        return list;
    }

    private void a(View view, int i) {
        int a = this.c.getViewAdapter().a();
        if ((i < 0 || i >= a) && !this.c.c()) {
            this.b = a(view, this.b);
            return;
        }
        while (i < 0) {
            i += a;
        }
        a = i % a;
        this.a = a(view, this.a);
    }

    private View a(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = (View) list.get(0);
        list.remove(0);
        return view;
    }
}
