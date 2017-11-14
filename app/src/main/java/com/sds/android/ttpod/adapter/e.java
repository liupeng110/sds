package com.sds.android.ttpod.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.sds.android.ttpod.framework.base.BaseActivity;
import com.sds.android.ttpod.framework.base.BaseFragment;
import java.util.List;

/* SlidingTabFragmentPagerAdapter */
public class e extends FragmentPagerAdapter implements com.sds.android.ttpod.widget.SlidingTabHost.a {
    private List<a> a;
    private Context b;

    /* SlidingTabFragmentPagerAdapter */
    public static class a {
        private long a;
        private int b;
        private CharSequence c;
        private int d;
        private Fragment e;

        public a(long j, int i, int i2, Fragment fragment) {
            this.a = j;
            this.b = i;
            this.d = i2;
            this.e = fragment;
        }

        public a(long j, CharSequence charSequence, int i, Fragment fragment) {
            this.a = j;
            this.c = charSequence;
            this.d = i;
            this.e = fragment;
        }

        public CharSequence a() {
            return this.c;
        }

        public void a(CharSequence charSequence) {
            this.c = charSequence;
        }

        public int b() {
            return this.b;
        }

        public int c() {
            return this.d;
        }

        public long d() {
            return this.a;
        }

        public Fragment e() {
            return this.e;
        }
    }

    public e(Context context, FragmentManager fragmentManager, List<a> list) {
        super(fragmentManager);
        this.b = context;
        this.a = list;
        if (this.a == null) {
            throw new IllegalArgumentException("FragmentBinders must not be null!");
        }
    }

    public Fragment getItem(int i) {
        return ((a) this.a.get(i)).e();
    }

    public void a() {
        this.b = null;
    }

    public void a(int i) {
        if (this.b != null) {
            BaseFragment topFragment = ((BaseActivity) this.b).getTopFragment();
            BaseFragment baseFragment = (BaseFragment) getItem(i);
            if (topFragment != null) {
                topFragment.setStatisticPage(baseFragment.getStatisitcPage());
                topFragment.setStatisticPageProperties(baseFragment.getStatisticPageProperties());
                topFragment.updateStatisticPageProperties();
                return;
            }
            ((BaseActivity) this.b).updateStatisticPage(baseFragment.getStatisitcPage());
        }
    }

    public int getCount() {
        return this.a.size();
    }

    public int b(int i) {
        return ((a) this.a.get(i)).c();
    }

    public int c(int i) {
        return ((a) this.a.get(i)).b();
    }

    public CharSequence getPageTitle(int i) {
        CharSequence a = ((a) this.a.get(i)).a();
        return a == null ? super.getPageTitle(i) : a.toString();
    }

    public long getItemId(int i) {
        return ((a) this.a.get(i)).d();
    }

    public int getItemPosition(Object obj) {
        for (a aVar : this.a) {
            if (aVar != null && obj.equals(aVar.e())) {
                return super.getItemPosition(obj);
            }
        }
        return -2;
    }
}
