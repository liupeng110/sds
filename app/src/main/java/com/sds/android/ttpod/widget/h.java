package com.sds.android.ttpod.widget;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import com.sds.android.sdk.lib.util.m;
import com.sds.android.ttpod.framework.a.b.b;
import com.sds.android.ttpod.framework.a.b.d.l;
import com.sds.android.ttpod.framework.base.i;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* TTViewPagerListener */
public class h implements OnPageChangeListener {
    private WeakReference<i> a;
    private int b;
    private ArrayList<a> c;

    /* TTViewPagerListener */
    public static class a {
        private String a;
        private String b;
        private String c;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public a(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public String a() {
            return this.c;
        }

        public String b() {
            return this.a;
        }

        public String c() {
            return this.b;
        }
    }

    public h(i iVar, int i, ArrayList<a> arrayList) {
        this.a = new WeakReference(iVar);
        this.b = i;
        this.c = arrayList;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (this.c == null || this.b < 0) {
            throw new IllegalArgumentException("TTViewPagerListener!");
        }
        b.a(((a) this.c.get(i)).a());
        l.a().b(((a) this.c.get(this.b)).c());
        this.b = i;
        l.a().a(((a) this.c.get(this.b)).c());
        i iVar = (i) this.a.get();
        if (iVar != null) {
            iVar.setTBSPage(((a) this.c.get(this.b)).c());
            String b = ((a) this.c.get(this.b)).b();
            if (!m.a(b)) {
                iVar.trackModule(b);
            }
        }
    }

    public void onPageScrollStateChanged(int i) {
    }
}
