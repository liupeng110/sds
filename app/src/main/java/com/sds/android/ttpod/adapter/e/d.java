package com.sds.android.ttpod.adapter.e;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import java.util.List;

/* TabPagerAdapter */
public class d extends PagerAdapter implements com.sds.android.ttpod.widget.SlidingTabHost.a {
    private List<a> a;

    /* TabPagerAdapter */
    public static class a {
        private int a;
        private int b;

        public int a() {
            return this.a;
        }

        public int b() {
            return this.b;
        }
    }

    public d(List<a> list) {
        this.a = list;
    }

    public int b(int i) {
        return ((a) this.a.get(i)).b();
    }

    public int c(int i) {
        return ((a) this.a.get(i)).a();
    }

    public int getCount() {
        return this.a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return false;
    }
}
