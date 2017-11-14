package com.sds.android.ttpod.framework.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* FragmentBackStackManager */
public class f implements g {
    private int a;
    private int b;
    private int c;
    private FragmentManager d;
    private BaseFragment e;
    private List<BaseFragment> f = new ArrayList();

    f(FragmentManager fragmentManager) {
        this.d = fragmentManager;
    }

    private void f(BaseFragment baseFragment) {
        this.f.add(baseFragment);
    }

    private BaseFragment i() {
        BaseFragment baseFragment = (BaseFragment) this.f.get(this.f.size() - 1);
        this.f.remove(baseFragment);
        return baseFragment;
    }

    boolean a() {
        return this.f.isEmpty();
    }

    private boolean g(BaseFragment baseFragment) {
        return this.e == baseFragment;
    }

    void b() {
        this.e = null;
        this.f.clear();
    }

    public BaseFragment c() {
        if (a()) {
            return this.e;
        }
        return (BaseFragment) this.f.get(this.f.size() - 1);
    }

    public List<BaseFragment> d() {
        return this.f;
    }

    private BaseFragment j() {
        int size = this.f.size();
        if (size > 1) {
            return (BaseFragment) this.f.get(size - 2);
        }
        if (size == 1) {
            return this.e;
        }
        return null;
    }

    final void a(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    final void a(BaseFragment baseFragment) {
        if (this.a == 0) {
            throw new IllegalArgumentException("you must call setLaunchFragmentAttr(int containerViewRes, int enterAnimRes, int popExitAnimRes) first");
        }
        baseFragment.markAsStatisticPage();
        baseFragment.setTopFragment(true);
        this.e = baseFragment;
        this.d.beginTransaction().add(this.a, (Fragment) baseFragment).commitAllowingStateLoss();
    }

    final BaseFragment e() {
        return this.e;
    }

    final void b(BaseFragment baseFragment) {
        this.e = baseFragment;
    }

    FragmentManager f() {
        return this.d;
    }

    public final void c(BaseFragment baseFragment) {
        if (!h(baseFragment)) {
            BaseFragment c = c();
            if (c != null) {
                c.setTopFragment(false);
                c.onStatsPause();
            }
            baseFragment.markAsStatisticPage();
            baseFragment.setTopFragment(true);
            f(baseFragment);
            this.d.beginTransaction().setCustomAnimations(this.b, 0, 0, this.c).add(this.a, (Fragment) baseFragment).addToBackStack(null).commitAllowingStateLoss();
        }
    }

    private boolean h(BaseFragment baseFragment) {
        BaseFragment c = c();
        if (c == null || !baseFragment.needSingleTop() || baseFragment.getClass() != c.getClass()) {
            return false;
        }
        if (c.getActivity() != null) {
            c.onNewBundle(baseFragment.getArguments());
        }
        baseFragment.finish();
        return true;
    }

    public final void d(BaseFragment baseFragment) {
        if (c() == baseFragment) {
            BaseFragment j = j();
            if (j != null && j.getView() != null) {
                j.getView().setVisibility(8);
            }
        }
    }

    public void e(BaseFragment baseFragment) {
        if (g(baseFragment)) {
            throw new IllegalArgumentException("Primary fragment must not be finish");
        }
        try {
            if (this.f.size() > 0 && this.f.get(this.f.size() - 1) == baseFragment) {
                this.d.popBackStack();
                BaseFragment i = i();
                if (i != null) {
                    i.setTopFragment(false);
                    i.onStatsPause();
                }
                i = c();
                if (i != null) {
                    i.setTopFragment(true);
                    i.onStatsResume();
                    i.pageStatisticBack();
                    View view = i.getView();
                    if (view != null) {
                        view.setVisibility(0);
                    }
                }
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public final void g() {
        BaseFragment j = j();
        if (j != null && j.getView() != null) {
            j.getView().setVisibility(0);
        }
    }

    public final void h() {
        BaseFragment j = j();
        if (j != null && j.getView() != null) {
            j.getView().setVisibility(8);
        }
    }
}
